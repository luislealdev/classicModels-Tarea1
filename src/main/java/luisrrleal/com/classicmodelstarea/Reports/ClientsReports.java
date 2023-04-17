package luisrrleal.com.classicmodelstarea.Reports;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import luisrrleal.com.classicmodelstarea.database.dao.ClientsDao;
import luisrrleal.com.classicmodelstarea.models.Client;

import java.io.FileNotFoundException;
import java.io.IOException;

public class ClientsReports {
    ClientsDao clientsDao = new ClientsDao();

    public void generate_best_client_list_pdf(String dest) throws IOException {
        //Initialize PDF writer
        PdfWriter writer = new PdfWriter(dest);

        //Initialize PDF document
        PdfDocument pdf = new PdfDocument(writer);

        // Initialize document
        Document document = new Document(pdf, PageSize.A4.rotate());
        document.setMargins(20, 20, 20, 20);

        PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA);
        PdfFont bold = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);
        Table table = new Table(UnitValue.createPercentArray(new float[]{3,3,3,3,3}))
                .useAllAvailableWidth();

        process(table, null, bold, true);
        for(Client client: clientsDao.findTwentyBestClients()) {
            process(table, client, font, false);
        }
        Paragraph textDept = new Paragraph("Best customers list");
        textDept.setTextAlignment(TextAlignment.CENTER);
        textDept.setFont(bold);
        textDept.setFontSize(20);
        textDept.setFontColor(ColorConstants.BLACK);
        document.add(textDept);

        document.add(table);

        //Close document
        document.close();
    }

    public void generate_client_orders_report_pdf(String dest, String client_name) throws IOException{
        //Initialize PDF writer
        PdfWriter writer = new PdfWriter(dest);

        //Initialize PDF document
        PdfDocument pdf = new PdfDocument(writer);

        // Initialize document
        Document document = new Document(pdf, PageSize.A4.rotate());
        document.setMargins(20, 20, 20, 20);

        PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA);
        PdfFont bold = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);
        Table table = new Table(UnitValue.createPercentArray(new float[]{3,3,3,3,3}))
                .useAllAvailableWidth();

        process(table, null, bold, true);
        for(Client client: clientsDao.findTwentyBestClients()) {
            process(table, client, font, false);
        }
        Paragraph textDept = new Paragraph(client_name+"'s orders");
        textDept.setTextAlignment(TextAlignment.CENTER);
        textDept.setFont(bold);
        textDept.setFontSize(20);
        textDept.setFontColor(ColorConstants.BLACK);
        document.add(textDept);

        document.add(table);

        //Close document
        document.close();
    }

    public void process(Table table, Client client, PdfFont font, boolean isHeader){
        if(isHeader){
            table.addHeaderCell(new Cell().add(new Paragraph("CUSTOMER NAME").setFont(font)));
            table.addHeaderCell(new Cell().add(new Paragraph("PHONE").setFont(font)));
            table.addHeaderCell(new Cell().add(new Paragraph("ADDRESS LINE 1").setFont(font)));
            table.addHeaderCell(new Cell().add(new Paragraph("ADDRESS LINE 2").setFont(font)));
            table.addHeaderCell(new Cell().add(new Paragraph("NUM. ORDERS").setFont(font)));

        }else{
            table.addCell(new Cell().add(new Paragraph(client.getCustomer_name() + "").setFont(font)));
            table.addCell(new Cell().add(new Paragraph(client.getPhone() + "").setFont(font)));
            table.addCell(new Cell().add(new Paragraph(client.getAddress_line_1() + "").setFont(font)));
            table.addCell(new Cell().add(new Paragraph(client.getAddress_line_2() + "").setFont(font)));
            table.addCell(new Cell().add(new Paragraph(client.getCustomer_number() + "").setFont(font)));
        }
    }
}
