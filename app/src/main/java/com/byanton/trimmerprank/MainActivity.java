package com.byanton.trimmerprank;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.InterstitialAd;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import com.byanton.trimmerprank.ui.main.SectionsPagerAdapter;

public class MainActivity extends AppCompatActivity{
    public InterstitialAd mInterstitialAd;
    Button btnsetting;
    int delayTime = 10;
    public int sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        Intent intent = getIntent();
        sp = intent.getIntExtra("SoundPosition",0);

        btnsetting = (Button) findViewById(R.id.settingbtn);
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                mInterstitialAd.loadAd(new AdRequest.Builder().build());
            }
        }, delayTime * 1000);




                mInterstitialAd.setAdListener(new AdListener(){
                    @Override
                    public void onAdClosed() {
                        SharedPreferences shpr = getSharedPreferences("shpr", MODE_PRIVATE);
                        boolean show = shpr.getBoolean("show", true);
                        if (show) {
                           startIntentPopUp();
                        }
                    }

                    @Override
                    public void onAdLoaded() {
                        if(mInterstitialAd.isLoaded()){
                            mInterstitialAd.show();
                        }
                    }
                });


        btnsetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Setting.class);
                startActivity(intent);
            }
        });


    }

    private void startIntentPopUp() {
        Intent intent = new Intent(MainActivity.this,PopUpRate.class);
        startActivity(intent);
        SharedPreferences shpr = getSharedPreferences("shpr",MODE_PRIVATE);
        SharedPreferences.Editor editor = shpr.edit();
        editor.putBoolean("show",false);
        editor.apply();
    }

}