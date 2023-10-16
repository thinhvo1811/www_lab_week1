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
- ✍ Khi đăng nhập thành công với tài khoản không có quyền Admin sẽ chỉ hiển thị thông tin người đăng nhập cùng các quyền mà người đó có
<p></p>
<img src="img/successLoginWithoutAdminRole.png"/>
- ✍ Khi đăng nhập thành công với tài khoản có quyền Admin sẽ hiển thị các chức năng như quản lý tài khoản, hiển thị các quyền của một tài khoản, hiển thị các tài khoản của một quyền
<p></p>
<img src="img/successfulLoginWithAdminRole.png"/>
2. Chức năng quản lý tài khoản
<p></p>
- ✍ Khi chọn chức năng quản lý tài khoản sẽ hiển thị các chức năng như thêm, sửa, xóa, hiển thị danh sách tài khoản và cấp quyền
<p></p>
<img src="img/manageAccount.png"/>
- ✍ Khi chọn danh sách tài khoản sẽ hiển thị danh sách các tài khoản mà không có status là DELETED
<p></p>
<img src="img/accountList.png"/>
- ✍ Khi chọn chức năng thêm tài khoản thì sẽ hiển thị form cho người dùng điền thông tin
<p></p>
- ✍ Khi thêm tài khoản thì không cho người dùng chọn status mà status mặc định là ACTIVE
<p></p>
<img src="img/addAccountForm.png"/>
- ✍ Khi điền AccountID đã tồn tại thì sẽ báo lỗi
<p></p>
<img src="img/failAddAccount.png"/>
- ✍ Khi điền thông tin hợp lệ thì sẽ hiển thị thông báo thành công và danh sách các tài khoản
<p></p>
<img src="img/successfulAddAccount.png"/>
- ✍ Khi chọn chức năng sửa tài khoản thì sẽ yêu cầu chọn AccountID muốn sửa (combobox sẽ chỉ hiển thị id của các tài khoản có status không phải là DELETED)
<p></p>
<img src="img/selectAccountForUpdating.png"/>
- ✍ Sau đó sẽ hiển thị các thông tin của Account mà được phép sửa (trừ AccountID) 
<p></p>
<img src="img/updateAccountForm.png"/>
- ✍ Chỉ cho phép sửa status thành ACTIVE hoặc DEACTIVE 
<p></p>
<img src="img/updateAccountForm2.png"/>
- ✍ Sau khi sửa thành công sẽ hiển thị thông báo thành công và danh sách các tài khoản
<p></p>
<img src="img/successfulUpdateAccount.png"/>
- ✍ Khi chọn chức năng xóa tài khoản thì sẽ yêu cầu chọn AccountID muốn xóa (combobox sẽ chỉ hiển thị id của các tài khoản có status không phải là DELETED)
<p></p>
<img src="img/selectAccountForDelete.png"/>
- ✍ Sau khi xóa thành công sẽ hiển thị thông báo thành công và danh sách các tài khoản (thực chất xóa là cập nhật lại status cho tài khoản đó thành DELETED)
<p></p>
<img src="img/successfulDeleteAccount.png"/>
- ✍ Khi chọn chức năng cấp quyền cho một tài khoản thì sẽ yêu cầu chọn RoleID và AccountID muốn cấp 
<p></p>
<img src="img/selectRoleAndAccountForGrantAccess.png"/>
- ✍ Khi tài khoản này đã tồn tại quyền này thì sẽ thông báo lỗi 
<p></p>
<img src="img/failGrantAccess.png"/>
- ✍ Nếu cấp thành công sẽ hiển thị danh sách các GrantAccess 
<p></p>
<img src="img/successfulGrantAccess.png.png"/>
3. Chức năng hiển thị các quyền của một tài khoản
<p></p>
- ✍ Khi chọn chức năng hiển thị các quyền của một tài khoản thì sẽ yêu cầu chọn AccountID muốn hiển thị các quyền của nó
<p></p>
<img src="img/selectAccountForShowRole.png"/>
- ✍ Danh sách các quyền của tài khoản sẽ được hiển thị
<p></p>
<img src="img/successfulShowRowByAccount.png"/>
4. Chức năng hiển thị các tài khoản của một quyền
<p></p>
- ✍ Khi chọn chức năng hiển thị các tài khoản của một quyền thì sẽ yêu cầu chọn RoleID muốn hiển thị các tài khoản của nó
<p></p>
<img src="img/selectRoleForShowAccount.png"/>
- ✍ Danh sách các tài khoản của quyền sẽ được hiển thị
<p></p>
<img src="img/successfulShowAccountByRole.png"/>
5. Chức năng đăng xuất
<p></p>
- ✍ Nếu chọn chức năng đăng xuất thì sẽ trở lại trang màn hình chính và ghi Logout Time vào Log
<p></p>
<img src="img/mainLayout.png"/>
<img src="img/writeLog.png"/>





