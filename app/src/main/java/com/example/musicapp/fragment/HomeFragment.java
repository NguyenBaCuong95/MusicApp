package com.example.musicapp.fragment;

import android.app.ActionBar;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.musicapp.MyApplycation;
import com.example.musicapp.activity.PlayMusicActivity;
import com.example.musicapp.adapter.AllSongAdapter;
import com.example.musicapp.adapter.BannerSongAdapter;
import com.example.musicapp.constant.Constant;
import com.example.musicapp.constant.GlobalFuntion;
import com.example.musicapp.databinding.FragmentHomeBinding;
import com.example.musicapp.listener.OnClickSongBannerListener;
import com.example.musicapp.model.Song;
import com.example.musicapp.service.MusicService;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;
    private List<Song> listbanner;
    private List<Song> listSong;
    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (listbanner == null || listbanner.isEmpty()) {
                return;
            } else {
                if (binding.viewPager2.getCurrentItem() == listbanner.size() - 1) {
                    binding.viewPager2.setCurrentItem(0);
                    return;
                }
                else {
                binding.viewPager2.setCurrentItem(binding.viewPager2.getCurrentItem() + 1);}

            }
        }
    };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        getListSongFirebase("");
        initlistener();
        return binding.getRoot();
    }

    private void initlistener() {
binding.imageSearchsong.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        searchSong();
    }

    private void searchSong() {
        String strKey = binding.txtSearch.getText().toString().trim();
        if (listSong != null) {listSong.clear();}
        getListSongFirebase(strKey);
        GlobalFuntion.hideSoftKeyboard(getActivity());
    }
});
    }

    private void getListSongFirebase(String key) {
        MyApplycation.referenceSong().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listSong = new ArrayList<>();
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    Song song = snapshot1.getValue(Song.class);
                    if (key.isEmpty()) {
                        listSong.add(song);
                    } else {
                        if (song.getTitle().compareToIgnoreCase(key) == 0) {
                            listSong.add(song);
                        }
                    }
                }
                showBannerSong();
                showAllSong();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void showBannerSong() {
        listbanner = getListBanner();
        BannerSongAdapter adapter = new BannerSongAdapter(listbanner, new OnClickSongBannerListener() {
            @Override
            public void onClick(Song song) {
                goToSongDetail(song);
            }
        });
        binding.viewPager2.setAdapter(adapter);
        binding.circleIndicator3.setViewPager(binding.viewPager2);
        binding.viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                handler.removeCallbacks(runnable);
                handler.postDelayed(runnable, 2000);
            }
        });
    }

    private void goToSongDetail(Song song) {
        MusicService.clearListSongPlaying();
        MusicService.mListSongPlaying.add(song);
        MusicService.isPlaying = false;
        GlobalFuntion.startMusicService(getActivity(), Constant.PLAY, 0);
        GlobalFuntion.startActivity(getActivity(), PlayMusicActivity.class);
    }

    private List<Song> getListBanner() {
        listbanner = new ArrayList<>();
        for (int i = 0; i < listSong.size(); i++) {
            if (listSong.get(i).isFeatured() && listbanner.size() <= 5) {
                listbanner.add(listSong.get(i));
            }
        }
        return listbanner;
    }

    private void showAllSong() {
        AllSongAdapter adapter = new AllSongAdapter(listSong, new OnClickSongBannerListener() {
            @Override
            public void onClick(Song song) {
                goToSongDetail(song);
            }
        });
        binding.rcvPopularSong.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        binding.rcvPopularSong.setAdapter(adapter);
    }
}