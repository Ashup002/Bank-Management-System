package bank.management.system;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;


public class Login extends JFrame implements ActionListener{
    
    JButton login, signup, clear;
    JTextField cardTextField;
    JPasswordField pinTextField; /*to hide the password*/
    
    Login() {
        /*function  */
        setTitle("AUTOMATED TELLER MACHINE");
         
        setLayout(null);
        
        /*IMAGE*/
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/logo.jpg")); /*add image*/
        Image i2 = i1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);      
        ImageIcon i3 = new ImageIcon(i2);
        JLabel label = new JLabel(i3);   /*JLabel use to set the image and we can write content*/
        label.setBounds(70, 10, 100, 100);
        
        add(label);
        
        JLabel text = new JLabel("Welcome To ATM");
        text.setFont(new Font("Osword", Font.BOLD, 38));
        text.setBounds(200, 40, 400, 40);
        add(text);
        
        JLabel cardno = new JLabel("Card No:");
        cardno.setFont(new Font("Ralway", Font.BOLD, 28));
        cardno.setBounds(120, 150, 150, 30);
        add(cardno);
        
        /*TEXT BOX FOR CARD NO*/
        cardTextField = new JTextField();
        cardTextField.setBounds(300, 150, 230, 30);
        cardTextField.setFont(new Font("Arial", Font.BOLD, 14));
        add(cardTextField);
        
        
        JLabel pin = new JLabel("PIN:");
        pin.setFont(new Font("Ralway", Font.BOLD, 28));
        pin.setBounds(120, 220, 250, 30);
        add(pin);
        
        /*TEXT BOX FOR PIN*/
        pinTextField = new JPasswordField();
        pinTextField.setBounds(300, 220, 230, 30);
        pinTextField.setFont(new Font("Arial", Font.BOLD, 14));
        add(pinTextField);
        
        /*SIGN IN BUTTON*/
        login = new JButton("SIGN IN");
        login.setBounds(300, 300, 100, 30);
        login.setBackground(Color.BLACK);
        login.setForeground(Color.WHITE);
        login.addActionListener(this);
        add(login);
        
        /*CLEAR BUTTON*/
        clear = new JButton("CLEAR");
        clear.setBounds(430, 300, 100, 30);
        clear.setBackground(Color.BLACK);
        clear.setForeground(Color.WHITE);
        clear.addActionListener(this);
        add(clear);
        
        /*SIGN UP BUTTON*/
        signup = new JButton("SIGN UP");
        signup.setBounds(300, 350, 230, 30);
        signup.setBackground(Color.BLACK);
        signup.setForeground(Color.WHITE);
        signup.addActionListener(this);
        add(signup);
        
        
        getContentPane().setBackground(Color.WHITE); /*to change background color*/
        
        setSize(800, 480); /*use to set lenght and breadth*/
        setVisible(true); /*use to visible the border line */
        setLocation(350, 200);
    }
    
    /*after click on button to perform*/  
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == clear){
            cardTextField.setText("");
            pinTextField.setText("");
        }else if (ae.getSource() == login) {
            
            Conn conn = new Conn();
            String cardnumber = cardTextField.getText();
            String pinnumber = pinTextField.getText();
            String query = "select * from login where cardnumber = '"+cardnumber+"' and pin = '"+pinnumber+"'";
            try {
                ResultSet rs = conn.s.executeQuery(query);
                if (rs.next()) {
                    setVisible(false);
                    new Transactions(pinnumber).setVisible(true);
                }else {
                    JOptionPane.showMessageDialog(null, "Incorrect Card Number or Pin");
                }
            }catch (Exception e) {
                System.out.println(e);
            }
            
        }else if (ae.getSource() == signup) {
            //to go signup page after click signup button //
            setVisible(false);
            new SignupOne().setVisible(true);
        }
    }
    /*main class*/
    public static void main(String args[]) {
        /*object*/
        new Login().setVisible(true);
         
    }
    
}

