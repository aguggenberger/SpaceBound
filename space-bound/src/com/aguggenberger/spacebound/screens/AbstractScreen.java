package com.aguggenberger.spacebound.screens;

import com.aguggenberger.spacebound.Assets;
import com.aguggenberger.spacebound.SpaceBound;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/**
 * The base class for all game screens.
 */

//AGTODO: REMOVE THIS, NOT NEEDED
public abstract class AbstractScreen implements Screen{
    protected final SpaceBound game;
    protected final BitmapFont font;
    protected final SpriteBatch batch;
    protected final Stage stage;

    public AbstractScreen(SpaceBound game){
        this.game = game;
        this.font = new BitmapFont();
        this.batch = new SpriteBatch();
        this.stage = new Stage( 0, 0, true );
    }
    
    protected String getName(){
        return getClass().getSimpleName();
    }

    @Override
    public void show(){
    	Gdx.app.log(SpaceBound.LOG, "Showing screen: " + getName() );
    }

    @Override
    public void resize(int width, int height ){
    	Gdx.app.log(SpaceBound.LOG, "Resizing screen: " + getName() + " to: " + width + " x " + height );
        // resize the stage
        stage.setViewport( width, height, true );
    }

    @Override
    public void render(float delta){
        // the following code clears the screen with the given RGB color (black)
        Gdx.gl.glClearColor( 0f, 0f, 0f, 1f );
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT );

        // update and draw the stage actors
        stage.act( delta );
        stage.draw();
    }

    @Override
    public void hide(){
    	Gdx.app.log(SpaceBound.LOG, "Hiding screen: " + getName() );
    }

    @Override
    public void pause(){
    	 Gdx.app.log(SpaceBound.LOG, "Pausing screen: " + getName() );
    }

    @Override
    public void resume(){
    	Gdx.app.log(SpaceBound.LOG, "Resuming screen: " + getName() );
    }

    @Override
    public void dispose(){
    	Gdx.app.log(SpaceBound.LOG, "Disposing screen: " + getName() );
    	stage.dispose();
        font.dispose();
        batch.dispose();
    }

	public Skin getSkin() {
		// TODO Auto-generated method stub
		return Assets.skin;
	}
}
