package com.aguggenberger.spacebound;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Meteor extends Actor {
	private Rectangle bounds = new Rectangle();
	private TextureRegion textureRegion;
	
	public Meteor(float x, float y, SpaceGame spaceGame) {

		textureRegion = Assets.meteors.random();
		
		if (textureRegion == null){
			textureRegion = null;
		}
		
		setWidth(textureRegion.getRegionWidth());
		setHeight(textureRegion.getRegionHeight());
		
		setPosition(MathUtils.random(0, spaceGame.getWidth()), spaceGame.getHeight());//), y - getHeight()/2);
		
		addAction(sequence(moveTo(getX(), -getHeight(), MathUtils.random(4.0f, 6.0f)), removeActor()));
		
		//add random rotation
		addAction(forever(sequence(rotateBy(MathUtils.random(-360f, 360f), MathUtils.random(1.0f, 6.0f)))));	
	}
	
	@Override
	public void act(float delta){
		super.act(delta);		
		updateBounds();
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		batch.setColor(getColor().r, getColor().g, getColor().b, getColor().a);		
		batch.draw(textureRegion, getX(), getY(), getWidth()/2, getHeight()/2, getWidth(), getHeight(), 1, 1, getRotation());
	}
	
	private void updateBounds() {
		bounds.set(getX(), getY(), getWidth(), getHeight());
	}
	
	public void crash(boolean front, boolean above) {
		clearActions();
		addAction(fadeOut(1f));
		if (front && above) 
			addAction(sequence(parallel(rotateBy(-360, 1.5f), moveBy(200, 200, 1.5f)), removeActor()));
		if (front && !above) 
			addAction(sequence(parallel(rotateBy(360, 1.5f), moveBy(200, -200, 1.5f)), removeActor()));
		if (!front && above) 
			addAction(sequence(parallel(rotateBy(360, 1.5f), moveBy(-200, 200, 1.5f)), removeActor()));
		if (!front && !above) 
			addAction(sequence(parallel(rotateBy(-360, 1.5f), moveBy(-200, -200, 1.5f)), removeActor()));
	}

	public Rectangle getBounds() {
		return bounds;
	}
}
