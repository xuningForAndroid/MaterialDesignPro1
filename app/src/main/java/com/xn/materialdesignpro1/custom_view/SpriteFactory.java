package com.xn.materialdesignpro1.custom_view;

public class SpriteFactory {

    public static Sprite create(Style style) {
        Sprite sprite = null;
        switch (style) {

            case CHASING_DOTS:
                sprite = new ChasingDots();
                break;
            case CIRCLE:
//                sprite = new Circle();
                break;
            default:
                break;
        }
        return sprite;
    }
}
