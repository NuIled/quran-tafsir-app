package com.quran.tafsir.Adapters;

import android.content.Context;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.quran.tafsir.Models.Tafsir;
import com.quran.tafsir.R;

import java.io.IOException;
import java.util.List;

public class AudioAdapter extends RecyclerView.Adapter<AudioAdapter.AudioViewHolder> {

    private List<Tafsir.Soar> soarList;
    private Context context;
    private MediaPlayer mediaPlayer;

    public AudioAdapter(List<Tafsir.Soar> soarList) {
        this.soarList = soarList;
    }

    @NonNull
    @Override
    public AudioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_audio, parent, false);
        return new AudioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AudioViewHolder holder, int position) {
        Tafsir.Soar soar = soarList.get(position);
        holder.nameTextView.setText(soar.getName());

        holder.playButton.setOnClickListener(v -> {
            try {
                if (mediaPlayer != null) {
                    mediaPlayer.release();
                }
                mediaPlayer = new MediaPlayer();
                mediaPlayer.setDataSource(soar.getUrl());
                mediaPlayer.prepare();
                mediaPlayer.start();
                holder.playButton.setText("Pause");
                holder.playButton.setOnClickListener(v1 -> {
                    if (mediaPlayer.isPlaying()) {
                        mediaPlayer.pause();
                        holder.playButton.setText("Play");
                    } else {
                        mediaPlayer.start();
                        holder.playButton.setText("Pause");
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
                holder.playButton.setText("Error");
            }
        });
    }

    @Override
    public int getItemCount() {
        return soarList.size();
    }

    public static class AudioViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        Button playButton;

        public AudioViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            playButton = itemView.findViewById(R.id.playButton);
        }
    }
}
