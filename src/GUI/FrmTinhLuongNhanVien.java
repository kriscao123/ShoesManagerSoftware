package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import dao.LuongDao;
import dao.NgayNghiDao;
import dao.NhanVienDao;
import entity.Luong;
import entity.NgayNghi;
import entity.NhanVien;



public class FrmTinhLuongNhanVien extends JFrame implements MouseListener, ActionListener {
	private String result;
	private JPanel panel_TinhLuong;
	private JTextField txtTenKT;
	private JTextField txtMaNV;
	private JTextField txtHoTenCN_TinhLuong;
	private JTextField txtSoNgayNghi;
	private JTextField txtSoNgayNghiCoLyDo;
	private JTextField txtTongTienLuong;
	private JTextField txtNgayTinhLuong;
	private JTextField txtHeSoLuong;
	private JTextPane txtLyDo;
	private JTextField txtMaNgayNghi;
	private JTextField txtNguoiTao;
	private JTextField txtNgayTao;
	private AbstractButton btnLuuNgayNghi;
	private AbstractButton btnLuu;
	private JButton btnTinh;
	private JButton btnLamMoi;
	private JTextField txtTimKiem;
	private JComboBox comboBox_Loc;
	private JButton btnTimKiemLuong;
	private DefaultTableModel modelHoaDon;
	private JTable table;
	private JComboBox comboBox_TimKiem_NgayNghi;
	private JTextField txtTimKiemNgayNghi;
	private DefaultTableModel modelNN;
	private JTable tableNN;
	private AbstractButton btnTimKiem;
	private List<NhanVien> listNV;
	private NhanVienDao nvDao;
	private LocalDate date1;
	private String dateCurrently;
	private List<NhanVien> dsNv;
	private NgayNghiDao nnDao;
	private ArrayList<NgayNghi> listNN;
	private LuongDao luongDao;
	private double tongTien;
	private List<Luong> dsLuong;
	private ArrayList<NgayNghi> dsNN;
	private Luong luong;
	
	public FrmTinhLuongNhanVien(String ma) {
		this.result = ma;
		getContentPane().add(taoFrame_TinhLuongNV());
		setSize(1200, 800);
	}
	
	public static void main(String[] args) {
		new FrmTinhLuongNhanVien("").setVisible(true);
	}

	public JPanel taoFrame_TinhLuongNV() {
		panel_TinhLuong = new JPanel();
		panel_TinhLuong.setBackground(new Color(204, 255, 255));
		panel_TinhLuong.setLayout(null);
		JPanel panel_FromDien = new JPanel();
		panel_FromDien.setBackground(new Color(102, 255, 255));
		panel_FromDien.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_FromDien.setBounds(20, 44, 320, 330);
		panel_TinhLuong.add(panel_FromDien);
		panel_FromDien.setLayout(null);

		JLabel lblNhnVinK = new JLabel("Nhân viên kế toán:");
		lblNhnVinK.setFont(new Font("Verdana", Font.PLAIN, 14));
		lblNhnVinK.setBounds(10, 10, 140, 43);
		panel_FromDien.add(lblNhnVinK);

		txtTenKT = new JTextField();
		txtTenKT.setEditable(false);
		txtTenKT.setFont(new Font("Verdana", Font.PLAIN, 14));
		txtTenKT.setColumns(10);
		txtTenKT.setBounds(150, 20, 150, 26);
		panel_FromDien.add(txtTenKT);

		txtMaNV = new JTextField();
		txtMaNV.setEditable(false);
		txtMaNV.setFont(new Font("Verdana", Font.PLAIN, 14));
		txtMaNV.setColumns(10);
		txtMaNV.setBounds(150, 56, 150, 26);
		panel_FromDien.add(txtMaNV);

		txtHoTenCN_TinhLuong = new JTextField();
		txtHoTenCN_TinhLuong.setEditable(false);
		txtHoTenCN_TinhLuong.setFont(new Font("Verdana", Font.PLAIN, 14));
		txtHoTenCN_TinhLuong.setColumns(10);
		txtHoTenCN_TinhLuong.setBounds(150, 92, 150, 26);
		panel_FromDien.add(txtHoTenCN_TinhLuong);

		JLabel lbMaCongNhan = new JLabel("Mã Nhân Viên:");
		lbMaCongNhan.setFont(new Font("Verdana", Font.PLAIN, 14));
		lbMaCongNhan.setBounds(10, 50, 191, 37);
		panel_FromDien.add(lbMaCongNhan);

		JLabel lbHoTenCN = new JLabel("Tên Nhân Viên:");
		lbHoTenCN.setFont(new Font("Verdana", Font.PLAIN, 14));
		lbHoTenCN.setBounds(10, 86, 191, 37);
		panel_FromDien.add(lbHoTenCN);

		JLabel lblHeSoLuong_TinhLuong = new JLabel("Hệ số lương:");
		lblHeSoLuong_TinhLuong.setFont(new Font("Verdana", Font.PLAIN, 14));
		lblHeSoLuong_TinhLuong.setBounds(10, 120, 191, 37);
		panel_FromDien.add(lblHeSoLuong_TinhLuong);
		
		txtHeSoLuong = new JTextField();
		txtHeSoLuong.setEditable(false);
		txtHeSoLuong.setFont(new Font("Verdana", Font.PLAIN, 14));
		txtHeSoLuong.setColumns(10);
		txtHeSoLuong.setBounds(150, 128, 150, 26);
		panel_FromDien.add(txtHeSoLuong);

		txtSoNgayNghi = new JTextField();
		txtSoNgayNghi.setFont(new Font("Verdana", Font.PLAIN, 14));
		txtSoNgayNghi.setColumns(10);
		txtSoNgayNghi.setBounds(150, 164, 150, 26);
		panel_FromDien.add(txtSoNgayNghi);

		JLabel lblSoNgayNghi = new JLabel("Số ngày nghỉ (K):");
		lblSoNgayNghi.setFont(new Font("Verdana", Font.PLAIN, 14));
		lblSoNgayNghi.setBounds(10, 158, 191, 37);
		panel_FromDien.add(lblSoNgayNghi);

		JLabel lblSoNgayNghiCoLyDo = new JLabel("Số ngày nghỉ (P):");
		lblSoNgayNghiCoLyDo.setFont(new Font("Verdana", Font.PLAIN, 14));
		lblSoNgayNghiCoLyDo.setBounds(10, 192, 191, 37);
		panel_FromDien.add(lblSoNgayNghiCoLyDo);

		txtSoNgayNghiCoLyDo = new JTextField();
		txtSoNgayNghiCoLyDo.setFont(new Font("Verdana", Font.PLAIN, 14));
		txtSoNgayNghiCoLyDo.setColumns(10);
		txtSoNgayNghiCoLyDo.setBounds(150, 200, 150, 26);
		panel_FromDien.add(txtSoNgayNghiCoLyDo);

		JLabel lblNgayTaoLuong = new JLabel("Ngày tính lương:");
		lblNgayTaoLuong.setFont(new Font("Verdana", Font.PLAIN, 14));
		lblNgayTaoLuong.setBounds(10, 226, 191, 37);
		panel_FromDien.add(lblNgayTaoLuong);

		txtTongTienLuong = new JTextField();
		txtTongTienLuong.setHorizontalAlignment(SwingConstants.RIGHT);
		txtTongTienLuong.setForeground(Color.RED);
//		txtTongTienLuong.setBorder(new EmptyBorder(0, 10, 0, 0));
		txtTongTienLuong.setEditable(false);
		txtTongTienLuong.setFont(new Font("Verdana", Font.BOLD, 20));
		txtTongTienLuong.setBackground(new Color(102, 255, 255));
		txtTongTienLuong.setColumns(10);
		txtTongTienLuong.setBounds(150, 274, 160, 34);
		panel_FromDien.add(txtTongTienLuong);

		JLabel lbTongTien = new JLabel("Tổng Lương:");
		lbTongTien.setFont(new Font("Verdana", Font.BOLD, 16));
		lbTongTien.setBounds(10, 270, 191, 44);
		panel_FromDien.add(lbTongTien);

		txtNgayTinhLuong = new JTextField();
		txtNgayTinhLuong.setEditable(false);
		txtNgayTinhLuong.setFont(new Font("Verdana", Font.PLAIN, 14));
		txtNgayTinhLuong.setColumns(10);
		txtNgayTinhLuong.setBounds(150, 234, 150, 26);
		panel_FromDien.add(txtNgayTinhLuong);

		

		JLabel lbGiaoDIenTinhLuong = new JLabel("Giao Diện Tính Lương Nhân Viên Hành Chánh");
		lbGiaoDIenTinhLuong.setFont(new Font("Verdana", Font.BOLD, 16));
		lbGiaoDIenTinhLuong.setBounds(20, 0, 446, 43);
		panel_TinhLuong.add(lbGiaoDIenTinhLuong);

		JPanel panel_NgayNghi = new JPanel();
		panel_NgayNghi.setBackground(new Color(102, 255, 255));
		panel_NgayNghi.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_NgayNghi.setBounds(20, 490, 500, 240);
		panel_TinhLuong.add(panel_NgayNghi);
		panel_NgayNghi.setLayout(null);

		txtLyDo = new JTextPane();
		txtLyDo.setFont(new Font("Verdana", Font.PLAIN, 14));
		txtLyDo.setBorder(new LineBorder(new Color(0, 0, 0)));
		txtLyDo.setBounds(10, 42, 200, 168);
		panel_NgayNghi.add(txtLyDo);

		JLabel lblLyDo = new JLabel("Ghi chú:");
		lblLyDo.setFont(new Font("Verdana", Font.ITALIC, 18));
		lblLyDo.setBounds(10, 0, 191, 37);
		panel_NgayNghi.add(lblLyDo);

		JLabel lblMaNgayNghi = new JLabel("Mã ngày nghỉ:");
		lblMaNgayNghi.setFont(new Font("Verdana", Font.PLAIN, 16));
		lblMaNgayNghi.setBounds(220, 40, 191, 37);
		panel_NgayNghi.add(lblMaNgayNghi);

		JLabel lblMaCN_NgayNghi = new JLabel("Người Tạo" + "");
		lblMaCN_NgayNghi.setFont(new Font("Verdana", Font.PLAIN, 16));
		lblMaCN_NgayNghi.setBounds(220, 80, 191, 37);
		panel_NgayNghi.add(lblMaCN_NgayNghi);

		JLabel lblNgayTao = new JLabel("Ngày tạo:");
		lblNgayTao.setFont(new Font("Verdana", Font.PLAIN, 16));
		lblNgayTao.setBounds(220, 120, 191, 37);
		panel_NgayNghi.add(lblNgayTao);

		txtMaNgayNghi = new JTextField();
		txtMaNgayNghi.setFont(new Font("Verdana", Font.PLAIN, 14));
		txtMaNgayNghi.setColumns(10);
		txtMaNgayNghi.setBounds(340, 44, 150, 30);
		panel_NgayNghi.add(txtMaNgayNghi);

		txtNguoiTao = new JTextField();
		txtNguoiTao.setFont(new Font("Verdana", Font.PLAIN, 14));
		txtNguoiTao.setColumns(10);
		txtNguoiTao.setBounds(340, 84, 150, 30);
		panel_NgayNghi.add(txtNguoiTao);

		txtNgayTao = new JTextField();
		txtNgayTao.setEditable(false);
		txtNgayTao.setFont(new Font("Verdana", Font.PLAIN, 14));
		txtNgayTao.setColumns(10);
		txtNgayTao.setBounds(340, 124, 150, 30);
		panel_NgayNghi.add(txtNgayTao);

		btnLuuNgayNghi = new JButton("Lưu");
		btnLuuNgayNghi.setIcon(new ImageIcon("img\\diskette.png"));
		btnLuuNgayNghi.setBackground(new java.awt.Color(58, 46, 130));
		btnLuuNgayNghi.setForeground(Color.WHITE);
		btnLuuNgayNghi.setFont(new Font("Verdana", Font.PLAIN, 14));
		btnLuuNgayNghi.setBounds(340, 168, 100, 42);
		panel_NgayNghi.add(btnLuuNgayNghi);

		JPanel panel_ChucNang = new JPanel();
		panel_ChucNang.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_ChucNang.setBackground(new Color(102, 255, 255));
		panel_ChucNang.setBounds(20, 380, 320, 70);
		panel_TinhLuong.add(panel_ChucNang);
		panel_ChucNang.setLayout(null);

		btnLuu = new JButton();
		btnLuu.setIcon(new ImageIcon("img\\diskette.png"));
		btnLuu.setBackground(new java.awt.Color(58, 46, 130));
		btnLuu.setForeground(Color.WHITE);
		btnLuu.setFont(new Font("Verdana", Font.PLAIN, 14));
		btnLuu.setBounds(240, 10, 70, 50);
		panel_ChucNang.add(btnLuu);

		btnTinh = new JButton();
		btnTinh.setIcon(new ImageIcon("img\\accounts.png"));
		btnTinh.setForeground(Color.WHITE);
		btnTinh.setBackground(new java.awt.Color(58, 46, 130));
		btnTinh.setFont(new Font("Verdana", Font.PLAIN, 14));
		btnTinh.setBounds(10, 10, 70, 50);
		panel_ChucNang.add(btnTinh);

		btnLamMoi = new JButton();
		btnLamMoi.setIcon(new ImageIcon("img\\refresh.png"));
		btnLamMoi.setForeground(Color.WHITE);
		btnLamMoi.setFont(new Font("Verdana", Font.PLAIN, 14));
		btnLamMoi.setBackground(new Color(58, 46, 130));
		btnLamMoi.setBounds(126, 10, 70, 50);
		panel_ChucNang.add(btnLamMoi);

		JPanel panel_BangDSLuong = new JPanel();
		panel_BangDSLuong.setBackground(new Color(102, 255, 255));
		panel_BangDSLuong.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_BangDSLuong.setBounds(352, 44, 820, 406);
		panel_TinhLuong.add(panel_BangDSLuong);
		panel_BangDSLuong.setLayout(null);

		txtTimKiem = new JTextField();
		txtTimKiem.setFont(new Font("Verdana", Font.PLAIN, 14));
		txtTimKiem.setColumns(10);
		txtTimKiem.setBounds(180, 10, 480, 34);
		panel_BangDSLuong.add(txtTimKiem);

		comboBox_Loc = new JComboBox();
		comboBox_Loc.setModel(new DefaultComboBoxModel(new String[] { " Tìm Kiếm Theo", " Mã nhân viên", " Tên nhân viên" }));
		comboBox_Loc.setFont(new Font("Verdana", Font.PLAIN, 14));
		comboBox_Loc.setBounds(10, 10, 154, 34);
		panel_BangDSLuong.add(comboBox_Loc);

		btnTimKiemLuong = new JButton("Tìm kiếm");
		btnTimKiemLuong.setIcon(new ImageIcon("img\\search.png"));
		btnTimKiemLuong.setForeground(Color.WHITE);
		btnTimKiemLuong.setBackground(new java.awt.Color(58, 46, 130));
		btnTimKiemLuong.setFont(new Font("Verdana", Font.PLAIN, 14));
		btnTimKiemLuong.setBounds(670, 10, 140, 34);
		panel_BangDSLuong.add(btnTimKiemLuong);
		/*
		 * Table
		 */
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 60, 800, 340);
		panel_BangDSLuong.add(scrollPane);
		String[] header = { "Mã NV", "Tên nhân viên", "Giới tính", "Ngày sinh", "Bộ phận", "Ngày vào làm",
				"Ngoại ngữ" };
		modelHoaDon = new DefaultTableModel(header, 0);

		table = new JTable(modelHoaDon) {
			public boolean editCellAt(int row, int column, EventObject e) { // Không cho chỉnh sửa giá trị trong table
				return false;
			}
		};

		JTableHeader header1 = table.getTableHeader();
		header1.setBackground(new Color(23, 70, 138));
		header1.setForeground(Color.white);
		header1.setFont(new Font("Tahoma", Font.PLAIN, 18));

		table.setFont(new Font("Tahoma", Font.PLAIN, 16));
		scrollPane.setViewportView(table);
		table.setRowHeight(30);


		/*
		 * End table
		 */

		JPanel panel = new JPanel();
		panel.setBackground(new Color(102, 255, 255));
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(540, 490, 622, 240);
		panel_TinhLuong.add(panel);
		panel.setLayout(null);

		comboBox_TimKiem_NgayNghi = new JComboBox();
		comboBox_TimKiem_NgayNghi
				.setModel(new DefaultComboBoxModel(new String[] { "Tìm Kiếm Theo", "Mã ngày nghỉ", "Ngày tạo" }));
		comboBox_TimKiem_NgayNghi.setBounds(10, 10, 154, 34);
		comboBox_TimKiem_NgayNghi.setFont(new Font("Verdana", Font.PLAIN, 16));
		panel.add(comboBox_TimKiem_NgayNghi);

		txtTimKiemNgayNghi = new JTextField();
		txtTimKiemNgayNghi.setBounds(180, 10, 240, 34);
		txtTimKiemNgayNghi.setFont(new Font("Verdana", Font.PLAIN, 14));
		txtTimKiemNgayNghi.setColumns(10);
		panel.add(txtTimKiemNgayNghi);

		/*
		 * Table
		 */
		JScrollPane scrollPane_BangNgayNghi = new JScrollPane();
		scrollPane_BangNgayNghi.setBounds(10, 52, 600, 180);
		panel.add(scrollPane_BangNgayNghi);
		String[] header2 = { "Mã ngày nghỉ", "Người Tạo" + "", "Lí do", "Ngày tạo" };
		modelNN = new DefaultTableModel(header2, 0);

		tableNN = new JTable(modelNN) {
			public boolean editCellAt(int row, int column, EventObject e) { // Không cho chỉnh sửa giá trị trong table
				return false;
			}
		};

		JTableHeader header3 = tableNN.getTableHeader();
		header3.setBackground(new Color(23, 70, 138));
		header3.setForeground(Color.white);
		header3.setFont(new Font("Tahoma", Font.PLAIN, 18));

		tableNN.setFont(new Font("Tahoma", Font.PLAIN, 18));
		scrollPane_BangNgayNghi.setViewportView(tableNN);
		tableNN.setRowHeight(28);

		table.getColumnModel().getColumn(0).setPreferredWidth(80);
		table.getColumnModel().getColumn(1).setPreferredWidth(120);
		table.getColumnModel().getColumn(2).setPreferredWidth(60);
		table.getColumnModel().getColumn(3).setPreferredWidth(100);
		table.getColumnModel().getColumn(4).setPreferredWidth(100);
		table.getColumnModel().getColumn(5).setPreferredWidth(100);
		
		tableNN.getColumnModel().getColumn(0).setPreferredWidth(80);
		tableNN.getColumnModel().getColumn(1).setPreferredWidth(100);
		tableNN.getColumnModel().getColumn(2).setPreferredWidth(140);
		tableNN.getColumnModel().getColumn(3).setPreferredWidth(80);

		/*
		 * End table
		 */

		btnTimKiem = new JButton("Tìm kiếm");
		btnTimKiem.setIcon(new ImageIcon("img\\search.png"));
		btnTimKiem.setForeground(Color.WHITE);
		btnTimKiem.setFont(new Font("Verdana", Font.PLAIN, 16));
		btnTimKiem.setBackground(new Color(58, 46, 130));
		btnTimKiem.setBounds(440, 10, 160, 34);
		panel.add(btnTimKiem);

		JLabel lblNgyNgh = new JLabel("Ngày nghỉ");
		lblNgyNgh.setFont(new Font("Verdana", Font.BOLD, 16));
		lblNgyNgh.setBounds(20, 450, 291, 43);
		panel_TinhLuong.add(lblNgyNgh);

		nvDao = new NhanVienDao();
		dsNv = nvDao.getAllNV();
		listNV = nvDao.getAllNV();
		docDuLieuDatabaseVaoTableLuong();
		nnDao = new NgayNghiDao();
		listNN = nnDao.getAllNN();
		luongDao = new LuongDao(); 
		DocDuLieuDatabaseVaoTableNgayNghi();

		table.addMouseListener(this);
		tableNN.addMouseListener(this);

		btnLuu.addActionListener(this);
		btnLuuNgayNghi.addActionListener(this);
		btnTimKiem.addActionListener(this);
		btnTimKiemLuong.addActionListener(this);
		btnTinh.addActionListener(this);
		btnLamMoi.addActionListener(this);
		
		for(NhanVien nv: dsNv) {
			if(nv.getMaNhanVien().equals(this.result)) {
				txtTenKT.setText(nv.getTenNhanVien());
				txtNguoiTao.setText(nv.getTenNhanVien());
			}
		}

		return panel_TinhLuong;
	}
	
	private void docDuLieuDatabaseVaoTableLuong() {
		modelHoaDon.getDataVector().removeAllElements();
		listNV = nvDao.getAllNV();
		for (NhanVien nv : listNV) {
			// Format ngay
			Date date = Calendar.getInstance().getTime();
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			String ngaySinh = dateFormat.format(nv.getNgaySinh());
			String ngayVaoLam = dateFormat.format(nv.getNgayVaoLam());

			// Get ngày hiện tại
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			date1 = LocalDate.now();
			dateCurrently = dtf.format(date1);

			txtNgayTinhLuong.setText(dateCurrently);

			if (nv.getMaNhanVien().charAt(0) == 'N') {
				modelHoaDon.addRow(new Object[] { nv.getMaNhanVien(), nv.getTenNhanVien(), nv.getGioiTinh(), ngaySinh,
						nv.getBoPhan(), ngayVaoLam, nv.getNgoaiNgu() });
			}

		}

	}
	private void DocDuLieuDatabaseVaoTableNgayNghi() {
		modelNN.getDataVector().removeAllElements();
		ArrayList<NgayNghi> list = nnDao.getAllNN();
		list = nnDao.getAllNN();
		for (NgayNghi nn : list) {
			modelNN.addRow(new Object[] { nn.getMaNgayNghi(), nn.getNguoiTao(), nn.getLyDo(), nn.getNgayTao() });
		}
	}
	
	private void showData() {
		int selectedRow = table.getSelectedRow();
		if(selectedRow == -1) {
			return;
		}
		txtMaNV.setText(modelHoaDon.getValueAt(selectedRow, 0).toString());
		txtHoTenCN_TinhLuong.setText(modelHoaDon.getValueAt(selectedRow, 1).toString());

		// txtNgayTinhLuong.setText(java.time.LocalDate.now());

//        Tính năm làm
		long year = 0;
		SimpleDateFormat myFormat = new SimpleDateFormat("dd-MM-yyyy");
		String startDate = modelHoaDon.getValueAt(selectedRow, 5).toString();
		try {
			Date date1 = myFormat.parse(startDate);
			Date date2 = myFormat.parse(dateCurrently);
			long diff = date2.getTime() - date1.getTime();
			long temp = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
			year = temp / 365;
		} catch (ParseException e) {
			e.printStackTrace();
		}

		// Tính hệ số lương
		int heSoLuong = 0;

		if (modelHoaDon.getValueAt(selectedRow, 6).toString().equals("Sơ Cấp")) {
			heSoLuong += 1;
		}

		if (modelHoaDon.getValueAt(selectedRow, 6).toString().equals("Trung Cấp")) {
			heSoLuong += 2;
		}

		if (modelHoaDon.getValueAt(selectedRow, 6).toString().equals("Cao Cấp")) {
			heSoLuong += 3;
		}

		if (year >= 10 && year < 20) {
			heSoLuong += 1;
		}

		if (year >= 20 && year < 30) {
			heSoLuong += 2;
		}

		if (year >= 30) {
			heSoLuong += 3;
		}

		float ketQuaHeSoLuong = 0;
		if (heSoLuong == 1) {
			ketQuaHeSoLuong = (float) 1.1;
		}

		if (heSoLuong == 2) {
			ketQuaHeSoLuong = (float) 1.2;
		}

		if (heSoLuong == 3) {
			ketQuaHeSoLuong = (float) 1.3;
		}

		if (heSoLuong == 4) {
			ketQuaHeSoLuong = (float) 1.4;
		}

		if (heSoLuong == 5) {
			ketQuaHeSoLuong = (float) 1.5;
		}

		if (heSoLuong == 6) {
			ketQuaHeSoLuong = (float) 1.6;
		}

		txtHeSoLuong.setText(Float.toString(ketQuaHeSoLuong));
	}
	
	public boolean checkDataLuong() {
		if (txtMaNV.getText().equals("") || txtSoNgayNghi.getText().equals("")
				|| txtSoNgayNghiCoLyDo.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!");
			return false;
		}
		int soNgayNghi;
		int soNgayNghiCoLyDo;
		try {
			soNgayNghi = Integer.parseInt(txtSoNgayNghi.getText().trim());
			if (soNgayNghi > 30) {
				JOptionPane.showMessageDialog(this, "Số ngày nghỉ không lí do phải < 30 ngày");
				txtSoNgayNghi.requestFocus();
				txtSoNgayNghi.selectAll();
				return false;
			}
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(this, "Số ngày nghỉ phải là số nguyên!");
			txtSoNgayNghi.requestFocus();
			txtSoNgayNghi.selectAll();
			return false;
		}

		try {
			soNgayNghiCoLyDo = Integer.parseInt(txtSoNgayNghiCoLyDo.getText());
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(this, "Số ngày nghỉ có lí do phải là số nguyên!");
			txtSoNgayNghiCoLyDo.requestFocus();
			txtSoNgayNghiCoLyDo.selectAll();
			return false;
		}
		String hotenNhanVien = txtHoTenCN_TinhLuong.getText().trim();
		if (!(hotenNhanVien.length() > 0)) {
			JOptionPane.showMessageDialog(null, "Thông tin nhân viên không được để trống");
			return false;
		} 
		return true;
	}
	
	private boolean checkDataNN() {
		//here
		int r = tableNN.getSelectedRow();
		String tenNguoiTao = "";
		try {
			tenNguoiTao = modelNN.getValueAt(r, 1).toString();
			System.out.println(tenNguoiTao);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		String maNN = txtMaNgayNghi.getText().trim();
		String nguoiTao = txtNguoiTao.getText().trim();
		String lyDo = txtLyDo.getText().trim();
		if (!(maNN.matches("(NN)\\d{3}"))) {
			JOptionPane.showMessageDialog(null,
					"Error: Mã nhân viên phải bắt đầu bằng ký tự 'NN' tiếp theo là 3 số từ 0-9 !");
			txtMaNgayNghi.setText("");
			txtMaNgayNghi.requestFocus();
			return false;
		} else if (!(maNN.length() > 0)) {
			JOptionPane.showMessageDialog(null, "Mã ngày nghỉ không được để trống !");
			txtMaNgayNghi.setText("");
			txtMaNgayNghi.requestFocus();
			return false;
		}

		else if (!(nguoiTao.length() > 0)) {
			JOptionPane.showMessageDialog(null, "Người tạo không được để trống !");
			txtNguoiTao.setText("");
			txtNguoiTao.requestFocus();
			return false;
		} else if (!(lyDo.length() > 0)) {
			JOptionPane.showMessageDialog(null, "Lý do không được để trống !");
			txtLyDo.setText("");
			txtLyDo.requestFocus();
			return false;
		}
		return true;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
				Object o = e.getSource();
				if(o.equals(table)) {
					showData();
				}
				else if(o.equals(tableNN)) {
					int r = tableNN.getSelectedRow();
					txtMaNgayNghi.setText(modelNN.getValueAt(r, 0).toString());
					
					try {
						txtNguoiTao.setText(modelNN.getValueAt(r, 1).toString());	
					} catch (Exception e2) {
						// TODO: handle exception
						txtNguoiTao.setText(txtTenKT.getText());
					}
					
					try {
						txtLyDo.setText(modelNN.getValueAt(r, 2).toString());
					} catch (Exception e2) {
						// TODO: handle exception
						txtLyDo.setText("");
					}
					try {
						txtNgayTao.setText(modelNN.getValueAt(r, 3).toString());
					} catch (Exception e2) {
						// TODO: handle exception
						DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
						date1 = LocalDate.now();
						dateCurrently = dtf.format(date1);
						txtNgayTao.setText(dateCurrently);
					}
				}
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
		Object obj = e.getSource();
		if (obj.equals(btnLamMoi)) {
			txtSoNgayNghi.setText("");
			txtSoNgayNghiCoLyDo.setText("");
			txtNguoiTao.setText("");
			txtMaNgayNghi.setText("");
			txtNgayTao.setText("");
			txtLyDo.setText("");
			txtMaNV.setText("");
			txtHoTenCN_TinhLuong.setText("");
			txtHeSoLuong.setText("");
			txtTongTienLuong.setText("");
			txtSoNgayNghi.requestFocus();
		} else if (obj.equals(btnTimKiem)) {
			if (comboBox_TimKiem_NgayNghi.getSelectedIndex() == 0) {
				DocDuLieuDatabaseVaoTableNgayNghi();

			} else if (comboBox_TimKiem_NgayNghi.getSelectedIndex() == 1) {
				if (txtTimKiemNgayNghi.getText().equals("")) {
					JOptionPane.showMessageDialog(this, "Vui lòng nhập mã ngày nghỉ cần tìm!");
					return;
				}
				modelNN.getDataVector().removeAllElements();
				int dem = 1;
				listNN = nnDao.getAllNN();
				for (NgayNghi nn : listNN) {
					// Format ngay
					Date date = Calendar.getInstance().getTime();
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

					// Get ngày hiện tại
					DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
					date1 = LocalDate.now();
					dateCurrently = dtf.format(date1);
					
					if (nn.getMaNgayNghi().equals(txtTimKiemNgayNghi.getText())) {
						modelNN.addRow(
								new Object[] { nn.getMaNgayNghi(), nn.getNguoiTao(), nn.getLyDo(), nn.getNgayTao() });
						dem++;
					}
				}
				if (dem == 1) {
					JOptionPane.showMessageDialog(this, "Không tìm thấy ngày nghỉ cần tìm");
					return;
				}
			} else if (comboBox_TimKiem_NgayNghi.getSelectedIndex() == 2) {
				if (txtTimKiemNgayNghi.getText().equals("")) {
					JOptionPane.showMessageDialog(this, "Vui lòng nhập ngày cần tìm!");
					return;
				}
				
				try {
					Integer.parseInt(txtTimKiemNgayNghi.getText());
				} catch (Exception e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(this, "Ngày tạo phải là số nguyên!");
					return;
				}
				
				if(Integer.parseInt(txtTimKiemNgayNghi.getText()) < 1 || Integer.parseInt(txtTimKiemNgayNghi.getText()) > 31) {
					JOptionPane.showMessageDialog(this, "Ngày tạo phải thỏa: 0 < x < 32");
					return;
				}
				
				int rows = modelNN.getRowCount();
				for(int i = rows - 1; i >=0; i--)
				{
					modelNN.removeRow(i); 
				}
				boolean check = true;
				
				for(NgayNghi nn : listNN) {
					//Covert from java sql date to string
					DateFormat df = new SimpleDateFormat("dd/MM/yyyy");  
					String text = df.format(nn.getNgayTao()); 
					
					//Covert from string to java util date
					Date dateNgayTao = new Date();
					try {
						dateNgayTao = new SimpleDateFormat("dd/MM/yyyy").parse(text);
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}  
					
			        ZoneId timeZone = ZoneId.systemDefault();
			        LocalDate getLocalDate = dateNgayTao.toInstant().atZone(timeZone).toLocalDate();
					if(Integer.toString(getLocalDate.getDayOfMonth()).equals(txtTimKiemNgayNghi.getText())) {
						check = false;
				        Date date = Calendar.getInstance().getTime();  
				        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");  
				        String ngayTao = dateFormat.format(nn.getNgayTao());  

				        if(Integer.toString(getLocalDate.getDayOfMonth()).equals(txtTimKiemNgayNghi.getText())) {
							modelNN.addRow(new Object[] {nn.getMaNgayNghi(), nn.getNguoiTao(), nn.getLyDo(), nn.getNgayTao()});
				        }
					}
			}
				if(check) {
					DocDuLieuDatabaseVaoTableNgayNghi();
					JOptionPane.showMessageDialog(this, "Không tìm thấy!");
					return;
				}
		}
	}
		if (obj.equals(btnTimKiemLuong)) {
			if (comboBox_Loc.getSelectedIndex() == 0) {
				docDuLieuDatabaseVaoTableLuong();
			}

			else if (comboBox_Loc.getSelectedIndex() == 1) {
				if (txtTimKiem.getText().equals("")) {
					JOptionPane.showMessageDialog(this, "Vui lòng nhập mã nhân viên cần tìm!");
					return;
				}
				modelHoaDon.getDataVector().removeAllElements();
				int dem = 1;
				listNV = nvDao.getAllNV();
				for (NhanVien nv : listNV) {
					// Format ngay
					Date date = Calendar.getInstance().getTime();
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					String ngaySinh = dateFormat.format(nv.getNgaySinh());
					String ngayVaoLam = dateFormat.format(nv.getNgayVaoLam());

					// Get ngày hiện tại
					DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
					date1 = LocalDate.now();
					dateCurrently = dtf.format(date1);

					txtNgayTinhLuong.setText(dateCurrently);

					
					if (nv.getMaNhanVien().charAt(0) == 'N') {
						if (nv.getMaNhanVien().equals(txtTimKiem.getText())) {
							modelHoaDon.addRow(new Object[] { nv.getMaNhanVien(), nv.getTenNhanVien(), nv.getGioiTinh(),
									ngaySinh, nv.getBoPhan(), ngayVaoLam, nv.getNgoaiNgu() });
							dem++;
						}
					}
				}
				if (dem == 1) {
					JOptionPane.showMessageDialog(this, "Không tìm thấy nhân viên cần tìm");
					return;
				}
			}

			else if (comboBox_Loc.getSelectedIndex() == 2) {
				if (txtTimKiem.getText().equals("")) {
					JOptionPane.showMessageDialog(this, "Vui lòng nhập tên nhân viên cần tìm!");
					return;
				}
				modelHoaDon.getDataVector().removeAllElements();
				int dem = 1;
				listNV = nvDao.getAllNV();
				for (NhanVien nv : listNV) {
					// Format ngay
					Date date = Calendar.getInstance().getTime();
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					String ngaySinh = dateFormat.format(nv.getNgaySinh());
					String ngayVaoLam = dateFormat.format(nv.getNgayVaoLam());

					// Get ngày hiện tại
					DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
					date1 = LocalDate.now();
					dateCurrently = dtf.format(date1);

					txtNgayTinhLuong.setText(dateCurrently);

					if (nv.getMaNhanVien().charAt(0) == 'N') {
						if (nv.getTenNhanVien().equals(txtTimKiem.getText())) {
							modelHoaDon.addRow(new Object[] { nv.getMaNhanVien(), nv.getTenNhanVien(), nv.getGioiTinh(),
									ngaySinh, nv.getBoPhan(), ngayVaoLam, nv.getNgoaiNgu() });
							dem++;
						}

					}
				}
				if (dem == 1) {
					JOptionPane.showMessageDialog(this, "Không tìm thấy nhân viên cần tìm");
					return;
				}
			}
		} else if (obj.equals(btnTinh)) {
			if (checkDataLuong()) {
				if(txtMaNV.getText().equals("") || txtSoNgayNghi.getText().equals("") || txtSoNgayNghiCoLyDo.getText().equals("")) {
					JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!");
					return;
				}
				float hsLuong = Float.parseFloat(txtHeSoLuong.getText().trim());
				int soNgayNghi = Integer.parseInt(txtSoNgayNghi.getText().trim());
				int soNgayNghiCoLyDo = Integer.parseInt(txtSoNgayNghiCoLyDo.getText().trim());
				String maNhanVien = txtMaNV.getText().trim();
				
				if(soNgayNghi + soNgayNghiCoLyDo >= 15) {
					tongTien = 0;
				}

				else if (soNgayNghi == 0 && soNgayNghiCoLyDo == 0) {
					tongTien = 10000000 * hsLuong;  // Tiền thưởng chính
					tongTien += 1000000 * hsLuong; // tiền thưởng khi đi làm đầy đủ
				} 
				else if (soNgayNghi != 0 || soNgayNghiCoLyDo != 0) {
					tongTien =10000000 * hsLuong - (soNgayNghi * 300000) - (soNgayNghiCoLyDo * 100000) ;
				} 

				Locale locale = new Locale("vn", "VN");
				NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);
				String moneyString = formatter.format(tongTien);
				
				txtTongTienLuong.setText(moneyString);
			}

		} else if (obj.equals(btnLuuNgayNghi)) {
			if (checkDataNN()) {
				int n = JOptionPane.showConfirmDialog(null, "Bạn có thật sự muốn lưu không?", "Cảnh báo",
						JOptionPane.YES_NO_OPTION);
				if (n == 0) {
					int indexLuong = 0;
					DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
					date1 = LocalDate.now();
					dateCurrently = dtf.format(date1);
					txtNgayTao.setText(dateCurrently);

					//get maLuong
					luongDao = new LuongDao();
					dsLuong = luongDao.getAllLuong();
					luong = new Luong();
					
					for(Luong l : dsLuong) {
						indexLuong++;
					}
					luong.setMaLuong("PL0" + indexLuong);
					
					
					String maNN = txtMaNgayNghi.getText().toString();
					String nguoiTao = txtNguoiTao.getText().toString();
					String lyDo = txtLyDo.getText().toString();
					//Lấy ngày hiện tại
			        long millis=System.currentTimeMillis();  
			        java.sql.Date ngayTao = new java.sql.Date(millis);
					NgayNghi nn = new NgayNghi(maNN, lyDo,ngayTao, nguoiTao, luong);

					if (nnDao.update(nn, txtMaNgayNghi.getText())) {
						modelNN.addRow(
								new Object[] { nn.getMaNgayNghi(), nn.getNguoiTao(), nn.getLyDo(), nn.getNgayTao() });
						JOptionPane.showMessageDialog(null, "Lưu thành công !");
						DocDuLieuDatabaseVaoTableNgayNghi();
						return;
					} else {
						JOptionPane.showMessageDialog(null, "Lưu thất bại");
						return;
					}
				}
			}
		} else if (obj.equals(btnLuu)) {
			if(txtMaNV.getText().equals("") || txtSoNgayNghi.getText().equals("") || txtSoNgayNghiCoLyDo.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!");
				return;
			} else if(Integer.parseInt(txtSoNgayNghi.getText()) < 0 || Integer.parseInt(txtSoNgayNghiCoLyDo.getText()) < 0) {
				JOptionPane.showMessageDialog(this, "Số ngày nghỉ phải lớn hơn bằng 0!");
			}
			
			if(txtTongTienLuong.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Vui lòng tính lương trước rồi mới lưu lương sau!");
				return;
			}
			int choose = JOptionPane.showConfirmDialog(null, "Bạn có thật sự muốn lưu không?", "Cảnh báo",
					JOptionPane.YES_NO_OPTION);
			if (choose == 0) { 				
				if (!txtTongTienLuong.getText().equals("")) {
					int index = 1;
					int indexNN = 1;
					dsLuong = luongDao.getAllLuong();
					for (Luong x : dsLuong) {
						index++;
					}		
					
					//xử lí ngày nghỉ nếu có
					nnDao = new NgayNghiDao();
					dsNN = nnDao.getAllNN();
					for(NgayNghi nn : dsNN) {
						indexNN++;
					}
					NgayNghi ngayNghi = new NgayNghi();
					if(Integer.parseInt(txtSoNgayNghiCoLyDo.getText()) > 0) {
						Luong l = new Luong();
						NhanVien nv = new NhanVien();
						nv.setMaNhanVien(txtMaNV.getText());
						l.setMaLuong("PL0" + index);
						l.setSoNgayNghiCoPhep(Integer.parseInt(txtSoNgayNghiCoLyDo.getText()));
						l.setSoNgayNghiKhongPhep(Integer.parseInt(txtSoNgayNghi.getText()));
						l.setNhanVien(nv);
						long millis = System.currentTimeMillis();
						java.sql.Date date = new java.sql.Date(millis);
						l.setHeSoLuong(Float.parseFloat(txtHeSoLuong.getText()));
						l.setNgayTao(date);
						l.setTongTien((float) tongTien);
						
						System.out.println(l);
						
						
						ngayNghi.setMaNgayNghi("NN00" + indexNN);
						ngayNghi.setLuong(l);
						
						ngayNghi.setLyDo(null);
						ngayNghi.setNguoiTao(null);
						ngayNghi.setNgayTao(null);
						nnDao.create(ngayNghi);
						l.setNgayNghi(ngayNghi);
						if (luongDao.themLuong(l)) {
							JOptionPane.showMessageDialog(this, "Lưu thành công!");
							DocDuLieuDatabaseVaoTableNgayNghi();
							return;
						} else {
							JOptionPane.showMessageDialog(this, "Lưu thất bại!");
							return;
						}
					} else {
						NhanVien nv = new NhanVien();
						nv.setMaNhanVien(txtMaNV.getText());
						Luong luong = new Luong();
						luong.setMaLuong("PL0" + index);
						luong.setSoNgayNghiCoPhep(Integer.parseInt(txtSoNgayNghiCoLyDo.getText()));
						luong.setSoNgayNghiKhongPhep(Integer.parseInt(txtSoNgayNghi.getText()));
						luong.setNhanVien(nv);
						luong.setNgayNghi(ngayNghi);

						long millis = System.currentTimeMillis();
						java.sql.Date date = new java.sql.Date(millis);
						luong.setHeSoLuong(Float.parseFloat(txtHeSoLuong.getText()));
						luong.setNgayTao(date);
						luong.setTongTien((float) tongTien);
						if (luongDao.themLuong(luong)) {
							JOptionPane.showMessageDialog(this, "Lưu thành công!");
							DocDuLieuDatabaseVaoTableNgayNghi();
							return;
						} else {
							JOptionPane.showMessageDialog(this, "Lưu thất bại!");
							return;
						}
					}
					
					
				}	
			}
		}
		
	}
}
