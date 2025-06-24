<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="<c:url value='/css/style.css' />">
<link rel="stylesheet" href="<c:url value='/css/baito_manual.css'/>">
<title>エンプロ良イ&#128077 ｜マニュアル閲覧</title>

</head>
<body>
	<header>
			  <h1 id="logo">
			    <a href="baito_calendar.jsp">
			      <img src="img/logo.png" width="300" height="auto" alt="エンプロ良イ&#128077">
			    </a>
			  </h1>
			  <ul id="nav">
			    <li><a href="<c:url value='/CalenderServlet'/>">カレンダー</a></li>
			    <li><a href="<c:url value='/ShiftServlet'/>">シフト</a></li>
			    <li><a href="<c:url value='/ChangePWServlet'/>">パスワード</a></li>
			    <li><a href="<c:url value='/ManualServlett'/>">マニュアル</a></li>
			    <li><a href="<c:url value='/LogoutServlet'/>">ログアウト</a></li>
			  </ul>
	</header>

<main>
    <h3>登録済みマニュアル一覧</h3>
        <table border="1">
        <tr>
            <th>ID</th>
            <th>ファイル名</th>
            <th>重要度</th>
            <th>更新日</th>
        </tr>
        <c:forEach var="manual" items="${manualList}">
            <tr>
                <td>${manual.id}</td>
                <td>${manual.manual_file}</td>
                <td>${manual.importance}</td>
                <td>${manual.date_up}</td>
            </tr>
        </c:forEach>
    </table>
</main>

<footer>
	<div class="gotop">
			 <a href="#top"><img src="img/gotop.png" alt="ページトップへ戻る" width=70px height=auto></a>
	</div>
		
	<br>
    <p>&copy; エンプロ良イ&#128077</p></footer>
</body>
</html>