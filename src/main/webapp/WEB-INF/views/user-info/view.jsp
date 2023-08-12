<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>유저 상세화면</title>
</head>
<body>
    <h3>유저 상세화면</h3>
    <p>이름 : ${user.uiName}</p>
    <p>생년월일 : ${user.uiBirth}</p>
    <p>소개 : ${user.uiDesc}</p>
    <p>가입일 : ${user.credat}</p>
    
    <button type="button" onclick="location.href='/user-info/update?uiNum=${user.uiNum}'">수정</button>
    <button type="button" onclick="deleteUser()">삭제</button>
    
    <script>
        function deleteUser() {
            const result = confirm("정말로 이 유저를 삭제하시겠습니까?");
            if (result) {
                const xhr = new XMLHttpRequest();
                xhr.open('POST', '/user-info/delete');
                xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
                xhr.onreadystatechange = function() {
                    if (xhr.readyState === 4) {
                        if (xhr.status === 200) {
                            const response = JSON.parse(xhr.responseText);
                            if (response.msg === '유저 삭제 성공') {
                                alert(response.msg);
                                location.href = response.url;
                            } else {
                                alert(response.msg);
                            }
                        }
                    }
                };
                xhr.send(`uiNum=${user.uiNum}`);
            }
        }
    </script>
</body>
</html>
