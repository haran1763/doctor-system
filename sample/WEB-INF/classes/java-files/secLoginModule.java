import java.util.Map;

import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;


public class secLoginModule implements LoginModule {
    
    private CallbackHandler callbackHandler = null;
    public Subject subject = null;
    public DaPrincipal daPrincipal = null;
	public userPrincipal user = null;
    


    @Override
    public void initialize(Subject subject, CallbackHandler callbackHandler, Map<String, ?> sharedState,
            Map<String, ?> options) {   
        this.subject = subject;
        this.callbackHandler = callbackHandler;
    }

    @Override
    public boolean login() throws LoginException {

        Callback[] callbackArray = new Callback[2];
        callbackArray[0] = new NameCallback("username");
        callbackArray[1] = new PasswordCallback("password",false);
        try {
            callbackHandler.handle(callbackArray);
        }
        catch(Exception e){
            e.printStackTrace();
        }
         
            return false;
    }

    @Override
    public boolean commit() throws LoginException {
        return false;
    }

    @Override
    public boolean abort() throws LoginException {
        daPrincipal = null;
        return true;
    }

    @Override
    public boolean logout() throws LoginException {

        return true;
    }
}
