package com.byanton.trimmerprank;


import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;


public class Setting extends AppCompatActivity {
    Handler mHandler;
    int stopPosition = 5000;
    public int position;
    public static final String CHANNEL_1 = "channel1";
    public MediaPlayer sound1, sound2, sound3, sound4, sound5, sound6;
    public int sound_position;
    public Button share;
    public boolean notifigation;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.setting_layout);
        sound1 = MediaPlayer.create(this, R.raw.mashin_barber00);
        sound2 = MediaPlayer.create(this, R.raw.mashin_barber11);
        sound3 = MediaPlayer.create(this, R.raw.mashin_barber22);
        sound4 = MediaPlayer.create(this, R.raw.mashin_barber33);
        sound5 = MediaPlayer.create(this, R.raw.mashin_barber44);
        sound6 = MediaPlayer.create(this, R.raw.mashin_barber66);
        share = (Button) findViewById(R.id.share);
        mHandler = new Handler();
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if(findViewById(R.id.framepreferences) != null){
            if(savedInstanceState != null)
                return;

            getFragmentManager().beginTransaction().add(R.id.framepreferences,new SettingFragment()).commit();
        }
      SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        notifigation = sharedPreferences.getBoolean("notification",true);

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plane");
                String shareBody = "Your share body!";
                String shareSub = "Your share subject!";
                intent.putExtra(Intent.EXTRA_SUBJECT,shareSub);
                intent.putExtra(Intent.EXTRA_TEXT,shareBody);
                startActivity(Intent.createChooser(intent,"Share"));
            }
        });

    }







    @Override
    public boolean onSupportNavigateUp() {
        finish();
        Intent intent = new Intent(Setting.this, MainActivity.class);
        startActivity(intent);
        return true;
    }






}
