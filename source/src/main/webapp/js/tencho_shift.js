const HOURS = Array.from({ length: 10 }, (_, i) => 9 + i); // 9:00〜18:00

const shifts = [
	{ date: '2025-06-02', name: '山田', startTime: '09:00', endTime: '17:00' },
	{ date: '2025-06-03', name: '田中', startTime: '13:00', endTime: '18:00' },
	{ date: '2025-06-03', name: '佐藤', startTime: '12:00', endTime: '15:00' }, // ★被り
	{ date: '2025-06-10', name: '佐藤', startTime: '10:00', endTime: '16:00' },
	{ date: '2025-06-17', name: '山田', startTime: '10:00', endTime: '18:00' },
	{ date: '2025-06-25', name: '田中', startTime: '09:30', endTime: '12:00' }
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

function renderTimeline(startDate) {
	grid.innerHTML = '';

	const weekStart = new Date(startDate);
	
	for (let i = 0; i < 7; i++) {
		const currentDate = new Date(weekStart);
		currentDate.setDate(weekStart.getDate() + i);
		const dateStr = formatDate(currentDate);
		const day = currentDate.getDate();
		const month = currentDate.getMonth() + 1;
		const dayName = WEEKDAYS[currentDate.getDay()];
		const dailyShifts = shifts.filter(s => s.date === dateStr);
		
		const row = document.createElement("div");
		row.className = "day-row";
		
		const label = document.createElement("div");
		label.className = "date-label";
		label.textContent = month + "/" + day + "(" + dayName + ")";
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
			bar.style.left = `${left}px`;
			bar.style.width = `${width}px`;
			bar.style.top = `${5 + index * 35}px`; // ← シフトごとに縦にずらす
			bar.textContent = s.name;
		
			line.appendChild(bar);
		});
		
		//⭐ 高さを調整（これが今回の修正点）
		const rowHeight = Math.max(40, dailyShifts.length * 40);
		line.style.height = `${rowHeight}px`;
		row.style.height = `${rowHeight}px`;
		
		row.appendChild(line);
		grid.appendChild(row);
	}
}
/*
document.getElementById("weekSelector").addEventListener("change", e => {
	renderTimeline(parseInt(e.target.value));
});
*/

renderTimeline(0); // 初期表示：第1週

function formatDate(date) {
	return date.toLocaleDateString("sv-SE");
}

function printSection(sectionId) {
    var content = document.getElementById(sectionId).innerHTML;

    var printWindow = window.open('', '', 'width=800,height=600');
    printWindow.document.write(`
        <html>
        <head>
            <title>印刷</title> 
            <link rel="stylesheet" type="text/css" href="${cssUrl}">
        </head>
        <body>
            ${content}
        </body>
        </html>
    `);
    printWindow.document.close();
    printWindow.focus();
    printWindow.print();
    printWindow.close();
}

const tableBody = document.querySelector('#shiftTable tbody');

shifts.forEach(shift => {
  	const row = document.createElement('tr');

  	row.innerHTML = `
    	<td>${shift.date}</td>
    	<td>${shift.name}</td>
    	<td>${shift.startTime}</td>
    	<td>${shift.endTime}</td>
  	`;

 	 tableBody.appendChild(row);
});

function text2Hidden() {
	const text1 = document.getElementById('text1');
	const text2 = document.getElementById('text2');
	text1.hidden = false;
	text2.hidden = true; // trueならfalseに、falseならtrueに
}
function text1Hidden() {
	const text1 = document.getElementById('text1');
	const text2 = document.getElementById('text2');
	text1.hidden = true; // trueならfalseに、falseならtrueに
	text2.hidden = false;
}


let cDate = new Date(); 
// 日付を週の月曜日に合わせる
function getStartOfWeek(date) {
	const d = new Date(date);
	d.setDate(d.getDate() - d.getDay());
	return d;
}

let currentWeekStart = getStartOfWeek(cDate);
renderTimeline(currentWeekStart);

// ボタンイベント
document.getElementById("prevWeek").addEventListener("click", () => {
currentWeekStart.setDate(currentWeekStart.getDate() - 7);
renderTimeline(currentWeekStart);
});

document.getElementById("nextWeek").addEventListener("click", () => {
currentWeekStart.setDate(currentWeekStart.getDate() + 7);
renderTimeline(currentWeekStart);
});

