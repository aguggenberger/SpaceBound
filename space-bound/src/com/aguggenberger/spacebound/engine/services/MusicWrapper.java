package com.aguggenberger.spacebound.engine.services;

import com.badlogic.gdx.audio.Music;

public class MusicWrapper {

	private float volume = 1f;
	
	private Music music;
	private String soundLocation;
	
	public MusicWrapper(Music music, String soundLocation) {		
		this.music = music;
		//this.music.setVolume(volume);		
		this.soundLocation = soundLocation;
	}
	
	public void setVolume(float volume) {
		this.volume = volume;
		music.setVolume(volume);
	}

	public void stop() {
		if (this.music.isPlaying())
			this.music.stop();		
	}

	public void dispose() {
		this.music.dispose();		
	}

	public void setLooping(boolean value) {
		this.music.setLooping(value);
		
	}

	public void play() {
		if (!this.music.isPlaying())
			this.music.play();
		
	}
	
	public void pause() {
		if (this.music.isPlaying())
			this.music.pause();		
	}

	public float getVolume() {
		return this.music.getVolume();
	}

}