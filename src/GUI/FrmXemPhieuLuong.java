package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import dao.CongDoanDao;
import dao.LuongDao;
import dao.NhanVienDao;
import entity.CongDoan;
import entity.Luong;
import entity.NhanVien;
import generatepdf.GenerateAllPdfForOneCN;
import generatepdf.GenerateAllPdfForOneNV;
import generatepdf.GenerateBillPdfForCN;
import generatepdf.GenerateBillPdfForNV;
public class FrmXemPhieuLuong extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panel_QLTaiKhoan;
	private JTable table;
	private String name;
	private JButton btnTimKiem, btnXuatTatCa;
	private JComboBox cmbTimKiem;
	private JTextField txtTimKiem;
	private DefaultTableModel modelHoaDon;
	private JButton btnXuat;
	private LuongDao luongDao;
	private CongDoanDao cdDao;
	private NhanVienDao nvDao;
	private List<Luong> dsLuong;
	private List<NhanVien> dsNv;
	private List<CongDoan> dsCd;
	private LocalDate date1;
	private String dateCurrently;
	private JLabel lbQLTaiKhoan;
	private String title;
	
	public FrmXemPhieuLuong(String text) { 
		this.name = text;
		getContentPane().add(taoFrame_XemPhieuLuong(name)); 
		taoFrame_XemPhieuLuong(name);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setSize(1200,800);
	}

	public static void main(String[] args) {
		new FrmXemPhieuLuong("").setVisible(true);
	}
	public JPanel taoFrame_XemPhieuLuong(String name) {
		panel_QLTaiKhoan = new JPanel();
		panel_QLTaiKhoan.setBackground(new Color(204, 255, 255));
		panel_QLTaiKhoan.setLayout(null);
		
		btnXuat = new JButton("Xuất");
		btnXuat.setIcon(new ImageIcon("img\\file.png"));
		btnXuat.setForeground(Color.WHITE);
		btnXuat.setFont(new Font("Verdana", Font.BOLD, 16));
		btnXuat.setBackground(new Color(58, 46, 130));
		btnXuat.setBounds(37, 129, 140, 40);
		panel_QLTaiKhoan.add(btnXuat);
		
		btnXuatTatCa = new JButton("Xuất tất cả");
		btnXuatTatCa.setIcon(new ImageIcon("img\\file.png"));
		btnXuatTatCa.setForeground(Color.WHITE);
		btnXuatTatCa.setFont(new Font("Verdana", Font.BOLD, 16));
		btnXuatTatCa.setBackground(new Color(58, 46, 130));
		btnXuatTatCa.setBounds(200, 129, 180, 40);
		panel_QLTaiKhoan.add(btnXuatTatCa);
		
		
		if(name.charAt(0) == 'C') {
			title = "Giao Diện Xem Phiếu Lương Công Nhân";
		} else {
			title = "Giao Diện Xem Phiếu Lương Nhân Viên Hành Chánh";
		}
		lbQLTaiKhoan = new JLabel(title);
		lbQLTaiKhoan.setBounds(50, 40, 1500, 50);
		lbQLTaiKhoan.setFont(new Font("Verdana", Font.BOLD, 22));
		panel_QLTaiKhoan.add(lbQLTaiKhoan);
		
		cmbTimKiem = new JComboBox();
		cmbTimKiem.setFont(new Font("Verdana", Font.PLAIN, 16));
		cmbTimKiem.setModel(new DefaultComboBoxModel(new String[] {"Tìm kiếm theo", "Theo tháng ", "Theo năm"}));
		cmbTimKiem.setBounds(420, 129, 160, 40);
		panel_QLTaiKhoan.add(cmbTimKiem);
		
		txtTimKiem = new JTextField();
		txtTimKiem.setFont(new Font("Verdana", Font.PLAIN, 16));
		txtTimKiem.setBounds(620, 129, 260, 40);
		txtTimKiem.setColumns(10);
		panel_QLTaiKhoan.add(txtTimKiem);

		
		btnTimKiem = new JButton("Tìm kiếm");
		btnTimKiem.setIcon(new ImageIcon("img\\search.png"));
		btnTimKiem.setForeground(Color.WHITE);
		btnTimKiem.setFont(new Font("Verdana", Font.PLAIN, 18));
		btnTimKiem.setBackground(new Color(58, 46, 130));
		btnTimKiem.setBounds(930, 129, 180, 40);
		panel_QLTaiKhoan.add(btnTimKiem);
		
		
		JPanel panel_ChiTiet = new JPanel();
		panel_ChiTiet.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_ChiTiet.setBackground(new Color(102, 255, 255));
		panel_ChiTiet.setBounds(37, 201, 1090, 540);
		panel_QLTaiKhoan.add(panel_ChiTiet);
		panel_ChiTiet.setLayout(null);
		
		//xu li
		luongDao = new LuongDao();
		cdDao = new CongDoanDao();
		nvDao = new NhanVienDao();
		
		if(name.charAt(0) == 'C') {
			/*
			 * Start table
			 */
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 11, 1070, 520);
			panel_ChiTiet.add(scrollPane);
			String[] header = { "Mã lương", "Mã Nhân Viên", "Tên nhân viên", "Công đoạn", "Số sản phẩm", "Hệ số", "Ngày tạo",
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
			table.getColumnModel().getColumn(1).setPreferredWidth(80);
			table.getColumnModel().getColumn(2).setPreferredWidth(120);
			table.getColumnModel().getColumn(3).setPreferredWidth(140);
			table.getColumnModel().getColumn(4).setPreferredWidth(80);
			table.getColumnModel().getColumn(5).setPreferredWidth(60);
			table.getColumnModel().getColumn(6).setPreferredWidth(100);
			table.getColumnModel().getColumn(7).setPreferredWidth(160);
			scrollPane.setViewportView(table);
			docDuLieuDatabaseVaoTableHoaDonCongNhan();
			showName();
			/*
			 * End table
			 */
		}
		
		
		if(name.charAt(0) == 'N') {
			/*
			 * Start table
			 */
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 11, 1070, 520);
			panel_ChiTiet.add(scrollPane);
			String[] header = { "Mã lương", "Mã Nhân Viên", "Tên nhân viên", "Số ngày nghỉ có phép", "Số ngày nghỉ", "Hệ số", "Ngày tạo",
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
			table.getColumnModel().getColumn(1).setPreferredWidth(80);
			table.getColumnModel().getColumn(2).setPreferredWidth(140);
			table.getColumnModel().getColumn(3).setPreferredWidth(100);
			table.getColumnModel().getColumn(4).setPreferredWidth(80);
			table.getColumnModel().getColumn(5).setPreferredWidth(120);
			table.getColumnModel().getColumn(6).setPreferredWidth(120);
			scrollPane.setViewportView(table);
			docDuLieuDatabaseVaoTableHoaDonNhanVien();
			showName();
			/*
			 * End table
			 */
		}
		
		nvDao = new NhanVienDao();

		btnXuat.addActionListener(this);
		btnXuatTatCa.addActionListener(this);
		btnTimKiem.addActionListener(this);
		return panel_QLTaiKhoan;
		//pack
	}
	
	public void docDuLieuDatabaseVaoTableHoaDonCongNhan() {
		modelHoaDon.getDataVector().removeAllElements();
		int dem = 1;
		dsLuong = luongDao.getAllLuong();
		dsNv = nvDao.getAllNV();
		dsCd = cdDao.getAllCD();
		
		for (Luong luong : dsLuong) {
			//Format ngay
			if(luong.getNhanVien().getMaNhanVien().equals(name)) {
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
	}
	
	
	public void docDuLieuDatabaseVaoTableHoaDonNhanVien() {
		modelHoaDon.getDataVector().removeAllElements();
		int dem = 1;
		dsLuong = luongDao.getAllLuong();
		for (Luong luong : dsLuong) {
			// Format ngay
			if(luong.getNhanVien().getMaNhanVien().equals(name)) {
				Date date = Calendar.getInstance().getTime();
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				String ngayTao = dateFormat.format(luong.getNgayTao());

				// format tien

				Locale locale = new Locale("vn", "VN");
				NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);
				String moneyString = formatter.format(luong.getTongTien());
				if (luong.getCongDoan() == null) {
					modelHoaDon.addRow(new Object[] { luong.getMaLuong(), luong.getNhanVien().getMaNhanVien(),
							luong.getNhanVien().getTenNhanVien(),luong.getSoNgayNghiCoPhep(), luong.getSoNgayNghiKhongPhep(), luong.getHeSoLuong(),
							ngayTao, moneyString });
				}
			}
		}
	}
	
	
	private void showName() {
		luongDao = new LuongDao();
		int index = modelHoaDon.getRowCount();	
		dsLuong = luongDao.getAllLuong();
		Luong luong = new Luong();
		for(int i = 0; i < index; ++i) {
			String maNhanVien = modelHoaDon.getValueAt(i, 1).toString();
			NhanVien nv = nvDao.getNhanVienTheoMa(maNhanVien);
			modelHoaDon.setValueAt(nv.getTenNhanVien(), i, 2);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if(o.equals(btnXuat)) {
			int selectedRow = table.getSelectedRow();
			if(selectedRow == -1) {
				JOptionPane.showMessageDialog(this, "Vui lòng chọn một phiếu lương cần xuất!");
				return;
			}
			int n = JOptionPane.showConfirmDialog(null, "Bạn có muốn xuất phiếu lương này không?", "Cảnh báo",
					JOptionPane.YES_NO_OPTION);
			if (n == 0) {
				if(name.charAt(0) == 'C') {
					try {
						String maLuong = modelHoaDon.getValueAt(selectedRow, 0).toString();
						String tenNV = modelHoaDon.getValueAt(selectedRow, 2).toString();
						String noSpaceStr = tenNV.replaceAll("\\s", "");
				        
						new GenerateBillPdfForCN(maLuong, tenNV, tenNV);
						String path="G:/Desktop/hoadon/" + maLuong.trim() + "_" + noSpaceStr + ".pdf";
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
				
				else if(name.charAt(0) == 'N') {
					try {
						String maLuong = modelHoaDon.getValueAt(selectedRow, 0).toString();
						String tenNV = modelHoaDon.getValueAt(selectedRow, 2).toString();
						String noSpaceStr = tenNV.replaceAll("\\s", "");
				        
						new GenerateBillPdfForNV(maLuong, tenNV, tenNV);
						String path="G:/Desktop/hoadon/" + maLuong.trim() + "_" + noSpaceStr + ".pdf";
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
		}
		
		else if(o.equals(btnXuatTatCa)) {
			int n = JOptionPane.showConfirmDialog(null, "Bạn có muốn xuất toàn bộ phiếu lương không?", "Cảnh báo",
					JOptionPane.YES_NO_OPTION);
			if (n == 0) {
				if(name.charAt(0) == 'C') {
					try {
						nvDao = new NhanVienDao();
						dsNv = nvDao.getAllNV();
						
						String tenNV = "";
						for(NhanVien nv : dsNv) {
							if(nv.getMaNhanVien().equals(name)) {
								tenNV = nv.getTenNhanVien();
							}
						}
						
						String noSpaceStr = tenNV.replaceAll("\\s", "");
				        
						new GenerateAllPdfForOneCN(tenNV, name);
						String path="G:/Desktop/hoadon/" +"DS_" + name + "_" + noSpaceStr + ".pdf";
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
				if(name.charAt(0) == 'N') {
					try {
						nvDao = new NhanVienDao();
						dsNv = nvDao.getAllNV();
						
						String tenNV = "";
						for(NhanVien nv : dsNv) {
							if(nv.getMaNhanVien().equals(name)) {
								tenNV = nv.getTenNhanVien();
							}
						}
						
						String noSpaceStr = tenNV.replaceAll("\\s", "");
				        
						new GenerateAllPdfForOneNV(tenNV, name);
						String path="G:/Desktop/hoadon/" +"DS_" + name + "_" + noSpaceStr.trim() + ".pdf";
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
		}
		
		else if(o.equals(btnTimKiem)) {
			if(valid()) {
				luongDao = new LuongDao();
				dsLuong = luongDao.getAllLuong();
				if(cmbTimKiem.getSelectedIndex() == 0) {
					docDuLieuDatabaseVaoTableHoaDonCongNhan();
					showName();
				}
				else if(cmbTimKiem.getSelectedIndex() == 1) {
					if(Integer.parseInt(txtTimKiem.getText()) < 1 || Integer.parseInt(txtTimKiem.getText()) > 12) {
						JOptionPane.showMessageDialog(this, "Số tháng phải thỏa: 1 < x < 12");
						return;
					} 
					
					if(name.charAt(0) == 'C') {
						int rows = modelHoaDon.getRowCount();
						for(int i = rows - 1; i >=0; i--)
						{
							modelHoaDon.removeRow(i); 
						}
						boolean check = true;
						
						for(Luong luong : dsLuong) {
							if(luong.getNhanVien().getMaNhanVien().equals(name)) {
								if(Integer.toString(luong.getNgayTao().getMonth()+1).equals(txtTimKiem.getText())) {
									check = false;
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
							        if(Integer.toString(luong.getNgayTao().getMonth()+1).equals(txtTimKiem.getText())) {
										modelHoaDon.addRow(new Object[] {luong.getMaLuong(), luong.getNhanVien().getMaNhanVien(),
												luong.getNhanVien().getTenNhanVien(), luong.getCongDoan(), luong.getSoSanPhamHoanThien(),
												luong.getHeSoLuong(), ngayTao, moneyString});
							        }
									showName();
								}
							}
						}
						if(check) {
							docDuLieuDatabaseVaoTableHoaDonCongNhan();
							showName();
							JOptionPane.showMessageDialog(this, "Không tìm thấy!");
							return;
						}
					}
					
					if(name.charAt(0) == 'N') {
						int rows = modelHoaDon.getRowCount();
						for(int i = rows - 1; i >=0; i--)
						{
							modelHoaDon.removeRow(i); 
						}
						boolean check = true;
						
						for(Luong luong : dsLuong) {
							if(luong.getNhanVien().getMaNhanVien().equals(name)) {
								if(Integer.toString(luong.getNgayTao().getMonth()+1).equals(txtTimKiem.getText())) {
									check = false;
									
									Date date = Calendar.getInstance().getTime();
									DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
									String ngayTao = dateFormat.format(luong.getNgayTao());

									// format tien
									Locale locale = new Locale("vn", "VN");
									NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);
									String moneyString = formatter.format(luong.getTongTien());
							        if(Integer.toString(luong.getNgayTao().getMonth()+1).equals(txtTimKiem.getText())) {
										modelHoaDon.addRow(new Object[] { luong.getMaLuong(), luong.getNhanVien().getMaNhanVien(),
												luong.getNhanVien().getTenNhanVien(),luong.getSoNgayNghiCoPhep() ,luong.getSoNgayNghiKhongPhep(), luong.getHeSoLuong(),
												ngayTao, moneyString });
							        }
									showName();
								}
							}
						}
						if(check) {
							docDuLieuDatabaseVaoTableHoaDonNhanVien();
							showName();
							JOptionPane.showMessageDialog(this, "Không tìm thấy!");
							return;
						}
					}
				}
				
				else if(cmbTimKiem.getSelectedIndex() == 2) {
					int year = Calendar.getInstance().get(Calendar.YEAR);
					if(Integer.parseInt(txtTimKiem.getText()) < 1900 || Integer.parseInt(txtTimKiem.getText()) > year) {
						JOptionPane.showMessageDialog(this, "Số năm phải thỏa: 1900 < x < Năm hiện tại");
						return;
					} 
					
					if(name.charAt(0) == 'C') {
						int rows = modelHoaDon.getRowCount();
						for(int i = rows - 1; i >=0; i--)
						{
							modelHoaDon.removeRow(i); 
						}
						boolean check = true;
						
						for(Luong luong : dsLuong) {
							if(luong.getNhanVien().getMaNhanVien().equals(name)) {
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
								if(Integer.toString(getLocalDate.getYear()).equals(txtTimKiem.getText())) {
									check = false;
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
							        if(Integer.toString(getLocalDate.getYear()).equals(txtTimKiem.getText())) {
										modelHoaDon.addRow(new Object[] {luong.getMaLuong(), luong.getNhanVien().getMaNhanVien(),
												luong.getNhanVien().getTenNhanVien(), luong.getCongDoan(), luong.getSoSanPhamHoanThien(),
												luong.getHeSoLuong(), ngayTao, moneyString});
							        }
									showName();
								}
							}
						}
						if(check) {
							docDuLieuDatabaseVaoTableHoaDonCongNhan();
							showName();
							JOptionPane.showMessageDialog(this, "Không tìm thấy!");
							return;
						}
					}
					
					if(name.charAt(0) == 'N') {
						int rows = modelHoaDon.getRowCount();
						for(int i = rows - 1; i >=0; i--)
						{
							modelHoaDon.removeRow(i); 
						}
						boolean check = true;
						
						for(Luong luong : dsLuong) {
							if(luong.getNhanVien().getMaNhanVien().equals(name)) {
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
								if(Integer.toString(getLocalDate.getYear()).equals(txtTimKiem.getText())) {
									check = false;
									
									Date date = Calendar.getInstance().getTime();
									DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
									String ngayTao = dateFormat.format(luong.getNgayTao());

									// format tien
									Locale locale = new Locale("vn", "VN");
									NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);
									String moneyString = formatter.format(luong.getTongTien());
							        if(Integer.toString(getLocalDate.getYear()).equals(txtTimKiem.getText())) {
										modelHoaDon.addRow(new Object[] { luong.getMaLuong(), luong.getNhanVien().getMaNhanVien(),
												luong.getNhanVien().getTenNhanVien(),luong.getSoNgayNghiCoPhep(), luong.getSoNgayNghiKhongPhep(), luong.getHeSoLuong(),
												ngayTao, moneyString });
							        }
									showName();
								}
							}
						}
						if(check) {
							docDuLieuDatabaseVaoTableHoaDonNhanVien();
							showName();
							JOptionPane.showMessageDialog(this, "Không tìm thấy!");
							return;
						}
					}
				}
			}
		}
	}
	
	public boolean valid() {
		if(txtTimKiem.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "Vui lòng không được bỏ trống!");
			return false;
		}
			try {
				Integer.parseInt(txtTimKiem.getText());
			} catch (Exception e2) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(this, "Vui lòng nhập kiểu dữ liệu là số nguyên để tìm kiếm!");
				return false;
			}
		return true;
	}
}
