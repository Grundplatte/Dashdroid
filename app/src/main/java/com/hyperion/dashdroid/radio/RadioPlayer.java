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

public class RadioPlayer implements View.OnClickListener, AudioManager.OnAudioFocusChangeListener {
	private final String BUFFERING = "Buffering";

	private MediaPlayer mediaPlayer;
	private RadioChannel lastChannel;
	private AudioManager audioManager;

	private ImageButton playStopButton;
	private TextView radioNameView;
	private TextView radioStatus;

	private boolean hasAudioFocus;

	public RadioPlayer(View radioView) {
		audioManager = (AudioManager) RadioModuleActivity.getInstance().getSystemService(Context.AUDIO_SERVICE);
		radioNameView = (TextView) radioView.findViewById(R.id.radioChannel);
		radioStatus = (TextView) radioView.findViewById(R.id.radioStatus);
		playStopButton = (ImageButton) radioView.findViewById(R.id.playStopButton);
		playStopButton.setOnClickListener(this);
		mediaPlayer = null;
		hasAudioFocus = false;
	}

	private void initMediaPlayer(RadioChannel channel) {
		if(mediaPlayer != null) {
			stopRadio();
		}
		mediaPlayer = new MediaPlayer();
		mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
		playRadioChannelIntern(channel);

	}

	public void playRadioChannel(RadioChannel channel) {
		lastChannel = channel;

		if(hasAudioFocus == false) {
			int result = audioManager.requestAudioFocus(this, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN);
			if(result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
				Log.d(getClass().getSimpleName(), "playRadioChannel: request granted");
				initMediaPlayer(channel);
				hasAudioFocus = true;
			}
		} else
			initMediaPlayer(channel);
	}

	private void playRadioChannelIntern(RadioChannel channel) {
		if(channel.getRadioStreams().size() == 0) {
			Toast.makeText(RadioModuleActivity.getInstance(), "No streams found for selected Channel!", Toast.LENGTH_SHORT).show();
			return;
		}

		setPlayingInfo(channel.getName());

		try {
			mediaPlayer.setDataSource(channel.getRadioStreams().get(0).getStream());
		} catch(IOException e) {
			e.printStackTrace();
		}
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
				stopRadio();
				return false;
			}
		});
		mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
			@Override
			public void onPrepared(MediaPlayer mediaPlayer) {
				mediaPlayer.start();
				radioStatus.setText("Playing");
			}
		});
		mediaPlayer.prepareAsync();

	}

	public void stopRadio() {
		if(mediaPlayer != null) {
			if(mediaPlayer.isPlaying())
				mediaPlayer.stop();
			mediaPlayer.release();
			mediaPlayer = null;
			setStoppedInfo();
		}
	}


	@Override
	public void onClick(View v) {
		if(lastChannel == null) {

		} else if(mediaPlayer != null) {
			stopRadio();
		} else {
			playRadioChannel(lastChannel);
		}
	}

	private void setPlayingInfo(String channelName) {
		playStopButton.setBackgroundResource(R.drawable.ic_stop_circle_filled_black_48dp);
		radioNameView.setText(channelName);
		radioStatus.setText(BUFFERING);
	}

	private void setStoppedInfo() {
		playStopButton.setBackgroundResource(R.drawable.ic_play_circle_filled_black_48dp);
		radioStatus.setText("Stopped");
	}

	@Override
	public void onAudioFocusChange(int focusChange) {
		switch(focusChange) {
			case AudioManager.AUDIOFOCUS_GAIN:
				// resume playback
				if(mediaPlayer == null) initMediaPlayer(lastChannel);
				else if(!mediaPlayer.isPlaying()) mediaPlayer.start();
				mediaPlayer.setVolume(1.0f, 1.0f);
				hasAudioFocus = true;
				break;

			case AudioManager.AUDIOFOCUS_LOSS:
				// Lost focus for an unbounded amount of time: stop playback and release media player
				stopRadio();
				hasAudioFocus = false;
				break;

			case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
				// Lost focus for a short time, but we have to stop
				// playback. We don't release the media player because playback
				// is likely to resume
				if(mediaPlayer.isPlaying()) mediaPlayer.pause();
				hasAudioFocus = false;
				break;

			case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
				// Lost focus for a short time, but it's ok to keep playing
				// at an attenuated level
				if(mediaPlayer.isPlaying()) mediaPlayer.setVolume(0.1f, 0.1f);
				break;
		}
	}
}
