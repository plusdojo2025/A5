document.getElementById('user_pw_form').onsubmit = function(event){
	 
    if (password.length < 8 || password.length > 50) {
        event.preventDefault();
        document.getElementById('output').textContent = '※パスワードは8文字以上50文字以内で入力してください';
        return;
    }


};




