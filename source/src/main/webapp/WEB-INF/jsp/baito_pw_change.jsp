<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>エンプロ良イ👍｜ユーザー管理画面</title>
<link rel="stylesheet" href="<c:url value='/css/baito_pw_change.css'/>">
</head>

<body>

	<br><br><br>

	<!-- タイトル -->
		<header>
			  <h1 id="logo">
			    <a href="tencho_calendar.jsp">
			      <img src="img/logo.png" width="300" height="auto" alt="エンプロ良イ👍">
			    </a>
			  </h1>
			  <ul id="nav">
			    <li><a href="/A5/CalenderServlet/">カレンダー</a></li>
			    <li><a href="/A5/ShiftServlet/">シフト</a></li>
			    <li><a href="/A5/EventServlet/">イベント</a></li>
			    <li><a href="/A5/ManualServlet/">マニュアル</a></li>
			    <li>
			      <details>
			        <summary class="details-summary">その他</summary>
			        <ul>
			          <li><a href="/A5/UserManageServlet/">ユーザー管理</a></li>
			          <li><a href="/A5/LogoutServlet/">ログアウト</a></li>
			        </ul>
			      </details>
			    </li>
			  </ul>
		</header>
		
	
	       
	        
	         <br><br><br>
	      
	
	<form id="user_pw_form" method="post" action=""<c:url value='/ChangePWServlet'/>">
	       <!-- 選択画面部分 -->
	
	       <div class="user_pw">
	                	<br><br>
	
	                  <input id="curPw"type="text" placeholder="現在のパスワード">
	               		
	                	<br><br>
	              
	                  <input id ="newPw" type="text" placeholder="変更後のパスワード">
	              		
	                	<br><br>
	
	                  <input id="newPw" type="text" placeholder="変更後のパスワード（2回目）">
	               		
	              		<br><br>
	
	                <div class="update_button">
	                        <input type="submit" value="変更">
	                </div>
		      </div>
		        
		
	</form>
				<!-- エラーメッセージ表示--> 
				<c:if test="${not empty error}">
	    		<p style="color:red; font-weight:bold;">${error}</p></c:if>
	    		
	    		
	    				<br>
			            <br>
			            <br>
			            <br>
			            <br>
			            <br>
			            <br>
			            <br>
			            <br>
			            <br>
			            <br>
			            <br>
			            <br>
			            <br>
			            <br>
			            <br>
			            <br>
			            <br>
			            <br>
			            <br>
			           



		   	<!-- フッター -->
		    <footer>
		    
		     	<div class="gotop">
					 <a href="#top"><img src="img/gotop.png" alt="ページトップへ戻る" width=70px height=auto></a>
				</div>
				
				<br>
		        <p>&copy; エンプロ良イ👍</p>
		        
		
		    </footer>
	
</body>
</html>