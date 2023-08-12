<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 수정</title>
</head>
<body>
	<h3>회원 수정</h3>

	<form id='updateForm'>
		<input type="hidden" name="uiNum" value="${userInfo.uiNum}"> <input
			type=text name='uiId' placeholder='아이디' value='${userInfo.uiId}'><br />
		<input type=text name='uiName' placeholder='이름'
			value='${userInfo.uiName}'><br /> <input type=password
			name='uiPwd' placeholder='비밀번호'><br />
		<textarea name=uiDesc placeholder='소개'>${userInfo.uiDesc}</textarea>
		<br /> <input type=date name=uiBirth placeholder='생년월일'
			value='${userInfo.uiBirth}'><br />
		<button>수정</button>
		<button type="reset">취소</button>
	</form>

	<script type='text/javascript'>
		$(document).ready(function() {
			$('#updateForm').on('submit', function(event) {
				event.preventDefault();

				var formData = {
					uiNum : $('input[name=uiNum]').val(),
					uiId : $('input[name=uiId]').val(),
					uiName : $('input[name=uiName]').val(),
					uiPwd : $('input[name=uiPwd]').val(),
					uiDesc : $('textarea[name=uiDesc]').val(),
					uiBirth : $('input[type=date][name=uiBirth]').val()
				};

				$.ajax({
					url : '/user-info/update',
					type : 'POST',
					data : JSON.stringify(formData),
					contentType : 'application/json;charset=utf-8;',

					success : function(result) {
						alert("수정되었습니다.");
						location.href = "/user-info";
					},

					error : function() {
						alert("에러가 발생했습니다.");
					}
				});
			});
		});
	</script>
</body>
</html>
