package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Calendar;
import java.util.Date;
import java.util.EventObject;
import java.util.List;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.RoundRectangle2D;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.BorderFactory;
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
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import com.toedter.calendar.JDateChooser;

import dao.NhanVienDao;
import dao.TaiKhoanDao;
import entity.NhanVien;
import entity.SanPham;
import entity.TaiKhoan;


public class FrmQuanLyNhanVien extends JFrame implements MouseListener, ActionListener {
	private JPanel panel_QLNhanVien;
	private JTextField txtMaNV;
	private JTextField txtTenNV;
	private JDateChooser dateChooserNgayVaoLam;
	private JComboBox cbGioiTinh;
	private JComboBox cbBoPhan;
	private JComboBox cbNgoaiNgu;
	private JDateChooser dateChooserNgaySinh;
	private JButton btnThem;
	private JButton btnSua;
	private DefaultTableModel modelDanhSachCongNhan;
	private JTable table;
	private JComboBox comboBox;
	private JTextField txtTimKiem;
	private JButton btnTimKiem;
	private JButton btnXoa;
	private NhanVienDao nvDao;
	private LocalDate date1;
	private String dateCurrently;
	private List<NhanVien> dsNV;
	private TaiKhoanDao tkDao;

	public FrmQuanLyNhanVien() { 
		getContentPane().add(taoFrame_QLCN()); 
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1200,800);
	}
	
	public static void main(String[] e) {
		new FrmQuanLyNhanVien().setVisible(true);
	}
	
	
	public JPanel taoFrame_QLCN() {
		panel_QLNhanVien = new JPanel();
		panel_QLNhanVien.setBackground(new Color(0, 205, 205));
		panel_QLNhanVien.setLayout(null);

		JLabel lbQLNhanVien = new JLabel("QUẢN LÝ NHÂN VIÊN");	
		lbQLNhanVien.setFont(new Font("Verdana", Font.BOLD, 18));
		lbQLNhanVien.setForeground(Color.WHITE);
		lbQLNhanVien.setBounds(20, 10, 287, 38);
		panel_QLNhanVien.add(lbQLNhanVien);
		
		JPanel panel_ChiTietNhanVien = new JPanel();
		panel_ChiTietNhanVien.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_ChiTietNhanVien.setBackground(new Color(102, 255, 255));
		panel_ChiTietNhanVien.setBounds(20, 60, 360, 560);
		panel_QLNhanVien.add(panel_ChiTietNhanVien);
		panel_ChiTietNhanVien.setLayout(null);

		JLabel lbMaNhanVien = new JLabel("Mã nhân viên:");
		lbMaNhanVien.setFont(new Font("Verdana", Font.PLAIN, 16));
		lbMaNhanVien.setBounds(10, 20, 159, 37);
		panel_ChiTietNhanVien.add(lbMaNhanVien);

		txtMaNV = new JTextField();
		txtMaNV.setFont(new Font("Verdana", Font.PLAIN, 14));
		txtMaNV.setColumns(10);
		txtMaNV.setBounds(160, 22, 170, 37);
		panel_ChiTietNhanVien.add(txtMaNV);

		JLabel lbTenNV = new JLabel("Tên nhân viên:");
		lbTenNV.setFont(new Font("Verdana", Font.PLAIN, 16));
		lbTenNV.setBounds(10, 80, 159, 37);
		panel_ChiTietNhanVien.add(lbTenNV);
		
		txtTenNV = new JTextField();
		txtTenNV.setFont(new Font("Verdana", Font.PLAIN, 14));
		txtTenNV.setColumns(10);
		txtTenNV.setBounds(160, 82, 170, 37);
		panel_ChiTietNhanVien.add(txtTenNV);

		JLabel lbNgayVaoLam = new JLabel("Ngày vào làm:");
		lbNgayVaoLam.setFont(new Font("Verdana", Font.PLAIN, 16));
		lbNgayVaoLam.setBounds(10, 140, 159, 37);
		panel_ChiTietNhanVien.add(lbNgayVaoLam);

		dateChooserNgayVaoLam = new JDateChooser();
		dateChooserNgayVaoLam.setFont(new Font("Verdana", Font.PLAIN, 14));
		dateChooserNgayVaoLam.setBounds(160, 142, 170, 37);
		panel_ChiTietNhanVien.add(dateChooserNgayVaoLam);

		//Thay đổi ở Giới tính
		//		JPanel panel_DSNV = new JPanel();
		cbGioiTinh = new JComboBox();
		cbGioiTinh.setModel(new DefaultComboBoxModel(new String[] {"Nam", "Nữ"}));
		cbGioiTinh.setFont(new Font("Verdana", Font.PLAIN, 14));
		cbGioiTinh.setBounds(160, 202, 170, 37);
		panel_ChiTietNhanVien.add(cbGioiTinh);

		JLabel lbGioiTinh = new JLabel("Giới Tính:");
		lbGioiTinh.setFont(new Font("Verdana", Font.PLAIN, 16));
		lbGioiTinh.setBounds(10, 200, 159, 37);
		panel_ChiTietNhanVien.add(lbGioiTinh);

		JLabel lblNgaysinh = new JLabel("Ngày sinh:");
		lblNgaysinh.setFont(new Font("Verdana", Font.PLAIN, 16));
		lblNgaysinh.setBounds(10, 260, 159, 37);
		panel_ChiTietNhanVien.add(lblNgaysinh);
		
		dateChooserNgaySinh = new JDateChooser();
		dateChooserNgaySinh.setBounds(160, 262, 170, 37);
		dateChooserNgaySinh.setFont(new Font("Verdana", Font.PLAIN, 14));
		panel_ChiTietNhanVien.add(dateChooserNgaySinh);

		//Thay doi
		JLabel lblBoPhan = new JLabel("Bộ phận:");
		lblBoPhan.setFont(new Font("Verdana", Font.PLAIN, 16));
		lblBoPhan.setBounds(10, 320, 159, 37);
		panel_ChiTietNhanVien.add(lblBoPhan);
		
		cbBoPhan = new JComboBox();
		cbBoPhan.setModel(new DefaultComboBoxModel(new String[] {"Công Nhân", "Nhân Viên Hành Chánh"}));
		cbBoPhan.setFont(new Font("Verdana", Font.PLAIN, 14));
		cbBoPhan.setBounds(160, 322, 170, 37);
		panel_ChiTietNhanVien.add(cbBoPhan);

		JLabel lbNgoaiNgu = new JLabel("Ngoại ngữ:");
		lbNgoaiNgu.setFont(new Font("Verdana", Font.PLAIN, 16));
		lbNgoaiNgu.setBounds(10, 380, 159, 37);
		panel_ChiTietNhanVien.add(lbNgoaiNgu);

		//Thay doi Ngoai Ngu
		cbNgoaiNgu = new JComboBox();
		cbNgoaiNgu.setModel(new DefaultComboBoxModel(new String[] {"Sơ Cấp", "Trung Cấp", "Cao Cấp"}));
		cbNgoaiNgu.setFont(new Font("Verdana", Font.PLAIN, 14));
		cbNgoaiNgu.setBounds(160, 382, 170, 37);
		panel_ChiTietNhanVien.add(cbNgoaiNgu);

		JPanel panel_ChucNang = new JPanel();
		panel_ChucNang.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_ChucNang.setBackground(new Color(102, 255, 255));
		panel_ChucNang.setBounds(20, 640, 360, 83);
		panel_QLNhanVien.add(panel_ChucNang);
		panel_ChucNang.setLayout(null);

		
		
		btnThem = new JButton("Thêm");
		btnThem.setIcon(new ImageIcon("img\\follow.png"));
		btnThem.setBackground(new java.awt.Color(58, 46, 130));
		btnThem.setForeground(Color.WHITE);
		btnThem.setFont(new Font("Verdana", Font.PLAIN, 14));
		btnThem.setBounds(8, 10, 112, 62);
		panel_ChucNang.add(btnThem);

		btnSua = new JButton("Sửa");
		btnSua.setIcon(new ImageIcon("img\\labor-day.png"));
		btnSua.setBackground(new java.awt.Color(58, 46, 130));
		btnSua.setForeground(Color.WHITE);
		btnSua.setFont(new Font("Verdana", Font.PLAIN, 14));
		btnSua.setBounds(124, 10, 112, 62);
		panel_ChucNang.add(btnSua);
		
		btnXoa = new JButton("Xóa");
		btnXoa.setIcon(new ImageIcon("img\\delete-user.png"));
		btnXoa.setBackground(new java.awt.Color(58, 46, 130));
		btnXoa.setForeground(Color.WHITE);
		btnXoa.setFont(new Font("Verdana", Font.PLAIN, 14));
		btnXoa.setBounds(240, 10, 112, 62);
		panel_ChucNang.add(btnXoa);

		JPanel panel_DSNV = new JPanel();
		panel_DSNV.setBackground(new Color(102, 255, 255));
		panel_DSNV.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_DSNV.setBounds(400, 60, 740, 664);
		panel_QLNhanVien.add(panel_DSNV);
		panel_DSNV.setLayout(null);		

		////
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 60, 720, 594);
		panel_DSNV.add(scrollPane);
		String[] header = { "Mã NV", "Tên Nhân Viên", "Ngày vào làm", "Giới tính", "Ngày sinh", "Bộ phận", "Ngoại ngữ"};
		modelDanhSachCongNhan = new DefaultTableModel(header, 0);

		table = new JTable(modelDanhSachCongNhan) {
			public boolean editCellAt(int row, int column, EventObject e) 
			{ 
				// Không cho chỉnh sửa giá trị trong table
				return false;
			}
		};

		JTableHeader header1 = table.getTableHeader();
		header1.setBackground(new Color(23, 70, 138));
		header1.setForeground(Color.white);
		header1.setFont(new Font("Tahoma", Font.BOLD, 14));

		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrollPane.setViewportView(table);
		table.setRowHeight(25);

		table.getColumnModel().getColumn(0).setPreferredWidth(60);
		table.getColumnModel().getColumn(1).setPreferredWidth(120);
		table.getColumnModel().getColumn(2).setPreferredWidth(100);
		table.getColumnModel().getColumn(3).setPreferredWidth(80);
		table.getColumnModel().getColumn(4).setPreferredWidth(100);
		table.getColumnModel().getColumn(5).setPreferredWidth(120);
		table.getColumnModel().getColumn(6).setPreferredWidth(100);
		/*
		 * End table
		 */

		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Tìm kiếm theo", "Tên nhân viên", "Mã nhân viên", "Bộ phận"}));
		comboBox.setFont(new Font("Verdana", Font.PLAIN, 16));
		comboBox.setBounds(10, 10, 154, 34);
		panel_DSNV.add(comboBox);

		txtTimKiem = new JTextField();
		txtTimKiem.setFont(new Font("Verdana", Font.PLAIN, 14));
		txtTimKiem.setColumns(10);
		txtTimKiem.setBounds(180, 10, 390, 34);
		panel_DSNV.add(txtTimKiem);

		btnTimKiem = new JButton("Tìm Kiếm");
		btnTimKiem.setIcon(new ImageIcon("img\\search.png"));
		btnTimKiem.setBackground(new java.awt.Color(58, 46, 130));
		btnTimKiem.setForeground(Color.WHITE);
		btnTimKiem.setFont(new Font("Verdana", Font.PLAIN, 16));
		btnTimKiem.setBounds(580, 10, 150, 34);
		panel_DSNV.add(btnTimKiem);
		
		///khai báo lớp
		nvDao = new NhanVienDao();
		doctulieuvaobangnhanvien();
		/*---------------------*/
		table.addMouseListener(this);
		btnSua.addActionListener(this);
		btnThem.addActionListener(this);
		btnTimKiem.addActionListener(this);

		return panel_QLNhanVien;

	}
	
	private boolean valid() {
		if(txtMaNV.getText().equals("") || txtTenNV.getText().equals("") || dateChooserNgayVaoLam.getDate().equals("") || 
				dateChooserNgaySinh.getDate().equals("")) {
						JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!");
						return false;
					}
		
		if(cbBoPhan.getSelectedIndex() == 0 && (txtMaNV.getText().charAt(0) != 'C' || txtMaNV.getText().charAt(1) != 'N')) {
			JOptionPane.showMessageDialog(this, "Qui tắc đặt mã nhân viên: \nCông nhân: CN + number \nNhân viên hành chánh: "
					+ "NVHC + number");
			return false;
		}
		
		if(cbBoPhan.getSelectedIndex() == 1 && (txtMaNV.getText().charAt(0) != 'N' || txtMaNV.getText().charAt(1) != 'V'
				|| txtMaNV.getText().charAt(2) != 'H' || txtMaNV.getText().charAt(3) != 'C')) {
			JOptionPane.showMessageDialog(this, "Qui tắc đặt mã nhân viên: \nCông nhân: CN + number \nNhân viên hành chánh: "
					+ "NVHC + number");
			return false;
		}
		return true;
	}
	
	public void doctulieuvaobangnhanvien() {
		modelDanhSachCongNhan.getDataVector().removeAllElements();
		int dem = 1;
		List<NhanVien> listNV = nvDao.getAllNV();
		for (NhanVien nv : listNV) {
			Date date = Calendar.getInstance().getTime();  
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
			String ngaySinh = dateFormat.format(nv.getNgaySinh());  
			String ngayVaoLam = dateFormat.format(nv.getNgayVaoLam());
			//Get ngày hiện tại
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			date1 = LocalDate.now();
			dateCurrently = dtf.format(date1);

			if(nv.getMaNhanVien().charAt(0) == 'C' || nv.getMaNhanVien().charAt(0) == 'N')
			modelDanhSachCongNhan.addRow(new Object[] {nv.getMaNhanVien(), nv.getTenNhanVien()
					, ngayVaoLam, nv.getGioiTinh() ,ngaySinh , nv.getBoPhan(), nv.getNgoaiNgu()});


		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int row = table.getSelectedRow();
		txtMaNV.setText(modelDanhSachCongNhan.getValueAt(row, 0).toString());
		txtTenNV.setText(modelDanhSachCongNhan.getValueAt(row, 1).toString());

		//Set ngày cho jdatechooser
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		//here
		java.sql.Date dateNS = java.sql.Date.valueOf(table.getValueAt(row, 4).toString());
		java.sql.Date dateNVL = java.sql.Date.valueOf(table.getValueAt(row, 2).toString());
		dateChooserNgaySinh.setDate(dateNS);
		dateChooserNgayVaoLam.setDate(dateNVL);
		
		if(table.getValueAt(row, 3).toString().equals("Nam")) {
			cbGioiTinh.setSelectedIndex(0); 
		}
		else if (table.getValueAt(row, 3).toString().equals("Nữ")) {
			cbGioiTinh.setSelectedIndex(1); 
		}

		if(table.getValueAt(row, 5).toString().equals("Công Nhân")) {
			cbBoPhan.setSelectedIndex(0);
		} 
		else if(table.getValueAt(row, 5).toString().equals("Nhân Viên Hành Chánh")) {
			cbBoPhan.setSelectedIndex(1);
		}
		
		if(table.getValueAt(row, 6).toString().equals("Sơ Cấp")) {
			cbNgoaiNgu.setSelectedIndex(0);
		}
		else if(table.getValueAt(row, 6).toString().equals("Trung Cấp")) {
			cbNgoaiNgu.setSelectedIndex(1);
		}
		else if(table.getValueAt(row, 6).toString().equals("Cao Cấp")) {
			cbNgoaiNgu.setSelectedIndex(2);
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
		if(o.equals(btnThem)) {			
			if(valid()) {
				dsNV = nvDao.getAllNV();
				for(NhanVien x: dsNV) {
					if(x.getMaNhanVien().equals(txtMaNV.getText() )) {
						JOptionPane.showMessageDialog(this, "Trùng mã nhân viên!");
						txtMaNV.requestFocus();
						txtMaNV.selectAll();
						return;
					}
				}
				
				int n = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn thêm nhân viên này không?", "Cảnh báo",
						JOptionPane.YES_NO_OPTION);
				if (n == 0) {
					NhanVien nv = new NhanVien();
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					String strNgaySinh = dateFormat.format(dateChooserNgaySinh.getDate());
					String strNgayVaoLam = dateFormat.format(dateChooserNgayVaoLam.getDate());
					java.sql.Date ngaySinh = java.sql.Date.valueOf(strNgaySinh); 
					java.sql.Date ngayVaoLam = java.sql.Date.valueOf(strNgayVaoLam);
					
					nv.setMaNhanVien(txtMaNV.getText());
					nv.setTenNhanVien(txtTenNV.getText());
					nv.setGioiTinh(cbGioiTinh.getSelectedItem().toString());
					nv.setNgaySinh(ngaySinh);
					nv.setNgayVaoLam(ngayVaoLam);
					nv.setBoPhan(cbBoPhan.getSelectedItem().toString());
					nv.setNgoaiNgu(cbNgoaiNgu.getSelectedItem().toString());
					
					if(cbBoPhan.getSelectedIndex() == 0) {
						SanPham sp = new SanPham("G001");
						nv.setSanPham(sp);
					} else if (cbBoPhan.getSelectedIndex() == 1) {
						SanPham sp = new SanPham();
						nv.setSanPham(sp);
					}
					
					if(nvDao.themNhanVien(nv)) {
						JOptionPane.showMessageDialog(this, "Thêm thành công!");
						doctulieuvaobangnhanvien();
						tkDao = new TaiKhoanDao();
						NhanVien x = new NhanVien();
						x.setMaNhanVien(txtMaNV.getText());
						TaiKhoan tk = new TaiKhoan();
						tk.setTenTaiKhoan(txtMaNV.getText());
						tk.setNhanVien(x);
						tkDao.themTaiKhoan(tk);
					}
				}
			}
		}
		else if (o.equals(btnSua)) {
			if(valid()) {
				dsNV = nvDao.getAllNV();
				String nameDB = "";
				String maDB = "";
				int index = 0;
				for(NhanVien x: dsNV) {
					if(x.getMaNhanVien().equals(txtMaNV.getText() )) {
						index++;
						
					}
				}
				
				if(index == 0) {
					JOptionPane.showMessageDialog(this, "Không tìm thấy mã nhân viên cần sửa!!");
					txtMaNV.requestFocus();
					txtMaNV.selectAll();
					return;
				} else {
					for(NhanVien x: dsNV) {
						if(x.getMaNhanVien().equals(txtMaNV.getText() )) {
							nameDB = x.getTenNhanVien();
							maDB = x.getMaNhanVien();
						}
					}
				}
				
				
				int n = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn sửa thông tin"
						+ " nhân viên" + nameDB + "\n với mã nhân viên là " +maDB+ " không?", "Cảnh báo",
						JOptionPane.YES_NO_OPTION);
				
				if(n == 0) {
					NhanVien nv = new NhanVien();
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					String strNgaySinh = dateFormat.format(dateChooserNgaySinh.getDate());
					String strNgayVaoLam = dateFormat.format(dateChooserNgayVaoLam.getDate());
					java.sql.Date ngaySinh = java.sql.Date.valueOf(strNgaySinh); 
					java.sql.Date ngayVaoLam = java.sql.Date.valueOf(strNgayVaoLam);
					
					nv.setMaNhanVien(txtMaNV.getText());
					nv.setTenNhanVien(txtTenNV.getText());
					nv.setGioiTinh(cbGioiTinh.getSelectedItem().toString());
					nv.setNgaySinh(ngaySinh);
					nv.setNgayVaoLam(ngayVaoLam);
					nv.setBoPhan(cbBoPhan.getSelectedItem().toString());
					nv.setNgoaiNgu(cbNgoaiNgu.getSelectedItem().toString());
					
					if(cbBoPhan.getSelectedIndex() == 0) {
						SanPham sp = new SanPham("G001");
						nv.setSanPham(sp);
					} else if (cbBoPhan.getSelectedIndex() == 1) {
						SanPham sp = new SanPham();
						nv.setSanPham(sp);
					}
					
					if(nvDao.capNhat(txtMaNV.getText() ,nv)) {
						JOptionPane.showMessageDialog(this, "Sửa thành công!");
						doctulieuvaobangnhanvien();
					}
				}
			}
		}
		else if (o.equals(btnTimKiem)) {
			if(comboBox.getSelectedIndex() == 0) {
				doctulieuvaobangnhanvien();
			}
			else if(comboBox.getSelectedIndex() == 1) { //Ten Nhan Vien
				if(txtTimKiem.getText().equals("")) {
					JOptionPane.showMessageDialog(this, "Vui lòng nhập tên nhân viên cần tìm!!!");
				}
				else {
					dsNV = nvDao.timTheoTen_VerK(txtTimKiem.getText());
					int rows = modelDanhSachCongNhan.getRowCount();
					for(int i = rows - 1; i >=0; i--)
					{
						modelDanhSachCongNhan.removeRow(i); 
					}
					for(NhanVien nv1 : dsNV) {
						SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
						String ngaySinh = myFormat.format(nv1.getNgaySinh());
						String ngayVaoLam = myFormat.format(nv1.getNgayVaoLam());
						String[] row = {nv1.getMaNhanVien(), nv1.getTenNhanVien(),
								ngayVaoLam, nv1.getGioiTinh(), ngaySinh, nv1.getBoPhan(),
								nv1.getNgoaiNgu()};
						modelDanhSachCongNhan.addRow(row);
					}
					table.setModel(modelDanhSachCongNhan);
				}
			}
			else if(comboBox.getSelectedIndex() == 2) {// Ma Nhan Vien
				if(txtTimKiem.getText().equals("")) {
					JOptionPane.showMessageDialog(this, "Vui lòng nhập mã nhân viên cần tìm!!!");
				}
				else {
					dsNV = nvDao.timTheoMa_VerK(txtTimKiem.getText());
					int rows = modelDanhSachCongNhan.getRowCount();
					for(int i = rows - 1; i >=0; i--)
					{
						modelDanhSachCongNhan.removeRow(i); 
					}
					for(NhanVien nv1 : dsNV) {
						SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
						String ngaySinh = myFormat.format(nv1.getNgaySinh());
						String ngayVaoLam = myFormat.format(nv1.getNgayVaoLam());
						String[] row = {nv1.getMaNhanVien(), nv1.getTenNhanVien(),
								ngayVaoLam, nv1.getGioiTinh(), ngaySinh, nv1.getBoPhan(),
								nv1.getNgoaiNgu()};
						modelDanhSachCongNhan.addRow(row);
					}
					table.setModel(modelDanhSachCongNhan);
				}
			}
			else if(comboBox.getSelectedIndex() == 3) {// Bo Phan
				dsNV = nvDao.timTheoBoPhan_VerK(txtTimKiem.getText());
				int rows = modelDanhSachCongNhan.getRowCount();
				for(int i = rows - 1; i >=0; i--)
				{
					modelDanhSachCongNhan.removeRow(i); 
				}
				
				for(NhanVien nv1 : dsNV) {
					SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
					String ngaySinh = myFormat.format(nv1.getNgaySinh());
					String ngayVaoLam = myFormat.format(nv1.getNgayVaoLam());
					String[] row = {nv1.getMaNhanVien(), nv1.getTenNhanVien(),
							ngayVaoLam, nv1.getGioiTinh(), ngaySinh, nv1.getBoPhan(),
							nv1.getNgoaiNgu()};
					modelDanhSachCongNhan.addRow(row);
					
				}
				table.setModel(modelDanhSachCongNhan);
			}
		}
	}	
}
