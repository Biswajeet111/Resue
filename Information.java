package com.example.resq;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Information extends AppCompatActivity {
    private TextView chatWindow;
    private EditText userInput;
    private Button sendButton;
    private ExecutorService executorService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information); // Ensure this matches your layout file

        chatWindow = findViewById(R.id.chatWindow);
        userInput = findViewById(R.id.userInput);
        sendButton = findViewById(R.id.sendButton);

        // Initialize background thread executor
        executorService = Executors.newSingleThreadExecutor();

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userMessage = userInput.getText().toString();
                userInput.setText("");
                chatWindow.append("\nUser: " + userMessage);

                // Call chatbot API asynchronously
                executorService.execute(() -> {
                    final String botResponse = getBotResponse(userMessage);
                    runOnUiThread(() -> chatWindow.append("\nBot: " + botResponse));
                });
            }
        });
    }

    private String getBotResponse(String message) {
        try {
            String apiUrl = "AlzaSyCfm-1R-UOslaJDz4ojKZUG7ZKrt0URV4k"; // Replace with your API endpoint
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            // Prepare JSON request
            JSONObject requestJson = new JSONObject();
            requestJson.put("message", message);

            // Send request
            OutputStream os = conn.getOutputStream();
            os.write(requestJson.toString().getBytes());
            os.flush();
            os.close();

            // Read response
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                response.append(line);
            }
            br.close();
            conn.disconnect();

            // Extract chatbot response from JSON
            JSONObject responseJson = new JSONObject(response.toString());
            return responseJson.optString("reply", "Sorry, I didn't understand.");

        } catch (Exception e) {
            Log.e("ChatbotError", "API Error", e);
            return "Error fetching chatbot response!";
        }
    }
}
