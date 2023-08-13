<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>회원 등록</h3>
<form>
	<input type="text" name="uiId" placeholder="아이디"><br>
	<input type="text" name="uiName" placeholder="이름"><br>
	<input type="password" name="uiPwd" placeholder="비밀번호"><br>
	<textarea name="uiDesc" placeholder="소개"></textarea><br>
	<input type="date" name="uiBirth" placeholder="생년월일"><br>
	<button type="button" onclick="addUser()">등록</button>
	<button type="reset">취소</button>
</form>
<script>

function addUser(){
	const param = {
			uiId : document.querySelector('input[name=uiId]').value,
			uiName : document.querySelector('input[name=uiName]').value,
			uiPwd : document.querySelector('input[name=uiPwd]').value,
			uiDesc : document.querySelector('textarea[name=uiDesc]').value,
			uiBirth : document.querySelector('input[name=uiBirth]').value,
	}
	
	const json = JSON.stringify(param);
	const xhr = new XMLHttpRequest();
	xhr.open('POST','/user-info/insert');
	xhr.onreadystatechange = function(){
		if(xhr.readyState === 4){
			if(xhr.status===200){
				console.log(xhr.responseText);
				console.log(json);
				
			}
		}
	}
	xhr.send(json);
}
</script>
</body>
</html>