package com.example.musicapp.constant;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.inputmethod.InputMethodManager;

import com.example.musicapp.service.MusicService;

public class GlobalFuntion {
    public static void hideSoftKeyboard(Activity activity) {
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) activity.
                    getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }
    }
    public static void startMusicService(Context ctx, int action, int songPosition) {
        Intent musicService = new Intent(ctx, MusicService.class);
        musicService.putExtra(Constant.MUSIC_ACTION, action);
        musicService.putExtra(Constant.SONG_POSITION, songPosition);
        ctx.startService(musicService);
    }
    public static <T >void startActivity(Context context, Class<T> clz) {
        Intent intent = new Intent(context, clz);
       // intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
