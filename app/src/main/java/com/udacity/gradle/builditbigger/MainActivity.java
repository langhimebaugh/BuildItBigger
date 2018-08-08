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


public class MainActivity extends AppCompatActivity {

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

        // retrieves a joke from the Java library
//        JokeWizard myJokeWizard = new JokeWizard();
//        String wizardJoke = myJokeWizard.getJoke();

//        Intent intent = new Intent(getBaseContext(), DisplayJokeActivity.class);
//        // packages the joke as an intent extra
//        intent.putExtra(DisplayJokeActivity.EXTRA_JOKE_STRING, wizardJoke);
//        // launches the activity from the Android library
//        startActivity(intent);

        new EndpointsAsyncTask().execute(getBaseContext());
    }

    class EndpointsAsyncTask extends AsyncTask<Context, Void, String> {
        private MyApi myApiService = null;
        private Context context;

        @Override
        protected String doInBackground(Context... params) {
            if(myApiService == null) {  // Only do this once
                MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                        new AndroidJsonFactory(), null)
                        // options for running against local devappserver
                        // - 10.0.2.2 is localhost's IP address in Android emulator
                        // - turn off compression when running against local devappserver
                        .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                        // http://localhost:8080/
                        // 2.1.1. Testing device registration on a physical device
                        //.setRootUrl("http://<my-computer-address>:8080/_ah/api/")
                        .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                            @Override
                            public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                                abstractGoogleClientRequest.setDisableGZipContent(true);
                            }
                        });
                // end options for devappserver

                // 2.3. Testing against a deployed backend

                // REPLACE...

//                MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
//                        .setRootUrl("http://10.0.2.2:8080/_ah/api/") // 10.0.2.2 is localhost's IP address in Android emulator
//                        .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
//                            @Override
//                            public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
//                                abstractGoogleClientRequest.setDisableGZipContent(true);
//                            }
//                        });

                // WITH THIS....

//                MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
//                        .setRootUrl("https://android-app-backend.appspot.com/_ah/api/");

                // where android-app-backend corresponds to your own Project ID created in section 2.2.

                myApiService = builder.build();
            }

            context = params[0];
            //String name = params[0].second;

            try {
                return myApiService.retrieveJoke().execute().getData();
            } catch (IOException e) {
                return e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String result) {

            Log.i(TAG, "onPostExecute: " + result);

            Toast.makeText(context, result, Toast.LENGTH_LONG).show();
        }
    }
}
