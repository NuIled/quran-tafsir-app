package com.quran.tafsir;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.quran.tafsir.Ads.AdmobAds;


public class RedirectActivity extends AppCompatActivity {
    TextView share,rate,more;
    RelativeLayout adbanner;
    AdmobAds admobAds ;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_redirect);
        adbanner = findViewById(R.id.Banner);

        admobAds = new AdmobAds(this);
        admobAds.showBanner(adbanner);
        admobAds.loadInter();
        TextView goToTafsirTextView = findViewById(R.id.goToTafsirTextView);
        goToTafsirTextView.setOnClickListener(v -> {
            admobAds.showInter(new AdmobAds.AdFinished() {
                @Override
                public void onAdFinished() {
                    Intent intent = new Intent(RedirectActivity.this, Tafsir_Activity.class);
                    startActivity(intent);
                }
            });
        });

        TextView goToTafsir = findViewById(R.id.app);
        goToTafsir.setOnClickListener(v -> {
            admobAds.showInter(new AdmobAds.AdFinished() {
            @Override
            public void onAdFinished() {
            Intent intent = new Intent(RedirectActivity.this, QuestionDetailActivity.class);
            startActivity(intent);
        }
        });
        });

        TextView Azkar = findViewById(R.id.appito);
        Azkar.setOnClickListener(v -> {
            admobAds.showInter(new AdmobAds.AdFinished() {
                @Override
                public void onAdFinished() {
                    Intent intent = new Intent(RedirectActivity.this, AzkarActivity.class);
                    startActivity(intent);
                }
            });
        });

        more = findViewById(R.id.more);
        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=")));
            }});

        share = findViewById(R.id.share);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent whatsappIntent = new Intent(Intent.ACTION_SEND);
                whatsappIntent.setType("text/plain");
                whatsappIntent.setPackage("com.whatsapp");
                whatsappIntent.putExtra(Intent.EXTRA_TEXT, "The text you wanted to share");
                try {
                    startActivity(whatsappIntent);
                } catch (android.content.ActivityNotFoundException ex) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=" + "com.whatsapp")));
                }
        }});

        rate = findViewById(R.id.rate);
        rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + getPackageName())));
                } catch (android.content.ActivityNotFoundException anfe) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=" + getPackageName())));
                }
            }
        });
    }
}
