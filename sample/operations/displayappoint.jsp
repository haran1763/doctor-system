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
SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy hh:mm:ss");
Date date,date1;

String tname = request.getParameter("tname");
String name = request.getParameter("name");
String str = String.format("select doctorid from %s where name = %s;",tname,name);
rs = stmt.executeQuery(str);
if(rs.next()){
str = String.format("select starttime,endtime from appointment where name = %s;",tname,rs.next());
}
rs = stmt.executeQuery()
%>

<h2>Your Appointments are :</h2>
<%
int i = 1;
while(rs.next()){
	date =new Date(rs.getLong(1));
	date1 = new Date(rs.getLong(2));
%>
<h3><% i + sdf.format(date) + "\t"%>

	
}


%>
<form action="/doctor/index.html">
<input type="submit" value="Return Home" />
</form>

</body>
</html>