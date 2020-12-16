import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.print.PrinterException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoUnit;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class BookReturn extends JInternalFrame implements KeyListener, ActionListener {
    
    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    private JPanel panel;
    private JLabel bookReturnLbl;
    private JLabel bookIdLbl;
    private JLabel memberIdLbl;
    private JLabel lateDaysLbl;
    private JLabel fineLbl;
    private JTextArea area;
    private JTextField bookId;
    private JTextField memberId;
    private JTextField lateDays;
    private JTextField fine;
    private JButton returnBtn;
    private JButton print;
    private JButton exit;
    
    public BookReturn() {
     
     conn = DBConnection.connect();   
        
        
    // create panel object
    panel = new JPanel();
    
    //create label object
    bookReturnLbl = new JLabel("Book Return");
    bookIdLbl = new JLabel("Book ID");
    memberIdLbl = new JLabel("Member ID");
    lateDaysLbl = new JLabel("Late Days");
    fineLbl = new JLabel("Fine");
    
    //creating textfield object
    bookId = new JTextField();
    memberId = new JTextField();
    lateDays = new JTextField();
    fine = new JTextField();
    
    //creating text area object
    area = new JTextArea();
    
    //creating button object
    returnBtn = new JButton("Return");
    print = new JButton("Print");
    exit = new JButton("Exit");
    
    //add key listener
    bookId.addKeyListener(this);
    
    //add actionListener to reutrnBtn
    returnBtn.addActionListener(this);
    print.addActionListener(this);
    exit.addActionListener(this);
    
    //label layout
    bookReturnLbl.setBounds(10, 10, 150, 20);
    bookIdLbl.setBounds(20, 50, 80, 20);
    memberIdLbl.setBounds(20, 100, 80, 20);
    lateDaysLbl.setBounds(20, 150, 80, 20);
    fineLbl.setBounds(20, 200, 80, 20);
    
    // add font to label
    bookReturnLbl.setFont(new Font("Consolas", Font.PLAIN, 20));
    
    //textfield layout
    bookId.setBounds(100, 50, 170, 30);
    memberId.setBounds(100, 100, 170, 30);
    lateDays.setBounds(100, 150, 170, 30);
    fine.setBounds(100, 200, 170, 30);
    
    //textarea layout
    area.setBounds(500, 50, 250, 250);
    
    //button layout
    returnBtn.setBounds(20, 350, 200, 50);
    print.setBounds(500, 350, 200, 50);
    exit.setBounds(850, 350, 200, 50);
    
    // add border to text field
    bookId.setBorder(new LineBorder(Color.black));
    memberId.setBorder(new LineBorder(Color.black));
    lateDays.setBorder(new LineBorder(Color.black));
    fine.setBorder(new LineBorder(Color.black));
    
    // add border to textarea
    area.setBorder(new LineBorder(Color.black));
    
    // add border to button
    returnBtn.setBorder(new LineBorder(Color.black));
    print.setBorder(new LineBorder(Color.black));
    exit.setBorder(new LineBorder(Color.black));
    
    panel.setLayout(null);
    panel.add(bookReturnLbl);
    panel.add(bookIdLbl);
    panel.add(memberIdLbl);
    panel.add(lateDaysLbl);
    panel.add(fineLbl);
    
    panel.add(bookId);
    panel.add(memberId);
    panel.add(lateDays);
    panel.add(fine);
    
    panel.add(area);
    
    panel.add(returnBtn);
    panel.add(print);
    panel.add(exit);
    
    panel.setBorder(new LineBorder(Color.black));
    
    getContentPane().add(panel);
    setVisible(true);
    setSize(1100, 500);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    
    }

    @Override
    public void keyTyped(KeyEvent e) {
    if(e.getSource() == bookId){
    try {
            String sql = "SELECT memberid, r_date FROM bookLend WHERE bid = '"+bookId.getText()+"'and mark = '0'";            
            pst = (PreparedStatement)conn.prepareStatement(sql);
            rs = pst.executeQuery();
            if(rs.next()) {
            memberId.setText(rs.getString("memberid"));
            DateTimeFormatter df = DateTimeFormatter.ofPattern("d-MMM-yyyy");
            String r_date = rs.getString("r_date");
            LocalDate today = LocalDate.now();
            LocalDate rday = LocalDate.parse(r_date, df);
            
            Long day_gap = ChronoUnit.DAYS.between(rday, today);
            lateDays.setText(day_gap.toString());
            
            long fine = 10 * day_gap;
            this.fine.setText(String.valueOf(fine));
            }
            
        }catch(Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
 }
    @Override
    public void keyPressed(KeyEvent e) {
    if(e.getSource() == bookId){
    try {
            String sql = "SELECT memberid, r_date FROM bookLend WHERE bid = '"+bookId.getText()+"'and mark = '0'";            
            pst = (PreparedStatement)conn.prepareStatement(sql);
            rs = pst.executeQuery();
            if(rs.next()) {
            memberId.setText(rs.getString("memberid"));
            DateTimeFormatter df = DateTimeFormatter.ofPattern("d-MMM-yyyy");
            String r_date = rs.getString("r_date");
            LocalDate today = LocalDate.now();
            LocalDate rday = LocalDate.parse(r_date,df);
            
            Long day_gap = ChronoUnit.DAYS.between(today, rday);
            lateDays.setText(day_gap.toString());
            
            long fine = 10 * day_gap;
            this.fine.setText(String.valueOf(fine));
            }
            
        }catch(Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    if(e.getSource() == bookId){
    try {
            String sql = "SELECT memberid, r_date FROM bookLend WHERE bid = '"+bookId.getText()+"'and mark = '0'";            
            pst = (PreparedStatement)conn.prepareStatement(sql);
            rs = pst.executeQuery();
            if(rs.next()) {
            memberId.setText(rs.getString("memberid"));
            DateTimeFormatter df = DateTimeFormatter.ofPattern("d-MMM-yyyy");
            String r_date = rs.getString("r_date");
            LocalDate today = LocalDate.now();
            LocalDate rday = LocalDate.parse(r_date, df);
            
            Long day_gap = ChronoUnit.DAYS.between(today, rday);
            if(day_gap > 0){
            lateDays.setText(day_gap.toString());
            
            long fine = 10 * day_gap;
            this.fine.setText(String.valueOf(fine));
            }else {
                lateDays.setText("0");
                fine.setText("0");
            }
           } 
        }catch(Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        }
    }
    
    public void actionPerformed(ActionEvent event) {
        if(event.getSource() == returnBtn){
        String bid = bookId.getText();
        String mid = memberId.getText();
        String ldate = lateDays.getText();
        String fine = this.fine.getText();
        try{
            String sql = "INSERT INTO bookreturn(bid, mid, late_days, fine) VALUES('"+bid+"', '"+mid+"', '"+ldate+"', '"+fine+"')";
            PreparedStatement pst = (PreparedStatement)conn.prepareStatement(sql);
            pst.execute();
            update();
            bill();
            returnMark();
            JOptionPane.showMessageDialog(null, "Successfully inserted");
            } catch(Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
    }
  else if(event.getSource() == print) {
            try {
                area.print();
            } catch (PrinterException ex) {
                Logger.getLogger(BookReturn.class.getName()).log(Level.SEVERE, null, ex);
            }
    }else if(event.getSource() == exit) {
            dispose();
    }
}
    private void bill() {
       area.setText("========================"+"\n"+"Library Name "+"\n"+"No of days "+lateDays.getText()+"\n"+"Amount\t "+fine.getText()+"\n"+"======================");
    }
    
    private void update() {
        try {
            String sql = "UPDATE booklend SET mark = '0' WHERE bid = '"+bookId.getText()+"'";
            pst = (PreparedStatement) conn.prepareStatement(sql);
            pst.execute();
            //JOptionPane.showMessageDialog(null, "Update success");
           
        }catch(Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    private void returnMark() {
        try {
            String sql = "UPDATE booklend SET mark = '1' WHERE bid = '"+bookId.getText()+"'";
            pst = (PreparedStatement) conn.prepareStatement(sql);
            pst.execute();
            //JOptionPane.showMessageDialog(null, "Update success");
           
        }catch(Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
}