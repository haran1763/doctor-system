<%@ page import = "java.util.*,java.sql.*" %>

<!Doctype html>
<html>
<head>
</head>
<body>
<%	Statement stmt = null;
      ResultSet rs = null;
	String jdbcURL = "jdbc:postgresql://localhost:5432/managementDB";
      String user = "postgres";
      String pwd = "1763";
	Connection conn = null;

  try {
           conn = DriverManager.getConnection(jdbcURL,user,pwd);
        } catch (SQLException e) {
            e.printStackTrace();
        }
%>

<% 
	
	String str = String.format("select * from doctors;");
	stmt = conn.createStatement();
	rs = stmt.executeQuery(str);
	while(rs.next()){
%>
<h4>Name  		 : <%= rs.getString("name") %></h3>
<h5>Phone  		 :<%= rs.getString("phone") %></h3>
<h5>Specialisation :<%= rs.getString("specialisation") %></h3>
<h5>Doctor Id	 :<%= rs.getInt("doctorid") %></h3>
<hr />

<%
}
%>

<form action="/doctor/appointment" method="POST">
	<label>Enter the aadhar</label>
	<input type="text" name="aadhar" /><br />
	<label>Enter the Doctor name</label>
	<input type="text" name="dname" /><br />
	<label>Enter the Date</label>
	<input type="date" name="date" /><br />
	<label>Enter the Start Time(hh:mm:ss)</label>
	<input type="text" name="start" /><br />
	<label>Enter the End Time(hh:mm:ss)</label>
	<input type="text" name="end" /><br />
	<input type="submit" value="submit"/>
</form>

</body>
</html>