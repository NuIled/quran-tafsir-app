package com.quran.tafsir.Adapters;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Surah {
    private String s_name;
    private List<AyaText> aya_texts;
    private String image_url;

    public Surah(JSONObject obj) throws JSONException {
        this.s_name = obj.getString("s_name");

        // Convert JSONArray to List<AyaText>
        JSONArray ayaArray = obj.getJSONArray("aya_texts");
        this.aya_texts = new ArrayList<>();
        for (int i = 0; i < ayaArray.length(); i++) {
            JSONObject ayaObj = ayaArray.getJSONObject(i);
            this.aya_texts.add(new AyaText(ayaObj));
        }
        this.image_url = obj.getString("image_url");
    }

    public String getSName() {
        return s_name;
    }

    public List<AyaText> getAyaTexts() {
        return aya_texts;
    }

    public String getImageUrl() {
        return image_url;
    }

}


