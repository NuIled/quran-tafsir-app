package com.quran.tafsir;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

//public class SplashScreen extends AppCompatActivity {
//    static int TIME = 3000;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_spalsh);
//
//        final Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                if(MyApp.isJsonDone == 0) {
//                    handler.postDelayed(this, TIME);
//                }
//                else if(MyApp.isJsonDone == 1) {
//                    Intent mainIntent = new Intent(SplashScreen.this, RedirectActivity.class);
//                    startActivity(mainIntent);
//                    finish();
//                }
//                else if(MyApp.isJsonDone == 2) {
//                    finish();
//                }
//            }
//        }, TIME);
//
//    }
//}
public class SplashScreen extends AppCompatActivity {

    private static final int SPLASH_DISPLAY_LENGTH = 2000; // 2 seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spalsh);

        new Handler().postDelayed(() -> {
            Intent intent = new Intent(SplashScreen.this, RedirectActivity.class);
            startActivity(intent);
            finish();
        }, SPLASH_DISPLAY_LENGTH);
    }
}