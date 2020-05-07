package com.byanton.trimmerprank;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class PopUpRate extends AppCompatActivity {
    EditText edrate;
    TextView txtview;
    ImageButton closepop;
    Button send;
    ImageView image;
    RatingBar rb;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.pop_up_rate);
        RelativeLayout relativeLayout = findViewById(R.id.rl_widget);
        relativeLayout.setBackgroundResource(0);

        edrate = (EditText)findViewById(R.id.etrate);
        txtview = (TextView)findViewById(R.id.textrate);
        closepop = (ImageButton)findViewById(R.id.closepop);
        send = (Button)findViewById(R.id.btnsendrequest);
        image = (ImageView)findViewById(R.id.okay);
        rb = (RatingBar)findViewById(R.id.ratingBar);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.BOTTOM;
        params.x = 0;
        params.y = 50;
        getWindow().setAttributes(params);

        getWindow().setLayout((int)(width*.8),(int)(height*.3));
        String url = ("http://www.google.com");
        final Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));


        closepop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopUpRate.this.finish();
            }
        });

        rb.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean fromUser) {
                int rating = (int)v;
                switch (rating){
                    case 1:
                        edrate.setVisibility(View.VISIBLE);
                    case 2:
                        edrate.setVisibility(View.VISIBLE);
                    case 3:
                        edrate.setVisibility(View.VISIBLE);
                }
                if(rating == 4 || rating == 5){
                    edrate.setVisibility(View.VISIBLE);
                    startActivity(i);
                }
            }
        });


        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edrate.setVisibility(View.GONE);
                image.setVisibility(View.VISIBLE);
                txtview.setText(R.string.thx_for_rate);
                Handler handler = new Handler();

                handler.postDelayed(new Runnable() {
                    public void run() {
                        PopUpRate.this.finish();
                    }
                }, 1000);






            }
        });

    }
}
