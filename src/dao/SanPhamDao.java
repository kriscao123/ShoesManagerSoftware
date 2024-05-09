package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.SanPham;

public class SanPhamDao {
	private Connection ketNoi;
	private ArrayList<SanPham> dsTK;
	private SanPham tk;
	public SanPhamDao() {
		// TODO Auto-generated constructor stub
		ketNoi = ConnectDB.getKetNoi();
	}

	
	/**
	 * Hàm lấy tất cả thông tin có trong bảng sản phẩm
	 * @return Trả về list danh sách sản phẩm
	 */
	public List<SanPham> getAllSP() {
		dsTK = new ArrayList<SanPham>();
		Connection con = ConnectDB.getKetNoi();
		PreparedStatement stmt = null;
		String sql = "select * from SanPham";
		try {
			stmt = ketNoi.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {				
				String maSanPham = rs.getString(1);
				String tenSanPham = rs.getString(2);
				tk = new SanPham(maSanPham, tenSanPham);
				dsTK.add(tk);
			}
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsTK;
	}	

	public static void main(String[] args) {
		SanPhamDao khachHangDAO = new SanPhamDao();
		System.out.println(khachHangDAO.getAllSP());
	}
}
