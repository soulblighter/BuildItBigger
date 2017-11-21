package br.com.soulblighter.jokeslib;

public class Joke {
    public String title;
    public String question;
    public String answer;

    public Joke(String title, String questions, String answer) {
        this.title = title;
        this.question = questions;
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "Joke{"+title+", "+question+", "+answer+"}";
    }
}
