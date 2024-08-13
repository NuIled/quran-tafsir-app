package com.quran.tafsir;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.quran.tafsir.Ads.AdmobAds;

public class Tafsir_Activity extends AppCompatActivity{
    RelativeLayout adbanner;
    AdmobAds admobAds ;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.tafsir_type);
            adbanner = findViewById(R.id.Banner);

            admobAds = new AdmobAds(this);
            admobAds.showBanner(adbanner);
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