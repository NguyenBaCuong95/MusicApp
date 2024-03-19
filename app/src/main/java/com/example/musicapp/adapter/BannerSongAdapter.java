package com.example.musicapp.adapter;

import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.musicapp.databinding.ItemBannerBinding;
import com.example.musicapp.listener.OnClickSongBannerListener;
import com.example.musicapp.model.Song;

import java.util.List;

public class BannerSongAdapter extends RecyclerView.Adapter<BannerSongAdapter.BannerSong> {

    private List<Song> list;
    private OnClickSongBannerListener listener;

    public BannerSongAdapter(List<Song> list, OnClickSongBannerListener listener) {
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public BannerSong onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemBannerBinding binding = ItemBannerBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        binding.getRoot().setLayoutParams(new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        return new BannerSong(binding.getRoot(), binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BannerSong holder, int position) {
        Song song = list.get(position);
        holder.onBind(song);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class BannerSong extends RecyclerView.ViewHolder {
        private ItemBannerBinding binding;

        public BannerSong(@NonNull View itemView, ItemBannerBinding binding) {
            super(itemView);
            this.binding = binding;
        }

        public void onBind(Song song) {
            Glide.with(binding.imageView4.getContext())
                    .load(song.getImage())
                    .into(binding.imageView4);
            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(song);
                }
            });
        }
    }
}
