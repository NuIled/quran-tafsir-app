package com.quran.tafsir;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    private TextView resultTitleTextView;
    private TextView resultValueTextView;
    private TextView additionalInfoTextView;
    private Button tryAgainButton;
    private Button returnHomeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results); // Replace with your actual layout file name

        // Initialize views
        resultTitleTextView = findViewById(R.id.resultTitle);
        resultValueTextView = findViewById(R.id.resultValue);
        additionalInfoTextView = findViewById(R.id.additionalInfo);
        tryAgainButton = findViewById(R.id.tryAgainButton);
        returnHomeButton = findViewById(R.id.returnHomeButton);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            int resultValue = extras.getInt("score");
            int additionalInfo = extras.getInt("totalQuestions");

            // Set the result data to views
            resultValueTextView.setText("الاجابات : " + resultValue); // Convert int to String
            additionalInfoTextView.setText("كل الاسئله: " + additionalInfo); // Convert int to String
        }

        // Set up button listeners
        tryAgainButton.setOnClickListener(v -> {
            // Logic for "Try Again" button
            Intent intent = new Intent(ResultActivity.this, QuestionDetailActivity.class);
            startActivity(intent);
            finish(); // Close the activity and go back
        });

        returnHomeButton.setOnClickListener(v -> {
            // Logic for "Return Home" button
            // Navigate to the main activity or home activity
            Intent intent = new Intent(ResultActivity.this, RedirectActivity.class);
            startActivity(intent);
            finish(); // Close the activity and go back
        });
    }
}
