package com.udacity.gradle.builditbigger.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.himebaugh.javalibrary.JokeWizard;

import javax.inject.Named;

/** An endpoint class we are exposing */
@Api(
        name = "myApi",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "backend.builditbigger.gradle.udacity.com",
                ownerName = "backend.builditbigger.gradle.udacity.com",
                packagePath = ""
        )
)
public class MyEndpoint {

    /** A simple endpoint method that takes a name and says Hi back */
    @ApiMethod(name = "retrieveJoke")
    public MyBean retrieveJoke() {

        MyBean response = new MyBean();
        // ======================================
        // retrieves a joke from the Java library
        // ======================================
        JokeWizard myJokeWizard = new JokeWizard();
        response.setData(myJokeWizard.getJoke());

//        android.util.Log.i(TAG, "retrieveJoke: ");
//        android.util.Log.i(TAG, "retrieveJoke: ");

        return response;
    }

}
