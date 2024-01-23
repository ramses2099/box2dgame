package com.jprograming;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

import static com.jprograming.Constants.*;

public class Bucket {

    private Sprite sprite;
    private Body body;
    private Vector2 position;

    private float width;
    private float height;

    public Bucket(World world, float x, float y) {

        sprite = new Sprite(new Texture(Gdx.files.internal("bucket.png")));

        /*---------------------------------------------------
		create body
		----------------------------------------------------*/
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        position = new Vector2(x, y);
        bodyDef.position.set(position);

        body = world.createBody(bodyDef);

        CircleShape circleShape = new CircleShape();

        float radio = IMG_WIDTH / SCALE / PPM;
        circleShape.setRadius(radio);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circleShape;
        fixtureDef.density = 0.5f;
        fixtureDef.friction = 0.4f;
        fixtureDef.restitution = 0.6f;

        Fixture fixture = body.createFixture(fixtureDef);
        circleShape.dispose();

        position = body.getPosition();
        float rotation = (MathUtils.radiansToDegrees * body.getAngle());

        width = (IMG_WIDTH / PPM);
        height = (IMG_HEIGHT / PPM);

        sprite.setSize(width, width);
        sprite.setRotation(rotation);
        sprite.setOrigin(0, 0);
        sprite.setPosition(position.x - width / SCALE, position.y - width / SCALE);

    }

    public void render(SpriteBatch batch) {
        sprite.draw(batch);
    }

    public void update(float deltatime) {
        position = body.getPosition();
        float rotation = (MathUtils.radiansToDegrees * body.getAngle());
        sprite.setRotation(rotation);
        sprite.setOrigin(0, 0);
        sprite.setPosition(position.x - width / SCALE, position.y - height / SCALE);
    }


}
