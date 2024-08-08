package com.quran.tafsir;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.quran.tafsir.Adapters.AudioAdapter;
import com.quran.tafsir.Models.Tafsir;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;

public class AudioActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AudioAdapter audioAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_player);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Load data and set adapter
        loadTafsirData();
    }

    private void loadTafsirData() {
        String jsonData = loadJSONFromAsset("audio.json"); // Ensure this matches your actual file name

        if (jsonData == null) {
            Toast.makeText(this, "Error loading JSON file", Toast.LENGTH_SHORT).show();
            return;
        }

        Gson gson = new Gson();
        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            JSONObject tafsirObject = jsonObject.getJSONObject("tafasir");
            JSONArray soarArray = tafsirObject.getJSONArray("soar");

            // Debug: Log JSON array content
            Log.d("JSON_DATA", soarArray.toString());

            Type soarListType = new TypeToken<List<Tafsir.Soar>>() {}.getType();
            List<Tafsir.Soar> soarList = gson.fromJson(soarArray.toString(), soarListType);

            if (soarList == null || soarList.isEmpty()) {
                Toast.makeText(this, "No audio files found", Toast.LENGTH_SHORT).show();
            } else {
                audioAdapter = new AudioAdapter(soarList);
                recyclerView.setAdapter(audioAdapter);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(this, "JSON Parsing Error", Toast.LENGTH_SHORT).show();
        }
    }


    private String loadJSONFromAsset(String fileName) {
        String json = null;
        try {
            InputStream is = getAssets().open(fileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

}
