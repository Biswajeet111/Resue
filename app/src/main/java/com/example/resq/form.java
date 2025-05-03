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

public class form extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_form);
        EditText doc1 = findViewById(R.id.doc1);
        EditText doc2 = findViewById(R.id.doc2);
        EditText emer1 = findViewById(R.id.emer1);
        EditText emer2 = findViewById(R.id.emer2);
        EditText emer3 = findViewById(R.id.emer3);
        EditText emer4 = findViewById(R.id.emer4);
        EditText emer5 = findViewById(R.id.emer5);
        String doc1Text = doc1.getText().toString();
        String doc2Text = doc2.getText().toString();
        String emer1Text = emer1.getText().toString();
        String emer2Text = emer2.getText().toString();
        String emer3Text = emer3.getText().toString();
        String emer4Text = emer4.getText().toString();
        String emer5Text = emer5.getText().toString();
        Button submit = findViewById(R.id.btn2);
        Bundle bundle = new Bundle();
        bundle.putString("key", doc1Text);
        bundle.putInt("key", 1234567890);
        bundle.putInt("key1", 934567899);


        submit.setOnClickListener(v -> {
            Intent intent = new Intent(form.this,bom.class);
            intent.putExtras(bundle);
            startActivity(intent);
        });
    }
}