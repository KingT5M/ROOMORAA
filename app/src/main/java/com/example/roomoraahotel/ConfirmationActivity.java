package com.example.roomoraahotel;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ConfirmationActivity extends AppCompatActivity {
    private TextView bookingSummaryTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);

        // Retrieve booking data from Intent
        String guestName = getIntent().getStringExtra("guestName");
        String idNumber = getIntent().getStringExtra("idNumber"); // Added to retrieve ID number
        String roomType = getIntent().getStringExtra("roomType");
        String checkInDate = getIntent().getStringExtra("checkIn");
        String checkOutDate = getIntent().getStringExtra("checkOut");
        int totalPrice = getIntent().getIntExtra("totalPrice", 0); // Updated to use totalPrice directly

        // Set up views
        bookingSummaryTextView = findViewById(R.id.tv_booking_summary);
        Button backToHomeButton = findViewById(R.id.btn_back_to_home);

        // Display booking summary
        String bookingSummary = "Guest Name: " + guestName + "\n" +
                "ID Number: " + idNumber + "\n" + // Added ID number to the summary
                "Room Type: " + roomType + "\n" +
                "Check-in: " + checkInDate + "\n" +
                "Check-out: " + checkOutDate + "\n" +
                "Total Price: $" + totalPrice;
        bookingSummaryTextView.setText(bookingSummary);

        // Back to Home button
        backToHomeButton.setOnClickListener(v -> {
            // Move to IDUploadActivity (Page 2)
            Intent intent = new Intent(ConfirmationActivity.this, IDUploadActivity.class);
            startActivity(intent);
        });
    }
}
