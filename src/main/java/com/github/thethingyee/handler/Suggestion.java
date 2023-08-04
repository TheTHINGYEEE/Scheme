package com.github.thethingyee.handler;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;

import java.util.UUID;

public class Suggestion {

    private final String suggestion;
    private int upVotes;
    private int downVotes;
    private Message message;
    private final User suggestor;
    private final String uuid;

    public Suggestion(String suggestion, int upVotes, int downVotes, User suggestor, Message message) {
        this.suggestion = suggestion;
        this.upVotes = upVotes;
        this.downVotes = downVotes;
        this.suggestor = suggestor;
        uuid = UUID.randomUUID().toString().split("-")[0];
        this.message = message;
    }

    public Suggestion(String suggestion, User suggestor) {
        this.suggestion = suggestion;
        this.suggestor = suggestor;
        uuid = UUID.randomUUID().toString().split("-")[0];
    }

    public String getSuggestion() {
        return suggestion;
    }

    public int getUpVotes() {
        return upVotes;
    }

    public int getDownVotes() {
        return downVotes;
    }

    public User getSuggestor() {
        return suggestor;
    }

    public String getUUID() {
        return uuid;
    }

    public Message getMessage() {
        return message;
    }

    public void setUpVotes(int upVotes) {
        this.upVotes = upVotes;
    }

    public void setDownVotes(int downVotes) {
        this.downVotes = downVotes;
    }

    public void setMessage(Message message) {
        this.message = message;
    }
}
