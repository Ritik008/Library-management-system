
import static com.sun.glass.ui.Cursor.setVisible;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;


public class Borrow extends JInternalFrame implements KeyListener, ActionListener {
    
    private Connection conn = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;
    
    private JPanel panel;
    
    private JLabel borrowLbl;
    private JLabel memberIdLbl;
    private JLabel memberNameLbl;
    private JLabel memberTypeLbl;
    private JLabel bookIdLbl;
    private JLabel bookNameLbl;
    private JLabel bookTypeLbl;
    private JLabel issueDateLbl;
    private JLabel returnDateLbl;
    private JTextField mark;
    private JTextField memberId;
    private JTextField memberName;
    private JTextField memberType;
    private JTextField bookId;
    private JTextField bookName;
    private JTextField bookType;
    private JDateChooser issueDate;
    private JDateChooser returnDate;
    private JButton clear;
    private JButton issue;
    private JButton exit;
    private JTable tb;
    
    public Borrow() {
    
    LocalDate today = LocalDate.now();
    LocalDate reday = today.plus(10, ChronoUnit.DAYS);    
        
    conn = DBConnection.connect();
    //panel object
    panel = new JPanel();
    
    //Labels object
    borrowLbl = new JLabel("Borrow");
    memberIdLbl = new JLabel("Member ID");
    memberNameLbl = new JLabel("Name");
    memberTypeLbl = new JLabel("MemberType");
    bookIdLbl = new JLabel("Book ID");
    bookNameLbl = new JLabel("Name");
    bookTypeLbl = new JLabel("Type");
    issueDateLbl = new JLabel("Issue Date");
    returnDateLbl = new JLabel("Return Date");
    
    
    //textfield objects
    mark = new JTextField();
    memberId = new JTextField();
    memberName = new JTextField();
    memberType = new JTextField();
    bookId = new JTextField();
    bookName = new JTextField();
    bookType = new JTextField();
    issueDate = new JDateChooser();
    returnDate = new JDateChooser();
   
     //add keyRealeased event to 
    memberId.addKeyListener(this);
    bookId.addKeyListener(this);
    
    //make mark editable false
    mark.setEditable(false);
    
    //button objects
    clear = new JButton("Clear");
    issue = new JButton("Issue");
    exit = new JButton("Exit");
    
    //add action listener to the button
    issue.addActionListener(this);
    clear.addActionListener(this);
    exit.addActionListener(this);
    
    //table object
    tb = new JTable();
    
    //label layout
    borrowLbl.setBounds(20, 20, 80, 20);
    mark.setBounds(800, 50, 150, 30);
    memberIdLbl.setBounds(20, 50, 80, 20);
    memberNameLbl.setBounds(20, 100, 80, 20);
    memberTypeLbl.setBounds(20, 150, 80, 20);
    bookIdLbl.setBounds(400, 50, 80, 20);
    bookNameLbl.setBounds(400, 100, 80, 20);
    bookTypeLbl.setBounds(400, 150, 80, 20);
    issueDateLbl.setBounds(30, 200, 80, 20);
    returnDateLbl.setBounds(350, 200, 80, 20);

    // add font to borrow label
    borrowLbl.setFont(new Font("Consolas", Font.PLAIN, 20));
    
    // add border to mark 
    mark.setBorder(new LineBorder(Color.black));
    
    //textfield layout
    memberId.setBounds(100, 50, 170, 30);
    memberName.setBounds(100, 100, 170, 30);
    memberType.setBounds(100, 150, 170, 30);
    bookId.setBounds(500, 50, 170, 30);
    bookName.setBounds(500, 100, 170, 30);
    bookType.setBounds(500, 150, 170, 30);
    issueDate.setBounds(100, 200, 170, 30);
    returnDate.setBounds(450, 200, 170, 30);
    
    // add border to textfields
    memberId.setBorder(new LineBorder(Color.black));
    memberName.setBorder(new LineBorder(Color.black));
    memberType.setBorder(new LineBorder(Color.black));
    bookId.setBorder(new LineBorder(Color.black));
    bookName.setBorder(new LineBorder(Color.black));
    bookType.setBorder(new LineBorder(Color.black));
    issueDate.setBorder(new LineBorder(Color.black));
    returnDate.setBorder(new LineBorder(Color.black));
    
    
    ((JTextField)issueDate.getDateEditor().getUiComponent()).getText().toString();
    ((JTextField)returnDate.getDateEditor().getUiComponent()).getText().toString();
    
    //button layout
    clear.setBounds(800, 100, 150, 30);
    issue.setBounds(800, 150, 150, 30);
    exit.setBounds(800, 200, 150, 30);
   
    // add border to button
    clear.setBorder(new LineBorder(Color.black));
    issue.setBorder(new LineBorder(Color.black));
    exit.setBorder(new LineBorder(Color.black));
    
    //table layout
    JScrollPane pane = new JScrollPane(tb);
    pane.setBounds(50, 250, 1000, 500);
     
   // set border to table
   pane.setBorder(new LineBorder(Color.black));
    
    panel.setLayout(null);
    
    panel.add(borrowLbl);
    panel.add(memberIdLbl);
    panel.add(memberNameLbl);
    panel.add(memberTypeLbl);
    panel.add(bookIdLbl);
    panel.add(bookNameLbl);
    panel.add(bookTypeLbl);
    panel.add(issueDateLbl);
    panel.add(returnDateLbl);
    
    panel.add(mark);
    panel.add(memberId);
    panel.add(memberName);
    panel.add(memberType);
    panel.add(bookId);
    panel.add(bookName);
    panel.add(bookType);
    panel.add(issueDate);
    panel.add(returnDate);
    
    panel.add(clear);
    panel.add(issue);
    panel.add(exit);
    
    panel.add(pane);
    
    panel.setBorder(new LineBorder(Color.black));
    
    getContentPane().add(panel);
    setVisible(true);
    setSize(1100, 500);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    tableload();
    autoId();
    }
      public void autoId() {
        try {
            String sql = "SELECT record_no FROM bookLend ORDER BY record_no DESC LIMIT 1";            
            pst = (PreparedStatement)conn.prepareStatement(sql);
            rs = pst.executeQuery();
            if(rs.next()) {
                String rnno = rs.getString("record_no");
                int co = rnno.length();
                String txt = rnno.substring(0, 2);
                String num = rnno.substring(2, co);
                int n = Integer.parseInt(num);
                n++;
                String snum = Integer.toString(n);
                String ftxt = txt + snum;
                mark.setText(ftxt);
            }else {
                mark.setText("RN1000");
            }
            
        }catch(Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
      
    public void keyReleased(KeyEvent e) {
        if(e.getSource() == memberId){
        try {
            String sql = "SELECT name, type FROM addmember WHERE mid = '"+memberId.getText()+"'";            
            pst = (PreparedStatement)conn.prepareStatement(sql);
            rs = pst.executeQuery();
            if(rs.next()) {
            memberName.setText(rs.getString("name"));
            memberType.setText(rs.getString("type"));
            }else {
                memberName.setText("");
                memberType.setText("");
            }
            
        }catch(Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        } 
    }else if(e.getSource() == bookId) {
      try {
            String sql = "SELECT name, book_type FROM addbook WHERE bid = '"+bookId.getText()+"'";            
            pst = (PreparedStatement)conn.prepareStatement(sql);
            rs = pst.executeQuery();
            if(rs.next()) {
            bookName.setText(rs.getString("name"));
            bookType.setText(rs.getString("book_type"));
            }else {
                bookName.setText("");
                bookType.setText("");
            }
            
        }catch(Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }    
    }else if(e.getSource() == exit) {
        dispose();
    }
  }

    @Override
    public void keyTyped(KeyEvent e) {
   if(e.getSource() == memberId){
        try {
            String sql = "SELECT name, type FROM addmember WHERE mid = '"+memberId.getText()+"'";            
            pst = (PreparedStatement)conn.prepareStatement(sql);
            rs = pst.executeQuery();
            if(rs.next()) {
            memberName.setText(rs.getString("name"));
            memberType.setText(rs.getString("type"));
            }else {
                memberName.setText("");
                memberType.setText("");
            }
            
        }catch(Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        } 
    }else if(e.getSource() == bookId) {
      try {
            String sql = "SELECT name, book_type FROM addbook WHERE bid = '"+bookId.getText()+"'";            
            pst = (PreparedStatement)conn.prepareStatement(sql);
            rs = pst.executeQuery();
            if(rs.next()) {
            bookName.setText(rs.getString("name"));
            bookType.setText(rs.getString("book_type"));
            }else {
                bookName.setText("");
                bookType.setText("");
            }
            
        }catch(Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }    
    }
 }

    @Override
    public void keyPressed(KeyEvent e) {
          if(e.getSource() == memberId){
        try {
            String sql = "SELECT name, type FROM addmember WHERE mid = '"+memberId.getText()+"'";            
            pst = (PreparedStatement)conn.prepareStatement(sql);
            rs = pst.executeQuery();
            if(rs.next()) {
            memberName.setText(rs.getString("name"));
            memberType.setText(rs.getString("type"));
            }else {
                memberName.setText("");
                memberType.setText("");
            }
            
        }catch(Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        } 
    }else if(e.getSource() == bookId) {
      try {
            String sql = "SELECT name, book_type FROM addbook WHERE bid = '"+bookId.getText()+"'";            
            pst = (PreparedStatement)conn.prepareStatement(sql);
            rs = pst.executeQuery();
            if(rs.next()) {
            bookName.setText(rs.getString("name"));
            bookType.setText(rs.getString("book_type"));
            }else {
                bookName.setText("");
                bookType.setText("");
            }
            
        }catch(Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }    
    } 
 }
    public void actionPerformed(ActionEvent event) {
        
        String rno = mark.getText();
        String mid = memberId.getText();
        String bid = bookId.getText();
        String idate = ((JTextField)issueDate.getDateEditor().getUiComponent()).getText().toString();
        String rdate = ((JTextField)returnDate.getDateEditor().getUiComponent()).getText().toString();
        
        
        if(event.getSource() == issue) {
             try{
            String sql = "INSERT INTO bookLend VALUES('"+rno+"', '"+mid+"','"+bid+"', '"+idate+"', '"+rdate+"', '0')";
            PreparedStatement pst = (PreparedStatement)conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Successfully inserted");
            } catch(Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
            update();
            clear();
            autoId();
            tableload();
        }else
        if(event.getSource() == clear) {
            clear();
        }else if(event.getSource() == exit) {
            dispose();
        }
    }
    private void tableload() {
        try {
            String sql = "SELECT *  FROM  booklend";
            pst = (PreparedStatement)conn.prepareStatement(sql);
            rs = pst.executeQuery();
            tb.setModel(net.proteanit.sql.DbUtils.resultSetToTableModel(rs));
            
        }catch(Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    private void clear() {
        bookId.setText("");
        memberId.setText("");
        bookName.setText("");
        bookType.setText("");
        memberName.setText("");
        memberType.setText("");
        
    }
    
    private void update() {
        try {
            String sql = "UPDATE addBook SET mark = '1' WHERE bid = '"+bookId.getText()+"'";
            pst = (PreparedStatement) conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Update success");
            tableload();
        }catch(Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
}
