package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class TimKiem extends JFrame {
	public TimKiem() {
		// TODO Auto-generated constructor stub
		this.setTitle("TÌM KIẾM");
		this.setSize(800, 500);
		// this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);

		Font font_sua = new Font("Arial", Font.BOLD, 20);

		JPanel jPanel_tdtk = new JPanel();
		jPanel_tdtk.setBackground(Color.PINK);
		JLabel jLabel_tdtk = new JLabel("Tìm Kiếm");
		jLabel_tdtk.setIcon(new ImageIcon(
				Toolkit.getDefaultToolkit().createImage(TimKiem.class.getResource("icon_searchmusic.png"))));
		jLabel_tdtk.setSize(400, 200);
		jLabel_tdtk.setForeground(Color.MAGENTA);
		jLabel_tdtk.setFont(font_sua);
		jPanel_tdtk.add(jLabel_tdtk);

		JPanel jPanel_tk = new JPanel();
		jPanel_tk.setBackground(Color.PINK);
		JLabel jLabel_goiy = new JLabel("Tên bài hát,ca sĩ,..");
		JTextField jTextField_tk = new JTextField(20);
		jPanel_tk.setLayout(new FlowLayout());
		jPanel_tk.add(jLabel_goiy);
		jPanel_tk.add(jTextField_tk);

		JPanel jPanel_bt_tk = new JPanel();
		jPanel_bt_tk.setBackground(Color.PINK);
		JButton jButton_tk = new JButton("Tìm Kiếm");
		jButton_tk.setBackground(Color.CYAN);
		jPanel_bt_tk.add(jButton_tk);

		DefaultTableModel dtm = new DefaultTableModel();
		dtm.addColumn("ID");
		dtm.addColumn("Tên Bài Hát");
		dtm.addColumn("Ca Sĩ");
		dtm.addColumn("Sáng Tác");
		dtm.addColumn("Năm Phát Hành");
		dtm.addColumn("Thể Loại");
		JTable tbl = new JTable(dtm);
		JScrollPane scb = new JScrollPane(tbl);

		jButton_tk.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (!jTextField_tk.getText().isEmpty()) {
					try {
						dtm.setRowCount(0);
						Class.forName("com.mysql.cj.jdbc.Driver");
						Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dack", "root", "");
						PreparedStatement ps = conn.prepareStatement("Select * from songlist where `Tên Bài Hát`='"
								+ jTextField_tk.getText() + "' or `Ca Sĩ`='" + jTextField_tk.getText()
								+ "' or `Sáng Tác`='" + jTextField_tk.getText() + "' or `Năm Phát Hành`='"
								+ jTextField_tk.getText() + "' or `Thể Loại`='" + jTextField_tk.getText() + "' or ID='"
								+ jTextField_tk.getText() + "'");
						ResultSet rs = ps.executeQuery();
						while (rs.next()) {
							String id = rs.getString("ID");
							String tenbh = rs.getString("Tên Bài Hát");
							String cs = rs.getString("Ca Sĩ");
							String st = rs.getString("Sáng Tác");
							String nph = rs.getString("Năm Phát Hành");
							String tl = rs.getString("Thể Loại");
							dtm.addRow(new String[] { id, tenbh, cs, st, nph, tl });
						}
						ps.close();
						conn.close();
					} catch (ClassNotFoundException | SQLException e2) {
						e2.printStackTrace();

					}
				} else {
					JOptionPane.showMessageDialog(null, "Xin mời nhập thông tin");
				}
			}
		});

		Container con = getContentPane();
		con.setLayout(new BoxLayout(con, BoxLayout.Y_AXIS));
		con.add(jPanel_tdtk);
		con.add(jPanel_tk);
		con.add(scb);
		con.add(jPanel_bt_tk);

		this.setVisible(true);
	}

	public static void main(String[] args) {
		new TimKiem();
	}

}
