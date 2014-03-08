package com.aguggenberger.spacebound;

import com.aguggenberger.spacebound.engine.EngineSettings;
import com.aguggenberger.spacebound.engine.GameExtended;
import com.aguggenberger.spacebound.resources.SpaceBoundFont;
import com.aguggenberger.spacebound.resources.SpaceBoundSFX;
import com.aguggenberger.spacebound.resources.SpaceBoundSkin;
import com.aguggenberger.spacebound.screens.LoadingScreen;
import com.aguggenberger.spacebound.screens.MainMenuScreen;
import com.aguggenberger.spacebound.screens.SplashScreen;
import com.aguggenberger.spacebound.screens.base.GameScreen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class SpaceBoundGame extends GameExtended {
	public static final String VERSION = "0.0.0.01 Pre-Alpha";
	public static final String LOG = "spacebound";
	
	public static final int WIDTH = 400;
	public static final int HEIGHT = 240;
	
	@Override
	protected EngineSettings setup() {
		
		//assets.load(SpaceBoundSFX.EXPLOSION_BIG, Sound.class);
		//assets.load(TyrianSfx.EXPLOSION_LONG, Sound.class);
		//assets.load(TyrianSfx.EXPLOSION_SHORT, Sound.class);
		
		//assets.load(TyrianSfx.HIT_ENEMY, Sound.class);
		//assets.load(TyrianSfx.HIT_PLAYER, Sound.class);
		//assets.load(TyrianSfx.HIT_SHIELD, Sound.class);
		
		//assets.load(TyrianSfx.MENU_ENTER_CLICK, Sound.class);
		assets.load(SpaceBoundSFX.MENU_ENTER_HIT, Sound.class);
		//assets.load(TyrianSfx.MENU_EXIT, Sound.class);
		//assets.load(TyrianSfx.MENU_SELECT, Sound.class);
		
		//assets.load(TyrianMusic.GAME_OVER_SOLO, Music.class);
		//assets.load(TyrianMusic.END_OF_LEVEL, Music.class);
		//assets.load(TyrianMusic.TYRIAN_THE_SOUND, Music.class);
		
		assets.load(SpaceBoundSkin.UI, Skin.class);
		assets.load(SpaceBoundFont.CONSOLAS, BitmapFont.class);
		
		return new EngineSettings(VERSION, LOG, WIDTH, HEIGHT);
	}

	@Override
	protected GameScreen getNextScreen(GameScreen screen) {
		
		if (screen == null) {
			return new SplashScreen();
		} else if (screen instanceof SplashScreen) {
			return new MainMenuScreen();
		} else if (screen instanceof MainMenuScreen) {
			MainMenuScreen menuScreen = (MainMenuScreen)screen;
			return menuScreen.getNextScreen();
		} else if (screen instanceof LoadingScreen) {
			LoadingScreen loadingScreen = (LoadingScreen)screen;
			return loadingScreen.getNextScreen();
		}
		
		return null;
	}

}
