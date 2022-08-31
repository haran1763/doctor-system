<!Doctype html>
<html>
<head>
</head>
<body>

<h2>Login page</h2>

<form action="/sample/authenticate" method="POST">
	<label>Enter the email</label>
	<input type="text" name="name" /><br />
	<label>Enter the password</label>
	<input type="password" name="pass" /><br />
	<label >Select the Role</label>
	<select style="margin-left: 3vw;" name="tname" id="tname">
		<option value="admin">Admin</option>
		<option value="doctors">Doctor</option>
		<option value="patient">Patient</option>
	  </select><br />
	<input type="submit" value="get OTP"/>
</form>
</body>
</html>