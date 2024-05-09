package entity;

import java.sql.Date;

public class NgayNghi {
	private String maNgayNghi;
	private String lyDo;
	private Date ngayTao;
	private String nguoiTao;
	private Luong luong;
	public String getMaNgayNghi() {
		return maNgayNghi;
	}
	public void setMaNgayNghi(String maNgayNghi) {
		this.maNgayNghi = maNgayNghi;
	}
	public String getLyDo() {
		return lyDo;
	}
	public void setLyDo(String lyDo) {
		this.lyDo = lyDo;
	}
	public Date getNgayTao() {
		return ngayTao;
	}
	public void setNgayTao(Date ngayTao) {
		this.ngayTao = ngayTao;
	}
	public String getNguoiTao() {
		return nguoiTao;
	}
	public void setNguoiTao(String nguoiTao) {
		this.nguoiTao = nguoiTao;
	}
	public Luong getLuong() {
		return luong;
	}
	public void setLuong(Luong luong) {
		this.luong = luong;
	}
	public NgayNghi() {
		super();
	}
	public NgayNghi(String maNgayNghi, String lyDo, Date ngayTao, String nguoiTao, Luong luong) {
		super();
		this.maNgayNghi = maNgayNghi;
		this.lyDo = lyDo;
		this.ngayTao = ngayTao;
		this.nguoiTao = nguoiTao;
		this.luong = luong;
	}
	public NgayNghi(String maNgayNghi) {
		super();
		this.maNgayNghi = maNgayNghi;
	}
	@Override
	public String toString() {
		return "NgayNghi [maNgayNghi=" + maNgayNghi + ", lyDo=" + lyDo + ", ngayTao=" + ngayTao + ", nguoiTao="
				+ nguoiTao + ", luong=" + luong + "]";
	}
}
