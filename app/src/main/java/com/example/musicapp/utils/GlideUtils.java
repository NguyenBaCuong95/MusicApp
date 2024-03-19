package com.example.musicapp.utils;

import static android.os.Build.VERSION_CODES.R;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.music.R;

public class GlideUtils {

    public static void loadUrlBanner(String url, ImageView imageView) {
        if (StringUtil.isEmpty(url)) {

            return;
        }
        Glide.with(imageView.getContext())
                .load(url)

                .dontAnimate()
                .into(imageView);
    }

    public static void loadUrl(String url, ImageView imageView) {
        if (StringUtil.isEmpty(url)) {

            return;
        }
        Glide.with(imageView.getContext())
                .load(url)

                .dontAnimate()
                .into(imageView);
    }
}