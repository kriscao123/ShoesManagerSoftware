package GUI;


import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import connectDB.ConnectDB;
import dao.TaiKhoanDao;


public class FrmLogin extends JFrame implements ActionListener, MouseListener, KeyListener{
	private javax.swing.JButton btnLogin;
    private JLabel lblCancel;
    private JLabel lblRefresh;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblTitile;
    private javax.swing.JLabel lblUserName;
    private javax.swing.JPanel pnlLogin;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUserName;
	private TaiKhoanDao DAO;
	
	public String temp = "";
	private GD_NhanVienKeToan nv;
	private static HashCode hashCode;

	public FrmLogin() {
		initComponents();
		setTitle("Đăng Nhập");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String args[]) {        
    	EventQueue.invokeLater(new Runnable() {
    		public void run() {
    			try {
    				FrmLogin frame = new FrmLogin();
    				frame.setVisible(true);
    			} catch (Exception e) {
    				e.printStackTrace();
    			}
    		}
    	});
    }
	
	private void initComponents() {
		pnlLogin = new javax.swing.JPanel();
        lblTitile = new javax.swing.JLabel();
        lblUserName = new javax.swing.JLabel();
        txtUserName = new javax.swing.JTextField();
        lblPassword = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        btnLogin = new javax.swing.JButton();
        lblRefresh = new javax.swing.JLabel();
        lblCancel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(600, 500));
        setMinimumSize(new java.awt.Dimension(600, 500));
        setResizable(false);
        setSize(new java.awt.Dimension(600, 500));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlLogin.setBackground(new java.awt.Color(255, 255, 255));

        lblTitile.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        lblTitile.setForeground(new java.awt.Color(0, 123, 255));
        lblTitile.setText("ĐĂNG NHẬP TÀI KHOẢN");

        lblUserName.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        lblUserName.setText("Tài khoản");

        txtUserName.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtUserName.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 123, 255), 1, true));

        lblPassword.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        lblPassword.setText("Mật khẩu");
        

        txtPassword.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtPassword.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 123, 255), 1, true));

        btnLogin.setBackground(new java.awt.Color(0, 123, 255));
        btnLogin.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        btnLogin.setForeground(new java.awt.Color(255, 255, 255));
        btnLogin.setText("ĐĂNG NHẬP");

        lblRefresh.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblRefresh.setForeground(new java.awt.Color(0, 123, 255));
        lblRefresh.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        lblRefresh.setText("Làm mới");

        lblCancel.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblCancel.setForeground(new java.awt.Color(0, 123, 255));
        lblCancel.setText("Thoát");

        javax.swing.GroupLayout pnlLoginLayout = new javax.swing.GroupLayout(pnlLogin);
        pnlLogin.setLayout(pnlLoginLayout);
        pnlLoginLayout.setHorizontalGroup(
            pnlLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLoginLayout.createSequentialGroup()
                .addGap(102, 102, 102)
                .addGroup(pnlLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblTitile, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUserName)
                    .addComponent(lblPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPassword)
                    .addComponent(btnLogin, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlLoginLayout.createSequentialGroup()
                        .addComponent(lblCancel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 281, Short.MAX_VALUE)
                        .addComponent(lblRefresh)))
                .addContainerGap(66, Short.MAX_VALUE))
        );
        pnlLoginLayout.setVerticalGroup(
            pnlLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLoginLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(lblTitile, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(lblUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblRefresh, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(lblCancel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(83, Short.MAX_VALUE))
        );

        
        getContentPane().add(pnlLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 600, 500));
        pack();
        txtUserName.setText("KT001");
//        txtUserName.setText("CN001");
        txtPassword.setText("123456");
        
        //Phần xử lí action listenner
        btnLogin.addActionListener(this);
        lblCancel.addMouseListener(this);	
        lblRefresh.addMouseListener(this);
		txtUserName.addKeyListener(this);
		txtPassword.addKeyListener(this);	
	}


	@Override
	public void actionPerformed(ActionEvent e) {
				Object o = e.getSource();
				if (o.equals(btnLogin)) {
					if(txtUserName.getText().equals("") || txtPassword.equals("")) {
						thongBao(txtUserName, "Vui lòng nhập đầy đủ thông tin!");
						return;
					}
					if (valid()) {
						if (KiemTraTaiKhoan(txtUserName.getText(), hashCode.hash(txtPassword.getText()))) {
							if(txtUserName.getText().charAt(0) == 'C' || txtUserName.getText().charAt(0) == 'N') {
								this.setVisible(false);
								GD_NhanVien login = new GD_NhanVien(txtUserName.getText());
								login.from_NhanVien.setVisible(true);
							}
							else 
								try {
									this.setVisible(false);
									GD_NhanVienKeToan window = new GD_NhanVienKeToan(txtUserName.getText());
									window.form_NhanVienKeToan.setVisible(true);
									return;
								} catch (Exception e1) {
									e1.printStackTrace();
								}
							
						} else {
							JOptionPane.showMessageDialog(this, "Mật khẩu hay tài khoản không chính xác!!!");
						}
					}
				} else if (o.equals(btnLogin)) {
					txtUserName.requestFocus();
					txtUserName.selectAll();
					txtUserName.setText("");
				}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Object o = e.getSource();
		if(o.equals(lblCancel)) {
			System.exit(0);
		}
		
		else if(o.equals(lblRefresh)) {
			txtUserName.setText("");
			txtPassword.setText("");
			txtUserName.requestFocus();
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
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
				Object o = e.getSource();
					if (e.getKeyChar() == KeyEvent.VK_ENTER) {
						if(txtUserName.getText().equals("") || txtPassword.equals("")) {
							thongBao(txtUserName, "Vui lòng nhập đầy đủ thông tin!");
							return;
						}
						if (valid()) {
							if (KiemTraTaiKhoan(txtUserName.getText(), hashCode.hash(txtPassword.getText()))) {
								if(txtUserName.getText().charAt(0) == 'C' || txtUserName.getText().charAt(0) == 'N') {
									this.setVisible(false);
									GD_NhanVien login = new GD_NhanVien(txtUserName.getText());
									login.from_NhanVien.setVisible(true);
								}
								else 
									try {
										this.setVisible(false);
										GD_NhanVienKeToan window = new GD_NhanVienKeToan(txtUserName.getText());
										window.form_NhanVienKeToan.setVisible(true);
										return;
									} catch (Exception e1) {
										e1.printStackTrace();
									}
								
							} else {
								JOptionPane.showMessageDialog(this, "Mật khẩu hay tài khoản không chính xác!!!");
							}
						}
					}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub		
	}
	
	private void thongBao(JTextField tf, String mes) {
		JOptionPane.showMessageDialog(this, mes);
		tf.selectAll();
		tf.requestFocus();
	}
	
	@SuppressWarnings("deprecation")
	private boolean valid() {
		String taiKhoan = txtUserName.getText().trim();
		if (taiKhoan.equals("")) {
			thongBao(txtUserName, "Tài khoản không được để trống");
			return false;
		} else if (txtPassword.getText().equals("")) {
			thongBao(txtPassword, "Bạn chưa nhập mật khẩu");
			return false;

		}
		return true;
	}
	
	private boolean KiemTraTaiKhoan(String taiKhoan, String matKhau) {
		String sql = "select * from TaiKhoan where TenTaiKhoan = '" + taiKhoan + "' and MatKhau = '" + matKhau + "'";
		ResultSet rs = null;
		boolean tmp = false;
		try {
			PreparedStatement stmt = ConnectDB.getKetNoi().prepareStatement(sql);
			rs = stmt.executeQuery();
			tmp = rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tmp;
	}
}
