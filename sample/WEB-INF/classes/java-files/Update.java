import java.sql.Connection;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;


public class Update extends HttpServlet{
    private dbConnection db = new dbConnection();
    private Operations op =new Operations();

    public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException,ServletException{

        Connection conn = db.dbConnect();


        String tname = req.getParameter("tname");
        String data[] = new String[3];

            data[0] = req.getParameter("name");
            data[1] =req.getParameter("field");
            data[2] =req.getParameter("val");

            op.update(conn, tname, data);
        res.sendRedirect("success.html");

    
    }
}
