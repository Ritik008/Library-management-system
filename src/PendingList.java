
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
import javax.swing.table.DefaultTableModel;


public class PendingList extends JInternalFrame implements ActionListener, KeyListener {

    private Connection conn = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;
    
    private JPanel panel;
    private JTable tb;
    private JLabel allMemberLbl;
    private JLabel memberNameLbl;
    private JTextField memberName;
    private JButton print;
    private JButton clear;
    private JButton exit;
    
    public PendingList() {
    
    conn = DBConnection.connect();
    
        
    panel = new JPanel();
        
    //table object
    tb = new JTable();
    DefaultTableModel dtm = new DefaultTableModel(0, 0);
    tb.setModel(dtm);
    
    
    // label object
    allMemberLbl = new JLabel("Pending List");
    memberNameLbl = new JLabel("Book ID");
    
    // set font to label
    allMemberLbl.setFont(new Font("Consolas", Font.PLAIN, 20));
    
    //textfield object
    memberName = new JTextField();
    
    //button object
    print = new JButton("Print");
    clear = new JButton("Clear");
    exit = new JButton("Exit");
    
    //add key listener to bookName
    memberName.addKeyListener(this);
    
    // add action listener to button
    clear.addActionListener(this);
    exit.addActionListener(this);
    
    //label layout
    allMemberLbl.setBounds(10, 10, 170, 20);
    memberNameLbl.setBounds(700, 20, 100, 20);
    
    //text field layout
    memberName.setBounds(800, 20, 170, 30);
    
    // table layout
    JScrollPane pane = new JScrollPane(tb);
    pane.setBounds(50, 60, 1000, 300);
    
    //button layout
    print.setBounds(400, 400, 150, 30);
    clear.setBounds(600, 400, 150, 30);
    exit.setBounds(800, 400, 150, 30);
    
    // add border to textfield
    memberName.setBorder(new LineBorder(Color.black));
    
    // add border to button
    print.setBorder(new LineBorder(Color.black));
    clear.setBorder(new LineBorder(Color.black));
    exit.setBorder(new LineBorder(Color.black));
    
    // add border to table
    pane.setBorder(new LineBorder(Color.black));
    
    panel.setLayout(null);
    panel.add(allMemberLbl);
    panel.add(memberNameLbl);
    panel.add(memberName);
    panel.add(pane);
    panel.add(clear);
    panel.add(exit);
    
    getContentPane().add(panel);
    setVisible(true);
    setSize(1100, 500);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    tableload();
    
    }  
    
    private void tableload() {
        try {
            String sql = "SELECT record_no, memberid, bid, issuedate, r_date, mark FROM booklend WHERE mark = '0'";
            pst = (PreparedStatement)conn.prepareStatement(sql);
            rs = pst.executeQuery();
            tb.setModel(net.proteanit.sql.DbUtils.resultSetToTableModel(rs));
        }catch(Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    public void actionPerformed(ActionEvent event) {
    if(event.getSource() == clear){
        memberName.setText("");
        tableload();
        }else 
        if(event.getSource() == exit) {
            dispose();
        }
    }
 public void keyReleased(KeyEvent e) {
        try {
            String sql = "SELECT record_no, memberid, bid, issuedate, r_date, mark FROM booklend WHERE mark = '0' AND memberid LIKE '%"+memberName.getText()+"%'";
            pst = (PreparedStatement)conn.prepareStatement(sql);
            rs = pst.executeQuery();
            tb.setModel(net.proteanit.sql.DbUtils.resultSetToTableModel(rs));            
        }catch(Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
  }

    @Override
    public void keyTyped(KeyEvent e) {
   try {
            String sql = "SELECT record_no, memberid, bid, issuedate, r_date, mark FROM booklend WHERE mark = '0' AND memberid LIKE '%"+memberName.getText()+"%'";
            pst = (PreparedStatement)conn.prepareStatement(sql);
            rs = pst.executeQuery();
            tb.setModel(net.proteanit.sql.DbUtils.resultSetToTableModel(rs));
            
        }catch(Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
 }

    @Override
    public void keyPressed(KeyEvent e) {
          try {
            String sql = "SELECT record_no, memberid, bid, issuedate, r_date, mark FROM booklend WHERE mark = '0' AND memberid LIKE '%"+memberName.getText()+"%'";
            pst = (PreparedStatement)conn.prepareStatement(sql);
            rs = pst.executeQuery();
            tb.setModel(net.proteanit.sql.DbUtils.resultSetToTableModel(rs));
            
        }catch(Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
            }
        }
}
