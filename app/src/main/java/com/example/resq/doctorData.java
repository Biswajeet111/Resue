package com.example.resq;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


import com.google.android.material.bottomsheet.BottomSheetDialog;

public class doctorData extends AppCompatActivity {

    private static final int CALL_PERMISSION_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctorData);

        Button callDoctorButton = findViewById(R.id.doc);
        callDoctorButton.setOnClickListener(v -> showDoctorInfo());
    }

    private void showDoctorInfo() {
        View view = getLayoutInflater().inflate(R.layout.bottom_sheet_doctor_info, null);

        TextView doctorNameTextView = view.findViewById(R.id.doctorName);
        TextView doctorNumberTextView = view.findViewById(R.id.doctorNumber);
        Button callButton = view.findViewById(R.id.callButton);

        String doctorName = "Dr. Rajesh Kumar";
        String doctorPhone = "+919876543210";

        doctorNameTextView.setText(doctorName);
        doctorNumberTextView.setText(doctorPhone);

        BottomSheetDialog dialog = new BottomSheetDialog(this);
        dialog.setContentView(view);
        dialog.show();

        // Handle click on phone number
        doctorNumberTextView.setOnClickListener(v -> callDoctor(doctorPhone));

        // Handle call button click
        callButton.setOnClickListener(v -> callDoctor(doctorPhone));
    }

    private void callDoctor(String phoneNumber) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, CALL_PERMISSION_REQUEST);
        } else {
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:" + phoneNumber));
            startActivity(callIntent);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == CALL_PERMISSION_REQUEST && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Permission granted! You can now call the doctor.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Call permission denied!", Toast.LENGTH_SHORT).show();
        }
    }
}