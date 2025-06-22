<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Map" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>エンプロ良イ👍｜カレンダー</title>
  <link rel="stylesheet" href="css/tencho_calendar.css">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/fullcalendar@5.10.1/main.min.css">

</head>
<body id="top">

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
  
  <div class="wrapper">
  <!--ボタン-->
  <div class="button-area">
  <a href="#shift">
    <button type="button" id="shiftBtn">シフト</button>
  </a>
  <a href="#event">
    <button type="button" id="eventBtn">イベント</button>
  </a>
</div>
  </div>

  <div id="calendar"></div>
  
 <!--  <style>
      .shift-count, .event-count {
      font-size: 30px;
    }
    .shift-count { color: red; }
    .event-count { color: blue; }
  </style> -->
  
  <!--シフト表-->
  <div id="shift">シフト</div><br>

  <!--イベント-->
  <div id="event">イベント</div>

  <!-- フッター -->
<footer>
        <p class="gotop">
            <a href="#top">
            <img src="img/gotop.png" alt="ページトップへ戻る" width="70" height="auto">
            </a>
        </p>
    </footer>
</div>

  <!-- JavaScript へデータを埋め込む -->
  <script src="https://cdn.jsdelivr.net/npm/fullcalendar@5.10.1/main.min.js"></script>
  <script>
	//変数shiftDateに日付と人数の情報をオブジェクト形式で入れる
    const shiftData = {
			//CalendarServletから渡されたデータ(Map形式)を受け取って変数mに入れる
    <% Map<String, Integer> m = (Map<String,Integer>)request.getAttribute("shiftData");
			//Mapの中身を1件ずつ取り出してJavaScriptの形式に出力している部分
    		if (m != null) {
    		    int count = 0;
    		    for (Map.Entry<String, Integer> e : m.entrySet()) {
    		    	//e.getKeyは日付、e.getValueはその日の人数、\" はダブルクォーテーションを文字として表示するためのエスケープ
    		        out.print("\"" + e.getKey() + "\": " + e.getValue());
    		        count++;
    		        //JavaScriptの形式が崩れないように、最後の項目以外ならカンマを出力
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
/*   const shiftData = {
    "2025-06-19": 3,
    "2025-06-20": 2
  };

  const eventData = {
    "2025-06-19": 1,
    "2025-06-20": 0
  };
 */  
    document.addEventListener('DOMContentLoaded', () => {
      const calendarEl = document.getElementById('calendar');
      const calendar = new FullCalendar.Calendar(calendarEl, {
    	  //月表示にする設定
        initialView: 'dayGridMonth',
        //セルのカスタマイズ
        dayCellContent: function(info) {
        	//セルの日付をyyyy-mm-ddの形にする
          const dateStr = info.date.toISOString().slice(0,10);
        	//その日付のシフト数とイベント数を取得、データがなければ0
          const sc = shiftData[dateStr] || 0;
          const ec = eventData[dateStr] || 0;

          //日付の数字を表示する<div>を作る
          const el = document.createElement('div');
          el.innerHTML = info.dayNumberText;

        //シフト数を表示する<div>を作り、「S:〇」と表示
        //<div class="shift-count"></div> と同義
          const scEl = document.createElement('div');
        	//shift-countというcssクラス名をつけて、あとでcssで指定できるようにする
          scEl.className = 'shift-count';
          scEl.innerText = `S:${sc}`;

        //イベント数を表示する<div>を作り、「E:〇」と表示
          const ecEl = document.createElement('div');
          ecEl.className = 'event-count';
          ecEl.innerText = `E:${ec}`;

          //日付のセルにシフト数とイベント数の情報を追加
          el.appendChild(scEl);
          el.appendChild(ecEl);

          //FullCalendarにカスタマイズを伝える
          return { domNodes: [el] };
        }
      });      
      //カレンダーを表示
      calendar.render();
      
    });
  </script>
</body>
</html>
