package com.Satway.gpstracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

//import android.animation.ObjectAnimator;
//import android.animation.PropertyValuesHolder;
//import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
//import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
//import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
//import android.widget.TextView;

import com.Satway.gpstracker.activities.MainActivity;
import android.content.SharedPreferences;

//splashscreen

public class SplashExample extends AppCompatActivity {

    ConstraintLayout constraintLayout;
    SharedPreferences sharedPreferences;
    ImageView sub, sub1, sub2, sub3;
    Animation fromleft, toright, fromrotate;
    static int flag=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_example);

        sharedPreferences = getSharedPreferences("cridential", Context.MODE_PRIVATE);
        sub = findViewById(R.id.imgicon2);
        sub1 = findViewById(R.id.imgicon3);
        sub2 = findViewById(R.id.textViewr);
        sub3 = findViewById(R.id.imageView8);
        constraintLayout = findViewById(R.id.layout_multiple);
        sub3.setVisibility(View.INVISIBLE);
        fromleft = AnimationUtils.loadAnimation(this, R.anim.left);
        toright = AnimationUtils.loadAnimation(this, R.anim.right);
        fromrotate = AnimationUtils.loadAnimation(this, R.anim.rotate);

        sub.setAnimation(fromleft);
        sub1.setAnimation(toright);
        sub2.setVisibility(View.GONE);

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {

                String username = sharedPreferences.getString("dealername", "");
                if (username.equals("")) {

                    Intent i = new Intent(SplashExample.this, MainActivity.class);
                    startActivity(i);
                    finish();
                } else {

                    Intent i = new Intent(SplashExample.this, DashboardActivity.class);
                    startActivity(i);
                    finish();

                }
            }
        }, 3600);

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                constraintLayout.setVisibility(View.INVISIBLE);
                sub3.setAnimation(fromrotate);
                sub3.setVisibility(View.VISIBLE);
//                sub2.setVisibility(View.VISIBLE);
            }
        }, 1100);

            new Handler().postDelayed(new Runnable() {

        @Override
        public void run() {

            sub2.setVisibility(View.VISIBLE);
        }
    }, 2800);
}
    }


