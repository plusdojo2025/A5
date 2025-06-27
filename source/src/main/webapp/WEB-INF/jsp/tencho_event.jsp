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
		<h1 id="logo">
			<a href="<c:url value='/CalendarServlet'/>"><img src="<c:url value='/img/logo.png'/>" width="300" height="auto" alt="エンプロ良イ&#128077"></a>
		</h1>
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
	</header>
	<main>
		<!-- 登録済みのイベント表示 -->
		<h2>直近のイベント</h2>
		<div  class="table">
			<table>
				<tr>
					<th>日付</th>
					<th>時間</th>
					<th>イベント</th>
					<th></th>
				</tr>
				<c:forEach var="e" items="${eventList}">
					<form method="POST" action="<c:url value='/EventServlet'/>">
						<tr> 
							<td><input type="hidden" value="${e.eventDate}" name="delEventDate" readonly="readonly">${e.eventDate}</td>
							<td><input type="hidden" value="${e.eventStart}" name="delEventStart" readonly="readonly">${e.eventStart}～<input type="hidden" value="${e.eventEnd}" name="delEventEnd" readonly="readonly">${e.eventEnd}</td>
							<td><input type="hidden" value="${e.eventType}" name="delEventType" readonly="readonly">${e.eventType}</td>
							<td><button type="submit" name="submit" value="イベント削除" onclick="return delCheck()">削除</button>
						</tr>
					</form>
				</c:forEach>
			</table>
		</div>
		<br>
		<!-- 選択肢を編集ボタン -->
		<div class="editChoice">
			<button onclick="openModal()">選択肢を編集</button>
		</div>
		<!-- モーダル本体 -->
		<div id="modal" class="modal">
			<div class="modal-content">
				<span class="close" onclick="closeModal()">&times;</span>
				<table>
					<c:forEach var="f" items="${eventTypeList}">
						<tr>
							<td>
								<form method="POST" action="<c:url value='/EventServlet'/>">
									<input type="hidden" value="${f}" name="delEvent">${f}
							</td>
							<td>
									<button type="submit" name="submit" value="削除" onclick="return delCheck()">削除</button>
								</form>
							</td>
						</tr>
					</c:forEach>
					<tr>
						<td>
							<form method="POST" action="<c:url value='/EventServlet'/>" id="newEvent">
								<input type="text" name="newEvent" placeholder="新しいイベント名を入力">
						</td>
						<td>
								<button type="submit" name="submit" value="追加">追加</button>
							</form>
						</td>
					</tr>
				</table>
			</div>
		</div>
		<br>
		<!-- イベント登録 -->
		<form id="regEvent" method="POST" action="<c:url value='/EventServlet' />">
			<div id="addButton">
				<div class="addEvent"><button type="button" value="追加" onclick="addTable()">追加</button></div><br>
				<div class="regEvent"><button type="submit" name="submit" value="登録">登録</button></div>
				<br><span id="error_message"></span>
			</div>
		</form>
	</main>
	<footer>
		<!-- ページトップに戻るボタン -->
		<p class="gotop">
			<a href="#top">
				<img src="<c:url value='/img/gotop.png' />" alt="ページトップへ戻る" width="70" height="auto">
			</a>
			<p>&copy; エンプロ良イ&#128077</p>
		</p>
	</footer>
	<script>
		'use strict';
		// 追加ボタンをおして入力欄を出す機能
		let tableCount = 0;
		function addTable() {
			if (tableCount < 7) {
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
				html += "<table>"
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
				html += "<c:forEach var='g' items='${eventTypeList}'>";
				html += "<option value='${g}'>${g}</option>";
				html += "</c:forEach>";
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
				html += "<td><button type='button' onclick=\"removeTable('" + tableId + "');return delCheck()\">削除</button></td></tr>";
				html += "</table>";
				html += "</div>"
				
				wrapper.innerHTML = html;
				
				var form = document.getElementById("regEvent");
				var addButtonDiv = document.getElementById("addButton");
				form.insertBefore(wrapper, addButtonDiv);
			}
		}
		
		// 削除ボタンをおして入力欄を消す機能
		function removeTable(id) {
			var wrapper = document.getElementById(id);
			if (wrapper) {
				wrapper.remove();
			} else {
				alert("テーブルが見つかりません: " + id);
			}
			tableCount--;
		}
		
		// モーダルを開く機能
		function openModal() {
			document.getElementById("modal").style.display = "block";
		}
		
		// モーダルを閉じる機能
		function closeModal() {
			document.getElementById("modal").style.display = "none";
		}
		
		// 背景クリックでモーダルを閉じる機能
		window.onclick = function(event) {
			const modal = document.getElementById("modal");
			if (event.target == modal) {
				modal.style.display = "none";
			}
		}
		
		// formをすべて入力しないとメッセージを表示する機能
		document.getElementById('regEvent').addEventListener('submit', function(e) {
			let isValid = true;
			const form = e.target;
			
			// すべての対象要素を取得
			const elements = form.querySelectorAll('input, select');
			
			// フォームの入力欄がない場合のエラーメッセージ表示
			if (elements.length === 0) {
				e.preventDefault();
				let errorMessageObj = document.getElementById('error_message');
				errorMessageObj.textContent = '※登録するイベントがありません。';
				return;
			}
			
			elements.forEach(el => {
				// 空欄かどうかをチェック
				if (!el.value.trim()) {
					isValid = false;
				}
				
				// selectタグ特有の「選択されていない」状態のチェック
				if (el.tagName === 'SELECT' && el.value === '') {
					isValid = false;
				}
				
				// datalist付きinputのチェック
				if (el.tagName === 'INPUT' && el.hasAttribute('list')) {
					const listId = el.getAttribute('list');
					const datalist = document.getElementById(listId);
					const options = Array.from(datalist.options).map(opt => opt.value);
					if (!options.includes(el.value)) {
						isValid = false;
					}
				}
			});
			
			// フォーム入力が正しければ確認ダイアログボックスを表示し、未入力の項目があればエラーメッセージを表示
			if (!isValid) {
				e.preventDefault(); // フォーム送信を止める
				let errorMessageObj = document.getElementById('error_message');
				errorMessageObj.textContent = '※全ての項目を入力してください';
			} else if (!window.confirm('登録します。よろしいですか？')) {
				return false;
			}
			
			errorMessageObj.textContent = null;
		});
		
		// formを入力しないとメッセージを表示する機能
		document.getElementById('newEvent').addEventListener('submit', function(e) {
			const input = this.querySelector('input[name="newEvent"]');
			
			if (!input.value.trim()) {
				e.preventDefault();
				alert("イベント内容を入力してください。");
			}
		});
		
		// 確認ダイアログボックスの表示
		function koushin() {
			if (!window.confirm('追加します。よろしいですか？')) {
				return false;
			}
		}
		
		function delCheck() {
			if (!window.confirm('削除します。よろしいですか？')) {
				return false;
			}
		}
	</script>
</body>
</html>