package com.byanton.trimmerprank;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;

import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Handler;
import android.preference.PreferenceManager;
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

import static com.byanton.trimmerprank.Setting.CHANNEL_1;

public class MainActivity extends AppCompatActivity{
    public InterstitialAd mInterstitialAd;
    Button btnsetting;
    int delayTime = 180;
    MediaPlayer sound10,sound20,sound30,sound40,sound50,sound60;
    public String sound_position;
    public boolean notifigation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        sound10 = MediaPlayer.create(this,R.raw.mashin_barber00);
        sound20 = MediaPlayer.create(this,R.raw.mashin_barber11);
        sound30 = MediaPlayer.create(this,R.raw.mashin_barber22);
        sound40 = MediaPlayer.create(this,R.raw.mashin_barber33);
        sound50 = MediaPlayer.create(this,R.raw.mashin_barber44);
        sound60 = MediaPlayer.create(this,R.raw.mashin_barber66);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        PreferenceManager.setDefaultValues(this,R.xml.preferences,false);
        SharedPreferences sharedPreferences = androidx.preference.PreferenceManager.getDefaultSharedPreferences(this);
        notifigation = sharedPreferences.getBoolean("notification",true);
        sound_position = sharedPreferences.getString("sound_position","Sound 1");


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
        if(notifigation == true){
            addNotification();
        }





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
                finish();
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
    private void addNotification() {
        createNotificationChannels();
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this, CHANNEL_1)
                        .setContentText("Set random message about application")
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("Trimmer Prank")
                        .setPriority(NotificationCompat.PRIORITY_HIGH)
                        .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                        .setAutoCancel(true);


        Notification notification = builder.build();

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(1, notification);
    }

    private void createNotificationChannels() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel1 = new NotificationChannel(CHANNEL_1, "Channel 1", NotificationManager.IMPORTANCE_HIGH);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel1);
        }
    }

}