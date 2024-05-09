package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.NhanVien;
import entity.TaiKhoan;

public class TaiKhoanDao {
	private Connection ketNoi;
	private ArrayList<TaiKhoan> dsTK;
	public TaiKhoanDao() {
		// TODO Auto-generated constructor stub
		ketNoi = ConnectDB.getKetNoi();
	}

	/**
	 * Láy thông tin tài khoản khi có tên tài khoản
	 * @param tenTaiKhoan
	 * @return Tài Khoản cần tìm
	 */
	public TaiKhoan getTaiKhoan(String tenTaiKhoan) {
		TaiKhoan tk = new TaiKhoan();
		PreparedStatement stmt = null;
		String sql = "select * from TaiKhoan where TenTaiKhoan = '" + tenTaiKhoan + "'";
		try {
			stmt = ketNoi.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				NhanVien nv = new NhanVien();
				tk.setTenTaiKhoan(rs.getString("TenTaiKhoan"));
				tk.setMatKhau(rs.getString("MatKhau"));
				tk.setNgayTao(rs.getDate("NgayTao"));
				tk.setNgayCapNhat(rs.getDate("NgayCapNhat"));
				tk.setNhanVien(nv);
			}
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tk;
	}
	
	public boolean capNhat(String tenTaiKhoan ,TaiKhoan tk) {
		int n = 0;
		PreparedStatement stmt = null;
		try {
			stmt = ketNoi.prepareStatement("update TaiKhoan set MatKhau = ?, NgayCapNhat = ? where TenTaiKhoan = ?");
			stmt.setString(1, tk.getMatKhau());
			stmt.setDate(2, (Date) tk.getNgayCapNhat());
			stmt.setString(3, tenTaiKhoan);
			n = stmt.executeUpdate();
		}  catch (SQLException ex) {
			// TODO: handle exception
			ex.printStackTrace();
		} finally {
			try {
				stmt.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}

		}
		return n > 0;
	}
	
	/**
	 * Hàm thêm tài khoản trả về true nếu thêm thành công và false nếu thất bại
	 * @param tk
	 * @return
	 */
	public boolean themTaiKhoan(TaiKhoan tk) {
		String sql = "insert into TaiKhoan values(?,?,?,?,?)";
		int n;
		try {
			PreparedStatement stmt = ketNoi.prepareStatement(sql);
			stmt = ketNoi.prepareStatement(sql);
			stmt.setString(1, tk.getTenTaiKhoan());
			stmt.setString(2, tk.getNhanVien().getMaNhanVien());
			stmt.setString(3, tk.getMatKhau());
			stmt.setDate(4, (Date) tk.getNgayTao());
			stmt.setDate(5, (Date) tk.getNgayCapNhat());
			try {
				n = stmt.executeUpdate();
				if (n > 0)
					return true;
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} catch (SQLException e2) {
			// TODO: handle exception
			e2.printStackTrace();
		}
		return false;
	}
	
	/**
	 * Tìm tài khoản khi có mã nhân viên.
	 * @param maNhanVien
	 * @return
	 */
	public List<TaiKhoan> getTKTheoMaNhanVien(String maNhanVien) {
		TaiKhoan tk = new TaiKhoan();
		dsTK = new ArrayList<TaiKhoan>();
		PreparedStatement stmt = null;
		String sql = "select * from TaiKhoan where MaNhanVien = '" + maNhanVien + "'";
		try {
			stmt = ketNoi.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				NhanVien nv = new NhanVien();
				
				String tenTK = rs.getString(1);
				String maNV = rs.getString(2);
				String matKhau = rs.getString(3);
				Date ngayTao = rs.getDate(4);
				Date ngayCapNhat = rs.getDate(5);
				tk = new TaiKhoan(tenTK,new NhanVien(maNV), matKhau, ngayTao, ngayCapNhat);
				dsTK.add(tk);
			}
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsTK;
	}
	
	public List<TaiKhoan> getTaiKhoanTheoTen(String tenTaiKhoan) {
		TaiKhoan tk = new TaiKhoan();
		dsTK = new ArrayList<TaiKhoan>();
		PreparedStatement stmt = null;
		String sql = "select * from TaiKhoan where TenTaiKhoan = '" + tenTaiKhoan + "'";
		try {
			stmt = ketNoi.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				NhanVien nv = new NhanVien();	
				String tenTK = rs.getString(1);
				String maNhanVien = rs.getString(2);
				String matKhau = rs.getString(3);
				Date ngayTao = rs.getDate(4);
				Date ngayCapNhat = rs.getDate(5);
				tk = new TaiKhoan(tenTK,new NhanVien(maNhanVien), matKhau, ngayTao, ngayCapNhat);
				dsTK.add(tk);
			}
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsTK;
	}
	
	/**
	 * Hàm lấy tất cả thông tin có trong bảng tài khoản
	 * @return Trả về list danh sách tài khoản
	 */
	public List<TaiKhoan> getAllKH() {
		dsTK = new ArrayList<TaiKhoan>();
		Connection con = ConnectDB.getKetNoi();
		PreparedStatement stmt = null;
		String sql = "select * from TaiKhoan";
		try {
			stmt = ketNoi.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				TaiKhoan tk = new TaiKhoan();
				NhanVien nv = new NhanVien();
				
				String tenTaiKhoan = rs.getString(1);
				String maNhanVien = rs.getString(2);
				String matKhau = rs.getString(3);
				Date ngayTao = rs.getDate(4);
				Date ngayCapNhat = rs.getDate(5);
				
//				tk.setNhanVien(nv);
//				tk.setTenTaiKhoan(rs.getString("TenTaiKhoan"));
//				tk.setMatKhau(rs.getString("MatKhau"));
//				tk.setNgayTao(rs.getDate("NgayTao"));
//				tk.setNgayCapNhat(rs.getDate("NgayCapNhat"));
				
				tk = new TaiKhoan(tenTaiKhoan,new NhanVien(maNhanVien), matKhau, ngayTao, ngayCapNhat);
				dsTK.add(tk);
			}
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsTK;
	}

	/**
	 * Hàm thêm một tài khoản vào cơ sở dữ liệu
	 * @param tk
	 * @return trả về true nếu thêm thành công và ngược lại
	 */
	public Boolean themTK(TaiKhoan tk) {
		String sql = "insert into TaiKhoan values(?,?,?,?,?)";
		int n;
		try {
			PreparedStatement stmt = ketNoi.prepareStatement(sql);
			NhanVien nv = new NhanVien();
			stmt.setString(1, tk.getTenTaiKhoan());
			stmt.setString(2, tk.getMatKhau());
			stmt.setDate(3, (Date) tk.getNgayTao());
			stmt.setDate(4, (Date) tk.getNgayCapNhat());
			stmt.setObject(5, nv);
			try {
				n = stmt.executeUpdate();
				if (n > 0)
					return true;
			} catch (Exception ex) {
				// TODO: handle exception
				ex.printStackTrace();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	

	/**
	 * Hàm xóa tài khoản khi có thông tin tên tài khoản
	 * @param tk
	 * @return trả về true nếu xóa thành công và ngược lại
	 */
	public boolean deleteTK(String tk) {
		try {
			PreparedStatement stmt = ketNoi.prepareStatement("delete from TaiKhoan where TenTaiKhoan = ?");
			stmt.setString(1, tk);
			int n = stmt.executeUpdate();
			if (n > 0) {
				return true;
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
			return false;
	}
	
	public boolean capNhatThem(String ma, TaiKhoan tk) {
		int n = 0;
		PreparedStatement stmt = null;
		try {
			stmt = ketNoi.prepareStatement("update TaiKhoan set TenTaiKhoan = ?,MatKhau = ?"
					+ " , NgayTao = ?, NgayCapNhat = ? where MaNhanVien = ?");
			stmt.setString(1, tk.getTenTaiKhoan());
			stmt.setString(2, tk.getMatKhau());
			stmt.setDate(3, (Date) tk.getNgayTao());
			stmt.setDate(4, (Date) tk.getNgayCapNhat());
			stmt.setString(5, ma);
			n = stmt.executeUpdate();
		}  catch (SQLException ex) {
			// TODO: handle exception
			ex.printStackTrace();
		} finally {
			try {
				stmt.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}

		}
		return n > 0;
	}
	
	public boolean capNhatUpdate(String ma, TaiKhoan tk) {
		int n = 0;
		PreparedStatement stmt = null;
		try {
			stmt = ketNoi.prepareStatement("update TaiKhoan set TenTaiKhoan = ?,MatKhau = ?"
					+ ", NgayCapNhat = ? where MaNhanVien = ?");
			stmt.setString(1, tk.getTenTaiKhoan());
			stmt.setString(2, tk.getMatKhau());
			stmt.setDate(3, (Date) tk.getNgayCapNhat());
			stmt.setString(4, ma);
			n = stmt.executeUpdate();
		}  catch (SQLException ex) {
			// TODO: handle exception
			ex.printStackTrace();
		} finally {
			try {
				stmt.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}

		}
		return n > 0;
	}
	
	
	public static void main(String[] args) {
		TaiKhoanDao tkDao = new TaiKhoanDao();
		NhanVien x = new NhanVien();
//		x.setMaNhanVien("CN007");
		TaiKhoan tk = new TaiKhoan();
//		tk.setTenTaiKhoan("CN007");
//		tk.setNhanVien(x);
//		tkDao.themTaiKhoan(tk);
		System.out.println(tkDao.getAllKH());
	}
}
