import java.sql.Statement;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.*;



public class Doctors extends HttpServlet{
    public void service(HttpServletRequest req,HttpServletResponse res) {
    System.out.println("[+] inside doctor get");
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy hh:mm:ss");
    Date date,date1;
    ResultSet rs = null;
    Statement stmt = null;

    try {
                dbConnection db = new dbConnection();
                Connection conn = db.dbConnect();
                String name = Authenticate.name;
                System.out.println(name);
                PrintWriter oWriter = res.getWriter();
                System.out.println("Inside Doctors module");
                stmt = conn.createStatement();

                oWriter.println("<html><body>");
                oWriter.println("<h2>The appointments you have are :</h2>");
                String str = String.format("select doctorid from doctors where email = '%s';",name);
                rs = stmt.executeQuery(str);
                if(rs.next()){
                str = String.format("select starttime,endtime from appointment where d_idno = %d;",rs.getInt(1));
                }
                System.out.println(str);
                rs = stmt.executeQuery(str);
                int i=1;
                while(rs.next()){
                    date = new Date(rs.getLong(1));
                    date1 = new Date(rs.getLong(2));
                    oWriter.println("<h3>"+i+". "+sdf.format(date)+"\t" + sdf.format(date1) + " \n</h3>");
                    i++;
                }
                oWriter.println("<a href = './index.html' >return home</a>");
                oWriter.println("</body></html>");
                

        } catch (Exception e) {
            e.printStackTrace();
        }   
    }
}
