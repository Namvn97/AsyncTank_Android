package com.example.namp5.asynctank_android;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import java.util.List;

/**
 * Created by namp5 on 11/26/2018.
 */

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.PhotoViewHolde>   {
    private List<Photo> mPhotos;

    public PhotoAdapter(List<Photo> mPhotos) {
        this.mPhotos = mPhotos;
    }

    @Override
    public PhotoViewHolde onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view  = inflater
                .inflate(R.layout.item_photo,parent,false);
        return new PhotoViewHolde(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoViewHolde holder, int position) {
        holder.loadImage(mPhotos.get(position));
    }

    @Override
    public int getItemCount() {
        return (mPhotos != null) ? mPhotos.size() : 0;
    }

    public class PhotoViewHolde extends RecyclerView.ViewHolder {
        private ImageView mImageView;
        public PhotoViewHolde(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.image_view);
        }
        private void loadImage (Photo photo) {
            Glide.with(itemView.getContext())
                    .load(photo.getmTitle())
                    .into(mImageView);
        }
    }
}
