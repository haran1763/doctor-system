import java.io.*;
import java.sql.Connection;
import javax.servlet.*;
import javax.servlet.http.*;

public class Create extends HttpServlet{

    private dbConnection db = new dbConnection();
    private Operations op = new Operations();
    public void doGet(HttpServletRequest req , HttpServletResponse res ) throws IOException,ServletException{

    }
    public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException,ServletException{

        Connection conn = db.dbConnect();
        String tname = req.getParameter("tname");
        System.out.println(tname);
        String data[] = new String[4];
        System.out.println("Before if" + req.getParameter("name") +" "+req.getParameter("phone") +" " + req.getParameter("spl"));
        
        if(tname.equals("hospitals")){
            data[0]= req.getParameter("name");
            data[1] = req.getParameter("phone");
            data[2] = req.getParameter("doctors");
            System.out.println(" inside if "+ data[0] +" "+ data[1]+" "+ data[2]);
        }
        if(tname.equals("doctors")){
            data[0]= req.getParameter("name");
            data[1] = req.getParameter("phone");
            data[2] = req.getParameter("spl");
            data[3] = req.getParameter("hospital");
            System.out.println(" inside if "+ data[0] +" "+ data[1]+" "+ data[2]);
        }
        
        
        op.insertData(conn, tname, data);

        res.sendRedirect("success.html");


    }
}
