package com.quran.tafsir;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.quran.tafsir.Adapters.AzkarAdapter;
import com.quran.tafsir.Models.AudioItem;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class AzkarActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AzkarAdapter audioAdapter;
    private List<AudioItem> audioList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        recyclerView = findViewById(R.id.categoriesRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Load JSON from assets
        String json = loadJSONFromAsset(this, "adhkar.json");
        parseJSON(json);

        audioAdapter = new AzkarAdapter(this, audioList);
        recyclerView.setAdapter(audioAdapter);
    }

    private String loadJSONFromAsset(Context context, String fileName) {
        StringBuilder json = new StringBuilder();
        try {
            AssetManager assetManager = context.getAssets();
            InputStream inputStream = assetManager.open(fileName);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                json.append(line);
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json.toString();
    }

    private void parseJSON(String json) {
        try {
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                JSONArray array = jsonObject.getJSONArray("array");

                for (int j = 0; j < array.length(); j++) {
                    JSONObject item = array.getJSONObject(j);
                    String text = item.getString("text");
                    String audioFile = item.getString("audio");
                    audioList.add(new AudioItem(text, audioFile.replace("/audio/", "audio/")));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
