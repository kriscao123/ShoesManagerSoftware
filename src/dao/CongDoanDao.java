package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.CongDoan;
import entity.SanPham;

public class CongDoanDao {
	private Connection ketNoi;
	public CongDoanDao() {
		// TODO Auto-generated constructor stub
		ketNoi = ConnectDB.getKetNoi();
	}

	/**
	 * Tìm công đoạn khi có tên công đoạn
	 * @param maNhanVien
	 * @return Công đoạn cần tìm
	 */
	public CongDoan getCongDoan(String tenCongDoan) {
		CongDoan cd = new CongDoan();
		PreparedStatement stmt = null;
		String sql = "select * from CongDoan where TenCongDoan = '" + tenCongDoan + "'";
		try {
			stmt = ketNoi.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				SanPham sp = new SanPham();
				cd.setTenCongDoan(rs.getString("TenCongDoan"));
				cd.setDonGia(rs.getFloat("DonGia"));
				cd.setDonGiaTangCa(rs.getFloat("DonGiaTangCa"));
				cd.setSoLuong(rs.getInt("SoLuong"));
				cd.setChiTieu(rs.getInt("ChiTieu"));
				cd.setSanPham(sp);
			}
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cd;
	}
	
	
	/**
	 * Lấy dữ liệu toàn bộ bảng công đoạn
	 * @return Danh sách công đoạn 
	 * 
	 */
	public List<CongDoan> getAllCD() {
		List<CongDoan> dsCD = new ArrayList<CongDoan>();
		PreparedStatement stmt = null;
		String sql = "select * from CongDoan";
		try {
			stmt = ketNoi.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				CongDoan cd = new CongDoan();
				SanPham sp = new SanPham();
				cd.setTenCongDoan(rs.getString("TenCongDoan"));
				cd.setDonGia(rs.getFloat("DonGia"));
				cd.setDonGiaTangCa(rs.getFloat("DonGiaTangCa"));
				cd.setSoLuong(rs.getInt("SoLuong"));
				cd.setChiTieu(rs.getInt("ChiTieu"));
				cd.setSanPham(sp);
				dsCD.add(cd);
			}
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsCD;
	}
	

	public static void main(String[] args) {
		CongDoanDao cd = new CongDoanDao();
		System.out.println(cd.getCongDoan("HoanThienSanPham"));
	}
}
