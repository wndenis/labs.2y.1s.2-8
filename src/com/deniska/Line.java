package com.deniska;

public class Line implements FigureBase {
    private Vector2 start,  end;

    // Main ctor
    Line(Vector2 start, Vector2 end){
        setStart(start);
        setEnd(end);
    }

    // copying ctor
    Line(Line other){ // Copying
        this(other.getStart(), other.getEnd());
    }

    Line(){
        this(new Vector2(0d, 0d), new Vector2(1d, 1d));
    }

    Line(Double x1, Double y1, Double x2, Double y2){
        this(new Vector2(x1, y1), new Vector2(x2, y2));
    }




    public Vector2 getStart() {
        return start;
    }
    public void setStart(Vector2 start) {
        this.start = start;
    }

    public Vector2 getEnd() {
        return end;
    }
    public void setEnd(Vector2 end) {
        this.end = end;
    }

    public void move(Vector2 delta){
        moveStart(delta);
        moveEnd(delta);
    }
    public void moveStart(Vector2 delta){
        start.move(delta);
    }
    public void moveEnd(Vector2 delta){
        end.move(delta);
    }


    @Override
    public String getType() {
        return "Отрезок";
    }

    @Override
    public FigureBase copy() {
        return new Line(this);
    }

    @Override
    public String represent() {
        return String.format("%s (%s), (%s)", getType(), start, end);
    }

    @Override
    public Line getConcreteObject() {
        return this;
    }
}
