package com.booking_house_be.dto;

import com.booking_house_be.entity.Booking;
import com.lowagie.text.*;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.util.Locale;

public class BillPDF {
    private Booking booking;

    public BillPDF(Booking booking) {
        this.booking = booking;
    }

    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);

        Font font = FontFactory.getFont("resources/fonts/times.tff", BaseFont.IDENTITY_H);
        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase("STT", font));

        table.addCell(cell);

        cell.setPhrase(new Phrase("Họ tên", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Thông tin nhà", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Thời gian thuê", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Thành tiền", font));
        table.addCell(cell);
    }

    private void writeTableData(PdfPTable table) {
        table.addCell(String.valueOf(1));
        table.addCell(booking.getAccount().getLastname() + " " + booking.getAccount().getFirstname());
        table.addCell(booking.getHouse().getName());
        table.addCell(formatTime(booking.getStartTime()));
        Locale locale = new Locale("vi", "VN");
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
    }

    public void export(ByteArrayOutputStream outputStream) throws DocumentException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, outputStream);

        document.open();
        Font font = FontFactory.getFont("resources/fonts/times.tff", BaseFont.IDENTITY_H);
        font.setSize(18);
        font.setColor(Color.BLUE);

        Paragraph p = new Paragraph("Hóa đơn thuê nhà", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);
        p.setSpacingAfter(2f);

        document.add(p);

        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100f);
        table.setWidths(new float[]{1f, 3.5f, 3.0f, 3.0f, 2f});
        table.setSpacingBefore(10);

        writeTableHeader(table);
        writeTableData(table);

        document.add(table);

        document.close();
    }

    public static String formatTime(LocalDateTime localDateTime) {
        return localDateTime.getDayOfMonth() + "/" + localDateTime.getMonthValue() + "/"
                + localDateTime.getYear() + " " + localDateTime.getHour() + ":"
                + (localDateTime.getMinute() < 10 ? "0" : "") + localDateTime.getMinute();
    }
}
