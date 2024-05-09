package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import com.toedter.calendar.JDateChooser;

import dao.NhanVienDao;
import dao.TaiKhoanDao;
import entity.NhanVien;
import entity.TaiKhoan;

public class FrmThongTinNhanVien extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panel_QLNhanVien;
	private JTextField txtMaNV;
	private JTextField txtTenNV;
	private JTextField txtGioiTinh;
	private JTextField txtBoPhan;
	private JTextField txtNgoaiNgu;
	private String name;
	
	private NhanVienDao nvDao;
	private TaiKhoanDao tkDao;
	private JDateChooser dateChooser_NgayVaoLam;
	private JLabel lbMatKhauHienTai;
	private JLabel lbMatKhauMoi;
	private JButton btnDoiMK;
	private JPasswordField txtMatKhauHienTai;
	private JPasswordField txtMatKhauMoi;
	private JButton btnSua;
	private List<TaiKhoan> dsTaiKhoan;
	private JComboBox cmbGioiTinh;
	private JDateChooser dateChooser_NgaySinh;
	private JComboBox cmbNgoaiNgu;
	private JTextField txtTenTaiKhoan;
	private HashCode hashCode;

	public FrmThongTinNhanVien(String text) {
		// TODO Auto-generated constructor stub
		this.name = text;
		getContentPane().add(taoFrame_ThongTinNV(name));
		taoFrame_ThongTinNV(name);
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setSize(1200,800);
	}
	
	public static void main(String[] args) {
		new FrmThongTinNhanVien("").setVisible(true);
	}
	
	public JPanel taoFrame_ThongTinNV(String name) {
		panel_QLNhanVien = new JPanel();
		panel_QLNhanVien.setBackground(new Color(204, 255, 255));
		panel_QLNhanVien.setLayout(null);
		
		JLabel lblThongTinNV = new JLabel("Thông Tin Nhân Viên");
		lblThongTinNV.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblThongTinNV.setBounds(20, 20, 200, 40);
		panel_QLNhanVien.add(lblThongTinNV);
		JPanel panel_ChiTietNhanVien = new JPanel();
		panel_ChiTietNhanVien.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_ChiTietNhanVien.setBackground(new Color(102, 255, 255));
		panel_ChiTietNhanVien.setBounds(20, 80, 1120, 640);
		panel_QLNhanVien.add(panel_ChiTietNhanVien);
		panel_ChiTietNhanVien.setLayout(null);
		
		
		JLabel lbTenNV = new JLabel("Tên nhân viên:");
		lbTenNV.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbTenNV.setBounds(537, 70, 159, 37);
		panel_ChiTietNhanVien.add(lbTenNV);
		
		txtTenNV = new JTextField();
		txtTenNV.setFont(new Font("Verdana", Font.PLAIN, 14));
		txtTenNV.setColumns(10);
		txtTenNV.setBounds(700, 70, 240, 37);
		panel_ChiTietNhanVien.add(txtTenNV);
		
		JLabel lbMaNhanVien = new JLabel("Mã nhân viên:");
		lbMaNhanVien.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbMaNhanVien.setBounds(537, 130, 159, 37);
		panel_ChiTietNhanVien.add(lbMaNhanVien);
		
		txtMaNV = new JTextField();
		txtMaNV.setFont(new Font("Verdana", Font.PLAIN, 14));
		txtMaNV.setColumns(10);
		txtMaNV.setBounds(700, 130, 240, 37);
		panel_ChiTietNhanVien.add(txtMaNV);
		
		String gender[] = { "Nam", "Nữ" };
		cmbGioiTinh = new JComboBox(gender);
		cmbGioiTinh.setFont(new Font("Verdana", Font.PLAIN, 14));
		cmbGioiTinh.setBackground(Color.WHITE);
		cmbGioiTinh.setBounds(700, 190, 240, 37);
		panel_ChiTietNhanVien.add(cmbGioiTinh);
		
		JLabel lbGioiTinh = new JLabel("Giới Tính:");
		lbGioiTinh.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbGioiTinh.setBounds(537, 190, 159, 37);
		panel_ChiTietNhanVien.add(lbGioiTinh);
		
		JLabel lbNgayVaoLam = new JLabel("Ngày vào làm:");
		lbNgayVaoLam.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbNgayVaoLam.setBounds(537, 250, 159, 37);
		panel_ChiTietNhanVien.add(lbNgayVaoLam);
		
		dateChooser_NgayVaoLam = new JDateChooser();
		dateChooser_NgayVaoLam.setBounds(700, 250, 240, 37);
		panel_ChiTietNhanVien.add(dateChooser_NgayVaoLam);
		
		JLabel lblNgaysinh = new JLabel("Ngày sinh:");
		lblNgaysinh.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNgaysinh.setBounds(537, 310, 159, 37);
		panel_ChiTietNhanVien.add(lblNgaysinh);
		
		txtBoPhan = new JTextField();
		txtBoPhan.setFont(new Font("Verdana", Font.PLAIN, 14));
		txtBoPhan.setColumns(10);
		txtBoPhan.setBounds(700, 370, 240, 37);
		panel_ChiTietNhanVien.add(txtBoPhan);
		
		JLabel lblBoPhan = new JLabel("Bộ phận:");
		lblBoPhan.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblBoPhan.setBounds(537, 370, 159, 37);
		panel_ChiTietNhanVien.add(lblBoPhan);
		
		JLabel lbNgoaiNgu = new JLabel("Ngoại ngữ:");
		lbNgoaiNgu.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbNgoaiNgu.setBounds(537, 430, 159, 37);
		panel_ChiTietNhanVien.add(lbNgoaiNgu);
		
		String [] ngoaiNgu = {"Sơ cấp", "Trung cấp", "Cao cấp"};
		cmbNgoaiNgu = new JComboBox(ngoaiNgu);
		cmbNgoaiNgu.setFont(new Font("Verdana", Font.PLAIN, 14));
		cmbNgoaiNgu.setBackground(Color.WHITE);
		cmbNgoaiNgu.setBounds(700, 430, 240, 37);
		panel_ChiTietNhanVien.add(cmbNgoaiNgu);
		
		dateChooser_NgaySinh = new JDateChooser();
		dateChooser_NgaySinh.setBounds(700, 310, 240, 37);
		panel_ChiTietNhanVien.add(dateChooser_NgaySinh);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("img\\TK.png"));
		lblNewLabel.setBounds(150, 20, 100, 100);
		panel_ChiTietNhanVien.add(lblNewLabel);
		
		btnSua = new JButton("Sửa");
		btnSua.setIcon(new ImageIcon("img\\labor-day.png"));
		btnSua.setForeground(Color.WHITE);
		btnSua.setFont(new Font("Verdana", Font.PLAIN, 18));
		btnSua.setBackground(new Color(58, 46, 130));
		btnSua.setBounds(1282, 694, 152, 49);
		panel_ChiTietNhanVien.add(btnSua);
		
		
		lbMatKhauHienTai = new JLabel("Mật khẩu hiện tại:");
		lbMatKhauHienTai.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbMatKhauHienTai.setBounds(40, 240, 180, 36);
		panel_ChiTietNhanVien.add(lbMatKhauHienTai);
		
		txtMatKhauHienTai = new JPasswordField();
		txtMatKhauHienTai.setFont(new Font("Verdana", Font.PLAIN, 14));
		txtMatKhauHienTai.setColumns(10);
		txtMatKhauHienTai.setBounds(220, 240, 200, 36);
		panel_ChiTietNhanVien.add(txtMatKhauHienTai);
		
		lbMatKhauMoi = new JLabel("Mật khẩu mới:");
		lbMatKhauMoi.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbMatKhauMoi.setBounds(40, 320, 180, 36);
		panel_ChiTietNhanVien.add(lbMatKhauMoi);
		
		txtMatKhauMoi = new JPasswordField();
		txtMatKhauMoi.setFont(new Font("Verdana", Font.PLAIN, 14));
		txtMatKhauMoi.setColumns(10);
		txtMatKhauMoi.setBounds(220, 320, 200, 36);
		panel_ChiTietNhanVien.add(txtMatKhauMoi);
		
		btnDoiMK = new JButton("Đổi mật khẩu");
		btnDoiMK.setIcon(new ImageIcon("img\\password.png"));
		btnDoiMK.setForeground(Color.WHITE);
		btnDoiMK.setFont(new Font("Verdana", Font.BOLD, 16));
		btnDoiMK.setBackground(new Color(58, 46, 130));
		btnDoiMK.setBounds(132, 440, 200, 44);
		panel_ChiTietNhanVien.add(btnDoiMK);
		
		nvDao = new NhanVienDao();
		NhanVien nv = nvDao.getNhanVienTheoMa(name);
		txtTenNV.setText(nv.getTenNhanVien());
		txtMaNV.setText(nv.getMaNhanVien());
		
		if(nv.getGioiTinh().equals("Nam")) {
			cmbGioiTinh.setSelectedIndex(0);
		} else {
			cmbGioiTinh.setSelectedIndex(1);
		}
		
		txtBoPhan.setText(nv.getBoPhan());
		
		if(nv.getNgoaiNgu().equals("Sơ Cấp")) {
			cmbNgoaiNgu.setSelectedIndex(0);
		} else if(nv.getNgoaiNgu().equals("Trung Cấp")) {
			cmbNgoaiNgu.setSelectedIndex(1);
		} else {
			cmbNgoaiNgu.setSelectedIndex(2);
		}
		
		cmbNgoaiNgu.setEditable(false);
		txtMaNV.setEditable(false);
		txtBoPhan.setEditable(false);
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String ngaySinh = dateFormat.format(nv.getNgaySinh());
		String ngayVaoLam = dateFormat.format(nv.getNgayVaoLam());

		Date date = Date.valueOf(ngaySinh);
		Date date2 = Date.valueOf(ngayVaoLam);
		dateChooser_NgaySinh.setDate(date);
		dateChooser_NgayVaoLam.setDate(date2);
		
		JLabel lblTenTaiKhoan = new JLabel("Tên tài khoản:");
		lblTenTaiKhoan.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTenTaiKhoan.setBounds(40, 160, 180, 36);
		panel_ChiTietNhanVien.add(lblTenTaiKhoan);
		
		txtTenTaiKhoan = new JTextField();
		txtTenTaiKhoan.setFont(new Font("Verdana", Font.PLAIN, 14));
		txtTenTaiKhoan.setColumns(10);
		txtTenTaiKhoan.setEditable(false);
		txtTenTaiKhoan.setBounds(220, 160, 200, 36);
		panel_ChiTietNhanVien.add(txtTenTaiKhoan);
		txtTenTaiKhoan.setText(nv.getMaNhanVien());
		txtTenTaiKhoan.setEditable(false);
		
		
		
		tkDao = new TaiKhoanDao();
		nvDao = new NhanVienDao();
		btnDoiMK.addActionListener(this);
		btnSua.addActionListener(this);
		
		return panel_QLNhanVien;
		

		//pack
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
