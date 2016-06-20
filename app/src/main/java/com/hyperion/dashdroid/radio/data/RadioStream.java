package com.hyperion.dashdroid.radio.data;

/**
 * Data class for handling radio streams
 */
public class RadioStream {
	private String stream;
	private int bitrate;
	private String contentType;
	private int status;

	public RadioStream(String stream, int bitrate, String contentType, int status) {
		this.stream = stream;
		this.bitrate = bitrate;
		this.contentType = contentType;
		this.status = status;
	}

	public String getStream() {
		return stream;
	}

	public int getBitrate() {
		return bitrate;
	}

	public String getContentType() {
		return contentType;
	}

	public int getStatus() {
		return status;
	}
}
