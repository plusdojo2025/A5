<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>エンプロ良イ&#128077｜シフト確認</title>
<link rel="stylesheet" href="<c:url value='/css/tencho_shift.css'/>" >
<link rel="stylesheet" href="<c:url value='/css/style.css'/>">
</head>
<body>
	<div class="container">
	
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
	
	<main>
	<!-- カレンダー表示切替ナビ -->
	<div class="calendar-nav">
		<button id="prevWeek" class="arrow left">◁</button>
		<div class="mode-buttons">
			<button type="button" id="bW" onclick="text2Hidden()">週 表示</button>
			<button type="button" id="bM" onclick="text1Hidden()">月 表示</button>
		</div>
		<button id="nextWeek" class="arrow right">▷</button>
	</div>
	
	
	<div id="printArea">
		<div id="text1">
		<div class="time-axis" id="timeAxis"></div>
		<div class="timeline-grid" id="timelineGrid"></div>
	</div>

	<!-- 表（月表示） -->
	<div id="text2" hidden="">
	<table id="shiftTable">
    <thead>
      <tr>
        <th>日付</th>
        <th>名前</th>
        <th>開始時間</th>
        <th>終了時間</th>
      </tr>
    </thead>
    <tbody>
      <!-- JavaScriptでここに行を追加します -->
    </tbody>
  </table>
  </div>
  </div>
	
	
	<div class="print_center">
		<button type="button" class="print" onclick="printSection('printArea')">印刷</button>
	</div>
	<p style="font-size: 0.9em; color: gray;">
	  	※ 印刷時に背景が表示されない場合は、ブラウザの「印刷設定」で「背景のグラフィックを印刷」にチェックを入れてください。
	</p>
	

	</main>
	<footer>
	<div class="gotop">
		 <a href="#top"><img src="<c:url value='/img/gotop.png'/>" alt="ページトップへ戻る" width=70px height=auto></a>
	</div>
	<br>
	<p>&copy; エンプロ良イ&#128077</p>
	</footer>
	</div>
<script>
const cssUrl = "<c:url value='/css/tencho_shift.css'/>";
</script>
<script src="<c:url value='/js/tencho_shift.js' />"></script>
</body>
</html>