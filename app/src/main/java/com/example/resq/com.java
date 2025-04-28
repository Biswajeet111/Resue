package com.example.resq;



import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.*;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.*;
import java.util.concurrent.TimeUnit;

public class com extends AppCompatActivity {

    private EditText name, phone, emergency, otp;
    private Button verify, submit , btn;
    private FirebaseAuth firebaseAuth;
    private String verificationId;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_com);

        // Initialize UI Components
        name = findViewById(R.id.name);
        phone = findViewById(R.id.phone);
        emergency = findViewById(R.id.emer1);
        otp = findViewById(R.id.otp);
        verify = findViewById(R.id.verify);
        submit = findViewById(R.id.btn);

        // Initialize Firebase
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("UserData");

        // Handle OTP Verification
        verify.setOnClickListener(v -> sendOtp());

        // Handle Submit
//        submit.setOnClickListener(v -> verifyOtp());
        submit.setOnClickListener(v -> {
            Intent intent = new Intent(this, bom.class);
            startActivity(intent);
            finish();
        });
    }

    private void sendOtp() {
        String phoneNumber = "+91" + phone.getText().toString().trim(); // Correct usage of EditText

        if (phoneNumber.isEmpty()) {
            Toast.makeText(this, "Enter phone number!", Toast.LENGTH_SHORT).show();
            return;
        }

//        PhoneAuthOptions options =
//                PhoneAuthOptions.newBuilder(firebaseAuth)
//                        .setPhoneNumber(phoneNumber)  // Ensure it includes the country code
//                        .setTimeout(60L, TimeUnit.SECONDS)
//                        .setActivity(this)
//                        .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
//                            @Override
//                            public void onCodeSent(@NonNull String verificationId, @NonNull PhoneAuthProvider.ForceResendingToken token) {
//                                com.this.verificationId = verificationId;
//                                Toast.makeText(com.this, "OTP sent successfully!", Toast.LENGTH_SHORT).show();
//                            }
//
//                            @Override
//                            public void onVerificationCompleted(@NonNull PhoneAuthCredential credential) {
//                                Toast.makeText(com.this, "Auto OTP Verified!", Toast.LENGTH_SHORT).show();
//                                signInWithCredential(credential);
//                            }
//
//                            @Override
//                            public void onVerificationFailed(@NonNull FirebaseException e) {
//                                Toast.makeText(com.this, "Failed to send OTP: " + e.getMessage(), Toast.LENGTH_LONG).show();
//                                Log.e("Firebase", e.getMessage());
//                            }
//                        })
//                        .build();
//
//        PhoneAuthProvider.verifyPhoneNumber(options);
//    }

//    private void verifyOtp() {
//        String enteredOtp = otp.getText().toString().trim();
//
//        if (enteredOtp.isEmpty()) {
//            Toast.makeText(this, "Enter OTP!", Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, enteredOtp);
//        signInWithCredential(credential);
//    }
//
//    private void signInWithCredential(PhoneAuthCredential credential) {
//        firebaseAuth.signInWithCredential(credential)
//                .addOnCompleteListener(task -> {
//                    if (task.isSuccessful()) {
//                        saveUserData(); // Store user data
//                    } else {
//                        Toast.makeText(this, "OTP verification failed!", Toast.LENGTH_SHORT).show();
//                    }
//                });
//    }

//    private void saveUserData() {
//        String nameH = name.getText().toString();
//        String phoneNumber = phone.getText().toString().trim();
//        String emergencyContact = emergency.getText().toString().trim();
//
//        if (nameH.isEmpty() || phoneNumber.isEmpty() || emergencyContact.isEmpty()) {
//            Toast.makeText(this, "Fill all fields!", Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        // Store user details in Firebase Realtime Database
//        String userId = databaseReference.push().getKey();
//        User user = new User(nameH, phoneNumber, emergencyContact);
//        databaseReference.child(userId).setValue(user)
//                .addOnSuccessListener(aVoid -> {
//                    Toast.makeText(this, "Data saved!", Toast.LENGTH_SHORT).show();
//                    goToNextActivity(); // Redirect user
//                })
//                .addOnFailureListener(e -> Toast.makeText(this, "Failed to save data!", Toast.LENGTH_SHORT).show());
//    }



    // User Model Class
//    public static class User {
//        public String name, phone, emergencyContact;
//
//        public User() {} // Default constructor required for Firebase
//        public User(String name, String phone, String emergencyContact) {
//            this.name = name;
//            this.phone = phone;
//            this.emergencyContact = emergencyContact;
//        }
    }
}



