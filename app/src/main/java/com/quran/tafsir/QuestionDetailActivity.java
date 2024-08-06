package com.quran.tafsir;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.quran.tafsir.Models.Question;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class QuestionDetailActivity extends AppCompatActivity {

    private TextView questionTextView;
    private RadioGroup optionsGroup;
    private Button submitButton;
    private Button continueButton;
    private TextView feedbackTextView;

    private List<Question> questions = new ArrayList<>();
    private int currentQuestionIndex = 0;
    private int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_detail);

        questionTextView = findViewById(R.id.questionTextView);
        optionsGroup = findViewById(R.id.optionsGroup);
        submitButton = findViewById(R.id.submitButton);
        continueButton = findViewById(R.id.continueButton);
        feedbackTextView = findViewById(R.id.feedbackTextView);

        loadQuestions();

        if (questions == null) {
            Log.e("QuestionDetailActivity", "Questions list is null");
        }

        showQuestion(currentQuestionIndex);

        submitButton.setOnClickListener(v -> {
            checkAnswer();
        });

        continueButton.setOnClickListener(v -> {
            continueToNextQuestion();
        });
    }

    private void loadQuestions() {
        String jsonData = loadJSONFromAsset("test.json");
        if (jsonData == null) {
            Toast.makeText(this, "Error loading questions", Toast.LENGTH_SHORT).show();
            return;
        }

        Gson gson = new Gson();
        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            JSONArray questionsArray = jsonObject.getJSONArray("questions");
            Type questionListType = new TypeToken<List<Question>>() {}.getType();
            questions = gson.fromJson(questionsArray.toString(), questionListType);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void showQuestion(int index) {
        Question question = questions.get(index);
        questionTextView.setText(question.getQuestion());

        optionsGroup.removeAllViews();
        for (Question.Option option : question.getOptions()) {
            RadioButton radioButton = new RadioButton(this);
            radioButton.setText(option.getAnswer());
            radioButton.setId(View.generateViewId());
            optionsGroup.addView(radioButton);
        }

        feedbackTextView.setText("");  // Clear feedback text
        feedbackTextView.setVisibility(View.GONE);  // Hide feedback initially
        continueButton.setVisibility(View.GONE);    // Hide continue button initially
    }

    private void checkAnswer() {
        int selectedId = -1;
        selectedId = optionsGroup.getCheckedRadioButtonId();
        Log.e("ssss",selectedId + "ss");
        if (selectedId == -1) {
            // No option selected
            Toast.makeText(this, "Please select an option", Toast.LENGTH_SHORT).show();
            return;
        }

        RadioButton selectedRadioButton = findViewById(selectedId);
        if (selectedRadioButton == null) {
            Toast.makeText(this, "Error: Selected option is not valid", Toast.LENGTH_SHORT).show();
            return;
        }
        String selectedAnswer = selectedRadioButton.getText().toString();

        Question question = questions.get(currentQuestionIndex);
        boolean isCorrect = false;
        String correctAnswer = "";

        for (Question.Option option : question.getOptions()) {
            if (option.getAnswer().equals(selectedAnswer)) {
                if (option.isCorrect()) {
                    isCorrect = true;
                    score++;
                }
                break;
            }
        }

        for (Question.Option option : question.getOptions()) {
            if (option.isCorrect()) {
                correctAnswer = option.getAnswer();
                break;
            }
        }

        if (isCorrect) {
            feedbackTextView.setText("Correct!");
        } else {
            feedbackTextView.setText("Incorrect! Correct answer: " + correctAnswer);
        }

        feedbackTextView.setVisibility(View.VISIBLE);  // Show feedback text
        continueButton.setVisibility(View.VISIBLE);    // Show continue button
        submitButton.setEnabled(false);  // Disable submit button
    }

    private void continueToNextQuestion() {
        if (currentQuestionIndex < questions.size() - 1) {
            currentQuestionIndex++;
            showQuestion(currentQuestionIndex);
            submitButton.setEnabled(true);  // Re-enable submit button for next question
        } else {
            showResults();
        }
    }

    private void showResults() {
        Intent intent = new Intent(QuestionDetailActivity.this, ResultsActivity.class);
        intent.putExtra("score", score);
        intent.putExtra("totalQuestions", questions.size());
        startActivity(intent);
        finish();
    }

    private String loadJSONFromAsset(String fileName) {
        StringBuilder jsonBuilder = new StringBuilder();
        try {
            InputStream inputStream = getAssets().open(fileName);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            String line;
            while ((line = reader.readLine()) != null) {
                jsonBuilder.append(line);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonBuilder.toString();
    }
}
