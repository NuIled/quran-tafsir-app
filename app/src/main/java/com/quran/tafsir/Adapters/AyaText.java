package com.quran.tafsir.Adapters;

import org.json.JSONException;
import org.json.JSONObject;

public class AyaText {
    private String aya_number;
    private String text;
    private String translation;

    public AyaText(JSONObject ayaObj) {
        try {
            this.aya_number = ayaObj.getString("aya_number");
            this.text = ayaObj.getString("text");
            this.translation = ayaObj.getString("translation");
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    public String getAyaNumber() {
        return aya_number;
    }

    public String getText() {
        return text;
    }

    public String getTranslation() {
        return translation;
    }
}
