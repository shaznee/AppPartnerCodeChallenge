package com.apppartner.androidprogrammertest;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;


public class AnimationActivity extends ActionBarActivity
{
    private ImageView imgAppPartner;
    private TextView txtAnimationPrompt,txtAnimationBonus;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_animation);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txtAnimationPrompt = (TextView) findViewById(R.id.txtAnimationPrompt);
        txtAnimationBonus = (TextView) findViewById(R.id.txtAnimationBonus);
        imgAppPartner = (ImageView) findViewById(R.id.imgAppPartner);


        Typeface typeFaceAnimationPrompt = Typeface.createFromAsset(getAssets(),
                "fonts/Jelloween - Machinato ExtraLight.ttf");
        Typeface typeFaceAnimationBonus = Typeface.createFromAsset(getAssets(),
                "fonts/Jelloween - Machinato Bold Italic.ttf");

        txtAnimationPrompt.setTypeface(typeFaceAnimationPrompt);
        txtAnimationBonus.setTypeface(typeFaceAnimationBonus);

        imgAppPartner.setOnTouchListener(new View.OnTouchListener() {
            float dX, dY;
            @Override
            public boolean onTouch(View view, MotionEvent event) {

                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:{
                        dX = view.getX() - event.getRawX();
                        dY = view.getY() - event.getRawY();
                    }break;
                    case MotionEvent.ACTION_MOVE: {
                        view.animate()
                                .x(event.getRawX() + dX)
                                .y(event.getRawY() + dY)
                                .setDuration(0)
                                .start();
                    }break;
                    default:
                        return false;
                }
                return true;
            }
        });

    }

    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void onFadeClicked(View view) {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.animation);
        imgAppPartner.startAnimation(animation);
    }
}
