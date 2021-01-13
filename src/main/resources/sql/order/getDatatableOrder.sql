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
p.trangthai status
from orders p
where 1 = 1
