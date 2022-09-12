import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class logout extends HttpServlet{
    public void service(HttpServletRequest req,HttpServletResponse res){
       try {
            req.getSession().invalidate();
           System.out.println(req.getUserPrincipal().getName()); 
           res.sendRedirect("/sample/index.html");
       } catch (Exception e) {
        System.out.println("Error in Logging out");
        e.printStackTrace();
       }
    }
}
