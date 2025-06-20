<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>エンプロ良イ:+1:｜マニュアル管理</title>
  <style>
    header {
      margin: 100px;
    }
    #nav {
      margin: 30px 0 0 0;
      padding: 0;
      list-style: none;
      display: flex;
      gap: 40px;
      justify-content: center;
      border: 2px solid #000;
      border-radius: 4px;
    }
    #nav li a {
      text-decoration: none;
      color: #000;
      padding: 8px 12px;
    }
    #nav li a:hover {
      background-color: #ddd;
      border-radius: 4px;
    }
    #ddAreaFileRef {
      border: 2px solid #000;
      border-radius: 10px;
      width: 80%;
      text-align: center;
      line-height: 100px;
      color: #000;
      margin: 20px auto;
      font-family: sans-serif;
      transition: border-color 0.3s;
    }
    #ddAreaFileRef.hover {
      border-color: blue;
      color: blue;
    }
    h2 {
      margin: 2% auto;
      text-align: center;
      border: 2px solid #000;
      border-radius: 1px;
      width: 80%;
      cursor: pointer;
    }
    h3 {
      margin: auto;
      text-align: center;
      border: 2px solid #000;
      border-radius: 1px;
      width: 80%;
    }
    #fileList {
      margin-top: 20px;
      text-align: center;
    }
    input[type="file"] {
      display: none;
    }
      .color-option {
    display: flex;
    gap: 20px;
    margin: 20px 0;
  }

  .color-option input[type="radio"] {
    display: none;
  }

  .color-label {
    width: 30px;
    height: 30px;
    border-radius: 50%;
    display: inline-block;
    cursor: pointer;
    border: 3px solid transparent;
  }

  /* 色ごとの背景 */
  .color-red { background-color: red; }
  .color-blue { background-color: blue; }
  .color-green { background-color: green; }
  .color-orange { background-color: orange; }
  .color-purple { background-color: purple; }

  /* 選択されたときの枠線 */
  input[type="radio"]:checked + .color-label {
    border-color: white;
  }
  </style>
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