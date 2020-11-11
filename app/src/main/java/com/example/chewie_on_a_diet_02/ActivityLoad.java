package com.example.chewie_on_a_diet_02;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ProgressBar;

public class ActivityLoad extends AppCompatActivity {
        ProgressBar progressBar;

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_load);


            getWindow().setFlags(
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            );
            progressBar = findViewById(R.id.progressbar);

            progressBar.setMax(100);
            progressBar.setScaleY(3f);
            progressAnimation();
        }

        public void progressAnimation() {
            ProgresBarAnimation animation = new ProgresBarAnimation(this, progressBar, 0f, 100f);
            animation.setDuration(8000);
            progressBar.setAnimation(animation);
        }
}