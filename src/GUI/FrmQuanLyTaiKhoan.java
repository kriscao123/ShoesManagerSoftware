package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.EventObject;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import dao.TaiKhoanDao;
import entity.TaiKhoan;

public class FrmQuanLyTaiKhoan extends JFrame implements MouseListener, ActionListener{
	private JPanel panel_QLTaiKhoan;
	private JTextField txtMaNV;
	private JTextField txtTenTK;
	private JPasswordField txtMatKhau;
	private JTextField txtNgayTao;
	private JTextField txtNgayCapNhat;
	private JButton btnSua;
	private JButton btnThem;
	private JTable table;
	private DefaultTableModel tableModel;
	private JComboBox comboBox;
	private JTextField txtTimKiem;
	private JButton btnTimKiem;
	private JButton btnXoa;
	private TaiKhoanDao tkDao;
	private List<TaiKhoan> dsTK;
	private HashCode hashCode;

	public FrmQuanLyTaiKhoan() { 
		getContentPane().add(taoFrame_QLTaiKhoan()); 
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setSize(1200,800);
	}
	
	public static void main(String[] args) {
		new FrmQuanLyTaiKhoan().setVisible(true);
	}
	
	public JPanel taoFrame_QLTaiKhoan() {
		panel_QLTaiKhoan = new JPanel();
		panel_QLTaiKhoan.setBackground(new Color(207, 207, 207));
		panel_QLTaiKhoan.setLayout(null);

		JLabel lbQLTaiKhoan = new JLabel("QUẢN LÝ TÀI KHOẢN");
		lbQLTaiKhoan.setBounds(20, 10, 330, 38);
		lbQLTaiKhoan.setFont(new Font("Verdana", Font.BOLD, 18));
		panel_QLTaiKhoan.add(lbQLTaiKhoan);

		JPanel panel_ChiTiet = new JPanel();
		panel_ChiTiet.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_ChiTiet.setBackground(new Color(102, 255, 255));
		panel_ChiTiet.setBounds(20, 60, 360, 480);
		panel_QLTaiKhoan.add(panel_ChiTiet);
		panel_ChiTiet.setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("img\\TK.png"));
		lblNewLabel.setBounds(128, 20, 100, 100);
		panel_ChiTiet.add(lblNewLabel);

		JLabel lbMaNV = new JLabel("Mã nhân viên:");
		lbMaNV.setFont(new Font("Verdana", Font.PLAIN, 16));
		lbMaNV.setBounds(10, 140, 164, 50);
		panel_ChiTiet.add(lbMaNV);
		
		txtMaNV = new JTextField();
		txtMaNV.setFont(new Font("Verdana", Font.PLAIN, 14));
		txtMaNV.setColumns(10);
		txtMaNV.setBounds(160, 146, 178, 38);
		panel_ChiTiet.add(txtMaNV);

		JLabel lbTenTaiKhoan = new JLabel("Tên tài khoản:");
		lbTenTaiKhoan.setFont(new Font("Verdana", Font.PLAIN, 16));
		lbTenTaiKhoan.setBounds(10, 200, 164, 50);
		panel_ChiTiet.add(lbTenTaiKhoan);

		txtTenTK = new JTextField();
		txtTenTK.setFont(new Font("Verdana", Font.PLAIN, 14));
		txtTenTK.setColumns(10);
		txtTenTK.setBounds(160, 206, 178, 38);
		panel_ChiTiet.add(txtTenTK);

		JLabel lbMatKhau = new JLabel("Mật khẩu:");
		lbMatKhau.setFont(new Font("Verdana", Font.PLAIN, 16));
		lbMatKhau.setBounds(10, 260, 164, 50);
		panel_ChiTiet.add(lbMatKhau);
		
		txtMatKhau = new JPasswordField();
		txtMatKhau.setFont(new Font("Verdana", Font.PLAIN, 14));
		txtMatKhau.setColumns(10);
		txtMatKhau.setBounds(160, 266, 178, 38);
		panel_ChiTiet.add(txtMatKhau);

		JLabel lblNgayTao_TaiKhoan = new JLabel("Ngày Tạo:");
		lblNgayTao_TaiKhoan.setFont(new Font("Verdana", Font.PLAIN, 16));
		lblNgayTao_TaiKhoan.setBounds(10, 320, 164, 50);
		panel_ChiTiet.add(lblNgayTao_TaiKhoan);
		
		txtNgayTao = new JTextField();
		txtNgayTao.setBounds(160, 326, 178, 38);
		txtNgayTao.setFont(new Font("Verdana", Font.PLAIN, 14));
		txtNgayTao.setEditable(false);
		panel_ChiTiet.add(txtNgayTao);

		JLabel lbNgayCapNhat_TaiKhoan = new JLabel("Ngày cập nhật:");
		lbNgayCapNhat_TaiKhoan.setFont(new Font("Verdana", Font.PLAIN, 16));
		lbNgayCapNhat_TaiKhoan.setBounds(10, 380, 164, 50);
		panel_ChiTiet.add(lbNgayCapNhat_TaiKhoan);
		
		txtNgayCapNhat = new JTextField();
		txtNgayCapNhat.setBounds(160, 386, 178, 38);
		txtNgayCapNhat.setFont(new Font("Verdana", Font.PLAIN, 14));
		txtNgayCapNhat.setEditable(false);
		panel_ChiTiet.add(txtNgayCapNhat);

		JPanel panel_ChucNang = new JPanel();
		panel_ChucNang.setBackground(new Color(102, 255, 255));
		panel_ChucNang.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_ChucNang.setBounds(20, 560, 360, 140);
		panel_QLTaiKhoan.add(panel_ChucNang);
		panel_ChucNang.setLayout(null);

		btnSua = new JButton("Cập nhật");
		btnSua.setIcon(new ImageIcon("img\\update.png"));
		btnSua.setBackground(new java.awt.Color(58, 46, 130));
		btnSua.setForeground(Color.WHITE);
		btnSua.setFont(new Font("Verdana", Font.PLAIN, 14));
		btnSua.setBounds(90, 80, 170, 44);
		panel_ChucNang.add(btnSua);

		btnThem = new JButton("Thêm");
		btnThem.setIcon(new ImageIcon("img\\add-user.png"));
		btnThem.setBackground(new java.awt.Color(58, 46, 130));
		btnThem.setForeground(Color.WHITE);
		btnThem.setFont(new Font("Verdana", Font.PLAIN, 14));
		btnThem.setBounds(18, 18, 140, 44);
		panel_ChucNang.add(btnThem);
		
		btnXoa = new JButton("Xóa");
		btnXoa.setIcon(new ImageIcon("img\\delete-user.png"));
		btnXoa.setBackground(new java.awt.Color(58, 46, 130));
		btnXoa.setForeground(Color.WHITE);
		btnXoa.setFont(new Font("Verdana", Font.PLAIN, 14));
		btnXoa.setBounds(200, 18, 140, 44);
		panel_ChucNang.add(btnXoa);	

		JPanel panel_DSTaiKhoan = new JPanel();
		panel_DSTaiKhoan.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_DSTaiKhoan.setBackground(new Color(102, 255, 255));
		panel_DSTaiKhoan.setBounds(420, 60, 730, 640);
		panel_QLTaiKhoan.add(panel_DSTaiKhoan);
		panel_DSTaiKhoan.setLayout(null);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 60, 710, 570);
		panel_DSTaiKhoan.add(scrollPane_1);

		table = new JTable();
		String[] header = {"Mã NV", "Tên tài khoản", "Mật khẩu", "Ngày tạo", "Ngày cập nhật"};
		tableModel = new DefaultTableModel(header, 0);
		table = new JTable(tableModel) {
			public boolean editCellAt(int row, int column, EventObject e) { // Không cho chỉnh sửa giá trị trong table
				return false;
			}
		};
		
		JTableHeader header1 = table.getTableHeader();
		header1.setBackground(new Color(23, 70, 138));
		header1.setForeground(Color.white);
		header1.setFont(new Font("Tahoma", Font.BOLD, 16));

		table.setFont(new Font("Tahoma", Font.PLAIN, 16));
		table.setRowHeight(25);
		
		scrollPane_1.setViewportView(table);

		String timKiem[] = {"Tìm kiếm theo", "Mã nhân viên", "Tên tài khoản"}; 
		comboBox = new JComboBox(timKiem);
		comboBox.setFont(new Font("Verdana", Font.PLAIN, 16));
		comboBox.setBounds(10, 10, 154, 34);
		panel_DSTaiKhoan.add(comboBox);

		txtTimKiem = new JTextField();
		txtTimKiem.setFont(new Font("Verdana", Font.PLAIN, 14));
		txtTimKiem.setColumns(10);
		txtTimKiem.setBounds(180, 10, 370, 34);
		panel_DSTaiKhoan.add(txtTimKiem);

		btnTimKiem = new JButton("Tìm Kiếm");
		btnTimKiem.setIcon(new ImageIcon("img\\search.png"));
		btnTimKiem.setForeground(Color.WHITE);
		btnTimKiem.setBackground(new java.awt.Color(58, 46, 130));
		btnTimKiem.setFont(new Font("Verdana", Font.PLAIN, 16));
		btnTimKiem.setBounds(560, 10, 160, 34);
		panel_DSTaiKhoan.add(btnTimKiem);
		
//		txtMaNV.setEditable(false);
		
		/*--------*/
		tkDao = new TaiKhoanDao();
		dsTK = tkDao.getAllKH();
		docDuLieuTaiKhoan();
		/*---------------------------*/
		table.addMouseListener(this);
		btnThem.addActionListener(this);
		btnSua.addActionListener(this);
		btnTimKiem.addActionListener(this);

		return panel_QLTaiKhoan;
		//pack
	}
	public void docDuLieuTaiKhoan() {
		tableModel.getDataVector().removeAllElements();
		dsTK = tkDao.getAllKH();
		int dem = 1;
		
        Date date = Calendar.getInstance().getTime();  
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");  

        
		for(TaiKhoan tk : dsTK) {
			String ngayTao = "";
			String ngayCapNhat = "";
			try {
		        ngayTao = dateFormat.format(tk.getNgayTao());  
		        ngayCapNhat = dateFormat.format(tk.getNgayCapNhat());
			} catch (Exception e) {
				// TODO: handle exception
		        ngayTao = "";  
		        ngayCapNhat = "";
			}
			tableModel.addRow(new Object[] {tk.getNhanVien().getMaNhanVien(),tk.getTenTaiKhoan(),
					tk.getMatKhau(), ngayTao, ngayCapNhat});
			dem++;
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int row = table.getSelectedRow();
		txtTenTK.setText(tableModel.getValueAt(row, 1).toString());
		txtMaNV.setText(tableModel.getValueAt(row, 0).toString());
		try {
			txtMatKhau.setText(tableModel.getValueAt(row, 2).toString());
			txtNgayTao.setText(tableModel.getValueAt(row, 3).toString());
			txtNgayCapNhat.setText(tableModel.getValueAt(row, 4).toString());
		} catch (Exception e2) {
			// TODO: handle exception
			txtMatKhau.setText("");
			txtNgayTao.setText("");
			txtNgayCapNhat.setText("");
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
		if(o.equals(btnTimKiem)) {
			tkDao = new TaiKhoanDao();

			if(comboBox.getSelectedIndex() == 0){
				docDuLieuTaiKhoan();
			}

			else if (comboBox.getSelectedIndex() == 1){
				if(txtTimKiem.getText().equals("")) {
					JOptionPane.showMessageDialog(this, "Vui lòng nhập Mã Nhân Viên!!!");
				}
				else {
					dsTK = tkDao.getTKTheoMaNhanVien(txtTimKiem.getText());
					int rows = tableModel.getRowCount();
					for(int i = rows - 1; i >=0; i--)
					{
						tableModel.removeRow(i); 
					}
					for (TaiKhoan tk : dsTK) {
						SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
						String ngayTao = myFormat.format(tk.getNgayTao());
						String ngayCapNhat = myFormat.format(tk.getNgayCapNhat());
						String[] row = {tk.getNhanVien().getMaNhanVien(), tk.getTenTaiKhoan(),
								tk.getMatKhau(), ngayTao, ngayCapNhat};
						tableModel.addRow(row);
					}
				}
				table.setModel(tableModel);
			}
			else if (comboBox.getSelectedIndex() == 2){
				if(txtTimKiem.getText().equals("")) {
					JOptionPane.showMessageDialog(this, "Vui lòng nhập tên tài khoản!!!");
				}
				else {
					dsTK = tkDao.getTaiKhoanTheoTen(txtTimKiem.getText());
					int rows1 = tableModel.getRowCount();
					for(int i = rows1 - 1; i >=0; i--)
					{
						tableModel.removeRow(i); 
					}
					for (TaiKhoan tk1 : dsTK) {
						SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
						String ngayTao = myFormat.format(tk1.getNgayTao());
						String ngayCapNhat = myFormat.format(tk1.getNgayCapNhat());
						String[] row2 = {tk1.getNhanVien().getMaNhanVien(), tk1.getTenTaiKhoan(),
								tk1.getMatKhau(), ngayTao, ngayCapNhat};
						tableModel.addRow(row2);
					}
				}
				table.setModel(tableModel);
			}
		}
		else if(o.equals(btnThem)) {
			if(validThem()) {
				if(txtMatKhau.getText().length() < 6) {
					JOptionPane.showMessageDialog(this, "Mật khẩu phải tối thiểu 6 kí tự!");
					return;
				}
				else {
			        long millis=System.currentTimeMillis();  
			        java.sql.Date date = new java.sql.Date(millis);
			        
			        String pass = hashCode.hash(txtMatKhau.getText());
					TaiKhoan tk = new TaiKhoan();
					tk.setTenTaiKhoan(txtTenTK.getText());
					tk.setMatKhau(pass);
					tk.setNgayTao(date);
					tk.setNgayCapNhat(date);
					
					tkDao = new TaiKhoanDao();
					int n = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn thêm tài khoản này không?", "Cảnh báo",
							JOptionPane.YES_NO_OPTION);
					if(n == 0) {
						if(tkDao.capNhatThem(txtMaNV.getText(), tk)) {
							JOptionPane.showMessageDialog(this, "Thêm thành công!");
							docDuLieuTaiKhoan();
							return;
						}
					}
				}
			}
		}
		else if(o.equals(btnSua)) {
			if(validCapNhat()) {
				if(txtMatKhau.getText().length() < 6) {
					JOptionPane.showMessageDialog(this, "Mật khẩu phải tối thiểu 6 kí tự!");
					return;
				}
				else {
			        long millis=System.currentTimeMillis();  
			        java.sql.Date date = new java.sql.Date(millis);
			        
			        String pass = hashCode.hash(txtMatKhau.getText());
					TaiKhoan tk = new TaiKhoan();
					tk.setTenTaiKhoan(txtTenTK.getText());
					tk.setMatKhau(pass);
					tk.setNgayCapNhat(date);
					
					tkDao = new TaiKhoanDao();
					int n = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn cập nhật tài khoản này không?", "Cảnh báo",
							JOptionPane.YES_NO_OPTION);
					if(n == 0) {
						if(tkDao.capNhatUpdate(txtMaNV.getText(), tk)) {
							JOptionPane.showMessageDialog(this, "Cập nhật thành công!");
							docDuLieuTaiKhoan();
							return;
						}
					}
				}
			}
		}
	}
	
	private boolean validThem() {
		if(txtMaNV.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "Vui lòng chọn một tài khoản"
					+ " \n     trên bảng để thao tác!");
			return false;
		}
		
		if(txtTenTK.getText().equals("") || txtMatKhau.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!");
			return false;
		}	
		
		tkDao = new TaiKhoanDao();
		dsTK = tkDao.getAllKH();
		int index = 0;
		boolean check = false;
		for(TaiKhoan tk : dsTK) {
			if(tk.getTenTaiKhoan().equals(txtTenTK.getText())) {
				index++;
				try {
					if(!tk.getMatKhau().equals("")) {
						check = true;
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}
		
		if(check) {
			JOptionPane.showMessageDialog(this, "Nhân viên này đã có tài khoản rồi!");
			return false;
		}
		
		if(index != 0 && check == true) {
			JOptionPane.showMessageDialog(this, "Tên tài khoản đã trùng, vui lòng thử lại tên khác!");
			txtTenTK.requestFocus();
			txtTenTK.selectAll();
			return false;
		}
		
		if(txtMaNV.getText().charAt(0) == 'C' && (txtTenTK.getText().charAt(0) != 'C' || txtTenTK.getText().charAt(1) != 'N')) {
			JOptionPane.showMessageDialog(this, "Qui tắc đặt tên tài khoản: \nCông nhân: phải bắt đầu bằng kí tự \"CN\" "
					+ "\nNhân viên hành chánh: phải bắt đầu bằng kí tự \"NVHC\"");
			return false;
		}
		
		if(txtMaNV.getText().charAt(0) == 'N' && (txtTenTK.getText().charAt(0) != 'N' || txtTenTK.getText().charAt(1) != 'V'
				|| txtTenTK.getText().charAt(2) != 'H' || txtTenTK.getText().charAt(3) != 'C')) {
			JOptionPane.showMessageDialog(this, "Qui tắc đặt tên tài khoản: \nCông nhân: phải bắt đầu bằng kí tự \"CN\" "
					+ "\nNhân viên hành chánh: phải bắt đầu bằng kí tự \"NVHC\"");
			return false;
		}
		
		return true;
	}
	
	private boolean validCapNhat() {
		if(txtMaNV.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "Vui lòng chọn một tài khoản"
					+ " \n     trên bảng để thao tác!");
			return false;
		}
		
		tkDao = new TaiKhoanDao();
		dsTK = tkDao.getAllKH();
		boolean check = true;
		for(TaiKhoan tk : dsTK) {
			if(tk.getTenTaiKhoan().equals(txtTenTK.getText())) {
				try {
					if(!tk.getMatKhau().equals("")) {
						check = false;
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}
		
		if(check) {
			JOptionPane.showMessageDialog(this, "Không thể cập nhật vì nhân viên này \n          chưa tạo tài khoản!");
			return false;
		}
		
		if(txtTenTK.getText().equals("") || txtMatKhau.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!");
			return false;
		}	
		
		if(txtMaNV.getText().charAt(0) == 'C' && (txtTenTK.getText().charAt(0) != 'C' || txtTenTK.getText().charAt(1) != 'N')) {
			JOptionPane.showMessageDialog(this, "Qui tắc đặt tên tài khoản: \nCông nhân: phải bắt đầu bằng kí tự \"CN\" "
					+ "\nNhân viên hành chánh: phải bắt đầu bằng kí tự \"NVHC\"");
			return false;
		}
		
		if(txtMaNV.getText().charAt(0) == 'N' && (txtTenTK.getText().charAt(0) != 'N' || txtTenTK.getText().charAt(1) != 'V'
				|| txtTenTK.getText().charAt(2) != 'H' || txtTenTK.getText().charAt(3) != 'C')) {
			JOptionPane.showMessageDialog(this, "Qui tắc đặt tên tài khoản: \nCông nhân: phải bắt đầu bằng kí tự \"CN\" "
					+ "\nNhân viên hành chánh: phải bắt đầu bằng kí tự \"NVHC\"");
			return false;
		}
		
		return true;
	}
}
