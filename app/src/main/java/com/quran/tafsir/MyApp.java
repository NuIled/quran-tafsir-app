package com.quran.tafsir;

import android.content.Context;

import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.ads.MobileAds;


import org.json.JSONException;
import org.json.JSONObject;

public class MyApp extends MultiDexApplication {

    RequestQueue requestQueue;
    public static final String JSON_LINK = "http://192.168.0.213/tutorials/json/guide.json";

    public static int isJsonDone = 0; // 0 not yet processed 1 json is done 2 error

    public static String BannerAdmob = "ca-app-pub-3940256099942544/6300978111";
    public static String InterstitialAdmob = "ca-app-pub-3940256099942544/1033173712";
    public static String NativeAdmob = "ca-app-pub-3940256099942544/2247696110";

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        requestQueue = Volley.newRequestQueue(this);

        MobileAds.initialize(this);

//        callAds();
    }

    private void callAds() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, JSON_LINK, null,
                new Response.Listener() {
                    @Override
                    public void onResponse(Object response)
                    {
                        try {
                            JSONObject AdsController = ((JSONObject) response).getJSONObject("AdsController");
                            BannerAdmob = AdsController.getString("BannerAdmob");
                            InterstitialAdmob = AdsController.getString("InterstitialAdmob");
                            NativeAdmob = AdsController.getString("NativeAdmob");
                            isJsonDone = 1;

                        } catch (JSONException e) {
                            isJsonDone = 2;
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        isJsonDone = 2;
                    }
                });
        jsonObjectRequest.setShouldCache(false);
        requestQueue.add(jsonObjectRequest);
    }
}
