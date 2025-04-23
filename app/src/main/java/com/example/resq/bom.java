package com.example.resq;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;

public class bom extends AppCompatActivity {

    private String selectedItem; // Variable to store selected spinner item
    private String answer;       // Variable to store the corresponding answer

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bom);

        // Initialize Spinner
        Spinner mySpinner = findViewById(R.id.mySpinner);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            mySpinner.setOutlineSpotShadowColor(R.color.black);
        }
        // Create an array of items for the spinner
        String[] items = {
                "Heart attack",
                "Stroke",
                "Allergic reaction",
                "Broken bone",
                "Food poisoning",
                "Unconsciousness",
                "Concussion",
                "Heavy bleeding",
                "Seizure",
                "High fever",
                "Asphyxiation",
                "Heart failure",
                "Drug overdose"
        };

        // Create a HashMap to store answers for each item
        HashMap<String, String> answers = new HashMap<>();
        answers.put("Heart attack", "Seek immediate medical attention. Call emergency services and start CPR if needed.");
        answers.put("Stroke", "Get the person to the hospital as soon as possible. Act FAST: Face drooping, Arm weakness, Speech difficulty, Time to call emergency.");
        answers.put("Allergic reaction", "Administer antihistamines or epinephrine if available. Seek medical help.");
        answers.put("Broken bone", "Immobilize the injured area and seek professional medical care.");
        answers.put("Food poisoning", "Stay hydrated and rest. Seek medical attention if symptoms persist.");
        answers.put("Unconsciousness", "Check for breathing and pulse. Call emergency services and provide CPR if necessary.");
        answers.put("Concussion", "Keep the person awake and monitor their symptoms. Seek medical help.");
        answers.put("Heavy bleeding", "Apply pressure to the wound and elevate the affected area. Seek emergency care.");
        answers.put("Seizure", "Ensure safety by clearing nearby objects. Do not restrain the person. Seek medical attention after the seizure ends.");
        answers.put("High fever", "Administer fever-reducing medication and keep hydrated. If the fever persists, consult a doctor.");
        answers.put("Asphyxiation", "Perform Heimlich maneuver if choking. Seek immediate medical help.");
        answers.put("Heart failure", "Seek emergency medical care. Administer oxygen if available.");
        answers.put("Drug overdose", "Call emergency services immediately. Administer naloxone if available.");

        // Create an ArrayAdapter for the spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        mySpinner.setAdapter(adapter);

        // Set an item selected listener
        mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedItem = parent.getItemAtPosition(position).toString(); // Store selected item
                answer = answers.get(selectedItem); // Store corresponding answer
                Toast.makeText(bom.this, "Selected: " + selectedItem, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                selectedItem = null;
                answer = null;
                Toast.makeText(bom.this, "Nothing selected", Toast.LENGTH_SHORT).show();
            }
        });

        // Initialize Submit Button
        Button submitButton = findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedItem != null && answer != null) {
                    // Start the new activity and pass the selected item and answer
                    Intent intent = new Intent(bom.this, dom.class);
                    intent.putExtra("SELECTED_ITEM", selectedItem);
                    intent.putExtra("ANSWER", answer);
                    startActivity(intent);
                } else {
                    Toast.makeText(bom.this, "Please select an item", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}




//        Spinner spinner = findViewById(R.id.spinner);
//
//// Get items from string resource
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
//                R.array.spinner_items, android.R.layout.simple_spinner_item);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//
//// Set adapter to Spinner
//        spinner.setAdapter(adapter);
//
//// Handle item selection
//        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                String selectedItem = parent.getItemAtPosition(position).toString();
//                Toast.makeText(getApplicationContext(), "Selected: " + selectedItem, Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//                // Do nothing
//            }
//        });

