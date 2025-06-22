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
    <li><a href="/A5/CalenderServlet">カレンダー</a></li>
    <li><a href="/A5/ShiftServlet">シフト</a></li>
    <li><a href="/A5/EventServlet">イベント</a></li>
    <li><a href="/A5/ManualServlet">マニュアル</a></li>
    <li>
      <details>
        <summary class="details-summary">その他</summary>
        <ul>
          <li><a href="/A5/UserResistServlet">ユーザー管理</a></li>
          <li><a href="/A5/LogoutServlet">ログアウト</a></li>
        </ul>
      </details>
    </li>
  </ul>
</header>
 
 	<br><br><br><br><br>
  


       

        <br><br>
      

       <P class="note">＊店長は赤文字です</P>

        <br>
    
        <form id="user_edit_form" method="POST" action="<c:url value='/UserManageServlet' />">
            <!-- 表 -->
        <div class="table_up_del">

               <table border="1">
			      <thead>
			        <tr>
			          <th>ID</th>
			          <th>ユーザーネーム</th>
			          <th>パスワード</th>
			        </tr>
			      </thead>
		      
		<tbody>
    		<c:forEach var="user" items="${userList}">
    	<tr>
          <td>
            ${user.id}
            <input type="hidden" name="id" value="${user.id}" />
          </td>
          
          <td>
            <input type="text" name="name" value="${user.name}"
              style="width:150px;<c:if test='${user.flag == 1}'>color:red;</c:if>" />
          </td>
          
          <td>
		      <!-- パスワードを伏せ字表示 -->
		      <c:forEach begin="1" end="${fn:length(user.pw)}">＊</c:forEach>
		      <input type="hidden" name="pw" value="${user.pw}" />
    	  </td>
          
          <td>
            <input type="password" name="pw" value="${user.pw}" style="width:150px;" />
            <input type="hidden" name="flag" value="${user.flag}" />
          </td>
          
          <td>
            <input type="submit" name="action" value="更新" class="update_button" />
            <input type="submit" name="action" value="削除" class="delete_button"
              onclick="return confirm('本当に削除しますか？');" />
          </td>
      </tr>
    </c:forEach>
  </tbody>
</table>
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
        <p>&copy; エンプロ良イ👍</p>
        

    </footer>
			
			

</body>
</html>