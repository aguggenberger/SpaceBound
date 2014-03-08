package com.aguggenberger.spacebound.engine.services;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenManager;

import com.aguggenberger.spacebound.engine.Engine;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.utils.Disposable;

public class MusicPlayer implements Disposable{
    
    private MusicWrapper currentMusic;
    private TweenManager manager;
    private boolean muted;
    private float volume;
    
    public MusicPlayer(TweenManager manager, float volume, boolean muted) {
    	this.manager = manager;
        this.volume = volume;
        this.muted = muted;
    }
 
    public void pause() {
        if(currentMusic != null) {
            currentMusic.stop();
        }
    }
 
    public void dispose() {
        if(currentMusic != null) {
            currentMusic.dispose();
        }
    }
 
    public void play(final String musicAssetName, String location) {
    	this.play(new MusicWrapper(Engine.assets.get(musicAssetName, Music.class), location));
    }
    
    public void play(final MusicWrapper music) {
        if(currentMusic != null) {
            manager.killTarget(currentMusic);
            Tween.to(currentMusic, MusicTweenAccessor.VOLUME, 1f)
	            .target(0f)
	            .setCallbackTriggers(TweenCallback.ANY)
	            .setCallback(new TweenCallback() {
	            	@Override
					public void onEvent(int type, BaseTween<?> source) {
						 if((type & TweenCallback.COMPLETE) != 0) {
						 
	                        currentMusic.stop();                        
	                        currentMusic.dispose();                       
	 
	                        start(music);
	                    }					
					}
	            }).start(manager);
	            
            
        } else {
            start(music);
        }
        
    }
    
    private void start(MusicWrapper music) {
        this.currentMusic = music;
        music.setVolume(0f);
        music.setLooping(true);
        music.play();
        Tween.to(music, MusicTweenAccessor.VOLUME, 1f)
        	.target(1f)
        	.start(manager);
    }
 
    public void resume() {
        if(currentMusic != null) {
            currentMusic.play();
        }
    }
 
    public MusicWrapper getCurrentMusic() {
        return currentMusic;
    }
    
    public boolean isMuted(){
    	return this.muted;
    }
    
    public void setMuted(boolean value){
    	this.muted = value;
    }
    
    public void setVolume(float value){
    	this.volume = value;
    }

	public void stop() {
		this.currentMusic.stop();
	}
    
}
