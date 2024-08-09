package com.quran.tafsir.Adapters;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.quran.tafsir.Models.AudioItem;
import com.quran.tafsir.R;
import java.io.IOException;
import java.util.List;

public class AzkarAdapter extends RecyclerView.Adapter<AzkarAdapter.AudioViewHolder> {

    private List<AudioItem> audioItems;
    private Context context;
    private MediaPlayer mediaPlayer;
    private String currentPlayingFileName = null;

    public AzkarAdapter(Context context, List<AudioItem> audioItems) {
        this.context = context;
        this.audioItems = audioItems;
    }

    @NonNull
    @Override
    public AudioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_audio, parent, false);
        return new AudioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AudioViewHolder holder, int position) {
        AudioItem audioItem = audioItems.get(position);
        holder.nameTextView.setText(audioItem.getText());

        holder.playButton.setOnClickListener(v -> {
            if (mediaPlayer != null && currentPlayingFileName != null && !currentPlayingFileName.equals(audioItem.getAudioFileName())) {
                mediaPlayer.release();
                mediaPlayer = null;
            }

            if (mediaPlayer == null) {
                mediaPlayer = new MediaPlayer();
            }

            try {
                AssetFileDescriptor afd = context.getAssets().openFd(audioItem.getAudioFileName());
                mediaPlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
                afd.close();
                mediaPlayer.prepare();
                mediaPlayer.start();
                currentPlayingFileName = audioItem.getAudioFileName();
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
        return audioItems.size();
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
