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

    public static String getBotResponse(String message) {
        HttpURLConnection conn = null;
        BufferedReader reader = null;

        try {
            String apiUrl = "https://your-bot-api-url.com/getResponse"; // Replace with your bot API URL
            URL url = new URL(apiUrl);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            JSONObject requestJson = new JSONObject();
            requestJson.put("message", message);

            OutputStream os = conn.getOutputStream();
            os.write(requestJson.toString().getBytes());
            os.flush();
            os.close();

            int responseCode = conn.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_OK) {
                Log.e("ChatHelper", "HTTP error code: " + responseCode);
                return "Error fetching response!";
            }

            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            JSONObject responseJson = new JSONObject(response.toString());
            return responseJson.optString("botResponse", "Sorry, I didn't understand.");
        } catch (Exception e) {
            Log.e("ChatHelper", "Error in bot response", e);
            return "Error fetching response!";
        } finally {
            try {
                if (reader != null) reader.close();
                if (conn != null) conn.disconnect();
            } catch (Exception e) {
                Log.e("ChatHelper", "Error closing resources", e);
            }
        }
    }
}