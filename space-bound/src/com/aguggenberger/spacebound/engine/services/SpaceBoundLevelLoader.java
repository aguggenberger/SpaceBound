package com.aguggenberger.spacebound.engine.services;

import com.aguggenberger.spacebound.resources.SpaceBoundAtlas;
import com.aguggenberger.spacebound.resources.SpaceBoundMusic;
import com.aguggenberger.spacebound.resources.SpaceBoundTexture;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class SpaceBoundLevelLoader implements ResourceLoader {
	@Override
	public void load(AssetManager assets) {
		assets.load(SpaceBoundMusic.LEVEL, Music.class);
		assets.load(SpaceBoundTexture.BACKGROUND_PURPLE, Texture.class);
		assets.load(SpaceBoundAtlas.GAME_ATLAS, TextureAtlas.class);
	}

	@Override
	public void unload(AssetManager assets) {
		assets.unload(SpaceBoundMusic.LEVEL);
		assets.unload(SpaceBoundTexture.BACKGROUND_PURPLE);
		assets.unload(SpaceBoundAtlas.GAME_ATLAS);
	}
}