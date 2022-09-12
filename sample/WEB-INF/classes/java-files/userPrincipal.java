import java.io.Serializable;
import java.security.Principal;

public class userPrincipal implements Principal,Serializable{
    public String name;
    public userPrincipal(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
    
    public boolean equals(Object object){
        System.out.println(
            "[+]equal function checking name and principle name in user Principal"
        );
        boolean flag = false;
        if(object instanceof userPrincipal) {flag = name.equals(((userPrincipal) object).getName());}
        return flag;
    }


}
