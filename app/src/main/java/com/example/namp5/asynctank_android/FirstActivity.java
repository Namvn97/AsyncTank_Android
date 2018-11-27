package com.example.namp5.asynctank_android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class FirstActivity extends AppCompatActivity {
    public static final int NUMBER_WAIT = 1500;
    public static final int NUMBER_ONE_HUNDRED = 100;

    private ImageView mImageButton;
    private Thread mThread;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_activity_main);
        startAnimation();
    }

    private void startAnimation() {
        mImageButton = findViewById(R.id.image_fisrt_activity);
        Animation translate = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.iconapp);
        translate.reset();
        mImageButton.setAnimation(translate);
        mThread = new Thread() {
            @Override
            public void run() {
                super.run();
                int waited = 0;
                while (waited < NUMBER_WAIT) {
                    try {
                        sleep(NUMBER_ONE_HUNDRED);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    waited += NUMBER_ONE_HUNDRED;
                }
                FirstActivity.this.finish();
                Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        };
        mThread.start();
    }
}
