<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>エンプロ良イ👍｜ログイン</title>
</head>
<body>
	<header>
	</header>
	<main>
		<form method="POST" action="/A5/LoginServlet">
			<table>
				<tr>
					<td>
						<label>
							<input type="text" name="user" placeholder="ユーザー名">
						</label>
					</td>
				</tr>
				<tr>
					<td>
						<label>
							<input type="password" name="pw" placeholder="パスワード">
						</label>
					</td>
				</tr>
				<tr>
					<td>
						<input type="submit" name="login" value="ログイン">
					</td>
				</tr>
			</table>
		</form>
	</main>
	<footer>
	</footer>
</body>
</html>