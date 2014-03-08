package com.aguggenberger.spacebound;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class GameScreen implements Screen, GestureListener {
	private Stage stage;
	private SpaceGame spaceGame;

	public GameScreen() {
		stage = new Stage();
		spaceGame = new SpaceGame(SpaceBound.getWidth(), SpaceBound.getHeight());
		stage.addActor(spaceGame);
	}
	
	public void resize(int width, int height) {
		stage.setViewport(SpaceBound.getWidth(), SpaceBound.getHeight(), true);
		stage.getCamera().translate(-stage.getGutterWidth(), -stage.getGutterHeight(), 0);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		stage.act(delta);
		stage.draw();
	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(new GestureDetector(this));
	}

	@Override 
    public void hide() {
    	Gdx.input.setInputProcessor(null);
    }
	
	@Override
	public boolean fling(float velocityX, float velocityY, int button) {
		return false;
	}

	@Override public void resume() {}
	@Override public void pause() {}
	@Override public void dispose() {}
	
	@Override public boolean tap(float x, float y, int count, int button) {
		Gdx.app.log("SPACEBOUND", "taps: " + count);
		
		for (int i = 1; i <= count; i++) {
			movePlayer(x, y);								
		}
		
		return false;
	}
	
	private void movePlayer(float x, float y){
		if (x > spaceGame.playerShip.getX()) 
			spaceGame.playerShip.tryMoveRight();
		else if (x < spaceGame.playerShip.getX())
			spaceGame.playerShip.tryMoveLeft();
	}
	
	@Override public boolean touchDown(float x, float y, int pointer, int button) {
		
		Gdx.app.log("SPACEBOUND", "touch down: " + x + ", " + y + " (" + pointer + ")");
		return false;
		}
	@Override public boolean longPress(float x, float y) {return false;}
	@Override public boolean pan(float x, float y, float deltaX, float deltaY) {return false;}
	@Override public boolean zoom(float initialDistance, float distance) {return false;}
	@Override public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {return false;}

}
