import java.sql.*;
import javax.servlet.http.*;


public class Patients extends HttpServlet{
    private Statement stmt = null;

    
    public void doPost(HttpServletRequest req,HttpServletResponse res){
        
        dbConnection db = new dbConnection();
        // Operations operate = new Operations();
        // Appointment ap = new Appointment();
        Connection conn = db.dbConnect();

        String userData[] = new String[5];
        userData[0] = req.getParameter("name");
        userData[1] = req.getParameter("phone");
        userData[2] = req.getParameter("aadhar");
        userData[3] = req.getParameter("pass");
        userData[4] = req.getParameter("email");

        try {
            
            
            
            stmt = conn.createStatement();
            String str = String.format("insert into patient(name,phone,aadhar,password,email) values ('%s','%s','%s','%s','%s');",userData[0],userData[1],userData[2],userData[3],userData[4]);
            
            stmt.executeUpdate(str);
            
            req.getRequestDispatcher("appointment.jsp").include(req,res);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // void appointment(HttpServletRequest req){
        

    
        // do{  
        //     System.out.println("Enter your wish : \n\t1.Book your appointment\n\t2.Verify your appointment\n\t-1.Exit");
        //     c=sc.nextInt();
        //     switch(c){
        //         case 1 :
        //         System.out.println("Book your appoinments from available doctors");
        //         ap.bookAppointment(conn,userData);
        //         break;
        //         case 2 : 
        //         System.out.println("Enter your aadhar :");
        //         String aadhar = sc.next();
        //         ap.viewAppointment(conn, aadhar);
        //     }

        // }while(c!=-1);

    // }




}