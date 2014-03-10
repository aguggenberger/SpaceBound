package com.aguggenberger.spacebound.screens;

import com.aguggenberger.spacebound.engine.Engine;
import com.aguggenberger.spacebound.engine.Level;
import com.aguggenberger.spacebound.engine.loading.BodyEditorLoader;
import com.aguggenberger.spacebound.engine.services.SpaceBoundLevelLoader;
import com.aguggenberger.spacebound.resources.SpaceBoundAtlas;
import com.aguggenberger.spacebound.resources.SpaceBoundTexture;
import com.aguggenberger.spacebound.screens.base.GameScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.QueryCallback;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

public class box2dTestScreen extends GameScreen {

	//private static final float SHIP_SCALE = 1; 
	private float SHIP_SCALE = 0.5f; 
    private static final float VIEWPORT_WIDTH = 10;
    private static final int MAX_METEORS = 200;
	
    private SpaceBoundLevelLoader levelloader;
	private Box2DDebugRenderer debugRenderer;
	
	//models
	private World world;
	private Body shipModel;
	private Vector2 shipModelOrigin;
	private Body[] meteorModels;
	
	
	//render
	private Sprite shipSprite;
	private Sprite[] meteorSprites;
	public static TextureAtlas atlas;
	public static Array<TextureRegion> meteors;
	public static Array<TextureRegion> powerups;
	public static TextureRegion meteorBrown_big1;
	public static TextureRegion meteorBrown_big2;
	public static TextureRegion meteorBrown_big3;
	public static TextureRegion meteorBrown_big4;
	public static TextureRegion meteorBrown_med1;
	public static TextureRegion meteorBrown_med3;
	public static TextureRegion meteorBrown_small1;
	public static TextureRegion meteorBrown_small2;
	public static TextureRegion meteorBrown_tiny1;
	public static TextureRegion meteorBrown_tiny2;
	public static TextureRegion spaceShip;
	public static Texture background;
	
	@Override
	protected void draw(float delta) {
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		
		world.step(1/60f, 6, 2);
		debugRenderer.render(world, camera.combined);
		

	    Vector2 shipPos = shipModel.getPosition().sub(shipModelOrigin);
	 
	    shipSprite.setPosition(shipPos.x, shipPos.y);
	    shipSprite.setOrigin(shipModelOrigin.x, shipModelOrigin.y);
	    shipSprite.setRotation(shipModel.getAngle() * MathUtils.radiansToDegrees);
	    
	    for (int i=0; i< meteorModels.length - 1 ; i++) {
            Vector2 ballPos = meteorModels[i].getPosition();
            meteorSprites[i].setPosition(ballPos.x - meteorSprites[i].getWidth()/2, ballPos.y - meteorSprites[i].getHeight()/2);
            meteorSprites[i].setRotation(meteorModels[i].getAngle() * MathUtils.radiansToDegrees);
	    }
	    

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        shipSprite.draw(batch);
        for (int i=0; i< meteorModels.length - 1; i++) meteorSprites[i].draw(batch);
        batch.end();

        batch.getProjectionMatrix().setToOrtho2D(0, 0, w, h);
        //batch.begin();
        //font.draw(batch, "Touch the screen to restart", 5, h-5);
        //batch.end();
	    
	    
	}
	
	@Override
	public void load() {
		levelloader = new SpaceBoundLevelLoader();
		levelloader.load(assets);
		assets.finishLoading();
		
		world = new World(new Vector2(0, 10), true);
		debugRenderer = new Box2DDebugRenderer();
				
		
		//AGTODO: add function
		//meteorModels = new Body[MAX_METEORS];		
		//meteorSprites= new Sprite[MAX_METEORS];
		
		
        meteorModels = new Body[0]; 
		
		meteorSprites= new Sprite[0];
		
		background = assets.get(SpaceBoundTexture.BACKGROUND_PURPLE, Texture.class);
		background.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
		
		atlas = assets.get(SpaceBoundAtlas.GAME_ATLAS);
		
		spaceShip = atlas.findRegion(SpaceBoundAtlas.SPACE_SHIP_REGION);
		
		createShip();		
		
		shipSprite = new Sprite(); 
		shipSprite = new Sprite(spaceShip);		
		shipSprite.setSize(SHIP_SCALE * shipSprite.getWidth(), SHIP_SCALE * shipSprite.getHeight());
		
		//shipSprite.setSize(spa, SHIP_WIDTH * shipSprite.getHeight() / shipSprite.getWidth());
		
		meteors = new Array<TextureRegion>();		
		meteorBrown_big1 = atlas.findRegion(SpaceBoundAtlas.METEOR_BROWN_BIG1_REGION);
		meteors.add(meteorBrown_big1);
		meteorBrown_big2 = atlas.findRegion(SpaceBoundAtlas.METEOR_BROWN_BIG2_REGION);
		meteors.add(meteorBrown_big2);
		meteorBrown_big3 = atlas.findRegion(SpaceBoundAtlas.METEOR_BROWN_BIG3_REGION);
		meteors.add(meteorBrown_big3);
		meteorBrown_big4 = atlas.findRegion(SpaceBoundAtlas.METEOR_BROWN_BIG4_REGION);
		meteors.add(meteorBrown_big4);
		meteorBrown_med1 = atlas.findRegion(SpaceBoundAtlas.METEOR_BROWN_MED1_REGION);
		meteors.add(meteorBrown_med1);
		meteorBrown_med3 = atlas.findRegion(SpaceBoundAtlas.METEOR_BROWN_MED3_REGION);
		meteors.add(meteorBrown_med3);
		meteorBrown_small1 = atlas.findRegion(SpaceBoundAtlas.METEOR_BROWN_SMALL1_REGION);
		meteors.add(meteorBrown_small1);
		meteorBrown_small2 = atlas.findRegion(SpaceBoundAtlas.METEOR_BROWN_SMALL2_REGION);
		meteors.add(meteorBrown_small2);
		meteorBrown_tiny1 = atlas.findRegion(SpaceBoundAtlas.METEOR_BROWN_TINY1_REGION);
		meteors.add(meteorBrown_tiny1);
		meteorBrown_tiny2 = atlas.findRegion(SpaceBoundAtlas.METEOR_BROWN_TINY1_REGION);
		meteors.add(meteorBrown_tiny2);
		powerups = new Array<TextureRegion>(); 
		powerups.add(atlas.findRegion(SpaceBoundAtlas.SILVER_SHEILD_REGION));
	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(new InputAdapter() {
			
			Vector3 testPoint = new Vector3();
			Body hitBody = null;
			
			QueryCallback callback = new QueryCallback() {
	                @Override public boolean reportFixture (Fixture fixture) {
	                        // if the hit point is inside the fixture of the body
	                        // we report it
	                        if (fixture.testPoint(testPoint.x, testPoint.y)) {
	                                hitBody = fixture.getBody();
	                                return false;
	                        } else
	                                return true;
	                }
	        };
			
            @Override public boolean touchDown(int x, int y, int pointer, int button) {
            	testPoint.set(x, y, 0);
        		camera.unproject(testPoint);
        		//world.QueryAABB(callback, testPoint.x - 0.0001f, testPoint.y - 0.0001f, testPoint.x + 0.0001f, testPoint.y + 0.0001f);
        		
            	log("touch: " + x + "," + y);
            	log("testp: " + testPoint.x + "," + testPoint.y);
            	log("sprite: " + shipSprite.getX() + "," + shipSprite.getY());
            	
            	Vector2 shipPos = shipModel.getPosition().sub(shipModelOrigin);
            	log("pos: " + shipPos.x + "," + shipPos.y);
            	log("calcpos: " + shipPos.x + shipModelOrigin.x + "," + shipModelOrigin.y);
            	if (testPoint.x < (shipPos.x + shipModelOrigin.x))
            		shipModel.setLinearVelocity(shipModel.getLinearVelocity().x - 10, 0);
            	else if (testPoint.x > (shipPos.x + shipModelOrigin.x))
            	  shipModel.setLinearVelocity(shipModel.getLinearVelocity().x + 10, 0);
                return true;
            }
		});
		super.show();
	}
	@Override
	protected void unload() {
		atlas.dispose();
		background.dispose();
	}
	
	@Override
	public void dispose() {
		levelloader.unload(assets);
		world.dispose();
		super.dispose();
	}
	
	private void createShip() {
		
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
	    shipModel = world.createBody(bd);
	    shipModel.setGravityScale(0);
	 
	    // 4. Create the body fixture automatically by using the loader.
	    //loader.attachFixture(shipModel, "ship-red", fd, SHIP_WIDTH);	    
	    //shipModelOrigin = loader.getOrigin("ship-red", SHIP_WIDTH).cpy();
	    
	    loader.attachFixture(shipModel, "ship-red", fd, spaceShip.getRegionWidth() * SHIP_SCALE);	    
	    shipModelOrigin = loader.getOrigin("ship-red", spaceShip.getRegionWidth() * SHIP_SCALE).cpy();   	    
	}
}
