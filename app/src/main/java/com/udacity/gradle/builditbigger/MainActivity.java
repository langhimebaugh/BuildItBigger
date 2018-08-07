package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.himebaugh.androidlibrary.DisplayJokeActivity;
import com.himebaugh.javalibrary.JokeWizard;


public class MainActivity extends AppCompatActivity {

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

        // retrieves a joke from the Java library
        JokeWizard myJokeWizard = new JokeWizard();
        String wizardJoke = myJokeWizard.getJoke();

        Intent intent = new Intent(getBaseContext(), DisplayJokeActivity.class);
        // packages the joke as an intent extra
        intent.putExtra(DisplayJokeActivity.EXTRA_JOKE_STRING, wizardJoke);
        // launches the activity from the Android library
        startActivity(intent);
    }

}
