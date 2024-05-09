package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.EventObject;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import javax.swing.AbstractButton;
import javax.swing.DefaultComboBoxModel;
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
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import dao.CongDoanDao;
import dao.LuongDao;
import dao.NhanVienDao;
import dao.SanPhamDao;
import entity.CongDoan;
import entity.Luong;
import entity.NgayNghi;
import entity.NhanVien;
import entity.SanPham;


public class FrmTinhLuongCongNhan extends JFrame implements MouseListener, ActionListener {
	private String result;
	private JPanel panel_TinhLuongCN;
	private JTextField txtNVkeToan;
	private JTextField txtMaCN;
	private JTextField txtHoTenCN;
	private JLabel lblSoSanPham;
	private JTextField txtSoSPHoanThien;
	private JLabel lblSoSanPhamTangCa;
	private JTextField txtSoSanPhamTangCa;
	private JTextField txtTongTien;
	private JTextField txtNgayTinhLuong;
	private JTextField txtHeSoLuong;
	private JButton btnTinh;
	private JButton btnLuu;
	private AbstractButton btnLamMoi;
	private JTextField txtMaSP;
	private JTextField txtTenSP;
	private JLabel lblTenCongDoan;
	private JComboBox cmbTenCongDoan;
	private JTextField txtChiTieu;
	private JLabel lblDonGia;
	private JTextField txtDonGia;
	private JLabel lbDonGiaTangCa;
	private JTextField txtDonGiaTangCa;
	private DefaultTableModel modelHoaDon;
	private JTable table;
	private JComboBox cmbTimKiem;
	private JButton btnTim;
	private JTextField txtTimKiem;
	private NhanVienDao nvDao;
	private CongDoanDao cdDao;
	private SanPhamDao spDao;
	private LuongDao luongDao;
	private List<SanPham> listSp;
	private List<NhanVien> listNV;
	private LocalDate date1;
	private String dateCurrently;
	private List<NhanVien> dsNv;
	private double tongTien;
	private List<Luong> dsLuong;
	
	
	public FrmTinhLuongCongNhan(String ma) { 
		this.result = ma;
		getContentPane().add(taoFrame_TinhLuongCN());
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setSize(1200,800);
	}
	
	
	public static void main(String[] args) {
		new FrmTinhLuongCongNhan("").setVisible(true);
	}

	
	public JPanel taoFrame_TinhLuongCN() {
		panel_TinhLuongCN = new JPanel();
		panel_TinhLuongCN.setBackground(new Color(147, 112, 219));
		panel_TinhLuongCN.setLayout(null);
		
		JPanel panel_TinhLuong = new JPanel();
		panel_TinhLuong.setBackground(new Color(102, 255, 255));
		panel_TinhLuong.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_TinhLuong.setBounds(10, 40, 320, 330);
		panel_TinhLuongCN.add(panel_TinhLuong);
		panel_TinhLuong.setLayout(null);
		
		JLabel lblNhnVinK_1 = new JLabel("Nhân viên kế toán:");
		lblNhnVinK_1.setFont(new Font("Verdana", Font.PLAIN, 14));
		lblNhnVinK_1.setBounds(10, 10, 190, 30);
		panel_TinhLuong.add(lblNhnVinK_1);
		
		txtNVkeToan = new JTextField();
		txtNVkeToan.setFont(new Font("Verdana", Font.PLAIN, 14));
		txtNVkeToan.setColumns(10);
		txtNVkeToan.setBounds(150, 12, 150, 26);
		panel_TinhLuong.add(txtNVkeToan);
		
		txtMaCN = new JTextField();
		txtMaCN.setFont(new Font("Verdana", Font.PLAIN, 14));
		txtMaCN.setColumns(10);
		txtMaCN.setBounds(150, 54, 150, 26);
		panel_TinhLuong.add(txtMaCN);
		
		txtHoTenCN = new JTextField();
		txtHoTenCN.setFont(new Font("Verdana", Font.PLAIN, 14));
		txtHoTenCN.setColumns(10);
		txtHoTenCN.setBounds(150, 94, 150, 26);
		panel_TinhLuong.add(txtHoTenCN);
		
		JLabel lbMaCongNhan_1 = new JLabel("Mã công nhân:");
		lbMaCongNhan_1.setFont(new Font("Verdana", Font.PLAIN, 14));
		lbMaCongNhan_1.setBounds(10, 48, 191, 37);
		panel_TinhLuong.add(lbMaCongNhan_1);
		
		JLabel lbHoTenCongNhan = new JLabel("Họ tên công nhân:");
		lbHoTenCongNhan.setFont(new Font("Verdana", Font.PLAIN, 14));
		lbHoTenCongNhan.setBounds(10, 86, 191, 37);
		panel_TinhLuong.add(lbHoTenCongNhan);
		
		JLabel lblHeSoLuong_TinhLuong_1 = new JLabel("Hệ số lương:");
		lblHeSoLuong_TinhLuong_1.setFont(new Font("Verdana", Font.PLAIN, 14));
		lblHeSoLuong_TinhLuong_1.setBounds(10, 124, 191, 37);
		panel_TinhLuong.add(lblHeSoLuong_TinhLuong_1);
		
		lblSoSanPham = new JLabel("Số sản phẩm:");
		lblSoSanPham.setFont(new Font("Verdana", Font.PLAIN, 14));
		lblSoSanPham.setBounds(10, 162, 213, 37);
		panel_TinhLuong.add(lblSoSanPham);
		
		txtSoSPHoanThien = new JTextField();
		txtSoSPHoanThien.setFont(new Font("Verdana", Font.PLAIN, 14));
		txtSoSPHoanThien.setColumns(10);
		txtSoSPHoanThien.setBounds(150, 170, 150, 26);
		panel_TinhLuong.add(txtSoSPHoanThien);
		
		lblSoSanPhamTangCa = new JLabel("Số SP tăng ca:");
		lblSoSanPhamTangCa.setFont(new Font("Verdana", Font.PLAIN, 14));
		lblSoSanPhamTangCa.setBounds(10, 200, 213, 37);
		panel_TinhLuong.add(lblSoSanPhamTangCa);
		
		txtSoSanPhamTangCa = new JTextField();
		txtSoSanPhamTangCa.setFont(new Font("Verdana", Font.PLAIN, 14));
		txtSoSanPhamTangCa.setColumns(10);
		txtSoSanPhamTangCa.setBounds(150, 206, 150, 26);
		panel_TinhLuong.add(txtSoSanPhamTangCa);
		
		
		JLabel lblNgayTao_1 = new JLabel("Ngày tính lương:");
		lblNgayTao_1.setFont(new Font("Verdana", Font.PLAIN, 14));
		lblNgayTao_1.setBounds(10, 238, 191, 37);
		panel_TinhLuong.add(lblNgayTao_1);
		
		
		JLabel lbTongTien_1 = new JLabel("Tổng Lương:");
		lbTongTien_1.setFont(new Font("Verdana", Font.BOLD, 16));
		lbTongTien_1.setBounds(10, 270, 191, 54);
		panel_TinhLuong.add(lbTongTien_1);
		
		txtTongTien = new JTextField();
		txtTongTien.setForeground(new Color(255, 0, 0));
		txtTongTien.setFont(new Font("Verdana", Font.BOLD, 20));
		txtTongTien.setEditable(false);
		txtTongTien.setColumns(10);
//		txtTongTien.setBorder(new EmptyBorder(0, 10, 0, 0));
		txtTongTien.setBackground(new Color(102, 255, 255));
		txtTongTien.setBounds(150, 280, 150, 36);
		panel_TinhLuong.add(txtTongTien);
		
		txtNgayTinhLuong = new JTextField();
		txtNgayTinhLuong.setEditable(false);
		txtNgayTinhLuong.setBounds(150, 244, 150, 26);
		panel_TinhLuong.add(txtNgayTinhLuong);
		txtNgayTinhLuong.setColumns(10);
		
		txtHeSoLuong = new JTextField();
		txtHeSoLuong.setFont(new Font("Verdana", Font.PLAIN, 14));
		txtHeSoLuong.setColumns(10);
		txtHeSoLuong.setBounds(150, 130, 150, 26);
		panel_TinhLuong.add(txtHeSoLuong);
		
//		JLabel lbTongTien_1_1 = new JLabel("VND");
//		lbTongTien_1_1.setFont(new Font("Verdana", Font.BOLD, 16));
//		lbTongTien_1_1.setBounds(375, 332, 49, 54);
//		panel_TinhLuong.add(lbTongTien_1_1);
		
		
		
		/////
		JLabel lbGiaoDIenTinhLuong_1 = new JLabel("Giao Diện Tính Lương Công Nhân");
		lbGiaoDIenTinhLuong_1.setFont(new Font("Verdana", Font.BOLD, 16));
		lbGiaoDIenTinhLuong_1.setBounds(10, 0, 341, 43);
		lbGiaoDIenTinhLuong_1.setForeground(Color.WHITE);
		panel_TinhLuongCN.add(lbGiaoDIenTinhLuong_1);
		
		JPanel panel_ChucNang = new JPanel();
		panel_ChucNang.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_ChucNang.setBackground(new Color(102, 255, 255));
		panel_ChucNang.setBounds(10, 380, 320, 70);
		panel_TinhLuongCN.add(panel_ChucNang);
		panel_ChucNang.setLayout(null);
		
		btnTinh = new JButton();
		btnTinh.setIcon(new ImageIcon("img\\accounts.png"));
		btnTinh.setForeground(new Color(255, 255, 255));
		btnTinh.setBackground(new java.awt.Color(58, 46, 130));
		btnTinh.setFont(new Font("Verdana", Font.PLAIN, 16));
		btnTinh.setBounds(10, 10, 70, 50);
		panel_ChucNang.add(btnTinh);
		
		btnLuu = new JButton();
		btnLuu.setIcon(new ImageIcon("img\\diskette.png"));
		btnLuu.setBackground(new java.awt.Color(58, 46, 130));
		btnLuu.setForeground(new Color(255, 255, 255));
		btnLuu.setFont(new Font("Verdana", Font.PLAIN, 16));
		btnLuu.setBounds(240, 10, 70, 50);
		panel_ChucNang.add(btnLuu);
		
		btnLamMoi = new JButton();
		btnLamMoi.setIcon(new ImageIcon("img\\refresh.png"));
		btnLamMoi.setForeground(Color.WHITE);
		btnLamMoi.setFont(new Font("Verdana", Font.PLAIN, 16));
		btnLamMoi.setBackground(new Color(58, 46, 130));
		btnLamMoi.setBounds(126, 10, 70, 50);
		panel_ChucNang.add(btnLamMoi);
		
		JPanel panel_SanPham = new JPanel();
		panel_SanPham.setBackground(new Color(102, 255, 255));
		panel_SanPham.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_SanPham.setBounds(10, 460, 320, 270);
		panel_TinhLuongCN.add(panel_SanPham);
		panel_SanPham.setLayout(null);
		
//		JLabel lbSanPham = new JLabel("Sản Phẩm");
//		lbSanPham.setFont(new Font("Verdana", Font.BOLD, 16));
//		lbSanPham.setBounds(10, 11, 291, 43);
//		panel_SanPham.add(lbSanPham);
		
		JLabel lbMaSP = new JLabel("Mã sản phẩm:");
		lbMaSP.setFont(new Font("Verdana", Font.PLAIN, 14));
		lbMaSP.setBounds(10, 10, 191, 37);
		panel_SanPham.add(lbMaSP);
		
		txtMaSP = new JTextField();
		txtMaSP.setEditable(false);
		txtMaSP.setFont(new Font("Verdana", Font.PLAIN, 14));
		txtMaSP.setColumns(10);
		txtMaSP.setBounds(130, 12, 170, 30);
		panel_SanPham.add(txtMaSP);
		
		JLabel lblTenSP = new JLabel("Tên sản phẩm:");
		lblTenSP.setFont(new Font("Verdana", Font.PLAIN, 14));
		lblTenSP.setBounds(10, 50, 191, 37);
		panel_SanPham.add(lblTenSP);
		
		txtTenSP = new JTextField();
		txtTenSP.setEditable(false);
		txtTenSP.setFont(new Font("Verdana", Font.PLAIN, 14));
		txtTenSP.setColumns(10);
		txtTenSP.setBounds(130, 52, 170, 30);
		panel_SanPham.add(txtTenSP);
		
		//title
//		JLabel lbCongDoan = new JLabel("Công Đoạn");
//		lbCongDoan.setFont(new Font("Verdana", Font.BOLD, 16));
//		lbCongDoan.setBounds(10, 121, 291, 43);
//		panel_SanPham.add(lbCongDoan);
		
		
		lblTenCongDoan = new JLabel("Tên công đoạn:");
		lblTenCongDoan.setFont(new Font("Verdana", Font.PLAIN, 14));
		lblTenCongDoan.setBounds(10, 90, 191, 37);
		panel_SanPham.add(lblTenCongDoan);
		
        String congDoan[] = { "-Công Đoạn-", "HoanThienSanPham", "SanXuatGiayDay",
        		"SanXuatDeGiay", "SanXuatThanGiay"}; 
		cmbTenCongDoan = new JComboBox(congDoan);
		cmbTenCongDoan.setEditable(false);
		cmbTenCongDoan.setFont(new Font("Verdana", Font.PLAIN, 14));
		cmbTenCongDoan.setBackground(new Color(255,255,255));
		cmbTenCongDoan.setBounds(130, 92, 170, 30);
		panel_SanPham.add(cmbTenCongDoan);
		
		JLabel lblSoNgayLamViec_1 = new JLabel("Chỉ Tiêu:");
		lblSoNgayLamViec_1.setFont(new Font("Verdana", Font.PLAIN, 14));
		lblSoNgayLamViec_1.setBounds(10, 130, 191, 37);
		panel_SanPham.add(lblSoNgayLamViec_1);
		
		txtChiTieu = new JTextField();
		txtChiTieu.setEditable(false);
		txtChiTieu.setFont(new Font("Verdana", Font.PLAIN, 14));
		txtChiTieu.setColumns(10);
		txtChiTieu.setBounds(130, 132, 170, 30);
		panel_SanPham.add(txtChiTieu);
		

		lblDonGia = new JLabel("Đơn giá:");
		lblDonGia.setFont(new Font("Verdana", Font.PLAIN, 14));
		lblDonGia.setBounds(10, 170, 191, 37);
		panel_SanPham.add(lblDonGia);
		
		txtDonGia = new JTextField();
		txtDonGia.setEditable(false);
		txtDonGia.setFont(new Font("Verdana", Font.PLAIN, 14));
		txtDonGia.setColumns(10);
		txtDonGia.setBounds(130, 172, 170, 30);
		panel_SanPham.add(txtDonGia);

		
		lbDonGiaTangCa = new JLabel("Đơn giá tăng ca:");
		lbDonGiaTangCa.setFont(new Font("Verdana", Font.PLAIN, 14));
		lbDonGiaTangCa.setBounds(10, 210, 191, 37);
		panel_SanPham.add(lbDonGiaTangCa);
		
		txtDonGiaTangCa = new JTextField();
		txtDonGiaTangCa.setEditable(false);
		txtDonGiaTangCa.setFont(new Font("Verdana", Font.PLAIN, 14));
		txtDonGiaTangCa.setColumns(10);
		txtDonGiaTangCa.setBounds(130, 212, 170, 30);
		panel_SanPham.add(txtDonGiaTangCa);
		
		
		JPanel panel_BangTinhLuong = new JPanel();
		panel_BangTinhLuong.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_BangTinhLuong.setBackground(new Color(102, 255, 255));
		panel_BangTinhLuong.setBounds(340, 40, 810, 690);
		panel_TinhLuongCN.add(panel_BangTinhLuong);
		panel_BangTinhLuong.setLayout(null);
		
		/*
		 * Start table
		 */
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 60, 790, 620);
		panel_BangTinhLuong.add(scrollPane);
		String[] header = { "Mã nhân viên", "Họ Tên", "Giới tính", "Ngày sinh", "Bộ phận", "Ngày vào làm",
				"Ngoại ngữ", "Mã Sản phẩm"};
		modelHoaDon = new DefaultTableModel(header, 0);
		
		table = new JTable(modelHoaDon) {
			public boolean editCellAt(int row, int column, EventObject e) { // Không cho chỉnh sửa giá trị trong table
				return false;
			}
		};
		
		JTableHeader header1 = table.getTableHeader();
		header1.setBackground(new Color(23, 70, 138));
		header1.setForeground(Color.white);
		header1.setFont(new Font("Tahoma", Font.PLAIN, 15));

		table.setFont(new Font("Tahoma", Font.PLAIN, 15));
		scrollPane.setViewportView(table);
		table.setRowHeight(25);
		
		table.getColumnModel().getColumn(0).setPreferredWidth(120);
		table.getColumnModel().getColumn(1).setPreferredWidth(120);
		table.getColumnModel().getColumn(2).setPreferredWidth(70);
		table.getColumnModel().getColumn(3).setPreferredWidth(70);
		table.getColumnModel().getColumn(4).setPreferredWidth(70);
		table.getColumnModel().getColumn(5).setPreferredWidth(100);
		table.getColumnModel().getColumn(6).setPreferredWidth(100);
		table.getColumnModel().getColumn(7).setPreferredWidth(100);
		/*
		 * End table
		 */
		
		cmbTimKiem = new JComboBox();
		cmbTimKiem.setModel(new DefaultComboBoxModel(new String[] {"Tìm kiếm theo", "Mã công nhân", "Tên công nhân"}));
		cmbTimKiem.setFont(new Font("Verdana", Font.PLAIN, 16));
		cmbTimKiem.setBounds(10, 10, 154, 34);
		panel_BangTinhLuong.add(cmbTimKiem);
		
		
		btnTim = new JButton("Tìm kiếm");
		btnTim.setIcon(new ImageIcon("img\\search.png"));
		btnTim.setForeground(new Color(255, 255, 255));
		btnTim.setBackground(new java.awt.Color(58, 46, 130));
		btnTim.setFont(new Font("Verdana", Font.PLAIN, 14));
		btnTim.setBounds(650, 10, 150, 34);
		panel_BangTinhLuong.add(btnTim);
		
		txtTimKiem = new JTextField();
		txtTimKiem.setFont(new Font("Verdana", Font.PLAIN, 14));
		txtTimKiem.setColumns(10);
		txtTimKiem.setBounds(180, 10, 460, 34);
		panel_BangTinhLuong.add(txtTimKiem);
		
		/*
		 * Bắt đầu xử lí chức năng
		 */
//		txtMaSP.setText("G001");
//		txtTenSP.setText("Giày Sneaker");
		txtHeSoLuong.setEditable(false);
		txtNVkeToan.setEditable(false);
		
		nvDao = new NhanVienDao();
		cdDao = new CongDoanDao();
		spDao = new SanPhamDao();
		luongDao = new LuongDao();
		listSp = spDao.getAllSP();
		listNV = nvDao.getAllNV();
		docDuLieuDatabaseVaoTableHoaDon();
		
		//Bắt sự kiện khi select combobox công đoạn
		cmbTenCongDoan.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	CongDoan cd = new CongDoan();
		    	int index = cmbTenCongDoan.getSelectedIndex();
		        if(index == 0) {
		        	txtChiTieu.setText("");
		        	txtDonGia.setText("");
		        	txtDonGiaTangCa.setText("");
		        } else {
			        String value = (String) cmbTenCongDoan.getSelectedItem();
			        cd = cdDao.getCongDoan(value);
			        txtDonGia.setText(Float.toString(cd.getDonGia()));
			        txtDonGiaTangCa.setText(Float.toString(cd.getDonGiaTangCa()));
			        txtChiTieu.setText(Integer.toString(cd.getChiTieu()));
		        }
		    }
		});
		
		table.addMouseListener(this);
		btnTinh.addActionListener(this);
		btnLamMoi.addActionListener(this);
		btnLuu.addActionListener(this);
		btnTim.addActionListener(this);
		
		
		dsNv = nvDao.getAllNV();				
		for(NhanVien nv: dsNv) {
			if(nv.getMaNhanVien().equals(this.result)) {
				txtNVkeToan.setText(nv.getTenNhanVien());
			}
		}
		
		return panel_TinhLuongCN;
	}
	
	public void docDuLieuDatabaseVaoTableHoaDon() {
		modelHoaDon.getDataVector().removeAllElements();
		listNV = nvDao.getAllNV();
		for (NhanVien nv : listNV) {
			//Format ngay
	        Date date = Calendar.getInstance().getTime();  
	        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");  
	        String ngaySinh = dateFormat.format(nv.getNgaySinh());  
	        String ngayVaoLam = dateFormat.format(nv.getNgayVaoLam());
	        
	        //Get ngày hiện tại
			 DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			 date1 = LocalDate.now();
			 dateCurrently = dtf.format(date1);
				
			//Format Luong Thuong
//			DecimalFormat formatter = new DecimalFormat("###,###,###.00");
//			String luongThuong = formatter.format(nv.getTienThuong())+" VNĐ";
			 txtNgayTinhLuong.setText(dateCurrently);

	        if(nv.getMaNhanVien().charAt(0) == 'C') {
				modelHoaDon.addRow(new Object[] {nv.getMaNhanVien(), nv.getTenNhanVien(), nv.getGioiTinh()
				, ngaySinh, nv.getBoPhan(), ngayVaoLam, nv.getNgoaiNgu(), nv.getSanPham().getMaSanPham()});
	        }
		}
	}
	
	/*
	 * Check dữ liệu
	 */
	private boolean validTinhLuong() {
		if(txtMaCN.getText().equals("") || txtHoTenCN.getText().equals("")) {
			thongBao(txtNVkeToan, "Vui lòng chọn công nhân cần tính lương!");
			return false;
		}
		
		if(txtSoSPHoanThien.getText().equals("")) {
			thongBao(txtSoSPHoanThien, "Bạn chưa nhập số sản phẩm!");
			return false;
		}
		
		if(txtSoSanPhamTangCa.getText().equals("")) {
			thongBao(txtSoSanPhamTangCa, "Bạn chưa nhập số sản phẩm tăng ca!");
			return false;
		}
		
		try {
			int soSanPham = Integer.parseInt(txtSoSPHoanThien.getText());
		} catch (Exception e) {
			// TODO: handle exception
			thongBao(txtSoSPHoanThien, "Số sản phẩm hoàn thiện phải là số nguyên!");
			return false;
		}
		
		try {
			int soSanPhamTangCa = Integer.parseInt(txtSoSanPhamTangCa.getText());
		} catch (Exception e) {
			// TODO: handle exception
			thongBao(txtSoSanPhamTangCa, "Số sản phẩm tăng ca phải là số nguyên!");
			return false;
		}
		
		int index = cmbTenCongDoan.getSelectedIndex();
		if(index == 0) {
			thongBao(txtNVkeToan, "Bạn chưa chọn công đoạn!");
		}
		return true;
	}
	
	private void showData() {
		int selectedRow = table.getSelectedRow();
		txtMaCN.setText(modelHoaDon.getValueAt(selectedRow, 0).toString());
		txtHoTenCN.setText(modelHoaDon.getValueAt(selectedRow, 1).toString());
		txtMaSP.setText(modelHoaDon.getValueAt(selectedRow, 7).toString());
		listSp = spDao.getAllSP();
		for(SanPham sp: listSp) {
			txtTenSP.setText(sp.getTenSanPham());
		}
//        Tính năm làm
			long year = 0;
			SimpleDateFormat myFormat = new SimpleDateFormat("dd-MM-yyyy");
			String startDate = modelHoaDon.getValueAt(selectedRow, 5).toString();
			try {
			    Date date1 = myFormat.parse(startDate);
			    Date date2 = myFormat.parse(dateCurrently);
			    long diff = date2.getTime() - date1.getTime();
			    long temp = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
			    year = temp/365;
			} catch (ParseException e) {
			    e.printStackTrace();
			}
			
			
			//Tính hệ số lương
			int heSoLuong = 0;
			
			if(modelHoaDon.getValueAt(selectedRow, 6).toString().equals("Sơ Cấp")) {
				heSoLuong+=1;
			}
			
			if(modelHoaDon.getValueAt(selectedRow, 6).toString().equals("Trung Cấp")) {
				heSoLuong+=2;
			}
			
			if(modelHoaDon.getValueAt(selectedRow, 6).toString().equals("Cao Cấp")) {
				heSoLuong+=3;
			}
		
			if(year >= 10 && year < 20) {
				heSoLuong+=1;
			}

			if(year >= 20 && year < 30) {
				heSoLuong+=2;
			}
			
			if(year >= 30) {
				heSoLuong+=3;
			}
			
			float ketQuaHeSoLuong = 0;
			if(heSoLuong == 1) {
				ketQuaHeSoLuong = (float) 1.1;
			}
			
			if(heSoLuong == 2) {
				ketQuaHeSoLuong = (float) 1.2;
			}
			
			if(heSoLuong == 3) {
				ketQuaHeSoLuong = (float) 1.3;
			}
			
			if(heSoLuong == 4) {
				ketQuaHeSoLuong = (float) 1.4;
			}
			
			if(heSoLuong == 5) {
				ketQuaHeSoLuong = (float) 1.5;
			}
			
			if(heSoLuong == 6) {
				ketQuaHeSoLuong = (float) 1.6;
			}
			
			txtHeSoLuong.setText(Float.toString(ketQuaHeSoLuong));
	}
	
	
	/**
	 * Thông báo
	 */
	private void thongBao(JTextField tf, String mes) {
		JOptionPane.showMessageDialog(this, mes);
		tf.selectAll();
		tf.requestFocus();
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		showData();
	}


	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if(o.equals(btnLamMoi)) {
			txtMaCN.setText("");
			txtHoTenCN.setText("");
			txtHeSoLuong.setText("");
			txtSoSPHoanThien.setText("");
			txtChiTieu.setText("");
			txtNgayTinhLuong.setText("");
			txtDonGia.setText("");
			txtDonGiaTangCa.setText("");
			cmbTenCongDoan.setSelectedIndex(0);
			txtTongTien.setText("");
			txtSoSanPhamTangCa.setText("");
			docDuLieuDatabaseVaoTableHoaDon();
		}
		
		else if(o.equals(btnTinh)) {
			if(validTinhLuong()) {
				boolean check;
				int soSanPham = Integer.parseInt(txtSoSPHoanThien.getText());
				int soSanPhamTangCa = Integer.parseInt(txtSoSanPhamTangCa.getText());
				double heSoLuong = Float.parseFloat(txtHeSoLuong.getText());
				int chiTieu = Integer.parseInt(txtChiTieu.getText());
				double donGia = Float.parseFloat(txtDonGia.getText());
				double donGiaTangCa = Float.parseFloat(txtDonGiaTangCa.getText());
				
				if((soSanPham + soSanPhamTangCa) >= chiTieu) {
					check = true;
				} else {
					check = false;
				}
				
				if(check) {
					tongTien = (double) ((((donGia * soSanPham) + (donGiaTangCa * soSanPhamTangCa)) * heSoLuong) 
							+ ((((donGia * soSanPham) + (donGiaTangCa * soSanPhamTangCa)) * heSoLuong) * 5 / 100));
				} else {
					tongTien = (double) ((((donGia * soSanPham) + (donGiaTangCa * soSanPhamTangCa)) * heSoLuong) 
							- ((((donGia * soSanPham) + (donGiaTangCa * soSanPhamTangCa)) * heSoLuong) * 5 / 100));
				}
				DecimalFormat df = new DecimalFormat("###,###,###.#");
				Locale locale = new Locale("vn", "VN");  
				NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);
				String moneyString = formatter.format(tongTien);
				txtTongTien.setText(moneyString);
			}
		}
		
		else if(o.equals(btnTim)) {
			if(cmbTimKiem.getSelectedIndex() == 0) {
				docDuLieuDatabaseVaoTableHoaDon();
			}
			
			else if(cmbTimKiem.getSelectedIndex() == 1) {
				if(txtTimKiem.getText().equals("")) {
					JOptionPane.showMessageDialog(this, "Vui lòng nhập mã công nhân cần tìm!");
				}
				modelHoaDon.getDataVector().removeAllElements();
				int dem = 1;
				listNV = nvDao.getAllNV();
				for (NhanVien nv : listNV) {
					//Format ngay
			        Date date = Calendar.getInstance().getTime();  
			        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");  
			        String ngaySinh = dateFormat.format(nv.getNgaySinh());  
			        String ngayVaoLam = dateFormat.format(nv.getNgayVaoLam());
			        
			        //Get ngày hiện tại
					 DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
					 date1 = LocalDate.now();
					 dateCurrently = dtf.format(date1);
				
					 txtNgayTinhLuong.setText(dateCurrently);

				    if(nv.getMaNhanVien().charAt(0) == 'C') {
						String str = nv.getMaNhanVien();
						String [] array = str.trim().split(txtTimKiem.getText());
				    	if(nv.getMaNhanVien().equals(txtTimKiem.getText())) {
							modelHoaDon.addRow(new Object[] {nv.getMaNhanVien(), nv.getTenNhanVien(), nv.getGioiTinh()
									, ngaySinh, nv.getBoPhan(), ngayVaoLam, nv.getNgoaiNgu(), nv.getSanPham().getMaSanPham()});
				    	dem++;
				    	}
				   }
				}
				   if(dem == 1) {
					   JOptionPane.showMessageDialog(this, "Không tìm thấy công nhân cần tìm");
					   return;
				   }
			}
			
			else if(cmbTimKiem.getSelectedIndex() == 2) {
				if(txtTimKiem.getText().equals("")) {
					JOptionPane.showMessageDialog(this, "Vui lòng nhập tên công nhân cần tìm!");
				}
				modelHoaDon.getDataVector().removeAllElements();
				int dem = 1;
				listNV = nvDao.getAllNV();
				for (NhanVien nv : listNV) {
					//Format ngay
			        Date date = Calendar.getInstance().getTime();  
			        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");  
			        String ngaySinh = dateFormat.format(nv.getNgaySinh());  
			        String ngayVaoLam = dateFormat.format(nv.getNgayVaoLam());
			        
			        //Get ngày hiện tại
					 DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
					 date1 = LocalDate.now();
					 dateCurrently = dtf.format(date1);
				
					 txtNgayTinhLuong.setText(dateCurrently);

				    if(nv.getMaNhanVien().charAt(0) == 'C') {
						String str = nv.getMaNhanVien();
						String [] array = str.trim().split(txtTimKiem.getText());
				    	if(nv.getTenNhanVien().equals(txtTimKiem.getText())) {
							modelHoaDon.addRow(new Object[] {nv.getMaNhanVien(), nv.getTenNhanVien(), nv.getGioiTinh()
									, ngaySinh, nv.getBoPhan(), ngayVaoLam, nv.getNgoaiNgu(), "G001"});
				    	dem++;
				    	}
				   }
				}
				if(dem == 1) {
					JOptionPane.showMessageDialog(this, "Không tìm thấy công nhân cần tìm");
					return;
				}
			}
		}
		
		else if(o.equals(btnLuu)) {
			int n = JOptionPane.showConfirmDialog(null, "Bạn có thật sự muốn lưu không?", "Cảnh báo",
					JOptionPane.YES_NO_OPTION);
			if (n == 0) {
				if(!txtTongTien.getText().equals("")) {
					int index = 1;
					dsLuong = luongDao.getAllLuong();
					
					for(Luong x : dsLuong) {
						index++;
					}
					
					//Tạo và thêm thuộc tính cho các lớp thực thể nếu có
					NhanVien nv = new NhanVien();
					nv.setMaNhanVien(txtMaCN.getText());
					NgayNghi ngayNghi = new NgayNghi();
					
					
					//Lấy ngày hiện tại
			        long millis=System.currentTimeMillis();  
			        java.sql.Date date = new java.sql.Date(millis);
			        
					Luong luong = new Luong();
					luong.setMaLuong("PL0"+index);
					luong.setNgayNghi(ngayNghi);
					luong.setSoNgayNghiCoPhep(0);
					luong.setSoNgayNghiKhongPhep(0);
					luong.setNhanVien(nv);
					luong.setCongDoan(cmbTenCongDoan.getSelectedItem().toString());
					luong.setSoSanPhamHoanThien(Integer.parseInt(txtSoSPHoanThien.getText()) + Integer.parseInt(txtSoSanPhamTangCa.getText()));
					luong.setHeSoLuong(Float.parseFloat(txtHeSoLuong.getText()));
					luong.setNgayTao(date);
					luong.setTongTien((float)tongTien);
					
					if(luongDao.themLuong(luong)) {
						JOptionPane.showMessageDialog(this, "Lưu thành công!");
					}
					else {
						JOptionPane.showMessageDialog(this, "Lưu thất bại!");
					}
				}
				else {
					JOptionPane.showMessageDialog(this, "Không có dữ liệu để lưu hoặc sai dữ liệu");
				}
			}
		}
	}
	
	
}
