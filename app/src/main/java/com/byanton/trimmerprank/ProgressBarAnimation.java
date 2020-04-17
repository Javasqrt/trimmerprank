package com.byanton.trimmerprank;

import android.content.Context;
import android.content.Intent;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ProgressBarAnimation extends Animation {
    private Context context;
    private ProgressBar pb;
    private TextView pbtext;
    private float from,to;


    public ProgressBarAnimation(Context context,ProgressBar progressBar, TextView textView, float from, float to){
        this.context = context;
        this.pb = progressBar;
        this.pbtext = textView;
        this.from = from;
        this.to = to;


    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        super.applyTransformation(interpolatedTime, t);

        float value = from + (to - from) * interpolatedTime;
        pb.setProgress((int) value);
        pbtext.setText((int)value + "%");

        if(value == to){
            context.startActivity(new Intent(context,HomeActivity.class));
        }
    }
}
