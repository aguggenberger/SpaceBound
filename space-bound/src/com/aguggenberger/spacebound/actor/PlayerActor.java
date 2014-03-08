package com.aguggenberger.spacebound.actor;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class PlayerActor extends Actor {	
	
	private float velocity = 0;
    private static final float MAX_VELOCITY = 10;
    private static final float MIN_VELOCITY = -10;
    private static final float VELOCITY_INC = 2;
	private TextureRegion textureRegion;
	
	public PlayerActor(TextureRegion textureRegion) {
		this.textureRegion = textureRegion;
		setBounds(0, 0, textureRegion.getRegionWidth(), textureRegion.getRegionHeight());
	}
	
	@Override
	public void setPosition(float x, float y) {
		//Set position only updates velocity
		if (x > getX()) 
			tryMoveRight();
		else if (x < getX())
			tryMoveLeft();
	}
	@Override
	public void act(float delta) {
		super.act(delta);
		super.setPosition(getX() + velocity, getY());		
	}
	
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		batch.draw(textureRegion, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), 
                getScaleX(), getScaleY(), getRotation());
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

}