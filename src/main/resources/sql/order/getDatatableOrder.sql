select
    p.iddonnhannuoi orderId,
    p.idkhachhang userId,
    p.idnguoiquantri managerId,
    p.idvatnuoi petId,
    p.lydo reason,
    p.khaibaodieukien conditions,
    p.ngayguidon sentDate,
    p.ngayduyetnuoi approvalDate,
    p.ngaygiaovatnuoi deliveryDate,
    p.trangthai status,
    pet.tenvatnuoi petName,
    user.hoten name,
    user.sodienthoai phone,
    user.diachi address
from orders p inner join pet on pet.idvatnuoi = p.idvatnuoi
              inner join user on user.id = p.idkhachhang
where 1 = 1
