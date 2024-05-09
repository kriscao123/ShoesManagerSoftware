package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.Luong;
import entity.NgayNghi;

public class NgayNghiDao {
	private Connection ketNoi;

	public  NgayNghiDao() {
		// TODO Auto-generated constructor stub
		ketNoi = ConnectDB.getKetNoi();
	}
	
	public boolean create(NgayNghi nn) {
		PreparedStatement pstm = null;
		int n = 0;
		try {
			pstm = ketNoi.prepareStatement("insert into  " + " NgayNghi values(?, ?, ?, ?, ?)");
			pstm.setString(1, nn.getMaNgayNghi());
			pstm.setString(2, nn.getLuong().getMaLuong());
			pstm.setString(3, nn.getNguoiTao());
			pstm.setString(4, nn.getLyDo());
			pstm.setDate(5, nn.getNgayTao());
			n = pstm.executeUpdate();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		finally {
			try {
				pstm.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return n > 0;
	}
	
	public boolean update(NgayNghi nn, String ma) {
		PreparedStatement pstm = null;
		int n = 0;
		try {
			pstm = ketNoi.prepareStatement("update NgayNghi set MaLuong = ?, NguoiTao = ?, LyDo = ?,"
					+ "NgayTao = ? where MaNgayNghi = ?");
			pstm.setString(1, nn.getLuong().getMaLuong());
			pstm.setString(2, nn.getNguoiTao());
			pstm.setString(3, nn.getLyDo());
			pstm.setDate(4, nn.getNgayTao());
			pstm.setString(5, nn.getMaNgayNghi());
			n = pstm.executeUpdate();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		finally {
			try {
				pstm.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return n > 0;
	}
	
	public boolean createPLuong(Luong l) {
		PreparedStatement pstm = null;
		int n = 0;
		try {
			pstm = ketNoi.prepareStatement("insert into  " + " Luong values(?, ?, ?, ?,?,?,?,?,?,?)");
			pstm.setString(0, l.getMaLuong());
			pstm.setString(1, l.getNgayNghi().getMaNgayNghi());
			pstm.setInt(2, l.getSoNgayNghiCoPhep());
			pstm.setInt(3,  l.getSoNgayNghiKhongPhep());
			pstm.setInt(4, l.getSoSanPhamHoanThien());
			pstm.setFloat(5, l.getHeSoLuong());
			pstm.setDate(6, (Date) l.getNgayTao());
			pstm.setFloat(7,  l.getTongTien());
			pstm.setString(8, l.getNhanVien().getMaNhanVien());
			pstm.setString(9, l.getCongDoan());
			
			n = pstm.executeUpdate();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		finally {
			try {
				pstm.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return n > 0;
	}
	
	
	public ArrayList<NgayNghi> getAllNN() {
		ArrayList<NgayNghi> listNN = new ArrayList<NgayNghi>();
		PreparedStatement stmt = null;
		String sql = "select * from NgayNghi";
		try {
			stmt = ketNoi.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				NgayNghi nn = new NgayNghi();
				nn.setMaNgayNghi(rs.getString("MaNgayNghi"));
				nn.setLyDo(rs.getString("LyDo"));
				nn.setNguoiTao(rs.getString("NguoiTao"));
				nn.setNgayTao(rs.getDate("NgayTao"));;
				listNN.add(nn);
				
			}
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listNN;
	}
	public static void main(String[] args) {

	}
}
