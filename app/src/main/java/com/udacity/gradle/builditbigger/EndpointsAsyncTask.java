package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.os.AsyncTask;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.jokeApi.JokeApi;
import com.udacity.gradle.builditbigger.backend.jokeApi.model.Joke;

import java.io.IOException;

public class EndpointsAsyncTask extends AsyncTask<Context, Void, Joke> {
    private static JokeApi mJokeApiService = null;
    private GCMApiCallback mCallback;

    public interface GCMApiCallback {
        void onApiReturn(Joke joke);
    }

    public EndpointsAsyncTask(GCMApiCallback callback) {
        mCallback = callback;
    }

    @Override
    protected Joke doInBackground(Context... params) {
        if(mJokeApiService == null) {  // Only do this once
            JokeApi.Builder builder = new JokeApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver

            mJokeApiService = builder.build();
        }

        try {
            return mJokeApiService.getJoke().execute().getData();
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    protected void onPostExecute(Joke Joke) {
        if(mCallback != null) {
            mCallback.onApiReturn(Joke);
            mCallback = null;
        }
    }
}
