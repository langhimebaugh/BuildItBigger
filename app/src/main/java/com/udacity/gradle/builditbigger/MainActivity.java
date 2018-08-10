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

// TODO: Step 4: Add Functional Tests
// Project contains connected tests to verify that the AsyncTask is indeed loading jokes.
// Add code to test that your Async task successfully retrieves a non-empty string. For a refresher on setting up Android tests, check out demo 4.09.

// All you need to do is to implement an Espresso Idling Resources test in order to verify that the AsyncTask is loading jokes.
// To answer your question, I've seen many projects succeed in what you are doing. They use a CountDown Latch (since Espresso Idling Resources are made for connected Android Tests) and implement a callback to notify the unit test when the call has been made, or they simply use code similar to :new myAsyncTask().execute().get(); which just will halt until the async task is finished. You can probably argue for a long while which test is "better" but they both work and they both get the job done for this use case.
// I apologize, Max G. is correct when he says that connected tests are required.
// Sounds good. Thanks for the info. I actually had used the CountDownLatch method to do my test, so maybe I'll have to change it up to use IdlingResources instead. I could get the test to run with CountdownLatch if the emulator was running, but it just would hang if in a regular JUnit test. Thanks for the feedback


// TODO: Step 5: Add a Paid Flavor
// Project contains paid/free flavors. The paid flavor has no ads and no unnecessary dependencies.
// Add free and paid product flavors to your app. Remove the ad (and any dependencies you can) from the paid flavor.

// TODO: Ads are required in the free version.

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
