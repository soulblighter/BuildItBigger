package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.IdlingResource;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.udacity.gradle.builditbigger.backend.jokeApi.model.Joke;

import br.com.soulblighter.androidjokeslib.JokeActivity;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements EndpointsAsyncTask.GCMApiCallback {

    private ProgressBar mProgressBar;
    private Button mButton;
    private EndpointsAsyncTask mEndpointsAsyncTask;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        mProgressBar = root.findViewById(R.id.progressBar);
        mButton = root.findViewById(R.id.button);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tellJoke();
            }
        });

        return root;
    }

    public void tellJoke() {
        if(mEndpointsAsyncTask == null) {
            mProgressBar.setVisibility(View.VISIBLE);
            mEndpointsAsyncTask = new EndpointsAsyncTask(this);
            mEndpointsAsyncTask.execute();
        }
    }

    public  void onApiReturn(Joke joke) {
        mProgressBar.setVisibility(View.GONE);
        mEndpointsAsyncTask= null;
        if(joke!= null){
            Intent i = new Intent(getActivity(), JokeActivity.class);
            i.putExtra(JokeActivity.EXTRA_JOKE_TITLE, joke.getTitle());
            i.putExtra(JokeActivity.EXTRA_JOKE_ANSWER, joke.getAnswer());
            i.putExtra(JokeActivity.EXTRA_JOKE_QUESTION, joke.getQuestion());
            startActivity(i);
        } else {
            Toast.makeText(getContext(), R.string.fetch_error, Toast.LENGTH_SHORT).show();
        }
    }
}
