<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>





<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>エンプロ良イ👍｜カレンダー</title>
  <link rel="stylesheet" href="css/tencho_calendar.css">
  <link rel="stylesheet" href="css/header_footer.css">
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
"<fmt:formatDate value='${s.shiftData}' pattern='yyyy-MM-dd' />": ${s.count}

  <header>
    <h1 id="logo">
      <a href="tencho_calendar.jsp"><img src="img/logo.png" width="300" height="auto" alt="エンプロ良イ👍"></a>
    </h1>
    <ul id="nav">
      <li><a href="tencho_calendar.jsp">カレンダー</a></li>
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
    <div class="button-area">
      <a href="#shift"><button type="button" id="shiftBtn">シフト</button></a>
      <a href="#event"><button type="button" id="eventBtn">イベント</button></a>
    </div>
  </div>

  <div id="calendar"></div>

  <div id="shift">シフト</div><br>
  <div id="event">イベント</div>

  <footer>
    <p class="gotop">
      <a href="#top">
        <img src="img/gotop.png" alt="ページトップへ戻る" width="70" height="auto">
      </a>
    </p>
  </footer>

  <script src="https://cdn.jsdelivr.net/npm/fullcalendar@5.10.1/main.min.js"></script>

<script>
  // JSTLでJavaのList<DTO>から "yyyy-MM-dd": 件数 の形式で出力
 const shiftData = {
    <c:forEach var="s" items="${calShift}" varStatus="loop">
      "<fmt:formatDate value='${s.shiftData}' pattern='yyyy-MM-dd' />": <c:out value="${s.count}" /><c:if test="${!loop.last}">,</c:if>
    </c:forEach>
  };

  const eventData = {
    <c:forEach var="e" items="${calEvent}" varStatus="loop">
      "<fmt:formatDate value='${e.eventData}' pattern='yyyy-MM-dd' />": <c:out value="${e.count}" /><c:if test="${!loop.last}">,</c:if>
    </c:forEach>
  };
  console.log("shiftData = ", shiftData);
  console.log("eventData keys:", Object.keys(eventData));
  console.log("eventData['2025-06-10'] =", eventData["2025-06-10"]);
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

    // 📌 上部表示で6/10の件数確認
    const s0610 = getCumulativeCount(shiftData, "2025-06-10");
    const e0610 = getCumulativeCount(eventData, "2025-06-10");

    const debugBanner = document.createElement('div');
    debugBanner.textContent = `🧪 [6/10 累計] S: ${s0610}, E: ${e0610}`;
    debugBanner.style.background = '#fdf6b2';
    debugBanner.style.padding = '10px';
    debugBanner.style.margin = '8px 0';
    debugBanner.style.border = '1px solid #facc15';
    debugBanner.style.fontWeight = 'bold';
    calendarEl.parentNode.insertBefore(debugBanner, calendarEl);

    const calendar = new FullCalendar.Calendar(calendarEl, {
      initialView: 'dayGridMonth',
      dayCellDidMount: function(info) {
        const dateStr = info.date.getFullYear() + '-' +
          String(info.date.getMonth() + 1).padStart(2, '0') + '-' +
          String(info.date.getDate()).padStart(2, '0');

        const shiftToday = shiftData[dateStr] ?? 0;
        const eventToday = eventData[dateStr] ?? 0;
        const shiftTotal = getCumulativeCount(shiftData, dateStr);
        const eventTotal = getCumulativeCount(eventData, dateStr);

        console.log(`📅 ${dateStr} → shiftTotal: ${shiftTotal}, eventTotal: ${eventTotal}`);

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
        shiftEl.textContent = shiftToday > 0 ? `S: ${shiftTotal}` : `${shiftTotal}`;
        shiftEl.className = 'shift-count';
        container.appendChild(shiftEl);

        const eventEl = document.createElement('div');
        eventEl.textContent = eventToday > 0 ? `E: ${eventTotal}` : `${eventTotal}`;
        eventEl.className = 'event-count';
        container.appendChild(eventEl);

        const frame = info.el.querySelector('.fc-daygrid-day-frame');
        if (frame) {
          frame.style.position = 'relative';
          frame.appendChild(container);
        }
      }
    });

    calendar.render();
  });
</script>







</body>
</html>
