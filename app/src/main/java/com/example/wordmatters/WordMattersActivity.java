package com.example.wordmatters;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Type;
import java.security.Key;
import java.util.Random;

public class WordMattersActivity extends AppCompatActivity {

    private int pressCounter = 0;
    private int maxPressCounter = 4;
    private String[] keys = {"R", "I", "B", "D", "X"};
    private String textAnswer = "BIRD";
    TextView txtScreen, txtQuestion, txtTitle;
    Animation smallbigforth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_matters);

        smallbigforth = AnimationUtils.loadAnimation(this, R.anim.smallbigforth);

        keys = shuffleArray(keys);

        for (String key : keys) {
            addView(((LinearLayout) findViewById(R.id.layoutParent)), key, ((EditText) findViewById(R.id.editText)));
        }

        maxPressCounter = 4;
    }

    private String[] shuffleArray(String[] ar) {
        Random rnd = new Random();
        for (int i = ar.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            String a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }

        return ar;
    }

    private void addView(LinearLayout viewParent, final String text, final EditText editText) {
        LinearLayout.LayoutParams linearLayoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );

        linearLayoutParams.rightMargin = 30;

        final TextView textView = new TextView(this);

        textView.setLayoutParams(linearLayoutParams);
        textView.setBackground(this.getResources().getDrawable(R.drawable.bgpink));
        textView.setTextColor(this.getResources().getColor(R.color.colorPurple));
        textView.setGravity(Gravity.CENTER);
        textView.setText(text);
        textView.setClickable(true);
        textView.setFocusable(true);
        textView.setTextSize(32);

        Typeface typeface =Typeface.createFromAsset(getAssets(), "fonts/FredokaOneRegular.ttf");

        txtQuestion = (TextView) findViewById(R.id.txt_Question);
        txtScreen = (TextView) findViewById(R.id.txt_Screen);
        txtTitle = (TextView) findViewById(R.id.textTitle);

        txtQuestion.setTypeface(typeface);
        txtScreen.setTypeface(typeface);
        txtTitle.setTypeface(typeface);

        editText.setTypeface(typeface);
        textView.setTypeface(typeface);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pressCounter < maxPressCounter) {
                    if (pressCounter == 0) {
                        editText.setText("");

                        editText.setText(editText.getText().toString() + text);
                        textView.startAnimation(smallbigforth);
                        textView.animate().alpha(0).setDuration(300);
                        pressCounter++;

                        if (pressCounter == maxPressCounter) {
                            doValidate();
                        }
                    }
                }
            }
        });

        viewParent.addView(textView);
    }

    private void doValidate(){
        pressCounter = 0;

        EditText editText = findViewById(R.id.editText);
        LinearLayout linearLayout = findViewById(R.id.layoutParent);

        if (editText.getText().toString().equals(textAnswer)){
            //Toast.makeText(WordMattersActivity.this, "Correct", Toast.LENGTH_SHORT).show();
            Intent a = new Intent(WordMattersActivity.this, BossAct.class);
            startActivity(a);
            editText.setText("");
        }else {
            editText.setText("");
            Toast.makeText(WordMattersActivity.this, "Wrong", Toast.LENGTH_SHORT).show();

        }

        keys = shuffleArray(keys);
        linearLayout.removeAllViews();
        for (String key : keys) {
            addView(linearLayout, key, editText);
        }
    }
    
}
