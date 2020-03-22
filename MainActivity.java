package com.example.pythonquizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.icu.util.ICUUncheckedIOException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button truebutton;
    private Button falsebutton;
    private TextView questions;
    private ImageButton nextbutton;
    private ImageButton prebutton;
    private TextView qcounter;
    private TextView qscore;
    private static int sc=0;

    private int currentQuestionIndex=0;

    private Questions questionsbank[]=new Questions[]{
            new Questions(R.string.questions1,true),
            new Questions(R.string.questions2,true),
            new Questions(R.string.questions3,false),
            new Questions(R.string.questions4,true),
            new Questions(R.string.questions5,false)
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        truebutton=findViewById(R.id.true_button);
        falsebutton=findViewById(R.id.false_button);
        nextbutton=findViewById(R.id.next_button);
        prebutton=findViewById(R.id.pre_button);
        questions=findViewById(R.id.question_text_view);
        qcounter=findViewById(R.id.question_counter);
        qscore=findViewById(R.id.score);

        truebutton.setOnClickListener(this);
        falsebutton.setOnClickListener(this);
        nextbutton.setOnClickListener(this);
        prebutton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.false_button:
                checkAnswer(false);
                break;
            case R.id.true_button:
                checkAnswer(true);
                break;
            case R.id.next_button:
                if(currentQuestionIndex<=3) {
                    currentQuestionIndex = (currentQuestionIndex + 1) % questionsbank.length;
                    updateQuestions(currentQuestionIndex);
                }
                else if (currentQuestionIndex==4)
                {
                    updateQuestions(currentQuestionIndex);
                }
                break;
            case R.id.pre_button:
                if(currentQuestionIndex>0) {
                    currentQuestionIndex = (currentQuestionIndex - 1) % questionsbank.length;
                    updateQuestions(currentQuestionIndex);
                }
               /* else if(currentQuestionIndex==0){
                    currentQuestionIndex=questionsbank.length-1;
                    updateQuestions(currentQuestionIndex);
                }*/
        }

    }
    private void updateScore(int s)
    {
        if(sc<50) {
            sc = sc + s;
            qscore.setText("Your Score is::" + (sc) + "/" + "50");
        }
        else
        {
            sc=50;
            qscore.setText("Your Score is::" + (sc) + "/" + "50");

        }
    }
    private void updateQuestions(int currentQuestionIndex) {
        qcounter.setText((currentQuestionIndex+1)+"/"+questionsbank.length);
        questions.setText(questionsbank[currentQuestionIndex].getAnswerId());
    }

    private void checkAnswer(boolean choosecorrectoption) {
        boolean messege=questionsbank[currentQuestionIndex].isAnswerType();
        int  messageid;
        if(messege==choosecorrectoption)
        {
            updateScore(10);
            messageid=R.string.correct;
        }
        else
        {
            currentQuestionIndex=0;
            updateQuestions(currentQuestionIndex);
            sc=0;
            qscore.setText("Your Score is::" +(sc)+"/"+"50");
            messageid=R.string.incorrect;
        }
        Toast.makeText(MainActivity.this,messageid,Toast.LENGTH_SHORT).show();
    }

}
