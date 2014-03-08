package com.aguggenberger.spacebound;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class InfiniteScrollBg extends Actor {
	private Sprite backgroundSprite;
	
	public InfiniteScrollBg(float width, float height) {
		setWidth(width);
		setHeight(height);
		backgroundSprite = new Sprite(Assets.background, 0, 0, (int)width, (int)height);
		//setPosition(width, 0);
		//setPosition(0, 0);
		//addAction(forever(sequence(moveTo(0, 0, 1f), moveTo(width, 0))));
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		backgroundSprite.draw(batch);
	}
}
