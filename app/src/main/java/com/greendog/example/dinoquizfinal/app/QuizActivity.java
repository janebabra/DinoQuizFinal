package com.greendog.example.dinoquizfinal.app;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class QuizActivity extends ActionBarActivity {
    TextView mQuestionTextView;
    int mCurrentIndex;
    TrueFalse[] mAnswerKey = new TrueFalse[]{
            new TrueFalse(R.string.question_birds, true),
            new TrueFalse(R.string.question_jurassic, false),
            new TrueFalse(R.string.question_nessi, false),
            new TrueFalse(R.string.question_veggi, false),
            new TrueFalse(R.string.question_biggest, false)
    };
    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private Button mPrevButton;

    private void updateQuestion() {
        int question = mAnswerKey[mCurrentIndex].getQuestion();
        // int question = mQuestionBank[mCurrentIndex].getQuestion();
        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
        mQuestionTextView.setText(question);
    }

    private void checkAnswer(boolean userPressedTrue) {
        boolean answerIsTrue = mAnswerKey[mCurrentIndex].isTrueQuestion();
        int messageResId;
        if (userPressedTrue == answerIsTrue) {
            messageResId = R.string.correct_toast;
        } else {
            messageResId = R.string.incorrect_toast;
        }
        Toast.makeText(this, messageResId, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        mTrueButton = (Button) findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }
        });

        mFalseButton = (Button) findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });

        mNextButton = (Button) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mAnswerKey.length;
                updateQuestion();
            }
        });

        mPrevButton = (Button) findViewById(R.id.prev_button);
        mPrevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Alternative zur Buchlösung
                mCurrentIndex = (mCurrentIndex + (mAnswerKey.length - 1)) % mAnswerKey.length;
                updateQuestion();

            }
        });


    }
}
