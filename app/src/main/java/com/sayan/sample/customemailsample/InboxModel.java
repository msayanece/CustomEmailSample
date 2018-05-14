package com.sayan.sample.customemailsample;

public class InboxModel {
    private String from;
    private String subject;
    private int messageNumber;

    public InboxModel(String from, String subject, int messageNumber) {
        this.from = from;
        this.subject = subject;
        this.messageNumber = messageNumber;
    }

    public String getFrom() {
        return from;
    }

    public String getSubject() {
        return subject;
    }

    public int getMessageNumber() {
        return messageNumber;
    }
}
