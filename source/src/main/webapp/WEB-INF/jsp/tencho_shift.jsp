<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>エンプロ良イ👍｜シフト確認</title>
<!-- <link rel="stylesheet" href="<c:url value='/css/tencho_shift.css'/>"> -->
<style>

head,main {
	margin: 0 auto 0 auto;
}

body {
font-family: sans-serif;
margin: 20px;
}

select {
margin-bottom: 10px;
}

.time-axis {
display: flex;
margin-left: 100px;
font-size: 12px;
}

.time-axis span {
width: 60px;
text-align: center;
border-left: 1px solid #ddd;
}

.timeline-grid {
display: flex;
flex-direction: column;
gap: 10px;
overflow-x: auto;
}

.day-row {
position: relative;
border-bottom: 1px solid #ccc;
height: auto;
min-height: 40px;
}

.date-label {
position: absolute;
left: 0;
top: 0;
width: 100px;
height: 100%;
background: #f0f0f0;
padding: 4px;
border-right: 1px solid #ccc;
box-sizing: border-box;
}

.time-line {
position: relative;
margin-left: 100px;
height: auto; /* 高さを自動に */
min-height: 40px;
background: #fafafa;
width: 600px;
}


.shift-bar {
position: absolute;
top: 5px;
height: 30px;
background: #e7191f;
color: white;
padding: 2px 5px;
font-size: 12px;
white-space: nowrap;
text-align: center;
}

#shiftForm {
margin: 20px 0;
padding: 10px;
background: #eef;
display: flex;
gap: 10px;
align-items: center;
}
#shiftForm input {
padding: 4px;
}


.shift-input-row {
display: flex;
align-items: center;
gap: 10px;
margin: 5px 0;
}
.input-date-label {
width: 100px;
font-weight: bold;
}

.shift-input-row {
	display: flex;
	gap: 10px;
	margin: 4px, 0;
	align-items: center;
}

.input-date-label {
	width: 100px;
	font-weight: bold;
}

/* 入力欄を縦に並べる */
.shift-input-fields {
	display: flex;
	flex-direction: column;
	gap: 5px;
}

</style>

</head>
<body>
<<<<<<< HEAD
	<br><br><br>
	<div class="container">
    	<br><br><br>
		<!-- タイトル -->
		<header>
		<h1 id="logo">
			<a href="tencho_calendar.jsp">
			<img src="img/logo.png" width="300" height="auto" alt="エンプロ良イ👍">
			</a>
		</h1>
		<ul id="nav">
			<li><a href="<c:url value='/CalenderServlet'/>">カレンダー</a></li>
		    <li><a href="<c:url value='/ShiftServlet'/>">シフト</a></li>
		    <li><a href="<c:url value='/EventServlet'/>">イベント</a></li>
		    <li><a href="<c:url value='/ManualServlett'/>">マニュアル</a></li>
		    <li>
		      <details>
		        <summary class="details-summary">その他</summary>
		        <ul>
		          <li><a href="<c:url value='/UserRegistServlet'/>">ユーザー管理</a></li>
		          <li><a href="<c:url value='/LogoutServlet'/>">ログアウト</a></li>
		        </ul>
		      </details>
=======
	<header>
	<h1 id="logo">
	      <a href="tencho_calendar.jsp"><img src="img/logo.png" width=300 height=auto alt="エンプロ良イ👍"></a>
	    </h1>
	    <ul id="nav">
			<li><a href="/A5/CalenderServlet">カレンダー</a></li>
			<li><a href="/A5/ShiftCheckServlet">シフト</a></li>
			<li><a href="/A5/EventServlet">イベント</a></li>
			<li><a href="/A5/ManualServlet">マニュアル</a></li>
			<li>
			<details>
				<summary class="details-summary">その他</summary>
				<ul>
				<li><a href="/A5/UserRegistServlet">ユーザー管理</a></li>
				<li><a href="/A5/LogoutServlet">ログアウト</a></li>
				</ul>
			</details>
>>>>>>> c6e61e4022acb9c692b7d08be71228cbd760ed5e
			</li>
		</ul>

	</header>
	
	<main>
	
	<!-- カレンダー表示切替ナビ -->
	<div class="calendar-nav">
		<button class="arrow left">◁</button>
		<div class="mode-buttons">
			<button>週 表示</button>
			<button>月 表示</button>
		</div>
		<button class="arrow right">▷</button>
	</div>
	
	
	<div id="printArea">
		<div class="time-axis" id="timeAxis"></div>
		<div class="timeline-grid" id="timelineGrid"></div>
	</div>
	
	<select id="weekSelector">
	<option value="0">月 第1週</option>
	<option value="1">第2週</option>
	<option value="2">第3週</option>
	<option value="3">第4週</option>
	<option value="4">第5週</option>
	</select>

	<div class="print_center">
		<button type="button" class="print" onclick="printSection('printArea')">印刷</button>
	</div>

	</main>
	<footer>
	<div class="gotop">
		 <a href="#top"><img src="img/gotop.png" alt="ページトップへ戻る" width=70px height=auto></a>
	</div>
	<br>
	<p>&copy; エンプロ良イ👍</p>
	</footer>

<script>
let d = new Date();
let nxtMth = d.getMonth() + 1;

const HOURS = Array.from({ length: 10 }, (_, i) => 9 + i); // 9:00〜18:00

const shifts = [
	{ date: '2025-06-02', name: '山田', startTime: '09:00', endTime: '17:00' },
	{ date: '2025-06-03', name: '田中', startTime: '13:00', endTime: '18:00' },
	{ date: '2025-06-03', name: '佐藤', startTime: '12:00', endTime: '15:00' }, // ★被り
	{ date: '2025-06-10', name: '佐藤', startTime: '10:00', endTime: '16:00' },
	{ date: '2025-06-17', name: '山田', startTime: '10:00', endTime: '18:00' },
	{ date: '2025-06-25', name: '田中', startTime: '08:30', endTime: '12:00' }
];


const WEEKDAYS = ['SUN', 'MON', 'TUE', 'WED', 'THU', 'FRI', 'SAT'];

const axis = document.getElementById("timeAxis");
const grid = document.getElementById("timelineGrid");

HOURS.forEach(h => {
	const span = document.createElement("span");
	if(h == '10' || h == '17'){
		span.textContent = '';
		axis.appendChild(span);
	} else {
		span.textContent = h;
		axis.appendChild(span);
	}
});

function renderTimeline(weekIndex) {
	grid.innerHTML = '';
	
	const weekStart = new Date(2025, 5, 1); // 2025-06-01
	weekStart.setDate(1 + weekIndex * 7);
	
	
	for (let i = 0; i < 7; i++) {
		const currentDate = new Date(weekStart);
		currentDate.setDate(weekStart.getDate() + i);
		const dateStr = formatDate(currentDate);
		const day = currentDate.getDate();
		const dayName = WEEKDAYS[currentDate.getDay()];
		const dailyShifts = shifts.filter(s => s.date === dateStr);
		
		const row = document.createElement("div");
		row.className = "day-row";
		
		const label = document.createElement("div");
		label.className = "date-label";
		label.textContent = day + "(" + dayName + ")";
		row.appendChild(label);
		
		const line = document.createElement("div");
		line.className = "time-line";
		
		dailyShifts.forEach((s, index) => {
			const [sh, sm] = s.startTime.split(":").map(Number);
			const [eh, em] = s.endTime.split(":").map(Number);
			const startMin = sh * 60 + sm;
			const endMin = eh * 60 + em;
			const left = startMin - 540; // 9:00 = 540分
			const width = endMin - startMin;
		
			const bar = document.createElement("div");
			bar.className = "shift-bar";
			bar.style.left = `${'$'}{left}px`;
			bar.style.width = `${'$'}{width}px`;
			bar.style.top = `${'$'}{5 + index * 35}px`; // ← シフトごとに縦にずらす
			bar.textContent = s.name;
		
			line.appendChild(bar);
		});
		
		//⭐ 高さを調整（これが今回の修正点）
		const rowHeight = Math.max(40, dailyShifts.length * 40);
		line.style.height = `${'$'}{rowHeight}px`;
		row.style.height = `${'$'}{rowHeight}px`;
		
		row.appendChild(line);
		grid.appendChild(row);
	}
}

document.getElementById("weekSelector").addEventListener("change", e => {
	renderTimeline(parseInt(e.target.value));
});

renderTimeline(0); // 初期表示：第1週

function formatDate(date) {
	return date.toLocaleDateString("sv-SE");
};

function printSection(sectionId) {
    const section = document.getElementById(sectionId);

    // 新しいウィンドウを開く
    const printWindow = window.open('', '', 'width=800,height=600');

    // HTML文書を作成
    printWindow.document.open();
    printWindow.document.write(`<html><head><title>印刷プレビュー</title><link rel="stylesheet" href="/A5/css/tencho_shift_print.css"/><script src="/A5/js/tencho_shift_print.js" defer></script></head><body>${section.outerHTML}</body></html>`);
    printWindow.document.close();

    printWindow.onload = function () {
        printWindow.focus();
        printWindow.print();
        printWindow.close();
    };
}



</script>

</body>
</html>