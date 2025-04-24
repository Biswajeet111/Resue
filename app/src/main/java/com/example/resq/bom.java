package com.example.resq;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class bom extends AppCompatActivity {

    private String selectedItem;
    private String answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bom);

        // Initialize Spinner
        Spinner mySpinner = findViewById(R.id.mySpinner);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            mySpinner.setOutlineSpotShadowColor(getResources().getColor(R.color.black));
        }

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
        answers.put("Heart attack", "Precautions: Maintain a healthy lifestyle, avoid smoking, eat heart-healthy foods, and exercise regularly. Treatment: Seek immediate medical attention. Call emergency services and administer CPR if trained and necessary. Provide aspirin to thin blood (if safe and recommended).");
        answers.put("Stroke", "Precautions: Monitor blood pressure, manage diabetes, maintain a healthy diet, and avoid smoking. Recognize symptoms early using FAST: Face drooping, Arm weakness, Speech difficulty, Time to act. Treatment: Get the person to the hospital urgently. Do not provide food or drinks as swallowing may be impaired. Keep the person calm and still.");
        answers.put("Allergic reaction", "Precautions: Avoid allergens, carry antihistamines or epinephrine, and consider wearing a medical alert bracelet. Treatment: For mild reactions, administer antihistamines. For anaphylaxis, use an epinephrine auto-injector (EpiPen) and call emergency services. Keep the patient lying flat and elevate their legs if necessary.");
        answers.put("Broken bone", "Precautions: Ensure adequate calcium and vitamin D intake, and wear protective gear during sports or high-risk activities. Treatment: Immobilize the area with a splint, apply ice packs to reduce swelling, and seek immediate professional medical care.");
        answers.put("Food poisoning", "Precautions: Practice safe food handling by washing hands and cooking food thoroughly. Avoid expired or contaminated items. Treatment: Stay hydrated with electrolyte solutions and rest. Seek medical attention for persistent symptoms or severe dehydration.");
        answers.put("Unconsciousness", "Precautions: Stay hydrated and avoid situations that could lead to fainting (e.g., standing too long). Treatment: Check for breathing and pulse. Call emergency services, provide CPR if needed, and place the person in a recovery position to keep the airway open.");
        answers.put("Concussion", "Precautions: Wear helmets during activities like biking or contact sports. Avoid risky behaviors leading to head injuries. Treatment: Keep the person awake and observe for symptoms like confusion or nausea. Avoid exertion until cleared by a healthcare professional.");
        answers.put("Heavy bleeding", "Precautions: Use tools carefully to avoid cuts. Wear protective clothing during activities with risks of injury. Treatment: Apply direct pressure to the wound using clean cloth or bandages. Elevate the affected area and seek emergency care if bleeding persists after 10 minutes.");
        answers.put("Seizure", "Precautions: Identify triggers and avoid them. Take prescribed medications consistently. Treatment: Protect the person by clearing nearby objects and turning them on their side. Do not restrain or put anything in their mouth. Seek medical attention afterward.");
        answers.put("High fever", "Precautions: Avoid infections by maintaining good hygiene. Ensure vaccinations are current. Treatment: Administer fever-reducing medications like acetaminophen or ibuprofen. Keep the person cool with damp cloths and ensure hydration. Seek medical care for prolonged or extremely high fever.");
        answers.put("Asphyxiation", "Precautions: Keep small objects away from young children. Learn first-aid techniques for choking. Treatment: Perform the Heimlich maneuver if choking is the cause. Begin CPR if breathing has stopped. Call emergency services immediately.");
        answers.put("Heart failure", "Precautions: Maintain a heart-healthy lifestyle with proper diet, regular exercise, and management of existing heart conditions. Treatment: Seek immediate emergency care. Administer oxygen if available, and keep the patient calm and still.");
        answers.put("Drug overdose", "Precautions: Safeguard medications and avoid misuse. Use prescription drugs only under medical supervision. Treatment: Call emergency services immediately. Administer naloxone if the overdose involves opioids, and ensure the person remains breathing and conscious.");
        // Add all other answers similarly

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        mySpinner.setAdapter(adapter);

        mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedItem = parent.getItemAtPosition(position).toString();
                answer = answers.get(selectedItem);
                Toast.makeText(bom.this, "Selected: " + selectedItem, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                selectedItem = null;
                answer = null;
                Toast.makeText(bom.this, "Nothing selected", Toast.LENGTH_SHORT).show();
            }
        });

//
        Button btnSubmit = findViewById(R.id.Chat);
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                executorService.execute(() -> {

                    runOnUiThread(() -> {
                        Intent intent = new Intent(bom.this, Information.class);

                        startActivity(intent);
                    });
                });
            }
        });

        Button submitButton = findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedItem != null && answer != null) {
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