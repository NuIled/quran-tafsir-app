package com.quran.tafsir;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class RedirectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_redirect);

        TextView goToTafsirTextView = findViewById(R.id.goToTafsirTextView);
        goToTafsirTextView.setOnClickListener(v -> {
            Intent intent = new Intent(RedirectActivity.this, MainActivity.class);
            startActivity(intent);
        });

        TextView goToTafsir = findViewById(R.id.app);
        goToTafsir.setOnClickListener(v -> {
            Intent intent = new Intent(RedirectActivity.this, QuestionDetailActivity.class);
            startActivity(intent);
        });
    }
}
