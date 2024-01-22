package View;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TuCachUser extends JFrame{
	public TuCachUser() {
		// TODO Auto-generated constructor stub
		this.setSize(300, 200);
		this.setLocationRelativeTo(null);
		
		JLabel jLabe_title = new JLabel("Bạn tiếp tục với tư cách: ", JLabel.CENTER);
		JPanel jPanel_btn = new JPanel();
		jPanel_btn.setLayout(new FlowLayout());
		JButton jButton_khach = new JButton("Khách");
		JButton jButton_dn = new JButton("Đăng Nhập");
		jPanel_btn.add(jButton_khach);
		jPanel_btn.add(jButton_dn);
		
		JLabel jLabel_taotk = new JLabel("Bạn chưa có tài khoản ->");
		JButton jButton_taotk = new JButton("Tạo Tài Khoản");
		JPanel jPanel_taotk = new JPanel();
		jPanel_taotk.setLayout(new FlowLayout());
		jPanel_taotk.add(jLabel_taotk);
		jPanel_taotk.add(jButton_taotk);
		
		
		Container cont = getContentPane();
		cont.setLayout(new GridLayout(3,1));
		cont.add(jLabe_title);
		cont.add(jPanel_btn);
		cont.add(jPanel_taotk);
		
		jButton_khach.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				TimKiem timKiem = new TimKiem();
			}
		});
		jButton_dn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				DangNhapUser dangNhapUser = new DangNhapUser();
				
			}
		});
		
		this.setVisible(true);
	}
public static void main(String[] args) {
	new TuCachUser();
}
}
