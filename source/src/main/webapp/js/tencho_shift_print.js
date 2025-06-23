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
}