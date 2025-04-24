package com.example.resq;


import android.util.Log;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class chatHelper {

    // Utility method for chatbot API interaction
    public static String getBotResponse(String message) {
        try {
            // API Endpoint
            String apiUrl = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent?key=AIzaSyCfm-1R-UOslaJdz4ojKZUG7ZKrt0URV4kg";
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            // Prepare JSON payload
            JSONObject partObject = new JSONObject();
            partObject.put("text", message);

            JSONObject contentsObject = new JSONObject();
            contentsObject.put("parts", new JSONArray().put(partObject));

            JSONObject requestJson = new JSONObject();
            requestJson.put("contents", new JSONArray().put(contentsObject));

            // Send JSON payload
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

            // Parse JSON response
            JSONObject responseJson = new JSONObject(response.toString());
            JSONArray candidates = responseJson.optJSONArray("candidates");

            if (candidates != null && candidates.length() > 0) {
                JSONObject contentObject = candidates.getJSONObject(0).optJSONObject("content");
                JSONArray parts = contentObject.optJSONArray("parts");

                if (parts != null && parts.length() > 0) {
                    return parts.getJSONObject(0).optString("text", "Sorry, I didn't understand.");
                }
            }

            return "Sorry, I didn't understand.";
        } catch (Exception e) {
            Log.e("ChatBotUtilsError", "Error in API request", e);
            return "Error fetching response!";
        }
    }
}