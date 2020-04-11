package com.byanton.trimmerprank;


import android.app.AlertDialog;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

public class TrimmerOne extends Fragment {
    MediaPlayer sounds = new MediaPlayer();
    ImageButton btnoff, btnon;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_trimmer_one, container, false);
        btnoff = (ImageButton) root.findViewById(R.id.btnoff);
        btnon = (ImageButton) root.findViewById(R.id.btnon);
        btnoff.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                btnon.setVisibility(View.VISIBLE);
                btnoff.setVisibility(View.GONE);
                Setting alrd_pos = new Setting();
                sounds.equals(alrd_pos.position);

                sounds.setLooping(true);
                sounds.start();

                btnon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        btnoff.setVisibility(View.VISIBLE);
                        btnon.setVisibility(View.GONE);
                        if (sounds.isLooping()) {
                            sounds.stop();

                        }
                    }
                });


            }

        });
        return root;
    }
}
