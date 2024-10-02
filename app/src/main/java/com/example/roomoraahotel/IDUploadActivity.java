package com.example.roomoraahotel;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class IDUploadActivity extends AppCompatActivity {
    // Define UI elements
    Button btnNext;
    EditText guestName, idNumber; // Add EditText references

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_idupload);

        btnNext = findViewById(R.id.btn_next);
        guestName = findViewById(R.id.guestName);
        idNumber = findViewById(R.id.idNumber);

        btnNext.setOnClickListener(v -> {
            // Retrieve data from EditTexts
            String guestNameInput = guestName.getText().toString();
            String idNumberInput = idNumber.getText().toString();

            // Move to RoomSelectionActivity (Page 3)
            Intent intent = new Intent(IDUploadActivity.this, RoomSelectionActivity.class);
            // Pass guest name and ID number to the next activity
            intent.putExtra("guestName", guestNameInput);
            intent.putExtra("idNumber", idNumberInput);
            startActivity(intent);
        });
    }
}
