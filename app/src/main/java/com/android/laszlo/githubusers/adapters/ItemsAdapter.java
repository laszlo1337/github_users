package com.android.laszlo.githubusers.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.laszlo.githubusers.R;
import com.android.laszlo.githubusers.activities.RepositoriesActivity;
import com.android.laszlo.githubusers.model.User;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by laszlo on 2017-01-26.
 */

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ItemViewHolder> {

    private List<User> users;
    private Context context;


    public ItemsAdapter(List<User> users, Context context) {
        this.users = users;
        this.context = context;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.single_item_view, parent, false);
        return new ItemViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        final User currentUser = users.get(position);
        Glide.with(context).load(currentUser.getAvatarUrl()).into(holder.imageView);
        holder.textView.setText(currentUser.getLogin());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, RepositoriesActivity.class);
                intent.putExtra("login",currentUser.getLogin());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public void setData(ArrayList<User> users) {
        if(this.users==null) {
            this.users = users;
        }
        notifyDataSetChanged();
    }


    //inner
    public static class ItemViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView;
        View itemView;

        public ItemViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            this.imageView = (ImageView) itemView.findViewById(R.id.image_view);
            this.textView = (TextView) itemView.findViewById(R.id.item_text_view);
        }

    }
}
