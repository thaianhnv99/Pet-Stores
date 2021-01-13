select
o.iddonnhannuoi orderId,
o.idkhachhang userId,
o.idnguoiquantri managerId,
o.idvatnuoi petId,
o.ngayguidon sentDate,
o.ngaygiaovatnuoi deliveryDate,
o.trangthai status,
p.tenvatnuoi petName
from orders o inner join pet p on o.idvatnuoi = p.idvatnuoi
where 1 = 1
