<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>エンプロ良イ👍｜シフト申請</title>

<style>
head,main {
	margin: 0 auto 0 auto;
}
button {
	font-size: 16px;
	padding: 5px 20px;
	width: 120px;
	height: 120px;
}

.momo {
	font-size: 16px;
	padding: 5px 20px;
	width: 120px;
	height: 44px;
}
</style>
</head>
<body>
	<header>
	<h1 id="logo">
	      <a href="tencho_calendar.jsp"><img src="img/logo.png" width=300 height=auto alt="エンプロ良イ👍"></a>
	    </h1>
	    <ul id="nav">
	      <li><a href="tencho_calelndar.jsp">カレンダー</a></li>
	      <li><a href="tencho_shift.jsp">シフト</a></li>
	      <li><a href="tencho_event.jsp">イベント</a></li>
	      <li><a href="tencho_manual.jsp">マニュアル</a></li>
	      <li>
		  <details>
		  	<summary class="details-summary">その他</summary>
		  	<ul>
		  	<li><a href="tencho_user_edit.jsp">ユーザー管理</a></li>
		  	<li><a href="tencho_login.jsp">ログアウト</a></li>
		  	</ul>
		  </details>
		  </li>
	    </ul>
	</header>
	<main>
	<ul>
	<li><button type="button">追加</button></li>
	<li><button type="button">追加</button></li>
	<li><button type="button">追加</button></li>
	<li><button type="button">追加</button></li>
	<li><button type="button">追加</button></li>
	<li><button type="button">追加</button></li>
	<li><button type="button">追加</button></li>
	<li><button type="button">追加</button></li>
	<li><button type="button">追加</button></li>
	</ul>
	<button type="button" class="momo">保存</button>
	</main>
	<footer>
	<a href="#top"><button type="button" class="momo">上に戻る</button></a>
	</footer>
</body>
</html>