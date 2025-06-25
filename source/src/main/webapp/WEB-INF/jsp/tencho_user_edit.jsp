<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>エンプロ良イ👍｜ユーザー管理画面</title>
<link rel="stylesheet" href="<c:url value='/css/tencho_user_edit.css'/>">
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
    <li><a href="<c:url value='/CalenderServlet'/>">カレンダー</a></li>
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
 
 		
      

       <P class="note">＊店長は赤文字です</P>

        
    
       
            <!-- 表 -->
        <div class="table_up_del">
			
     <c:if test="${not empty message}">
  		<div class="up_del_mes" style="color:red">${message}</div>
	</c:if>
			
               <table border="1">
			      <thead>
			        <tr>
			          <th>ID</th>
			          <th>ユーザーネーム</th>
			          <th>パスワード</th>
			          <th></th>  
			        </tr>
			      </thead>
		      
		<tbody>
    		<c:forEach var="user" items="${userList}">
    		 <form id="user_edit_form" method="POST" action="<c:url value='/UserManageServlet' />">
    	<tr>
          <td>
            ${user.id}
            <input type="hidden" name="id" value="${user.id}" />
          </td>
          
          <td>
            <input type="text" name="name" value="${user.name}"
    		class="username-input"
    		style="<c:if test='${user.flag == 1}'>color:red; border-color:red;</c:if>" />
          </td>
          
          <td>
		      <!-- パスワードを伏せ字表示 -->
		       <input type="text" value="<c:forEach begin='1' end='${fn:length(user.pw)}'>＊</c:forEach>" 
         		disabled style="width:200px;" />
		      <input type="hidden" name="flag" value="${user.flag}" />
    	  </td>
          
          <td>
            <input type="submit" name="action" value="更新" class="update_button" 
            onclick="return confirm('更新してもよろしいですか？');" />
            <input type="submit" name="action" value="削除" class="delete_button"
              onclick="return confirm('本当に削除しますか？');" />
          </td>
      </tr>
      </form>
    </c:forEach>
  </tbody>
</table>
</div>


       					


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
			
			

</body>
</html>