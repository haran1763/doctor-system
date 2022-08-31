import java.io.Serializable;
import java.security.Principal;

public class DaPrincipal implements Principal,Serializable{
    public final String name;
    public DaPrincipal(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
    
    public boolean equals(Object object){
        System.out.println(
            "[+]equal function checking name and principle name"
        );
        boolean flag = false;
        if(object instanceof DaPrincipal) {flag = name.equals(((DaPrincipal) object).getName());}
        return flag;
    }


}
