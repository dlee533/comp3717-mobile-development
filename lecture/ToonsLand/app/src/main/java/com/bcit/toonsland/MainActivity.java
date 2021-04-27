package com.bcit.toonsland;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private static String SERVICE_URL = "https://api4all.azurewebsites.net/api/people";
    private ArrayList<Toon> toonList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toonList = new ArrayList<>();
        recyclerView = findViewById(R.id.recycler_main);

        GetToons getToons = new GetToons();
        getToons.execute();
    }

    public class GetToons extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            HttpHandler sh = new HttpHandler();
            String jsonStr = null;

            jsonStr = sh.makeServiceCall(SERVICE_URL);

            if (jsonStr != null) {
                jsonStr = "{\"toons\":" + jsonStr + "}";
                Gson gson = new Gson();
                ToonsBase base = gson.fromJson(jsonStr, ToonsBase.class);
                toonList = base.getToons();
            } else {
                Log.d("MainActivity", "Couldn't get json from server");
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            ToonsAdapter adapter = new ToonsAdapter(toonList);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        }
    }
}