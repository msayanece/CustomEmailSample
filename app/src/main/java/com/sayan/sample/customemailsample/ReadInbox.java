package com.sayan.sample.customemailsample;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Random;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;

public class ReadInbox extends AsyncTask<Void, Void, Message[]> {

    //Declaring Variables
    private Context context;

    //Progressdialog to show while sending email
    private ProgressDialog progressDialog;

    //Class Constructor
    public ReadInbox(Context context) {
        //Initializing variables
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        //Showing progress dialog while sending email
        progressDialog = ProgressDialog.show(context, "Sending message", "Please wait...", false, false);
    }

    @Override
    protected void onPostExecute(Message[] messages) {
        super.onPostExecute(messages);
        //Dismissing the progress dialog
        progressDialog.dismiss();
        //Showing a success message
//        Toast.makeText(context, "Message Sent", Toast.LENGTH_LONG).show();
        MainActivity mainActivity = (MainActivity) context;
        mainActivity.onInboxReadCompleted();
    }

    @Override
    protected Message[] doInBackground(Void... params) {
        Store store = null;
        Message[] messages = null;
        try {
            Properties props = new Properties();
            props.put("mail.pop3.host", "smtp.gmail.com");
            props.put("mail.pop3.port", "995");
            props.put("mail.pop3.starttls.enable", "true");
            Session session = Session.getInstance(props,
                    new javax.mail.Authenticator() {
                        //Authenticating the password
                        protected PasswordAuthentication getPasswordAuthentication() {
                            //Creating MimeMessage object
                            return new PasswordAuthentication(Config.EMAIL, Config.PASSWORD);
                        }
                    });
            Thread.sleep(1000);
            store = session.getStore("pop3s");
            store.connect("pop.gmail.com", Config.EMAIL, Config.PASSWORD);
            Folder emailFolder = store.getFolder("INBOX");
            emailFolder.open(Folder.READ_ONLY);
            messages = emailFolder.getMessages(1, 20);
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    System.in));
            ArrayList<InboxModel> inboxModels = new ArrayList<>();
            for (int i = 0, n = messages.length; i < n; i++) {
                Message message = messages[i];
                inboxModels.add(new InboxModel(message.getFrom()[0].toString(), message.getSubject(),message.getSentDate().toString(), message.getMessageNumber()));
                System.out.println("---------------------------------");
                System.out.println("Email Number " + (i + 1));
                System.out.println("Subject: " + message.getSubject());
                System.out.println("From: " + message.getFrom()[0]);
                System.out.println("Text: " + message.getMessageNumber());
                System.out.println("Sent Date: " + message.getSentDate());

//                    writePart(message, i);
//                    String line = reader.readLine();
//                    if ("YES".equals(line)) {
//                        message.writeTo(System.out);
//                    } else if ("QUIT".equals(line)) {
//                        break;
//                    }
            }
            InboxListSingleton inboxListSingleton = InboxListSingleton.getInstance();
            inboxListSingleton.setInboxModels(inboxModels);
            //close the store and folder objects
            emailFolder.close(false);
            store.close();

        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//            } catch (IOException e) {
//                e.printStackTrace();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
        return messages;
    }


//    public static void writeMessage(Part p, int messageNumber) throws Exception {
//        if (p instanceof Message)
//            writeEnvelope((Message) p);
//    }
//
//
//    /*
//     * This method checks for content-type
//     * based on which, it processes and
//     * fetches the content of the message
//     */
//    public static void writePart(Part p, int messageNumber) throws Exception {
//        if (p instanceof Message)
////            Call methos writeEnvelope
//            writeEnvelope((Message) p);
//
//        System.out.println("----------------------------");
//        System.out.println("CONTENT-TYPE: " + p.getContentType());
//
//        //check if the content is plain text
//        if (p.isMimeType("text/plain")) {
//            System.out.println("This is plain text");
//            System.out.println("---------------------------");
//            System.out.println((String) p.getContent());
//        }
//        //check if the content has attachment
//        else if (p.isMimeType("multipart/*")) {
//            System.out.println("This is a Multipart");
//            System.out.println("---------------------------");
//            Multipart mp = (Multipart) p.getContent();
//            int count = mp.getCount();
//            for (int i = 0; i < count; i++)
//                writePart(mp.getBodyPart(i), messageNumber);
//        }
//        //check if the content is a nested message
//        else if (p.isMimeType("message/rfc822")) {
//            System.out.println("This is a Nested Message");
//            System.out.println("---------------------------");
//            writePart((Part) p.getContent(), messageNumber);
//        }
//        //check if the content is an inline image
//        //TODO uncomment this
////        else if (p.isMimeType("image/jpeg")) {
////            System.out.println("--------> image/jpeg");
////            Object o = p.getContent();
////
////            InputStream x = (InputStream) o;
////            // Construct the required byte array
////            System.out.println("x.length = " + x.available());
////            while ((i = (int) ((InputStream) x).available()) > 0) {
////                int result = (int) (((InputStream) x).read(bArray));
////                if (result == -1)
////                    int i = 0;
////                byte[] bArray = new byte[x.available()];
////
////                break;
////            }
////            FileOutputStream f2 = new FileOutputStream("/tmp/image.jpg");
////            f2.write(bArray);
////        }
//        else if (p.getContentType().contains("image/")) {
//            System.out.println("content type" + p.getContentType());
//            File f = new File("image" + new Date().getTime() + ".jpg");
//            DataOutputStream output = new DataOutputStream(
//                    new BufferedOutputStream(new FileOutputStream(f)));
//            com.sun.mail.util.BASE64DecoderStream test =
//                    (com.sun.mail.util.BASE64DecoderStream) p
//                            .getContent();
//            byte[] buffer = new byte[1024];
//            int bytesRead;
//            while ((bytesRead = test.read(buffer)) != -1) {
//                output.write(buffer, 0, bytesRead);
//            }
//        }
//        else {
//            Object o = p.getContent();
//            if (o instanceof String) {
//                System.out.println("This is a string");
//                System.out.println("---------------------------");
//                System.out.println((String) o);
//            }
//            else if (o instanceof InputStream) {
//                System.out.println("This is just an input stream");
//                System.out.println("---------------------------");
//                InputStream is = (InputStream) o;
//                is = (InputStream) o;
//                int c;
//                while ((c = is.read()) != -1)
//                    System.out.write(c);
//            }
//            else {
//                System.out.println("This is an unknown type");
//                System.out.println("---------------------------");
//                System.out.println(o.toString());
//            }
//        }
//    }
//
//    /*
//     * This method would print FROM,TO and SUBJECT of the message
//     */
//    public static void writeEnvelope(Message m) throws Exception {
//        System.out.println("This is the message envelope");
//        System.out.println("---------------------------");
//        Address[] a;
//
//        // FROM
//        if ((a = m.getFrom()) != null) {
//            for (int j = 0; j < a.length; j++)
//                System.out.println("FROM: " + a[j].toString());
//        }
//
//        // TO
//        if ((a = m.getRecipients(Message.RecipientType.TO)) != null) {
//            for (int j = 0; j < a.length; j++)
//                System.out.println("TO: " + a[j].toString());
//        }
//
//        // SUBJECT
//        if (m.getSubject() != null)
//            System.out.println("SUBJECT: " + m.getSubject());
//
//    }
}
