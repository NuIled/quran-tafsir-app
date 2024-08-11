package com.quran.tafsir.Ads;

import static android.content.ContentValues.TAG;

import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.quran.tafsir.MyApp;

public class AdmobAds {

    public interface AdFinished {
        void onAdFinished();
    }

    private final AppCompatActivity mActivity;
    private InterstitialAd mInterstitialAd;


    public AdmobAds(AppCompatActivity activity) {
        this.mActivity = activity;
    }

    public void showBanner(RelativeLayout adContainer) {
        AdView adView = new AdView(mActivity);
        adView.setAdUnitId(MyApp.BannerAdmob);
        adContainer.addView(adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        AdSize adSize = getAdSize();
        adView.setAdSize(adSize);
        adView.loadAd(adRequest);

    }

    private AdSize getAdSize() {
        Display display = mActivity.getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);

        float widthPixels = outMetrics.widthPixels;
        float density = outMetrics.density;

        int adWidth = (int) (widthPixels / density);
        // Step 3 - Get adaptive ad size and return for setting on the ad view.
        return AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(mActivity, adWidth);
    }

    public void loadInter() {
        AdRequest adRequest = new AdRequest.Builder().build();

        InterstitialAd.load(mActivity, MyApp.InterstitialAdmob, adRequest, new InterstitialAdLoadCallback() {
            @Override
            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                mInterstitialAd = interstitialAd;
            }

            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                // Handle the error
                mInterstitialAd = null;
            }
        });

    }

    public void showInter(final AdFinished adFinished) {
        if (mInterstitialAd != null) {
            mInterstitialAd.show(mActivity);
            mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback(){
                @Override
                public void onAdClicked() {
                    adFinished.onAdFinished();
                    Log.d(TAG, "Ad was clicked.");
                }

                @Override
                public void onAdDismissedFullScreenContent() {
                    adFinished.onAdFinished();
                    Log.d(TAG, "Ad dismissed fullscreen content.");
                    mInterstitialAd = null;
                }

                @Override
                public void onAdFailedToShowFullScreenContent(AdError adError) {
                    Log.e(TAG, "Ad failed to show fullscreen content.");
                    mInterstitialAd = null;
                }

                @Override
                public void onAdImpression() {
                    Log.d(TAG, "Ad recorded an impression.");
                }

                @Override
                public void onAdShowedFullScreenContent() {
                    mInterstitialAd = null;
                    loadInter();
                    Log.d(TAG, "Ad showed fullscreen content.");
                }
            });

        } else {
            adFinished.onAdFinished();
        }
    }

}
