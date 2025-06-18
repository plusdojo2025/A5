
// 初期状態：今日から7日分表示
let shiftData = generateDateRange(new Date().toISOString().split('T')[0], 7);


function generateInitialWeek() {
const days = [];
const today = new Date();

for (let i = -3; i <= 3; i++) { // ← 表示する日数を変更したい場合はここを調整
const d = new Date(today);
d.setDate(today.getDate() + i);

const y = d.getFullYear();
const m = String(d.getMonth() + 1).padStart(2, '0');
const day = String(d.getDate()).padStart(2, '0');
const dateStr = `${y}-${m}-${day}`;

days.push({ date: dateStr, shifts: [] });
}

return days;
}

function generateDateRange(startDateStr, days) {
const daysArray = [];
const start = new Date(startDateStr);

for (let i = 0; i < days; i++) {
const d = new Date(start);
d.setDate(start.getDate() + i);

const y = d.getFullYear();
const m = String(d.getMonth() + 1).padStart(2, '0');
const day = String(d.getDate()).padStart(2, '0');
const dateStr = `${y}-${m}-${day}`;

daysArray.push({ date: dateStr, shifts: [] });
}

return daysArray;
}



document.getElementById("shiftForm").addEventListener("submit", function (e) {
e.preventDefault();

const date = document.getElementById("date").value;
const name = document.getElementById("name").value;
const start = document.getElementById("start").value;
const end = document.getElementById("end").value;

if (!date || !name || !start || !end) return;

let day = shiftData.find(d => d.date === date);
if (!day) {
day = { date, shifts: [] };
shiftData.push(day);
}

day.shifts.push({ name, start, end });

document.getElementById("shiftForm").reset();

renderTimeline();
});

function timeToPercent(time) {
const [h, m] = time.split(":").map(Number);
const minutes = h * 60 + m;
const start = 8 * 60;
const end = 22 * 60;
return ((minutes - start) / (end - start)) * 100;
}

function renderTimeline() {
const container = document.getElementById("shiftTimeline");
container.innerHTML = "";

shiftData.sort((a, b) => a.date.localeCompare(b.date));

shiftData.forEach(day => {
const dayDiv = document.createElement("div");
dayDiv.className = "day";

const dateLabel = document.createElement("div");
dateLabel.className = "date-label";
dateLabel.textContent = `📅 ${day.date}`;
dayDiv.appendChild(dateLabel);

const timelineWrapper = document.createElement("div");
timelineWrapper.className = "timeline-wrapper";

const timeScale = document.createElement("div");
timeScale.className = "time-scale";

["08:00", "10:00", "12:00", "14:00", "16:00", "18:00", "20:00", "22:00"].forEach(t => {
const span = document.createElement("span");
span.textContent = t;
timeScale.appendChild(span);
});

timelineWrapper.appendChild(timeScale);

day.shifts.forEach(shift => {
const row = document.createElement("div");
row.className = "shift-row";

const bar = document.createElement("div");
bar.className = "shift-bar";

const start = timeToPercent(shift.start);
const end = timeToPercent(shift.end);
const width = end - start;

bar.style.left = `${start}%`;
bar.style.width = `${width}%`;
bar.textContent = shift.name;

row.appendChild(bar);
timelineWrapper.appendChild(row);
});

dayDiv.appendChild(timelineWrapper);
container.appendChild(dayDiv);
});
}
renderTimeline();

document.getElementById("updateRange").addEventListener("click", () => {
const startDate = document.getElementById("rangeStart").value;
const days = parseInt(document.getElementById("rangeDays").value);

if (!startDate || isNaN(days)) return;

// シフトデータ保持のため、既存のデータはマージするようにも改修可能
shiftData = generateDateRange(startDate, days);
renderTimeline();
});
