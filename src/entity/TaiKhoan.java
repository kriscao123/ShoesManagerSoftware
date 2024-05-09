package entity;

import java.util.Date;

public class TaiKhoan {
	private String tenTaiKhoan;
	private String matKhau;
	private Date ngayTao;
	private Date ngayCapNhat;
	
	private NhanVien nhanVien;
	
	public TaiKhoan() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param tenTaiKhoan
	 * @param matKhau
	 * @param ngayTao
	 * @param ngayCapNhat
	 */
	public TaiKhoan(String tenTaiKhoan, String matKhau, Date ngayTao, Date ngayCapNhat) {
		super();
		this.tenTaiKhoan = tenTaiKhoan;
		this.matKhau = matKhau;
		this.ngayTao = ngayTao;
		this.ngayCapNhat = ngayCapNhat;
	}

	/**
	 * @param tenTaiKhoan
	 */
	public TaiKhoan(String tenTaiKhoan) {
		super();
		this.tenTaiKhoan = tenTaiKhoan;
	}

	public String getTenTaiKhoan() {
		return tenTaiKhoan;
	}

	public void setTenTaiKhoan(String tenTaiKhoan) {
		this.tenTaiKhoan = tenTaiKhoan;
	}

	public String getMatKhau() {
		return matKhau;
	}

	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}

	public Date getNgayTao() {
		return ngayTao;
	}

	public void setNgayTao(Date ngayTao) {
		this.ngayTao = ngayTao;
	}

	public Date getNgayCapNhat() {
		return ngayCapNhat;
	}

	public void setNgayCapNhat(Date ngayCapNhat) {
		this.ngayCapNhat = ngayCapNhat;
	}

	public NhanVien getNhanVien() {
		return nhanVien;
	}

	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}
	
	public TaiKhoan(String tenTaiKhoan, NhanVien nhanVien, String matKhau, Date ngayTao, Date ngayCapNhat) {
		super();
		this.nhanVien = nhanVien;
		this.tenTaiKhoan = tenTaiKhoan;
		this.matKhau = matKhau;
		this.ngayTao = ngayTao;
		this.ngayCapNhat = ngayCapNhat;
		
	}


	@Override
	public String toString() {
		return "TaiKhoan [tenTaiKhoan=" + tenTaiKhoan + ", matKhau=" + matKhau + ", ngayTao=" + ngayTao
				+ ", ngayCapNhat=" + ngayCapNhat + ", nhanVien=" + nhanVien + "]";
	}
}
