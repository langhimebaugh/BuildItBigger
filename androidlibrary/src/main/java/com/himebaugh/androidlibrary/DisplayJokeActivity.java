package com.himebaugh.androidlibrary;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.NavUtils;
import android.widget.TextView;

public class DisplayJokeActivity extends AppCompatActivity {

    private final static String TAG = DisplayJokeActivity.class.getName();

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

        // Show the Up button in the action bar.
//        ActionBar actionBar = getSupportActionBar();
//        if (actionBar != null) {
//            actionBar.setDisplayHomeAsUpEnabled(true);
//        }

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        //getCallingActivity().
//
//        this.onBackPressed();
        

//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //getActivity().onBackPressed();
//                //this.onBackPressed();
//
//                Log.i(TAG, "onClick: ");
//            }
//        });

    }

}
