<%@page import = "java.util.*,java.sql.*"%>

<!Doctype html>
<html>
<head>
<style>

*{
	margin:0;

}
	
</style>
</head>
<body>

<%
String jdbcURL = "jdbc:postgresql://localhost:5432/managementDB";
        String user = "postgres";
        String password = "1763";
%>

<h2>
	Medicare System....
</h2>

<% 
Connection conn = DriverManager.getConnection(jdbcURL,user,password);
Statement stmt = conn.createStatement();
ResultSet rs;
String tname = request.getParameter("tname");
String str = String.format("select * from %s;",tname);
rs = stmt.executeQuery(str);
System.out.println(rs);
if(tname.equals("hospitals")){
while(rs.next()){
%>

<h4>Name  		: <%= rs.getString("name") %></h3>
<h5>Phone 		: <%= rs.getString("phone") %></h3>
<h5>No of doctors :<%= rs.getString("doctors") %></h3>
<h5>Hospital Id	:<%= rs.getInt("hospitalid") %></h3>
<hr />
<%
}
}
else if(tname.equals("doctors")){
while(rs.next()){
%>
<h4>Name  		 : <%= rs.getString("name") %></h3>
<h5>Phone  		 :<%= rs.getString("phone") %></h3>
<h5>Specialisation :<%= rs.getString("specialisation") %></h3>
<h5>Doctor Id	 :<%= rs.getInt("doctorid") %></h3>
<hr />
<%
}
}
%>

<form action="/doctor/index.html">
<input type="submit" value="Return Home" />
</form>

</body>
</html>