package com.example.resq;

import android.content.Intent;
import android.net.Uri;
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
                "Drug overdose",
                "Nose Bleeding",
                "Constipation"
        };

        // Create a HashMap to store answers for each item
        HashMap<String, String> answers = new HashMap<>();
        answers.put("Heart attack", "Treatment: Call emergency services immediately for chest pain, shortness of breath. Call emergency services and administer CPR if trained and necessary. Provide aspirin to thin blood (if safe and recommended).\n" +
                "Precaution: Manage heart health: diet, exercise, no smoking.");
        answers.put("Stroke", "Treatment: Call emergency services immediately for sudden weakness, speech difficulty. Get the person to the hospital urgently. Do not provide food or drinks as swallowing may be impaired. Keep the person calm and still.\n" +
                " Precautions: Monitor blood pressure, manage diabetes, maintain a healthy diet, and avoid smoking. Recognize symptoms early using FAST: Face drooping, Arm weakness, Speech difficulty, Time to act.");
        answers.put("Allergic reaction", "Treatment: For severe reaction, use epinephrine auto-injector, call emergency services.\n For mild reactions, administer antihistamines. For anaphylaxis, use an epinephrine auto-injector (EpiPen) and call emergency services. Keep the patient lying flat and elevate their legs if necessary.\n" +
                "Precaution: Avoid known allergens.Precautions: Avoid allergens, carry antihistamines or epinephrine, and consider wearing a medical alert bracelet. ");
        answers.put("Broken bone", "Treatment: Immobilize the area with a splint, apply ice packs to reduce swelling, and seek immediate professional medical care. \n" +
                "Precaution: Prevent falls and injuries. Precautions: Ensure adequate calcium and vitamin D intake, and wear protective gear during sports or high-risk activities. ");
        answers.put("Food poisoning", " Treatment: Stay hydrated with electrolyte solutions and rest. Seek medical attention for persistent symptoms or severe dehydration. \n" +
                "Precautions: Practice safe food handling by washing hands and cooking food thoroughly. Avoid expired or contaminated items.");
        answers.put("Unconsciousness", "Treatment: Check for breathing and pulse. Call emergency services, provide CPR if needed, and place the person in a recovery position to keep the airway open.\n" +
                "Precautions: Stay hydrated and avoid situations that could lead to fainting (e.g., standing too long).");
        answers.put("Concussion", "Treatment: Keep the person awake and observe for symptoms like confusion or nausea. Avoid exertion until cleared by a healthcare professional.\n" +
                "Precautions: Wear helmets during activities like biking or contact sports. Avoid risky behaviors leading to head injuries.");
        answers.put("Heavy bleeding",  "Treatment: Apply direct pressure to the wound using clean cloth or bandages. Elevate the affected area and seek emergency care if bleeding persists after 10 minutes.\n" +
                "Precautions: Use tools carefully to avoid cuts. Wear protective clothing during activities with risks of injury.");
        answers.put("Seizure",  "Treatment: Protect the person by clearing nearby objects and turning them on their side. Do not restrain or put anything in their mouth. Seek medical attention afterward.\n"  +
                "Precautions: Identify triggers and avoid them. Take prescribed medications consistently.");
        answers.put("High fever",  "Treatment: Administer fever-reducing medications like acetaminophen or ibuprofen. Keep the person cool with damp cloths and ensure hydration. Seek medical care for prolonged or extremely high fever.\n"  +
                "Precautions: Avoid infections by maintaining good hygiene. Ensure vaccinations are current.");
        answers.put("Asphyxiation", "Treatment: Perform the Heimlich maneuver if choking is the cause. Begin CPR if breathing has stopped. Call emergency services immediately.\n" +
                "Precautions: Keep small objects away from young children. Learn first-aid techniques for choking. ");
        answers.put("Heart failure",  "Treatment: Seek immediate emergency care. Administer oxygen if available, and keep the patient calm and still.\n" +
                "Precautions: Maintain a heart-healthy lifestyle with proper diet, regular exercise, and management of existing heart conditions.");
        answers.put("Drug overdose", "Treatment: Call emergency services immediately. Administer naloxone if the overdose involves opioids, and ensure the person remains breathing and conscious.\n"+
                "Precautions: Safeguard medications and avoid misuse. Use prescription drugs only under medical supervision. ");
       answers.put("Nose Bleeding", "Treatment: Sit forward and firmly pinch the soft part of your nose for 10-15 minutes to stop a nosebleed.\n" +
               "Prevention: Keep your nasal passages moist and avoid picking or forceful blowing to help prevent nosebleeds. ");
       answers.put("Constipation", "Short Treatment: Increase fiber and fluid intake, try gentle exercise, consider a mild over-the-counter laxative if needed.\n" +
               "Precaution: Maintain a high-fiber diet, drink plenty of water, and engage in regular physical activity.");

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

        Button submitButton ,doc;
        submitButton = findViewById(R.id.submitButton);
        doc = findViewById(R.id.doc);
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

        doc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = getIntent().getExtras();

                int number = bundle.getInt("key");
                String uriString = "tel: " + number; // Convert the number to a URI string
                Uri uri = Uri.parse(uriString); // Parse the string into a URI

                Intent intent = new Intent(Intent.ACTION_DIAL); // Example: Dialer action
                intent.setData(uri); // Set the URI as data

                Intent chooser = Intent.createChooser(intent, "Choose an app to make the call");
                startActivity(chooser);

            }}
        );
    }
}
