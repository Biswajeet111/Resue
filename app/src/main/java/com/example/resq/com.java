package com.example.resq;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

//import com.google.firebase.FirebaseException;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.PhoneAuthCredential;
//import com.google.firebase.auth.PhoneAuthOptions;
//import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class com extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_com);
//        DatabaseReference databaseReference;
        Button btn;
        btn = findViewById(R.id.btn);
        EditText name;
        name = findViewById(R.id.name);
        String userInput = name.getText().toString();


//         databaseReference = FirebaseDatabase.getInstance().getReference("UserInputs");
//        String key = databaseReference.push().getKey(); // Unique ID
//        databaseReference.child(key).setValue(userInput);

        btn.setOnClickListener(v -> {
            Intent next = new Intent(com.this,bom.class);
            startActivity(next);
        });
        }

    }
//PhoneAuthOptions options =
//        PhoneAuthOptions.newBuilder(FirebaseAuth.getInstance())
//                .setPhoneNumber("+91XXXXXXXXXX") // User's phone number
//                .setTimeout(60L, TimeUnit.SECONDS) // OTP expiration time
//                .setActivity(this) // Current activity
//                .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
//                    @Override
//                    public void onVerificationCompleted(PhoneAuthCredential credential) {
//                        Log.d("OTP", "Verification completed");
//                    }
//
//                    @Override
//                    public void onVerificationFailed(FirebaseException e) {
//                        Log.e("OTP", "Verification failed: " + e.getMessage());
//                    }
//
//                    @Override
//                    public void onCodeSent(String verificationId, PhoneAuthProvider.ForceResendingToken token) {
//                        Log.d("OTP", "OTP Sent: " + verificationId);
//                        // Store verificationId to verify later
//                    }
//                })
//                .build();
//        PhoneAuthProvider.verifyPhoneNumber(options);
//
//        String verificationId = "",enteredOtp = "";
//PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, enteredOtp);
//        FirebaseAuth.getInstance().signInWithCredential(credential)
//                .addOnCompleteListener(task -> {
//        if (task.isSuccessful()) {
//        Log.d("OTP", "User verified successfully!");
//                    } else {
//                            Log.e("OTP", "Verification failed.");
//                    }
//                            });