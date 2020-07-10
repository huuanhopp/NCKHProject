package com.example.nckhproject.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.nckhproject.model.Chat;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.nckhproject.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHoder> {
    List<Chat> chatsList;
    Context context;
    String imageUrl;

    public static final int MSG_TYPE_LEFT = 0;
    public static final int MSG_TYPE_RIGHT = 1;

    public MessageAdapter(List<Chat> userList, Context context, String imageUrl) {
        this.chatsList = userList;
        this.context = context;
        this.imageUrl = imageUrl;
    }


    @NonNull
    @Override
    public ViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == MSG_TYPE_LEFT){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_item_left,parent);
            return new MessageAdapter.ViewHoder(view);
        }
        else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_item_right,parent);
            return new MessageAdapter.ViewHoder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoder holder, int position) {
        Chat chat = chatsList.get(position);
        holder.showMessage.setText(chat.getMessage());
        if(imageUrl.equals("default")){
            holder.userProfileImage.setImageResource(R.mipmap.ic_launcher);
        }
        else {
            Glide.with(context).load(imageUrl).into(holder.userProfileImage);
        }
    }

    @Override
    public int getItemCount() {
        return chatsList.size();
    }
    public class ViewHoder extends RecyclerView.ViewHolder {
        public TextView showMessage;
        public CircleImageView userProfileImage;

        public ViewHoder(@NonNull View itemView) {
            super(itemView);
            showMessage = itemView.findViewById(R.id.showMessage);
            userProfileImage = itemView.findViewById(R.id.user_profile_pic);
        }
    }

    @Override
    public int getItemViewType(int position) {
        Chat chat = chatsList.get(position);
        if(chat.getSender().equals("ADMIN")){
            return MSG_TYPE_LEFT;
        }
        return MSG_TYPE_RIGHT;
    }
}
