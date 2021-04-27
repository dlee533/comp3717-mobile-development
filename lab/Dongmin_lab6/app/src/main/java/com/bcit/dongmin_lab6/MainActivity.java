package com.bcit.dongmin_lab6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public MapsFragment mapsFragment;
    private LatLng locationLatLng;
    private int radius;
    private List<Place> placeList;
    private static String SERVICE_URL = "https://api.opentripmap.com/0.1/en/places/radius?radius=%d&lon=%f&lat=%f&rate=3&format=json&apikey=%s";
//            String formattedString = "https://api.opentripmap.com/0.1/en/places/radius?radius=10000&lon=-122&lat=49&rate=3&format=json&apikey=5ae2e3f221c38a28845f05b67c298a22a61b2e87bcbaeb34790fba4f";

    private static String API_KEY = "5ae2e3f221c38a28845f05b67c298a22a61b2e87bcbaeb34790fba4f";
    private EditText editTextLocation;
    private EditText editTextRadius;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        placeList = new ArrayList<>();

        FragmentManager fragmentManager = getSupportFragmentManager();
        mapsFragment = (MapsFragment) fragmentManager.findFragmentById(R.id.fragment_main_content);

        Button searchButton = findViewById(R.id.button_main_search);
        searchButton.setOnClickListener(clickSearch);

        Button myLocationButton = findViewById(R.id.button_main_useCurrentLocation);
        myLocationButton.setOnClickListener(clickMyLocation);

        editTextLocation = findViewById(R.id.editText_main_location);
        editTextRadius = findViewById(R.id.editText_main_radius);
    }

    private View.OnClickListener clickMyLocation = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            mapsFragment.GoToCurrentLocationWithPermissions();
        }
    };

    private View.OnClickListener clickSearch = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            locationLatLng = null;
            String location = editTextLocation.getText().toString();
            radius = Integer.valueOf(editTextRadius.getText().toString()) * 1000;
            locationLatLng = mapsFragment.searchLocation(location);
            if (locationLatLng == null) {
                return;
            }

            mapsFragment.circleMap(locationLatLng, radius);

            GetPlaces getPlaces = new GetPlaces();
            getPlaces.execute();
        }
    };

    private class GetPlaces extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            HttpHandler sh = new HttpHandler();
            String jsonStr = null;

            int radius = Integer.valueOf(editTextRadius.getText().toString()) * 1000;
            double latitude = locationLatLng.latitude;
            double longitude = locationLatLng.longitude;

            String formattedString = String.format(SERVICE_URL, radius, longitude, latitude, API_KEY);
            System.out.println(formattedString);
            jsonStr = sh.makeServiceCall(formattedString);

            if (jsonStr != null) {
                jsonStr = "{\"places\":" + jsonStr + "}";
                Gson gson = new Gson();
                System.out.println(jsonStr);
                PlaceBase base = gson.fromJson(jsonStr, PlaceBase.class);
                placeList = base.getPlaces();
            } else {
                Log.d("MainActivity", "Couldn't get json from server");
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            if (placeList.size() == 0) {
                String location = editTextLocation.getText().toString();
                Toast toast = Toast.makeText(getApplicationContext(), "There's no popular location near the " + location, Toast.LENGTH_LONG);
                toast.show();
            } else if (placeList.size() > 10) {
                mapsFragment.zoomIn(locationLatLng, 18f);
            }

            for (Place place : placeList) {
                LatLng latLng = new LatLng(place.getLat(), place.getLon());
                String name = place.getName();
                mapsFragment.markMap(latLng, name);
            }
        }
    }
}