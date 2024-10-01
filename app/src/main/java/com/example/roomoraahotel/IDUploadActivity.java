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
            // Move to RoomSelectionActivity (Page 3)
            Intent intent = new Intent(IDUploadActivity.this, RoomSelectionActivity.class);
            startActivity(intent);
        });
    }
}
