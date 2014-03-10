package com.aguggenberger.spacebound.screens.base;

import com.aguggenberger.spacebound.engine.Engine;
import com.aguggenberger.spacebound.engine.EngineSettings;
import com.aguggenberger.spacebound.engine.services.MusicPlayer;
import com.aguggenberger.spacebound.engine.services.PreferencesManager;
import com.aguggenberger.spacebound.engine.services.SoundManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Scaling;

public abstract class GameScreen implements Screen {

	protected final EngineSettings settings = Engine.settings;
	protected final PreferencesManager prefs = Engine.preferences;
	protected final SoundManager sounds = Engine.sounds;
	protected final MusicPlayer music = Engine.music;
	protected final AssetManager assets = Engine.assets;
	protected final SpriteBatch batch = Engine.batch;
	protected OrthographicCamera camera = Engine.camera;
	protected final BitmapFont font = Engine.font;
	protected Rectangle viewport = Engine.viewport;

	private boolean done;
	
	public boolean isDone() {
		return done;
	}

	public void setDone() {
		done = true;
	}

	public String getName() {
		return getClass().getSimpleName();
	}

	@Override
	public void render(float delta) {
		update(delta);

		camera.update();
		camera.apply(Gdx.gl10);
		
//		// set viewport
//        Gdx.gl.glViewport((int)  viewport.x, (int) viewport.y,
//                          (int) viewport.width, (int) viewport.height);

		batch.setProjectionMatrix(camera.combined);

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		draw(delta);
	}

	@Override
	public void resize(int width, int height) {
		log(String.format("Resizing screen to: %dx%d", width, height));
		
		Vector2 newVirtualRes= new Vector2(0f, 0f);
		Vector2 crop = new Vector2(width, height);

		// get new screen size conserving the aspect ratio
		newVirtualRes.set(Scaling.fit.apply((float)Engine.settings.VIRTUAL_WIDTH, (float)Engine.settings.VIRTUAL_HEIGHT, (float)width, (float)height));

		// ensure our scene is centered in screen
		crop.sub(newVirtualRes);
		//crop.scl(.5f);

		// build the viewport for further application
		viewport = new Rectangle(crop.x, crop.y, newVirtualRes.x, newVirtualRes.y);
		
//		 // calculate new viewport
//        float aspectRatio = (float)width/(float)height;
//        float scale = 1f;
//        Vector2 crop = new Vector2(0f, 0f); 
//        
//        
//        if(aspectRatio > Engine.settings.ASPECT_RATIO)
//        {
//            scale = (float)height/(float)Engine.settings.VIRTUAL_HEIGHT;
//            crop.x = (width - Engine.settings.VIRTUAL_WIDTH * scale)/2f;
//        }
//        else if(aspectRatio < Engine.settings.ASPECT_RATIO)
//        {
//            scale = (float)width/(float)Engine.settings.VIRTUAL_WIDTH;
//            crop.y = (height - Engine.settings.VIRTUAL_HEIGHT * scale)/2f;
//        }
//        else
//        {
//            scale = (float)width/(float)Engine.settings.VIRTUAL_WIDTH;
//        }
//
//        float w = (float)Engine.settings.VIRTUAL_WIDTH*scale;
//        float h = (float)Engine.settings.VIRTUAL_HEIGHT*scale;
//        viewport = new Rectangle(crop.x, crop.y, w, h);
        
	}

	@Override
	public void show() {		
		log("Showing screen: " + getName());
	}

	@Override
	public void hide() {
		log("Hiding screen: " + getName()); 
	}

	@Override
	public void pause() {
		log("Pausing screen: " + getName());
	}

	@Override
	public void resume() {
		log("Resuming screen: " + getName());
	}

	@Override
	public void dispose() {
		log("Disposing screen: " + getName());
		unload();
	}

	protected void log(String text) {
		Engine.log(text);
	}

	protected void update(float delta) {}
	protected abstract void draw(float delta);

	public void load() {}
	protected void unload() {}
}