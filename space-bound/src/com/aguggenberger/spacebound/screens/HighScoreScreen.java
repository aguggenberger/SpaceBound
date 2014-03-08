package com.aguggenberger.spacebound.screens;

import com.aguggenberger.spacebound.resources.SpaceBoundMusic;
import com.aguggenberger.spacebound.screens.base.UIScreen;
import com.badlogic.gdx.audio.Music;

public class HighScoreScreen extends UIScreen {

	@Override
	public void load() {
		super.load();
		assets.load(SpaceBoundMusic.GAME_MUSIC, Music.class);
	}

	@Override
	protected void unload() {
		super.unload();
		assets.unload(SpaceBoundMusic.GAME_MUSIC);
	}
	
	@Override
	public void show() {
		super.show();
		music.play(SpaceBoundMusic.GAME_MUSIC, this.getName());
	}
	
}
