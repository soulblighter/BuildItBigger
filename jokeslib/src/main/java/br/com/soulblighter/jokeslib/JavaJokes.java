package br.com.soulblighter.jokeslib;

import java.util.Random;

public class JavaJokes {

    // https://www.rd.com/jokes/
    private static Joke[] jokes = {
            new Joke("Not Safe For Symphony", "Q: Why shouldn't you let kids watch big band performances on TV?", "A: Too much sax and violins."),
            new Joke("Beethoven Today", "Q: What is Beethoven doing now?", "A: De-composing"),
            new Joke("A Descending Chord", "Q: What do you get when you drop a piano down a mineshaft?", "A: A-flat minor"),
            new Joke("Country Music, Backwards", "Q: What happens if you play a county song backwards?", "A: Your wife returns to you, your dog comes back to life, and you get out of prison."),
            new Joke("New Age, Backwards", "Q: What do you get when you play New Age music backwards?", "A: New Age music."),
            new Joke("Music To-Go", "Q: Why do bagpipe players walk while they play?", "A: To get away from the noise."),
            new Joke("The Music Thieves", "Q: Why did the burglars decide to rob a music store?", "A: For the lute."),
            new Joke("Ludwig Van had a Farm", "Q: Why did Beethoven get rid of his chickens?", "A: All they said was, \"Bach, Bach, Bach.\""),
            new Joke("The Beethoven Diet", "Q: What was Beethoven's favorite fruit?", "A: BA-NA-NA-NAAAAA!"),
            new Joke("Lost Composure", "Q: Why couldn't the string quartet find their composer?", "A: He was Haydn"),
            new Joke("No C Notes to Spare", "Q: Why didn't Handel go shopping?", "A: Because he was Baroque."),
            new Joke("A Quick Joke", "Wanna hear a joke about a staccato?", "Never mind, it's too short."),
            new Joke("Top Brass", "Q: How do you fix a broken tuba?", "A: With a tuba glue."),
            new Joke("Stand and Deliver", "Q: How do you make a bandstand?", "A: Take away their chairs."),
            new Joke("Trumpetbeard the Dread", "Q: How are trumpets like pirates?", "A: They both murder in the high C's"),
            new Joke("Balloons On The Air", "Q: Which musical genre makes balloons terrified?", "A: Pop."),
            new Joke("Fishing For Chords", "Q: What's the difference between a piano and a tuna?", "A: You can tune a piano, but you can't piano a tuna."),
    };

    public Joke getJoke() {
        return jokes[new Random().nextInt(jokes.length-1)];
    }
}
