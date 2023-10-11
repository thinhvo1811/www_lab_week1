## Giới thiệu:
- ✍ Môn học: Lập trình WWW (Java)
- ✍ Chủ đề: Bài tập tuần 01
<br />

## Đề bài:
<img src="img/debai.png"/>
<br />

## Bài làm:

1. Chức năng đăng nhập
- ✍ Màn hình chính
<p></p>
<img src="img/mainLayout.png"/>
- ✍ Form đăng nhập
<p></p>
<img src="img/login.png"/>
- ✍ Khi đăng nhập không thành công sẽ báo lỗi
<p></p>
<img src="img/failLogin.png"/>
- ✍ Khi đăng nhập thành công với tài khoản không có quyền Admin sẽ chỉ hiển thị thông tin người đăng nhập
<p></p>
<img src="img/successLoginWithoutAdminRole.png"/>
- ✍ Khi đăng nhập thành công với tài khoản có quyền Admin sẽ hiển các chức năng thêm, xóa, sửa, cấp quyền, xem Role và Account
<p></p>
<img src="img/successfulLoginWithAdminRole.png"/>
- ✍ Khi chọn chức năng thêm sẽ hiển thị các bảng muốn thêm
<p></p>
<img src="img/addLayout.png"/>
- ✍ Khi chọn thêm Account thì sẽ hiển thị form cho người dùng điền thông tin
<p></p>
<img src="img/addAccountForm.png"/>
- ✍ Khi điền mã Account đã tồn tại thì sẽ báo lỗi
<p></p>
<img src="img/failAddAccount.png"/>
- ✍ Khi điền thông tin hợp lệ thì sẽ hiển thị thông báo thành công và danh sách các Account
<p></p>
<img src="img/successfulAddAccount.png"/>
- ✍ Các bảng còn lại cũng có thể thực hiện thêm tương tự
<p></p>
<img src="img/failAddRole.png"/>
<img src="img/successfulAddRole.png"/>
<img src="img/failAddGrantAccess.png"/>
<img src="img/successfulAddGrantAccess.png"/>
- ✍ Tuy việc thêm Log chỉ nên được thực hiện khi đăng nhập, nhưng đề bài yêu cầu thêm tất cả các bảng nên vẫn có chức năng thêm Log riêng
<p></p>
<img src="img/addLog.png"/>
<img src="img/successfulAddLog.png"/>
- ✍ Khi chọn chức năng sửa sẽ hiển thị các bảng muốn sửa
<p></p>
<img src="img/updateLayout.png"/>
- ✍ Khi chọn sửa Account thì sẽ yêu cầu chọn AccountID muốn sửa (combobox sẽ chỉ hiển thị các account có status không phải là DELETED)
<p></p>
<img src="img/selectAccountForUpdating.png"/>
- ✍ Sau đó sẽ hiển thị các thông tin của Account mà được phép sửa (trừ AccountID) 
<p></p>
<img src="img/updateAccountForm.png"/>
- ✍ Sau khi sửa thành công sẽ hiển thị thông báo thành công và danh sách các Account
<p></p>
<img src="img/successfulUpdateAccount.png"/>
- ✍ Các bảng còn tại cũng có thể thực hiện sửa tương tự 
<p></p>
- ✍ Khi chọn chức năng xóa sẽ hiển thị các bảng muốn xóa
<p></p>
<img src="img/deleteLayout.png"/>
- ✍ Khi chọn xóa Account thì sẽ yêu cầu chọn AccountID muốn xóa (combobox sẽ chỉ hiển thị các account có status không phải là DELETED)
<p></p>
<img src="img/selectAccountForDelete.png"/>
- ✍ Sau khi xóa thành công sẽ hiển thị thông báo thành công và danh sách các Account (thực chất xóa là cập nhật lại status cho Account đó thành DELETED)
<p></p>
<img src="img/successfulDeleteAccount.png"/>
- ✍ Các bảng còn tại cũng có thể thực hiện xóa tương tự 
<p></p>
- ✍ Khi chọn chức năng hiển thị các Role của một Account thì sẽ yêu cầu chọn AccountID muốn hiển thị các Role của nó
<p></p>
<img src="img/selectAccountForShowRole.png"/>
- ✍ Danh sách các Role của Account sẽ được hiển thị
<p></p>
<img src="img/successfulShowRowByAccount.png"/>
- ✍ Khi chọn chức năng hiển thị các Account của một Role thì sẽ yêu cầu chọn RoleID muốn hiển thị các Account của nó
<p></p>
<img src="img/selectRoleForShowAccount.png"/>
- ✍ Danh sách các Account của Role sẽ được hiển thị
<p></p>
<img src="img/successfulShowAccountByRole.png"/>
- ✍ Khi chọn chức năng cấp Role cho một Account thì sẽ yêu cầu chọn RoleID và AccountID muốn cấp 
<p></p>
<img src="img/selectRoleAndAccountForGrantAccess.png"/>
- ✍ Khi Account này đã được cấp Role này thì sẽ thông báo lỗi 
<p></p>
<img src="img/failGrantAccess.png"/>
- ✍ Nếu cấp thành công sẽ hiển thị danh sách các GrantAccess 
<p></p>
<img src="img/successfulGrantAccess.png"/>
- ✍ Nếu chọn chức năng đăng xuất thì sẽ trở lại trang màn hình chính và ghi Logout Time vào Log
<p></p>
<img src="img/mainLayout.png"/>





