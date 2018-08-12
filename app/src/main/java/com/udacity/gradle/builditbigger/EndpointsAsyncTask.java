package com.udacity.gradle.builditbigger;

import android.os.AsyncTask;
import android.util.Log;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;

public class EndpointsAsyncTask extends AsyncTask<Void, Void, String> {

    private final static String TAG = EndpointsAsyncTask.class.getName();

    private MyApi myApiService = null;

    // NOTE:
    // https://discussions.udacity.com/t/modifying-gce-starter-code-and-creating-async-task/473168/10
    // Define an interface with a method that you will call within the onPostExecute() and implement in the Activity.
    // Avoid passing in context and causing a leak.

    // Define a new interface EndpointsAsyncTaskListener that triggers a callback in the host activity
    EndpointsAsyncTaskListener mCallback;

    // EndpointsAsyncTaskListener interface, calls a method in the host activity named onJokeReceived
    public interface EndpointsAsyncTaskListener {
        void onJokeReceived(String result);
    }

    public EndpointsAsyncTask(EndpointsAsyncTaskListener mCallback) {
        this.mCallback = mCallback;
    }


    @Override
    protected String doInBackground(Void... voids) {
        if (myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });

            myApiService = builder.build();
        }

        try {
            return myApiService.retrieveJoke().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {

        Log.i(TAG, "onPostExecute: ");
        mCallback.onJokeReceived(result);
    }

}
