import java.sql.Connection;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;


public class Delete extends HttpServlet{
    private dbConnection db = new dbConnection();
    private Operations op =new Operations();

    public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException,ServletException{

        Connection conn = db.dbConnect();


        String tname = req.getParameter("tname");

        String name = req.getParameter("name");
            
        op.Delete(conn, tname, name);
        res.sendRedirect("success.html");

    
    }    
}
