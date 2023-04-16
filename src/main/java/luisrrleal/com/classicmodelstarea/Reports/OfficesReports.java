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
import luisrrleal.com.classicmodelstarea.database.dao.OfficesDao;
import luisrrleal.com.classicmodelstarea.models.Client;
import luisrrleal.com.classicmodelstarea.models.Office;

import java.io.IOException;

public class OfficesReports {
    OfficesDao officesDao = new OfficesDao();


    public void create_offices_list_Pdf(String dest) throws IOException{
        //Initialize PDF writer
        PdfWriter writer = new PdfWriter(dest);

        //Initialize PDF document
        PdfDocument pdf = new PdfDocument(writer);

        // Initialize document
        Document document = new Document(pdf, PageSize.A4.rotate());
        document.setMargins(20, 20, 20, 20);

        PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA);
        PdfFont bold = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);
        Table table = new Table(UnitValue.createPercentArray(new float[]{1,2,1,2,2,2,1,1,1}))
                .useAllAvailableWidth();

        process(table, null, bold, true);
        for(Office office: officesDao.findAll())
        {
            process(table, office, font, false);
        }

        Paragraph textDept = new Paragraph("Offices List");
        textDept.setTextAlignment(TextAlignment.CENTER);
        textDept.setFont(bold);
        textDept.setFontSize(20);
        textDept.setFontColor(ColorConstants.BLACK);
        document.add(textDept);

        document.add(table);

        //Close document
        document.close();
    }

    public void create_offices_employees_list_Pdf(String dest) throws IOException{
        //Initialize PDF writer
        PdfWriter writer = new PdfWriter(dest);

        //Initialize PDF document
        PdfDocument pdf = new PdfDocument(writer);

        // Initialize document
        Document document = new Document(pdf, PageSize.A4.rotate());
        document.setMargins(20, 20, 20, 20);

        PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA);
        PdfFont bold = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);
        Table table = new Table(UnitValue.createPercentArray(new float[]{2, 3, 3, 3, 1, 3}))
                .useAllAvailableWidth();

        process(table, null, bold, true);
        for(Office office: officesDao.findAll())
        {
            process(table, office, font, false);
        }

        Paragraph textDept = new Paragraph("Offices-Employees List");
        textDept.setTextAlignment(TextAlignment.CENTER);
        textDept.setFont(bold);
        textDept.setFontSize(20);
        textDept.setFontColor(ColorConstants.BLACK);
        document.add(textDept);

        document.add(table);

        //Close document
        document.close();
    }

    public void process(Table table, Office office, PdfFont font, boolean isHeader){
        if(isHeader){
            table.addHeaderCell(new Cell().add(new Paragraph("OFFICE CODE").setFont(font)));
            table.addHeaderCell(new Cell().add(new Paragraph("CITY").setFont(font)));
            table.addHeaderCell(new Cell().add(new Paragraph("PHONE").setFont(font)));
            table.addHeaderCell(new Cell().add(new Paragraph("ADDRESS LINE 1").setFont(font)));
            table.addHeaderCell(new Cell().add(new Paragraph("ADDRESS LINE 2").setFont(font)));
            table.addHeaderCell(new Cell().add(new Paragraph("STATE").setFont(font)));
            table.addHeaderCell(new Cell().add(new Paragraph("COUNTRY").setFont(font)));
            table.addHeaderCell(new Cell().add(new Paragraph("POSTAL CODE").setFont(font)));
            table.addHeaderCell(new Cell().add(new Paragraph("TERRITORY").setFont(font)));
        }else{
            table.addCell(new Cell().add(new Paragraph(office.getOffice_code() + "").setFont(font)));
            table.addCell(new Cell().add(new Paragraph(office.getCity() + "").setFont(font)));
            table.addCell(new Cell().add(new Paragraph(office.getPhone() + "").setFont(font)));
            table.addCell(new Cell().add(new Paragraph(office.getAddress_line_1() + "").setFont(font)));
            table.addCell(new Cell().add(new Paragraph(office.getAddress_line_2() + "").setFont(font)));
            table.addCell(new Cell().add(new Paragraph(office.getState() + "").setFont(font)));
            table.addCell(new Cell().add(new Paragraph(office.getCountry() + "").setFont(font)));
            table.addCell(new Cell().add(new Paragraph(office.getPostal_code() + "").setFont(font)));
            table.addCell(new Cell().add(new Paragraph(office.getTerritory() + "").setFont(font)));
        }
    }
}
