package com.himebaugh.androidlibrary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class DisplayJokeActivity extends AppCompatActivity {

    public static final String EXTRA_JOKE_STRING = "extra_joke_string";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_joke);

        TextView jokeTextView = findViewById(R.id.jokeTextView);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(EXTRA_JOKE_STRING)) {
            jokeTextView.setText(intent.getStringExtra(EXTRA_JOKE_STRING));
        }

    }

}
