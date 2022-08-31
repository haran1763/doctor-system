<%@ page import = "java.util.*"%>

<!Doctype html>
<html>
<head>
</head>
<body>
<h1>Make your wish....</h1>

<form action="/doctor/book.jsp" method="POST">
<input type="submit" value="Book Appointment" />
</form>

<button onclick='execute()'>View Apppointment</button>


<form id='view' action="/doctor/view.jsp" method="POST">
</form>
<script>
	function execute(){
		tag = `
			Enter your aadhar
			<input type="text" name="aadhar" />
			<input type="submit" value="submit">
		`;
		document.getElementById('view').innerHTML=tag;
	}
</script>
</body>
</html>