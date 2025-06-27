<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">  
	<title>エンプロ良イ&#128077｜マニュアル管理</title>
	<link rel="stylesheet" href="<c:url value='/css/style.css' />">
	<link rel="stylesheet" href="<c:url value='/css/tencho_manual.css' />">
</head>
<body>
	<header>
		<h1 id="logo">
			<a href="tencho_calendar.jsp"><img src="img/logo.png" width="300" alt="エンプロ良イ:+1:"></a>
		</h1>
		<ul id="nav">
			<li><a href="<c:url value='/CalendarServlet'/>">カレンダー</a></li>
			<li><a href="<c:url value='/ShiftServlet'/>">シフト</a></li>
			<li><a href="<c:url value='/EventServlet'/>">イベント</a></li>
			<li><a href="<c:url value='/ManualServlet' />">マニュアル</a></li>      
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
		<form action="<c:url value='/ManualServlet' />" method="post" enctype="multipart/form-data">
			<div id="ddAreaFileRef">
				枠内をクリックしてファイルを選択
			</div>
			<input type="file" id="fileInput" name="file" accept="image/*">
			<div id="fileList"></div>
			<h3>重要度を設定<br>重要度を以下の色から選んでください：</h3>	
			<!-- <p>重要度を色で選んでください：</p> -->
			<div class="color-option">
				<label>
					<input type="radio" name="importance" value="1">
					<span class="color-label color-red"></span>
				</label>
				<label>
					<input type="radio" name="importance" value="2">
					<span class="color-label color-blue"></span>
				</label>
				<label>
					<input type="radio" name="importance" value="3">
					<span class="color-label color-green"></span>
				</label>
				<label>
					<input type="radio" name="importance" value="4">
					<span class="color-label color-orange"></span>
				</label>
				<label>
					<input type="radio" name="importance" value="5">
					<span class="color-label color-purple"></span>
				</label>
			</div>
			<div class="regist"><button type = "submit" name="bu" value="登録">登録</button></div>
			<!-- <h2 id="uploadTrigger">登録</h2> -->
		</form>
		<h3>登録済みマニュアル一覧</h3>
		<div id="trzmtbl">
			<%-- <img src="<c:url value='/mt/${e.img}' />"> これ要らないかも--%>
			<table border="1">
				<tr>
					<th>ID</th>
					<th>ファイル名</th>
					<th>重要度</th>
					<th>更新日</th>
				</tr>
				<c:forEach var="manual" items="${manualList}">
					<tr>
						<td>${manual.fileId}</td>
						<td><a href="<c:url value='/mt/${manual.manualFile}'/>">${manual.manualFile}</a></td>
						<td>${manual.importance}</td>
						<td>${manual.date}</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</main>
	<script>
	//ここから↓
	function previewImage(obj){
		var fileReader = new FileReader();
		
		// 読み込み後に実行する処理
		fileReader.onload = (function() {
			// canvas にプレビュー画像を表示
			var canvas = document.getElementById('preview');
			var ctx = canvas.getContext('2d');
			var image = new Image();
			image.src = fileReader.result;
			console.log(fileReader.result) // ← (確認用)
			
			image.onload = (function () {
				canvas.width = image.width;
				canvas.height = image.height;
				ctx.drawImage(image, 0, 0);
			});
		});
		
		// 画像読み込み
		fileReader.readAsDataURL(obj.files[0]);
		console.log(fileReader.result) // ← (確認用)null
	}
	
	//ここまで↑先生のやつ  
	window.onload = function () {
		const ddAreaFileRef = document.getElementById('ddAreaFileRef');
		const fileInput = document.getElementById('fileInput');
		const fileList = document.getElementById('fileList');
		const uploadTrigger = document.getElementById('uploadTrigger');
		
		// ドラッグ系
		ddAreaFileRef.addEventListener('dragover', (event) => {
			event.preventDefault();
			ddAreaFileRef.classList.add('hover');
		});
		ddAreaFileRef.addEventListener('dragleave', () => {
			ddAreaFileRef.classList.remove('hover');
		});
		ddAreaFileRef.addEventListener('drop', (event) => {
			event.preventDefault();
			ddAreaFileRef.classList.remove('hover');
			const files = event.dataTransfer.files;
			handleFiles(files);
		});
		
		// クリックでファイル選択
		ddAreaFileRef.addEventListener('click', () => {
			fileInput.click();
		});
		
		// ファイル選択時
		fileInput.addEventListener('change', () => {
			const files = fileInput.files;
			handleFiles(files);
		});
		
		function handleFiles(files) {
			fileList.innerHTML = '';
			for (let i = 0; i < files.length; i++) {
				const file = files[i];
				const fileElement = document.createElement('div');
				fileElement.textContent = file.name;
				fileList.appendChild(fileElement);
			}
		}
		
		uploadTrigger.addEventListener('click', () => {
			const files = fileInput.files;
			if (files.length === 0) {
				alert("ファイルを選択してください");
				return;
			}
			uploadFiles(files);
		});
		
		function uploadFiles(files) {
			const formData = new FormData();
			for (let i = 0; i < files.length; i++) {
				formData.append("file", files[i]);
			}
			
			fetch("<%= request.getContextPath() %>/ManualServlet", {
				method: "POST",
				body: formData
			})
			
			.then(response => response.text())
			.then(result => {
				alert("アップロード成功: " + result);
			})
			
			.catch(error => {
				alert("アップロード失敗: " + error);
			});
		}
	};
	</script>
	<footer>
	<p class="gotop">
      <a href="#top">
        <img src= "<c:url value= '/img/gotop.png'/>" alt="ページトップへ戻る" width="70" height="auto">
      </a>
    </p>
    <p>&copy; エンプロ良イ&#128077</p>
	</footer>
</body>
</html>