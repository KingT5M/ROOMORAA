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

        // Back to Home button
        backToHomeButton.setOnClickListener(v -> {
            // Move to IDUploadActivity (Page 2)
            Intent intent = new Intent(ConfirmationActivity.this, IDUploadActivity.class);
            startActivity(intent);
        });
    }
}
