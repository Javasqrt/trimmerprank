package com.byanton.trimmerprank;


import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.media.MediaPlayer;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Switch;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;



public class Setting extends AppCompatActivity {
    Handler mHandler;
    int stopPosition = 5000;
    public int position;
    public static final String CHANNEL_1 = "channel1";
    public MediaPlayer sound1, sound2, sound3, sound4, sound5, sound6;
    public Button settsound;
    public Switch notifigation;
    public static final String SHARED_PREF = "sharedPredf";
    public static final String SOUND_POSITION = "soundPosition";
    public static final String NOTIF_SWITCH = "notificationSwitch";
    public int sp;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.setting_layout);
        settsound = (Button) findViewById(R.id.btnsound);
        notifigation = (Switch) findViewById(R.id.switch1);
        sound1 = MediaPlayer.create(this, R.raw.mashin_barber00);
        sound2 = MediaPlayer.create(this, R.raw.mashin_barber11);
        sound3 = MediaPlayer.create(this, R.raw.mashin_barber22);
        sound4 = MediaPlayer.create(this, R.raw.mashin_barber33);
        sound5 = MediaPlayer.create(this, R.raw.mashin_barber44);
        sound6 = MediaPlayer.create(this, R.raw.mashin_barber66);
        mHandler = new Handler();
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
       final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Setting.this, android.R.layout.select_dialog_singlechoice);
        arrayAdapter.add("Sound 1");
        arrayAdapter.add("Sound 2");
        arrayAdapter.add("Sound 3");
        arrayAdapter.add("Sound 4");
        arrayAdapter.add("Sound 5");
        arrayAdapter.add("Sound 6");
        settsound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final AlertDialog.Builder alrt_bulder = new AlertDialog.Builder(Setting.this);
                alrt_bulder.setSingleChoiceItems(arrayAdapter, position, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        position = which;
                        switch (which) {
                            case 0:
                                soundPlay(sound1);
                                break;

                            case 1:
                                soundPlay(sound2);
                                break;

                            case 2:
                                soundPlay(sound3);
                                break;

                            case 3:
                                soundPlay(sound4);
                                break;

                            case 4:
                                soundPlay(sound5);
                                break;

                            case 5:
                                soundPlay(sound6);
                                break;


                        }


                    }
                })
                        .setCancelable(true)
                        .setPositiveButton(R.string.okay, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                 arrayAdapter.getItem(position);




                            }
                        })
                        .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = alrt_bulder.create();
                alert.setTitle(R.string.sound_effects);
                alert.show();

            }
        });


    }

    public void soundPlay(final MediaPlayer sounds) {
        sounds.start();
        sounds.setLooping(true);

        final Runnable stopAction = new Runnable() {
            @Override
            public void run() {
                sounds.stop();

            }
        };
        mHandler.postDelayed(stopAction,stopPosition);
    }





    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    private void addNotification() {
        createNotificationChannels();
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this, CHANNEL_1)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("Trimmer Prank")
                        .setPriority(NotificationCompat.PRIORITY_HIGH)
                        .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                        .setAutoCancel(false);


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


    public void  savePosition(){
        SharedPreferences sharedPreferences =  getSharedPreferences(SHARED_PREF,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(SOUND_POSITION,position);
        sp = sharedPreferences.getInt(SOUND_POSITION,0);
    }


}
