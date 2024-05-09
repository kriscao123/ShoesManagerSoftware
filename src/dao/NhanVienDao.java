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
import entity.SanPham;

public class NhanVienDao {
	private Connection ketNoi;
	private List<NhanVien> dsNV;
	public NhanVienDao() {
		// TODO Auto-generated constructor stub
		ketNoi = ConnectDB.getKetNoi();
	}
	
	/**
	 * Hàm thêm nhân viên khi có thông tin một nhân viên
	 * @param nv
	 * @return true nếu thêm thành công và false nếu thêm thất bại
	 */
	public boolean themNhanVien(NhanVien nv) {
		String sql = "insert into NhanVien values(?,?,?,?,?,?,?,?)";
		int n;
		try {
			PreparedStatement stmt = ketNoi.prepareStatement(sql);
			stmt.setString(1, nv.getMaNhanVien());
			stmt.setString(2, nv.getTenNhanVien());
			stmt.setString(3, nv.getGioiTinh());
			stmt.setDate(4, (Date) nv.getNgaySinh());
			stmt.setString(5, nv.getBoPhan());
			stmt.setDate(6, (Date) nv.getNgayVaoLam());
			stmt.setString(7, nv.getNgoaiNgu());
			stmt.setString(8, nv.getSanPham().getMaSanPham());
			try {
				n = stmt.executeUpdate();
				if (n > 0)
					return true;
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Tìm thông tin nhân viên khi có mã nhân viên
	 * @param maNhanVien
	 * @return Nhân viên cần tìm
	 */
	public NhanVien getNhanVienTheoMa(String maNhanVien) {
		NhanVien nv = new NhanVien();
		PreparedStatement stmt = null;
		String sql = "select * from NhanVien where MaNhanVien = '" + maNhanVien + "'";
		try {
			stmt = ketNoi.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				SanPham sp = new SanPham();
				nv.setMaNhanVien(rs.getString("MaNhanVien"));
				nv.setTenNhanVien(rs.getString("TenNhanVien"));
				nv.setGioiTinh(rs.getString("GioiTinh"));
				nv.setNgaySinh(rs.getDate("NgaySinh"));
				nv.setBoPhan(rs.getString("BoPhan"));
				nv.setNgayVaoLam(rs.getDate("NgayVaoLam"));
				nv.setNgoaiNgu(rs.getString("NgoaiNgu"));
				nv.setSanPham(sp);
			}
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nv;
	}
	
	/**
	 * Hàm cập nhật thông tin nhân viên
	 * @param maNhanVien
	 * @param nv
	 * @return true nếu cập nhật thành công và false nếu thất bại
	 */
	public boolean capNhat(String maNhanVien ,NhanVien nv) {
		int n = 0;
		PreparedStatement stmt = null;
		try {
			stmt = ketNoi.prepareStatement("update NhanVien set TenNhanVien = ?, GioiTinh = ?, NgaySinh = ?"
					+ ",BoPhan = ?, NgayVaoLam = ?, NgoaiNgu = ? where MaNhanVien = ?");
			stmt.setString(1, nv.getTenNhanVien());
			stmt.setString(2, nv.getGioiTinh());
			stmt.setDate(3, (Date) nv.getNgaySinh());
			stmt.setString(4, nv.getBoPhan());
			stmt.setDate(5, (Date) nv.getNgayVaoLam());
			stmt.setString(6, nv.getNgoaiNgu());
			stmt.setString(7, maNhanVien);
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
	
	public NhanVien getNhanVien(String maNhanVien) {
		NhanVien nv = new NhanVien();
		PreparedStatement stmt = null;
		String sql = "select * from NhanVien where MaNhanVien = '" + maNhanVien + "'";
		try {
			stmt = ketNoi.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				SanPham sp = new SanPham();
				nv.setTenNhanVien(rs.getString("TenNhanVien"));
				nv.setGioiTinh(rs.getString("GioiTinh"));
				nv.setNgaySinh(rs.getDate("NgaySinh"));
				nv.setBoPhan(rs.getString("BoPhan"));
				nv.setNgayVaoLam(rs.getDate("NgayVaoLam"));
				nv.setNgoaiNgu(rs.getString("NgoaiNgu"));
				nv.setSanPham(sp);
			}
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nv;
	}
	
	/**
	 * Tìm nhân viên theo mã nhân viên. verion:K
	 * @param maNhanVien
	 * @return
	 */
	public List<NhanVien> timTheoMa_VerK(String maNhanVien) {
		dsNV = new ArrayList<NhanVien>();
		NhanVien nv = new NhanVien();
		PreparedStatement stmt = null;
		String sql = "Select * from NhanVien where MaNhanVien = '" + maNhanVien +"'";
		try {
			stmt = ketNoi.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {

				nv.setMaNhanVien(rs.getString("MaNhanVien"));
				nv.setTenNhanVien(rs.getString("TenNhanVien"));
				nv.setNgayVaoLam(rs.getDate("NgayVaoLam"));
				nv.setGioiTinh(rs.getString("GioiTinh"));
				nv.setNgaySinh(rs.getDate("NgaySinh"));
				nv.setBoPhan(rs.getString("BoPhan"));
				nv.setNgoaiNgu(rs.getString("NgoaiNgu"));
				dsNV.add(nv);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsNV;
	}
	
	/**
	 * Tìm nhân viên theo tên nhân viên. Version: K
	 * @param tenNhanVien
	 * @return
	 */
	public List<NhanVien> timTheoTen_VerK(String tenNhanVien) {
		dsNV = new ArrayList<NhanVien>();
		NhanVien nv = new NhanVien();
		PreparedStatement stmt = null;
		String sql = "Select * from NhanVien where TenNhanVien = N'" + tenNhanVien +"'";
		try {
			stmt = ketNoi.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {

				nv.setMaNhanVien(rs.getString("MaNhanVien"));
				nv.setTenNhanVien(rs.getString("TenNhanVien"));
				nv.setNgayVaoLam(rs.getDate("NgayVaoLam"));
				nv.setGioiTinh(rs.getString("GioiTinh"));
				nv.setNgaySinh(rs.getDate("NgaySinh"));
				nv.setBoPhan(rs.getString("BoPhan"));
				nv.setNgoaiNgu(rs.getString("NgoaiNgu"));
				dsNV.add(nv);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsNV;
	}
	
	/**
	 * Tìm nhân viên theo bộ phận. Version:K
	 * @param boPhan
	 * @return
	 */
	public List<NhanVien> timTheoBoPhan_VerK(String boPhan) {
		dsNV = new ArrayList<NhanVien>();
		PreparedStatement stmt = null;
		String sql = "Select * from NhanVien where BoPhan = N'" + boPhan +"'";
		try {
			stmt = ketNoi.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				NhanVien nv = new NhanVien();
				nv.setMaNhanVien(rs.getString("MaNhanVien"));
				nv.setTenNhanVien(rs.getString("TenNhanVien"));
				nv.setNgayVaoLam(rs.getDate("NgayVaoLam"));
				nv.setGioiTinh(rs.getString("GioiTinh"));
				nv.setNgaySinh(rs.getDate("NgaySinh"));
				nv.setBoPhan(rs.getString("BoPhan"));
				nv.setNgoaiNgu(rs.getString("NgoaiNgu"));
				dsNV.add(nv);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsNV;
	}

	
	
	/**
	 * Tìm thông tin nhân viên khi có tên công nhân
	 * @param tenCongNhan
	 * @return Nhân viên cần tìm
	 */
	public NhanVien getNhanVienTheoTen(String tenCongNhan) {
		NhanVien nv = new NhanVien();
		PreparedStatement stmt = null;
		String sql = "select * from NhanVien where MaNhanVien = '" + tenCongNhan + "'";
		try {
			stmt = ketNoi.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				SanPham sp = new SanPham();
				nv.setMaNhanVien(rs.getString("MaNhanVien"));
				nv.setTenNhanVien(rs.getString("TenNhanVien"));
				nv.setGioiTinh(rs.getString("GioiTinh"));
				nv.setNgaySinh(rs.getDate("NgaySinh"));
				nv.setBoPhan(rs.getString("BoPhan"));
				nv.setNgayVaoLam(rs.getDate("NgayVaoLam"));
				nv.setNgoaiNgu(rs.getString("NgoaiNgu"));
				nv.setSanPham(sp);
			}
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nv;
	}
	
	/**
	 * Hàm lấy tất cả thông tin có trong bảng nhân viên
	 * @return Trả về list danh sách nhân viên
	 */
	public List<NhanVien> getAllNV() {
		List<NhanVien> dSNV = new ArrayList<NhanVien>();
		PreparedStatement stmt = null;
		String sql = "select * from NhanVien";
		try {
			stmt = ketNoi.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				NhanVien nv = new NhanVien();
				SanPham sp = new SanPham(rs.getString("MaSanPham"));
				nv.setMaNhanVien(rs.getString("MaNhanVien"));
				nv.setTenNhanVien(rs.getString("TenNhanVien"));
				nv.setGioiTinh(rs.getString("GioiTinh"));
				nv.setNgaySinh(rs.getDate("NgaySinh"));
				nv.setBoPhan(rs.getString("BoPhan"));
				nv.setNgayVaoLam(rs.getDate("NgayVaoLam"));
				nv.setNgoaiNgu(rs.getString("NgoaiNgu"));
				nv.setSanPham(sp);
				dSNV.add(nv);
			}
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dSNV;
	}

	/**
	 * Hàm thêm một nhân viên vào cơ sở dữ liệu
	 * @param nhanVien
	 * @return trả về true nếu thêm thành công và ngược lại
	 */
	public Boolean themNV(NhanVien nhanVien) {
		String sql = "insert into NhanVien values(?,?,?,?,?,?,?,?)";
		int n;
		try {
			PreparedStatement stmt = ketNoi.prepareStatement(sql);
			NhanVien nv = new NhanVien();
			stmt.setString(1, nhanVien.getMaNhanVien());
			stmt.setString(2, nhanVien.getTenNhanVien());
			stmt.setString(3, nhanVien.getGioiTinh());
			stmt.setDate(4, (Date) nhanVien.getNgaySinh());
			stmt.setString(5,  nhanVien.getBoPhan());
			stmt.setDate(6,  (Date) nhanVien.getNgayVaoLam());
			stmt.setString(7,  nhanVien.getNgoaiNgu());
			stmt.setObject(8,  nhanVien.getSanPham());
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
	 * Hàm xóa nhân viên khi có thông tin mã nhân viên
	 * @param maNhanVien
	 * @return trả về true nếu xóa thành công và ngược lại
	 */
	public boolean xoaNhanVien(String maNhanVien) {
		try {
			PreparedStatement stmt = ketNoi.prepareStatement("delete from NhanVien where MaNhanVien = ?");
			stmt.setString(1, maNhanVien);
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

	public boolean update(String ma, NhanVien nv) {
		PreparedStatement stmt = null;
		try {
			stmt = ConnectDB.getKetNoi().prepareStatement("update NhanVien set TenNhanVien = ?,GioiTinh = ?,"
					+ "NgaySinh = ?, NgayVaoLam = ?, NgoaiNgu = ? where MaNhanVien = ?");
			stmt.setString(1, nv.getTenNhanVien());
			stmt.setString(2, nv.getGioiTinh());
			stmt.setDate(3, (Date) nv.getNgaySinh());
			stmt.setDate(4, (Date) nv.getNgayVaoLam());
			stmt.setString(5, nv.getNgoaiNgu());
			stmt.setString(6, ma);
			stmt.executeUpdate();
			int n = stmt.executeUpdate();
			if (n > 0)
				return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;

	}
	

	

	public static void main(String[] args) {
		NhanVienDao nv = new NhanVienDao();
//		System.out.println(nv.getAllNV());
	}
}
