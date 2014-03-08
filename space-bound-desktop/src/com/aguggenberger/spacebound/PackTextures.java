package com.aguggenberger.spacebound;

import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.tools.imagepacker.TexturePacker2;
import com.badlogic.gdx.tools.imagepacker.TexturePacker2.Settings;

public class PackTextures {
	public static void main(String[] args) {
                Settings settings = new Settings();
                settings.filterMin = TextureFilter.Linear;
                settings.filterMag = TextureFilter.Linear;
                TexturePacker2.process(settings, "../dev-images", "../space-bound-android/assets/atlas", "images");
                
                TexturePacker2.Settings s = new TexturePacker2.Settings();
                TexturePacker2.process(s, "../dev-images/loading", "../space-bound-android/assets/atlas", "loading.pack");
        }
}
