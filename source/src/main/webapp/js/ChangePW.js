function checkPasswordMatch() {
  const pw1 = document.getElementById("newPw1").value;
  const pw2 = document.getElementById("newPw2").value;
  const msg = document.getElementById("pwMessage");

  if (pw1 !== pw2) {
    msg.textContent = "新しいパスワードが一致しません。";
    return;
  }
  
  // パスワードが一致していたらフォーム送信
  document.getElementById("user_pw_form").submit();
}

