package com.quran.tafsir.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Question {
    @SerializedName("question")
    private String questionText;

    @SerializedName("options")
    private List<Option> options;

    public String getQuestion() {
        return questionText;
    }

    public List<Option> getOptions() {
        return options;
    }


    public static class Option {
        @SerializedName("answer")
        private String answerText;

        @SerializedName("correct")
        private boolean isCorrect;

        public String getAnswer() {
            return answerText;
        }

        public boolean isCorrect() {
            return isCorrect;
        }
    }
}
