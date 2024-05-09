package GUI;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import dao.NhanVienDao;
import entity.NhanVien;

public class GD_NhanVien extends JFrame implements ActionListener{
	
	JFrame from_NhanVien;
	public JLayeredPane layeredPane;
	private JLayeredPane layeredPane_1;
	private JButton  btnXemThongTinCN, btnQLTaiKhoan;
	private FrmXemPhieuLuong taoFrame;
	private JPanel pnlXemPhieuLuong;
	private FrmThongTinNhanVien taoFrame3;
	private JPanel pnlThongTinNV;
	private JLabel lblNewLabel;
	private String name;
	private String tenNhanVien;
	
	private NhanVienDao nvDao;
	private List<NhanVien> dsNhanVien;
	private JButton btnThoat;
	private JButton btnDangXuat;
	private JLabel lblTenNV;
	private JLabel lblDate;
	private JLabel lblTime;
	private LocalDate date1;
	private String dateCurrently;
	public void switchPanels(JPanel panel) {
		layeredPane.removeAll();
		layeredPane.add(panel);
		layeredPane.repaint();
		layeredPane.revalidate();
	}
	
	
	
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					GD_NhanVien window = new GD_NhanVien("");
//					window.from_NhanVien.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
	public static void main(String[] args) {
		GD_NhanVien window = new GD_NhanVien("");
		window.from_NhanVien.setVisible(true);
	}

	/**
	 * Create the application.
	 */
//	public GD_NhanVien() {
//		getContentPane().add(taoFormTrangChu());
//	}

	public GD_NhanVien(String text) {
		// TODO Auto-generated constructor stub
		this.name = text;
		taoFormTrangChu(name);
		getContentPane().add(taoFormTrangChu(name));
	}



	/**
	 * Initialize the contents of the frame.
	 */
	public JPanel taoFormTrangChu(String text) {
		from_NhanVien = new JFrame();
		from_NhanVien.setBounds(100, 100, 1938, 1069);
		from_NhanVien.setExtendedState(JFrame.MAXIMIZED_BOTH);
		from_NhanVien.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		from_NhanVien.getContentPane().setLayout(null);
		
		
		// Panel Header
//		JPanel panel_Header = new JPanel();
//		panel_Header.setBackground(new Color(0, 51, 102));
//		panel_Header.setBounds(0, 0, 1922, 67);
//		from_NhanVien.getContentPane().add(panel_Header);
//		panel_Header.setLayout(null);
//		
//		JLabel lblChaoMung = new JLabel("Chào mừng:");
//		lblChaoMung.setForeground(Color.WHITE);
//		lblChaoMung.setFont(new Font("Verdana", Font.PLAIN, 16));
//		lblChaoMung.setBounds(1607, 23, 111, 22);
//		panel_Header.add(lblChaoMung);
//		
		
		
		
		//Panel Menu
		JPanel panel_Menu = new JPanel();
		panel_Menu.setBackground(new Color(0, 153, 255));
		panel_Menu.setBounds(0, 0, 200, 800);
		from_NhanVien.getContentPane().add(panel_Menu);
		panel_Menu.setLayout(null);
		
		lblTenNV = new JLabel(name);
		lblTenNV.setForeground(Color.WHITE);
		lblTenNV.setFont(new Font("Verdana", Font.BOLD, 16));
		lblTenNV.setBounds(10, 360, 200, 22);
		panel_Menu.add(lblTenNV);
		
		
		
		btnXemThongTinCN = new JButton("Thông Tin NV");
		btnXemThongTinCN.setBackground(new java.awt.Color(58, 46, 130));
		btnXemThongTinCN.setFont(new Font("Verdana", Font.PLAIN, 16));
		btnXemThongTinCN.setForeground(new java.awt.Color(255, 255, 255));
		btnXemThongTinCN.setBounds(10, 208, 183, 49);
		panel_Menu.add(btnXemThongTinCN);
		

		
		btnQLTaiKhoan = new JButton("Phiếu Lương");
		btnQLTaiKhoan.setBackground(new java.awt.Color(58, 46, 130));
		btnQLTaiKhoan.setFont(new Font("Verdana", Font.PLAIN, 16));
		btnQLTaiKhoan.setForeground(new java.awt.Color(255, 255, 255));
		btnQLTaiKhoan.setBounds(10, 270, 183, 49);
		panel_Menu.add(btnQLTaiKhoan);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("img\\logo.png"));
		lblNewLabel.setBounds(4, 10, 196, 150);
		panel_Menu.add(lblNewLabel);
		
		lblDate = new JLabel();
		lblDate.setHorizontalAlignment(SwingConstants.CENTER);
		lblDate.setForeground(Color.WHITE);
		lblDate.setFont(new Font("Verdana", Font.BOLD, 18));
		lblDate.setBounds(10, 420, 183, 47);
		panel_Menu.add(lblDate);
		
		lblTime = new JLabel();
		lblTime.setHorizontalAlignment(SwingConstants.CENTER);
		lblTime.setForeground(Color.WHITE);
		lblTime.setFont(new Font("Verdana", Font.BOLD, 18));
		lblTime.setBounds(10, 500, 183, 49);
		panel_Menu.add(lblTime);
		
		
		//Panel footer
		
		layeredPane = new JLayeredPane();
		layeredPane.setBackground(new Color(0, 51, 255));
		layeredPane.setBounds(201, 0, 1200, 800);
		from_NhanVien.getContentPane().add(layeredPane);
		layeredPane.setLayout(new CardLayout(0, 0));
		
		btnDangXuat = new JButton("Đăng xuất");
		btnDangXuat.setIcon(new ImageIcon("img\\logout.png"));
		btnDangXuat.setBackground(new java.awt.Color(58, 46, 130));
		btnDangXuat.setFont(new Font("Verdana", Font.PLAIN, 16));
		btnDangXuat.setForeground(new java.awt.Color(255, 255, 255));
		btnDangXuat.setBounds(10, 600, 183, 49);
		panel_Menu.add(btnDangXuat);
		
		btnThoat = new JButton("Thoát");
		btnThoat.setIcon(new ImageIcon("img\\—Pngtree—shut down vector icon_4016043.png"));
		btnThoat.setBackground(new java.awt.Color(58, 46, 130));
		btnThoat.setFont(new Font("Verdana", Font.PLAIN, 16));
		btnThoat.setForeground(new java.awt.Color(255, 255, 255));
		btnThoat.setBounds(10, 674, 183, 49);
		panel_Menu.add(btnThoat);
		
		///Tạo Frame Quản lý công nhân
		taoFrame3 = new FrmThongTinNhanVien(this.name);
		pnlThongTinNV = taoFrame3.taoFrame_ThongTinNV(name);
		layeredPane.add(pnlThongTinNV);
		btnXemThongTinCN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				switchPanels(pnlThongTinNV);
				
			}
		});
		
		///Tạo Frame quản lý phiếu lương
		taoFrame = new FrmXemPhieuLuong(this.name);
		pnlXemPhieuLuong = taoFrame.taoFrame_XemPhieuLuong(this.name);
		layeredPane.add(pnlXemPhieuLuong);
		btnQLTaiKhoan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				switchPanels(pnlXemPhieuLuong);
				
			}
		});
	
		//Show tên nhân viên
		nvDao = new NhanVienDao();
		dsNhanVien = nvDao.getAllNV();
		for(NhanVien nv: dsNhanVien) {
			if(nv.getMaNhanVien().equals(text))
				tenNhanVien = nv.getTenNhanVien();
		}
		lblTenNV.setText("CN : "+tenNhanVien);
		
		//Show ngày và giờ hiện tại
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		date1 = LocalDate.now();
		dateCurrently = dtf.format(date1);
		lblDate.setText(dateCurrently);
		initClock();
	
		btnDangXuat.addActionListener(this);
		btnThoat.addActionListener(this);
		
		JPanel panel = new JPanel();
		
		btnThoat.addActionListener(this);
		btnDangXuat.addActionListener(this);
		return panel;
	}
	
	public void initClock()	{
		ClockThread th = new ClockThread(lblTime);
		th.start();
	}
	
	private void send(String text) {
		this.name = text;
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if(o.equals(btnThoat)) {
			int n = JOptionPane.showConfirmDialog(null, "Bạn có thật sự muốn thoát?", "Cảnh báo",
					JOptionPane.YES_NO_OPTION);
			if (n == 0) {
				System.exit(0);
			}
		}
		else if(o.equals(btnDangXuat)) {
			int n = JOptionPane.showConfirmDialog(null, "Bạn có thật sự muốn đăng xuất?", "Cảnh báo",
					JOptionPane.YES_NO_OPTION);
			if (n == 0) {
				from_NhanVien.setVisible(false);
				FrmLogin login = new FrmLogin();
				login.setVisible(true);
			}
		}
	}
}
