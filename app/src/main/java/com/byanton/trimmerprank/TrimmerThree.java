package com.byanton.trimmerprank;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import static android.content.Context.MODE_PRIVATE;


public class TrimmerThree extends Fragment {
    public InterstitialAd mInterstitialAd;
    boolean show;
    ImageButton btnoff,btnon;
    public String a;
    MediaPlayer sound10,sound20,sound30,sound40,sound50,sound60;
    public MediaPlayer sounds;
    @Nullable
    @Override
    public View onCreateView( LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_trimmer_three, container, false);
        btnoff = (ImageButton) root.findViewById(R.id.btnoff);
        btnon = (ImageButton) root.findViewById(R.id.btnon);
        sound10 = MediaPlayer.create(getContext(),R.raw.mashin_barber00);
        sound20 = MediaPlayer.create(getContext(),R.raw.mashin_barber11);
        sound30 = MediaPlayer.create(getContext(),R.raw.mashin_barber22);
        sound40 = MediaPlayer.create(getContext(),R.raw.mashin_barber33);
        sound50 = MediaPlayer.create(getContext(),R.raw.mashin_barber44);
        sound60 = MediaPlayer.create(getContext(),R.raw.mashin_barber66);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());

        a = sharedPreferences.getString("sound_position","Sound 1");
        SharedPreferences shpr = this.getActivity().getSharedPreferences("shpr",MODE_PRIVATE);
        show = shpr.getBoolean("show",true);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        MobileAds.initialize(getContext(), new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        mInterstitialAd = new InterstitialAd(getContext());
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        btnoff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnoff.setVisibility(View.GONE);
                btnon.setVisibility(View.VISIBLE);


                if (a != null) {
                    if (a.equals("Sound 1")) {
                        sounds = sound10;
                        sounds.setLooping(true);
                        sounds.start();

                    } else if (a.equals("Sound 2")) {
                        sounds = sound20;
                        sounds.setLooping(true);
                        sounds.start();
                    } else if (a.equals("Sound 3")) {
                        sounds = sound30;
                        sounds.setLooping(true);
                        sounds.start();
                    } else if (a.equals("Sound 4")) {
                        sounds = sound40;
                        sounds.setLooping(true);
                        sounds.start();
                    } else if (a.equals("Sound 5")) {
                        sounds = sound50;
                        sounds.setLooping(true);
                        sounds.start();
                    } else if (a.equals("Sound 6")) {
                        sounds = sound60;
                        sounds.setLooping(true);
                        sounds.start();
                    }
                    return;
                }
            }
        });
        btnon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnon.setVisibility(View.GONE);
                btnoff.setVisibility(View.VISIBLE);
                mInterstitialAd.setAdListener(new AdListener(){
                    @Override
                    public void onAdClosed() {
                        if (show) {
                            startIntentPopUp();
                        }
                        super.onAdClosed();
                    }

                    @Override
                    public void onAdLoaded() {
                        if(mInterstitialAd.isLoaded()){
                            mInterstitialAd.show();
                        }

                    }
                });
                stopPlaying();



                stopPlaying();




            }
        });


    }




    public void stopPlaying(){
        if(sounds.isLooping()){
            sounds.pause();
            sounds.seekTo(0);


        }

    }



    @Override
    public void onPause() {
        if(btnon.getVisibility() == View.VISIBLE){
            stopPlaying();
        }
        super.onPause();



    }
    public void startIntentPopUp() {
        Intent intent = new Intent(getContext(),PopUpRate.class);
        startActivity(intent);
        SharedPreferences shpr = this.getActivity().getSharedPreferences("shpr",MODE_PRIVATE);
        SharedPreferences.Editor editor = shpr.edit();
        editor.putBoolean("show",false);
        editor.apply();
    }



}
