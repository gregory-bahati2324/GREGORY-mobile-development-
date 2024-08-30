package com.example.registerloginapp;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.Firebase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ImageAdapter extends FirebaseRecyclerAdapter<user, ImageAdapter.ViewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public ImageAdapter(@NonNull FirebaseRecyclerOptions<user> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ImageAdapter.ViewHolder holder, int position, @NonNull user model) {

        holder.kitu.setText(model.getKitu());
        holder.phone.setText(model.getPhone());

        Picasso.get().load(model.getProfileImageUrl()).into(holder.profileImageUrl);

    }

    @NonNull
    @Override
    public ImageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.image_item, parent, false));

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView kitu, phone;
        ImageView profileImageUrl;

        public ViewHolder(@NonNull View itemview){
            super(itemview);

            kitu = itemview.findViewById(R.id.kitu);
            phone = itemview.findViewById(R.id.phone);
            profileImageUrl = itemview.findViewById(R.id.imageUrl);

        }
    }
}
