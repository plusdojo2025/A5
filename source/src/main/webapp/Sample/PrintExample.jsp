<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>印刷テスト</title>
</head>
<body>
<h1>全体のタイトル</h1>
	
	<div id="printArea">
		<h2>印刷対象部分</h2>
		<p>この部分だけを印刷したい</p>
		<p>この部分だけを印刷したい</p>
		<p>この部分だけを印刷したい</p>
		<p>この部分だけを印刷したい</p>
		<p>この部分だけを印刷したい</p>
		<p>この部分だけを印刷したい</p>
		<p>この部分だけを印刷したい</p>
	</div>
	
	<div>
		<p>この部分は印刷されません</p>
	</div>
	
	<button onclick="printSection('printArea')">この部分だけ印刷</button>
</body>
<script>
function printSection(sectionId){
	var printContents = document.getElementById(sectionId).innerHTML;
	var originalContents = document.body.innerHTML;
	
	document.body.innerHTML = printContents;
	window.print();
	document.body.innerHTML = originalContents;
}
</script>
</html>
