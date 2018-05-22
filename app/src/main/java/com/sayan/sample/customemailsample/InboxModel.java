package com.sayan.sample.customemailsample;

import java.util.Date;

public class InboxModel {
    private String from;
    private String subject;
    private int messageNumber;
    private String sentDate;
    private int color = -1;

    public InboxModel(String from, String subject, String sentDate, int messageNumber) {
        this.from = from;
        this.subject = subject;
        this.messageNumber = messageNumber;
        this.sentDate=sentDate;
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

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getSentDate() {
        return sentDate;
    }
}
