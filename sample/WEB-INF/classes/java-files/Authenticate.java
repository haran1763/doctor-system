import java.io.*;
import javax.security.auth.login.LoginContext;
import javax.servlet.*;
import javax.servlet.http.*;

public class Authenticate extends HttpServlet{

    public static LoginContext loginContext = null;
    static String otp;
    static String tname,name,pass;
    public void service(HttpServletRequest req,HttpServletResponse res) throws IOException,ServletException{
        System.out.println(req.getParameter("tname")+ " " +req.getParameter("name")+ " " + req.getParameter("pass"));
        name = req.getParameter("name");
        pass = req.getParameter("pass");
        tname = req.getParameter("tname");
        try {
            Sendmail sm = new Sendmail();
            otp = sm.main(name);
        } catch (Exception e) {
            //TODO: handle exception
            e.printStackTrace();
        }
            System.out.println("The otp in authenticate " + otp);
            res.sendRedirect("./Admin/OTP.html"); 
      
}
}