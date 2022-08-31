import java.util.Map;

import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;

import java.sql.*;

public class DaLoginModule implements LoginModule {
    
    private CallbackHandler callbackHandler = null;
    public Subject subject = null;
    public DaPrincipal daPrincipal = null;
	public userPrincipal user = null;
    String name,pass,tname;
    Object[] generator= null;
    String jdbcURL = "jdbc:postgresql://localhost:5432/managementDB";
    String username = "postgres";
    String password = "1763";
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;
    @Override
    public void initialize(Subject subject, CallbackHandler callbackHandler, Map<String, ?> sharedState,
            Map<String, ?> options) {   
        this.subject = subject;
        this.callbackHandler = callbackHandler;
        name = Authenticate.name;
        pass = Authenticate.pass;
        tname = Authenticate.tname;
        try {
            conn = DriverManager.getConnection(jdbcURL, username, password);
            stmt = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean login() throws LoginException {

        boolean flag = false;
        Callback[] callbackArray = new Callback[1];
        callbackArray[0] = new NameCallback("username");
        // callbackArray[1] = new PasswordCallback("password",false);
        try {
            callbackHandler.handle(callbackArray);
            String username = ((NameCallback) callbackArray[0]).getName();
            System.out.println(username);
            System.out.println("Inside Login module " + name + " " + pass + " " + tname);

            // && rs.getString(2).equals(pass)
            System.out.println("The auth otp is " + Authenticate.otp);

            data(tname);
                while(rs.next()){
                    if(rs.getString(1).equals(name) && rs.getString(2).equals(pass) ){
                        if(username.equals(Authenticate.otp)){
                            daPrincipal  = new DaPrincipal(tname);
                            user  = new userPrincipal(name);
                            flag = true;
                            break;
                        }
                        else{
                            System.out.println("wrong otp");
                        }
                    }
                }
        } catch (Exception e) {
            System.out.println("Error in login");
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean commit() throws LoginException {
        boolean flag = false;
        System.out.println("inside commit method");
        if(subject !=null && !subject.getPrincipals().contains(daPrincipal)){
            subject.getPrincipals().add(daPrincipal);
            subject.getPrincipals().add(user);
            System.out.println(subject);    
            flag =true;
        }

        
        return flag;
    }

    @Override
    public boolean abort() throws LoginException {
        if(subject !=null && !subject.getPrincipals().contains(daPrincipal))
        subject.getPrincipals().remove(daPrincipal);
        subject=null;
        daPrincipal = null;
        return true;
    }

    @Override
    public boolean logout() throws LoginException {
        subject.getPrincipals().remove(daPrincipal);
        subject.getPrincipals().remove(user);
        subject = null;
        
        return true;
    }

        void data(String tname){
        try {
                String query = String.format("select email,password from %s;",tname);
                rs = stmt.executeQuery(query);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
