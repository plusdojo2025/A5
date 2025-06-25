<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>エンプロ良イ&#128077｜ログイン</title>
	<link rel="stylesheet" href="<c:url value='/css/login.css'/>">
</head>
<body>
	<br><br><br>
	<!-- タイトル -->
	<header>
		<h1 id="logo">
			<a href="tencho_calendar.jsp">
				<img src="img/logo.png" width="300" height="auto" alt="エンプロ良イ&#128077">
			</a>
		</h1>
	</header>
	<main>
		<form method="POST" action="<c:url value='/LoginServlet' />">
			<div class="login_form">
				<table>
					<tr>
						<td>
							<br><br>
							<label>
								<input id="user_name" type="text" name="user" placeholder="ユーザー名" required>
							</label>
							<br><br>
						</td>
					</tr>
					<tr>
						<td>
							<label>
								<input id="password" type="password" name="pw" placeholder="パスワード" required>
							</label>
							<br><br>
						</td>
					</tr>
					<tr>
						<td>
							<input id="login_button" type="submit" name="login" value="ログイン">
							<br><br>
						</td>
					</tr>
				</table>
			</div>
		</form>
	</main>
	<!-- フッター -->
	<footer>
		<p>&copy; エンプロ良イ&#128077</p>
	</footer>
</body>
</html>