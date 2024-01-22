package View;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class ThemBaiHat extends JFrame {
	public ThemBaiHat() {
		this.setTitle("Thêm Bài Hát");
		this.setSize(400, 500);
		this.setLocationRelativeTo(null);

		Font font_them = new Font("Times New Roman", Font.BOLD, 20);

		JPanel jPanel_td = new JPanel();
		jPanel_td.setBackground(Color.PINK);
		JLabel jLabel_td = new JLabel("Thêm Bài Hát");
		jLabel_td.setForeground(Color.BLUE);
		jLabel_td.setFont(font_them);
		jPanel_td.add(jLabel_td);

		Font font_lb = new Font("Times New Roman", Font.BOLD, 16);
		JLabel jLabel_name = new JLabel("Tên bài hát:", JLabel.CENTER);
		jLabel_name.setForeground(Color.RED);
		jLabel_name.setFont(font_lb);
		JTextField jTextField_name = new JTextField(10);
		JLabel jLabel_cs = new JLabel("Ca sĩ:", JLabel.CENTER);
		jLabel_cs.setForeground(Color.RED);
		jLabel_cs.setFont(font_lb);
		JTextField jTextField_cs = new JTextField(10);
		JLabel jLabel_st = new JLabel("Sáng tác:", JLabel.CENTER);
		jLabel_st.setForeground(Color.RED);
		jLabel_st.setFont(font_lb);
		JTextField jTextField_st = new JTextField(10);
		JLabel jLabel_nph = new JLabel("Năm phát hành:", JLabel.CENTER);
		jLabel_nph.setForeground(Color.RED);
		jLabel_nph.setFont(font_lb);
		JTextField jTextField_nph = new JTextField(10);
		JLabel jLabel_tl = new JLabel("Thể Loại:", JLabel.CENTER);
		jLabel_tl.setForeground(Color.RED);
		jLabel_tl.setFont(font_lb);
		JTextField jTextField_tl = new JTextField(10);

		JPanel jPanel_ok = new JPanel();
		jPanel_ok.setBackground(Color.PINK);
		jPanel_ok.setLayout(new FlowLayout());
		JButton jButton_ok = new JButton("Thêm");
		// icon
		jButton_ok.setIcon(
				new ImageIcon(Toolkit.getDefaultToolkit().createImage(ThemBaiHat.class.getResource("icon_ok.png"))));
		jPanel_ok.add(jButton_ok);

		JPanel jPanel_giua = new JPanel();
		jPanel_giua.setBackground(Color.PINK);
		jPanel_giua.setLayout(new GridLayout(5, 2, 10, 10));

		jPanel_giua.add(jLabel_name);
		jPanel_giua.add(jTextField_name);
		jPanel_giua.add(jLabel_cs);
		jPanel_giua.add(jTextField_cs);
		jPanel_giua.add(jLabel_st);
		jPanel_giua.add(jTextField_st);
		jPanel_giua.add(jLabel_nph);
		jPanel_giua.add(jTextField_nph);
		jPanel_giua.add(jLabel_tl);
		jPanel_giua.add(jTextField_tl);

		jButton_ok.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String name = jTextField_name.getText();
				String cs = jTextField_cs.getText();
				String st = jTextField_st.getText();
				String nph = jTextField_nph.getText();
				String tl = jTextField_tl.getText();
				if (!name.isEmpty() && !cs.isEmpty() && !st.isEmpty() && !nph.isEmpty() && !tl.isEmpty()) {
					try {
						Class.forName("com.mysql.cj.jdbc.Driver");
						Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dack", "root", "");
						Statement sttm = conn.createStatement();
						sttm.executeUpdate(
								"insert into songlist (`Tên Bài Hát`, `Ca Sĩ`, `Sáng Tác`, `Năm Phát Hành`, `Thể Loại`) values ('"
										+ jTextField_name.getText() + "','" + jTextField_cs.getText() + "','"
										+ jTextField_st.getText() + "','" + jTextField_nph.getText() + "','"
										+ jTextField_tl.getText() + "')");
						JOptionPane.showMessageDialog(null, "Thêm thành công");
						sttm.close();
						conn.close();
						setVisible(false);
					} catch (Exception e2) {
						// TODO: handle exception
						e2.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(null, "Thêm thất bại! \n" + "Vui lòng nhập đủ thông tin.");
				}
			}
		});

		Container con = this.getContentPane();
		con.setLayout(new BoxLayout(con, BoxLayout.Y_AXIS));
		con.add(jPanel_td);
		con.add(jPanel_giua);
		con.add(jPanel_ok);

		this.setVisible(true);
	}

	public static void main(String[] args) {
		new ThemBaiHat();
	}
}
