import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;



public class Login extends JFrame implements ActionListener {
    private JLabel username;
    private JLabel password;
    private JTextField user;
    private JPasswordField pass;
    private JButton login;
    private JButton cancel;
    
    public Login() {
        super("Login"); 
        JPanel p = new JPanel();
        
        //labels
        username = new JLabel("Username");
        password = new JLabel("Password");
        
        //text fields
        user = new JTextField();
        pass = new JPasswordField();
        
        // Buttons
        login = new JButton("Login");
        cancel = new JButton("Cancel");
        
        //Label dimensions
        username.setBounds(20, 30, 80, 20);
        password.setBounds(20, 80, 80, 20);
        
        //text fields dimensions
        user.setBounds(100, 30, 150, 30);
        pass.setBounds(100, 80, 150, 30);
        
        // add border to text field
        user.setBorder(new LineBorder(Color.black));
        pass.setBorder(new LineBorder(Color.black));
        
        //Button dimensions
        login.setBounds(50, 160, 80, 30);
        cancel.setBounds(150, 160, 80, 30);
        
        //add border to button
        login.setBorder(new LineBorder(Color.black));
        cancel.setBorder(new LineBorder(Color.black));
        
        // Button function
        login.addActionListener(this);
        cancel.addActionListener(this);
        
        p.setLayout(null);
        p.add(username);
        p.add(password);
        p.add(user);
        p.add(pass);
        p.add(login);
        p.add(cancel);
        
        p.setBorder(new LineBorder(Color.black));
        
        getContentPane().add(p);
        
        setDefaultCloseOperation(3);
        setSize(300, 300);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent event) {
            if(event.getSource() == login) {
            String u = user.getText();
            String p = new String(pass.getPassword());
            if(u.equals("") && p.equals("")){
            Home home = new Home();
            home.clock();
            dispose();
            home.setVisible(true);
            }else if(u != "" && p != "") {
                JOptionPane.showMessageDialog(null, "Please check your username and password!");
            }
            } 
        if(event.getSource() == cancel) {
            System.exit(0);
        } 
    }
}
