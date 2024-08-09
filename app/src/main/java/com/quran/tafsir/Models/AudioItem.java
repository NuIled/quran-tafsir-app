package com.quran.tafsir.Models;

public class AudioItem {
    private String text;
    private String audioFileName;

    public AudioItem(String text, String audioFileName) {
        this.text = text;
        this.audioFileName = audioFileName;
    }

    public String getText() {
        return text;
    }

    public String getAudioFileName() {
        return audioFileName;
    }
}
