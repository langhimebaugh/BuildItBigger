package com.udacity.gradle.builditbigger;

import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertNotNull;

@RunWith(AndroidJUnit4.class)
public class EndpointsAsyncTaskTest implements EndpointsAsyncTask.EndpointsAsyncTaskListener {

    private final static String TAG = EndpointsAsyncTaskTest.class.getName();

    // Verify that the AsyncTask is indeed loading jokes.
    // Add code to test that your Async task successfully retrieves a non-empty string.
    @Test
    public void nonEmptyJokeReceived() throws ExecutionException, InterruptedException {

        EndpointsAsyncTask task = new EndpointsAsyncTask(this);
        task.execute();

        // get result with get() method rather than onJokeReceived()
        String result = task.get();

        Log.i(TAG, "nonEmptyJokeReceived: result="+result);

        // Not Null
        assertNotNull(result);

        // Not Empty
        Assert.assertNotEquals("",result);

        // No Error has occurred
        // Will throw error if cannot connect to Endpoint and test will fail
        Assert.assertFalse(result.contains("Error"));
    }

    // This doesn't work with the test.
    @Override
    public void onJokeReceived(String result) {
        Log.i(TAG, "onJokeReceived: result="+result);
    }
}
