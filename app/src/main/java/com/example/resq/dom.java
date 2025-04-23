package com.example.resq;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class dom extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dom);
        // Retrieve the passed data
        String selectedItem = getIntent().getStringExtra("SELECTED_ITEM");
        String answer = getIntent().getStringExtra("ANSWER");

        // Display the selected item
        TextView resultTextView = findViewById(R.id.resultTextView);
        resultTextView.setText("Selected Emergency: " + selectedItem  + answer);
    }
}



