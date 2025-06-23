<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>ハンバーガーメニュー</title>
  <style>
    body {
      margin: 0;
      font-family: sans-serif;
    }
    .menu-toggle {
      display: none;
    }

    .menu-icon {
      display: block;
      width: 30px;
      height: 25px;
      position: absolute;
      top: 20px;
      right: 20px;
      cursor: pointer;
      z-index: 2;
    }

    .menu-icon span {
      background: #000;
      display: block;
      height: 4px;
      margin: 5px 0;
      border-radius: 2px;
      transition: 0.3s;
    }

    nav {
      background: #eee;
      position: absolute;
      top: 0;
      right: -200px;
      width: 200px;
      height: 100vh;
      padding-top: 60px;
      transition: 0.3s;
    }

    nav ul {
      list-style: none;
      padding: 0;
      margin: 0;
    }

    nav ul li {
      padding: 10px 20px;
    }

    nav ul li a {
      text-decoration: none;
      color: #333;
    }

    /* チェックが入ったときのスタイル */
    .menu-toggle:checked ~ nav {
      right: 0;
    }

    .menu-toggle:checked ~ .menu-icon span:nth-child(1) {
      transform: rotate(45deg) translateY(9px);
    }

    .menu-toggle:checked ~ .menu-icon span:nth-child(2) {
      opacity: 0;
    }

    .menu-toggle:checked ~ .menu-icon span:nth-child(3) {
      transform: rotate(-45deg) translateY(-9px);
    }
  </style>
</head>
<body>

  <!-- チェックボックスで切り替える -->
  <input type="checkbox" id="menu-toggle" class="menu-toggle" />
  <label for="menu-toggle" class="menu-icon">
    <span></span>
    <span></span>
    <span></span>
  </label>

  <!-- ナビゲーションメニュー -->
  <nav>
    <ul>
      <li><a href="#">ホーム</a></li>
      <li><a href="#">シフト</a></li>
      <li><a href="#">イベント</a></li>
      <li><a href="#">マニュアル</a></li>
    </ul>
  </nav>

</body>
</html>