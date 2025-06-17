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
    <h1 id="header" style="text-align: center;">エンプロ良イ👍</h1>
    <nav class="headerNav">
        <ul>
            <li><a href=""></a>カレンダー</li>
            <li><a href=""></a>シフト</li>
            <li><a href=""></a>イベント</li>
            <li><a href=""></a>マニュアル</li>
            <li><a href=""></a>その他▼</li>
        </ul>
    </nav>
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
            for (let i = 0; i < files.length; i++) {
                const file = files[i];
                const fileElement = document.createElement('div');
                fileElement.textContent = file.name;
                fileList.appendChild(fileElement);
            }
        }
</script>
    <h3>
    重要度を設定：
    </h3>

    <h2>
    登録
    </h2>
</form>
<h3>
    登録済みマニュアル一覧
</h3>



</main>
	<footer></footer>
</body>
</html>