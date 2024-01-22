package View;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class DangNhapUser extends JFrame {
	public DangNhapUser() {
		this.setSize(250, 300);
		this.setTitle("Đăng Nhập");
		this.setLocationRelativeTo(null);

		Font font_dn = new Font("Arial", Font.BOLD, 15);

		JPanel jPanel_tieude = new JPanel();
		JLabel jLabel_tieude = new JLabel("Đăng Nhập");
		jLabel_tieude.setFont(font_dn);
		jLabel_tieude.setForeground(Color.MAGENTA);
		jPanel_tieude.add(jLabel_tieude);

		JPanel jPanel_tkmk = new JPanel();
		jPanel_tkmk.setLayout(new GridLayout(2, 2, 10, 10));
		JLabel jLabel_Email = new JLabel("ID", JLabel.CENTER);
		jLabel_Email.setFont(font_dn);
		JTextField jTextField_Email = new JTextField(10);
		JLabel jLabel_Pass = new JLabel("Mật Khẩu", JLabel.CENTER);
		jLabel_Pass.setFont(font_dn);
		JPasswordField jPass = new JPasswordField(10);
		jPanel_tkmk.add(jLabel_Email);
		jPanel_tkmk.add(jTextField_Email);
		jPanel_tkmk.add(jLabel_Pass);
		jPanel_tkmk.add(jPass);

		JPanel jPanel_btlg = new JPanel();
		jPanel_btlg.setLayout(new FlowLayout());
		JButton jblogin = new JButton("Login");
		jPanel_btlg.add(jblogin);

		jblogin.setIcon(
				new ImageIcon(Toolkit.getDefaultToolkit().createImage(DangNhap.class.getResource("icon_loginn.png"))));

		jblogin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dack", "root", "");
					Statement sttm = con.createStatement();
					ResultSet rs = sttm.executeQuery("select * from thongtinnguoidung where `Tài khoản` ='"
							+ jTextField_Email.getText() + "'and `Mật khẩu` = '" + jPass.getText() + "'");

					if (rs.next()) {
						JOptionPane.showMessageDialog(null, "Đăng nhập thành công");
						setVisible(false);
						//TcnUser tcnUser = new TcnUser();
						// tcnUser.setVisible(true);
					} else {
						JOptionPane.showMessageDialog(null, "Tài Khoản hoặc Mật Khẩu không đúng!", "Lỗi",
								JOptionPane.INFORMATION_MESSAGE);
					}
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
			}
		});

		Container con_dn = getContentPane();
		con_dn.setLayout(new GridLayout(3, 1));
		con_dn.add(jPanel_tieude);
		con_dn.add(jPanel_tkmk);
		con_dn.add(jPanel_btlg);

		this.setVisible(true);
	}
	public static void main(String[] args) {
		new DangNhapUser();
	}
}
