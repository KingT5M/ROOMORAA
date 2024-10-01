package com.example.roomoraahotel;

import android.os.Bundle;
import android.os.Environment;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.tom_roush.pdfbox.pdmodel.PDDocument;
import com.tom_roush.pdfbox.pdmodel.PDPage;
import com.tom_roush.pdfbox.pdmodel.PDPageContentStream;
import com.tom_roush.pdfbox.pdmodel.font.PDType1Font;
import java.io.File;
import java.io.IOException;

public class ConfirmationActivity extends AppCompatActivity {
    private TextView bookingSummaryTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);

        // Retrieve booking data from Intent
        String guestName = getIntent().getStringExtra("guestName");
        String roomType = getIntent().getStringExtra("roomType");
        String checkInDate = getIntent().getStringExtra("checkInDate");
        String checkOutDate = getIntent().getStringExtra("checkOutDate");
        int roomPrice = getIntent().getIntExtra("roomPrice", 0);
        boolean kidsBedIncluded = getIntent().getBooleanExtra("kidsBedIncluded", false);
        boolean roomServiceIncluded = getIntent().getBooleanExtra("roomServiceIncluded", false);
        int extraCosts = getIntent().getIntExtra("extraCosts", 0);
        int totalPrice = roomPrice + extraCosts;

        // Set up views
        bookingSummaryTextView = findViewById(R.id.tv_booking_summary);
        Button downloadPDFButton = findViewById(R.id.btn_download_pdf);
        Button backToHomeButton = findViewById(R.id.btn_back_to_home);

        // Display booking summary
        String bookingSummary = "Guest Name: " + guestName + "\n" +
                "Room Type: " + roomType + "\n" +
                "Check-in: " + checkInDate + "\n" +
                "Check-out: " + checkOutDate + "\n" +
                (kidsBedIncluded ? "Kid's Bed: Included\n" : "") +
                (roomServiceIncluded ? "Room Service: Included\n" : "") +
                "Room Price: $" + roomPrice + "\n" +
                "Extra Costs: $" + extraCosts + "\n" +
                "Total Price: $" + totalPrice;
        bookingSummaryTextView.setText(bookingSummary);

        // PDF Download button functionality
        downloadPDFButton.setOnClickListener(v -> generatePDF(bookingSummary));

        // Back to Home button
        backToHomeButton.setOnClickListener(v -> {
            // Handle going back to the home screen (MainActivity)
            finish();
        });
    }

    private void generatePDF(String content) {
        // Create a new PDF document
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);

        try {
            // Start writing to PDF
            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            contentStream.setFont(PDType1Font.HELVETICA, 12);
            contentStream.beginText();
            contentStream.setLeading(14.5f);
            contentStream.newLineAtOffset(25, 725);

            // Write each line of the content
            for (String line : content.split("\n")) {
                contentStream.showText(line);
                contentStream.newLine();
            }

            contentStream.endText();
            contentStream.close();

            // Save the PDF to external storage
            File file = new File(Environment.getExternalStorageDirectory(), "BookingSummary.pdf");
            document.save(file);
            document.close();

            // Notify the user
            bookingSummaryTextView.setText("PDF downloaded to: " + file.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
            bookingSummaryTextView.setText("Error: Could not generate PDF.");
        }
    }
}
