package com.example.chewie_on_a_diet_02;

import android.content.Context;
import android.content.Intent;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ProgressBar;

public class ProgresBarAnimation extends Animation {
    private Context context;
    private ProgressBar progressBar;
    private float form;
    private float to;

    public ProgresBarAnimation(Context context, ProgressBar progressBar, float from, float to){
        this.context = context;
        this.progressBar = progressBar;
        this.form = from;
        this.to = to;
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        super.applyTransformation(interpolatedTime, t);
        float value = form + (to - form) * interpolatedTime;
        progressBar.setProgress((int)value);

        if (value == to){
            Intent intent = new Intent(context,ActivityMain.class);
            context.startActivity(intent);
        }
    }
}
