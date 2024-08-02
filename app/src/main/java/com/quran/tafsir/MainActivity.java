package com.quran.tafsir;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.quran.tafsir.Adapters.CardAdapter;
import com.quran.tafsir.Adapters.Surah;
import com.quran.tafsir.utils.AssetUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CardAdapter cardAdapter;
    private List<Surah> surahList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Load JSON from assets
        String json = AssetUtils.loadJSONFromAsset(this, "data.json");

        // Parse JSON data
        parseJSON(json);

        // Set up the adapter
        cardAdapter = new CardAdapter(this, surahList);
        recyclerView.setAdapter(cardAdapter);
    }

    private void parseJSON(String json) {

        try {
            JSONObject obj = new JSONObject(json);
            JSONArray data = obj.getJSONArray("data");
            for (int i =0 ; i < data.length(); i++)
            {
                JSONObject surah = data.getJSONObject(i);
                Surah sura = new Surah(surah);
                surahList.add(sura);
            }
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}