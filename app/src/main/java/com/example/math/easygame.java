package com.example.math;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class easygame extends AppCompatActivity
{
    TextView firstvalue;
    TextView secondvalue;
    TextView val1;
    TextView val2;
    TextView val3;
    TextView val4;
    TextView timer;
    TextView Score_Text;
    Button repeate,next;
    int second = 15;
    private int score;
    private boolean stopTimer= false;
    int result;
    Handler handler;
    Runnable runable;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easygame);
        firstvalue = findViewById(R.id.firstvalue);
        secondvalue = findViewById(R.id.secondvalue);
        val1 = findViewById(R.id.val1);
        val2 = findViewById(R.id.val2);
        val3 = findViewById(R.id.val3);
        val4 = findViewById(R.id.val4);
        timer = findViewById(R.id.timer);
        Score_Text = findViewById(R.id.score);
        repeate = findViewById(R.id.repeate);
        next = findViewById(R.id.next);

//        next.setEnabled(false);

//        timer();
//        easyquestion();

        val1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answerverify(val1);
            }
        });

        val2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answerverify(val2);
            }
        });
        val3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answerverify(val3);
            }
        });
        val4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answerverify(val4);
            }
        });
    }

    private void timer()
    {
        handler = new Handler();
        runable = new Runnable() {
            @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public void run() {
                timer.setText("TIME:" + second);
                second--;
                if (second < 0)
                {
//                    alert();
                    if (score < 30)
                    {
                        Toast.makeText(easygame.this,"Passing Marks 30",Toast.LENGTH_LONG).show();
                        second = 15;
                        stopTimer = true;
                        score = 0;
                        Score_Text.setText("SCORE:"+ score);
                        question();
                        timer();
                    }
                    else
                    {
                        Toast.makeText(easygame.this,"Level 1 Passed",Toast.LENGTH_LONG).show();
                        stopTimer = true;
                        next.setEnabled(true);
                        next.setBackground(getResources().getDrawable(R.drawable.green_button));
                        next.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                               startActivity(new Intent(easygame.this,Level2.class));
                                finish();
                            }
                        });
                    }

                }
                if (stopTimer == false)
                {
                    handler.postDelayed(this,1000);
                }
            }
        };
        handler.postDelayed(runable,1000);
    }

    private void question()
    {
        int min = 1;
        int max = 10;
        int firstval = new Random().nextInt(max) + 10;
        int secondval = new Random().nextInt(max) + 15;
        int thirdval = new Random().nextInt(max) + 20;

        int a = new Random().nextInt((max - min ) + 1) + min;
        int b = new Random().nextInt((max-min)+1)+ min;
        result = a * b;
        firstvalue.setText(String.valueOf(a));
        secondvalue.setText(String.valueOf(b));

        randomshow(firstval,secondval,thirdval);
    }

    private void randomshow(int firstval, int secondval, int thirdval)
    {
        int rand = new Random().nextInt(4);
        if (rand == 0)
        {
            val1.setText(String.valueOf(secondval));
            val2.setText(String.valueOf(thirdval));
            val3.setText(String.valueOf(firstval));
            val4.setText(String.valueOf(result));
        }
        if (rand == 1)
        {
            val1.setText(String.valueOf(result));
            val2.setText(String.valueOf(secondval));
            val3.setText(String.valueOf(thirdval));
            val4.setText(String.valueOf(firstval));
        }
        if (rand == 2)
        {
            val1.setText(String.valueOf(secondval));
            val2.setText(String.valueOf(result));
            val3.setText(String.valueOf(thirdval));
            val4.setText(String.valueOf(firstval));
        }
        if(rand == 3)
        {
            val1.setText(String.valueOf(secondval));
            val2.setText(String.valueOf(thirdval));
            val3.setText(String.valueOf(result));
            val4.setText(String.valueOf(firstval));
        }
        if (rand == 4)
        {
            val1.setText(String.valueOf(secondval));
            val2.setText(String.valueOf(thirdval));
            val3.setText(String.valueOf(firstval));
            val4.setText(String.valueOf(result));
        }
    }

    private void answerverify(TextView textView)
    {

        if (result == Integer.parseInt(textView.getText().toString()))
        {

            score +=5;
            Score_Text.setText("SCORE:"+ score);
        }
        else
        {
            Vibrator vibrator = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
            vibrator.vibrate(100);
        }
        question();
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        stopTimer = false;
//        finish();
        handler.removeCallbacks(runable);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        handler.removeCallbacks(runable);
    }

    @Override
    protected void onResume() {
        super.onResume();
        question();
        timer();
    }

}