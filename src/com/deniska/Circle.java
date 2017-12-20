package com.deniska;

public class Circle implements FigureBase {
    private Vector2 position;
    private Double radius;

    // main ctor
    Circle(Vector2 position, Double radius){
        setPosition(position);
        setRadius(radius);
    }

    // copying ctor
    Circle(Circle other){ // Copying
        this(other.getPosition(), other.getRadius());
    }

    Circle(){
        setPosition(new Vector2());
        setRadius(1d);
    }

    Circle(Double x, Double y, Double radius){
        this(new Vector2(x, y), radius);
    }




    public Vector2 getPosition() {
        return position;
    }
    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public Double getRadius() {
        return radius;
    }
    public void setRadius(Double radius) {
        if (radius >= 0)
            this.radius = radius;
        else
            System.out.println("Недопустимый радиус (" + radius + ")");
    }

    @Override
    public String getType() {
        return "Окружность";
    }

    @Override
    public FigureBase copy() {
        return new Circle(this);
    }

    @Override
    public String represent() {
        return String.format("%s (%s), (R = %.2f)", getType(), position, radius);
    }

    @Override
    public FigureBase getConcreteObject() {
        return this;
    }
}
