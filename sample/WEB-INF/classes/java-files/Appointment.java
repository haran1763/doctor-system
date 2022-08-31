import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.*;


public class Appointment extends HttpServlet{
    
    dbConnection db = new dbConnection();
    ResultSet rs = null;
    Statement stmt = null;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    
   public void service(HttpServletRequest req,HttpServletResponse res) {

        Date d1,d2;
        int did=0;
       boolean val;
       Connection conn = db.dbConnect();
       try {
            stmt = conn.createStatement();
            String appData[] = new String[4];

            appData[0] = req.getParameter("dname");
            appData[1] = req.getParameter("date");
            appData[2] = req.getParameter("start");
            appData[3] = req.getParameter("end");

            String aadhar = req.getParameter("aadhar");

            System.out.println(appData[0] + " " + appData[1] + " "  + appData[2] + " " + appData[3]);
            String query = String.format("select doctorid from doctors where name = '%s' ;",appData[0]);
            rs = stmt.executeQuery(query);
            if(rs.next()){
                did = rs.getInt(1);
            }
            String Starttime = String.format("%s %s",appData[1],appData[2]);
            String Endtime = String.format("%s %s",appData[1],appData[3]);
            d1 = sdf.parse(Starttime);
            d2 = sdf.parse(Endtime);

            val =  verifyAppointment(conn,d1.getTime(),d2.getTime(),did);

            if(val){
                query = String.format("select patientid from patient where aadhar = '%s';",aadhar);
                rs = stmt.executeQuery(query);
                if(rs.next()){
                    query = String.format("insert into appointment(pidno,d_idno,starttime,endtime) values('%d','%d','%d','%d');",rs.getInt(1),did,d1.getTime(),d2.getTime());                    
                    stmt.executeUpdate(query);
                    System.out.println("[+] appointment insertion sucessfull");
                    res.sendRedirect("success.html");
                }
            }
            else{
                res.sendRedirect("error.jsp");
                System.out.println("[+] appointment insertion unsuccessfull");
            }
            

        }
        catch(Exception e){
            e.printStackTrace();
        }

    }
            
//                   
//                 boolean val=false;
//                 Date d1,d2;
//                 do{

//                     String Time[] = operate.getData(date);
    
    
//                     
    
//                     
                    
//                     query = String.format("select starttime,endtime from appointment where d_idno = '%d';",did);
//                     rs = db.getdbdata(conn, query);
//                     //verify appointment
//                    val =  verifyAppointment(conn,d1.getTime(),d2.getTime(),did);
//                     if(val){
//                         query = String.format("insert into patient(name,phone,aadhar) values('%s','%s','%s');",userData[0],userData[1],userData[2]);
//                     db.sendquery(conn,query);
//                     query = String.format("select patientid from patient where Aadhar = '%s' ;",userData[2]);
//                     rs = db.getdbdata(conn, query);
//                     if(rs.next())
//                     {
//                         pid = rs.getInt("patientid");
//                       }
//                       query = String.format("insert into appointment(pidno,d_idno,starttime,endtime) values('%s','%s','%d','%d');",pid,did,d1.getTime(),d2.getTime());
//                 db.sendquery(conn, query);
//                 System.out.println("Appointment fixed successfully ");
//                         break;
//                     }
//                     else{
//                         System.out.println("Doctor is busy at that time plzz alter your valuable time...." + val);
//                     }
//                 }while(true);
                
//         } catch (Exception e) {
//             e.printStackTrace();
//         }
//     }

//     ///

//     void viewAppointment(Connection conn,String aadhar){
//         String query = String.format("select patientid from patient where aadhar = '%s';",aadhar);
//         rs = db.getdbdata(conn, query);
//         int pid=0,did=0;
//         String dname=null,hname=null;
//         try {
//             if(rs.next()){
//                 pid = rs.getInt(1);
//             }
//             query = String.format("select d_idno,starttime,endtime from appointment where pidno = '%d';", pid);
//             rs = db.getdbdata(conn, query);
//             if(rs.next()){
//                 did = rs.getInt(1);
//             }
//             Date date = new Date(rs.getLong(2));
//             query = String.format("select name,hospitalid from doctors where doctorid = '%d';",did);
//             rs = db.getdbdata(conn, query);
//             if(rs.next()){
//                 dname = rs.getString(1);
//             }
//             query = String.format("select name from hospitals where hospitalid = '%d';",rs.getInt(2));
//             rs = db.getdbdata(conn, query);
//             if(rs.next()){
//                 hname = rs.getString(1);
//             }
//          System.out.println("Appointment fixed with " + dname + " in hospital " + hname + " on " + sdf.format(date));
//         } catch (Exception e) {
//             e.printStackTrace();
//         }
//  }

    boolean verifyAppointment(Connection conn,long st,long et,int did)  throws SQLException{

    ResultSet rs;
    String query = String.format("select d_idno from appointment where (%d between starttime and endtime) or (%d between starttime and endtime);",st,et);
    rs = stmt.executeQuery(query);

    while(rs.next()){
        if(did == rs.getInt(1)){
            return false;
        }
    }
    return true;
 }

}



// ystem.out.println(rs.getLong(1) + "  " +rs.getLong(2));
              
