package com.aguggenberger.spacebound.screens;

import aurelienribon.bodyeditor.BodyEditorLoader;

import com.aguggenberger.spacebound.screens.base.GameScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

public class box2dTestScreen extends GameScreen{
	
	private static final float BOTTLE_WIDTH = 40;
	private World world;
	private Box2DDebugRenderer debugRenderer;
	private Body bottleModel;
	private Vector2 bottleModelOrigin;
	private Sprite bottleSprite;

	@Override
	public void show() {
		world = new World(new Vector2(0, -10), true);
		debugRenderer = new Box2DDebugRenderer();
		bottleSprite = new Sprite(); 
				
		createBottle();
		super.show();
	}
		
	private void createBottle() {
		
	    // 0. Create a loader for the file saved from the editor.
	    BodyEditorLoader loader = new BodyEditorLoader(Gdx.files.internal("physic/spacebound.json"));
	 
	    // 1. Create a BodyDef, as usual.
	    BodyDef bd = new BodyDef();
	    bd.position.set(0, 0);
	    bd.type = BodyType.DynamicBody;
	 
	    // 2. Create a FixtureDef, as usual.
	    FixtureDef fd = new FixtureDef();
	    fd.density = 1;
	    fd.friction = 0.5f;
	    fd.restitution = 0.3f;
	 
	    // 3. Create a Body, as usual.
	    bottleModel = world.createBody(bd);
	 
	    // 4. Create the body fixture automatically by using the loader.
	    loader.attachFixture(bottleModel, "ship-red", fd, BOTTLE_WIDTH);
	    
	    bottleModelOrigin = loader.getOrigin("test01", BOTTLE_WIDTH).cpy();
	}

	@Override
	protected void draw(float delta) {
		world.step(1/60f, 6, 2);
		debugRenderer.render(world, camera.combined);
		

	    Vector2 bottlePos = bottleModel.getPosition().sub(bottleModelOrigin);
	 
	    bottleSprite.setPosition(bottlePos.x, bottlePos.y);
	    bottleSprite.setOrigin(bottleModelOrigin.x, bottleModelOrigin.y);
	    bottleSprite.setRotation(bottleModel.getAngle() * MathUtils.radiansToDegrees);
	}

}
