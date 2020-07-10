package com.example.nckhproject.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.nckhproject.model.User;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.nckhproject.R;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHoder> {
    List<User> userList;
    Context context;

    public UserAdapter(List<User> userList, Context context) {
        this.userList = userList;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item,parent);
        return new UserAdapter.ViewHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoder holder, int position) {
        User user = userList.get(position);
        holder.userName.setText(user.getUserName());
        if(user.getImageURL().equals("default")){
            holder.userProfileImage.setImageResource(R.mipmap.ic_launcher);
        }
        else {
            Glide.with(context).load(user.getImageURL()).into(holder.userProfileImage);
        }
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }
    public class ViewHoder extends RecyclerView.ViewHolder {
        public TextView userName;
        public ImageView userProfileImage;

        public ViewHoder(@NonNull View itemView) {
            super(itemView);
            userName = itemView.findViewById(R.id.user_name);
            userProfileImage = itemView.findViewById(R.id.user_profile_pic);
        }
    }
}
