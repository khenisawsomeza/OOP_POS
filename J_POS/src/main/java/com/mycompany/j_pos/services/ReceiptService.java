/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.j_pos.services;

import com.mycompany.j_pos.models.sale.Sale;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author Khenyshi
 */
public class ReceiptService {
    
    // Generate PDF receipt
    public void printReceiptToPDF(Sale sale, double tax, double discount) {
        try {
            double subtotal = sale.getTotal();
            double finalTotal = subtotal + tax - discount;
            
            // Create receipts directory if it doesn't exist
            File directory = new File("receipts");
            if (!directory.exists()) {
                directory.mkdirs();
            }
            
            // Generate filename with timestamp
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String filename = "receipts/receipt_" + timestamp + ".pdf";
            
            // Create PDF document
            Document document = new Document(PageSize.A4);
            PdfWriter.getInstance(document, new FileOutputStream(filename));
            
            document.open();
            
            // Add title
            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
            Paragraph title = new Paragraph("RECEIPT", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            title.setSpacingAfter(20);
            document.add(title);
            
            // Add date/time
            Font normalFont = FontFactory.getFont(FontFactory.HELVETICA, 10);
            Paragraph datetime = new Paragraph("Date: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()), normalFont);
            datetime.setSpacingAfter(15);
            document.add(datetime);
            
            // Add separator
            document.add(new Paragraph("_________________________________________________________________"));
            document.add(Chunk.NEWLINE);
            
            // Add sale details
            Font itemFont = FontFactory.getFont(FontFactory.COURIER, 10);
            Paragraph saleDetails = new Paragraph(sale.printReceipt(), itemFont);
            document.add(saleDetails);
            
            // Add separator
            document.add(new Paragraph("_________________________________________________________________"));
            document.add(Chunk.NEWLINE);
            
            // Add totals
            Font totalFont = FontFactory.getFont(FontFactory.HELVETICA, 11);
            document.add(new Paragraph(String.format("TAX: ₱%.2f", tax), totalFont));
            document.add(new Paragraph(String.format("DISCOUNT: ₱%.2f", discount), totalFont));
            document.add(new Paragraph("_________________________________________________________________"));
            
            Font finalTotalFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14);
            Paragraph total = new Paragraph(String.format("TOTAL: ₱%.2f", finalTotal), finalTotalFont);
            total.setSpacingBefore(10);
            document.add(total);
            
            // Add footer
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            Paragraph footer = new Paragraph("Thank you for your purchase!", normalFont);
            footer.setAlignment(Element.ALIGN_CENTER);
            document.add(footer);
            
            document.close();
            
            JOptionPane.showMessageDialog(
                null, 
                "Receipt saved successfully!\nLocation: " + new File(filename).getAbsolutePath(), 
                "Receipt Saved", 
                JOptionPane.INFORMATION_MESSAGE
            );
            
            // Optionally open the PDF
            openPDF(filename);
            
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(
                null, 
                "Error generating PDF receipt: " + e.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE
            );
        }
    }
    
    // Open PDF after creation
    private void openPDF(String filename) {
        try {
            File file = new File(filename);
            if (file.exists()) {
                if (java.awt.Desktop.isDesktopSupported()) {
                    java.awt.Desktop.getDesktop().open(file);
                }
            }
        } catch (Exception e) {
            System.out.println("Could not open PDF automatically: " + e.getMessage());
        }
    }
    
    // Keep the original method for dialog display (optional)
    public void printReceipt(Sale sale, double tax, double discount) {
        double subtotal = sale.getTotal();
        double finalTotal = subtotal + tax - discount;
        String receiptText = "";
        receiptText += sale.printReceipt();
        
        receiptText += String.format("TAX: ₱%.2f \n"
                                   + "DISCOUNT: ₱%.2f \n"
                                   + "---------------------------------\n"
                                   + "TOTAL: ₱%.2f", tax, discount, finalTotal);
        System.out.println(receiptText);
        JOptionPane.showMessageDialog(
            null, 
            receiptText, 
            "Receipt", 
            JOptionPane.INFORMATION_MESSAGE
        );
    }
}