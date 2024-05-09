package entity;

public class CongDoan {
	private String tenCongDoan;
	private float donGia;
	private float donGiaTangCa;
	private int soLuong;
	private int chiTieu;
	private SanPham sanPham;
	
	public CongDoan() {
		// TODO Auto-generated constructor stub
	}

	
	public CongDoan(String tenCongDoan, float donGia, float donGiaTangCa, int soLuong, int chiTieu, SanPham sanPham) {
		super();
		this.tenCongDoan = tenCongDoan;
		this.donGia = donGia;
		this.donGiaTangCa = donGiaTangCa;
		this.soLuong = soLuong;
		this.chiTieu = chiTieu;
		this.sanPham = sanPham;
	}

	
	public String getTenCongDoan() {
		return tenCongDoan;
	}

	public void setTenCongDoan(String tenCongDoan) {
		this.tenCongDoan = tenCongDoan;
	}

	public float getDonGia() {
		return donGia;
	}

	public void setDonGia(float donGia) {
		this.donGia = donGia;
	}

	public float getDonGiaTangCa() {
		return donGiaTangCa;
	}

	public void setDonGiaTangCa(float donGiaTangCa) {
		this.donGiaTangCa = donGiaTangCa;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	public int getChiTieu() {
		return chiTieu;
	}

	public void setChiTieu(int chiTieu) {
		this.chiTieu = chiTieu;
	}

	public SanPham getSanPham() {
		return sanPham;
	}

	public void setSanPham(SanPham sanPham) {
		this.sanPham = sanPham;
	}


	@Override
	public String toString() {
		return "CongDoan [tenCongDoan=" + tenCongDoan + ", donGia=" + donGia + ", donGiaTangCa=" + donGiaTangCa
				+ ", soLuong=" + soLuong + ", chiTieu=" + chiTieu + ", sanPham=" + sanPham + "]";
	}
	
	
}
