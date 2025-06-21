<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>エンプロ良イ👍｜ユーザー管理画面</title>
<link rel="stylesheet" href="<c:url value='/css/style3.css'/>">
</head>

<body>
<!-- タイトル -->
       <div class="top">
          <h2>エンプロ良イ👍</h2>
        </div>
        
         <br><br><br>
      

<form id="user_pw_form" method="post" action=""<c:url value='/ChangePWServlet'/>">
       <!-- 選択画面部分 -->

       <div class="user_pw">
                	<br><br>

                  <input type="text" placeholder="現在のパスワード">
               		value="${user.currentPassword != null ? user.currentPassword : ''}" required>
                	<br><br>
              
                  <input type="text" placeholder="変更後のパスワード">
              		value="${user.newPassword != null ? user.newPassword : ''}" required>
                	<br><br>

                  <input type="text" placeholder="変更後のパスワード（2回目）">
               		value="${user.newPassword != null ? user.newPassword : ''}" required>
              		<br><br>

                <div class="update_button">
                        <input type="submit" value="変更">
                </div>
	      </div>
	        
	
</form>
			<!-- エラーメッセージ表示 -->
			<c:if test="${not empty error}">
    		<p style="color:red; font-weight:bold;">${error}</p>
</c:if>

</body>
</html>