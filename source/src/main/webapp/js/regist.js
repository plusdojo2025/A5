document.getElementById('user_choice_form').onsubmit = function(event){
	 const name =document.getElementById('name').value;
     const password =document.getElementById('password').value;
  
  

  
  if( name === '' || password ==='' ){
        event.preventDefault();
        document.getElementById('output').textContent = '※必須項目を入力してください';
        return;
    }else {
        document.getElementById('output').textContent = ''; 
    }

    
    const result = confirm("こちらの内容で登録してよろしいですか？");
    if (!result) {
        event.preventDefault(); 
    }
    
    };
    
    
