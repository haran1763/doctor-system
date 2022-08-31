<!Doctype html>
<html>
<head>
</head>
<body>
<h2> Welcome to doctor creation portal.....</h2>
<form action="/doctor/create" method="POST">
<input type="hidden" name = "tname" value = "hospitals" />
<label>Enter the name : </label>
<input type="text" id="name"/><br/>

<label>Enter the phone: </label>
<input type="text" id="phone"/><br/>

<label>Enter the specialisation : </label>
<input type="text" id ="spl"/><br/>

<label>Enter the hospital name : </label>
<input type="text" id = "hospital"/><br/>

<input type="submit" value="Submit" id="button">
</form>

</body>
</html>