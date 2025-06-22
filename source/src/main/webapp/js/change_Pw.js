document.getElementById('user_pw_form').onsubmit = function(event){
	 const curPw =document.getElementById('curPw').value;
     const newPw =document.getElementById('newPw').value;
     
  	
  

  
  if( curPw === '' || newPw ==='' ){
        event.preventDefault();
        document.getElementById('output').textContent = '※パスワードを入力してください';
        return;
    }else {
        document.getElementById('output').textContent = ''; 
    }

    
    const result = confirm("こちらの内容で変更してよろしいですか？");
    if (!result) {
        event.preventDefault(); 
    }
    
    };
    