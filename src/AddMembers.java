
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


public class AddMembers extends JInternalFrame implements ActionListener {
    
    private Connection conn = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;
    
    private JPanel panel;
   
    private JLabel memberLbl;
    private JLabel idLbl;
    private JLabel nameLbl;
    private JLabel nicLbl;
    private JLabel birthdayLbl;
    private JLabel genderLbl;
    private JLabel addressLbl;
    private JLabel emailLbl;
    private JLabel contactLbl;
    private JLabel memberTypeLbl;
    
    private JTextField id;
    private JTextField name;
    private JTextField nic;
    private JDateChooser birthday;
    private JComboBox gender;
    private JTextArea address;
    private JTextField email;
    private JTextField contactNo;
    private JComboBox memberType;
    
    private JButton add;
    private JButton update;
    private JButton delete;
    private JButton exit;
    
    private JTable tb;
    
    public AddMembers() {
    
    conn = DBConnection.connect();
    
    
    panel = new JPanel();
    
    //label objects
    memberLbl = new JLabel("Members");
    idLbl = new JLabel("Member Id");
    nameLbl = new JLabel("Name");
    nicLbl = new JLabel("NIC");    
    birthdayLbl = new JLabel("Birthday");
    genderLbl = new JLabel("Gender");
    addressLbl = new JLabel("Address");
    emailLbl = new JLabel("Email");
    contactLbl = new JLabel("Contact No");
    memberTypeLbl = new JLabel("Member Type");
    
    //array
    String[] gen = {"Select gender", "Male", "Female"};
    String[] type = {"Select type","Student", "Other"};
    
    //set font to the label
    memberLbl.setFont(new Font("Consolas", Font.PLAIN, 20));
    
    
    //text field objects
    id = new JTextField();
    name = new JTextField();
    nic = new JTextField();
    birthday = new JDateChooser();
    gender = new JComboBox(gen);
    address = new JTextArea();
    JScrollPane p = new JScrollPane(address);
    email = new JTextField();
    contactNo = new JTextField();
    memberType = new JComboBox(type);
    
    //set id editable false
    id.setEditable(false);
    
    //buttons object
    add = new JButton("Add");
    update = new JButton("Update");
    delete = new JButton("Delete");
    exit = new JButton("Exit");
    
    //table object
    tb = new JTable();
    
    // label layout
    memberLbl.setBounds(20, 20, 80, 20);
    idLbl.setBounds(20, 50, 80, 20);
    nameLbl.setBounds(20, 100, 80, 20);
    nicLbl.setBounds(20, 150, 80, 20);
    birthdayLbl.setBounds(20, 200, 80, 20);
    genderLbl.setBounds(20, 250, 80, 20);
    addressLbl.setBounds(400, 50, 80, 20);
    emailLbl.setBounds(400, 150, 80, 20);
    contactLbl.setBounds(400, 200, 80, 20);
    memberTypeLbl.setBounds(400, 250, 80, 20);
    
    // text field layout
    id.setBounds(100, 50, 170, 30);
    name.setBounds(100, 100, 170, 30);
    nic.setBounds(100, 150, 170, 30);
    birthday.setBounds(100, 200, 170, 30);
    gender.setBounds(100, 250, 170, 30);
    p.setBounds(500, 50, 200, 60);
    email.setBounds(500, 150, 170, 30);
    contactNo.setBounds(500, 200, 170, 30);
    memberType.setBounds(500, 250, 170, 30);
    
    //add border to textfield
    id.setBorder(new LineBorder(Color.black));
    name.setBorder(new LineBorder(Color.black));
    nic.setBorder(new LineBorder(Color.black));
    birthday.setBorder(new LineBorder(Color.black));
    gender.setBorder(new LineBorder(Color.black));
    p.setBorder(new LineBorder(Color.black));
    email.setBorder(new LineBorder(Color.black));
    contactNo.setBorder(new LineBorder(Color.black));
    memberType.setBorder(new LineBorder(Color.black));
    
    
    //button layout
    add.setBounds(800, 50, 150, 30);
    update.setBounds(800, 100, 150, 30);
    delete.setBounds(800, 150, 150, 30);
    exit.setBounds(800, 200, 150, 30);
    
    //add border to buttons
    add.setBorder(new LineBorder(Color.black));
    update.setBorder(new LineBorder(Color.black));
    delete.setBorder(new LineBorder(Color.black));
    exit.setBorder(new LineBorder(Color.black));
    
    //table layout
    JScrollPane pane = new JScrollPane(tb);
    pane.setBounds(50, 300, 1000, 500);
    
    
    //add border to table
    pane.setBorder(new LineBorder(Color.black));
    
    
    //add mouseclick event to the table
    tb.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            DefaultTableModel tmodel = (DefaultTableModel)tb.getModel();
            int selectrowindex = tb.getSelectedRow();
            
            id.setText(tmodel.getValueAt(selectrowindex, 0).toString());
            name.setText(tmodel.getValueAt(selectrowindex, 1).toString());
            nic.setText(tmodel.getValueAt(selectrowindex, 2).toString());
            ((JTextField)birthday.getDateEditor().getUiComponent()).setText(tmodel.getValueAt(selectrowindex, 3).toString());
            gender.setSelectedItem(tmodel.getValueAt(selectrowindex, 4).toString());
            address.setText(tmodel.getValueAt(selectrowindex, 5).toString());
            email.setText(tmodel.getValueAt(selectrowindex, 6).toString());
            contactNo.setText(tmodel.getValueAt(selectrowindex, 7).toString());
            memberType.setSelectedItem(tmodel.getValueAt(selectrowindex, 8).toString());
            
            
        }
    });
    
    //add actionListener to button
    add.addActionListener(this);
    update.addActionListener(this);
    delete.addActionListener(this);
    exit.addActionListener(this);
    
    panel.setLayout(null);
    panel.add(memberLbl);
    panel.add(idLbl);
    panel.add(nameLbl);
    panel.add(nicLbl);
    panel.add(birthdayLbl);
    panel.add(genderLbl);
    panel.add(addressLbl);
    panel.add(emailLbl);
    panel.add(contactLbl);
    panel.add(memberTypeLbl);
    
    
    panel.add(id);
    panel.add(name);
    panel.add(nic);
    panel.add(birthday);
    panel.add(gender);
    panel.add(p);
    panel.add(email);
    panel.add(contactNo);
    panel.add(memberType);
    
    panel.add(add);
    panel.add(update);
    panel.add(delete);
    panel.add(exit);
    
    panel.add(pane);
    
    panel.setBorder(new LineBorder(Color.black));
    
    getContentPane().add(panel);
    setVisible(true);
    setSize(1100, 500);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    clearField();
    tableload();
    autoId();
    }
    
    public void actionPerformed(ActionEvent event) {
        if(event.getSource() == add) {
            String mid = this.id.getText();
            String name = this.name.getText();
            String nic = this.nic.getText();
            String date = ((JTextField)this.birthday.getDateEditor().getUiComponent()).getText();
            String gender = this.gender.getSelectedItem().toString();
            String email = this.email.getText();
            String  address = this.address.getText();
            String contact = this.contactNo.getText();
            String type = this.memberType.getSelectedItem().toString();
            try{
            String sql = "INSERT INTO addmember VALUES('"+mid+"', '"+name+"', '"+nic+"', '"+date+"', '"+gender+"' , '"+address+"', '"+email+"', '"+contact+"', '"+type+"')";
            PreparedStatement pst = (PreparedStatement)conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Successfully inserted");
            } catch(Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
            tableload();
            clearField();
            
        }else
        if(event.getSource() == update) {
            try {
            String mid = this.id.getText();
            String name = this.name.getText();
            String nic = this.nic.getText();
            String date = ((JTextField)this.birthday.getDateEditor().getUiComponent()).getText().toString();
            String gender = this.gender.getSelectedItem().toString();
            String email = this.email.getText();
            String  address = this.address.getText();
            String contact = this.contactNo.getText();
            String type = this.memberType.getSelectedItem().toString();
            
            String sql = "UPDATE addmember SET name='"+name+"', nic = '"+nic+"', birthday='"+date+"', gender = '"+gender+"', address='"+address+"', email='"+email+"', contact='"+contact+"', type='"+type+"' WHERE mid = '"+mid+"'";
            pst = conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Update success");
            tableload();
            }catch(Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }else
        if(event.getSource() == delete) {
            try {
                String sql = "DELETE FROM addmember WHERE mid = '"+id.getText()+"'";
                pst = conn.prepareStatement(sql);
                pst.execute();
                JOptionPane.showMessageDialog(null, "Delete success");
                tableload();
            }catch(Exception e) {
            JOptionPane.showMessageDialog(null, e);
            }
        }else if(event.getSource() == exit) {
            dispose();
        }
    } 
    private void clearField() {
        id.setText("");
        name.setText("");
        nic.setText("");
        ((JTextField)birthday.getDateEditor().getUiComponent()).setText("");
        gender.setSelectedIndex(0);
        address.setText("");
        email.setText("");
        contactNo.setText("");
        memberType.setSelectedIndex(0);
    }
     public void autoId() {
        try {
            String sql = "SELECT mid FROM addmember ORDER BY mid DESC LIMIT 1";            
            pst = (PreparedStatement)conn.prepareStatement(sql);
            rs = pst.executeQuery();
            if(rs.next()) {
                String rnno = rs.getString("mid");
                int co = rnno.length();
                String txt = rnno.substring(0, 2);
                String num = rnno.substring(2, co);
                int n = Integer.parseInt(num);
                n++;
                String snum = Integer.toString(n);
                String ftxt = txt + snum;
                id.setText(ftxt);
            }else {
                id.setText("MI1000");
            }
            
        }catch(Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void tableload() {
        try {
            String sql = "SELECT *  FROM  addmember";
            pst = (PreparedStatement)conn.prepareStatement(sql);
            rs = pst.executeQuery();
            tb.setModel(net.proteanit.sql.DbUtils.resultSetToTableModel(rs));
            
        }catch(Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
}

