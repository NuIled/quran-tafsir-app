package com.quran.tafsir.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.quran.tafsir.R;
import com.quran.tafsir.SurahDetailActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardViewHolder> {

    private List<Surah> surahList;
    private Context context;

    public CardAdapter(Context context, List<Surah> surahList) {
        this.context = context;
        this.surahList = surahList;
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_view, parent, false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        Surah surah = surahList.get(position);
        holder.titleTextView.setText(surah.getSName());
        
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, SurahDetailActivity.class);
            intent.putExtra("s_name", surah.getSName());

            // Convert List<AyaText> to a JSON array string
            JSONArray ayaArray = new JSONArray();
            for (AyaText ayaText : surah.getAyaTexts()) {
                try {
                    JSONObject ayaJson = new JSONObject();
                    ayaJson.put("aya_number", ayaText.getAyaNumber());
                    ayaJson.put("text", ayaText.getText());
                    ayaJson.put("translation", ayaText.getTranslation());
                    ayaArray.put(ayaJson);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            intent.putExtra("aya_texts", ayaArray.toString());

            context.startActivity(intent);
        });
    }


    @Override
    public int getItemCount() {
        return surahList.size();
    }

    public static class CardViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView titleTextView;

        public CardViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img);
            titleTextView = itemView.findViewById(R.id.title);
        }
    }
}
