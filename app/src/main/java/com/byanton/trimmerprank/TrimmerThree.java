package com.byanton.trimmerprank;

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


public class TrimmerThree extends Fragment {
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


        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
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
        super.onPause();

    }


}
