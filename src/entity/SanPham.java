package entity;

public class SanPham {
	private String maSanPham;
	private String tenSanPham;
	
//	private CongDoan congDoan;
	
	public SanPham() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param maSanPham
	 * @param tenSanPham
	 */
	public SanPham(String maSanPham, String tenSanPham) {
		super();
		this.maSanPham = maSanPham;
		this.tenSanPham = tenSanPham;
	}
	
	

	public SanPham(String maSanPham) {
		super();
		this.maSanPham = maSanPham;
	}

	public String getMaSanPham() {
		return maSanPham;
	}

	public void setMaSanPham(String maSanPham) {
		this.maSanPham = maSanPham;
	}

	public String getTenSanPham() {
		return tenSanPham;
	}

	public void setTenSanPham(String tenSanPham) {
		this.tenSanPham = tenSanPham;
	}

	@Override
	public String toString() {
		return "SanPham [maSanPham=" + maSanPham + ", tenSanPham=" + tenSanPham + "]";
	}

//	public CongDoan getCongDoan() {
//		return congDoan;
//	}
//
//	public void setCongDoan(CongDoan congDoan) {
//		this.congDoan = congDoan;
//	}
//
//	@Override
//	public String toString() {
//		return "SanPham [maSanPham=" + maSanPham + ", tenSanPham=" + tenSanPham + ", congDoan=" + congDoan + "]";
//	}
	
	
}
