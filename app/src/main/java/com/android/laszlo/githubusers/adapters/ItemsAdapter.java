package com.android.laszlo.githubusers.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.laszlo.githubusers.R;
import com.android.laszlo.githubusers.activities.RepositoriesActivity;
import com.android.laszlo.githubusers.model.Repository;
import com.android.laszlo.githubusers.model.User;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.android.laszlo.githubusers.activities.GitHubUsers.apiService;

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

                Call<List<Repository>> call = apiService.getUserRepos(currentUser.getLogin());
                call.enqueue(new Callback<List<Repository>>() {
                    @Override
                    public void onResponse(Call<List<Repository>> call, Response<List<Repository>> response) {
                        List<Repository> repos = new ArrayList<>();
                        if(response.code()==200) {

                            repos.addAll(response.body());
                        }
                        Toast.makeText(context,"status: "+response.code()+" repos: " + repos.size(),Toast.LENGTH_LONG).show();
                        StringBuilder sb = new StringBuilder();
                        for (int i = 0; i < repos.size(); i++){
                            sb.append(repos.get(i).getName()+"\n");
                        }
                        Log.d("all repos \n", sb.toString());
                    }

                    @Override
                    public void onFailure(Call<List<Repository>> call, Throwable t) {

                    }
                });

                Intent intent = new Intent(context, RepositoriesActivity.class);
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
