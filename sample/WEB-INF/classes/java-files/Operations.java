import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

public class Operations {
	Statement stmt = null;
    void insertData(Connection conn,String tname,String data[]) {
		String query=null;
		System.out.println(tname);
	try{
		stmt = conn.createStatement();
		if(tname.equals("hospitals")){
			query = String.format("insert into %s values('%s','%s','%s');",tname,data[0],data[1],data[2]);
		}
		else{
			String queryhid = String.format("select hospitalid from hospitals where name = '%s';",data[3]);
			ResultSet rs = stmt.executeQuery(queryhid);
			if(rs.next()){
				query = String.format("insert into %s values('%s','%s','%s','%d');",tname,data[0],data[1],data[2],rs.getInt(1));
			}
		}
		stmt.executeUpdate(query);

	
	System.out.println(tname + " Insertion successful");
	}
	catch(Exception e){
		e.printStackTrace();
	}
	System.out.println("-----------------------------------------------------------------------------------------------");        
    }


	 ResultSet Display(Connection conn,String tname ){
		String querySelect =String.format("select * from %s;",tname );
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs =  stmt.executeQuery(querySelect);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
		}


		void Delete(Connection conn,String tname,String name){
			try {
				stmt = conn.createStatement();
					String str = String.format("delete from %s where name = '%s';",tname,name);
					stmt.executeUpdate(str);
					System.out.println("Deletion successfull");
				} catch (Exception e) {
					e.printStackTrace();
				}

		}


		void update(Connection conn,String tname,String update[]){
			try {
				stmt = conn.createStatement();
	
				String query = String.format("update %s set %s='%s' where name='%s';",tname,update[1],update[2],update[0]);
				// update hospitals set doctors=2 where name = 'mangalyan';
				stmt.executeUpdate(query);
				System.out.println("updation complete");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		
		String[] getData(String[] arr){
			String[] Arr = new String[arr.length];
			try {
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				for(int i=0;i<arr.length;i++){
					System.out.println("Enter the " +arr[i]+" : ");
					Arr[i] = br.readLine();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
				
			return Arr;
		}
}

