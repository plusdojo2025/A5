<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>エンプロ良イ👍｜マニュアル管理</title>
</head>
<body>
    <style>
        header{
            margin: 100px;
        }
        
    </style>
	<header>
	<h1 id="logo">
      <a href="tencho_calendar.jsp"><img src="img/logo.png" width=300 height=auto alt="エンプロ良イ👍"></a>
    </h1>
    <ul id="nav">
      <li><a href="tencho_calelndar.jsp">カレンダー</a></li>
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
  <style>
        #header{
            color:#000000
        }
        .headerNav ul{
    margin: 30px 0 0 0;
    padding: 0 0 0 0;
    list-style-type: none;

    display: flex;
    gap: 40px;
    justify-content: center;

    border: 2px solid #000000;
            border-radius: 1px;
}
        #ddAreaFileRef {
            border: 2px solid #000000;
            border-radius: 10px;
            width: 80%;
            
            text-align: center;
            line-height: 100px;
            color: #000000;
            margin: 20px auto;
            font-family: sans-serif;
        }

        #ddAreaFileRef.hover {
            border-color: #ff0000;
            color: #0000ff;
        }
        h2{
            margin: 2% auto;
            text-align: center;
            border: 2px solid #000000;
            border-radius: 1px;
            width: 80%;
            
        }
        h3{
            margin: auto;
            text-align: center;
            border: 2px solid #000000;
            border-radius: 1px;
            width: 80%;
            
        }
         #fileList {
            margin-top: 20px;
        }
        input[type="file"] {
            display: none;
        }   /* 画面上から隠すCSS処理 */
    </style>

<form>
    <div id="ddAreaFileRef">ここにドラッグ＆ドロップ<br>
     (ファイルを左クリックし続けて引っ張ってきて枠内で離す)<br>
     ファイルを参照する（エクスプローラーが開き、そこから選択）</div>
    <input type="file" id="fileInput" multiple>
    <div id="fileList"></div>
        <!-- これらのDivでそのボタンを実現 -->
<script>
        const ddAreaFileRef = document.getElementById('ddAreaFileRef');
        const fileInput = document.getElementById('fileInput');
        const fileList = document.getElementById('fileList');

        ddAreaFileRef.addEventListener('dragover', (event) => {
            event.preventDefault();
            ddAreaFileRef.style.borderColor = 'blue';
        });

        ddAreaFileRef.addEventListener('dragleave', () => {
            ddAreaFileRef.style.borderColor = '#ccc';
        });

        ddAreaFileRef.addEventListener('drop', (event) => {
            event.preventDefault();
            ddAreaFileRef.style.borderColor = '#ccc';

            const files = event.dataTransfer.files;
            handleFiles(files);
        });

        ddAreaFileRef.addEventListener('click', () => {
            fileInput.click();
        });

        fileInput.addEventListener('change', () => {
            const files = fileInput.files;
            handleFiles(files);
        });

        function handleFiles(files) {
    fileList.innerHTML = '';  // 表示リセット（任意）
    for (let i = 0; i < files.length; i++) {
        const file = files[i];
        const fileElement = document.createElement('div');
        fileElement.textContent = file.name;
        fileList.appendChild(fileElement);
    }
    
    // ファイルをサーバーへ送信
    uploadFiles(files);
}

        // 以下gpt送る処理
        function uploadFiles(files) {
    const formData = new FormData();
    for (let i = 0; i < files.length; i++) {
        formData.append('file', files[i]);  // 'file' はサーバー側と一致させる
    }

    fetch('/ManualServlet', { 
        method: 'POST',
        body: formData
    })
    .then(response => response.text())
    .then(result => {
        alert('アップロード成功: ' + result);
    })
    .catch(error => {
        alert('アップロード失敗: ' + error);
    });
}
// クリックによるアップロードを実行
document.getElementById('uploadTrigger').addEventListener('click', () => {
    const files = fileInput.files;
    if (files.length === 0) {
        alert('ファイルを選択してください');
        return;
    }

    uploadFiles(files); // ← 既にある関数を使う
});


</script>
    <h3>
    重要度を設定：
    </h3>

    <h2 id="uploadTrigger" style="cursor: pointer;">登録</h2>

</form>
<h3>
    登録済みマニュアル一覧
</h3>



</main>
	<footer></footer>
</body>
</html>