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
    String name,pass,otp,tname;
    totp TOTP = new totp();
    String jdbcURL = "jdbc:postgresql://localhost:5432/managementDB";
    String username = "postgres";
    String password = "1763";
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;
    Map sharedState;



    @Override
    public void initialize(Subject subject, CallbackHandler callbackHandler, Map<String, ?> sharedState,
            Map<String, ?> options) {   
        this.subject = subject;
        this.callbackHandler = callbackHandler;
        this.name = Authenticate.name;
        this.pass = Authenticate.pass;
        this.tname = Authenticate.tname;
        this.sharedState = sharedState;
        try {
            conn = DriverManager.getConnection(jdbcURL, username, password);
            stmt = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    @Override
    public boolean login() throws LoginException {
        System.out.println(callbackHandler);

        boolean flag = false;
        Callback[] callbackArray = new Callback[1];
        callbackArray[0] = new NameCallback("username");

      

        try {
            callbackHandler.handle(callbackArray);
            otp = ((NameCallback) callbackArray[0]).getName();
            System.out.println("Inside Login module " + otp);
            System.out.println(TOTP.main());
            

            //check the otp with google authenticator
                    if(otp.equals(TOTP.main())){
                        daPrincipal  = new DaPrincipal(tname);
                        user  = new userPrincipal(name);
                        flag = true;
                    }
                    else{
                        System.out.println("wrong otp");
                    }
            



            } catch (Exception e) {
            System.out.println("Error in login");
            e.printStackTrace();
        }
        System.out.println("success in Dalogin module " + flag);
        System.out.println(sharedState);
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
