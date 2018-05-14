package com.sayan.sample.customemailsample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;

public class MainActivity extends AppCompatActivity {

    //Declaring EditText
    private EditText editTextEmail;
    private EditText editTextSubject;
    private EditText editTextMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initializing the views
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextSubject = (EditText) findViewById(R.id.editTextSubject);
        editTextMessage = (EditText) findViewById(R.id.editTextMessage);
        ReadInbox sm = new ReadInbox(this);

        //Executing sendmail to send email
        sm.execute();
    }


    private void sendEmail() {
        //Getting content for email
        String email = editTextEmail.getText().toString().trim();
        String subject = editTextSubject.getText().toString().trim();
        String message = editTextMessage.getText().toString().trim();

        //Creating SendMail object
        SendMail sm = new SendMail(this, email, subject, message);

        //Executing sendmail to send email
        sm.execute();
    }

    public void sendMailOnClick(View view) {
//        new AlertDialog.Builder(this)
//                .setMessage("Kindly enter your email id")
//                .setTitle("Email details")
//                .setPositiveButton("Submit", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        sendEmail();
//                    }
//                })
//                .show();
//        sendEmail();

    }

    public void onInboxReadCompleted() {
        RecyclerView mRecyclerViewMailInbox = findViewById(R.id.mRecyclerViewMailInbox);
        mRecyclerViewMailInbox.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerViewMailInbox.setLayoutManager(layoutManager);
        mRecyclerViewMailInbox.setAdapter(new MailInboxRecyclerAdapter(InboxListSingleton.getInstance().getInboxModels()));
    }
}
