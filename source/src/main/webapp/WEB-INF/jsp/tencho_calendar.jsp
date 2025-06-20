<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Map" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>エンプロ良イ👍｜カレンダー</title>
  <link rel="stylesheet" href="calendar.css">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/fullcalendar@5.10.1/main.min.css">
  <style>
    .shift-count, .event-count {
      font-size: 10px;
      background: rgba(255, 255, 255, 0.8);
      margin-top: 2px;
      padding: 1px 3px;
      border-radius: 3px;
    }
    .shift-count { color: red; }
    .event-count { color: blue; }
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

  <div id="calendar"></div>
  
  <!--シフト表-->
  <div id="shift">シフト</div><br>

  <!--イベント-->
  <div id="event">イベント</div>

  

  <!-- JavaScript へデータを埋め込む -->
  <script src="https://cdn.jsdelivr.net/npm/fullcalendar@5.10.1/main.min.js"></script>
  <script>
    const shiftData = {
    		<%
    		Map<String, Integer> m = (Map<String, Integer>) request.getAttribute("shiftData");
    		if (m != null) {
    		    int count = 0;
    		    for (Map.Entry<String, Integer> e : m.entrySet()) {
    		        out.print("\"" + e.getKey() + "\": " + e.getValue());
    		        count++;
    		        if (count < m.size()) out.print(",");
    		    }
    		}
    		%>
    };

    const eventData = {
      <% Map<String,Integer> m2 = (Map<String,Integer>)request.getAttribute("eventData");
         if (m2 != null) {
        	int count = 0;
           for (Map.Entry<String,Integer> e : m2.entrySet()) {
             out.print("\"" + e.getKey() + "\": " + e.getValue());
             count++;
             if(count < m2.size())out.print(",");
           }
         }
      %>
    };
 
    document.addEventListener('DOMContentLoaded', () => {
      const calendarEl = document.getElementById('calendar');
      const calendar = new FullCalendar.Calendar(calendarEl, {
        initialView: 'dayGridMonth',
        dayCellContent: function(info) {
          const dateStr = info.date.toISOString().slice(0,10);
          const sc = shiftData[dateStr] || 0;
          const ec = eventData[dateStr] || 0;

          const el = document.createElement('div');
          el.innerHTML = info.dayNumberText;

          const scEl = document.createElement('div');
          scEl.className = 'shift-count';
          scEl.innerText = `S:${sc}`;

          const ecEl = document.createElement('div');
          ecEl.className = 'event-count';
          ecEl.innerText = `E:${ec}`;

          el.appendChild(scEl);
          el.appendChild(ecEl);

          return { domNodes: [el] };
        }
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
