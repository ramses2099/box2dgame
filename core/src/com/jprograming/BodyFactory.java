package com.jprograming;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class BodyFactory {
    World world;
    static BodyFactory instance;

    private BodyFactory(World world) {
        this.world = world;
    }

    public static BodyFactory getInstance(World world) {
        if (instance == null) {
            instance = new BodyFactory(world);
        }
        return instance;
    }

    private FixtureDef makeFixture(Material material, Shape shape) {
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;

        switch (material) {
            case STEEL: {
                fixtureDef.density = 1f;
                fixtureDef.friction = 0.3f;
                fixtureDef.restitution = 0.1f;
                break;
            }
            case WOOD: {
                fixtureDef.density = 0.5f;
                fixtureDef.friction = 0.7f;
                fixtureDef.restitution = 0.3f;
                break;
            }
            case RUBBER: {
                fixtureDef.density = 1f;
                fixtureDef.friction = 0;
                fixtureDef.restitution = 1.0f;
                break;
            }
            case STONE: {
                fixtureDef.density = 1f;
                fixtureDef.friction = 0.9f;
                fixtureDef.restitution = 0.01f;
                break;
            }
            default: {
                fixtureDef.density = 7f;
                fixtureDef.friction = 0.5f;
                fixtureDef.restitution = 0.3f;
            }
        }

        return fixtureDef;
    }

    public Body makeCirclePolyBody(Vector2 position, float radius, Material material, BodyDef.BodyType bodyType, boolean fixedRotation) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = bodyType;
        bodyDef.position.set(position);
        bodyDef.fixedRotation = fixedRotation;

        Body body = world.createBody(bodyDef);
        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(radius / 2);
        body.createFixture(makeFixture(material, circleShape));
        circleShape.dispose();
        return body;
    }
    //
    public Body makeBoxPolyBody(Vector2 position, float width, float height, Material material, BodyDef.BodyType bodyType, boolean fixedRotation) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = bodyType;
        bodyDef.position.set(position);
        bodyDef.fixedRotation = fixedRotation;

        Body body = world.createBody(bodyDef);
        PolygonShape polygonShape = new PolygonShape();
        polygonShape.setAsBox(width/2, height/2);
        body.createFixture(makeFixture(material, polygonShape));
        polygonShape.dispose();
        return body;
    }
    //
    public Body makePolygonShapeBody(Vector2[] vertices, Vector2 position, Material material, BodyDef.BodyType bodyType){
        BodyDef boxBodyDef = new BodyDef();
        boxBodyDef.type = bodyType;
        boxBodyDef.position.set(position);
        Body boxBody = world.createBody(boxBodyDef);
        //
        PolygonShape polygonShape = new PolygonShape();
        polygonShape.set(vertices);
        boxBody.createFixture(makeFixture(material, polygonShape));
        polygonShape.dispose();

        return boxBody;

    }
}
