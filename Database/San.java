import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;

public class San extends JFrame implements ActionListener {

    static Connection con = null;

    JTextField jt1;
    JTextField jt2;
    JTextField jt3;
    JTextField jt4;
    JButton btn;
    JButton login;

    public San() {
        this.setVisible(true);
        this.setSize(500, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setTitle("Student Details");
        this.getContentPane().setBackground(Color.CYAN);

        jt1 = new JTextField(" Id");
        jt2 = new JTextField("name");
        jt3 = new JTextField(" Marks");
        jt4 = new JTextField(" Div");
        btn = new JButton("Submit");
        login = new JButton("Login");
        jt1.setBounds(10, 20, 200, 30);
        jt2.setBounds(10, 80, 200, 30);
        jt3.setBounds(10, 140, 200, 30);
        jt4.setBounds(10, 200, 200, 30);
        btn.setBounds(10, 260, 90, 30);
        login.setBounds(10, 300, 90, 30);
        this.add(jt1);
        this.add(jt2);
        this.add(jt3);
        this.add(jt4);
        this.add(btn);
        this.add(login);
        btn.addActionListener(this);
        login.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btn) {
            int id = Integer.parseInt(jt1.getText());
            String name = jt2.getText();
            int marks = Integer.parseInt(jt3.getText());
            String div = jt4.getText();
            String sql = "INSERT INTO details (Id,Name,Marks,`Div`) VALUES(?,?,?,?)";

            try {
                PreparedStatement st = con.prepareStatement(sql);
                st.setInt(1, id);
                st.setString(2, name);
                st.setInt(3, marks);
                st.setString(4, div);
                st.executeUpdate();
                System.out.println("Data Inserted");
            } catch (Exception ep) {
                System.out.println(ep.getMessage());
            }
        } else {
          
            new Log(con);
            
        }

    }

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/student";
        String user = "root";
        String password = "";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, user, password);
            new San();  
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

