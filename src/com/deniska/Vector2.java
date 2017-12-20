package com.deniska;

import java.util.Locale;

public class Vector2 {
    public Double x; public Double y;

    Vector2(){
        this.x = 0d;
        this.y = 0d;
    }

    Vector2(float x, float y){
        this((double) x, (double) y);
    }

    Vector2(Double x, Double y){
        this.x = x;
        this.y = y;
    }

    public static Vector2 sum(Vector2 v1, Vector2 v2){
        return new Vector2(v1.x + v2.x, v1.y + v2.y);
    }

    public static Double distance(Vector2 v1, Vector2 v2){
        return Math.sqrt(Math.pow(Math.abs(v1.x - v2.x), 2) + Math.pow(Math.abs(v1.y - v2.y), 2));
    }

    public void move(Vector2 delta){
        this.x += delta.x;
        this.y += delta.y;
    }

    @Override
    public String toString(){
        return String.format(Locale.US, "x:%.2f; y:%.2f", x, y);
    }
}
