package delivery.lorry.registration.system;

import java.util.Arrays;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Login {
    private final char[] password;
    
    Login(String username, char pass[], JFrame f){
        this.password = new char[]{'a', 'd', 'm', 'i', 'n'};
        if(username.equals("admin") && Arrays.equals(pass, this.password)){ //verify username and password
            JOptionPane.showMessageDialog(null, "Login Successful");
            MainInterface mainInterface = new MainInterface(); //call main interface class
            f.dispose(); //close login page
        }
        else{
            JOptionPane.showMessageDialog(null, "Login Fail");
        }
    }
}