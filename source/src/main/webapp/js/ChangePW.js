window.addEventListener('DOMContentLoaded', function () {
    document.getElementById('user_pw_form').onsubmit = function (event) {
        const new1 = document.getElementById("newPw1").value;
        const new2 = document.getElementById("newPw2").value;
        const output = document.getElementById("output");
        output.textContent = ""; // 前のメッセージをクリア

        if (
            new1.length < 8 || new1.length > 50 ||
            new2.length < 8 || new2.length > 50
        ) {
            output.textContent = "パスワードは8文字以上50文字以内にしてください";
            output.style.color = "red";
            event.preventDefault(); // 送信をキャンセル
            return false;
        }

        return true; // 送信許可
    };
});