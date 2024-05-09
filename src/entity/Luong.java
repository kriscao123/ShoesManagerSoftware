package entity;

import java.util.Date;

public class Luong {
	private String maLuong;
	private int soNgayNghiCoPhep;
	private int soNgayNghiKhongPhep;
	private int soSanPhamHoanThien;
	private String congDoan;
	private float heSoLuong;
	private Date ngayTao;
	private float tongTien;
	private int chiTieu;
	
	private NgayNghi ngayNghi;
	private NhanVien nhanVien;
	public Luong() {
	}
	public Luong(String maLuong, int soNgayNghiCoPhep, int soNgayNghiKhongPhep, int soSanPhamHoanThien, String congDoan,
			float heSoLuong, Date ngayTao, float tongTien, int chiTieu, NgayNghi ngayNghi, NhanVien nhanVien) {
		super();
		this.maLuong = maLuong;
		this.soNgayNghiCoPhep = soNgayNghiCoPhep;
		this.soNgayNghiKhongPhep = soNgayNghiKhongPhep;
		this.soSanPhamHoanThien = soSanPhamHoanThien;
		this.congDoan = congDoan;
		this.heSoLuong = heSoLuong;
		this.ngayTao = ngayTao;
		this.tongTien = tongTien;
		this.chiTieu = chiTieu;
		this.ngayNghi = ngayNghi;
		this.nhanVien = nhanVien;
	}
	public String getMaLuong() {
		return maLuong;
	}
	public void setMaLuong(String maLuong) {
		this.maLuong = maLuong;
	}
	public int getSoNgayNghiCoPhep() {
		return soNgayNghiCoPhep;
	}
	public void setSoNgayNghiCoPhep(int soNgayNghiCoPhep) {
		this.soNgayNghiCoPhep = soNgayNghiCoPhep;
	}
	public int getSoNgayNghiKhongPhep() {
		return soNgayNghiKhongPhep;
	}
	public void setSoNgayNghiKhongPhep(int soNgayNghiKhongPhep) {
		this.soNgayNghiKhongPhep = soNgayNghiKhongPhep;
	}
	public int getSoSanPhamHoanThien() {
		return soSanPhamHoanThien;
	}
	public void setSoSanPhamHoanThien(int soSanPhamHoanThien) {
		this.soSanPhamHoanThien = soSanPhamHoanThien;
	}
	public String getCongDoan() {
		return congDoan;
	}
	public void setCongDoan(String congDoan) {
		this.congDoan = congDoan;
	}
	public float getHeSoLuong() {
		return heSoLuong;
	}
	public void setHeSoLuong(float heSoLuong) {
		this.heSoLuong = heSoLuong;
	}
	public Date getNgayTao() {
		return ngayTao;
	}
	public void setNgayTao(Date ngayTao) {
		this.ngayTao = ngayTao;
	}
	public float getTongTien() {
		return tongTien;
	}
	public void setTongTien(float tongTien) {
		this.tongTien = tongTien;
	}
	public int getChiTieu() {
		return chiTieu;
	}
	public void setChiTieu(int chiTieu) {
		this.chiTieu = chiTieu;
	}
	public NgayNghi getNgayNghi() {
		return ngayNghi;
	}
	public void setNgayNghi(NgayNghi ngayNghi) {
		this.ngayNghi = ngayNghi;
	}
	public NhanVien getNhanVien() {
		return nhanVien;
	}
	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}
	@Override
	public String toString() {
		return "Luong [maLuong=" + maLuong + ", soNgayNghiCoPhep=" + soNgayNghiCoPhep + ", soNgayNghiKhongPhep="
				+ soNgayNghiKhongPhep + ", soSanPhamHoanThien=" + soSanPhamHoanThien + ", congDoan=" + congDoan
				+ ", heSoLuong=" + heSoLuong + ", ngayTao=" + ngayTao + ", tongTien=" + tongTien + ", chiTieu="
				+ chiTieu + ", ngayNghi=" + ngayNghi + ", nhanVien=" + nhanVien + "]";
	}
	
}
