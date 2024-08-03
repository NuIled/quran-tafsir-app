package com.quran.tafsir;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.quran.tafsir.Adapters.AyaAdapter;
import com.quran.tafsir.Adapters.AyaText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SurahDetailActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AyaAdapter adapter;
    private List<AyaText> ayaTextList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surah_detail);

        recyclerView = findViewById(R.id.ayaRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Intent intent = getIntent();
        String sName = intent.getStringExtra("s_name");
        String imageUrl = intent.getStringExtra("imageUrl");
        String ayaTextsJson = intent.getStringExtra("aya_texts");

        // Set the Surah name in a TextView
        TextView surahNameTextView = findViewById(R.id.detailSurahNameTextView);
        surahNameTextView.setText(sName);

        // Parse the JSON array
        ayaTextList = new ArrayList<>();
        try {
            JSONArray ayaArray = new JSONArray(ayaTextsJson);
            for (int i = 0; i < ayaArray.length(); i++) {
                JSONObject ayaObject = ayaArray.getJSONObject(i);
                ayaTextList.add(new AyaText(ayaObject));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // Set up the adapter
        adapter = new AyaAdapter(this, ayaTextList);
        recyclerView.setAdapter(adapter);
    }
}
