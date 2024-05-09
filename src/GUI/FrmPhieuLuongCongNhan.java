package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.EventObject;
import java.util.List;
import java.util.Locale;

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

import com.toedter.calendar.JDateChooser;

import dao.CongDoanDao;
import dao.LuongDao;
import dao.NhanVienDao;
import entity.CongDoan;
import entity.Luong;
import entity.NhanVien;
import generatepdf.GenerateAllPdfCN;
import generatepdf.GenerateBillPdfForCN;

public class FrmPhieuLuongCongNhan extends JFrame implements MouseListener, ActionListener {
	private String result;
	private JPanel panel_QLPhieuLuong;
	private JTextField txtMaPhieuLuong;
	private JTextField txtNguoiLap;
	private JLabel lblNgayTao;
	private JDateChooser txtNgayTinhLuong;
	private JTextField txtChiTieu;
	private JTextField txtCongDoan;
	private JTextField txtSoLuongSP;
	private JTextField txtMaCN;
	private JTextField txtHoTenCN;
	private JTextField txtHeSoLuong;
	private JTextField txtTongTien;
	private JButton btnXuat;
	private AbstractButton btnXuatDS;
	private DefaultTableModel modelHoaDon;
	private JTable table;
	private JComboBox cmbTimKiem;
	private JTextField txtTimKiem;
	private JButton btnTim;
	private JButton btnLamMoi;
	private CongDoanDao cdDao;
	private NhanVienDao nvDao;
	private List<CongDoan> dsCd;
	private LuongDao luongDao;
	private List<Luong> dsLuong;
	private List<NhanVien> dsNv;
	private LocalDate date1;
	private String dateCurrently;
	
	
	public FrmPhieuLuongCongNhan(String ma) { 
		this.result = ma;
		getContentPane().add(taoFrame_PhieuLuong());
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setSize(1200,800);
	}
	
	public static void main(String[] args)  {
		new FrmPhieuLuongCongNhan("").setVisible(true);
	}
	
	public JPanel taoFrame_PhieuLuong() {
		panel_QLPhieuLuong = new JPanel();
		panel_QLPhieuLuong.setForeground(new Color(245, 255, 250));
		panel_QLPhieuLuong.setBackground(new Color(84, 255, 159));
		panel_QLPhieuLuong.setLayout(null);
		JPanel panel_ChiTietPhieuLuong = new JPanel();
		panel_ChiTietPhieuLuong.setBackground(new Color(102, 255, 255));
		panel_ChiTietPhieuLuong.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_ChiTietPhieuLuong.setBounds(20, 60, 330, 560);
		panel_QLPhieuLuong.add(panel_ChiTietPhieuLuong);
		panel_ChiTietPhieuLuong.setLayout(null);
		
		JLabel lblMaPhieuLuong = new JLabel("Mã phiếu lương:");
		lblMaPhieuLuong.setFont(new Font("Verdana", Font.PLAIN, 14));
		lblMaPhieuLuong.setBounds(10, 8, 130, 44);
		panel_ChiTietPhieuLuong.add(lblMaPhieuLuong);
		
		txtMaPhieuLuong = new JTextField();
		txtMaPhieuLuong.setFont(new Font("Verdana", Font.PLAIN, 14));
		txtMaPhieuLuong.setColumns(10);
		txtMaPhieuLuong.setBorder(new EmptyBorder(0, 5, 0, 0));
		txtMaPhieuLuong.setBackground(SystemColor.menu);
		txtMaPhieuLuong.setBounds(150, 16, 170, 30);
		panel_ChiTietPhieuLuong.add(txtMaPhieuLuong);
		
		
		
		JLabel lblNguoiLap = new JLabel("Người lập:");
		lblNguoiLap.setFont(new Font("Verdana", Font.PLAIN, 14));
		lblNguoiLap.setBounds(10, 58, 130, 44);
		panel_ChiTietPhieuLuong.add(lblNguoiLap);
		
		txtNguoiLap = new JTextField();
		txtNguoiLap.setFont(new Font("Verdana", Font.PLAIN, 14));
		txtNguoiLap.setColumns(10);
		txtNguoiLap.setBorder(new EmptyBorder(0, 5, 0, 0));
		txtNguoiLap.setBackground(SystemColor.menu);
		txtNguoiLap.setBounds(150, 66, 170, 30);
		panel_ChiTietPhieuLuong.add(txtNguoiLap);
		
		JLabel lblMaCN = new JLabel("Mã công nhân:");
		lblMaCN.setFont(new Font("Verdana", Font.PLAIN, 14));
		lblMaCN.setBounds(10, 108, 130, 44);
		panel_ChiTietPhieuLuong.add(lblMaCN);
		
		txtMaCN = new JTextField();
		txtMaCN.setFont(new Font("Verdana", Font.PLAIN, 14));
		txtMaCN.setColumns(10);
		txtMaCN.setBorder(new EmptyBorder(0, 5, 0, 0));
		txtMaCN.setBackground(SystemColor.menu);
		txtMaCN.setBounds(150, 116, 170, 30);
		panel_ChiTietPhieuLuong.add(txtMaCN);
		
		JLabel lblHoTenCN = new JLabel("Tên Công Nhân:");
		lblHoTenCN.setFont(new Font("Verdana", Font.PLAIN, 14));
		lblHoTenCN.setBounds(10, 158, 130, 44);
		panel_ChiTietPhieuLuong.add(lblHoTenCN);
		
		txtHoTenCN = new JTextField();
		txtHoTenCN.setFont(new Font("Verdana", Font.PLAIN, 14));
		txtHoTenCN.setColumns(10);
		txtHoTenCN.setBorder(new EmptyBorder(0, 5, 0, 0));
		txtHoTenCN.setBackground(SystemColor.menu);
		txtHoTenCN.setBounds(150, 166, 170, 30);
		panel_ChiTietPhieuLuong.add(txtHoTenCN);
		
		JLabel lblHeSoLuong = new JLabel("Hệ số lương:");
		lblHeSoLuong.setFont(new Font("Verdana", Font.PLAIN, 14));
		lblHeSoLuong.setBounds(10, 210, 170, 44);
		panel_ChiTietPhieuLuong.add(lblHeSoLuong);

		txtHeSoLuong = new JTextField();
		txtHeSoLuong.setFont(new Font("Verdana", Font.PLAIN, 14));
		txtHeSoLuong.setColumns(10);
		txtHeSoLuong.setBorder(new EmptyBorder(0, 5, 0, 0));
		txtHeSoLuong.setBackground(SystemColor.menu);
		txtHeSoLuong.setBounds(150, 218, 170, 30);
		panel_ChiTietPhieuLuong.add(txtHeSoLuong);
		
		
		JLabel lblSoSanPhamHoanThien = new JLabel("SP hoàn thiện:");
		lblSoSanPhamHoanThien.setFont(new Font("Verdana", Font.PLAIN, 14));
		lblSoSanPhamHoanThien.setBounds(10, 264, 170, 44);
		panel_ChiTietPhieuLuong.add(lblSoSanPhamHoanThien);
		
		txtSoLuongSP = new JTextField();
		txtSoLuongSP.setFont(new Font("Verdana", Font.PLAIN, 14));
		txtSoLuongSP.setColumns(10);
		txtSoLuongSP.setBorder(new EmptyBorder(0, 5, 0, 0));
		txtSoLuongSP.setBackground(SystemColor.menu);
		txtSoLuongSP.setBounds(150, 272, 170, 30);
		panel_ChiTietPhieuLuong.add(txtSoLuongSP);
		
		JLabel lblCongDoan = new JLabel("Công đoạn:");
		lblCongDoan.setFont(new Font("Verdana", Font.PLAIN, 14));
		lblCongDoan.setBounds(10, 318, 170, 44);
		panel_ChiTietPhieuLuong.add(lblCongDoan);
		
		txtCongDoan = new JTextField();
		txtCongDoan.setFont(new Font("Verdana", Font.PLAIN, 14));
		txtCongDoan.setColumns(10);
		txtCongDoan.setBorder(new EmptyBorder(0, 5, 0, 0));
		txtCongDoan.setBackground(SystemColor.menu);
		txtCongDoan.setBounds(150, 326, 170, 30);
		panel_ChiTietPhieuLuong.add(txtCongDoan);
		
		JLabel lblChiTieu = new JLabel("Chỉ Tiêu:");
		lblChiTieu.setFont(new Font("Verdana", Font.PLAIN, 14));
		lblChiTieu.setBounds(10, 372, 170, 44);
		panel_ChiTietPhieuLuong.add(lblChiTieu);
		
		txtChiTieu = new JTextField();
		txtChiTieu.setFont(new Font("Verdana", Font.PLAIN, 14));
		txtChiTieu.setColumns(10);
		txtChiTieu.setBorder(new EmptyBorder(0, 5, 0, 0));
		txtChiTieu.setBackground(SystemColor.menu);
		txtChiTieu.setBounds(150, 380, 170, 30);
		panel_ChiTietPhieuLuong.add(txtChiTieu);
		
		lblNgayTao = new JLabel("Ngày tính lương:");
		lblNgayTao.setFont(new Font("Verdana", Font.PLAIN, 14));
		lblNgayTao.setBounds(10, 428, 170, 44);
		panel_ChiTietPhieuLuong.add(lblNgayTao);
		
		txtNgayTinhLuong = new JDateChooser();
		txtNgayTinhLuong.setBounds(150, 436, 170, 30);
		panel_ChiTietPhieuLuong.add(txtNgayTinhLuong);
		
		JLabel lblTongTien = new JLabel("Tổng Tiền:");
		lblTongTien.setFont(new Font("Verdana", Font.BOLD, 16));
		lblTongTien.setBounds(10, 490, 170, 44);
		panel_ChiTietPhieuLuong.add(lblTongTien);
		
		
		txtTongTien = new JTextField();
		txtTongTien.setForeground(new Color(255, 0, 0));
		txtTongTien.setFont(new Font("Verdana", Font.BOLD, 20));
		txtTongTien.setEditable(false);
		txtTongTien.setColumns(10);
//		txtTongTien.setBorder(new EmptyBorder(0, 10, 0, 0));
		txtTongTien.setBackground(new Color(102, 255, 255));
//		txtTongTien.setBounds(220, 337, 190, 43);
		txtTongTien.setBounds(150, 498, 170, 30);
		panel_ChiTietPhieuLuong.add(txtTongTien);
		
		JLabel lblMaPhieuLuong_1 = new JLabel("Phiếu Lương Công Nhân");
		lblMaPhieuLuong_1.setFont(new Font("Verdana", Font.BOLD, 16));
		lblMaPhieuLuong_1.setBounds(20, 10, 373, 51);
		panel_QLPhieuLuong.add(lblMaPhieuLuong_1);
		
		JPanel panel_ChucNang = new JPanel();
		panel_ChucNang.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_ChucNang.setBackground(new Color(102, 255, 255));
		panel_ChucNang.setBounds(20, 652, 330, 60);
		panel_QLPhieuLuong.add(panel_ChucNang);
		panel_ChucNang.setLayout(null);
		
		btnXuat = new JButton("Xuất");
		btnXuat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnXuat.setIcon(new ImageIcon("img\\file.png"));
		btnXuat.setForeground(Color.WHITE);
		btnXuat.setFont(new Font("Verdana", Font.PLAIN, 16));
		btnXuat.setBackground(new java.awt.Color(58, 46, 130));
		btnXuat.setBounds(20, 10, 130, 40);
		panel_ChucNang.add(btnXuat);
		
		btnXuatDS = new JButton("Xuất DS");
		btnXuatDS.setIcon(new ImageIcon("img\\file.png"));
		btnXuatDS.setForeground(Color.WHITE);
		btnXuatDS.setFont(new Font("Verdana", Font.PLAIN, 16));
		btnXuatDS.setBackground(new Color(58, 46, 130));
		btnXuatDS.setBounds(170, 10, 140, 40);
		panel_ChucNang.add(btnXuatDS);
		
		JPanel panel_DSPhieuLuong = new JPanel();
		panel_DSPhieuLuong.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_DSPhieuLuong.setBackground(new Color(102, 255, 255));
		panel_DSPhieuLuong.setBounds(390, 60, 758, 662);
		panel_QLPhieuLuong.add(panel_DSPhieuLuong);
		panel_DSPhieuLuong.setLayout(null);
	
		
		/*
		 * Start table
		 */
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 50, 740, 604);
		panel_DSPhieuLuong.add(scrollPane);
		String[] header = { "Mã PL", "Mã Nhân Viên", "Tên nhân viên", "Công đoạn", "Số sản phẩm", "Hệ số", "Ngày tạo",
				"Tổng tiền"};
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

		table.setFont(new Font("Tahoma", Font.PLAIN, 17));
		scrollPane.setViewportView(table);
		table.setRowHeight(25);
		
		table.getColumnModel().getColumn(0).setPreferredWidth(60);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().getColumn(2).setPreferredWidth(120);
		table.getColumnModel().getColumn(3).setPreferredWidth(100);
		table.getColumnModel().getColumn(4).setPreferredWidth(100);
		table.getColumnModel().getColumn(5).setPreferredWidth(60);
		table.getColumnModel().getColumn(6).setPreferredWidth(100);
		table.getColumnModel().getColumn(7).setPreferredWidth(120);
		/*
		 * End table
		 */
		
		cmbTimKiem = new JComboBox();
		cmbTimKiem.setModel(new DefaultComboBoxModel(new String[] {"Tìm kiếm theo", "Tên công nhân", "Mã công nhân", "Ngày tính lương"}));
		cmbTimKiem.setFont(new Font("Verdana", Font.PLAIN, 14));
		cmbTimKiem.setBounds(10, 10, 154, 34);
		panel_DSPhieuLuong.add(cmbTimKiem);
		
		txtTimKiem = new JTextField();
		txtTimKiem.setFont(new Font("Verdana", Font.PLAIN, 14));
		txtTimKiem.setColumns(10);
		txtTimKiem.setBounds(180, 10, 360, 34);
		panel_DSPhieuLuong.add(txtTimKiem);
		
//		cmbSapXep = new JComboBox();
//		cmbSapXep.setModel(new DefaultComboBoxModel(new String[] {"Sắp xếp theo ", "Tổng tiền tăng dần", "Tổng tiền giảm dần"}));
//		cmbSapXep.setFont(new Font("Verdana", Font.PLAIN, 16));
//		cmbSapXep.setBounds(10, 75, 198, 51);
//		panel_DSPhieuLuong.add(cmbSapXep);
		
		btnTim = new JButton("Tìm kiếm");
		btnTim.setIcon(new ImageIcon("img\\search.png"));
		btnTim.setBackground(new java.awt.Color(58, 46, 130));
		btnTim.setForeground(Color.WHITE);
		btnTim.setFont(new Font("Verdana", Font.PLAIN, 14));
		btnTim.setBounds(550, 10, 140, 34);
		panel_DSPhieuLuong.add(btnTim);
		
		btnLamMoi = new JButton();
		btnLamMoi.setIcon(new ImageIcon("img\\refresh.png"));
		btnLamMoi.setForeground(Color.WHITE);
		btnLamMoi.setFont(new Font("Verdana", Font.PLAIN, 14));
		btnLamMoi.setBackground(new Color(58, 46, 130));
		btnLamMoi.setBounds(698, 10, 50, 34);
		panel_DSPhieuLuong.add(btnLamMoi);
		
		luongDao = new LuongDao();
		dsLuong = luongDao.getAllLuongNV();
		cdDao = new CongDoanDao();
		nvDao = new NhanVienDao();
		
		docDuLieuDatabaseVaoTableHoaDon();
		showName();
		showCongDoan();
		table.addMouseListener(this);
		
		btnXuat.addActionListener(this);
		btnTim.addActionListener(this);
		btnLamMoi.addActionListener(this);
		btnXuatDS.addActionListener(this);
	
		dsNv = nvDao.getAllNV();
		for(NhanVien nv: dsNv) {
			if(nv.getMaNhanVien().equals(this.result)) {
				txtNguoiLap.setText(nv.getTenNhanVien());
			}
		}
		
		return panel_QLPhieuLuong;
	}
	
	public void docDuLieuDatabaseVaoTableHoaDon() {
		modelHoaDon.getDataVector().removeAllElements();
		int dem = 1;
		dsLuong = luongDao.getAllLuong();
		dsNv = nvDao.getAllNV();
		dsCd = cdDao.getAllCD();
		
		for (Luong luong : dsLuong) {
			//Format ngay
	        Date date = Calendar.getInstance().getTime();  
	        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");  
	        String ngayTao = dateFormat.format(luong.getNgayTao());  
	        
	        //Get ngày hiện tại
			 DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			 date1 = LocalDate.now();
			 dateCurrently = dtf.format(date1);

			 //format tien
				Locale locale = new Locale("vn", "VN");  
				NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);
				String moneyString = formatter.format(luong.getTongTien());
	        if(luong.getCongDoan() != null) {
				modelHoaDon.addRow(new Object[] {luong.getMaLuong(), luong.getNhanVien().getMaNhanVien(),
						luong.getNhanVien().getTenNhanVien(), luong.getCongDoan(), luong.getSoSanPhamHoanThien(),
						luong.getHeSoLuong(), ngayTao, moneyString});
	        }
		}
	}
	
	private void showName() {
		luongDao = new LuongDao();
		int index = table.getRowCount();	
		dsLuong = luongDao.getAllLuong();
		Luong luong = new Luong();
		for(int i = 0; i < index; ++i) {
			String maNhanVien = modelHoaDon.getValueAt(i, 1).toString();
			NhanVien nv = nvDao.getNhanVienTheoMa(maNhanVien);
			modelHoaDon.setValueAt(nv.getTenNhanVien(), i, 2);
			//here
		}
	}

	
	private void showCongDoan() {
		dsCd = cdDao.getAllCD();
		for(CongDoan cd : dsCd) {
			if(txtCongDoan.getText().equals(cd.getTenCongDoan()));
				txtChiTieu.setText(Integer.toString(cd.getChiTieu()));
		}
	}
	
	
	private void showData() throws ParseException {
		int selectedRow = table.getSelectedRow();
		txtMaPhieuLuong.setText(modelHoaDon.getValueAt(selectedRow, 0).toString());
		txtMaCN.setText(modelHoaDon.getValueAt(selectedRow, 1).toString());
		txtHoTenCN.setText(modelHoaDon.getValueAt(selectedRow, 2).toString());
		txtCongDoan.setText(modelHoaDon.getValueAt(selectedRow, 3).toString());
		txtSoLuongSP.setText(modelHoaDon.getValueAt(selectedRow, 4).toString());
		txtHeSoLuong.setText(modelHoaDon.getValueAt(selectedRow, 5).toString());
		Date date = new SimpleDateFormat("dd-MM-yyyy").parse(modelHoaDon.getValueAt(selectedRow, 6).toString());
		txtNgayTinhLuong.setDate(date);
		txtTongTien.setText(modelHoaDon.getValueAt(selectedRow, 7).toString());
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		try {
			showData();
//			showCongDoan();
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
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
		Object o = e.getSource();
		if(o.equals(btnXuat)) {
			int n = JOptionPane.showConfirmDialog(null, "Bạn có muốn in phiếu lương này không?", "Cảnh báo",
					JOptionPane.YES_NO_OPTION);
			if (n == 0) {
				try {
					String str = txtHoTenCN.getText();
			        String noSpaceStr = str.replaceAll("\\s", "");
			        
					new GenerateBillPdfForCN(txtMaPhieuLuong.getText(), txtNguoiLap.getText(), txtHoTenCN.getText());
					String path="G:/Desktop/hoadon/" + txtMaPhieuLuong.getText().trim() + "_" + noSpaceStr + ".pdf";
					File file=new File(path);
					if(file.exists()) {	
						Process process=Runtime.getRuntime().exec("rundll32 url.dll, FileProtocolHandler "+path);
						process.waitFor();
						JOptionPane.showMessageDialog(this, "Xuất phiếu lương thành công!");
					}
					else
					JOptionPane.showMessageDialog(this, "Xuất phiếu lương thành công!");
					
					} catch (Exception e2) {
						// TODO: handle exception
						e2.printStackTrace();
					}
			}
		}
		else if(o.equals(btnXuatDS)) {
			int n = JOptionPane.showConfirmDialog(null, "Bạn có muốn in phiếu lương cho toàn bộ công nhân không?", "Cảnh báo",
					JOptionPane.YES_NO_OPTION);
			if (n == 0) {
				try {
					new GenerateAllPdfCN(txtNguoiLap.getText());
					String path="G:/Desktop/hoadon/" +"DanhSachPhieuLuongCN"+ ".pdf";
					File file=new File(path);
					if(file.exists()) {	
						Process process=Runtime.getRuntime().exec("rundll32 url.dll, FileProtocolHandler "+path);
						process.waitFor();
					}
					else
					JOptionPane.showMessageDialog(this, "Lỗi xuất danh sách phiếu lương!");
					
					} catch (Exception e2) {
						// TODO: handle exception
						e2.printStackTrace();
					}
			}
		}
		else if(o.equals(btnTim)) {
			if (cmbTimKiem.getSelectedIndex() == 0) {
				docDuLieuDatabaseVaoTableHoaDon();
				showName();
			}
			else if(cmbTimKiem.getSelectedIndex() == 1) {
				if (txtTimKiem.getText().equals("")) {
					JOptionPane.showMessageDialog(this, "Vui lòng nhập tên nhân viên cần tìm!");
					return;
				}
			
				modelHoaDon.getDataVector().removeAllElements();
				int dem = 1;
				dsLuong = luongDao.getAllLuong();
				nvDao = new NhanVienDao();
				dsNv = nvDao.getAllNV();
				String ma = "";
				for(NhanVien nv : dsNv) {
					if(nv.getTenNhanVien().equals(txtTimKiem.getText()))
						ma = nv.getMaNhanVien();
				}
				
				for(Luong luong : dsLuong) {
					//Format ngay
			        Date date = Calendar.getInstance().getTime();  
			        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");  
			        String ngayTao = dateFormat.format(luong.getNgayTao());  
			        
			        //Get ngày hiện tại
					 DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
					 date1 = LocalDate.now();
					 dateCurrently = dtf.format(date1);

					 //format tien
						Locale locale = new Locale("vn", "VN");  
						NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);
						String moneyString = formatter.format(luong.getTongTien());
			        if(luong.getCongDoan() != null) {
			        	if(luong.getNhanVien().getMaNhanVien().equals(ma)) {
							modelHoaDon.addRow(new Object[] {luong.getMaLuong(), luong.getNhanVien().getMaNhanVien(),
									luong.getNhanVien().getTenNhanVien(), luong.getCongDoan(), luong.getSoSanPhamHoanThien(),
									luong.getHeSoLuong(), ngayTao, moneyString});
							dem++;
			        	}
			        }
				showName();
				}
				if (dem == 1) {
					JOptionPane.showMessageDialog(this, "Không tìm thấy phiếu lương cần tìm");
					docDuLieuDatabaseVaoTableHoaDon();
					showName();
					return;
				}
				showName();
			}
			
			else if(cmbTimKiem.getSelectedIndex() == 2) {
				if (txtTimKiem.getText().equals("")) {
					JOptionPane.showMessageDialog(this, "Vui lòng nhập mã công nhân cần tìm!");
					return;
				}
			
				modelHoaDon.getDataVector().removeAllElements();
				int dem = 1;
				dsLuong = luongDao.getAllLuong();
				nvDao = new NhanVienDao();
				dsNv = nvDao.getAllNV();	
				for(Luong luong : dsLuong) {
					//Format ngay
			        Date date = Calendar.getInstance().getTime();  
			        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");  
			        String ngayTao = dateFormat.format(luong.getNgayTao());  
			        
			        //Get ngày hiện tại
					 DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
					 date1 = LocalDate.now();
					 dateCurrently = dtf.format(date1);

					 //format tien
						Locale locale = new Locale("vn", "VN");  
						NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);
						String moneyString = formatter.format(luong.getTongTien());
			        if(luong.getCongDoan() != null) {
			        	if(luong.getNhanVien().getMaNhanVien().equals(txtTimKiem.getText())) {
							modelHoaDon.addRow(new Object[] {luong.getMaLuong(), luong.getNhanVien().getMaNhanVien(),
									luong.getNhanVien().getTenNhanVien(), luong.getCongDoan(), luong.getSoSanPhamHoanThien(),
									luong.getHeSoLuong(), ngayTao, moneyString});
							dem++;
			        	}
			        }
				showName();
				}
				if (dem == 1) {
					JOptionPane.showMessageDialog(this, "Không tìm thấy phiếu lương cần tìm");
					docDuLieuDatabaseVaoTableHoaDon();
					showName();
					return;
				}
				showName();
			}
			
			else if(cmbTimKiem.getSelectedIndex() == 3) {
				if (txtTimKiem.getText().equals("")) {
					JOptionPane.showMessageDialog(this, "Vui lòng nhập ngày cần tìm!");
					return;
				}
				
				try {
					Integer.parseInt(txtTimKiem.getText());
				} catch (Exception e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(this, "Ngày phải là số nguyên!");
					return;
				}
				
				if(Integer.parseInt(txtTimKiem.getText()) < 1 || Integer.parseInt(txtTimKiem.getText()) > 31) {
					JOptionPane.showMessageDialog(this, "Ngày phải thỏa: 0 < x < 32");
					return;
				}
				
				boolean check = true;
			
				modelHoaDon.getDataVector().removeAllElements();
				int dem = 1;
				dsLuong = luongDao.getAllLuong();
				nvDao = new NhanVienDao();
				dsNv = nvDao.getAllNV();	
				for(Luong luong : dsLuong) {
					//Covert from java sql date to string
					DateFormat df = new SimpleDateFormat("dd/MM/yyyy");  
					String text = df.format(luong.getNgayTao()); 
					
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
			        
					//Format ngay
			        Date date = Calendar.getInstance().getTime();  
			        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");  
			        String ngayTao = dateFormat.format(luong.getNgayTao());  
			        
			        //Get ngày hiện tại
					 DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
					 date1 = LocalDate.now();
					 dateCurrently = dtf.format(date1);

					 //format tien
						Locale locale = new Locale("vn", "VN");  
						NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);
						String moneyString = formatter.format(luong.getTongTien());
			        if(luong.getCongDoan() != null) {
			        	if(Integer.toString(getLocalDate.getDayOfMonth()).equals(txtTimKiem.getText())) {
			        		check = false;
							modelHoaDon.addRow(new Object[] {luong.getMaLuong(), luong.getNhanVien().getMaNhanVien(),
									luong.getNhanVien().getTenNhanVien(), luong.getCongDoan(), luong.getSoSanPhamHoanThien(),
									luong.getHeSoLuong(), ngayTao, moneyString});
			        	}
			        }
				showName();
				}
				if (check) {
					JOptionPane.showMessageDialog(this, "Không tìm thấy phiếu lương cần tìm");
					docDuLieuDatabaseVaoTableHoaDon();
					showName();
					return;
				}
				showName();
			}
		}
		else if(o.equals(btnLamMoi)) {
			docDuLieuDatabaseVaoTableHoaDon();
			showName();
		}
	}
}
