import java.io.IOException;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.servlet.http.HttpServlet;

public class DaCallbackHandler extends HttpServlet implements CallbackHandler{

    static String tname,name,pass;

    @Override
    public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
        System.out.println("inside DacallBack Handler");
       
            NameCallback nameCallback = (NameCallback) callbacks[0];
            nameCallback.setName(name);
            PasswordCallback password = (PasswordCallback) callbacks[1];
            password.setPassword(pass.toCharArray());
            // NameCallback tnameCallback = (NameCallback) callbacks[2];
            // tnameCallback.setName(userData.getTname());
    }

   
}