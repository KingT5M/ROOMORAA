package com.example.roomoraahotel;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;
import java.util.HashMap;

public class RoomSelectionActivity extends AppCompatActivity {
    private Spinner roomTypeSpinner;
    private EditText checkInDate, checkOutDate;
    private CheckBox kidsBed, roomService;
    HashMap<String, Integer> roomPrices = new HashMap<>();
    int roomPrice, totalPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_selection);

        // Retrieve guest name and ID number from the Intent
        String guestName = getIntent().getStringExtra("guestName");
        String idNumber = getIntent().getStringExtra("idNumber");

        // Initialize UI elements
        roomTypeSpinner = findViewById(R.id.spinner_room_type);
        checkInDate = findViewById(R.id.checkin_date);
        checkOutDate = findViewById(R.id.checkout_date);
        kidsBed = findViewById(R.id.checkbox_kids_bed);
        roomService = findViewById(R.id.checkbox_room_service);
        Button confirmBooking = findViewById(R.id.btn_confirm_booking);

        // Populate room types in Spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.room_types, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        roomTypeSpinner.setAdapter(adapter);

        // Room types and prices
        roomPrices.put("Single Bed", 100);
        roomPrices.put("Double Room", 200);
        roomPrices.put("Penthouse", 500);

        // Set up DatePicker for check-in and check-out dates
        checkInDate.setOnClickListener(v -> showDatePickerDialog(checkInDate));
        checkOutDate.setOnClickListener(v -> showDatePickerDialog(checkOutDate));

        // Add focus change listeners to the EditTexts
        checkInDate.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) showDatePickerDialog(checkInDate);
        });

        checkOutDate.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) showDatePickerDialog(checkOutDate);
        });

        // Confirm booking button action
        confirmBooking.setOnClickListener(v -> {
            // Calculate total price
            String selectedRoom = roomTypeSpinner.getSelectedItem().toString();
            Integer price = roomPrices.get(selectedRoom);
            if (price != null) {
                roomPrice = price;
                totalPrice = roomPrice;
                if (kidsBed.isChecked()) totalPrice += 50;
                if (roomService.isChecked()) totalPrice += 100;

                // Move to ConfirmationActivity (Page 4)
                Intent intent = new Intent(RoomSelectionActivity.this, ConfirmationActivity.class);
                // Pass all necessary data to ConfirmationActivity
                intent.putExtra("guestName", guestName);
                intent.putExtra("idNumber", idNumber);
                intent.putExtra("roomType", selectedRoom);
                intent.putExtra("checkIn", checkInDate.getText().toString());
                intent.putExtra("checkOut", checkOutDate.getText().toString());
                intent.putExtra("totalPrice", totalPrice);
                startActivity(intent);
            } else {
                // Handle error case when no room is selected
                Toast.makeText(RoomSelectionActivity.this, "Please select a room type", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Method to show DatePickerDialog
    private void showDatePickerDialog(final EditText dateField) {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                RoomSelectionActivity.this,
                (view, year1, month1, dayOfMonth) -> {
                    // Set the date to the EditText
                    String date = dayOfMonth + "/" + (month1 + 1) + "/" + year1;
                    dateField.setText(date);
                }, year, month, day);
        datePickerDialog.show();
    }
}
