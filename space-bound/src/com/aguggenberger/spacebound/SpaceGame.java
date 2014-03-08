package com.aguggenberger.spacebound;

import java.util.Iterator;

import javax.swing.plaf.metal.MetalBorders.PaletteBorder;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class SpaceGame extends Table {
	private InfiniteScrollBg background;
	private Array<Meteor> meteors;
	private Array<Powerup> powerups;
	private long lastMeteorTime = 0;
	private float meteorSpawnTime = 800000000f;	
	private long lastPowerupTime = 0;
	private float powerupSpawnTime = 800000000f * 3;
	
	private Music backgroundMusic;
	private int score = 0;
	private String ScoreString;
	private Label scoreLabel;
	
	public PlayerShip playerShip;
	
	public SpaceGame(float width, float height) {
		super();
		setColor(Color.RED);
		setBounds(0, 0, width, height);
		//setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		//setBounds(0, 0, 800, 480);
		setClip(true);
		debug();
		ScoreString = "Score: 0";
		scoreLabel = new Label(ScoreString, Assets.skin);
		scoreLabel.setWidth(200);
		scoreLabel.setWrap(true);
		scoreLabel.setPosition(0, getHeight() - 10 - scoreLabel.getHeight());
		//scoreLabel.setAlignment(Align.top | Align.left);
		
		backgroundMusic = Assets.backgroundMusic;
		backgroundMusic.setLooping(true);
		backgroundMusic.play();		
		
		background = new InfiniteScrollBg(getWidth(), getHeight());
		
		addActor(background);
		playerShip = new PlayerShip(this);
		addActor(playerShip);
		meteors = new Array<Meteor>();
		powerups = new Array<Powerup>();		
		
		addActor(scoreLabel);	
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);			
		
		if (TimeUtils.nanoTime() - lastMeteorTime > meteorSpawnTime) 
			spawnMeteor();
		if (TimeUtils.nanoTime() - lastPowerupTime > powerupSpawnTime) 
			spawnPowerup();
		
//		Actor hitActor = Assets.stage.hit(getX(), getY(), false);
//		if (hitActor != null){
//			if (hitActor instanceof PlayerShip){
//				//Gdx.app.log("SPACEBOUND", "Player Hit " + delta);
//			}
//			Gdx.app.log("SPACEBOUND", "Hit " + hitActor.getClass().getName() + " " + delta);
//			
//		}
		
		if (playerShip.getVelocity() != 0){
			if (playerShip.getVelocity() > 0 && 
					(playerShip.getBounds().x + playerShip.getWidth() > getWidth()))
				playerShip.stopShip();
			else if (playerShip.getVelocity() < 0 && 
					playerShip.getBounds().x + playerShip.getX() < 0)
				playerShip.stopShip();
		}
		
		checkMeteors();
		checkPowerups();
		
		scoreLabel.setText("Score: " + score);		
		scoreLabel.toFront();		
	}

	private void checkMeteors() {
		Iterator<Meteor> iter = meteors.iterator();
		while (iter.hasNext()) {
			Meteor meteor = iter.next();
			if (meteor.getBounds().x + meteor.getWidth() <= 0) {
				iter.remove();
				removeActor(meteor);
			}
			if (meteor.getBounds().overlaps(playerShip.getBounds())) {
		        iter.remove();
                //AGTODO: add player crash, game over
		        if (meteor.getX() > playerShip.getX()) {
                    if (meteor.getY() > playerShip.getY()) 
                    	meteor.crash(true, true);
                    else 
                    	meteor.crash(true, false);
                }
                else {
                    if (meteor.getY() > playerShip.getY()) meteor.crash(false, true);
                    else meteor.crash(false, false);
                }
		        score = 0;
		        playerShip.resetShip();
			}
		}
	}
	

	private void checkPowerups() {
		Iterator<Powerup> iter = powerups.iterator();
		while (iter.hasNext()) {
			Powerup powerup = iter.next();
			if (powerup.getBounds().x + powerup.getWidth() <= 0) {
				iter.remove();
				removeActor(powerup);
			}
			if (powerup.getBounds().overlaps(playerShip.getBounds())) {
		        iter.remove();
                //AGTODO: add player crash
		        score += 1;
                if (powerup.getX() > playerShip.getX()) {
                    if (powerup.getY() > playerShip.getY()) 
                    	powerup.crash(true, true);
                    else 
                    	powerup.crash(true, false);
                }
                else {
                    if (powerup.getY() > playerShip.getY()) powerup.crash(false, true);
                    else powerup.crash(false, false);
                }
			}
		}
	}

	private void spawnMeteor() {
		float y = MathUtils.random(0, getWidth());
		float x = Gdx.graphics.getHeight();
		Meteor meteor = new Meteor(x, y, this);
		meteors.add(meteor);
		addActor(meteor);		
		lastMeteorTime = TimeUtils.nanoTime();
		Gdx.app.log("SPACEBOUND", "Meteors: " + meteors.size);
	}
	
	private void spawnPowerup() {
		float y = MathUtils.random(0, getWidth());
		float x = Gdx.graphics.getHeight();
		Powerup powerup = new Powerup(x, y, this);
		powerups.add(powerup);
		addActor(powerup);		
		lastPowerupTime = TimeUtils.nanoTime();
		Gdx.app.log("SPACEBOUND", "powerup: " + powerups.size);
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		batch.setColor(Color.WHITE);
		super.draw(batch, parentAlpha);
	}
}
