package com.sayan.sample.customemailsample;

import java.util.ArrayList;

public class InboxListSingleton {

    private static InboxListSingleton instance;
    private ArrayList<InboxModel> inboxModels;

    private InboxListSingleton() {
    }

    public static InboxListSingleton getInstance() {
        if (instance == null) {
            instance = new InboxListSingleton();
        }
        return instance;
    }



    public ArrayList<InboxModel> getInboxModels() {
        return inboxModels;
    }

    public void setInboxModels(ArrayList<InboxModel> inboxModels) {
        this.inboxModels = inboxModels;
    }
}
