
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public class dbConnection {
    public Connection dbConnect(){
	String jdbcURL = "jdbc:postgresql://localhost:5432/managementDB";
        String user = "postgres";
        String password = "1763";
        
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(jdbcURL,user,password);
            return conn;
        } catch (SQLException e) {
            e.printStackTrace();
            return conn;
        }
    }

    void Display(ResultSet rs){
        try {
            ResultSetMetaData rsmd = rs.getMetaData();
            while(rs.next()){
                for(int i=1;i<=rsmd.getColumnCount();i++){
                    if(rsmd.getColumnType(i)==12){
                        System.out.println(rsmd.getColumnName(i)+" : " + rs.getString(i));
                    } 
                    else{
                        System.out.println(rsmd.getColumnName(i) + " : " + rs.getInt(i));
                    }
                }
                System.out.println();
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
