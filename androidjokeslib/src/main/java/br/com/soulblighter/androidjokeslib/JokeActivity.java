package br.com.soulblighter.androidjokeslib;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class JokeActivity extends AppCompatActivity {

    private TextView mJokeTitle;
    private TextView mJokeQuestion;
    private TextView mJokeAnswer;
    private Button mCloseButton;

    public final static String EXTRA_JOKE_TITLE = "joke_title";
    public final static String EXTRA_JOKE_QUESTION = "joke_question";
    public final static String EXTRA_JOKE_ANSWER = "joke_answer";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

        mJokeTitle = findViewById(R.id.jokeTitle);
        mJokeQuestion = findViewById(R.id.jokeQuestion);
        mJokeAnswer = findViewById(R.id.jopkeAnswer);
        mCloseButton = findViewById(R.id.closeButton);

        Intent i = getIntent();
        if(     i.hasExtra(EXTRA_JOKE_TITLE) &&
                i.hasExtra(EXTRA_JOKE_QUESTION) &&
                i.hasExtra(EXTRA_JOKE_ANSWER)) {
            mJokeTitle.setText(i.getStringExtra(EXTRA_JOKE_TITLE));
            mJokeQuestion.setText(i.getStringExtra(EXTRA_JOKE_QUESTION));
            mJokeAnswer.setText(i.getStringExtra(EXTRA_JOKE_ANSWER));
        } else {
            Toast.makeText(this, R.string.error_missing_extras, Toast.LENGTH_SHORT).show();
            finish();
        }

        mCloseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JokeActivity.this.finish();
            }
        });
    }
}
