<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>エンプロ良イ👍｜カレンダー</title>
  <link rel="stylesheet" href="css/tencho_calendar.css">
  <link rel="stylesheet" href="css/header_footer.css">
  <link rel="stylesheet" href="<c:url value='/css/tencho_shift.css'/>">
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
      <a href="tencho_calendar.jsp"><img src="img/logo.png" width="300" height="auto" alt="エンプロ良イ👍"></a>
    </h1>
    <c:if test="${sessionScope.flag == 1}">
    <ul id="tnav">
      <li><a href="tencho_calendar.jsp">カレンダー</a></li>
      <li><a href="tencho_shift.jsp">シフト</a></li>
      <li><a href="tencho_event.jsp">イベント</a></li>
      <li><a href="<c:url value='/ManualServlet' />">マニュアル</a></li>
      <details>
        <summary class="details-summary">その他</summary>
        <li><a href="tencho_user_edit.jsp">ユーザー管理</a></li>
        <li><a href="login.jsp">ログアウト</a></li>
      </details>
    </ul>
    </c:if>
    
    <c:if test="${sessionScope.flag == 0}">
    <ul id="bnav">
      <li><a href="baito_calendar.jsp">カレンダー</a></li>
      <li><a href="baito_shift.jsp">シフト</a></li>
      <li><a href="baito_event.jsp">パスワード</a></li>
      <li><a href="<c:url value='/ManualServlet' />">マニュアル</a></li>
      <li><a href="login.jsp">ログアウト</a></li>
    </ul>
    </c:if>
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
</script>

</body>
</html>
