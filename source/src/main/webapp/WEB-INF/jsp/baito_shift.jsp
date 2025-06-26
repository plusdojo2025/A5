<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>エンプロ良イ&#128077｜シフト申請</title>
<link rel="stylesheet" href="<c:url value='/css/baito_shift.css'/>" >
<link rel="stylesheet" href="<c:url value='/css/style.css'/>">
</head>
<body>
	<div class="container">
	<!-- タイトル -->
	<header>
	    <h1 id="logo">
	    	<a href="tencho_calendar.jsp">
	    		<img src="img/logo.png" width=300 height=auto alt="エンプロ良イ👍">
	    	</a>
	    </h1>
	    <ul id="nav">
	    	<li><a href="<c:url value='/CalendarServlet'/>">カレンダー</a></li>
		    <li><a href="<c:url value='/ShiftServlet'/>">シフト</a></li>
		    <li><a href="<c:url value='/ChangePWServlet'/>">パスワード</a></li>
		    <li><a href="<c:url value='/ManualServlet'/>">マニュアル</a></li>
	    	<li><a href="<c:url value='/LogoutServlet'/>">ログアウト</a></li>
	    </ul>
	</header>
  
	<main>
		<div class="time-axis" id="timeAxis"></div>
		<div class="timeline-grid" id="timelineGrid"></div>
	
	
		<select id="weekSelector">
			<option value="0">月 第1週</option>
			<option value="1">第2週</option>
			<option value="2">第3週</option>
			<option value="3">第4週</option>
			<option value="4">第5週</option>
		</select>
	
		<div id="inputRowsContainer"></div>
		
		<!-- 一括追加ボタン -->
		<button id="bulkAddBtn">保存</button>
		
		
		<form>
		<select id="weSel">
		</select>
		
		<ul id="add_button">
		<!-- ボタン追加 -->
		
		</ul>
		
		<!-- 新たなボタン群 -->
		<div id="week1"></div>
		<div id="week2"></div>
		<div id="week3"></div>
		<div id="week4"></div>
		<div id="week5"></div>
		
		<!-- 一括追加ボタン -->
		<button id="hozon">保存</button>
		
		</form>
	</main>

<footer>
	<div class="gotop">
		 <a href="#top"><img src="<c:url value='/img/gotop.png'/>" alt="ページトップへ戻る" width=70px height=auto></a>
	</div>
</footer>
</div>

<script>
const HOURS = Array.from({ length: 10 }, (_, i) => 9 + i); // 9:00〜18:00

const shifts = [
	{ date: '2025-07-02', name: '山田', startTime: '09:00', endTime: '17:00' },
	{ date: '2025-07-03', name: '田中', startTime: '13:00', endTime: '18:00' },
	{ date: '2025-07-03', name: '佐藤', startTime: '12:00', endTime: '15:00' }, // ★被り
	{ date: '2025-07-10', name: '佐藤', startTime: '10:00', endTime: '16:00' },
	{ date: '2025-07-17', name: '山田', startTime: '10:00', endTime: '18:00' },
	{ date: '2025-07-25', name: '田中', startTime: '08:30', endTime: '12:00' }
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
	
	renderInputRows(weekStart);
	
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
			// bar.textContent = s.name + "（" + s.startTime + "〜" + s.endTime + "）";
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
}

function renderInputRows(weekStart) {
	const container = document.getElementById("inputRowsContainer");
	container.innerHTML = ""; // 一旦クリア

	for (let i = 0; i < 7; i++) {
	const date = new Date(weekStart);
	date.setDate(date.getDate() + i);
	const yyyy = date.getFullYear();
	const mm = String(date.getMonth() + 1).padStart(2, "0");
	const dd = String(date.getDate()).padStart(2, "0");
	const dayName = WEEKDAYS[date.getDay()];
	const dateStr = yyyy + "-" + mm + "-" + dd;
	const displayStr = mm + "/" + dd + " (" + dayName + ")";

	const row = document.createElement("div");
	row.className = "shift-input-row";
	row.dataset.date = dateStr;

	row.innerHTML = `
	<span class="input-date-label">${'$'}{displayStr}</span>
	<input type="time" class="start-time" required>
	<input type="time" class="end-time" required>
	<input type="text" class="worker-name" placeholder="名前" required>
	`;

	
	container.appendChild(row);
	}
}

document.getElementById("bulkAddBtn").addEventListener("click", () => {
	const rows = document.querySelectorAll(".shift-input-row");
	let added = 0;
	
	rows.forEach(row => {
		const date = row.dataset.date;
		const start = row.querySelector(".start-time").value;
		const end = row.querySelector(".end-time").value;
		const name = row.querySelector(".worker-name").value;
		
		if (start && end && name) {
			shifts.push({ date, startTime: start, endTime: end, name });
			added++;
		}
	});
	
	const weekIndex = parseInt(document.getElementById("weekSelector").value);
	renderTimeline(weekIndex);
	
	alert(`${added}件のシフトを追加しました。`);
});

// 	↑がｇｐｔ

var lastDay = new Date();
lastDay.setDate(1);
lastDay.setMonth(lastDay.getMonth() + 2);
lastDay.setDate(0); // 来月/最終日

/*
function addListItem() {
	const date = new Date();
	date.setMonth(date.getMonth() + 1);
	date.setDate(0);
	for (let i = 0; i < lastDay.getDate(); i++) {
		date.setDate(date.getDate() + 1);
		const yyyy = date.getFullYear();
		const mm = String(date.getMonth() + 1).padStart(2, "0");
		const dd = String(date.getDate()).padStart(2, "0");
		const dayName = WEEKDAYS[date.getDay()];
		const dateStr = yyyy + "-" + mm + "-" + dd;
		const displayStr = mm + "/" + dd + " (" + dayName + ")";
		
		
		const ul = document.getElementById("add_button");
		
		
		const li = document.createElement("li");
		const item = document.createElement("div");
		item.className = "shift-input-row";
		
		item.innerHTML = `
			<div class="tateisitai">
				<table>
					<tr>
						<td>${'$'}{displayStr}</td>
						<td>
							<div class="kkadd">
							<button type="button" class="add" onclick="prihidd(this)">
								<b>追加</b>
							</button>
							</div>
						</td>
					</tr>
				</table>
				<div class="kokoform" hidden="">
					<div class="time-entry">
			      		<div class="time-inputs">
			      			<div class="time-row">
			        		<label>開始時刻：</label><input type="time" name="startTime">
			        		</div>
			        		<div class="time-row">
				        		<label>終了時刻：</label><input type="time" name="endTime">
							</div>
						</div>
						<button type="button" class="delete-btn" onclick="del(this)">削除</button>
					</div>
				</div>
			</div>
		`;
		li.appendChild(item);
		
		ul.appendChild(li);
		
	}
}
*/

var fd = new Date();
fd.setDate(1); 
fd.setMonth(fd.getMonth() + 1);// fdを来月初日
console.log(fd);
let atoNanDay = lastDay.getDate();
atoNanDay -= fd.getDay();
console.log(atoNanDay);
let weekNum = 0;
if(fd.getDay() != 0){
	weekNum++;
}
weekNum = Math.floor(atoNanDay/7);
if(atoNanDay % 7 != 0){
	weekNum++;
}
// ↑でweekNumに来月の週の数を格納

function addListItem() {
	const date = new Date();
	date.setMonth(date.getMonth() + 1);
	date.setDate(0);
	for (let i = 0; i < (7 - fd.getDay()); i++) {
		date.setDate(date.getDate() + 1);
		const yyyy = date.getFullYear();
		const mm = String(date.getMonth() + 1).padStart(2, "0");
		const dd = String(date.getDate()).padStart(2, "0");
		const dayName = WEEKDAYS[date.getDay()];
		const dateStr = yyyy + "-" + mm + "-" + dd;
		const displayStr = mm + "/" + dd + " (" + dayName + ")";
		
		
		const div = document.getElementById("week1");
		
		const item = document.createElement("div");
		item.className = "shift-input-row";
		
		item.innerHTML = `
			<div class="tateisitai">
				<table>
					<tr>
						<td>${'$'}{displayStr}</td>
						<td>
							<div class="kkadd">
							<button type="button" class="add" onclick="prihidd(this)">
								<b>追加</b>
							</button>
							</div>
						</td>
					</tr>
				</table>
				<div class="kokoform" hidden="">
					<div class="time-entry">
			      		<div class="time-inputs">
			      			<div class="time-row">
			        		<label>開始時刻：</label><input type="time" name="startTime">
			        		</div>
			        		<div class="time-row">
				        		<label>終了時刻：</label><input type="time" name="endTime">
							</div>
						</div>
						<button type="button" class="delete-btn" onclick="del(this)">削除</button>
					</div>
				</div>
			</div>
		`;
		
		div.appendChild(item);
		
	}
	for (let j = 2; j < 5; j++){
		for (let i = 0; i < 7; i++) {
			date.setDate(date.getDate() + 1);
			const yyyy = date.getFullYear();
			const mm = String(date.getMonth() + 1).padStart(2, "0");
			const dd = String(date.getDate()).padStart(2, "0");
			const dayName = WEEKDAYS[date.getDay()];
			const dateStr = yyyy + "-" + mm + "-" + dd;
			const displayStr = mm + "/" + dd + " (" + dayName + ")";
			
			const idStr = "week" + j;
			const div = document.getElementById(idStr);
			
			const item = document.createElement("div");
			item.className = "shift-input-row";
			
			item.innerHTML = `
				<div class="tateisitai">
					<table>
						<tr>
							<td>${'$'}{displayStr}</td>
							<td>
								<div class="kkadd">
								<button type="button" class="add" onclick="prihidd(this)">
									<b>追加</b>
								</button>
								</div>
							</td>
						</tr>
					</table>
					<div class="kokoform" hidden="">
						<div class="time-entry">
				      		<div class="time-inputs">
				      			<div class="time-row">
				        		<label>開始時刻：</label><input type="time" name="startTime">
				        		</div>
				        		<div class="time-row">
					        		<label>終了時刻：</label><input type="time" name="endTime">
								</div>
							</div>
							<button type="button" class="delete-btn" onclick="del(this)">削除</button>
						</div>
					</div>
				</div>
			`;
			div.appendChild(item);
		}
	}
	/*
	if(weekNum == 5){
		const dn = 7 * 3 + 
		for (let i = 0; i <7 - fd.getDay(); i++) {
			date.setDate(date.getDate() + 1);
			const yyyy = date.getFullYear();
			const mm = String(date.getMonth() + 1).padStart(2, "0");
			const dd = String(date.getDate()).padStart(2, "0");
			const dayName = WEEKDAYS[date.getDay()];
			const dateStr = yyyy + "-" + mm + "-" + dd;
			const displayStr = mm + "/" + dd + " (" + dayName + ")";
			
			
			const div = document.getElementById("week5");
			
			const item = document.createElement("div");
			item.className = "shift-input-row";
			
			item.innerHTML = `
				<div class="tateisitai">
					<table>
						<tr>
							<td>${'$'}{displayStr}</td>
							<td>
								<div class="kkadd">
								<button type="button" class="add" onclick="prihidd(this)">
									<b>追加</b>
								</button>
								</div>
							</td>
						</tr>
					</table>
					<div class="kokoform" hidden="">
						<div class="time-entry">
				      		<div class="time-inputs">
				      			<div class="time-row">
				        		<label>開始時刻：</label><input type="time" name="startTime">
				        		</div>
				        		<div class="time-row">
					        		<label>終了時刻：</label><input type="time" name="endTime">
								</div>
							</div>
							<button type="button" class="delete-btn" onclick="del(this)">削除</button>
						</div>
					</div>
				</div>
			`;
			
			div.appendChild(item);
		}
	}
	*/
}
addListItem();

//追加ボタンのイベント
const abtns = document.querySelectorAll('button.add');
abtns.forEach((btn, index) => {
	btn.id = "abtn" + (index + 1);
});

const kkfs = document.querySelectorAll('div.kokoform');
kkfs.forEach((pri, index) => {
	pri.id = "abtn" + (index + 1) + "fm";
});

const kkads = document.querySelectorAll('div.kkadd');
kkads.forEach((pri, index) => {
	pri.id = "abtn" + (index + 1) + "f";
});

function prihidd(elem){
	let id = elem.id + "f";
	document.getElementById(id).hidden = true;
	id = id + "m";
	const vanish = document.getElementById(id);
	vanish.hidden = false; 
}

const dbtns = document.querySelectorAll('button.delete-btn');
dbtns.forEach((btn, index) => {
	btn.id = (index + 1) + "f";
});

function del(elem){
	let id = "abtn" + elem.id;
	document.getElementById(id).hidden = false;
	id = id + "m";
	const vanish = document.getElementById(id);
	vanish.hidden = true; 
}



// 週選択のリストを作成
const select = document.getElementById('weSel');

for(let i = 0; i < weekNum; i++) {
	const newOption = document.createElement('option');
	newOption.value = "value" + i;
	newOption.text = (fd.getMonth() + 1) + "月 第" + (i + 1) + "週";

	// セレクトボックスに追加
	select.appendChild(newOption);
}
/*
// 週選択でボタンの表示切替
document.getElementById("weSel").addEventListener("change", e => {
	renderTimeline(parseInt(e.target.value));
});
*/
</script>

</body>
</html>



