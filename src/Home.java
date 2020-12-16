
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;



public class Home extends JFrame implements ActionListener {
    private Connection con = null;
    private JPanel panel;
    private JLabel name;
    private JLabel date;
    private JLabel time;
    private JButton borrowBtn;
    private JButton bookReturnBtn;
    private JButton membersBtn;
    private JButton booksBtn;
    private JButton allBooks;
    private JButton allMembers;
    private JButton lendHistory;
    private JButton pendingList;
    private JButton logout;
    private JDesktopPane pane;
   
    
    public Home() {
    
    con = DBConnection.connect();
        
    panel = new JPanel();
    
    pane = new JDesktopPane();
    
    // Objects of labels
    name = new JLabel("Liberary Name");
    date = new JLabel("Date");
    time = new JLabel("Time");
    
    //set font to label
    Font f = new Font("Consolas", Font.PLAIN, 20);
    name.setFont(f);
    date.setFont(f);
    time.setFont(f);
    
    
    // Objects of buttons
    borrowBtn = new JButton("Borrow");
    bookReturnBtn = new JButton("Return");
    membersBtn = new JButton("Add Members");
    booksBtn = new JButton("Add Books");
    allBooks = new JButton("All Books");
    allMembers = new JButton("All Members");
    lendHistory = new JButton("Lend History");
    pendingList = new JButton("Pending List");
    logout = new JButton("Logout");
    
    // set background color
    allBooks.setBackground(Color.WHITE);
    allMembers.setBackground(Color.WHITE);
    lendHistory.setBackground(Color.WHITE);
    pendingList.setBackground(Color.WHITE);
    logout.setBackground(Color.WHITE);
    
    //set layour of labels
    name.setBounds(2, 100, 150, 30);
    date.setBounds(1050, 95, 150, 30);
    time.setBounds(1200, 95, 150, 30);
    
    //Set layout of button;
    borrowBtn.setBounds(2, 200, 150, 30);
    bookReturnBtn.setBounds(2, 300, 150, 30);
    membersBtn.setBounds(2, 400, 150, 30);
    booksBtn.setBounds(2, 500, 150, 30);
    allBooks.setBounds(2, 2, 200, 30);
    allMembers.setBounds(200, 2, 200, 30);
    lendHistory.setBounds(400, 2, 200, 30);
    pendingList.setBounds(600, 2, 200, 30);
    logout.setBounds(800, 2, 200, 30);
    
    //set border to button
    borrowBtn.setBorder(new LineBorder(Color.black));
    bookReturnBtn.setBorder(new LineBorder(Color.black));
    membersBtn.setBorder(new LineBorder(Color.black));
    booksBtn.setBorder(new LineBorder(Color.black));
    allBooks.setBorder(new LineBorder(Color.black));
    allMembers.setBorder(new LineBorder(Color.black));
    lendHistory.setBorder(new LineBorder(Color.black));
    pendingList.setBorder(new LineBorder(Color.black));
    logout.setBorder(new LineBorder(Color.black));
    
    //set layour to desktop pane
    pane.setBounds(200, 120, 1200, 600);
    
    //set color to pane
    pane.setBackground(Color.DARK_GRAY);
    
    //set border to pane
    pane.setBorder(new LineBorder(Color.black));
    
    //add action listener to button
    membersBtn.addActionListener(this);
    booksBtn.addActionListener(this);
    borrowBtn.addActionListener(this);
    bookReturnBtn.addActionListener(this);
    allBooks.addActionListener(this);
    allMembers.addActionListener(this);
    lendHistory.addActionListener(this);
    pendingList.addActionListener(this);
    logout.addActionListener(this);
    
    //add component to panel
    panel.setLayout(null);
    panel.add(name);
    panel.add(date);
    panel.add(time);
    panel.add(borrowBtn);
    panel.add(bookReturnBtn);
    panel.add(membersBtn);
    panel.add(booksBtn);  
    panel.add(allBooks);
    panel.add(allMembers);
    panel.add(lendHistory);
    panel.add(pendingList);
    panel.add(logout);
    panel.add(pane);
    
    //add border to panel
    panel.setBorder(new LineBorder(Color.black));
    
    getContentPane().add(panel);
    setSize(1350, 700);
    setVisible(true);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public void actionPerformed(ActionEvent event) {
       if(event.getSource() == membersBtn) {
           AddMembers member = new AddMembers();
           pane.add(member).setVisible(true);
       }else if(event.getSource() == booksBtn) {
           AddBooks books = new AddBooks();
           pane.add(books).setVisible(true);
       }else if(event.getSource() == borrowBtn) {
           Borrow borrow = new Borrow();
           pane.add(borrow).setVisible(true);
       }else if(event.getSource() == bookReturnBtn) {
           BookReturn bookReturn = new BookReturn();
           pane.add(bookReturn).setVisible(true);
       }else if(event.getSource() == allBooks) {
           AllBooks books = new AllBooks();
           pane.add(books).setVisible(true);
       }else if(event.getSource() == allMembers) {
           AllMembers member = new AllMembers();
           pane.add(member).setVisible(true);
       }else if(event.getSource() == lendHistory) {
           LendHistory lend = new LendHistory();
           pane.add(lend).setVisible(true);
       }else if(event.getSource() == pendingList) {
           PendingList pending = new PendingList();
           pane.add(pending).setVisible(true);
       }else if(event.getSource() == logout) {
            this.dispose();
            Login log = new Login();
            log.setVisible(true);
       }
    }
    public void clock() {
        Thread clock = new Thread() {
            public void run() {
         try {
        while(true){           
        Calendar cal = new GregorianCalendar();
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int month = (cal.get(Calendar.MONTH))+1;
        int year = cal.get(Calendar.YEAR);
        date.setText(day+"-"+month+"-"+year);
        
        int second = cal.get(Calendar.SECOND);
        int minute = cal.get(Calendar.MINUTE);
        int hour = cal.get(Calendar.HOUR);
        time.setText(hour+":"+minute+":"+second);
                    sleep(1000);
        }
                } catch (InterruptedException ex) {
                    Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        clock.start();
    }
}
