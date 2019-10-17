
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



public class LendHistory extends JInternalFrame implements KeyListener, ActionListener {

    private Connection conn = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;
    
    private JPanel panel;
    private JTable tb;
    private JLabel allMemberLbl;
    private JLabel memberNameLbl;
    private JLabel fromLbl;
    private JLabel toLbl;
    private JTextField memberName;
    private JButton print;
    private JButton clear;
    private JButton filter;
    private JButton exit;
    private JDateChooser from;
    private JDateChooser to;
    
    
    public LendHistory() {
    
    conn = DBConnection.connect();
    
        
    panel = new JPanel();
        
    //table object
    tb = new JTable();
    DefaultTableModel dtm = new DefaultTableModel(0, 0);
    tb.setModel(dtm);
    
    
    // label object
    allMemberLbl = new JLabel("Lend History");
    memberNameLbl = new JLabel("Member ID");
    fromLbl = new JLabel("From");
    toLbl = new JLabel("To");
    
    // add font to label 
    allMemberLbl.setFont(new Font("Consolas", Font.PLAIN, 20));
    
    
    //textfield object
    memberName = new JTextField();
    
    //button object
    print = new JButton("Print");
    clear = new JButton("Clear");
    filter = new JButton("Filter");
    exit = new JButton("Exit");
    
    //date chooser combo object
    from = new JDateChooser();
    to = new JDateChooser();
    
    //add key listener to bookName
    memberName.addKeyListener(this);
    
    // add action listener to clear button
    clear.addActionListener(this);
    filter.addActionListener(this);
    exit.addActionListener(this);
    
    //label layout
    allMemberLbl.setBounds(10, 10, 170, 20);
    memberNameLbl.setBounds(700, 20, 100, 20);
    fromLbl.setBounds(10, 400, 80, 20);
    toLbl.setBounds(200, 400, 80, 20);
    
    //date chooser combo layout
    from.setBounds(50, 400, 100, 20);
    to.setBounds(220, 400, 100, 20);
    
    //text field layout
    memberName.setBounds(800, 20, 170, 30);
    
    // table layout
    JScrollPane pane = new JScrollPane(tb);
    pane.setBounds(50, 60, 1000, 300);
    
    //button layout
    print.setBounds(750, 400, 120, 30);
    clear.setBounds(550, 400, 120, 30);
    filter.setBounds(350, 400, 100, 30);
    exit.setBounds(950, 400, 120, 30);
    
    // add borders to textfield
    memberName.setBorder(new LineBorder(Color.black));
    
    // add border to table
    pane.setBorder(new LineBorder(Color.black));
    
    // add border to datefield
    from.setBorder(new LineBorder(Color.black));
    to.setBorder(new LineBorder(Color.black));
    
    // add border to button
    print.setBorder(new LineBorder(Color.black));
    clear.setBorder(new LineBorder(Color.black));
    filter.setBorder(new LineBorder(Color.black));
    exit.setBorder(new LineBorder(Color.black));
    
    panel.setLayout(null);
    panel.add(allMemberLbl);
    panel.add(memberNameLbl);
    panel.add(fromLbl);
    panel.add(toLbl);
    panel.add(memberName);
    panel.add(pane);
    panel.add(print);
    panel.add(clear);
    panel.add(exit);
    panel.add(filter);
    panel.add(from);
    panel.add(to);
    
    getContentPane().add(panel);
    setVisible(true);
    setSize(1100, 500);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    tableload();
    
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
    private void lendFilter() {
        String FDate = ((JTextField)from.getDateEditor().getUiComponent()).getText().toString();
        String TDate = ((JTextField)to.getDateEditor().getUiComponent()).getText().toString();
        
        if(FDate.length() > 0 && TDate.isEmpty()) {
            try {
                String reportSql = "SELECT 'record_no', 'mid', 'bid', 'i_date', 'r_date' FROM  booklend WHERE i_date= '"+FDate+"' AND  mid LIKE '%"+memberName.getText()+"%'";
                pst = (PreparedStatement) conn.prepareStatement(reportSql);
                rs = pst.executeQuery();
                tb.setModel(net.proteanit.sql.DbUtils.resultSetToTableModel(rs));
                
            }catch(Exception e) {
                JOptionPane.showMessageDialog(null, "first date");
            }
        }else if(FDate.length() > 0 && TDate.length() > 0) {
            try {
                String reportSql = "SELECT record_no, mid, bid, i_date, r_date FROM booklend WHERE  mid LIKE '%"+memberName.getText()+"%' AND i_date between '"+FDate+"' and '"+TDate+"'";
                pst = (PreparedStatement) conn.prepareStatement(reportSql);
                rs = pst.executeQuery();
                tb.setModel(net.proteanit.sql.DbUtils.resultSetToTableModel(rs));
            }catch(Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }else {
            JOptionPane.showMessageDialog(rootPane, "Select date to filter data");
        }
    }
 public void keyReleased(KeyEvent e) {
        try {
            String sql = "SELECT *  FROM  booklend where mid LIKE '%"+memberName.getText()+"%'";
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
            String sql = "SELECT *  FROM  addmember where name LIKE '%"+memberName.getText()+"%'";
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
            String sql = "SELECT *  FROM  addbook";
            pst = (PreparedStatement)conn.prepareStatement(sql);
            rs = pst.executeQuery();
            tb.setModel(net.proteanit.sql.DbUtils.resultSetToTableModel(rs));
            
        }catch(Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
            }
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == clear){
            memberName.setText("");
            tableload();
            ((JTextField)from.getDateEditor().getUiComponent()).setText("");
            ((JTextField)to.getDateEditor().getUiComponent()).setText("");
            }else if(e.getSource() == filter) {
               lendFilter();
            }else if(e.getSource() == exit) {
                dispose();
            }
        }
}
