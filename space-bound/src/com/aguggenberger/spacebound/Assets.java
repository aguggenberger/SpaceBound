package com.aguggenberger.spacebound;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;

public class Assets {
	private static AssetManager manager;
	public static TextureAtlas atlas;
	public static Array<TextureRegion> meteors;
	public static Array<TextureRegion> powerups;
	public static TextureRegion meteorBrown_big1;
	public static TextureRegion meteorBrown_big2;
	public static TextureRegion meteorBrown_big3;
	public static TextureRegion meteorBrown_big4;
	public static TextureRegion meteorBrown_med1;
	public static TextureRegion meteorBrown_med3;
	public static TextureRegion meteorBrown_small1;
	public static TextureRegion meteorBrown_small2;
	public static TextureRegion meteorBrown_tiny1;
	public static TextureRegion meteorBrown_tiny2;
	public static TextureRegion spaceShip;
	public static Texture background;
	public static Music backgroundMusic;	
	public static Skin skin;

	public static void load() {
		manager = new AssetManager();
		manager.load("uiskin/uiskin.atlas", TextureAtlas.class);
		manager.load("texture/darkPurple.png", Texture.class);
		manager.load("atlas/images.atlas", TextureAtlas.class);
		manager.load("music/backgroundMusic1.mp3", Music.class);		
		manager.finishLoading();		
		
		FileHandle handle = Gdx.files.internal("uiskin/uiskin.json");
		//skin = new Skin(manager.get("uiskin.atlas", TextureAtlas.class));
		skin = new Skin(handle);
		background = manager.get("texture/darkPurple.png", Texture.class);
		background.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
		
		atlas = manager.get("atlas/images.atlas");
		
		spaceShip = atlas.findRegion("playerShip1_red");
		
		meteors = new Array<TextureRegion>();		
		meteorBrown_big1 = atlas.findRegion("meteorBrown_big1");
		meteors.add(meteorBrown_big1);
		meteorBrown_big2 = atlas.findRegion("meteorBrown_big2");
		meteors.add(meteorBrown_big2);
		meteorBrown_big3 = atlas.findRegion("meteorBrown_big3");
		meteors.add(meteorBrown_big3);
		meteorBrown_big4 = atlas.findRegion("meteorBrown_big4");
		meteors.add(meteorBrown_big4);
		meteorBrown_med1 = atlas.findRegion("meteorBrown_med1");
		meteors.add(meteorBrown_med1);
		meteorBrown_med3 = atlas.findRegion("meteorBrown_med3");
		meteors.add(meteorBrown_med3);
		meteorBrown_small1 = atlas.findRegion("meteorBrown_small1");
		meteors.add(meteorBrown_small1);
		meteorBrown_small2 = atlas.findRegion("meteorBrown_small2");
		meteors.add(meteorBrown_small2);
		meteorBrown_tiny1 = atlas.findRegion("meteorBrown_tiny1");
		meteors.add(meteorBrown_tiny1);
		meteorBrown_tiny2 = atlas.findRegion("meteorBrown_tiny2");
		meteors.add(meteorBrown_tiny2);
		powerups = new Array<TextureRegion>(); 
		powerups.add(atlas.findRegion("shield_silver"));
		
		backgroundMusic = manager.get("music/backgroundMusic1.mp3", Music.class); //Gdx.audio.newSound(Gdx.files.internal("backgroundMusic1.mp3"));
	}

	public static void dispose() {
		meteors.clear();
		atlas.dispose();
		backgroundMusic.dispose();
	}
}
