<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>エンプロ良イ👍｜ユーザー登録画面</title>
<link rel="stylesheet" href="/webapp/css/style1.css">
</head>

<body>
<!-- タイトル -->
       <div class="top">
          <h2>エンプロ良イ👍</h2>
        </div>

        <br><br>
   
      <!-- ログイン画面に戻るボタン -->
      
      <div class="rogin_back">

        <h2>ログイン画面に戻る</h2>

       </div>

       <br><br>

<form id="user_choice_form" method="POST" action="/webapp/RegistServlet">

       <!-- 選択画面部分 -->

       <div class="user_choice">

              <p>追加するユーザーの種類を選択してください</p>

              <label><input type="radio" name="workers" />店長</label> 

              <label><input type="radio" name="workers" />店員</label>

                <br><br>

              
                  <input type="text" placeholder="登録するユーザー名">
                

                <br><br>
              
              
                  <input type="text" placeholder="登録するパスワード">
               

                <br><br>

                <div class="regist_pw_button">
                      
                  <button>登録用パスワード<br>（店長のメールで送信済み）</button>

                </div>

              <br><br>

              <p>登録用パスワードと登録ボタンのみで一覧を表示する</p>


              <br><br>

                <div class="regist_button">
                        <input type="submit" value="登録">
                </div>
      </div>
      
    </form>
      
  



    

</body>

</html>