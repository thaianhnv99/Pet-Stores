package com.myproject.authentic.controller;

import com.myproject.authentic.model.*;
import com.myproject.authentic.model.RolesDto;
import com.myproject.authentic.model.UserDto;
import com.myproject.authentic.model.RolesEntity;
import com.myproject.authentic.model.UserEntity;
import com.myproject.authentic.model.UserRolesEntity;
import com.myproject.authentic.repository.RolesRepository;
import com.myproject.authentic.repository.UserRepository;
import com.myproject.authentic.repository.UserRolesRepository;
import com.myproject.authentic.security.JwtAuthenticationProvider;
import com.myproject.common.Constant;
import com.myproject.common.dto.ResultInsideDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping(path = "/api/account")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    private JwtAuthenticationProvider jwtAuthenticationProvider;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RolesRepository rolesRepository;
    @Autowired
    private UserRolesRepository userRolesRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping(path = "/signup")
    public ResponseEntity<ResultInsideDTO> registerAccount(@RequestBody @Valid UserDto userDto) {
        ResultInsideDTO resultInsideDTO = new ResultInsideDTO();
        resultInsideDTO.setKey(Constant.RESPONSE_KEY.SUCCESS);
        if (userRepository.existsByUsername(userDto.getUsername())) {
            resultInsideDTO.setKey(Constant.RESPONSE_KEY.ERROR);
            resultInsideDTO.setMessages("Error: Username is already taken!");
            return new ResponseEntity<>(resultInsideDTO, HttpStatus.BAD_REQUEST);
        }
        if (userRepository.existsByEmail(userDto.getEmail())) {
            resultInsideDTO.setKey(Constant.RESPONSE_KEY.ERROR);
            resultInsideDTO.setMessages("Error: Email is already in use!");
            return new ResponseEntity<>(resultInsideDTO, HttpStatus.BAD_REQUEST);
        }
        // Create new user's account
        UserDto user = new UserDto(userDto.getUsername(),
                passwordEncoder.encode(userDto.getPassword()), userDto.getEmail());
        Set<String> strRoles = new HashSet<>();
        for (String role : userDto.getLstRoleInput()) {
            strRoles.add(role);
        }
        Set<RolesDto> roles = new HashSet<>();
        if (strRoles == null) {
            RolesEntity userRole = rolesRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole.toDTO());
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        RolesEntity adminRole = rolesRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole.toDTO());

                        break;
                    case "mod":
                        RolesEntity modRole = rolesRepository.findByName(ERole.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole.toDTO());

                        break;
                    default:
                        RolesEntity userRole = rolesRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole.toDTO());
                }
            });
        }
        UserEntity userEntity = userRepository.save(user.toEntity());
        roles.forEach(rolesDto -> {
            UserRolesEntity userRolesEntity = new UserRolesEntity();
            userRolesEntity.setRolesId(rolesDto.getId());
            userRolesEntity.setUserId(userEntity.getId());
            userRolesRepository.save(userRolesEntity);
        });
        return new ResponseEntity<>(resultInsideDTO, HttpStatus.OK);
    }

    @PostMapping(path = "/signin")
    public ResponseEntity<?> loginAccount(@RequestBody @Valid UserDto userDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userDto.getUsername(), userDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtAuthenticationProvider.generateJwtToken(authentication);
        JwtUserDetails userDetails = (JwtUserDetails) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());
        return new ResponseEntity<>(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles), HttpStatus.OK);
    }

}
