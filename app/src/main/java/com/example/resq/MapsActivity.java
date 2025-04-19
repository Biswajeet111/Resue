package com.example.resq;

import android.Manifest;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.Priority;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.Task;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private GoogleMap mMap;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        requestLocationPermission();
        testApiKey(); // Test API Key on startup

        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
    }

    private void requestLocationPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
        } else {
            checkLocationEnabled();
        }
    }

    private void checkLocationEnabled() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (locationManager != null && !locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            new AlertDialog.Builder(this)
                    .setMessage("Location is turned off. Please enable it to use this feature.")
                    .setPositiveButton("Settings", (dialog, which) -> startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)))
                    .setNegativeButton("Cancel", null)
                    .show();
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
            getCurrentLocationAndSearchHospitals();
        }
    }

    private void getCurrentLocationAndSearchHospitals() {
        if (progressBar != null) {
            progressBar.setVisibility(View.VISIBLE);
        }

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        fusedLocationProviderClient.getCurrentLocation(Priority.PRIORITY_HIGH_ACCURACY, null)
                .addOnSuccessListener(location -> {
                    if (progressBar != null) {
                        progressBar.setVisibility(View.GONE);
                    }

                    if (location != null) {
                        LatLng currentLocation = new LatLng(location.getLatitude(), location.getLongitude());
                        mMap.addMarker(new MarkerOptions().position(currentLocation).title("You are here"));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 15));

                        searchNearestHospital(currentLocation);
                    } else {
                        Toast.makeText(this, "Unable to fetch location", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(e -> {
                    if (progressBar != null) {
                        progressBar.setVisibility(View.GONE);
                    }
                    Toast.makeText(this, "Error fetching location", Toast.LENGTH_SHORT).show();
                });
    }

    private void searchNearestHospital(LatLng currentLocation) {

        String apiKey = BuildConfig.MAPS_API_KEY;
        String url = String.format(
                "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=%f,%f&rankby=distance&type=hospital,pharmacy,doctor&key=%s",
                currentLocation.latitude, currentLocation.longitude, apiKey
        );

        new Thread(() -> {
            try {
                HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
                InputStreamReader reader = new InputStreamReader(connection.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(reader);
                StringBuilder result = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    result.append(line);
                }

                JSONObject responseJson = new JSONObject(result.toString());
                JSONArray resultsArray = responseJson.getJSONArray("results");

                if (resultsArray.length() > 0) {
                    runOnUiThread(() -> {
                        for (int i = 0; i < resultsArray.length(); i++) {
                            JSONObject hospital = null;
                            try {
                                hospital = resultsArray.getJSONObject(i);
                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }
                            JSONObject locationObj = null;
                            try {
                                locationObj = hospital.getJSONObject("geometry").getJSONObject("location");
                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }

                            LatLng hospitalLocation = null;
                            try {
                                hospitalLocation = new LatLng(locationObj.getDouble("lat"), locationObj.getDouble("lng"));
                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }
                            String name = null;
                            try {
                                name = hospital.getString("name");
                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }

                            mMap.addMarker(new MarkerOptions().position(hospitalLocation).title(name));
                        }
                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 13));
                    });
                } else {
                    runOnUiThread(() -> Toast.makeText(this, "No hospitals found nearby", Toast.LENGTH_SHORT).show());
                }

            } catch (Exception e) {
                e.printStackTrace();
                runOnUiThread(() -> Toast.makeText(this, "Error fetching hospital data", Toast.LENGTH_SHORT).show());
            }
        }).start();
    }

    private void testApiKey() {
        String apiKey = BuildConfig.MAPS_API_KEY;  // Ensure your API key is securely stored
        String testUrl = String.format(
                "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=28.6139,77.2090&radius=5000&type=hospital&key=%s",
                apiKey
        );

        new Thread(() -> {
            try {
                HttpURLConnection connection = (HttpURLConnection) new URL(testUrl).openConnection();
                InputStreamReader reader = new InputStreamReader(connection.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(reader);
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    response.append(line);
                }

                JSONObject jsonResponse = new JSONObject(response.toString());
                String apiStatus = jsonResponse.getString("status");

                runOnUiThread(() -> {
                    Toast.makeText(this, "API Status: " + apiStatus, Toast.LENGTH_SHORT).show();
                    Log.d("API Test", "Response: " + jsonResponse.toString());
                });

            } catch (Exception e) {
                e.printStackTrace();
                runOnUiThread(() -> Toast.makeText(this, "API Error! Check your key.", Toast.LENGTH_SHORT).show());
            }
        }).start();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                checkLocationEnabled();
            } else {
                Toast.makeText(this, "Location permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
}