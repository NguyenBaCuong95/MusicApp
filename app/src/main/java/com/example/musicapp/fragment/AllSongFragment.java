package com.example.musicapp.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.musicapp.R;
import com.example.musicapp.databinding.FragmentAllSongBinding;


public class AllSongFragment extends Fragment {
    private FragmentAllSongBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAllSongBinding.inflate(inflater,container, false );
        // Inflate the layout for this fragment
        return binding.getRoot();
    }
}