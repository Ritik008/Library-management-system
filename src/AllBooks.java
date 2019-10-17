
import static com.sun.glass.ui.Cursor.setVisible;
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

public class AllBooks extends JInternalFrame implements KeyListener, ActionListener {
    
    private Connection conn = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;
    
    private JPanel panel;
    private JTable tb;
    private JLabel allBookLbl;
    private JLabel bookNameLbl;
    private JTextField bookName;
    private JButton print;
    private JButton clear;
    private JButton exit;
    public AllBooks() {
    
    conn = DBConnection.connect();
    
        
    panel = new JPanel();
        
    //table object
    tb = new JTable();
    DefaultTableModel dtm = new DefaultTableModel(0, 0);
    tb.setModel(dtm);
    
    
    // label object
    allBookLbl = new JLabel("All Books");
    bookNameLbl = new JLabel("Book Name");
    
    //textfield object
    bookName = new JTextField();
    
    //button object
    print = new JButton("Print");
    clear = new JButton("Clear");
    exit = new JButton("Exit");
    
    //add key listener to bookName
    bookName.addKeyListener(this);
    
    //add action listener to clear
    clear.addActionListener(this);
    exit.addActionListener(this);
    
    //label layout
    allBookLbl.setBounds(10, 10, 120, 20);
    bookNameLbl.setBounds(700, 20, 100, 20);
    
    // add font to label
    allBookLbl.setFont(new Font("Consolas", Font.PLAIN, 20));
    
    //text field layout
    bookName.setBounds(800, 20, 170, 30);
    
    // table layoul
    JScrollPane p = new JScrollPane(tb);
    p.setBounds(50, 60, 1000, 300);

    //button layout
    print.setBounds(400, 400, 150, 30);
    clear.setBounds(600, 400, 150, 30);
    exit.setBounds(800, 400, 150, 30);
    
    // add border to text field
    bookName.setBorder(new LineBorder(Color.black));
    
    // add border to table 
    p.setBorder(new LineBorder(Color.black));
    
    // add border to button
    print.setBorder(new LineBorder(Color.black));
    clear.setBorder(new LineBorder(Color.black));
    exit.setBorder(new LineBorder(Color.black));
    
    panel.setLayout(null);
    panel.add(allBookLbl);
    panel.add(bookNameLbl);
    panel.add(bookName);
    panel.add(p);
    panel.add(print);
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
            String sql = "SELECT *  FROM  addbook";
            pst = (PreparedStatement)conn.prepareStatement(sql);
            rs = pst.executeQuery();
            tb.setModel(net.proteanit.sql.DbUtils.resultSetToTableModel(rs));
            
        }catch(Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void actionPerformed(ActionEvent event) {
    if(event.getSource() == clear){
        bookName.setText("");
        tableload();
    }else if(event.getSource() == exit) {
        dispose();
    }
}

    @Override
    public void keyTyped(KeyEvent e) {
    try {
            String sql = "SELECT *  FROM  addbook WHERE name LIKE '%"+bookName.getText()+"%'";
            pst = (PreparedStatement)conn.prepareStatement(sql);
            rs = pst.executeQuery();
            tb.setModel(net.proteanit.sql.DbUtils.resultSetToTableModel(rs));
            
        }catch(Exception ex) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
     try {
            String sql = "SELECT *  FROM  addbook WHERE name LIKE '%"+bookName.getText()+"%'";
            pst = (PreparedStatement)conn.prepareStatement(sql);
            rs = pst.executeQuery();
            tb.setModel(net.proteanit.sql.DbUtils.resultSetToTableModel(rs));
            
        }catch(Exception ex) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
}