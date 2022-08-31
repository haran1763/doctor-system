<!Doctype html>
<html>
<head>
</head>
<body>
<h2> Welcome to hospital creation portal.....</h2>
<form action = "/doctor/create"method= "POST">
<input type="hidden" name = "tname" value = "hospitals" />
<label>Enter the name : </label>
<input type="text" name="name"/><br/>

<label>Enter the phone: </label>
<input type="text" name="phone"/><br/>

<label>Enter the number of doctors working : </label>
<input type="text" name ="doctors"/><br/>

<input type = "submit" />
</form>
</body>
</html>