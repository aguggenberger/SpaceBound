package com.aguggenberger.spacebound;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class PlayerShip extends Actor {
	private SpaceGame spaceGame;
	private Rectangle bounds = new Rectangle();
	private float velocity = 0;
    private static final float MAX_VELOCITY = 10;
    private static final float MIN_VELOCITY = -10;
    private static final float VELOCITY_INC = 2;
	
	public PlayerShip(SpaceGame spaceGame) {
		this.spaceGame = spaceGame;
		setWidth(Assets.spaceShip.getRegionWidth());
		setHeight(Assets.spaceShip.getRegionHeight());
		setPosition(spaceGame.getWidth() / 2 - getWidth() / 2, getHeight() / 2 + 25);
	}
	
	@Override
	public void act(float delta){
		super.act(delta);
		updateBounds();
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		batch.setColor(getColor().r, getColor().g, getColor().b, getColor().a);
		
		//Gdx.app.debug("MyTag", "set: x " + getX() + velocity * Gdx.graphics.getDeltaTime() );
		setPosition(getX() + velocity, getY());
		batch.draw(Assets.spaceShip, getX(), getY(), getWidth()/2, getHeight()/2, getWidth(), getHeight(), 1, 1, getRotation());
		
		//batch.draw(Assets.spaceShip, getX() , getY(), getWidth()/2, getHeight()/2, getWidth(), getHeight(), 1, 1, getRotation());
	}
	
	private void updateBounds() {
		bounds.set(getX(), getY(), getWidth(), getHeight());
	}

	public void tryMoveLeft() {
		clearActions();
		if (getActions().size == 0) moveWithVelocity(velocity - VELOCITY_INC);
	}

	private void moveWithVelocity(float value){		
		velocity = value;
		if (velocity > MAX_VELOCITY)
            velocity = MAX_VELOCITY;
        if (velocity < MIN_VELOCITY)
            velocity = MIN_VELOCITY;
        
        //addAction(repeat(moveTo(getX() + velocity, getY()))));
		//addAction(forever(sequence(moveTo(getX() + velocity, getY()))));		
	}
	
	public void tryMoveRight() {
		clearActions();
		if (getActions().size == 0) moveWithVelocity(velocity + VELOCITY_INC);
	}
		
	public Rectangle getBounds() {
		return bounds;
	}

	public void stopShip() {
		velocity = 0;		
	}

	public float getVelocity() {
		return velocity;
	}
	
	public void resetShip(){
		setPosition(spaceGame.getWidth() / 2 - getWidth() / 2, getHeight() / 2 + 25);
		velocity = 0;
	}

}
