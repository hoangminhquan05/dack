package View;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.KeyStore.TrustedCertificateEntry;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class DangNhap extends JFrame {
	public DangNhap() {
		this.setSize(375, 500);
		this.setTitle("Đăng Nhập");
		this.setLocationRelativeTo(null);
		this.setIconImage(Toolkit.getDefaultToolkit().createImage(DangNhap.class.getResource("lock_td.png")));
		Font font_dn = new Font("Times New Roman", ABORT, 15);

		JPanel jPanel_gif = new JPanel();
		JLabel jLabel_gif = new JLabel();
		jLabel_gif.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(DangNhap.class.getResource("dnh.gif"))));
		jPanel_gif.add(jLabel_gif);
		
		JPanel jPanel_tkmk = new JPanel();
		jPanel_tkmk.setLayout(new GridLayout(2, 2,0, 10));
		JLabel jLabel_Email = new JLabel("Tài Khoản", JLabel.CENTER);
		jLabel_Email.setFont(font_dn);
		jLabel_Email.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(DangNhap.class.getResource("ID.png"))));
		JTextField jTextField_Email = new JTextField(10);
		jTextField_Email.setFont(font_dn);
		JLabel jLabel_Pass = new JLabel("Mật Khẩu", JLabel.CENTER);
		jLabel_Pass.setFont(font_dn);
		jLabel_Pass.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(DangNhap.class.getResource("mk.png"))));

		JPasswordField jPass = new JPasswordField(10);
		jPanel_tkmk.add(jLabel_Email);
		jPanel_tkmk.add(jTextField_Email);
		jPanel_tkmk.add(jLabel_Pass);
		jPanel_tkmk.add(jPass);

		JPanel jPanel_btlg = new JPanel();
		jPanel_btlg.setLayout(new FlowLayout());
		JButton jblogin = new JButton("Login");
		jblogin.setBackground(Color.lightGray);
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
					ResultSet rs = sttm.executeQuery("select * from tkmk where Taikhoan ='" + jTextField_Email.getText()
							+ "'and Matkhau = '" + jPass.getText() + "'");
					if (rs.next()) {
						JOptionPane.showMessageDialog(null, "Đăng Nhập Thành Công");
						DanhSach danhSach = new DanhSach();
						setVisible(false);
					} else {
						// JOptionPane.showConfirmDialog(null, "Fail login");
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
		con_dn.setLayout(new BoxLayout(con_dn, BoxLayout.Y_AXIS));
		con_dn.add(jPanel_gif);
		con_dn.add(jPanel_tkmk);
		con_dn.add(jPanel_btlg);

		this.setVisible(true);
	}
	public static void main(String[] args) {
		new DangNhap();
	}
}
