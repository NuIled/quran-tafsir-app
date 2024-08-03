package com.quran.tafsir.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.quran.tafsir.R;

import java.util.List;

public class AyaAdapter extends RecyclerView.Adapter<AyaAdapter.AyaViewHolder> {

    private List<AyaText> ayaTextList;
    private Context context;

    public AyaAdapter(Context context, List<AyaText> ayaTextList) {
        this.context = context;
        this.ayaTextList = ayaTextList;
    }

    @NonNull
    @Override
    public AyaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_aya, parent, false);
        return new AyaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AyaViewHolder holder, int position) {
        AyaText ayaText = ayaTextList.get(position);
        holder.ayaNumberTextView.setText(ayaText.getAyaNumber());
        holder.ayaTextTextView.setText(ayaText.getText());
        holder.translationTextView.setText(ayaText.getTranslation());
    }

    @Override
    public int getItemCount() {
        return ayaTextList.size();
    }

    public static class AyaViewHolder extends RecyclerView.ViewHolder {
        TextView ayaNumberTextView;
        TextView ayaTextTextView;
        TextView translationTextView;

        public AyaViewHolder(@NonNull View itemView) {
            super(itemView);
            ayaNumberTextView = itemView.findViewById(R.id.ayaNumber);
            ayaTextTextView = itemView.findViewById(R.id.ayaText);
            translationTextView = itemView.findViewById(R.id.translation);
        }
    }
}
