package com.example.musicapp.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.example.musicapp.constant.Constant;
import com.example.musicapp.model.Song;

import java.util.ArrayList;
import java.util.List;

public class MusicService extends Service implements MediaPlayer.OnPreparedListener, MediaPlayer.OnCompletionListener{
    public static List<Song> mListSongPlaying;
    public static int mSongPosition;
    public static int mLengthSong;
    public static boolean isPlaying;
    public static int mAction = -1;
    public static MediaPlayer mPlayer;

    @Override
    public void onCreate() {
        super.onCreate();
        if (mPlayer == null) {
            mPlayer = new MediaPlayer();
        }
    }

    public static void clearListSongPlaying() {
        if (mListSongPlaying != null) {
            mListSongPlaying.clear();
        } else {
            mListSongPlaying = new ArrayList<>();
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            if (bundle.containsKey(Constant.MUSIC_ACTION)) {
                mAction = bundle.getInt(Constant.MUSIC_ACTION);
            }
            if (bundle.containsKey(Constant.SONG_POSITION)) {
                mSongPosition = bundle.getInt(Constant.SONG_POSITION);
            }

            handleActionMusic(mAction);
        }

        return START_NOT_STICKY;
    }

    private void handleActionMusic(int action) {
        switch (action) {
            case Constant.PLAY:
                playSong();
                break;

            case Constant.PREVIOUS:
                prevSong();
                break;

            case Constant.NEXT:
                nextSong();
                break;

            case Constant.PAUSE:
                pauseSong();
                break;

            case Constant.RESUME:
                resumeSong();
                break;

            case Constant.CANNEL_NOTIFICATION:
                cannelNotification();
                break;

            default:
                break;
        }
    }

    private void nextSong() {
    }

    private void pauseSong() {
    }

    private void resumeSong() {
    }

    private void cannelNotification() {
    }

    private void prevSong() {
        
    }

    private void playSong() {
        String songUrl = mListSongPlaying.get(mSongPosition).getUrl();
        if (!songUrl.isEmpty()) {
            playMediaPlayer(songUrl);
        }
    }

    private void playMediaPlayer(String songUrl) {
        try {
            if (mPlayer.isPlaying()) {
                mPlayer.stop();
            }
            mPlayer.reset();
            mPlayer.setDataSource(songUrl);
            mPlayer.prepareAsync();
            initControl();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initControl() {
        mPlayer.setOnPreparedListener(this);
        mPlayer.setOnCompletionListener(this);
    }

    @Override
    public void onCompletion(MediaPlayer mp) {

    }

    @Override
    public void onPrepared(MediaPlayer mp) {

    }
}
