<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>エンプロ良イ👍｜カレンダー</title>
  <link rel="stylesheet" href="calendar.css">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/fullcalendar@5.10.1/main.min.css">
 <style>
    /* シフトやイベントの件数を表示するためのスタイル */
    .shift-count, .event-count {
      position: absolute;
      top: 5px;
      right: 5px;
      font-size: 12px;
      background-color: rgba(255, 255, 255, 0.7);
      border-radius: 50%;
      padding: 2px 5px;
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
   <!-- シフトとイベントの件数表示 -->
  <div style="text-align: center; margin-top: 20px;">
    <h3>シフトの件数: <%= request.getAttribute("shiftCount") %>件</h3>
    <h3>イベントの件数: <%= request.getAttribute("eventCount") %>件</h3>
  </div>
  
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
    
    const shiftData = <%= request.getAttribute("shiftData") %>; // 日付別シフト件数
    const eventData = <%= request.getAttribute("eventData") %>; // 日付別イベント件数

    /*
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
      ]; */

      const elem = document.getElementById("calendar");

      const calendar = new FullCalendar.Calendar(elem, {
        initialView: "dayGridMonth",
        initialDate: new Date(),
        events: [],
        dayCellContent: function(info) {
            const date = info.dateStr;
        
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