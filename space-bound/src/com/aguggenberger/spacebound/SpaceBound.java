package com.aguggenberger.spacebound;

import com.aguggenberger.spacebound.screens.MainMenuScreen;
import com.aguggenberger.spacebound.screens.OptionsScreen;
import com.aguggenberger.spacebound.screens.SplashScreen;
import com.aguggenberger.spacebound.screens.StartGameScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.FPSLogger;

public class SpaceBound extends Game {
	public static final String LOG = SpaceBound.class.getSimpleName();
	private FPSLogger fpsLogger;
	
	private GameScreen gameScreen;

    public StartGameScreen getStartGameScreen(){
    	return new StartGameScreen(this);
    }      	
	
	public static int getWidth(){
		return Gdx.graphics.getWidth();
	}
	
	public static int getHeight(){
		return Gdx.graphics.getHeight();
	}
		
	// Game methods

    @Override
    public void create()  {
        Gdx.app.log(SpaceBound.LOG, "Creating game" );
		Assets.load();
        fpsLogger = new FPSLogger();
        //setScreen(getSplashScreen());
		//gameScreen = new GameScreen();
		//setScreen(gameScreen);
    }

    @Override
    public void resize(int width, int height ){
        super.resize( width, height );
        Gdx.app.log( SpaceBound.LOG, "Resizing game to: " + width + " x " + height );
    }

    @Override
    public void render(){
        super.render();
        // output the current FPS
        fpsLogger.log();
    }

    @Override
    public void pause(){
        super.pause();
        Gdx.app.log(SpaceBound.LOG, "Pausing game");
    }

    @Override
    public void resume(){
        super.resume();
        Gdx.app.log(SpaceBound.LOG, "Resuming game");
    }

    @Override
    public void setScreen(Screen screen){
        super.setScreen(screen);
        Gdx.app.log(SpaceBound.LOG, "Setting screen: " + screen.getClass().getSimpleName());
    }

    @Override
    public void dispose(){
        Gdx.app.log(SpaceBound.LOG, "Disposing game");
        Assets.dispose();
		//gameScreen.dispose();
        super.dispose();
    }	
}
