package com.example.resq;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class com extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_com);
        Button btn;
        EditText etd1,etd2;
        btn = findViewById(R.id.btn);
        etd1 = findViewById(R.id.etd1);
        etd2 = findViewById(R.id.etd2);
        String phone = etd1.getText().toString();
        String otp = etd2.getText().toString();
        if (phone.isEmpty() || otp.isEmpty()) {
            return;
        }
        btn.setOnClickListener(v -> {
            Intent next = new Intent(com.this,MapsActivity.class);
            startActivity(next);
        });
        }

    }
