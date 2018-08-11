package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.himebaugh.androidlibrary.DisplayJokeActivity;
import com.himebaugh.javalibrary.JokeWizard;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;


public class MainActivity extends AppCompatActivity implements EndpointsAsyncTask.EndpointsAsyncTaskListener {

    private final static String TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    // Add a button to the main activity that retrieves a joke from the Java
    // library, packages the joke as an intent extra, and launches the activity from
    // the Android library.

    // Button in fragment_main.xml onClick calls tellJoke() method below...

    // There we go! Now we can launch the activity from our library, and it's
    // easy to reuse that activity between different apps!

    public void tellJoke(View view) {

        // Make the button kick off a task to retrieve a joke,
        // then launch the activity from your Android Library to display it.

        Log.i(TAG, "tellJoke: ");

        new EndpointsAsyncTask(this).execute();
    }

    @Override
    public void onJokeReceived(String jokeString) {

        Log.i(TAG, "onJokeReceived: ");

        Intent intent = new Intent(getBaseContext(), DisplayJokeActivity.class);
        // packages the joke as an intent extra
        intent.putExtra(DisplayJokeActivity.EXTRA_JOKE_STRING, jokeString);
        // launches the activity from the Android library
        startActivity(intent);
    }

}
