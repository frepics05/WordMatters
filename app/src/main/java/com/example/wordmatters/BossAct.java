package com.example.wordmatters;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class BossAct extends AppCompatActivity {

    TextView txtScreen, txtQuestion, txtTitle, txtBtn;
    ImageView bigBoss;
    Animation smaltobig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boss);

        smaltobig = AnimationUtils.loadAnimation(this, R.anim.smalltobig);

        Typeface typeface =Typeface.createFromAsset(getAssets(), "fonts/FredokaOneRegular.ttf");

        txtQuestion = (TextView) findViewById(R.id.txt_Question);
        txtScreen = (TextView) findViewById(R.id.txt_Screen);
        txtTitle = (TextView) findViewById(R.id.textTitle);
        txtBtn = (TextView) findViewById(R.id.txtBtn);

        bigBoss = (ImageView) findViewById(R.id.bigBoss);
        bigBoss.startAnimation(smaltobig);

        txtQuestion.setTypeface(typeface);
        txtScreen.setTypeface(typeface);
        txtTitle.setTypeface(typeface);
        txtBtn.setTypeface(typeface);
    }
}
