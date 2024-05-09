package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.Luong;
import entity.NgayNghi;
import entity.NhanVien;

public class LuongDao {
	private static LocalDate date1;
	private static String dateCurrently;
	private Connection ketNoi;
	public LuongDao() {
		// TODO Auto-generated constructor stub
		ketNoi = ConnectDB.getKetNoi();
	}

	
	/**
	 * Thêm một lương cho nhân viên
	 * @return true nếu thêm thành công và ngược lại
	 */
	public boolean themLuong(Luong tk) {
		String sql = "insert into Luong values(?,?,?,?,?,?,?,?,?,?)";
		int n;
		PreparedStatement stmt = null;
		try {
			stmt = ketNoi.prepareStatement(sql);
			stmt.setString(1, tk.getMaLuong());
			stmt.setString(2, tk.getNgayNghi().getMaNgayNghi());
			stmt.setInt(3, tk.getSoNgayNghiCoPhep());
			stmt.setInt(4, tk.getSoNgayNghiKhongPhep());
			stmt.setInt(5, tk.getSoSanPhamHoanThien());
			stmt.setFloat(6, tk.getHeSoLuong());
			stmt.setDate(7, (Date) tk.getNgayTao());
			stmt.setFloat(8, tk.getTongTien());
			stmt.setString(9, tk.getNhanVien().getMaNhanVien());
			stmt.setString(10, tk.getCongDoan());
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
	 * Hàm lấy tất cả thông tin có trong bảng Lương
	 * @return Trả về list danh sách lương
	 */
	public List<Luong> getAllLuong() {
		List<Luong> dsLuong = new ArrayList<Luong>();
		PreparedStatement stmt = null;
		String sql = "select * from Luong";
		try {
			stmt = ketNoi.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Luong luong = new Luong();
				NhanVien nv = new NhanVien(rs.getString("MaNhanVien"));
				NgayNghi ngayNghi = new NgayNghi();
				luong.setMaLuong(rs.getString("MaLuong"));
				luong.setNgayNghi(ngayNghi);
				luong.setSoNgayNghiCoPhep(rs.getInt("SoNgayNghiCoPhep"));
				luong.setSoNgayNghiKhongPhep(rs.getInt("SoNgayNghi"));
				luong.setSoSanPhamHoanThien(rs.getInt("SoSanPhamHoanThien"));
				luong.setHeSoLuong(rs.getFloat("HeSoLuong"));
				luong.setNgayTao(rs.getDate("NgayTao"));
				luong.setTongTien(rs.getFloat("TongTien"));
				luong.setNhanVien(nv);
				luong.setCongDoan(rs.getString("CongDoan"));
				dsLuong.add(luong);
			}
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsLuong;
	}
		

			public List<Luong> getAllLuongGiam() {
				List<Luong> dsLuong = new ArrayList<Luong>();
				PreparedStatement stmt = null;
				String sql = "select * from Luong ORDER BY TongTien DESC";
				try {
					stmt = ketNoi.prepareStatement(sql);
					ResultSet rs = stmt.executeQuery();
					while (rs.next()) {
						Luong luong = new Luong();
						NhanVien nv = new NhanVien(rs.getString("MaNhanVien"));
						NgayNghi ngayNghi = new NgayNghi();
						luong.setMaLuong(rs.getString("MaLuong"));
						luong.setNgayNghi(ngayNghi);
						luong.setSoNgayNghiCoPhep(rs.getInt("SoNgayNghiCoPhep"));
						luong.setSoNgayNghiKhongPhep(rs.getInt("SoNgayNghi"));
						luong.setSoSanPhamHoanThien(rs.getInt("SoSanPhamHoanThien"));
						luong.setHeSoLuong(rs.getFloat("HeSoLuong"));
						luong.setNgayTao(rs.getDate("NgayTao"));
						luong.setTongTien(rs.getFloat("TongTien"));
						luong.setNhanVien(nv);
						luong.setCongDoan(rs.getString("CongDoan"));
						dsLuong.add(luong);
					}
					stmt.close();
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return dsLuong;
			}
			public List<Luong> getAllLuongTang() {
				List<Luong> dsLuong = new ArrayList<Luong>();
				PreparedStatement stmt = null;
				String sql = "select * from Luong ORDER BY TongTien ASC";
				try {
					stmt = ketNoi.prepareStatement(sql);
					ResultSet rs = stmt.executeQuery();
					while (rs.next()) {
						Luong luong = new Luong();
						NhanVien nv = new NhanVien(rs.getString("MaNhanVien"));
						NgayNghi ngayNghi = new NgayNghi();
						luong.setMaLuong(rs.getString("MaLuong"));
						luong.setNgayNghi(ngayNghi);
						luong.setSoNgayNghiCoPhep(rs.getInt("SoNgayNghiCoPhep"));
						luong.setSoNgayNghiKhongPhep(rs.getInt("SoNgayNghi"));
						luong.setSoSanPhamHoanThien(rs.getInt("SoSanPhamHoanThien"));
						luong.setHeSoLuong(rs.getFloat("HeSoLuong"));
						luong.setNgayTao(rs.getDate("NgayTao"));
						luong.setTongTien(rs.getFloat("TongTien"));
						luong.setNhanVien(nv);
						luong.setCongDoan(rs.getString("CongDoan"));
						dsLuong.add(luong);
					}
					stmt.close();
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return dsLuong;
			}	
			
	public List<Luong> getAllLuongNV() {
		List<Luong> dsLuong = new ArrayList<Luong>();
		PreparedStatement stmt = null;
		String sql = "select * from Luong ";
		try {
			stmt = ketNoi.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Luong luong = new Luong();
				NhanVien nv = new NhanVien(rs.getString("MaNhanVien"));
				NgayNghi ngayNghi = new NgayNghi();
				luong.setMaLuong(rs.getString("MaLuong"));
				luong.setNgayNghi(ngayNghi);
				luong.setSoNgayNghiCoPhep(rs.getInt("SoNgayNghiCoPhep"));
				luong.setSoNgayNghiKhongPhep(rs.getInt("SoNgayNghi"));
				luong.setSoSanPhamHoanThien(rs.getInt("SoSanPhamHoanThien"));
				luong.setHeSoLuong(rs.getFloat("HeSoLuong"));
				luong.setNgayTao(rs.getDate("NgayTao"));
				luong.setTongTien(rs.getFloat("TongTien"));
				luong.setNhanVien(nv);
				dsLuong.add(luong);
			}
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsLuong;
	}	
				
	

	public static void main(String[] args) {
		
	}
}
