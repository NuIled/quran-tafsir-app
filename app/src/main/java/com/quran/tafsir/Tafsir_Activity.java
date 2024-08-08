package com.quran.tafsir;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Tafsir_Activity extends AppCompatActivity{

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.tafsir_type);

            TextView goToTafsirTextView = findViewById(R.id.goToTafsirTextView);
            goToTafsirTextView.setOnClickListener(v -> {
                Intent intent = new Intent(Tafsir_Activity.this, AudioActivity.class);
                startActivity(intent);
            });

            TextView goToTafsir = findViewById(R.id.app);
            goToTafsir.setOnClickListener(v -> {
                Intent intent = new Intent(Tafsir_Activity.this, MainActivity.class);
                startActivity(intent);
            });
        }
    }