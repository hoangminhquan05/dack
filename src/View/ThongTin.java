package View;

import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ThongTin extends JFrame {
	public ThongTin() {
		// TODO Auto-generated constructor stub
		this.setTitle("Thông Tin");
		this.setSize(800, 500);
		this.setLocationRelativeTo(null);
		Font font = new Font("Arial", Font.BOLD, 20);
		JLabel jLabel_tieude = new JLabel("Thông Tin Bài Hát", JLabel.CENTER);
		jLabel_tieude.setFont(font);
		jLabel_tieude.setForeground(Color.RED);

		DefaultTableModel dtm = new DefaultTableModel();
		dtm.addColumn("ID");
		dtm.addColumn("Tên Bài Hát");
		dtm.addColumn("Ca Sĩ");
		dtm.addColumn("Sáng Tác");
		dtm.addColumn("Năm Phát Hành");
		dtm.addColumn("Thể Loại");
		JTable tbl = new JTable(dtm);
		JScrollPane scb = new JScrollPane(tbl);
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dack", "root", "");
			PreparedStatement ps = conn.prepareStatement("Select * from songlist where `Tên Bài Hát`='"+DanhSach.jTextField_tk.getText()+"' or `Ca Sĩ`='"+DanhSach.jTextField_tk.getText()+"' or `Sáng Tác`='"+DanhSach.jTextField_tk.getText()+"' or `Năm Phát Hành`='"+DanhSach.jTextField_tk.getText()+"' or `Thể Loại`='"+DanhSach.jTextField_tk.getText()+"' or ID='"+DanhSach.jTextField_tk.getText()+"'");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				String id = rs.getString("ID");
				String tenbh = rs.getString("Tên Bài Hát");
				String cs = rs.getString("Ca Sĩ");
				String st = rs.getString("Sáng Tác");
				String nph = rs.getString("Năm Phát Hành");
				String tl = rs.getString("Thể Loại");
				dtm.addRow(new String[] {id,tenbh,cs,st,nph,tl});
			}ps.close();
			conn.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
		
		this.setVisible(true);
		this.add(jLabel_tieude, "North");
		this.add(scb, "Center");
	}
public static void main(String[] args) {
	new ThongTin();
}
}
