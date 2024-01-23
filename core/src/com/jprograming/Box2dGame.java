package com.jprograming;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

import static com.jprograming.Constants.*;

public class Box2dGame extends ApplicationAdapter {
    SpriteBatch batch;

    World world;
    Box2DDebugRenderer bugRenderer;
    OrthographicCamera camera;
    ExtendViewport viewport;
    float accumulator = 0;
    Body body;
    Texture tex;
    Sprite sprite;

    Bucket bucket;

    @Override
    public void create() {
        //
        camera = new OrthographicCamera();
        viewport = new ExtendViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);


        batch = new SpriteBatch();
        world = new World(new Vector2(0, -9.8f), true);
        bugRenderer = new Box2DDebugRenderer();



        /*---------------------------------------------------
		create image
		----------------------------------------------------*/
        tex = new Texture(Gdx.files.internal("drop.png"));

        sprite = new Sprite(new Texture(Gdx.files.internal("bucket.png")));

        bucket = new Bucket(world, WORLD_WIDTH / 2, WORLD_HEIGHT / 2);


		/*---------------------------------------------------
		create body
		----------------------------------------------------*/
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(new Vector2(1.5f, 3));

        body = world.createBody(bodyDef);

        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(0.16f);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circleShape;
        fixtureDef.density = 0.5f;
        fixtureDef.friction = 0.4f;
        fixtureDef.restitution = 0.6f;

        Fixture fixture = body.createFixture(fixtureDef);
        circleShape.dispose();


    }

    @Override
    public void render() {
        ScreenUtils.clear(0, 0, 0.2f, 1);
        /*---------------------------------------------------
		UPDATE WORLD PHYSISCS
		----------------------------------------------------*/
        doPhysicsStep(Gdx.graphics.getDeltaTime());

        bucket.update(Gdx.graphics.getDeltaTime());


        /*---------------------------------------------------
		DRAW
		----------------------------------------------------*/
        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        Vector2 pos = body.getPosition();
        float rotation = (MathUtils.radiansToDegrees * body.getAngle());

        float w = (64.0f / PPM), h = (64.0f / PPM);

        sprite.setSize(w, h);
        sprite.setRotation(rotation);
        sprite.setOrigin(0, 0);
        sprite.setPosition(pos.x - w / 2, pos.y - h / 2);
        sprite.draw(batch);

        bucket.render(batch);


        batch.end();

        if (DRAW_BOX2D_DEBUG) {
            bugRenderer.render(world, camera.combined);
            camera.update();
        }
    }

    @Override
    public void dispose() {
        batch.dispose();
        world.dispose();
        bugRenderer.dispose();
        tex.dispose();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
        batch.setProjectionMatrix(camera.combined);
    }

    //
    private void doPhysicsStep(float deltatime) {
        float frameTime = Math.min(deltatime, 0.25f);
        accumulator += frameTime;
        while (accumulator >= TIME_STEP) {
            world.step(TIME_STEP, VELOCITY_ITERATIONS, POSITION_ITERATIONS);
            accumulator -= TIME_STEP;
        }
    }

}
