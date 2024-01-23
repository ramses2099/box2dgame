package com.jprograming;

public final class Constants {

    public static final float SCREEN_WIDTH = 800.0f;
    public static final float SCREEN_HEIGHT = 600.0f;

    /*----------------------------------------------
    World dimension SCREEN_WITH / PPM
    Screen = pixel
    WORLD_WIDTH = meter
    ----------------------------------------------*/
    public static final float WORLD_WIDTH = 8.0f;
    public static final float WORLD_HEIGHT = 6.0f;

    public static final float TIME_STEP = 1 /60.0f;
    public static final int VELOCITY_ITERATIONS = 6;
    public static final int POSITION_ITERATIONS = 2;

    /*--------------------------------------------
    100 pixels = 1 meter
    ---------------------------------------------*/
    public static final float PPM = 100.0f;
    public static final float SCALE = 2.0f;

    public static final boolean DRAW_BOX2D_DEBUG = true;

    public static final float IMG_WIDTH = 64.0f;
    public static final float IMG_HEIGHT = 64.0f;

}
