<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	import="java.time.LocalDate,
			java.time.YearMonth,
			java.time.DayOfWeek,
			java.time.format.TextStyle,
			java.time.format.DateTimeFormatter,
			java.time.temporal.TemporalAdjusters,
			java.util.*"
%>
<%
	// 現在の年月
	LocalDate now = LocalDate.now();
	YearMonth ym = YearMonth.of(now.getYear(), now.getMonth());
	Locale locale = Locale.JAPAN;
	
	// 各週の情報を格納するリスト
	List<Map<String, String>> weekList = new ArrayList<>();
	
	// 月初から1週間ずつ処理
	LocalDate firstDay = ym.atDay(1).with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY));
	LocalDate date = firstDay.with(DayOfWeek.MONDAY);
	int weekCount = 1;
	
	while (date.getMonth() == ym.getMonth()) {
		LocalDate weekStart = date;
		LocalDate weekEnd = date.plusDays(6);
		if (weekEnd.getMonth() != ym.getMonth()) {
			weekEnd = ym.atEndOfMonth(); // 月末で調整
		}
		
		Map<String, String> weekInfo = new HashMap<>();
		weekInfo.put("label", ym.getMonthValue() + "月第" + weekCount + "週");
		weekInfo.put("start", weekStart.toString());
		weekInfo.put("end", weekEnd.toString());
		weekList.add(weekInfo);
		
		date = date.plusWeeks(1);
		weekCount++;
	}
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>エンプロ良イ👍｜イベント</title>
	<link rel="stylesheet" href="/webapp/css/baito_shift.css">
</head>
<body>
	<header>
		<h1 id="logo">
			<a href="tencho_calendar.jsp"><img src="images/logo.png" width=300 height=auto alt="エンプロ良イ👍"></a>
		</h1>
		<ul id="nav">
			<li><a href="baito_calelndar.jsp">カレンダー</a></li>
			<li><a href="baito_shift.jsp">シフト</a></li>
			<li><a href="baito_event.jsp">イベント</a></li>
			<li><a href="baito_manual.jsp">マニュアル</a></li>
			<details>
				<summary class="details-summary">その他</summary>
				<li><a href="baito_user_edit.jsp">ユーザー管理</a></li>
				<li><a href="baito_login.jsp">ログアウト</a></li>
			</details>
		</ul>
	</header>
	<main>
		<!-- カレンダー -->
		<!-- 日付と時間選択 -->
		<select id="weekSelect" onchange="showTable()">
			<option value="">-- 選択 --</option>
			<% for (int i = 0; i < weekList.size(); i++) {
				Map<String, String> week = weekList.get(i);
				String id = "week" + i;
			%>
			<option value="<%= id %>"><%= week.get("label") %></option>
			<% } %>
		</select>
		<% for (int i = 0; i < weekList.size(); i++) {
			Map<String, String> week = weekList.get(i);
			String id = "week" + i;
			LocalDate start = LocalDate.parse(week.get("start"));
			LocalDate end = LocalDate.parse(week.get("end"));
			String formattedRange = start.format(DateTimeFormatter.ofPattern("MM/dd (E)", locale)) +
									" ～ " +
									end.format(DateTimeFormatter.ofPattern("MM/dd (E)", locale));
		%>
		<form id="form">
			<div id="<%= id %>" class="weekSection" style="display:none;">
				<h3><%= week.get("label") %>（<%= formattedRange %>）</h3>
				<table>
					<% LocalDate d = start;
					while (!d.isAfter(end)) { %>
					<tr>
						<td><input type="text" name="date" value="<%= d.format(DateTimeFormatter.ofPattern("MM/dd (E)", locale)) %>" readonly="readonly"></td>
					</tr>
					<tr>
						<td>start<input type="time" list="daliststart" step="600" id="<%= d.format(DateTimeFormatter.ofPattern("MM-dd", locale)) %>" name="start" min="9:00" max="18:00">
						<datalist id="daliststart">
							<option value="9:00"></option>
							<option value="9:10"></option>
							<option value="9:20"></option>
							<option value="9:30"></option>
							<option value="9:40"></option>
							<option value="9:50"></option>
							<option value="10:00"></option>
							<option value="10:10"></option>
							<option value="10:20"></option>
							<option value="10:30"></option>
							<option value="10:40"></option>
							<option value="10:50"></option>
							<option value="11:00"></option>
							<option value="11:10"></option>
							<option value="11:20"></option>
							<option value="11:30"></option>
							<option value="11:40"></option>
							<option value="11:50"></option>
							<option value="12:00"></option>
							<option value="12:10"></option>
							<option value="12:20"></option>
							<option value="12:30"></option>
							<option value="12:40"></option>
							<option value="12:50"></option>
							<option value="13:00"></option>
							<option value="13:10"></option>
							<option value="13:20"></option>
							<option value="13:30"></option>
							<option value="13:40"></option>
							<option value="13:50"></option>
							<option value="14:00"></option>
							<option value="14:10"></option>
							<option value="14:20"></option>
							<option value="14:30"></option>
							<option value="14:40"></option>
							<option value="14:50"></option>
							<option value="15:00"></option>
							<option value="15:10"></option>
							<option value="15:20"></option>
							<option value="15:30"></option>
							<option value="15:40"></option>
							<option value="15:50"></option>
							<option value="15:00"></option>
							<option value="15:10"></option>
							<option value="15:20"></option>
							<option value="15:30"></option>
							<option value="15:40"></option>
							<option value="15:50"></option>
							<option value="16:00"></option>
							<option value="16:10"></option>
							<option value="16:20"></option>
							<option value="16:30"></option>
							<option value="16:40"></option>
							<option value="16:50"></option>
							<option value="17:00"></option>
							<option value="17:10"></option>
							<option value="17:20"></option>
							<option value="17:30"></option>
							<option value="17:40"></option>
							<option value="17:50"></option>
							<option value="18:00"></option>
						</datalist></td>
						<td><button type="button">▲</button><br><button type="button">▼</button></td>
						<td>
							<select name="event">
								<option value="">選択してください</option>
								<option value="貸切">貸切</option>
								<option value="催事">催事</option>
								<option value="期間限定">期間限定</option>
								<option value="定期開催">定期開催</option>
								<option value="シフト締切">シフト締切</option>
								<option value="臨時休業">臨時休業</option>
							</select>
						</td>
					</tr>
					<tr>
						<td>end<input type="time" list="dalistend" step="600" id="<%= d.format(DateTimeFormatter.ofPattern("MM_dd", locale)) %>" name="end" min="9:00" max="18:00">
						<datalist id="dalistend">
							<option value="9:00"></option>
							<option value="9:10"></option>
							<option value="9:20"></option>
							<option value="9:30"></option>
							<option value="9:40"></option>
							<option value="9:50"></option>
							<option value="10:00"></option>
							<option value="10:10"></option>
							<option value="10:20"></option>
							<option value="10:30"></option>
							<option value="10:40"></option>
							<option value="10:50"></option>
							<option value="11:00"></option>
							<option value="11:10"></option>
							<option value="11:20"></option>
							<option value="11:30"></option>
							<option value="11:40"></option>
							<option value="11:50"></option>
							<option value="12:00"></option>
							<option value="12:10"></option>
							<option value="12:20"></option>
							<option value="12:30"></option>
							<option value="12:40"></option>
							<option value="12:50"></option>
							<option value="13:00"></option>
							<option value="13:10"></option>
							<option value="13:20"></option>
							<option value="13:30"></option>
							<option value="13:40"></option>
							<option value="13:50"></option>
							<option value="14:00"></option>
							<option value="14:10"></option>
							<option value="14:20"></option>
							<option value="14:30"></option>
							<option value="14:40"></option>
							<option value="14:50"></option>
							<option value="15:00"></option>
							<option value="15:10"></option>
							<option value="15:20"></option>
							<option value="15:30"></option>
							<option value="15:40"></option>
							<option value="15:50"></option>
							<option value="15:00"></option>
							<option value="15:10"></option>
							<option value="15:20"></option>
							<option value="15:30"></option>
							<option value="15:40"></option>
							<option value="15:50"></option>
							<option value="16:00"></option>
							<option value="16:10"></option>
							<option value="16:20"></option>
							<option value="16:30"></option>
							<option value="16:40"></option>
							<option value="16:50"></option>
							<option value="17:00"></option>
							<option value="17:10"></option>
							<option value="17:20"></option>
							<option value="17:30"></option>
							<option value="17:40"></option>
							<option value="17:50"></option>
							<option value="18:00"></option>
						</datalist></td>
						<td><button type="button">▲</button><br><button type="button">▼</button></td>
						<td><button type="button" onclick="getId(this)">削除</button></td>
					</tr>
					<% d = d.plusDays(1); } %>
				</table>
			</div>
			<% } %>
			<input type="submit" value="保存">
		</form>
		<!-- 上に戻るボタン -->
		<button  type="button" onclick="location.href='#top'">上に戻る</button>
	</main>
	<footer>
	</footer>
	<script>
		function showTable() {
			const selected = document.getElementById("weekSelect").value;
			const sections = document.getElementsByClassName("weekSection");
			for (let i = 0; i < sections.length; i++) {
				sections[i].style.display = "none";
			}
			if (selected) {
				document.getElementById(selected).style.display = "block";
			}
		}
		
		function adjustTime(minutes) {
			const input = document.getElementById("StartTime");
			let [h, m] = input.value.split(":").map(Number);
			let total = h * 60 + m + minutes;
			if (total < 0) total = 0;
			if (total > 23 * 60 + 59) total = 23 * 60 + 59;
			const hh = String(Math.floor(total / 60)).padStart(2, "0");
			const mm = String(total % 60).padStart(2, "0");
			input.value = `${hh}:${mm}`;
		}
		
		function getId(ele) {
			var id_value = ele.id;
			console.log(id_value)
		}
	</script>
</body>
</html>