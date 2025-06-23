<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>エンプロ良イ&#128077｜イベント</title>
	<link rel="stylesheet" href="<c:url value='/css/tencho_event.css'/>">
	<link rel="stylesheet" href="<c:url value='/css/header_footer.css'/>">
	<link rel="stylesheet" href="<c:url value='/css/style.css'/>">
</head>
<body>
	<header>
		<div class="logo">
			<a href="<c:url value='/CalendarServlet'/>"><img src="<c:url value='/img/logo.png'/>" width="300" alt="エンプロ良イ&#128077"></a>
		</div>
		<ul id="nav">
			<li><a href="<c:url value='/CalendarServlet'/>">カレンダー</a></li>
			<li><a href="<c:url value='/ShiftCheckServlet'/>">シフト</a></li>
			<li><a href="<c:url value='/EventServlet'/>">イベント</a></li>
			<li><a href="<c:url value='/ManualServlet'/>">マニュアル</a></li>
			<details>
				<summary class="details-summary">その他</summary>
				<li><a href="baito_user_edit.jsp">ユーザー管理</a></li>
				<li><a href="baito_login.jsp">ログアウト</a></li>
			</details>
		</ul>
	</header>
	<main>
		<!-- 登録済みのイベント表示 -->
		<table>
			<tr>
				<th>日付</th>
				<th>時間</th>
				<th>イベント</th>
			</tr>
			<c:forEach var="e" items="${eventList}">
			<tr>
				<td>${e.eventDate}</td>
				<td>${e.eventStart}～${eventEnd}</td>
				<td>${e.eventType}</td>
			</tr>
			</c:forEach>
		</table>
		<!-- 登録するイベント表示 -->
		<div id="table" class="table"></div>
		<!-- 日付と時間選択 -->
		<form id="form" method="POST" action="<c:url value='/EventServlet' />">
			<div id="addButton">
				<input type="button" value="追加" onclick="addTable()">
				<input type="button" value="表示" onclick="generateTable()">
				<input type="submit" value="登録">
			</div>
		</form>
		<!-- 上に戻るボタン -->
		<button  type="button" onclick="location.href='#top'">上に戻る</button>
	</main>
	<footer>
	</footer>
	<script>
		let tableCount = 0;
		
		function addTable() {
			tableCount++;
			
			var tableId = "dynamicTable_" + tableCount;
			
			// テーブル全体を囲むdiv
			var wrapper = document.createElement("div");
			wrapper.setAttribute("id", tableId);
			
			// 新しいtable要素を作成
			const table = document.createElement("table");
			table.style.marginTop = "10px";
			
			// テーブルと削除ボタンを文字列で連結して作成
			var html = "";
			html += "<div class='form'>"
			html += "<table style='margin-top:20px;'>"
			html += "<tr><td><input type='date' name='date' class='date'></td></tr>";
			html += "<tr><td>start<input type='time' list='datalistStart' name='start' class='start' step='600' min='09:00' max='18:00'>";
			html += "<datalist id='datalistStart'>";
			html += "<option value='09:00'></option>";
			html += "<option value='09:10'></option>";
			html += "<option value='09:20'></option>";
			html += "<option value='09:30'></option>";
			html += "<option value='09:40'></option>";
			html += "<option value='09:50'></option>";
			html += "<option value='10:00'></option>";
			html += "<option value='10:10'></option>";
			html += "<option value='10:20'></option>";
			html += "<option value='10:30'></option>";
			html += "<option value='10:40'></option>";
			html += "<option value='10:50'></option>";
			html += "<option value='11:00'></option>";
			html += "<option value='11:10'></option>";
			html += "<option value='11:20'></option>";
			html += "<option value='11:30'></option>";
			html += "<option value='11:40'></option>";
			html += "<option value='11:50'></option>";
			html += "<option value='12:00'></option>";
			html += "<option value='12:10'></option>";
			html += "<option value='12:20'></option>";
			html += "<option value='12:30'></option>";
			html += "<option value='12:40'></option>";
			html += "<option value='12:50'></option>";
			html += "<option value='13:00'></option>";
			html += "<option value='13:10'></option>";
			html += "<option value='13:20'></option>";
			html += "<option value='13:30'></option>";
			html += "<option value='13:40'></option>";
			html += "<option value='13:50'></option>";
			html += "<option value='14:00'></option>";
			html += "<option value='14:10'></option>";
			html += "<option value='14:20'></option>";
			html += "<option value='14:30'></option>";
			html += "<option value='14:40'></option>";
			html += "<option value='14:50'></option>";
			html += "<option value='15:00'></option>";
			html += "<option value='15:10'></option>";
			html += "<option value='15:20'></option>";
			html += "<option value='15:30'></option>";
			html += "<option value='15:40'></option>";
			html += "<option value='15:50'></option>";
			html += "<option value='16:00'></option>";
			html += "<option value='16:10'></option>";
			html += "<option value='16:20'></option>";
			html += "<option value='16:30'></option>";
			html += "<option value='16:40'></option>";
			html += "<option value='16:50'></option>";
			html += "<option value='17:00'></option>";
			html += "<option value='17:10'></option>";
			html += "<option value='17:20'></option>";
			html += "<option value='17:30'></option>";
			html += "<option value='17:40'></option>";
			html += "<option value='17:50'></option>";
			html += "<option value='18:00'></option>";
			html += "</datalist></td>";
			html += "<td><select name='event' class='event'>";
			html += "<option value=''>選択してください</option>";
			html += "<option value='貸切'>貸切</option>";
			html += "<option value='催事'>催事</option>";
			html += "<option value='期間限定'>期間限定</option>";
			html += "<option value='定期開催'>定期開催</option>";
			html += "<option value='シフト締切'>シフト締切</option>";
			html += "<option value='臨時休業'>臨時休業</option>";
			html += "</select></td></tr>";
			html += "<tr><td>end<input type='time' list='datalistEnd' name='end' class='end' step='600' min='09:00' max='18:00'>";
			html += "<datalist id='datalistEnd'>";
			html += "<option value='09:00'></option>";
			html += "<option value='09:10'></option>";
			html += "<option value='09:20'></option>";
			html += "<option value='09:30'></option>";
			html += "<option value='09:40'></option>";
			html += "<option value='09:50'></option>";
			html += "<option value='10:00'></option>";
			html += "<option value='10:10'></option>";
			html += "<option value='10:20'></option>";
			html += "<option value='10:30'></option>";
			html += "<option value='10:40'></option>";
			html += "<option value='10:50'></option>";
			html += "<option value='11:00'></option>";
			html += "<option value='11:10'></option>";
			html += "<option value='11:20'></option>";
			html += "<option value='11:30'></option>";
			html += "<option value='11:40'></option>";
			html += "<option value='11:50'></option>";
			html += "<option value='12:00'></option>";
			html += "<option value='12:10'></option>";
			html += "<option value='12:20'></option>";
			html += "<option value='12:30'></option>";
			html += "<option value='12:40'></option>";
			html += "<option value='12:50'></option>";
			html += "<option value='13:00'></option>";
			html += "<option value='13:10'></option>";
			html += "<option value='13:20'></option>";
			html += "<option value='13:30'></option>";
			html += "<option value='13:40'></option>";
			html += "<option value='13:50'></option>";
			html += "<option value='14:00'></option>";
			html += "<option value='14:10'></option>";
			html += "<option value='14:20'></option>";
			html += "<option value='14:30'></option>";
			html += "<option value='14:40'></option>";
			html += "<option value='14:50'></option>";
			html += "<option value='15:00'></option>";
			html += "<option value='15:10'></option>";
			html += "<option value='15:20'></option>";
			html += "<option value='15:30'></option>";
			html += "<option value='15:40'></option>";
			html += "<option value='15:50'></option>";
			html += "<option value='16:00'></option>";
			html += "<option value='16:10'></option>";
			html += "<option value='16:20'></option>";
			html += "<option value='16:30'></option>";
			html += "<option value='16:40'></option>";
			html += "<option value='16:50'></option>";
			html += "<option value='17:00'></option>";
			html += "<option value='17:10'></option>";
			html += "<option value='17:20'></option>";
			html += "<option value='17:30'></option>";
			html += "<option value='17:40'></option>";
			html += "<option value='17:50'></option>";
			html += "<option value='18:00'></option>";
			html += "</datalist></td>";
			html += "<td><button type='button' onclick=\"removeTable('" + tableId + "');generateTable()\">削除</button></td></tr>";
			html += "</table>";
			html += "</div>"
			
			wrapper.innerHTML = html;
			
			var form = document.getElementById("form");
			var addButtonDiv = document.getElementById("addButton");
			form.insertBefore(wrapper, addButtonDiv);
		}
		
		function removeTable(id) {
			var wrapper = document.getElementById(id);
			if (wrapper) {
				wrapper.remove();
			} else {
				alert("テーブルが見つかりません: " + id);
			}
		}
		
		function generateTable() {
			const sets = document.querySelectorAll(".form");
			let html = "<table><tr><th>日付</th><th>時間</th><th>イベント</th></tr>";
			
			sets.forEach(set => {
				const date = set.querySelector(".date").value;
				const start = set.querySelector(".start").value;
				const end = set.querySelector(".end").value;
				const event = set.querySelector(".event").value.trim();
				
				if (date && start && end && event) {
					html += "<tr>" +
					"<td>" + date + "</td>" +
					"<td>" + start + "～" + end + "</td>" +
					"<td>" + event + "</td>" +
					"</tr>";
				}
			});
			
			html += "</table>";
			document.getElementById("table").innerHTML = html;
		}
	</script>
</body>
</html>