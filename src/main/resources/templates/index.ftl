<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <title>Index</title>
</head>
<body>
    <div class="container">
        <form action="/file" method="post" enctype="multipart/form-data" autocomplete="on">
			First name: <input type="text" name="fname" /><br />
			Last name: <input type="text" name="lname" /><br />
			E-mail: <input type="email" name="email" autocomplete="off" /><br />
			<#--
			File:<input type="file" name="file" multiple="true" webkitdirectory="true" directory="true" style="top: -0.5px; left: -40px; " />
			 -->
			Upload File:<input type="file" name="file" multiple="true" accept="image/png" style="top: -0.5px; left: -40px; " /><br>
			<input type="submit" />
		</form>
    </div>
    
    <a href="/file/2">文件下载1</a><br>
    <a href="/file/微信支付副本">文件下载2</a><br>
   	 
    
</body>
</html>