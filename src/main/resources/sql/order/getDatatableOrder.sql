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
pet.tenvatnuoi petName
from orders p inner join pet on pet.idvatnuoi = p.idvatnuoi
where 1 = 1
