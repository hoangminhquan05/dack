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
import java.sql.Statement;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.google.protobuf.DescriptorProtos.MethodOptions.IdempotencyLevel;
import com.mysql.cj.protocol.a.result.TextBufferRow;

public class SuaBaiHat extends JFrame {
	private JTextField jTextField_name;
	private JTextField jTextField_cs;
	private JTextField jTextField_st;
	private JTextField jTextField_nph;
	private JTextField jTextField_tl;

	public SuaBaiHat() {
		this.setTitle("SỬA");
		this.setSize(400, 500);
		// this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);

		Font font_sua = new Font("Times New Roman", Font.BOLD, 21);

		JPanel jPanel_td = new JPanel();
		jPanel_td.setBackground(Color.PINK);
		JLabel jLabel_td = new JLabel("Sửa Thông Tin");
		jLabel_td.setForeground(Color.BLUE);
		jLabel_td.setFont(font_sua);
		jPanel_td.add(jLabel_td);
		
		Font font_sua2 = new Font("Times New Roman", Font.BOLD, 16);
		JLabel jLabel_name = new JLabel("Tên bài hát:", JLabel.CENTER);
		jLabel_name.setForeground(Color.BLUE);
		jLabel_name.setFont(font_sua2);
		jTextField_name = new JTextField(10);
		JLabel jLabel_cs = new JLabel("Ca sĩ:", JLabel.CENTER);
		jLabel_cs.setForeground(Color.BLUE);
		jLabel_cs.setFont(font_sua2);
		jTextField_cs = new JTextField(10);
		JLabel jLabel_st = new JLabel("Sáng tác:", JLabel.CENTER);
		jLabel_st.setForeground(Color.BLUE);
		jLabel_st.setFont(font_sua2);
		jTextField_st = new JTextField(10);
		JLabel jLabel_nph = new JLabel("Năm phát hành:", JLabel.CENTER);
		jLabel_nph.setForeground(Color.BLUE);
		jLabel_nph.setFont(font_sua2);
		jTextField_nph = new JTextField(10);
		JLabel jLabel_tl = new JLabel("Thể Loại:", JLabel.CENTER);
		jLabel_tl.setForeground(Color.BLUE);
		jLabel_tl.setFont(font_sua2);
		jTextField_tl = new JTextField(10);

		JPanel jPanel_ok = new JPanel();
		jPanel_ok.setLayout(new FlowLayout());
		JButton jButton_ok = new JButton("Sửa");

		jButton_ok.setIcon(
				new ImageIcon(Toolkit.getDefaultToolkit().createImage(SuaBaiHat.class.getResource("icon_ok.png"))));

		jPanel_ok.add(jButton_ok);
		jPanel_ok.setBackground(Color.pink);

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

		jTextField_name.setText(DanhSach.ten);
		jTextField_cs.setText(DanhSach.cs);
		jTextField_st.setText(DanhSach.st);
		jTextField_nph.setText(DanhSach.nph);
		jTextField_tl.setText(DanhSach.tl);

		Container con = this.getContentPane();
		con.setLayout(new BoxLayout(con, BoxLayout.Y_AXIS));
		con.add(jPanel_td);
		con.add(jPanel_giua);
		con.add(jPanel_ok);

		jButton_ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String iD = DanhSach.id;
				String ten = jTextField_name.getText();
				String cs = jTextField_cs.getText();
				String st = jTextField_st.getText();
				String nph = jTextField_nph.getText();
				String tl = jTextField_tl.getText();
				if (!ten.isEmpty() && !cs.isEmpty() && !st.isEmpty() && !nph.isEmpty() && !tl.isEmpty()) {
					try {
						Class.forName("com.mysql.cj.jdbc.Driver");
						Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dack", "root", "");
						Statement sttm = conn.createStatement();
						sttm.executeUpdate("update songlist set `Tên Bài Hát` = '" + jTextField_name.getText() + "',"
								+ "`Ca Sĩ`='" + jTextField_cs.getText() + "',`Sáng Tác`='" + jTextField_st.getText()
								+ "', `Năm Phát Hành` = '" + jTextField_nph.getText() + "',`Thể Loại`='"
								+ jTextField_tl.getText() + "' where ID =" + iD);
						JOptionPane.showMessageDialog(null, "Sửa thành công");

						sttm.close();
						conn.close();
						setVisible(false);
					} catch (Exception e2) {
						// TODO: handle exception
						e2.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(null, "Sửa thất bại! \n" + "Vui lòng nhập đủ thông tin.");
				}

			}
		});

		this.setVisible(true);
	}

	public static void main(String[] args) {
		SuaBaiHat suaBaihat = new SuaBaiHat();
	}
}