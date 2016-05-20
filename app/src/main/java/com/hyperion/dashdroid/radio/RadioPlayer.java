package com.hyperion.dashdroid.radio;

import android.media.MediaPlayer;

import java.io.IOException;

public class RadioPlayer extends MediaPlayer {
    private static RadioPlayer instance;

    public static RadioPlayer getInstance() {
        if(instance == null)
            instance = new RadioPlayer();

        return instance;
    }

    public RadioPlayer() {
        super();
    }

    public void playRadioChannel(RadioChannel channel) {
        if(isPlaying()){
            // reset
            stop();
            reset();
        }
        try {
            setDataSource(channel.getRadioStreams().get(0).getStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        prepareAsync();
        setOnPreparedListener(new OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                start();
            }
        });
    }

    public void stop() {
        if(isPlaying()){
            // reset
            stop();
            reset();
        }
    }

}
