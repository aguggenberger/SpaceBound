package com.aguggenberger.spacebound;

import com.aguggenberger.spacebound.SpaceBound;
import com.aguggenberger.spacebound.engine.Engine;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.tools.imagepacker.TexturePacker2;
import com.badlogic.gdx.tools.imagepacker.TexturePacker2.Settings;

public class Main {
	
	private final static float scale = 0.5f;
	
	public static void main(String[] args) {		
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "Space Bound";
		cfg.useGL20 = false;
		cfg.width = (int) Math.ceil(Engine.settings.VIRTUAL_WIDTH * scale);
		cfg.height = (int) Math.ceil(Engine.settings.VIRTUAL_HEIGHT * scale);
		
		Settings settings = new Settings();
        settings.filterMin = TextureFilter.Linear;
        settings.filterMag = TextureFilter.Linear;
        TexturePacker2.process(settings, "../dev-images", "../space-bound-android/assets", "images");
		
        TexturePacker2.Settings s = new TexturePacker2.Settings();
        TexturePacker2.process(s, "../dev-images/loading", "../space-bound-android/assets/atlas", "loading.pack");
		//new LwjglApplication(new SpaceBound(), cfg);
		new LwjglApplication(new SpaceBoundGame(), cfg);
	}
}
