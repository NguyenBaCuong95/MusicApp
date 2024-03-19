package com.example.musicapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.musicapp.databinding.ItemSongPopularBinding;
import com.example.musicapp.listener.OnClickSongBannerListener;
import com.example.musicapp.model.Song;

import java.util.List;

public class AllSongAdapter extends RecyclerView.Adapter<AllSongAdapter.ItemSongPopular> {
    private List<Song> list;
    private OnClickSongBannerListener listener;

    public AllSongAdapter(List<Song> list, OnClickSongBannerListener listener) {
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ItemSongPopular onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemSongPopularBinding binding = ItemSongPopularBinding.inflate
                (LayoutInflater.from(parent.getContext()), parent, false);

        return new ItemSongPopular(binding.getRoot(), binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemSongPopular holder, int position) {
        Song song = list.get(position);
        holder.onBind(song);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ItemSongPopular extends RecyclerView.ViewHolder {
        private ItemSongPopularBinding binding;

        public ItemSongPopular(@NonNull View itemView, ItemSongPopularBinding binding) {
            super(itemView);
            this.binding = binding;
        }

        public void onBind(Song song) {
            Glide.with(binding.imageSong.getContext())
                    .load(song.getImage())
                    .into(binding.imageSong);
            binding.txtBaihat.setText(song.getTitle());
            binding.txtTacgia.setText("Artist: " + song.getArtist());
binding.getRoot().setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        listener.onClick(song);
    }
});
        }
    }
}
