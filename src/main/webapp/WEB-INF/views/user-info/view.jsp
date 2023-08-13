<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>유저 상세화면</title>
</head>
<body>
<h3>유저 상세화면</h3>
<div id="user-info">
    이름 : <span id="uiName"></span><br>
    생년월일 : <span id="uiBirth"></span><br>
    소개 : <span id="uiDesc"></span><br>
    가입일 : <span id="credat"></span><br>
    <button type="button" id="updateButton">수정</button>
    <button>삭제</button>
</div>
<script>

    function getUrlParam(key) {
        const url = new URL(window.location.href);
        return url.searchParams.get(key);
    }

    function loadView() {
        const xhr = new XMLHttpRequest();
        const uiNum = getUrlParam('uiNum');
        const url = '/user-info/view?uiNum=' + uiNum;


        xhr.open('GET', url);
        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4) {
                if (xhr.status === 200) {
                    const userInfo = JSON.parse(xhr.responseText);
                    console.log(xhr.responseText);
                    document.querySelector('#uiName').textContent = userInfo.uiName;
                    document.querySelector('#uiBirth').textContent = userInfo.uiBirth;
                    document.querySelector('#uiDesc').textContent = userInfo.uiDesc;
                    document.querySelector('#credat').textContent = userInfo.credat;
                    document.querySelector('#updateButton').onclick = function () {
                        location.href = '/user-info/update?uiNum=' + userInfo.uiNum;
                    };
                }
            }
        }
        xhr.send();
    }
    
    window.addEventListener('load', loadView);
 </script>
 </body>
 </html>
