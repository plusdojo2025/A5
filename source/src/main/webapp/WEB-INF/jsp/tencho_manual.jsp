<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <title>エンプロ良イ&#128077｜マニュアル管理</title>
  <link rel="stylesheet" href="<c:url value='/css/style.css' />">
<link rel="stylesheet" href="<c:url value='/css/tencho_manual.css'/>">
  <%--<style>
    tencho_manual.cssに移動済み
  </style>--%>
</head>
<body>
  <header>
    <h1 id="logo">
      <a href="tencho_calendar.jsp"><img src="img/logo.png" width="300" alt="エンプロ良イ:+1:"></a>
    </h1>
    <ul id="nav">
      <li><a href="tencho_calendar.jsp">カレンダー</a></li>
      <li><a href="tencho_shift.jsp">シフト</a></li>
      <li><a href="tencho_event.jsp">イベント</a></li>
      <li><a href="tencho_manual.jsp">マニュアル</a></li>
      <details>
        <summary class="details-summary">その他</summary>
        <li><a href="tencho_user_edit.jsp">ユーザー管理</a></li>
        <li><a href="tencho_login.jsp">ログアウト</a></li>
      </details>
    </ul>
  </header>

  <main>
    <form method="post" enctype="multipart/form-data" onsubmit="return false;">
      <div id="ddAreaFileRef">
        ここにドラッグ＆ドロップ<br>
        またはクリックしてファイルを選択
      </div>
      <input type="file" id="fileInput" name="file" multiple>
      <div id="fileList"></div>

      <h3>重要度を設定：
	<form>
  	<p>重要度を色で選んでください：</p>
  	<div class="color-option">
    <label>
      <input type="radio" name="color" value="red">
      <span class="color-label color-red"></span>
    </label>
    <label>
      <input type="radio" name="color" value="blue">
      <span class="color-label color-blue"></span>
    </label>
    <label>
      <input type="radio" name="color" value="green">
      <span class="color-label color-green"></span>
    </label>
    <label>
      <input type="radio" name="color" value="orange">
      <span class="color-label color-orange"></span>
    </label>
    <label>
      <input type="radio" name="color" value="purple">
      <span class="color-label color-purple"></span>
    </label>
  	</div>
	</form>
	</h3>
      <h2 id="uploadTrigger">登録</h2>
    </form>

    <h3>登録済みマニュアル一覧</h3>
        <table border="1">
        <tr>
            <th>ID</th>
            <th>ファイル名</th>
            <th>重要度</th>
            <th>更新日</th>
        </tr>
        <c:forEach var="manual" items="${manualList}">
            <tr>
                <td>${manual.id}</td>
                <td>${manual.manual_file}</td>
                <td>${manual.importance}</td>
                <td>${manual.date_up}</td>
            </tr>
        </c:forEach>
    </table>
    
  </main>

  <script>
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

  <footer></footer>
</body>
</html>