package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

public class DanhSach extends JFrame {
	static String id;
	static String ten;
	static String cs;
	static String st;
	static String nph;
	static String tl;
	private JTable tbl;
	private JScrollPane scb;
	static JTextField jTextField_tk;

	public DanhSach() {
		this.setSize(950, 500);
		this.setTitle("Danh sách bài hát");
		this.setLocationRelativeTo(null);
		// this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		URL urlMusic = DanhSach.class.getResource("icon_music.png");
		Image img = Toolkit.getDefaultToolkit().createImage(urlMusic);
		this.setIconImage(img);

		JButton jButton_tk = new JButton("Tìm kiếm");
		JButton jButton_them = new JButton("Thêm");
		JButton jButton_sua = new JButton("Sửa");
		JButton jButton_xoa = new JButton("Xóa");
		JButton jButton_lm = new JButton("Làm mới");
		JLabel jlb = new JLabel("                   ");
		jButton_tk.setIcon(
				new ImageIcon(Toolkit.getDefaultToolkit().createImage(DanhSach.class.getResource("icon_tk.png"))));
		jButton_them.setIcon(
				new ImageIcon(Toolkit.getDefaultToolkit().createImage(DanhSach.class.getResource("icon_add.png"))));
		jButton_sua.setIcon(
				new ImageIcon(Toolkit.getDefaultToolkit().createImage(DanhSach.class.getResource("icon_edit.png"))));
		jButton_xoa.setIcon(
				new ImageIcon(Toolkit.getDefaultToolkit().createImage(DanhSach.class.getResource("icon_delete.png"))));
		jButton_lm.setIcon(
				new ImageIcon(Toolkit.getDefaultToolkit().createImage(DanhSach.class.getResource("icon_rf.png"))));
		
		JLabel jLabel_goiy = new JLabel("(Tên bài hát,ca sĩ,..)");
		Font fontt = new Font("Arial", ABORT, 10);
		jLabel_goiy.setFont(fontt);

		Container con = this.getContentPane();

		JPanel jPanel_tren = new JPanel();
		Font font = new Font("Times New Roman", Font.BOLD, 20);
		JLabel jLabel_ds = new JLabel("Danh sách bài hát");
		jLabel_ds.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(urlMusic)));
		jLabel_ds.setFont(font);
		jLabel_ds.setForeground(Color.RED);
		jPanel_tren.setBackground(Color.PINK);
		jPanel_tren.add(jLabel_ds);
	
		Font font_tbl = new Font("Times New Roman", Font.BOLD, 17);
		Border border_tbl = BorderFactory.createLineBorder(Color.RED);
		TitledBorder borderTitle = BorderFactory.createTitledBorder(border_tbl, "Thông Tin Bài Hát");
		borderTitle.setTitleFont(font_tbl);

		Border border_cn = BorderFactory.createLineBorder(Color.RED);
		TitledBorder borderTitled_cn = BorderFactory.createTitledBorder(border_cn, "Chức Năng");
		borderTitle.setTitleFont(font_tbl);

		DefaultTableModel dtm = new DefaultTableModel();
		dtm.addColumn("ID");
		dtm.addColumn("Tên Bài Hát");
		dtm.addColumn("Ca Sĩ");
		dtm.addColumn("Sáng Tác");
		dtm.addColumn("Năm Phát Hành");
		dtm.addColumn("Thể Loại");
		JTable tbl = new JTable(dtm);
		JScrollPane scb = new JScrollPane(tbl);
		scb.setBorder(borderTitle);
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dack", "root", "");
			PreparedStatement ps = conn.prepareStatement("Select * from songlist");
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
		} catch (Exception e2) {
			e2.printStackTrace();
		}

		jButton_lm.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					dtm.setRowCount(0);

					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dack", "root", "");
					PreparedStatement ps = conn.prepareStatement("Select * from songlist");
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
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
			}
		});
		JPanel jPanel_bt = new JPanel();
		jPanel_bt.setLayout(new FlowLayout());
		jTextField_tk = new JTextField(15);
		jPanel_bt.add(jButton_them);
		jPanel_bt.add(jButton_sua);
		jPanel_bt.add(jButton_xoa);
		jPanel_bt.add(jButton_lm);
		jPanel_bt.add(jlb);
		jPanel_bt.add(jButton_tk);
		jPanel_bt.add(jLabel_goiy);
		jPanel_bt.add(jTextField_tk);
		jPanel_bt.setBorder(borderTitled_cn);
		jPanel_bt.setBackground(Color.CYAN);
		borderTitled_cn.setTitleFont(font_tbl);

		jButton_them.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ThemBaiHat themBaiHat = new ThemBaiHat();
			}
		});

		tbl.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				int selectedRow = tbl.getSelectedRow();
				id = tbl.getValueAt(selectedRow, 0).toString();
				ten = tbl.getValueAt(selectedRow, 1).toString();
				cs = tbl.getValueAt(selectedRow, 2).toString();
				st = tbl.getValueAt(selectedRow, 3).toString();
				nph = tbl.getValueAt(selectedRow, 4).toString();
				tl = tbl.getValueAt(selectedRow, 5).toString();
				System.out.println(ten + cs + st + nph + tl);

			}
		});
		jButton_sua.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				SuaBaiHat suaBaiHat = new SuaBaiHat();
			}
		});

		jButton_tk.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (!jTextField_tk.getText().isEmpty()) {
					ThongTin thongTin = new ThongTin();
				} else {
					JOptionPane.showMessageDialog(null, "Mời nhập thông tin bài hát!");
				}
			}
		});
		jButton_xoa.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (id == null) {
					JOptionPane.showMessageDialog(null, "Xin hãy chọn bài hát");
				} else {
					try {
						Class.forName("com.mysql.cj.jdbc.Driver");
						Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dack", "root", "");
						Statement sttm = conn.createStatement();
						int ret = JOptionPane.showConfirmDialog(null, "Xác Nhận Xóa?", "Xóa",
								JOptionPane.YES_NO_OPTION);
						if (ret == JOptionPane.YES_OPTION) {
							sttm.executeUpdate("delete from songlist where ID ='" + id + "'");
							JOptionPane.showMessageDialog(null, "Xóa thành công");
						}

					} catch (Exception e2) {
						// TODO: handle exception
						e2.printStackTrace();
					}
				}
			}
		});
		

		con.setLayout(new BoxLayout(con, BoxLayout.Y_AXIS));
		con.add(jPanel_tren);
		con.add(scb);
		con.add(jPanel_bt);

		this.setVisible(true);
	}

	public static void main(String[] args) {
		new DanhSach();
	}

}
