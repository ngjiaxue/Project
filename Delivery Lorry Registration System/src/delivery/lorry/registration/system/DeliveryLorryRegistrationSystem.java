package delivery.lorry.registration.system;

import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class DeliveryLorryRegistrationSystem {

    public static void main(String[] args) {
        //declare elements to use
        JFrame f = new JFrame("Login Page");
        JLabel l = new JLabel("Username");
        JLabel l1 = new JLabel("Password");
        JTextField tf = new JTextField();
        JPasswordField pf = new JPasswordField();
        JButton b = new JButton("Login");
        JButton b1 = new JButton("Cancel");
        
        //set elements bounds
        l.setBounds(20, 10, 100, 30);
        l1.setBounds(20, 55, 100, 30);
        tf.setBounds(20, 35, 260, 25);
        pf.setBounds(20, 80, 260, 25);
        b.setBounds(20, 120, 110, 25);
        b1.setBounds(170, 120, 110, 25);
        
        //add function to button
        //login button
        b.addActionListener((ActionEvent e) -> {
            Login login = new Login(tf.getText(), pf.getPassword(), f);  //call Login class
        });
        //cancel button        
        b1.addActionListener((ActionEvent e) -> {
            f.dispose();
        });
        
        //add elements to fram
        f.add(l);
        f.add(l1);
        f.add(tf);
        f.add(pf);
        f.add(b);
        f.add(b1);
        f.setLayout(null);
        f.setSize(300, 200);
        f.setVisible(true);
        f.setLocationRelativeTo(null);
        f.setResizable(false);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
}