package GUI;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import dao.NhanVienDao;
import entity.NhanVien;


public class GD_NhanVienKeToan extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = -8429245150764495782L;
	public JFrame form_NhanVienKeToan;
	private JLabel lblChaoMung , lblTenNV;
	private JButton btnQLTaiKhoan,btnDangXuat,btnQLCN;
	private JMenuBar menuBar_1;
	private JMenu mnTinhLuong1;
	private JMenuItem mntmTinhLuongCN1;
	private JMenuItem mntmTinhLuongNV1;
	private JPanel panel_PhieuLuong;
	private JMenuBar menuBar;
	private JMenu mnPhieuLuong1;
	private JMenuItem mntmPhieuLuongNV;
	private JMenuItem mntmPhieuLuongCN;
	private JLabel lblNewLabel;
	private JLabel lblDate;
	private JLabel lblTime;
	private JButton btnThoat;
	private JLayeredPane layeredPane;
	private FrmTinhLuongNhanVien taoFrame1;
	
	public String maNv;
	private String name;
	private LocalDate date1;
	private String dateCurrently;
	private String timeStamp;
	private JButton btnCongDoan;
	private JPanel pnlTinhLuongNV;
	private FrmTinhLuongCongNhan taoFrame2;
	private JPanel pnlTinhLuongCN;
	private FrmPhieuLuongNhanVien taoFrame;
	private JPanel pnlPhieuLuong;
	private FrmPhieuLuongCongNhan taoFrame5;
	private JPanel pnlPhieuLuongCN;
	private FrmQuanLyNhanVien taoFrame3;
	private JPanel pnlQLCN;
	private FrmQuanLyCongDoan taoFrame6;
	private JPanel pnlQLCD;
	private FrmQuanLyTaiKhoan taoFrame4;
	private JPanel pnlQLTaiKhoan;
	private NhanVienDao nvDao;
	private List<NhanVien> dsNhanVien;
	
	public void switchPanels(JPanel panel) {
		layeredPane.removeAll();
		layeredPane.add(panel);
		layeredPane.repaint();
		layeredPane.revalidate();
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GD_NhanVienKeToan window = new GD_NhanVienKeToan("");
					window.form_NhanVienKeToan.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public GD_NhanVienKeToan(String ma) {
		this.maNv = ma;
		getContentPane().add(taoFormTrangChu());
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
	}

	private JPanel taoFormTrangChu() {
		
		form_NhanVienKeToan = new JFrame();
		form_NhanVienKeToan.setBounds(100, 100, 1938, 1069);
		form_NhanVienKeToan.setExtendedState(JFrame.MAXIMIZED_BOTH);
		form_NhanVienKeToan.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		form_NhanVienKeToan.getContentPane().setLayout(null);
		
		
		// Panel Header
//		JPanel panel_Header = new JPanel();
//		panel_Header.setBackground(new Color(0, 51, 102));
//		panel_Header.setBounds(0, 0, 1922, 67);
//		form_NhanVienKeToan.getContentPane().add(panel_Header);
//		panel_Header.setLayout(null);
		
//		lblChaoMung = new JLabel("Chào mừng:");
//		lblChaoMung.setFont(new Font("Verdana", Font.PLAIN, 16));
//		lblChaoMung.setForeground(Color.WHITE);
//		lblChaoMung.setBounds(1583, 21, 111, 22);
//		panel_Header.add(lblChaoMung);
//		
		
		
		
		//Panel Menu
		JPanel panel_Menu = new JPanel();
		panel_Menu.setBackground(new Color(0, 153, 255));
		panel_Menu.setBounds(0, 0, 203, 894);
		form_NhanVienKeToan.getContentPane().add(panel_Menu);
		panel_Menu.setLayout(null);
		
		
		btnCongDoan = new JButton("Công Đoạn");
		btnCongDoan.setIcon(new ImageIcon("img\\kyc.png"));
		btnCongDoan.setBackground(new java.awt.Color(58, 46, 130));
		btnCongDoan.setFont(new Font("Verdana", Font.PLAIN, 16));
		btnCongDoan.setForeground(new java.awt.Color(255, 255, 255));
		btnCongDoan.setBounds(0, 289, 203, 49);
		panel_Menu.add(btnCongDoan);
		
		
		btnQLCN = new JButton("Nhân Viên");
		btnQLCN.setIcon(new ImageIcon("img\\businesswoman.png"));
		btnQLCN.setBackground(new java.awt.Color(58, 46, 130));
		btnQLCN.setFont(new Font("Verdana", Font.PLAIN, 16));
		btnQLCN.setForeground(new java.awt.Color(255, 255, 255));
		btnQLCN.setBounds(0, 349, 203, 49);
		panel_Menu.add(btnQLCN);
		

		
		btnQLTaiKhoan = new JButton("Tài khoản ");
		btnQLTaiKhoan.setIcon(new ImageIcon("img\\skills.png"));
		btnQLTaiKhoan.setBackground(new java.awt.Color(58, 46, 130));
		btnQLTaiKhoan.setFont(new Font("Verdana", Font.PLAIN, 16));
		btnQLTaiKhoan.setForeground(new java.awt.Color(255, 255, 255));
		btnQLTaiKhoan.setBounds(0, 409, 203, 49);
		panel_Menu.add(btnQLTaiKhoan);
		
		JPanel panel_TinhLuong = new JPanel();
		panel_TinhLuong.setBackground(new java.awt.Color(58, 46, 130));
		panel_TinhLuong.setBounds(0, 229, 203, 49);
		panel_Menu.add(panel_TinhLuong);
		panel_TinhLuong.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		
		menuBar_1 = new JMenuBar();
		menuBar_1.setBackground(new java.awt.Color(58, 46, 130));
		panel_TinhLuong.add(menuBar_1);
		
		
		mnTinhLuong1 = new JMenu("Lương");
		mnTinhLuong1.setIcon(new ImageIcon("img\\accounts.png"));
		mnTinhLuong1.setForeground(Color.WHITE);
		mnTinhLuong1.setBackground(new java.awt.Color(58, 46, 130));
		mnTinhLuong1.setHorizontalAlignment(SwingConstants.LEFT);
		mnTinhLuong1.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		menuBar_1.add(mnTinhLuong1);
		
		mntmTinhLuongCN1 = new JMenuItem("Lương Công Nhân");
		mntmTinhLuongCN1.setIcon(new ImageIcon("img\\construction-worker.png"));
		mntmTinhLuongCN1.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mnTinhLuong1.add(mntmTinhLuongCN1);
		
		mntmTinhLuongNV1 = new JMenuItem("Lương Nhân Viên");
		mntmTinhLuongNV1.setIcon(new ImageIcon("img\\businesswoman.png"));
		mntmTinhLuongNV1.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mnTinhLuong1.add(mntmTinhLuongNV1);
		
		panel_PhieuLuong = new JPanel();
		panel_PhieuLuong.setBackground(new java.awt.Color(58, 46, 130));
		panel_PhieuLuong.setBounds(0, 169, 203, 49);
		panel_Menu.add(panel_PhieuLuong);
		panel_PhieuLuong.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		menuBar = new JMenuBar();
		menuBar.setBackground(new java.awt.Color(58, 46, 130));
		menuBar.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		panel_PhieuLuong.add(menuBar);
		
		mnPhieuLuong1 = new JMenu("Phiếu Lương");
		mnPhieuLuong1.setIcon(new ImageIcon("img\\receipt.png"));
		mnPhieuLuong1.setForeground(new Color(255, 255, 255));
		mnPhieuLuong1.setBackground(new java.awt.Color(58, 46, 130));
		mnPhieuLuong1.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		menuBar.add(mnPhieuLuong1);
		
		mntmPhieuLuongNV = new JMenuItem("Phiếu Lương NV");
		mntmPhieuLuongNV.setIcon(new ImageIcon("img\\businesswoman.png"));
		mntmPhieuLuongNV.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mnPhieuLuong1.add(mntmPhieuLuongNV);
		
		mntmPhieuLuongCN = new JMenuItem("Phiếu Lương CN");
		mntmPhieuLuongCN.setIcon(new ImageIcon("img\\construction-worker.png"));
		mntmPhieuLuongCN.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mnPhieuLuong1.add(mntmPhieuLuongCN);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("img\\logo.png"));
		lblNewLabel.setBounds(6, 10, 196, 150);
		panel_Menu.add(lblNewLabel);
		
		lblTenNV = new JLabel();
		lblTenNV.setForeground(Color.RED);
		lblTenNV.setFont(new Font("Verdana", Font.BOLD, 16));
		lblTenNV.setBounds(10, 480, 200, 22);
		panel_Menu.add(lblTenNV);
		
		lblDate = new JLabel("");
		lblDate.setHorizontalAlignment(SwingConstants.CENTER);
		lblDate.setForeground(Color.WHITE);
		lblDate.setFont(new Font("Verdana", Font.BOLD, 18));
		lblDate.setBounds(10, 540, 183, 47);
		panel_Menu.add(lblDate);
		
		lblTime = new JLabel("");
		lblTime.setHorizontalAlignment(SwingConstants.CENTER);
		lblTime.setForeground(Color.WHITE);
		lblTime.setFont(new Font("Verdana", Font.BOLD, 18));
		lblTime.setBounds(10, 580, 183, 49);
		panel_Menu.add(lblTime);
		
		
	
		
		
		
		
		
		//Panel footer
		
		
//		btnDangXuat = new JButton("Đăng Xuất");
//		btnDangXuat.setIcon(new ImageIcon("img\\logout.png"));
//		btnDangXuat.setForeground(Color.WHITE);
//		btnDangXuat.setFont(new Font("Verdana", Font.PLAIN, 16));
//		btnDangXuat.setBackground(Color.RED);
//		btnDangXuat.setBounds(0, 800, 175, 41);
//		panel_Menu.add(btnDangXuat);
		
		btnDangXuat = new JButton("Đăng xuất");
		btnDangXuat.setIcon(new ImageIcon("img\\logout.png"));
		btnDangXuat.setBackground(new java.awt.Color(58, 46, 130));
		btnDangXuat.setFont(new Font("Verdana", Font.PLAIN, 16));
		btnDangXuat.setForeground(new java.awt.Color(255, 255, 255));
		btnDangXuat.setBounds(0, 640, 203, 49);
		panel_Menu.add(btnDangXuat);
		
		btnThoat = new JButton("Thoát");
		btnThoat.setIcon(new ImageIcon("img\\—Pngtree—shut down vector icon_4016043.png"));
		btnThoat.setBackground(new java.awt.Color(58, 46, 130));
		btnThoat.setFont(new Font("Verdana", Font.PLAIN, 16));
		btnThoat.setForeground(new java.awt.Color(255, 255, 255));
		btnThoat.setBounds(0, 694, 203, 49);
		panel_Menu.add(btnThoat);
		
		
		layeredPane = new JLayeredPane();
		layeredPane.setBackground(new Color(0, 51, 255));
		layeredPane.setBounds(201, 0, 1200, 800);
		form_NhanVienKeToan.getContentPane().add(layeredPane);
		layeredPane.setLayout(new CardLayout(0, 0));
		
		
		
		/// Tạo Frame tính lương nhân viên
			taoFrame1 = new FrmTinhLuongNhanVien(this.maNv);
			pnlTinhLuongNV = taoFrame1.taoFrame_TinhLuongNV();
			layeredPane.add(pnlTinhLuongNV);
			mntmTinhLuongNV1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
				switchPanels(pnlTinhLuongNV);
			}
		});
		
			
		// Tạo Frame tính lương công nhân
		taoFrame2 = new FrmTinhLuongCongNhan(this.maNv);
		pnlTinhLuongCN = taoFrame2.taoFrame_TinhLuongCN();
		layeredPane.add(pnlTinhLuongCN);
		mntmTinhLuongCN1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				switchPanels(pnlTinhLuongCN);
			}
		});
		
		//Tạo Frame quản lý phiếu lương nhân viên
		taoFrame = new FrmPhieuLuongNhanVien(this.maNv);
		pnlPhieuLuong = taoFrame.taoFrame_PhieuLuong();
		layeredPane.add(pnlPhieuLuong);
		mntmPhieuLuongNV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				switchPanels(pnlPhieuLuong);
				
			}
		});

//		/Tạo frame phiếu lương cn
		
		taoFrame5 = new FrmPhieuLuongCongNhan(this.maNv);
		pnlPhieuLuongCN = taoFrame5.taoFrame_PhieuLuong();
		layeredPane.add(pnlPhieuLuongCN);
		mntmPhieuLuongCN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				switchPanels(pnlPhieuLuongCN);
				
			}
		});
		
		
		//Tạo Frame Quản lý công nhân
		taoFrame3 = new FrmQuanLyNhanVien();
		pnlQLCN = taoFrame3.taoFrame_QLCN();
		layeredPane.add(pnlQLCN);
		btnQLCN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				switchPanels(pnlQLCN);
				
			}
		});
		
		taoFrame6 = new FrmQuanLyCongDoan();
		pnlQLCD = taoFrame6.taoFrame_QLCD();
		layeredPane.add(pnlQLCD);
		btnCongDoan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				switchPanels(pnlQLCD);
				
			}
		});
		

		
		//tạo frame Quản lý tài khoản
		taoFrame4 = new FrmQuanLyTaiKhoan();
		pnlQLTaiKhoan = taoFrame4.taoFrame_QLTaiKhoan();
		layeredPane.add(pnlQLTaiKhoan);
		btnQLTaiKhoan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				switchPanels(pnlQLTaiKhoan);
				
			}
		});
		
		/*
		 * Xử lí
		 */	
		btnThoat.addActionListener(this);
		btnDangXuat.addActionListener(this);
		
		//Show tên nhân viên
		nvDao = new NhanVienDao();
		dsNhanVien = nvDao.getAllNV();
		for(NhanVien nv: dsNhanVien) {
			if(nv.getMaNhanVien().equals(this.maNv))
				name = nv.getTenNhanVien();
		}
		
		lblTenNV.setText("NV : "+name);
//		
		//Show ngày và giờ hiện tại
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		date1 = LocalDate.now();
		dateCurrently = dtf.format(date1);
		
		lblDate.setText(dateCurrently);
		initClock();
		
		JPanel panel = new JPanel();
		return panel;
		//pack
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
				form_NhanVienKeToan.setVisible(false);
				FrmLogin login = new FrmLogin();
				login.setVisible(true);
			}
		}
		
	}
	
	public void initClock()	{
		ClockThread th = new ClockThread(lblTime);
		th.start();
	}
}	
