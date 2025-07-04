<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>エンプロ良イ👍｜カレンダー</title>
  <link rel="stylesheet" href="<c:url value='/css/tencho_shift.css'/>">
  <link rel="stylesheet" href="<c:url value='/css/style.css'/>">
  <link rel="stylesheet" href="<c:url value='/css/tencho_calendar.css'/>">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/fullcalendar@5.10.1/main.min.css">
  <style>
    .shift-count, .event-count {
      font-size: 13px;
      text-align: left;
      line-height: 1.2;
      padding-left: 2px;
      z-index: 1000;
    }
    .shift-count {
      color: red;
      background-color: rgba(255, 0, 0, 0.08);
    }
    .event-count {
      color: blue;
      background-color: rgba(0, 0, 255, 0.08);
    }
    .fc-daygrid-day-frame {
      position: relative;
    }
  </style>
</head>

<body id="top">

  <header>
  
    <h1 id="logo">
      <a href="<c:url value='/CalendarServlet'/>"><img src="img/logo.png" width="300" height="auto" alt="エンプロ良イ👍"></a>
    </h1>

<!-- 店長フラグ1の場合店長用のメニューを表示 -->    
    <c:if test="${sessionScope.flag == 1}">
    <ul id="tnav">
     <li><a href="<c:url value='/CalendarServlet'/>">カレンダー</a></li>
     <li><a href="<c:url value='/ShiftServlet'/>">シフト</a></li>
	 <li><a href="<c:url value='/EventServlet'/>">イベント</a></li>
      <li><a href="<c:url value='/ManualServlet' />">マニュアル</a></li>
      <!-- アコーディオンメニュー -->
      <li>
      <details>
        <summary class="details-summary">その他</summary>
              <ul>
        <li><a href="<c:url value='/UserRegistServlet' />">ユーザー管理</a></li>
        <li><a href="<c:url value='/LogoutServlet' />">ログアウト</a></li>
        </ul>
      </details>
      </li>
    </ul>
    </c:if>

<!-- 店長フラグ0の場合店員用のメニューとハンバーガーメニューを表示 -->    
    <c:if test="${sessionScope.flag == 0}">
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
    <ul id="bnav">
      <li><a href="<c:url value='/CalenderServlet'/>">カレンダー</a></li>
     <li><a href="<c:url value='/ShiftServlet'/>">シフト</a></li>
     <li><a href="<c:url value='/ChangePWServlet'/>">パスワード</a></li>
      <li><a href="<c:url value='/ManualServlet' />">マニュアル</a></li>
      <li><a href="<c:url value='/LogoutServlet' />">ログアウト</a></li>

    </ul>
    </c:if>
  </header>
  
<!-- シフト一覧、イベント一覧に飛ぶボタン -->
  <div class="wrapper">
    <div class="button-area">
      <a href="#shift"><button type="button" id="shiftBtn">今週のシフト</button></a>
      <a href="#event"><button type="button" id="eventBtn">直近のイベント</button></a>
    </div>
  </div>

  <div id="calendar"></div>
<br>
<br>
<br>

<!-- シフト表示 -->
  <div id="shift">
      <p id=sTitle>今週のシフト</p>
  <!-- カレンダー表示切替ナビ -->
 	<div class="calendar-nav">
		<button id="prevWeek" class="arrow left">◁</button>
		<!--
		<div class="mode-buttons">
			<button type="button" id="bW" onclick="text2Hidden()">週 表示</button>
			<button type="button" id="bM" onclick="text1Hidden()">月 表示</button>
		</div>-->
		<button id="nextWeek" class="arrow right">▷</button>
	</div>
	
	
	<div id="printArea">
		<div id="text1">
		<div class="time-axis" id="timeAxis"></div>
		<div class="timeline-grid" id="timelineGrid"></div>
	</div>
	
	<!-- 
	<select id="weekSelector">
	<option value="0">月 第1週</option>
	<option value="1">第2週</option>
	<option value="2">第3週</option>
	<option value="3">第4週</option>
	<option value="4">第5週</option>
	</select>  -->
	
	
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
  </div>
  <br>
  <br>
  <br>
  
<!-- イベント表示 -->
  <div id="event">
      <p id=eTitle>直近のイベント</p>
  <!-- 前月次月切り替えボタン -->
<c:set var="prevWeek" value="${weekOffset - 1}" />
<c:set var="nextWeek" value="${weekOffset + 1}" />

<div id=weeklybutton>
<form action="CalendarServlet#event" method="GET">
	<input type="hidden" name="weekOffset" value="${weekOffset - 1}">
	<input type="submit" value="前の7件"<c:if test="${weekOffset <= 0}">disabled</c:if>>
</form>

<form action="CalendarServlet#event" method="GET">
	<input type="hidden" name="weekOffset" value="${weekOffset + 1}">
	<input type="submit" value="次の7件">
</form>
</div>

	<!-- 今日から1週間分のイベントを表示 -->
    <table border="1">
    <thead>
		<tr>
			<th>日付</th>
			<th>イベント種別</th>
			<th>開始時刻</th>
			<th>終了時刻</th>
		</tr>
		</thead>
		<c:forEach var="event" items="${weeklyEvents}">
		<tbody>
			<tr>
				<td>${event.eventDate}</td>
				<td>${event.eventType}</td>
				<td>${event.eventStart}</td>
				<td>${event.eventEnd}</td>
				
			</tr>
			</tbody>
		</c:forEach>
	</table>
  </div>
<br>
<br>
<br>
<br>

<footer>
    <p class="gotop">
      <a href="#top">
        <img src="<c:url value='/img/gotop.png' />" alt="ページトップへ戻る" width="70" height="auto">
      </a>
    </p>
    <p>&copy; エンプロ良イ&#128077</p>
  </footer>

  <script src="https://cdn.jsdelivr.net/npm/fullcalendar@5.10.1/main.min.js"></script>
  

<script>
  // サーバー側で用意された Map<String,Integer> をJSに埋め込む
  const shiftData = {
    <c:forEach var="entry" items="${shiftMap}" varStatus="loop">
      "${entry.key}": ${entry.value}<c:if test="${!loop.last}">,</c:if>
    </c:forEach>
  };

  const eventData = {
    <c:forEach var="entry" items="${eventMap}" varStatus="loop">
      "${entry.key}": ${entry.value}<c:if test="${!loop.last}">,</c:if>
    </c:forEach>
  };

  function getCumulativeCount(dataMap, targetDateStr) {
    let total = 0;
    const sortedDates = Object.keys(dataMap).sort();
    for (const date of sortedDates) {
      if (date <= targetDateStr) {
        const count = Number(dataMap[date]);
        total += isNaN(count) ? 0 : count;
      } else {
        break;
      }
    }
    return total;
  }

  document.addEventListener('DOMContentLoaded', () => {
    const calendarEl = document.getElementById('calendar');

    const calendar = new FullCalendar.Calendar(calendarEl, {
      initialView: 'dayGridMonth',
      dayCellDidMount: function(info) {
        const dateStr = info.date.getFullYear() + '-' +
          String(info.date.getMonth() + 1).padStart(2, '0') + '-' +
          String(info.date.getDate()).padStart(2, '0');

        const shiftTotal = getCumulativeCount(shiftData, dateStr);
        const eventTotal = getCumulativeCount(eventData, dateStr);

        const container = document.createElement('div');
        container.style.position = 'absolute';
        container.style.bottom = '2px';
        container.style.left = '2px';
        container.style.fontSize = '13px';
        container.style.background = 'rgba(255, 255, 255, 0.85)';
        container.style.padding = '2px 4px';
        container.style.borderRadius = '4px';
        container.style.zIndex = '10';

        const shiftEl = document.createElement('div');
        shiftEl.textContent = `S: ${shiftTotal}`;
        shiftEl.className = 'shift-count';
        container.appendChild(shiftEl);

        const eventEl = document.createElement('div');
        eventEl.textContent = `E: ${eventTotal}`;
        eventEl.className = 'event-count';
        container.appendChild(eventEl);

        const frame = info.el.querySelector('.fc-daygrid-day-frame');
        if (frame) {
          frame.appendChild(container);
        }
      }
    });

    calendar.render();
  });
  const shiftListData = ${shiftList };
</script>

<script src="<c:url value='/js/tencho_shift.js' />"></script>
</body>
</html>
