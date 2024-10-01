package com.example.roomoraahotel;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

public class WelcomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        // Transition to next activity after 4 seconds
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(WelcomeActivity.this, IDUploadActivity.class);
            startActivity(intent);
            finish();
        }, 4000); // 4 seconds
    }
}
