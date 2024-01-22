package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.net.URL;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TuCach extends JFrame {
	public TuCach() {
		this.setTitle("Tư Cách Của Bạn");
		this.setSize(450, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);

		URL urlTc = DanhSach.class.getResource("hoitucach.png");
		Image img = Toolkit.getDefaultToolkit().createImage(urlTc);
		this.setIconImage(img);
		Font font_tc = new Font("Times New Roman", Font.BOLD, 22);
		JLabel jLabel_tc = new JLabel("Bạn đăng nhập với tư cách? ", JLabel.CENTER);
		jLabel_tc.setFont(font_tc);
		jLabel_tc.setForeground(Color.RED);
		JPanel jPanel_tc = new JPanel();
		jPanel_tc.setLayout(new BorderLayout());
		jPanel_tc.setBackground(Color.cyan);
		jPanel_tc.add(jLabel_tc, BorderLayout.CENTER);
		JPanel jPanel_bttc = new JPanel();
		jPanel_bttc.setLayout(new FlowLayout());
		JButton jButton_ngdung = new JButton("Người Dùng");
		jButton_ngdung
				.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(TuCach.class.getResource("user.png"))));
		JButton jButton_qly = new JButton("Quản Lý");
		jButton_qly.setIcon(
				new ImageIcon(Toolkit.getDefaultToolkit().createImage(TuCach.class.getResource("manager.png"))));
		jPanel_bttc.add(jButton_ngdung);
		jPanel_bttc.add(jButton_qly);
		JPanel jPanel_pic = new JPanel();
		JLabel jLabel_pic = new JLabel();
		jLabel_pic.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(TuCach.class.getResource("tc.png"))));
		jPanel_pic.add(jLabel_pic);
		Container con_tc = getContentPane();
		con_tc.setLayout(new BoxLayout(con_tc, BoxLayout.Y_AXIS));
		con_tc.add(jPanel_tc);
		con_tc.add(jPanel_pic);
		con_tc.add(jPanel_bttc);

		jButton_ngdung.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
			//	TuCachUser tuCachUser = new TuCachUser();
				TimKiem timKiem = new TimKiem();
			}
		});
		jButton_qly.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				DangNhap dangNhap = new DangNhap();
			}
		});

		this.setVisible(true);
	}

	public static void main(String[] args) {
		new TuCach();
	}

}
