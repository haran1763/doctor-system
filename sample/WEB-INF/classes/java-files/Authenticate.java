import java.io.*;
import java.sql.*;

import javax.servlet.*;
import javax.servlet.http.*;

public class Authenticate extends HttpServlet{

    String jdbcURL = "jdbc:postgresql://localhost:5432/managementDB";
    String username = "postgres";
    String password = "1763";
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;
    public Authenticate(){
        try {
            conn = DriverManager.getConnection(jdbcURL, username, password);
            stmt = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String otp;
    static String tname,name,pass;
    public void service(HttpServletRequest req,HttpServletResponse res) throws IOException,ServletException{
        System.out.println(req.getParameter("tname")+ " " +req.getParameter("name")+ " " + req.getParameter("pass"));
        name = req.getParameter("name");
        pass = req.getParameter("pass");
        tname = req.getParameter("tname");
        try {
            data(tname);
            while(rs.next()){
                if(rs.getString(1).equals(name) && rs.getString(2).equals(pass) ){
                    res.sendRedirect("./Admin/OTP.jsp");
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
        }            
}

void data(String tname){
    try {
            String query = String.format("select email,password from %s;",tname);
            rs = stmt.executeQuery(query);
    } catch (SQLException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
}
}