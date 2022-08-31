<@ page import = "java.util.*,src.*">

<!Doctype html>
<html>
<head>
</head>
<body>
<% 
	List<Hospitals> theHospitals = (List<Hospitals>) request.getAttribute("hospitals");
%>
<%= theHospitals %>

</body>
</html>