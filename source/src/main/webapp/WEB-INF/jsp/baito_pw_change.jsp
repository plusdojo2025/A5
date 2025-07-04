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

	<!-- タイトル -->
		<header>
		 <!-- ハンバーガーメニュー -->
                <!-- チェックボックスで切り替える -->
  <input type="checkbox" id="menu-toggle" class="menu-toggle" />
  <label for="menu-toggle" class="menu-icon">
    <span></span>
    <span></span>
    <span></span>
  </label>

  <!-- ナビゲーションメニュー -->
  <nav>
    <ul>
      <li><a href="#">ホーム</a></li>
      <li><a href="#">シフト</a></li>
      <li><a href="#">イベント</a></li>
      <li><a href="#">マニュアル</a></li>
    </ul>
  </nav>
			  <h1 id="logo">
			    <a href="tencho_calendar.jsp">
			      <img src="img/logo.png" width="300" height="auto" alt="エンプロ良イ👍">
			    </a>
			  </h1>
			  <ul id="nav">
			    <li><a href="<c:url value='/CalendarServlet'/>">カレンダー</a></li>
			     <li><a href="<c:url value='/ShiftServlet'/>">シフト</a></li>
			     <li><a href="<c:url value='/ChangePWServlet'/>">パスワード</a></li>
			      <li><a href="<c:url value='/ManualServlet' />">マニュアル</a></li>
			      <li><a href="<c:url value='/LogoutServlet' />">ログアウト</a></li>
			  </ul>
		</header>
		
	
	       
	        
	         <br><br><br>
	      
	
	<form id="user_pw_form" method="post" action="<c:url value='/ChangePWServlet'/>">
	       <!-- 選択画面部分 -->
	
	       <div class="user_pw">
	       
	                	<br><br>
	
	                  <input id="curPw" type="password" name="curPw" placeholder="現在のパスワード"
			           value="${pw}">
	               		
	                	<br><br>
	              
	                  <input id="newPw1" type="password" name="newPw1" placeholder="変更後のパスワード"
			           value="${pw}">
	              		
	                	<br><br>
	
	                  <input id="newPw2" type="password" name="newPw2" placeholder="変更後のパスワード（2回目）"
			           value="${pw}">
	               		
	              		<br><br>
	              		
	              	  <p id="output" style="color:red; font-weight: bold;"></p>
	
	                <div class="update_button">
	                        <input type="submit" value="変更" onclick="return kaku()">
	                </div>
	                
	                
	                
		      </div>
		        
		
	</form>
	${errMsg }
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
					 <a href="#top">
					 <img src="<c:url value='/img/gotop.png' />" alt="ページトップへ戻る" width="70" height="auto"></a>
				</div>
				
				<br>
				
				<div class="cafe_img">
					<img src="<c:url value='/img/coffee.png'/>"  width="400" height="300">
				</div>
				
		        <p>&copy; エンプロ良イ&#128077</p>
		        
		
		    </footer>
		    
		    <script>
		    function kaku(){
		    	cur = document.getElementById("curPw").value;
		    	new1 = document.getElementById("newPw1").value;
		    	new2 = document.getElementById("newPw2").value;
		        let output = document.getElementById("output");
		    	output.textContent = ""; // 前回のメッセージをクリア
		    	
		    	if(cur=="" || new1=="" || new2==""){
		    		alert("全部入力してください");
		    		return false;
		    	}
		    	if(new1!=new2){
		    		alert("変更後のパスワードが一致しません");
		    		return false;
		    	}
		    	
		    	if (new1.length < 8 || new1.length > 50 ) {
    		        output.textContent = "パスワードは8文字以上50文字以内にしてください";
    		        output.style.color = "red"; 
    		        return false;
		    	}		    		      
		    	 	
		    	 


		    }
		    
		    </script>
		    
		    
	
</body>
</html>