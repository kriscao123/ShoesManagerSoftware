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

import dao.LuongDao;
import dao.NhanVienDao;
import entity.Luong;
import entity.NhanVien;
import generatepdf.GenerateAllPdfNV;
import generatepdf.GenerateBillPdfForNV;


public class FrmPhieuLuongNhanVien extends JFrame implements MouseListener, ActionListener {
	private String result;
	private JPanel panel_QLPhieuLuong;
	private JTextField txtMaPhieuLuong;
	private JTextField txtNguoiLapPL;
	private JTextField txtMaCN;
	private JTextField txtHoTenCN;
	private JTextField txtHeSoLuong;
	private JTextField txtNgayNghiCoPhep;
	private JTextField txtSoNgayNghi;
	private JTextField txtTongTien;
	private JDateChooser dateChooser_NgayTao;
	private JButton btnXuat;
	private JButton btnXuatDS;
	private DefaultTableModel modelPhieuLuongNV;
	private JTable table;
	private JComboBox cmbTimKiem;
	private JTextField txtTimKiem;
	private JButton btnTimKiem;
	private JButton btnLamMoi;
	private LuongDao luongDao;
	private List<Luong> dsLuong;
	private NhanVienDao nvDao;
	private LocalDate date1;
	private String dateCurrently;
	private List<NhanVien> dsNv;
	private List<Luong> listNV;
	
	
	public FrmPhieuLuongNhanVien(String ma) {
		this.result = ma;
		getContentPane().add(taoFrame_PhieuLuong());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1200, 800);
	}
	
	public static void main(String[] args) {
		new FrmPhieuLuongNhanVien("").setVisible(true);
	}

	public JPanel taoFrame_PhieuLuong() {
		panel_QLPhieuLuong = new JPanel();
		panel_QLPhieuLuong.setForeground(new Color(245, 255, 250));
		panel_QLPhieuLuong.setBackground(new Color(255, 192, 203));
		panel_QLPhieuLuong.setLayout(null);

		JPanel panel_ChiTietPhieuLuong = new JPanel();
		panel_ChiTietPhieuLuong.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_ChiTietPhieuLuong.setBackground(new Color(102, 255, 255));
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
		
		txtNguoiLapPL = new JTextField();
		txtNguoiLapPL.setFont(new Font("Verdana", Font.PLAIN, 14));
		txtNguoiLapPL.setColumns(10);
		txtNguoiLapPL.setBorder(new EmptyBorder(0, 5, 0, 0));
		txtNguoiLapPL.setBackground(SystemColor.menu);
		txtNguoiLapPL.setBounds(150, 66, 170, 30);
		panel_ChiTietPhieuLuong.add(txtNguoiLapPL);

		JLabel lblMaCN = new JLabel("Mã nhân viên :");
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

		JLabel lblHoTenCN = new JLabel("Tên Nhân Viên:");
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
		txtHeSoLuong.setBorder(new EmptyBorder(0, 5, 0, 0))	;
		txtHeSoLuong.setBackground(SystemColor.menu);
		txtHeSoLuong.setBounds(150, 218, 170, 30);
		panel_ChiTietPhieuLuong.add(txtHeSoLuong);

		JLabel lblNgaynghicophep = new JLabel("Ngày nghỉ (P):");
		lblNgaynghicophep.setFont(new Font("Verdana", Font.PLAIN, 14));
		lblNgaynghicophep.setBounds(10, 264, 170, 44);
		panel_ChiTietPhieuLuong.add(lblNgaynghicophep);

		txtNgayNghiCoPhep = new JTextField();
		txtNgayNghiCoPhep.setFont(new Font("Verdana", Font.PLAIN, 14));
		txtNgayNghiCoPhep.setColumns(10);
		txtNgayNghiCoPhep.setBorder(new EmptyBorder(0, 5, 0, 0));
		txtNgayNghiCoPhep.setBackground(SystemColor.menu);
		txtNgayNghiCoPhep.setBounds(150, 272, 170, 30);
		panel_ChiTietPhieuLuong.add(txtNgayNghiCoPhep);

		

		JLabel lblSoNgayNghi = new JLabel("Ngày nghỉ (K):");
		lblSoNgayNghi.setFont(new Font("Verdana", Font.PLAIN, 14));
		lblSoNgayNghi.setBounds(10, 318, 170, 44);
		panel_ChiTietPhieuLuong.add(lblSoNgayNghi);
		
		txtSoNgayNghi = new JTextField();
		txtSoNgayNghi.setFont(new Font("Verdana", Font.PLAIN, 14));
		txtSoNgayNghi.setColumns(10);
		txtSoNgayNghi.setBorder(new EmptyBorder(0, 5, 0, 0));
		txtSoNgayNghi.setBackground(SystemColor.menu);
		txtSoNgayNghi.setBounds(150, 326, 170, 30);
		panel_ChiTietPhieuLuong.add(txtSoNgayNghi);

		JLabel lblNgaytao = new JLabel("Ngày tính lương:");
		lblNgaytao.setFont(new Font("Verdana", Font.PLAIN, 14));
		lblNgaytao.setBounds(10, 372, 170, 44);
		panel_ChiTietPhieuLuong.add(lblNgaytao);
			
		dateChooser_NgayTao = new JDateChooser();
		dateChooser_NgayTao.setBounds(150, 380, 170, 30);
		panel_ChiTietPhieuLuong.add(dateChooser_NgayTao);

//		JLabel lblNgayCapNhat = new JLabel("Ngày cập nhật:");
//		lblNgayCapNhat.setFont(new Font("Verdana", Font.PLAIN, 14));
//		lblNgayCapNhat.setBounds(10, 490, 235, 51);
//		panel_ChiTietPhieuLuong.add(lblNgayCapNhat);

		

		JLabel lblTongTien = new JLabel("Tổng Tiền:");
		lblTongTien.setFont(new Font("Verdana", Font.BOLD, 14));
		lblTongTien.setBounds(10, 430, 130, 50);
		panel_ChiTietPhieuLuong.add(lblTongTien);
		
		txtTongTien = new JTextField();
		txtTongTien.setForeground(Color.RED);
		txtTongTien.setFont(new Font("Verdana", Font.BOLD, 20));
		txtTongTien.setColumns(10);
//		txtTongTien.setBorder(new EmptyBorder(0, 5, 0, 0));
		txtTongTien.setBackground(new Color(102, 255, 255));
		txtTongTien.setBounds(150, 438 , 170, 30);
		panel_ChiTietPhieuLuong.add(txtTongTien);

		

//		JDateChooser dateChooser_NgayCapNhat = new JDateChooser();
//		dateChooser_NgayCapNhat.setBounds(234, 496, 212, 38);
//		panel_ChiTietPhieuLuong.add(dateChooser_NgayCapNhat);

		JPanel panel_ChucNang = new JPanel();
		panel_ChucNang.setBackground(new Color(102, 255, 255));
		panel_ChucNang.setBorder(new LineBorder(new Color(0, 0, 0)));
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
		panel_DSPhieuLuong.setBackground(new Color(102, 255, 255));
		panel_DSPhieuLuong.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_DSPhieuLuong.setBounds(390, 60, 758, 662);
		panel_QLPhieuLuong.add(panel_DSPhieuLuong);		
		panel_DSPhieuLuong.setLayout(null);

		/*
		 * Table
		 */
		JScrollPane scrollPane = new JScrollPane();	
		scrollPane.setBounds(10, 50, 740, 604);
		panel_DSPhieuLuong.add(scrollPane);
		String[] header2 = { "Mã PL", "Mã Nhân Viên", "Tên nhân viên", "Hệ số lương", "Ngày nghỉ (P)",
				"Ngày nghỉ", "Ngày tạo", "Tổng tiền" };
		modelPhieuLuongNV = new DefaultTableModel(header2, 0);

		table = new JTable(modelPhieuLuongNV) {
			public boolean editCellAt(int row, int column, EventObject e) { // Không cho chỉnh sửa giá trị trong table
				return false;
			}
		};

		JTableHeader header3 = table.getTableHeader();
		header3.setBackground(new Color(23, 70, 138));
		header3.setForeground(Color.white);
		header3.setFont(new Font("Tahoma", Font.PLAIN, 14));

		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrollPane.setViewportView(table);

		table.setRowHeight(25);

		table.getColumnModel().getColumn(0).setPreferredWidth(40);
		table.getColumnModel().getColumn(1).setPreferredWidth(80);
		table.getColumnModel().getColumn(2).setPreferredWidth(100);
		table.getColumnModel().getColumn(3).setPreferredWidth(80);
		table.getColumnModel().getColumn(4).setPreferredWidth(80);
		table.getColumnModel().getColumn(5).setPreferredWidth(60);
		table.getColumnModel().getColumn(6).setPreferredWidth(60);
		table.getColumnModel().getColumn(7).setPreferredWidth(80);

		/*
		 * End table
		 */

		 cmbTimKiem = new JComboBox();
		cmbTimKiem.setModel(new DefaultComboBoxModel(new String[] { "Tìm kiếm theo", "Mã Lương", "Mã nhân viên", "Ngày Tạo"}));
		cmbTimKiem.setFont(new Font("Verdana", Font.PLAIN, 14));
		cmbTimKiem.setBounds(10, 10, 154, 34);
		panel_DSPhieuLuong.add(cmbTimKiem);

		txtTimKiem = new JTextField();
		txtTimKiem.setFont(new Font("Verdana", Font.PLAIN, 14));
		txtTimKiem.setColumns(10);
		txtTimKiem.setBounds(180, 10, 360, 34);
		panel_DSPhieuLuong.add(txtTimKiem);

//		 cmbSapXep = new JComboBox();
//		cmbSapXep.setModel(
//				new DefaultComboBoxModel(new String[] { "Sắp xếp theo", "Tổng tiền tăng dần", "Tổng tiền giảm dần" }));
//		cmbSapXep.setFont(new Font("Verdana", Font.PLAIN, 14));
//		cmbSapXep.setBounds(10, 90, 198, 51);
//		panel_DSPhieuLuong.add(cmbSapXep);

		btnTimKiem = new JButton("Tìm kiếm"); 
		btnTimKiem.setIcon(new ImageIcon("img\\search.png"));
		btnTimKiem.setBackground(new java.awt.Color(58, 46, 130));
		btnTimKiem.setForeground(Color.WHITE);
		btnTimKiem.setFont(new Font("Verdana", Font.PLAIN, 14));
		btnTimKiem.setBounds(550, 10, 140, 34);
		panel_DSPhieuLuong.add(btnTimKiem);
		
		btnLamMoi = new JButton();
		btnLamMoi.setIcon(new ImageIcon("img\\refresh.png"));
		btnLamMoi.setForeground(Color.WHITE);
		btnLamMoi.setFont(new Font("Verdana", Font.PLAIN, 14));
		btnLamMoi.setBackground(new Color(58, 46, 130));
		btnLamMoi.setBounds(698, 10, 50, 34);
		panel_DSPhieuLuong.add(btnLamMoi);
		
		JLabel lblMaPhieuLuong_1 = new JLabel("Phiếu Lương Nhân Viên ");
		lblMaPhieuLuong_1.setFont(new Font("Verdana", Font.BOLD, 16));
		lblMaPhieuLuong_1.setBounds(20, 10, 373, 51);
		panel_QLPhieuLuong.add(lblMaPhieuLuong_1);
		
		luongDao = new LuongDao();
		dsLuong = luongDao.getAllLuongNV();
		nvDao = new NhanVienDao();

		docDuLieuDatabaseVaoTableHoaDon();
		showName();
		table.addMouseListener(this);
		
		btnTimKiem.addActionListener(this);
		btnXuat.addActionListener(this);
		btnLamMoi.addActionListener(this);
		btnXuatDS.addActionListener(this);
		
		dsNv = nvDao.getAllNV();
		for(NhanVien nv: dsNv) {
			if(nv.getMaNhanVien().equals(this.result)) {
				txtNguoiLapPL.setText(nv.getTenNhanVien());
			}
		}
		
		return panel_QLPhieuLuong;
	}
	
	private void showData() throws ParseException {
		int selectedRow = table.getSelectedRow();
		txtMaPhieuLuong.setText(modelPhieuLuongNV.getValueAt(selectedRow, 0).toString());
		txtMaCN.setText(modelPhieuLuongNV.getValueAt(selectedRow, 1).toString());
		txtHoTenCN.setText(modelPhieuLuongNV.getValueAt(selectedRow, 2).toString());
		txtHeSoLuong.setText(modelPhieuLuongNV.getValueAt(selectedRow, 3).toString());
		txtSoNgayNghi.setText(modelPhieuLuongNV.getValueAt(selectedRow, 5).toString());
		txtNgayNghiCoPhep.setText(modelPhieuLuongNV.getValueAt(selectedRow, 4).toString());
		Date date = new SimpleDateFormat("dd-MM-yyyy").parse(modelPhieuLuongNV.getValueAt(selectedRow, 6).toString());
		dateChooser_NgayTao.setDate(date);
		txtTongTien.setText(modelPhieuLuongNV.getValueAt(selectedRow, 7).toString());

	}
	
	private void showName() {
		luongDao = new LuongDao();
		int index = table.getRowCount();	
		dsLuong = luongDao.getAllLuong();
		Luong luong = new Luong();
		for(int i = 0; i < index; ++i) {
			String maNhanVien = modelPhieuLuongNV.getValueAt(i, 1).toString();
			NhanVien nv = nvDao.getNhanVienTheoMa(maNhanVien);
			modelPhieuLuongNV.setValueAt(nv.getTenNhanVien(), i, 2);
		}
	}
	
	public void docDuLieuDatabaseVaoTableHoaDon() {
		modelPhieuLuongNV.getDataVector().removeAllElements();
		dsLuong = luongDao.getAllLuong();
		for (Luong luong : dsLuong) {
			// Format ngay
			Date date = Calendar.getInstance().getTime();
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			String ngayTao = dateFormat.format(luong.getNgayTao());

			// Get ngày hiện tại
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			date1 = LocalDate.now();
			dateCurrently = dtf.format(date1);

			// format tien

			Locale locale = new Locale("vn", "VN");
			NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);
			String moneyString = formatter.format(luong.getTongTien());
			if (luong.getCongDoan() == null) {
				modelPhieuLuongNV.addRow(new Object[] { luong.getMaLuong(), luong.getNhanVien().getMaNhanVien(),
						luong.getNhanVien().getTenNhanVien(), luong.getHeSoLuong(), luong.getSoNgayNghiCoPhep(),
						luong.getSoNgayNghiKhongPhep(), ngayTao, moneyString });
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		try {
			showData();
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
			      
					new GenerateBillPdfForNV(txtMaPhieuLuong.getText(), txtNguoiLapPL.getText() , txtHoTenCN.getText());
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
			int n = JOptionPane.showConfirmDialog(null, "Bạn có muốn in phiếu lương cho toàn bộ nhân viên hành chánh không?", "Cảnh báo",
					JOptionPane.YES_NO_OPTION);
			if (n == 0) {
				try {
					new GenerateAllPdfNV(txtNguoiLapPL.getText());
					String path="G:/Desktop/hoadon/" +"DanhSachPhieuLuongNV"+ ".pdf";
					File file=new File(path);
					if(file.exists()) {	
						Process process=Runtime.getRuntime().exec("rundll32 url.dll, FileProtocolHandler "+path);
						process.waitFor();
						JOptionPane.showMessageDialog(this, "Xuất phiếu lương thành công!");
					}
					else
					JOptionPane.showMessageDialog(this, "Lỗi xuất danh sách phiếu lương!");
					} catch (Exception e2) {
						// TODO: handle exception
						e2.printStackTrace();
					}
			}
		}
		else if (o.equals(btnTimKiem)) {
			if (cmbTimKiem.getSelectedIndex() == 0) {
				docDuLieuDatabaseVaoTableHoaDon();
				showName();
			}

			else if (cmbTimKiem.getSelectedIndex() == 1) {
				if (txtTimKiem.getText().equals("")) {
					JOptionPane.showMessageDialog(this, "Vui lòng nhập mã lương cần tìm!");
					return;
				}
				modelPhieuLuongNV.getDataVector().removeAllElements();
				int dem = 1;
				listNV = luongDao.getAllLuongNV();
				for (Luong nv : listNV) {
					// Format ngay
					Date date = Calendar.getInstance().getTime();
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					String ngayTao = dateFormat.format(nv.getNgayTao());

					// Get ngày hiện tại
					DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
					date1 = LocalDate.now();
					dateCurrently = dtf.format(date1);

					// format tien

					Locale locale = new Locale("vn", "VN");
					NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);
					String moneyString = formatter.format(nv.getTongTien());
					if (nv.getNhanVien().getMaNhanVien().charAt(0) == 'N') {
						if (nv.getMaLuong().equals(txtTimKiem.getText())) {
							modelPhieuLuongNV.addRow(new Object[] { nv.getMaLuong(), nv.getNhanVien().getMaNhanVien(),
									nv.getNhanVien().getTenNhanVien(), nv.getHeSoLuong(), nv.getSoNgayNghiCoPhep(),
									nv.getSoNgayNghiKhongPhep(), ngayTao, moneyString});
							dem++;
						}
					}
				}
				if (dem == 1) {
					JOptionPane.showMessageDialog(this, "Không tìm thấy phiếu lương cần tìm");
					return;
				}
			}

			else if (cmbTimKiem.getSelectedIndex() == 2) {
				if (txtTimKiem.getText().equals("")) {
					JOptionPane.showMessageDialog(this, "Vui lòng nhập mã nhân viên cần tìm!");
					return;
				}
				modelPhieuLuongNV.getDataVector().removeAllElements();
				int dem = 1;
				listNV = luongDao.getAllLuongNV();
				for (Luong nv : listNV) {
					// Format ngay
					Date date = Calendar.getInstance().getTime();
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					String ngayTao = dateFormat.format(nv.getNgayTao());

					// Get ngày hiện tại
					DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
					date1 = LocalDate.now();
					dateCurrently = dtf.format(date1);

					// format tien

					Locale locale = new Locale("vn", "VN");
					NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);
					String moneyString = formatter.format(nv.getTongTien());

					if (nv.getNhanVien().getMaNhanVien().charAt(0) == 'N') {
						if (nv.getNhanVien().getMaNhanVien().equals(txtTimKiem.getText())) {
							modelPhieuLuongNV.addRow(new Object[] { nv.getMaLuong(), nv.getNhanVien().getMaNhanVien(),
									nv.getNhanVien().getTenNhanVien(), nv.getHeSoLuong(), nv.getSoNgayNghiCoPhep(),
									nv.getSoNgayNghiKhongPhep(), ngayTao, moneyString});
							dem++;
						}

					}
				}
				if (dem == 1) {
					JOptionPane.showMessageDialog(this, "Không tìm thấy nhân viên cần tìm");
					return;
				}
			}
			else if(cmbTimKiem.getSelectedIndex() == 3) {
				//here
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
			
				modelPhieuLuongNV.getDataVector().removeAllElements();
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
			        

			     // Format ngay
					Date date = Calendar.getInstance().getTime();
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					String ngayTao = dateFormat.format(luong.getNgayTao());

					// Get ngày hiện tại
					DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
					date1 = LocalDate.now();
					dateCurrently = dtf.format(date1);

					// format tien

					Locale locale = new Locale("vn", "VN");
					NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);
					String moneyString = formatter.format(luong.getTongTien());
					if (luong.getCongDoan() == null) {
						if(Integer.toString(getLocalDate.getDayOfMonth()).equals(txtTimKiem.getText())) {
							check = false;
							modelPhieuLuongNV.addRow(new Object[] { luong.getMaLuong(), luong.getNhanVien().getMaNhanVien(),
									luong.getNhanVien().getTenNhanVien(), luong.getHeSoLuong(), luong.getSoNgayNghiCoPhep(),
									luong.getSoNgayNghiKhongPhep(), ngayTao, moneyString });
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
		} else if(o.equals(btnLamMoi)) {
			docDuLieuDatabaseVaoTableHoaDon();
			showName();
		}
	}
}
