<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>エンプロ良イ👍｜ユーザー登録画面</title>
<link rel="stylesheet" href="<c:url value='/css/tencho_user_reg.css'/>">

</head>

<body>

	<br><br><br><br><br>
	
<!-- タイトル -->
<header>
  <h1 id="logo">
    <a href="tencho_calendar.jsp">
      <img src="img/logo.png" width="300" height="auto" alt="エンプロ良イ👍">
    </a>
  </h1>
  <ul id="nav">
    <li><a href="<c:url value='/CalendarServlet'/>">カレンダー</a></li>
    <li><a href="<c:url value='/ShiftServlet'/>">シフト</a></li>
    <li><a href="<c:url value='/EventServlet'/>">イベント</a></li>
    <li><a href="<c:url value='/ManualServlet'/>">マニュアル</a></li>
    <li>
      <details>
        <summary class="details-summary">その他</summary>
        <ul>
          <li><a href="<c:url value='/UserRegistServlet'/>">ユーザー管理</a></li>
          <li><a href="<c:url value='/LogoutServlet'/>">ログアウト</a></li>
        </ul>
      </details>
    </li>
  </ul>
</header>
 
 	<br><br><br>
  

   
    

		<!-- エラーメッセージ表示 -->
		<c:if test="${not empty errMsg}">
		  <p style="color:red">${errMsg}</p>
		</c:if>

			<!-- ユーザー登録フォーム -->
		<form id="user_choice_form" method="POST" action="<c:url value='/UserRegistServlet' />">
			<div class="user_choice">
			    <p>追加するユーザーの種類を選択してください</p>
			
			    <label>
			      <input  type="radio" name="tencho_flag" value="1" 
			         ${tencho_flag == '1' ? 'checked' : ''}/>店長
			    </label>
			    
			    <label>
			      <input  type="radio" name="tencho_flag" value="0"
			        ${tencho_flag == '0' ? 'checked' : ''}/>店員
			    </label>
			
			    <br><br>
			
			<input id="name" type="text" name="user_name" placeholder="登録するユーザー名" 
			           value="${name}">
			
			    <br><br>
			
			    <input id="password" type="password" name="password" placeholder="登録するパスワード"
			           value="${pw}">
			
			    <br><br>
			
			 	<p id="output"></p>
			 	
			    <div class="regist_button">
			      <input type="submit" value="登録">
			      
			    </div>
			   
			  </div>
			  
			</form>
			
			 
			
		        
					
       
						        
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
		
		<div class="cafe_img">
		<img src="img/coffee.png" width="400" height="300">
		</div>
		
        <p>&copy; エンプロ良イ&#128077</p>
        

    </footer>
			
			
			       
			  
			    
			
			<script src="/A5/js/regist.js"></script>
			
	</body>
</html>