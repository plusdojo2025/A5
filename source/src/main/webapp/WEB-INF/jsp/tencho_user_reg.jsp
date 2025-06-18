<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>エンプロ良イ👍｜ユーザー登録画面</title>
<link rel="stylesheet" href="<c:url value='/css/style1.css'/>">
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

              <label><input type="radio" name="employees" value="店長"/>店長</label> 

              <label><input type="radio" name="employees" value="店員"/>店員</label>

                <br><br>

              
                  <input type="text" name="name" placeholder="登録するユーザー名">
                

                <br><br>
              
              
                  <input type="text" name="password" placeholder="登録するパスワード">
               

                <br><br>


                <div class="regist_button">
                        <input type="submit" value="登録">
                </div>
      </div>
      
    </form>
    
    <script src="/webapp/js/regist.js"></script>
      
  



    

</body>

</html>