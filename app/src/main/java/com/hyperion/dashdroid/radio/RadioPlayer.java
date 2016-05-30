package com.hyperion.dashdroid.radio;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.hyperion.dashdroid.R;
import com.hyperion.dashdroid.radio.data.RadioChannel;

import java.io.IOException;

public class RadioPlayer implements View.OnClickListener {
    private final String BUFFERING = "BUFFERING";

    private static RadioPlayer instance;
    private MediaPlayer mediaPlayer;
    private RadioChannel lastChannel;
    private AudioManager audioManager;

    private ImageButton playStopButton;
    private TextView radioNameView;
    private TextView radioStatus;

    private boolean preparing;

    public void setRadioView(View radioView) {
        radioNameView = (TextView)radioView.findViewById(R.id.radioChannel);
        radioStatus = (TextView)radioView.findViewById(R.id.radioStatus);
        playStopButton = (ImageButton)radioView.findViewById(R.id.playStopButton);
        playStopButton.setOnClickListener(this);
    }

    public static RadioPlayer getInstance() {
        if (instance == null)
            instance = new RadioPlayer();

        return instance;
    }

    public RadioPlayer() {
        playStopButton = null;
        radioStatus = null;
        radioNameView = null;
        lastChannel = null;
        preparing = false;
        mediaPlayer = new MediaPlayer();
        audioManager = (AudioManager)RadioModuleActivity.getInstance().getSystemService(Context.AUDIO_SERVICE);

    }

    public void playRadioChannel(RadioChannel channel) {

        if(preparing)
            return;

        if(mediaPlayer.isPlaying()) {
            // reset
            mediaPlayer.stop();
            mediaPlayer.reset();
        }

        if (channel.getRadioStreams().size() == 0) {
            Toast.makeText(RadioModuleActivity.getInstance(), "No streams found for selected Channel!", Toast.LENGTH_SHORT).show();
        } else {
            if(playStopButton == null || radioNameView == null) {
                Log.e(getClass().getSimpleName(), "playRadioChannel: radioView not set! Cant access buttons/text.");
            }
            else {
                playStopButton.setBackgroundResource(R.drawable.ic_stop_circle_filled_black_48dp);
                radioNameView.setText(channel.getName());
                radioStatus.setText(BUFFERING);
            }

            try {
                Log.e(getClass().getSimpleName(), "playRadioChannel: " + channel.getRadioStreams().get(0).getStatus());
                mediaPlayer.setDataSource(channel.getRadioStreams().get(0).getStream());
                lastChannel = channel;
            } catch (IOException e) {
                e.printStackTrace();
            }
            preparing = true;
            mediaPlayer.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
                @Override
                public void onBufferingUpdate(MediaPlayer mp, int percent) {
                    Log.d(getClass().getSimpleName(), "onBufferingUpdate: " + percent);
                }
            });
            mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                @Override
                public boolean onError(MediaPlayer mp, int what, int extra) {
                    Log.d(getClass().getSimpleName(), "onError: " + what);
                    return false;
                }
            });
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    preparing = false;
                        mediaPlayer.start();
                        radioStatus.setText("");
                }
            });
            mediaPlayer.prepareAsync();
        }
    }

    public void stopRadio() {
        if (mediaPlayer.isPlaying() || preparing) {
            if(playStopButton == null || radioNameView == null) {
                Log.e(getClass().getSimpleName(), "playRadioChannel: radioView not set! Cant access buttons/text.");
            }
            else {
                playStopButton.setBackgroundResource(R.drawable.ic_play_circle_filled_black_48dp);
                radioStatus.setText("Stopped");
            }

            // reset
            mediaPlayer.stop();
            mediaPlayer.reset();
            preparing = false;
        }
    }

    public void reset(){
        stopRadio();
        lastChannel = null;
    }

    @Override
    public void onClick(View v) {
        Log.d(getClass().getSimpleName(), "onClick: button");
        if(lastChannel == null){

        }
        else if(mediaPlayer.isPlaying() || preparing){
            stopRadio();
        }
        else {
            playRadioChannel(lastChannel);
        }
    }
}
