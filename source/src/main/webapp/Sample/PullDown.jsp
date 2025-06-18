<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <title>選択肢の追加・削除</title>
  <style>
    #popup {
      display: none;
      position: fixed;
      top: 30%;
      left: 30%;
      background: #fff;
      border: 1px solid #ccc;
      padding: 1em;
      z-index: 10;
    }
    #overlay {
      display: none;
      position: fixed;
      top: 0; left: 0; right: 0; bottom: 0;
      background: rgba(0,0,0,0.4);
      z-index: 5;
    }
  </style>
</head>
<body>
  <select id="mySelect">
    <option value="a">りんご</option>
    <option value="b">みかん</option>
  </select>
  <button onclick="openPopup()">選択肢を編集</button>

  <!-- モーダル風ポップアップ -->
  <div id="overlay" onclick="closePopup()"></div>
  <div id="popup">
    <h3>選択肢を追加・削除</h3>
    <input type="text" id="newOption" placeholder="新しい選択肢名" />
    <button onclick="addOption()">追加</button>
    <br><br>
    <select id="deleteSelect"></select>
    <button onclick="removeOption()">削除</button>
    <br><br>
    <button onclick="closePopup()">閉じる</button>
  </div>

  <script>
    function openPopup() {
      // 選択肢削除用のセレクトを更新
      const mainSelect = document.getElementById("mySelect");
      const deleteSelect = document.getElementById("deleteSelect");
      deleteSelect.innerHTML = "";
      for (let opt of mainSelect.options) {
        let clone = document.createElement("option");
        clone.value = opt.value;
        clone.text = opt.text;
        deleteSelect.add(clone);
      }

      document.getElementById("overlay").style.display = "block";
      document.getElementById("popup").style.display = "block";
    }

    function closePopup() {
      document.getElementById("overlay").style.display = "none";
      document.getElementById("popup").style.display = "none";
    }

    function addOption() {
      const input = document.getElementById("newOption");
      const value = input.value.trim();
      if (value) {
        const select = document.getElementById("mySelect");
        const opt = document.createElement("option");
        opt.value = value;
        opt.text = value;
        select.add(opt);
        input.value = "";
        openPopup(); // 更新
      }
    }

    function removeOption() {
      const delSelect = document.getElementById("deleteSelect");
      const value = delSelect.value;
      const mainSelect = document.getElementById("mySelect");
      for (let i = 0; i < mainSelect.options.length; i++) {
        if (mainSelect.options[i].value === value) {
          mainSelect.remove(i);
          break;
        }
      }
      openPopup(); // 更新
    }
  </script>
</body>
</html>
