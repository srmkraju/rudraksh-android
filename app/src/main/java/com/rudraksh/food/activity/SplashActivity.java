package com.rudraksh.food.activity;

import android.content.Intent;
import android.os.Build;
import android.os.CountDownTimer;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.rudraksh.food.R;

/**
 * Created by dell3 on 29/5/16.
 */

public class SplashActivity extends BaseActivity implements Animation.AnimationListener {

    private ImageView splashImageViewLogo;
    private TextView splashTexViewPalmBook;
    private Animation animFadeIn;

    @Override
    protected void initView() {
        splashImageViewLogo = (ImageView) findViewById(R.id.activity_splash_logo);
        splashTexViewPalmBook = (TextView) findViewById(R.id.activity_splash_tv_rudraksh_service);

        final Window window = SplashActivity.this.getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(SplashActivity.this.getResources().getColor(R.color.colorPrimaryDark));
        }


        animFadeIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);
        animFadeIn.setAnimationListener(this);

        animFadeIn = AnimationUtils.loadAnimation(SplashActivity.this, R.anim.fade_in);
        animFadeIn.setAnimationListener(SplashActivity.this);
        splashTexViewPalmBook.startAnimation(animFadeIn);

        new SplashDownCountDown(6000, 1000).start();
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_splash;
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {

    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

    private class SplashDownCountDown extends CountDownTimer {

        public SplashDownCountDown(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long milliSecond) {

        }

        @Override
        public void onFinish() {
            Intent intent;
            intent = new Intent(SplashActivity.this, MainActivity.class);
            navigateToNextActivity(intent, true);
        }
    }
}
