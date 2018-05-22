package com.sayan.sample.customemailsample;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.Random;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Admin on 04-09-2017.
 */

public class MailInboxRecyclerAdapter extends RecyclerView.Adapter<MailInboxRecyclerAdapter.ViewHolder> {

    private ArrayList<InboxModel> inboxModels;

    public MailInboxRecyclerAdapter(ArrayList<InboxModel> inboxModels) {
        this.inboxModels = inboxModels;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.child_recycler_inbox_mail, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        InboxModel inboxModel = inboxModels.get(position);
        String subject = inboxModel.getSubject();
        String from = inboxModel.getFrom();
//        message.getMessageNumber();
        holder.mailFrom.setText(from);
        holder.profileIcon.setText(from.substring(0,1));
        holder.profileContainer.setColorFilter(getRandomColor());
        if (subject.equalsIgnoreCase("")){
            holder.mailSubject.setText("[no subject]");
        }
        else
            holder.mailSubject.setText(subject);
    }

    private int getRandomColor() {
        Random rnd = new Random();
        return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
    }

    @Override
    public int getItemCount() {
        return inboxModels.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView mailFrom;
        private final TextView mailSubject;
        private  TextView profileIcon;
        private TextView profileBackground;
        private RelativeLayout iconContainer;
        private CircleImageView profileContainer;
        LinearLayout view;

        ViewHolder(View itemView) {
            super(itemView);
            mailFrom = itemView.findViewById(R.id.mailFrom);
            mailSubject = itemView.findViewById(R.id.mailSubject);
            profileIcon=itemView.findViewById(R.id.profile_icon);
            //profileBackground=itemView.findViewById(R.id.profileIcon);
            iconContainer=itemView.findViewById(R.id.icon_container);
            profileContainer=itemView.findViewById(R.id.profile_container);
        }
    }
}
