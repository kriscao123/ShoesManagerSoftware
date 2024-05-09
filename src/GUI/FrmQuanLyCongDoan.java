package GUI;

import java.awt.Color;
import java.awt.Font;
import java.util.EventObject;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class FrmQuanLyCongDoan extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel taoFrame_QLCD;
	private JPanel panel_QLCongDoan;
	private JMenuBar menuBar_1;
	private JMenu mnCongDoan;
	private JMenuItem mntmCongDoan1;
	private JMenuItem mntmCongDoan2; 
	private JMenuItem mntmCongDoan3;
	private JMenuItem mntmCongDoan4;
	private JButton btnThem;
	private JButton btnSua;
	private JButton btnXoa;
	private JButton btnIn;
	private JTextField txtMaCD;
	private JTextField txtTenCD;
	private JTextField txtTenCN;
	private JComboBox cbTenCD;
	private JTextField txtSoluong;
	private JTextField txtDonGia;
	private JTextField txtDonGiaTangCa;
	private DefaultTableModel modelDanhSachCongNhan;
	private JTable table;
	private JMenuItem mntmCongDoan;
	private JTextField txtTimKiem;
	private JButton btnTimKiem;

	public FrmQuanLyCongDoan() { 
		getContentPane().add(taoFrame_QLCD()); 
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1200,800);
	}
	
	public static void main(String[] e) {
		new FrmQuanLyCongDoan().setVisible(true);
	}
	
	public JPanel taoFrame_QLCD() {
		panel_QLCongDoan = new JPanel();
		panel_QLCongDoan.setBackground(new Color(236, 171, 83));
		panel_QLCongDoan.setLayout(null);

		JLabel lbQLCongDoan = new JLabel("QUẢN LÝ CÔNG ĐOẠN");	
		lbQLCongDoan.setFont(new Font("Verdana", Font.BOLD, 18));
		lbQLCongDoan.setBounds(20, 10, 287, 38);
		lbQLCongDoan.setForeground(Color.WHITE);
		panel_QLCongDoan.add(lbQLCongDoan);
		
		JPanel panel_TTCongDoan = new JPanel();
		panel_TTCongDoan.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_TTCongDoan.setBackground(new Color(102, 255, 255));
		panel_TTCongDoan.setBounds(10, 60, 360, 440);
		panel_QLCongDoan.add(panel_TTCongDoan);
		panel_TTCongDoan.setLayout(null);
		
		JLabel lbMaCD = new JLabel("Mã công đoạn:");
		lbMaCD.setFont(new Font("Verdana", Font.PLAIN, 14));
		lbMaCD.setBounds(10, 20, 159, 37);
		panel_TTCongDoan.add(lbMaCD);

		txtMaCD = new JTextField();
		txtMaCD.setFont(new Font("Verdana", Font.PLAIN, 14));
		txtMaCD.setColumns(10);
		txtMaCD.setBounds(160, 22, 178, 37);
		txtMaCD.setEditable(false);
		panel_TTCongDoan.add(txtMaCD);

		JLabel lbTenCD = new JLabel("Tên công đoạn:");
		lbTenCD.setFont(new Font("Verdana", Font.PLAIN, 14));
		lbTenCD.setBounds(10, 80, 159, 37);
		panel_TTCongDoan.add(lbTenCD);
		
		cbTenCD = new JComboBox();
		cbTenCD.setModel(new DefaultComboBoxModel(new String[] {"Sản xuất dây giày", "Sản xuất đế giày",
				"Sản xuất thân giày", "Hoàn thiện sản phẩm"}));
		cbTenCD.setFont(new Font("Verdana", Font.PLAIN, 14));
		cbTenCD.setBounds(160, 82, 178, 37);
		panel_TTCongDoan.add(cbTenCD);
		
		JLabel lbTenCN = new JLabel("Tên công nhân:");
		lbTenCN.setFont(new Font("Verdana", Font.PLAIN, 14));
		lbTenCN.setBounds(10, 140, 159, 37);
		panel_TTCongDoan.add(lbTenCN);
		
		txtTenCN = new JTextField();
		txtTenCN.setFont(new Font("Verdana", Font.PLAIN, 14));
		txtTenCN.setColumns(10);
		txtTenCN.setBounds(160, 142, 178, 37);
		panel_TTCongDoan.add(txtTenCN);
		
		JLabel lbSoLuong = new JLabel("Số lượng:");
		lbSoLuong.setFont(new Font("Verdana", Font.PLAIN, 14));
		lbSoLuong.setBounds(10, 200, 159, 37);
		panel_TTCongDoan.add(lbSoLuong);
		
		txtSoluong= new JTextField();
		txtSoluong.setFont(new Font("Verdana", Font.PLAIN, 14));
		txtSoluong.setColumns(10);
		txtSoluong.setBounds(160, 202, 178, 37);
		panel_TTCongDoan.add(txtSoluong);
		
		JLabel lbDonGia = new JLabel("Đơn giá:");
		lbDonGia.setFont(new Font("Verdana", Font.PLAIN, 14));
		lbDonGia.setBounds(10, 260, 159, 37);
		panel_TTCongDoan.add(lbDonGia);
		
		txtDonGia = new JTextField();
		txtDonGia.setFont(new Font("Verdana", Font.PLAIN, 14));
		txtDonGia.setColumns(10);
		txtDonGia.setBounds(160, 262, 178, 37);
		panel_TTCongDoan.add(txtDonGia);
		
		JLabel lbDonGiaTangCa = new JLabel("Đơn giá tăng ca:");
		lbDonGiaTangCa.setFont(new Font("Verdana", Font.PLAIN, 14));
		lbDonGiaTangCa.setBounds(10, 320, 159, 37);
		panel_TTCongDoan.add(lbDonGiaTangCa);
		
		txtDonGiaTangCa = new JTextField();
		txtDonGiaTangCa.setFont(new Font("Verdana", Font.PLAIN, 14));
		txtDonGiaTangCa.setColumns(10);
		txtDonGiaTangCa.setBounds(160, 322, 178, 37);
		panel_TTCongDoan.add(txtDonGiaTangCa);
		
		JPanel panel_CongDoan = new JPanel();
		panel_CongDoan.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_CongDoan.setBackground(new Color(102, 255, 255));
		panel_CongDoan.setBounds(400, 60, 740, 660);
		panel_QLCongDoan.add(panel_CongDoan);
		panel_CongDoan.setLayout(null);
		
		menuBar_1 = new JMenuBar();
		menuBar_1.setBackground(new java.awt.Color(58, 46, 130));
		menuBar_1.setBounds(20, 20, 140, 40);	
		panel_CongDoan.add(menuBar_1);
		
		
		mnCongDoan = new JMenu("Công Đoạn");
		mnCongDoan.setIcon(new ImageIcon("img\\qlCongdoan.png"));
		mnCongDoan.setForeground(Color.WHITE);
		mnCongDoan.setBackground(new java.awt.Color(58, 46, 130));
		mnCongDoan.setHorizontalAlignment(SwingConstants.CENTER);
		mnCongDoan.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		menuBar_1.add(mnCongDoan);
		
		mntmCongDoan = new JMenuItem("Tất cả");
		mntmCongDoan.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mnCongDoan.add(mntmCongDoan);
		
		mntmCongDoan1 = new JMenuItem("Sản xuất dây dày");
		mntmCongDoan1.setIcon(new ImageIcon("img\\sxDaygiay.png"));
		mntmCongDoan1.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mnCongDoan.add(mntmCongDoan1);
		
		mntmCongDoan2 = new JMenuItem("Sản xuất đế giày");
		mntmCongDoan2.setIcon(new ImageIcon("img\\sxDegiay.png"));
		mntmCongDoan2.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mnCongDoan.add(mntmCongDoan2);
		
		mntmCongDoan3 = new JMenuItem("Sản xuất thân giày");
		mntmCongDoan3.setIcon(new ImageIcon("img\\sxThanGiay.png"));
		mntmCongDoan3.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mnCongDoan.add(mntmCongDoan3);
		
		mntmCongDoan4 = new JMenuItem("Hoàn thiện sản phẩm");
		mntmCongDoan4.setIcon(new ImageIcon("img\\spHoanthien.png"));
		mntmCongDoan4.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mnCongDoan.add(mntmCongDoan4);
		
		JPanel panel_ChucNang = new JPanel();
		panel_ChucNang.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_ChucNang.setBackground(new Color(102, 255, 255));
		panel_ChucNang.setBounds(10, 520, 360, 200);
		panel_QLCongDoan.add(panel_ChucNang);
		panel_ChucNang.setLayout(null);
		
		btnThem = new JButton("Thêm");
		btnThem.setIcon(new ImageIcon("img\\follow.png"));
		btnThem.setBackground(new java.awt.Color(58, 46, 130));
		btnThem.setForeground(Color.WHITE);
		btnThem.setFont(new Font("Verdana", Font.PLAIN, 14));
		btnThem.setBounds(30, 22, 130, 62);
		panel_ChucNang.add(btnThem);

		btnSua = new JButton("Sửa");
		btnSua.setIcon(new ImageIcon("img\\labor-day.png"));
		btnSua.setBackground(new java.awt.Color(58, 46, 130));
		btnSua.setForeground(Color.WHITE);
		btnSua.setFont(new Font("Verdana", Font.PLAIN, 14));
		btnSua.setBounds(200, 22, 130, 62);
		panel_ChucNang.add(btnSua);
		
		btnXoa = new JButton("Xóa");
		btnXoa.setIcon(new ImageIcon("img\\delete-user.png"));
		btnXoa.setBackground(new java.awt.Color(58, 46, 130));
		btnXoa.setForeground(Color.WHITE);
		btnXoa.setFont(new Font("Verdana", Font.PLAIN, 14));
		btnXoa.setBounds(30, 120, 130, 62);
		panel_ChucNang.add(btnXoa);
		
		btnIn = new JButton("Làm mới");
		btnIn.setIcon(new ImageIcon("img\\refresh.png"));
		btnIn.setBackground(new java.awt.Color(58, 46, 130));
		btnIn.setForeground(Color.WHITE);
		btnIn.setFont(new Font("Verdana", Font.PLAIN, 14));
		btnIn.setBounds(200, 120, 130, 62);
		panel_ChucNang.add(btnIn);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 70, 700, 580);
		panel_CongDoan.add(scrollPane);
		String[] header = { "Mã CN", "Tên công nhân", "Mã công đoạn", "Tên công đoạn", "Số lượng", "Đơn Giá",
				"Đơn giá (TC)"};
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

		table.getColumnModel().getColumn(0).setPreferredWidth(70);
		table.getColumnModel().getColumn(1).setPreferredWidth(120);
		table.getColumnModel().getColumn(2).setPreferredWidth(120);
		table.getColumnModel().getColumn(3).setPreferredWidth(120);
		table.getColumnModel().getColumn(4).setPreferredWidth(80);
		table.getColumnModel().getColumn(5).setPreferredWidth(70);
		table.getColumnModel().getColumn(6).setPreferredWidth(100);
		
		txtTimKiem = new JTextField();
		txtTimKiem.setFont(new Font("Verdana", Font.PLAIN, 14));
		txtTimKiem.setColumns(10);
		txtTimKiem.setBounds(180, 20, 390, 34);
		panel_CongDoan.add(txtTimKiem);

		btnTimKiem = new JButton("Tìm Kiếm");
		btnTimKiem.setIcon(new ImageIcon("img\\search.png"));
		btnTimKiem.setBackground(new java.awt.Color(58, 46, 130));
		btnTimKiem.setForeground(Color.WHITE);
		btnTimKiem.setFont(new Font("Verdana", Font.PLAIN, 16));
		btnTimKiem.setBounds(580, 20, 150, 34);
		panel_CongDoan.add(btnTimKiem);
		
		return panel_QLCongDoan;	
	}
}
