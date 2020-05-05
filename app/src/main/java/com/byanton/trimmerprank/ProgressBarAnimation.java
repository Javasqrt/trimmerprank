package com.byanton.trimmerprank;

import android.content.Context;
import android.content.Intent;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.InterstitialAd;

public class ProgressBarAnimation extends Animation {
    private Context context;
    private ProgressBar pb;
    private TextView pbtext;
    private float from,to;
    private InterstitialAd mInterstitialAd;


    public ProgressBarAnimation(Context context,ProgressBar progressBar, TextView textView, float from, float to,InterstitialAd mInterstitialAd){
        this.context = context;
        this.pb = progressBar;
        this.pbtext = textView;
        this.from = from;
        this.to = to;
        this.mInterstitialAd = mInterstitialAd;

    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        super.applyTransformation(interpolatedTime, t);

        float value = from + (to - from) * interpolatedTime;
        pb.setProgress((int) value);
        pbtext.setText((int)value + "%");

        if(value == to){
            mInterstitialAd.setAdListener(new AdListener() {
                @Override
                public void onAdFailedToLoad(int i) {
                    super.onAdFailedToLoad(i);
                    context.startActivity(new Intent(context,HomeActivity.class));
                }

                @Override
                public void onAdClosed() {
                    super.onAdClosed();
                    context.startActivity(new Intent(context,HomeActivity.class));
                }

                @Override
                public void onAdLoaded() {
                    super.onAdLoaded();
                    if(mInterstitialAd.isLoaded()) {
                        mInterstitialAd.show();
                    }
                }
            });


        }
    }
}
