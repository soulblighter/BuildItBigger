package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.udacity.gradle.builditbigger.backend.jokeApi.model.Joke;

import br.com.soulblighter.androidjokeslib.JokeActivity;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements EndpointsAsyncTask.GCMApiCallback {

    private ProgressBar mProgressBar;
    private Button mButton;
    private EndpointsAsyncTask mEndpointsAsyncTask;
    private InterstitialAd mInterstitialAd;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        AdView mAdView = (AdView) root.findViewById(R.id.adView);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);

        mProgressBar = root.findViewById(R.id.progressBar);
        mButton = root.findViewById(R.id.button);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tellJoke();
            }
        });

        mInterstitialAd = new InterstitialAd(getActivity());
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                // Load the next interstitial.
                mInterstitialAd.loadAd(new AdRequest.Builder().build());
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
        mEndpointsAsyncTask = null;
        if(joke!= null){
            Intent i = new Intent(getActivity(), JokeActivity.class);
            i.putExtra(JokeActivity.EXTRA_JOKE_TITLE, joke.getTitle());
            i.putExtra(JokeActivity.EXTRA_JOKE_ANSWER, joke.getAnswer());
            i.putExtra(JokeActivity.EXTRA_JOKE_QUESTION, joke.getQuestion());
            startActivity(i);
        } else {
            Toast.makeText(getContext(), R.string.fetch_error, Toast.LENGTH_SHORT).show();
        }
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }
    }
}
