<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>エンプロ良イ👍｜ユーザー管理画面</title>
<link rel="stylesheet" href="<c:url value='/css/style2.css'/>">
</head>

<body>

<header>
  <h1 id="logo">
    <a href="tencho_calendar.jsp">
      <img src="img/logo.png" width="300" height="auto" alt="エンプロ良イ👍">
    </a>
  </h1>
  <ul id="nav">
    <li><a href="/A5/CalenderServlet/">カレンダー</a></li>
    <li><a href="/A5/ShiftServlet/">シフト</a></li>
    <li><a href="/A5/EventServlet/">イベント</a></li>
    <li><a href="/A5/ManualServlet/">マニュアル</a></li>
    <li>
      <details>
        <summary class="details-summary">その他</summary>
        <ul>
          <li><a href="/A5/UserManageServlet/">ユーザー管理</a></li>
          <li><a href="/A5/LogoutServlet/">ログアウト</a></li>
        </ul>
      </details>
    </li>
  </ul>
</header>
 
 	<br><br><br><br><br>
  

<!-- タイトル -->
       <div class="top">
          <h2>エンプロ良イ👍</h2>
        </div>

        <br><br>
      <!-- ログイン画面に戻るボタン -->
      
      <div class="rogin_back">

        <h2><a href="/webapp/LoginServlet">ログイン画面に戻る</a></h2>

       </div>

       <br><br>

       <P>＊店長は赤文字です</P>

        <br>
    
        <form id="user_edit_form" method="POST" action="<c:url value='/UserManageServlet' />">>
            <!-- 表 -->
             <div class="table_up_del">

                    <table border="1">
                        <tbody>
                            <tr>
                                <th>ID</th>
                                <th>ユーザーネーム</th>
                                <th>パスワード</th>
                                
                            </tr>

                            <tr>
                                <td></td>
                                <!--  <td input type="text" name="name" value=${dd}></td>-->
                                <td></td>
                                
                                
                            </tr>

                            <tr>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td class="no-bottom-border"><input class ="update_button"type="submit" value="変更"><input class="delete_button"type="submit" value="削除"></td>
                                
                            </tr>

                            <tr>
                                <td></td>
                                <td></td>
                                <td></td>
                                
                            </tr>

                            <tr>
                                <td></td>
                                <td></td>
                                <td></td>
                            </tr>

                            <tr>
                                <td></td>
                                <td></td>
                                <td></td>
                            </tr>

                            <tr>
                                <td></td>
                                <td></td>
                                <td></td>
                            </tr>

                            <tr>
                                <td></td>
                                <td></td>
                                <td></td>
                            </tr>

                            <tr>
                                <td></td>
                                <td></td>
                                <td></td>
                            </tr>

                            <tr>
                                <td></td>
                                <td></td>
                                <td></td>
                            </tr>

                            <tr>
                                <td></td>
                                <td></td>
                                <td></td>
                            </tr>

                            <tr>
                                <td></td>
                                <td></td>
                                <td></td>
                            </tr>

                            <tr>
                                <td></td>
                                <td></td>
                                <td></td>
                            </tr>

                            <tr>
                                <td>${user.id}</td>
                                <td></td>
                                <td></td>
                            </tr>

                            <tr>
                                <td>${user.id}</td>
                                <td></td>
                                <td></td>
                            </tr>

                            

                        </tbody>
            
                    </table>

                </div>
       </form>
       
       				<br>
			            <br>
			            <br>
			            <br>
			            <br>
			            <br>
			            <br>
			            <br>
			            <br>
			            <br>
			            <br>
			            <br>
			            <br>
			            <br>
			            <br>
			            <br>
			            <br>
			            <br>
			            <br>
			            <br>
			           



   	<!-- フッター -->
    <footer>
    
     	<div class="gotop">
			 <a href="#top"><img src="img/gotop.png" alt="ページトップへ戻る" width=70px height=auto></a>
		</div>
		
		<br>
        <p>&copy; エンプロ良イ👍</p>
        

    </footer>
			
			

</body>
</html>