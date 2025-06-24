document.getElementById('user_pw_form').onsubmit = function(event){
	 const name =document.getElementById('name').value;
     const password =document.getElementById('password').value;
      
  	
  if( name === '' || password ===''){
        event.preventDefault();
        document.getElementById('output').textContent = '※ユーザー名とパスワードを入力してください';
        return;
    }else {
        document.getElementById('output').textContent = ''; 
    }
    
    if (password.length < 8 || password.length > 50) {
        event.preventDefault();
        document.getElementById('output').textContent = '※パスワードは8文字以上50文字以内で入力してください';
        return;
    }






function checkPasswordMatch() {
  const pw1 = document.getElementById("newPw1").value;
  const pw2 = document.getElementById("newPw2").value;
  const msg = document.getElementById("pwMessage");

   if (pw1 !== pw2) {
    event.preventDefault();
    msg.textContent = "新しいパスワードが一致しません。";
    msg.style.color = "red";
  } else {
    msg.textContent = "";
  }
};
