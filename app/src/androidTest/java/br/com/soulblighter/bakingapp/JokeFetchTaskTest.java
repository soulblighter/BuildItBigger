package br.com.soulblighter.bakingapp;


import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.udacity.gradle.builditbigger.EndpointsAsyncTask;
import com.udacity.gradle.builditbigger.backend.jokeApi.model.Joke;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.Semaphore;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class JokeFetchTaskTest {

    @Test
    public void GCMEndpointsAsynkTaskTest() {
        final Semaphore semaphore = new Semaphore(0);
        EndpointsAsyncTask endpointsAsyncTask = new EndpointsAsyncTask(new EndpointsAsyncTask.GCMApiCallback() {
            public void onApiReturn(Joke joke) {
                assertNotNull(joke);
                if (joke != null){
                    assertNotNull(joke.getAnswer());
                    assertNotNull(joke.getQuestion());
                    assertNotNull(joke.getTitle());
                    semaphore.release();
                }
            }
        });
        endpointsAsyncTask.execute();
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
            fail();
        }
    }

}
