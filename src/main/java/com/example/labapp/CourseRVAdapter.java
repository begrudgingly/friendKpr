package com.example.labapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CourseRVAdapter extends RecyclerView.Adapter<CourseRVAdapter.ViewHolder> {

    // variable for our array list and context
    private ArrayList<FriendModel> friendModelArrayList;
    private Context context;

    // constructor
    public CourseRVAdapter(ArrayList<FriendModel> friendModelArrayList, Context context) {
        this.friendModelArrayList = friendModelArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_rv_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FriendModel modal = friendModelArrayList.get(position);
        holder.friendName.setText(modal.getFriendName());
        holder.friendDob.setText(modal.getFriendDob());
        holder.friendPhone.setText(modal.getFriendPhone());
        holder.friendEmail.setText(modal.getFriendEmail());

    }

    @Override
    public int getItemCount() {
        // returning the size of our array list
        return friendModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView friendName, friendDob, friendPhone, friendEmail;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            friendName = itemView.findViewById(R.id.idName);
            friendDob = itemView.findViewById(R.id.idDob);
            friendPhone = itemView.findViewById(R.id.idPhone);
            friendEmail = itemView.findViewById(R.id.idEmail);
        }
    }
}
