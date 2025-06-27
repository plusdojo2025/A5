<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="<c:url value='/css/style.css' />">
	<link rel="stylesheet" href="<c:url value='/css/baito_manual.css'/>">
	<title>エンプロ良イ&#128077 ｜マニュアル閲覧</title>
</head>
<body>
	<header>
		<h1 id="logo">
			<a href="baito_calendar.jsp">
				<img src="img/logo.png" width="300" height="auto" alt="エンプロ良イ&#128077"></a>
		</h1>
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
		<!-- 店員用メニュー -->
		<ul id="nav">
			<li><a href="<c:url value='/CalendarServlet'/>">カレンダー</a></li>
			<li><a href="<c:url value='/ShiftServlet'/>">シフト</a></li>
			<li><a href="<c:url value='/ChangePWServlet'/>">パスワード</a></li>
			<li><a href="<c:url value='/ManualServlet' />">マニュアル</a></li>
			<li><a href="<c:url value='/LoginServlet' />">ログアウト</a></li>
		</ul>
	</header>
	<!-- ここから↓ -->
	<form method="get" action="${pageContext.request.contextPath}/manual-list">
	<label>並び替え対象:
		<select name="sortColumn">
			<option value="fileId">ID</option>
			<option value="manualFile">ファイル名</option>
			<option value="importance">重要度</option>
			<option value="date">更新日</option>
		</select>
	</label>
	<label>順序:
		<input type="radio" name="sortOrder" value="ASC" checked> 昇順
		<input type="radio" name="sortOrder" value="DESC"> 降順
	</label>
	<button type="submit">並び替え</button>
</form>
	<!-- ここまで↑ 重要度変更に関するやつ -->
	<main>
		<h3>登録済みマニュアル一覧</h3>
		<div id="trzmtbl">
		<table border="1">
			<tr>
				<th>ID</th>
				<th>ファイル名</th>
				<th>重要度</th>
				<th>更新日</th>
			</tr>
			<c:forEach var="manual" items="${manualList}">
				<tr>
					<td>${manual.fileId}</td>
					<td><a href="<c:url value='/mt/${manual.manualFile}'/>">${manual.manualFile}</a></td>
					<td>${manual.importance}</td>
					<td>${manual.date}</td>
				</tr>
			</c:forEach>
		</table>
		</div>
	</main>
	<footer>
		<div class="gotop">
			<a href="#top"><img src="img/gotop.png" alt="ページトップへ戻る" width=70px height=auto></a>
		</div>
		<br>
		<p>&copy; エンプロ良イ&#128077</p>
	</footer>
</body>
</html>