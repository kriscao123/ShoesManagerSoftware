package generatepdf;

import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import dao.LuongDao;
import dao.NhanVienDao;
import entity.Luong;
import entity.NhanVien;

public class GenerateAllPdfNV {
	private LuongDao luongDao;
	private List<Luong> dsLuong;
	private NhanVienDao nvDao;
	private List<NhanVien> dsNV;

	public GenerateAllPdfNV(String tenKT) {
		DecimalFormat df = new DecimalFormat("###,###,###.#");
		float tongTien = 0;
		Luong luong = new Luong();
		luongDao = new LuongDao();
		dsLuong = luongDao.getAllLuong();
		nvDao = new NhanVienDao();
		dsNV = nvDao.getAllNV();
		
		Document doc = new Document();
		try {

			BaseFont bf1 = BaseFont.createFont("font/timesbd.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			Font f1 = new Font(bf1, 10);
			Font f2 = new Font(bf1, 13);

			PdfWriter write = PdfWriter.getInstance(doc,
					new FileOutputStream("G:/Desktop/hoadon/" +"DanhSachPhieuLuongNV"+ ".pdf"));

			doc.open();
			Paragraph p1 = new Paragraph("Hữu Nghị - Uy Tín, Thái Độ, Chất Lượng", f2);
			p1.setAlignment(Paragraph.ALIGN_CENTER);
			Paragraph p2 = new Paragraph("PHIẾU XUẤT DANH SÁCH LƯƠNG NHÂN VIÊN", f2);
			p2.setAlignment(Paragraph.ALIGN_CENTER);
			p2.setSpacingAfter(20f);

			doc.add(p1);
			doc.add(p2);
			doc.add(new Paragraph(String.format("Công ty:%7sCông Ty Sản Xuất Giầy Hữu Nghị", ""), f1));
			doc.add(new Paragraph(String.format("Địa chỉ:%7sTầng 02, Cao ốc Newton Residence, Số 38 Trương Quốc Dung, Phường 08, Quận Phú Nhuận, TP.HCM", ""),
					f1));

			doc.add(new Paragraph("\n"));
			LocalTime time = LocalTime.now();
			String s1 = String.format("%1s" + "" + "%1s" + "Nhân viên lập:%3s" + tenKT, "", "", "");
			String s2 = String.format(
					"%1s" + "" + "%1s" + "Ngày in:%3s" + LocalDate.now() + "%3s%02d:%02d:%02d",
					"", "", "", "", time.getHour(), +time.getMinute(), +time.getSecond());
			String s3 = String.format("", "", "", "", "", "");
			doc.add(new Paragraph(s1, f1));
			doc.add(new Paragraph(s2, f1));
			doc.add(new Paragraph("\n"));
			doc.add(new Paragraph(s3, f1));
//			doc.add(new Paragraph(String.format("Địa chỉ:%7s" + kh.getDiaChi(), ""), f1));

			PdfPTable table = new PdfPTable(9);
			/**
			 * for table
			 */
			table.setWidthPercentage(100);
			table.setSpacingBefore(5f);
			table.setSpacingAfter(20f);

			float[] colwidth = { 0.6f, 1.3f, 1.5f, 2f, 0.75f, 1.25f, 1.25f, 2f, 2.5f };
			table.setWidths(colwidth);

			PdfPCell c0 = new PdfPCell(new Paragraph("STT", f1));
			PdfPCell c1 = new PdfPCell(new Paragraph("Mã Luơng", f1));
			PdfPCell c2 = new PdfPCell(new Paragraph("Mã Nhân Viên", f1));
			PdfPCell c3 = new PdfPCell(new Paragraph("Tên Nhân Viên", f1));
			PdfPCell c4 = new PdfPCell(new Paragraph("Hệ số", f1));
			PdfPCell c5 = new PdfPCell(new Paragraph("Số ngày nghỉ có phép", f1));
			PdfPCell c6 = new PdfPCell(new Paragraph("Số ngày nghỉ", f1));
			PdfPCell c7 = new PdfPCell(new Paragraph("Ngày Tạo", f1));
			PdfPCell c8 = new PdfPCell(new Paragraph("Thành tiền (VNĐ)", f1));

			c0.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
			c1.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
			c2.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
			c3.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
			c4.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
			c5.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
			c6.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
			c7.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
			c8.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		
			c0.setFixedHeight(30);
			c0.setBorderWidthRight(0f);
			c1.setBorderWidthRight(0f);
			c2.setBorderWidthRight(0f);
			c3.setBorderWidthRight(0f);
			c4.setBorderWidthRight(0f);
			c5.setBorderWidthRight(0f);
			c6.setBorderWidthRight(0f);
			c7.setBorderWidthRight(0f);
			c8.setBorderWidthRight(0.5f);

			table.addCell(c0);
			table.addCell(c1);
			table.addCell(c2);
			table.addCell(c3);
			table.addCell(c4);
			table.addCell(c5);
			table.addCell(c6);
			table.addCell(c7);
			table.addCell(c8);
			
			int i = 1;
			for (Luong l : dsLuong) {
				if(l.getNhanVien().getMaNhanVien().charAt(0) == 'N') {
					tongTien += l.getTongTien();
					NhanVien nv = l.getNhanVien();
					PdfPCell c9 = new PdfPCell(new Paragraph(i++ + "", f1));
					PdfPCell c10 = new PdfPCell(new Paragraph(l.getMaLuong(), f1));
					PdfPCell c11 = new PdfPCell(new Paragraph(l.getNhanVien().getMaNhanVien(), f1));
					PdfPCell c12 = new PdfPCell();
					for(NhanVien x : dsNV) {
						if(l.getNhanVien().getMaNhanVien().equals(x.getMaNhanVien())) {
							c12 = new PdfPCell(new Paragraph(x.getTenNhanVien(), f1));
						}
					}
					PdfPCell c13 = new PdfPCell(new Paragraph(l.getHeSoLuong() + "", f1));
					PdfPCell c14 = new PdfPCell(new Paragraph(l.getSoNgayNghiCoPhep() + "", f1));
					PdfPCell c15 = new PdfPCell(new Paragraph(l.getSoNgayNghiKhongPhep() + "", f1));
					PdfPCell c16 = new PdfPCell(new Paragraph(l.getNgayTao() + "", f1));
					PdfPCell c17 = new PdfPCell(
							new Paragraph(df.format(l.getTongTien()), f1));

					c8.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
					c9.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
					c10.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
					c11.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
					c12.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
					c13.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
					c14.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
					c15.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
					c16.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
					c17.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
					
					
					c9.setFixedHeight(30f);
					c9.setBorderWidthTop(0f);
					c9.setBorderWidthRight(0f);
					c10.setBorderWidthTop(0f);
					c10.setBorderWidthRight(0f);
					c11.setBorderWidthTop(0f);
					c11.setBorderWidthRight(0f);
					c12.setBorderWidthTop(0f);
					c12.setBorderWidthRight(0f);
					c13.setBorderWidthTop(0f);
					c13.setBorderWidthRight(0f);
					c14.setBorderWidthTop(0f);
					c14.setBorderWidthRight(0f);
					c15.setBorderWidthTop(0f);
					c16.setBorderWidthTop(0f);
					c17.setBorderWidthTop(0f);


					table.addCell(c9);
					table.addCell(c10);
					table.addCell(c11);
					table.addCell(c12);
					table.addCell(c13);
					table.addCell(c14);
					table.addCell(c15);
					table.addCell(c16);
					table.addCell(c17);
				}
			}





			doc.add(new Paragraph("\n"));
			doc.add(table);
			
			doc.add(new Paragraph(String.format("----------------------------------------------------------------------------------------------------------------------------------")));
			
			doc.add(new Paragraph(
					String.format("%-30s %-136s" + df.format(tongTien) + " vnd", "Tổng tiền:", ""),
					f1));
			doc.add(new Paragraph(String.format("----------------------------------------------------------------------------------------------------------------------------------")));
			
			doc.add(new Paragraph("\n\n"));
			doc.add(new Paragraph(String.format("%8s %120s   Người lập Phiếu Lương", "", ""), f1));
			doc.add(new Paragraph(String.format("%5s %125s (Ký, ghi rõ họ tên)", "", ""), f1));
			doc.add(new Paragraph("\n\n\n"));
			doc.add(new Paragraph(String.format("%8s %-30s %110s %-30s", "", "", "", tenKT), f1));
			doc.add(new Paragraph("\n"));
			Paragraph p3 = new Paragraph(
					"Nhân viên vui lòng kiểm tra lại thông tin, nếu có sai sót xin hãy báo ngay lại cho"
					+ "\n chúng tôi để được giải quyết kịp thời", f1);
			p3.setAlignment(Paragraph.ALIGN_CENTER);
			doc.add(p3);

			doc.close();
			write.close();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Không thể tạo hóa đơn");
			e.printStackTrace();
		}
	}
}