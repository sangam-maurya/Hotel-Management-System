package com.example.Service;

import com.example.entity.Booking;
import com.example.entity.Property;
import com.example.entity.Room;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;

@Service
public class PdfService {

    public void generateBookPdf(String filePath, Property property, Booking booking){
        try{
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();
            PdfPTable table = new PdfPTable(2);
           table.addCell("name");
           table.addCell(property.getName());
           table.addCell("no of guest");
            document.add(table);
            document.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void createPdfWithImage(byte[] imageBytes) {
        try {
            // 1. Define the output PDF file path (using your specified path)
            String folderPath = "S:\\Hms.booking\\";  // Folder path
            String fileName = "text.pdf";  // PDF file name
            File file = new File(folderPath + fileName);  // Complete path

            // 2. Create a Document object
            Document document = new Document();

            // 3. Create PdfWriter instance to write to the file
            PdfWriter.getInstance(document, new FileOutputStream(file));

            // 4. Open the document to start adding content
            document.open();

            // 5. Add the image to the document
            Image image = Image.getInstance(imageBytes);  // Convert byte array to image
            image.scaleToFit(500, 500);  // Resize image to fit within the PDF page
            document.add(image);  // Add image to the document

            // 6. Close the document
            document.close();

            System.out.println("PDF generated at: " + file.getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


