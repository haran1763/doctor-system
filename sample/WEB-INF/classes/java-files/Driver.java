

import javax.security.auth.login.LoginContext;

public class Driver{
    public boolean verify(String tname,String name,String pass) {
        try {
            LoginContext loginContext = null;
            loginContext.login();
            return true;    
        } catch (Exception e) {
            return false;
        }
    }
   
}