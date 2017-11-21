package com.udacity.gradle.builditbigger.backend;

import br.com.soulblighter.jokeslib.Joke;

/** The object model for the data we are sending through endpoints */
public class JokeBean {

    private Joke myData;

    public Joke getData() {
        return myData;
    }

    public void setData(Joke data) {
        myData = data;
    }
}
