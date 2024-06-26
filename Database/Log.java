import javax.swing.*;
import java.sql.*;
import java.awt.event.*;

public class Log extends JFrame implements ActionListener {
    JTextField jt1;
    JButton btn;
    String sql = "SELECT Marks FROM details WHERE Name = ?";
    Connection con;

    public Log(Connection con) {
        this.con = con;
        this.setVisible(true);
        this.setSize(500, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        jt1 = new JTextField("Name");
        jt1.setBounds(10, 30, 100, 30);
        btn = new JButton("Check");
        btn.setBounds(10, 70, 100, 30);
        this.add(jt1);
        this.add(btn);
        btn.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btn) {
            try {
                PreparedStatement stmt = con.prepareStatement(sql);
                stmt.setString(1, jt1.getText());
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    JOptionPane.showMessageDialog(this, "Marks: " + rs.getString("Marks"));
                } else {
                    JOptionPane.showMessageDialog(this, " No Such USer");
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
        this.dispose();
    }
}
