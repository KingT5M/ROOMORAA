package com.example.roomoraahotel;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button bookButton = findViewById(R.id.bookingButton);
        bookButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, BookingConfirmationActivity.class);
            startActivity(intent);
        });

    }
}
