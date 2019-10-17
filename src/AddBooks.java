
import static com.sun.glass.ui.Cursor.setVisible;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;



public class AddBooks extends JInternalFrame implements ActionListener {
   
    private Connection conn = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;
    
    private JPanel panel;
    
    private JLabel bookLbl;
    private JLabel bookIdLbl;
    private JLabel nameLbl;
    private JLabel bcodeLbl;
    private JLabel dateLbl;
    private JLabel categoryLbl;
    private JLabel bookTypeLbl;
    private JLabel publisherLbl;
    private JLabel priceLbl;
    
    private JTextField bookId;
    private JTextField name;
    private JTextField bcode;
    private JDateChooser date;
    private JComboBox category;
    private JComboBox bookType;
    private JTextArea publisher;
    private JTextField price;
    
    private JButton add;
    private JButton update;
    private JButton delete;
    private JButton exit;
    
    private JTable tb;
    
    public AddBooks() {
    conn = DBConnection.connect();   
        
    //panel object
    panel = new JPanel();
    
    //labels objects
    bookLbl = new JLabel("Book");
    bookIdLbl = new JLabel("Book Id");
    nameLbl = new JLabel("Name");
    bcodeLbl = new JLabel("B Code");
    dateLbl = new JLabel("Date");
    categoryLbl = new JLabel("Category");
    bookTypeLbl = new JLabel("Book Type");
    publisherLbl = new JLabel("Publisher");
    priceLbl = new JLabel("Price");
    
    // set font to book label
    bookLbl.setFont(new Font("Consolas", Font.PLAIN, 20));
        
    // Array 
    String cat[] = {"Select Category", "Language", "Technology", "History", "Other"};
    String type[] = {"Select Type", "Borrow", "Read Only"};

    //TextFields objects
    bookId = new JTextField();
    name = new JTextField();
    bcode = new JTextField();
    date = new JDateChooser();
    category = new JComboBox(cat);
    bookType = new JComboBox(type);
    publisher = new JTextArea();
    JScrollPane p = new JScrollPane(publisher);
    price = new JTextField();
    
    //
    bookId.setEditable(false);
    
    //button objects
    add = new JButton("Add");
    update = new JButton("Update");
    delete = new JButton("Delete");
    exit = new JButton("Exit");
    
    //add action to button
    add.addActionListener(this);
    update.addActionListener(this);
    delete.addActionListener(this);
    exit.addActionListener(this);
    
    // table object
    tb = new JTable();
    DefaultTableModel dtm = new DefaultTableModel(0,0);
    tb.setModel(dtm);
   
    //add mouse event to table
    tb.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            DefaultTableModel tmodel = (DefaultTableModel)tb.getModel();
            int selectrowindex = tb.getSelectedRow();
            
            bookId.setText(tmodel.getValueAt(selectrowindex, 0).toString());
            name.setText(tmodel.getValueAt(selectrowindex, 1).toString());
            bcode.setText(tmodel.getValueAt(selectrowindex, 2).toString());
            ((JTextField)date.getDateEditor().getUiComponent()).setText(tmodel.getValueAt(selectrowindex, 3).toString());
            category.setSelectedItem(tmodel.getValueAt(selectrowindex, 4).toString());
            bookType.setSelectedItem(tmodel.getValueAt(selectrowindex, 5).toString());
            publisher.setText(tmodel.getValueAt(selectrowindex, 6).toString());
            price.setText(tmodel.getValueAt(selectrowindex, 7).toString()); 
        }
    });
    
    // label layout
    bookLbl.setBounds(20,20, 80, 20);
    bookIdLbl.setBounds(20, 50, 80, 20);
    nameLbl.setBounds(20, 100, 80, 20);
    bcodeLbl.setBounds(20, 150, 80, 20);
    dateLbl.setBounds(20, 200, 80, 20);
    categoryLbl.setBounds(20, 250, 80, 20);
    bookTypeLbl.setBounds(400, 50, 80, 20);
    publisherLbl.setBounds(400, 100, 80, 20);
    priceLbl.setBounds(400, 250, 80, 20);
    
    //textfield layout
    bookId.setBounds(100, 50, 170, 30);
    name.setBounds(100, 100, 170, 30);
    bcode.setBounds(100, 150, 170, 30);
    date.setBounds(100, 200, 170, 30);
    category.setBounds(100, 250, 170, 30);
    bookType.setBounds(500, 50, 170, 30);
    p.setBounds(500, 100, 250, 100);
    price.setBounds(500, 250, 170, 30);
    
    //table layout
    JScrollPane pane = new JScrollPane(tb);
    pane.setBounds(50, 300, 1000, 500);
    
     //button layout
    add.setBounds(800, 50, 150, 30);
    update.setBounds(800, 100, 150, 30);
    delete.setBounds(800, 150, 150, 30);
    exit.setBounds(800, 200, 150, 30);
    
    // add border to textfield
    bookId.setBorder(new LineBorder(Color.black));
    name.setBorder(new LineBorder(Color.black));
    bcode.setBorder(new LineBorder(Color.black));
    date.setBorder(new LineBorder(Color.black));
    category.setBorder(new LineBorder(Color.black));
    bookType.setBorder(new LineBorder(Color.black));
    p.setBorder(new LineBorder(Color.black));
    price.setBorder(new LineBorder(Color.black));
    
    // add border to button
    add.setBorder(new LineBorder(Color.black));
    update.setBorder(new LineBorder(Color.black));
    delete.setBorder(new LineBorder(Color.black));
    exit.setBorder(new LineBorder(Color.black));
    
    //add to the panel
    panel.setLayout(null);
    panel.add(bookLbl);
    panel.add(bookIdLbl);
    panel.add(nameLbl);
    panel.add(bcodeLbl);
    panel.add(dateLbl);
    panel.add(categoryLbl);
    panel.add(bookTypeLbl);
    panel.add(publisherLbl);
    panel.add(priceLbl);
    
    panel.add(bookId);
    panel.add(name);
    panel.add(bcode);
    panel.add(date);
    panel.add(category);
    panel.add(bookType);
    panel.add(p);
    panel.add(price);
    
    panel.add(add);
    panel.add(update);
    panel.add(delete);
    panel.add(exit);
    
    panel.add(pane);
    
    panel.setBorder(new LineBorder(Color.black));
    
    // add border to table
    tb.setBorder(new LineBorder(Color.black));
    
    tableload();
    autoId();
    getContentPane().add(panel);
    setVisible(true);
    setSize(1100, 500);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    
    public void actionPerformed(ActionEvent event) {
        if(event.getSource() == add) {
            String bid = this.bookId.getText();
            String name = this.name.getText();
            String bcode = this.bcode.getText();
            String date = ((JTextField)this.date.getDateEditor().getUiComponent()).getText().toString();
            String category = this.category.getSelectedItem().toString();
            String bookType = this.bookType.getSelectedItem().toString();
            String  publisher = this.publisher.getText();
            String price = this.price.getText();
            try{
            String sql = "INSERT INTO addbook VALUES('"+bid+"', '"+name+"', '"+bcode+"', '"+date+"', '"+category+"' , '"+bookType+"', '"+publisher+"', '"+price+"','0')";
            PreparedStatement pst = (PreparedStatement)conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Successfully inserted");
            } catch(Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
            clearField();
            tableload();
            autoId();
        }else 
            if(event.getSource() == update) {
            try {
            String bid = this.bookId.getText();
            String name = this.name.getText();
            String bcode = this.bcode.getText();
            String date = ((JTextField)this.date.getDateEditor().getUiComponent()).getText().toString();
            String category = this.category.getSelectedItem().toString();
            String bookType = this.bookType.getSelectedItem().toString();
            String  publisher = this.publisher.getText();
            String price = this.price.getText();
            
            String sql = "UPDATE addbook SET name='"+name+"', b_code = '"+bcode+"', date='"+date+"', category = '"+category+"', book_type='"+bookType+"', publisher='"+publisher+"', price='"+price+"'";
            pst = conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Update success");
            tableload();
            clearField();
            }catch(Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
    }else
        if(event.getSource() == delete) {
            try {
                String sql = "DELETE FROM addbook WHERE bid = '"+bookId.getText()+"'";
                pst = conn.prepareStatement(sql);
                pst.execute();
                JOptionPane.showMessageDialog(null, "Delete success");
                tableload();
            }catch(Exception e) {
            JOptionPane.showMessageDialog(null, e);
            }
            clearField();
        }else if(event.getSource() == exit) {
                dispose();
        }       
}
    
   private void clearField() { 
        //bookId.setText("");
        name.setText("");
        bcode.setText("");
        ((JTextField)date.getDateEditor().getUiComponent()).setText("");
        category.setSelectedIndex(0);
        bookType.setSelectedIndex(0);
        publisher.setText("");
        price.setText("");
   }
    
    public void autoId() {
        try {
            String sql = "SELECT bid FROM addbook ORDER BY bid DESC LIMIT 1";            
            pst = (PreparedStatement)conn.prepareStatement(sql);
            rs = pst.executeQuery();
            if(rs.next()) {
                String rnno = rs.getString("bid");
                int co = rnno.length();
                String txt = rnno.substring(0, 2);
                String num = rnno.substring(2, co);
                int n = Integer.parseInt(num);
                n++;
                String snum = Integer.toString(n);
                String ftxt = txt + snum;
                bookId.setText(ftxt);
            }else {
                bookId.setText("BI1000");
            }
            
        }catch(Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
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
}
