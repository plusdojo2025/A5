<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>エンプロ良イ👍｜カレンダー</title>
  <link rel="stylesheet" href="calendar.css">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/fullcalendar@5.10.1/main.min.css">
</head>
<body>
  <header>
	<h1 id="logo">
      <a href="tencho_calendar.jsp"><img src="images/logo.png" width=300 height=auto alt="エンプロ良イ👍"></a>
    </h1>
    <ul id="nav">
      <li><a href="tencho_calelndar.jsp">カレンダー</a></li>
      <li><a href="tencho_shift.jsp">シフト</a></li>
      <li><a href="tencho_event.jsp">イベント</a></li>
      <li><a href="tencho_manual.jsp">マニュアル</a></li>
	  <details>
		<summary class="details-summary">その他</summary>
		      <li><a href="tencho_user_edit.jsp">ユーザー管理</a></li>
			  <li><a href="tencho_login.jsp">ログアウト</a></li>
		</details>
    </ul>
  </header> 
  <!--ボタン-->
  <div style="text-align: center; margin-top: 20px;">
  <button type="button" id="shiftBtn">シフト</button>
  <button type="button" id="eventBtn">イベント</button>
  </div>

  <!--カレンダー-->
  <div id="calendar"></div>
  
  <!--シフト表-->
  <div id="shift">シフト</div><br>

  <!--イベント-->
  <div id="event">イベント</div>

  <footer></footer>

  <!-- Script -->
  <script src="https://unpkg.com/@popperjs/core@2" defer></script>
  <script src="https://unpkg.com/tippy.js@6" defer></script>
  <script src="https://cdn.jsdelivr.net/npm/fullcalendar@5.10.1/main.min.js" defer></script>

  <script>
    document.addEventListener('DOMContentLoaded', function () {
      const events = [
        {
          id: "",
          start: "",
          title: "",
          description: "",
          backgroundColor: "red",
          borderColor: "red",
          editable: true
        }
      ];

      const elem = document.getElementById("calendar");

      const calendar = new FullCalendar.Calendar(elem, {
        initialView: "dayGridMonth",
        initialDate: new Date(),
        events: events,
      });

      calendar.render();
	   // ボタンがクリックされたらページ内の対応するIDにスクロールする
      document.getElementById("shiftBtn").addEventListener("click", function () {
        document.getElementById("shift").scrollIntoView({ behavior: "smooth" });
      });

      document.getElementById("eventBtn").addEventListener("click", function () {
        document.getElementById("event").scrollIntoView({ behavior: "smooth" });
      });
    });
  </script>
</body>
</html>