package com.deniska;


import java.util.ArrayList;

public class Editor {
    private ArrayList<FigureBase> figureBases; //arraylist

    Editor(){
        figureBases = new ArrayList<>();
    }

    public void addFigure(FigureBase f, Integer position){ figureBases.set(position, f.getConcreteObject()); }
    public void addFigure(FigureBase f){
        figureBases.add(f.getConcreteObject());
    }

    public void remFigure(int index){
        figureBases.remove(index);
    }

    public void clearFigures(){
        figureBases.clear();
    }

    public void representFigures(){
        for (int i = 0; i < figureBases.size(); i++) {
            System.out.printf("%d) %s\n", i, figureBases.get(i).represent());
        }
        System.out.println();
    }

    public FigureBase getFigure(int index){
        return figureBases.get(index);
    }//TODO а вот оно вообще надо?

    public int getFiguresCount(){
        return figureBases.size();
    }

    public Equation getLineEquation(Line line){
        Vector2 p1 = line.getStart();
        Vector2 p2 = line.getEnd();
        Double kx = p1.y - p2.y;
        Double ky = p2.x - p1.x;
        Double c = p1.x * p2.y - p2.x * p1.y;
        return new Equation(kx, ky, c);
    }

    public boolean isIntersects(int index1, int index2){
        FigureBase figureBase1 = getFigure(index1);
        FigureBase figureBase2 = getFigure(index2);
        Class<? extends FigureBase> concrete1 = figureBase1.getClass();
        Class<? extends FigureBase> concrete2 = figureBase2.getClass();

        return isIntersects(concrete1.cast(figureBase1), concrete2.cast(figureBase2));
    }

    public boolean isIntersects(FigureBase figureBase1, FigureBase figureBase2){
        // здесь мне сломали ногу
        if (figureBase1 instanceof Line && figureBase2 instanceof Line)
            return isIntersects((Line) figureBase1, (Line) figureBase2);
        else if (figureBase1 instanceof Circle && figureBase2 instanceof Circle)
            return isIntersects((Circle) figureBase1, (Circle) figureBase2);
        else if (figureBase1 instanceof Circle && figureBase2 instanceof Line)
            return isIntersects((Circle) figureBase1, (Line) figureBase2);
        else if (figureBase1 instanceof Line && figureBase2 instanceof Circle)
            return isIntersects((Line) figureBase1, (Circle) figureBase2);
        return false;
    }




    public boolean isIntersects(Line line, Circle circle){
        // герон
        double a = Vector2.distance(line.getStart(), line.getEnd());
        double b = Vector2.distance(line.getStart(), circle.getPosition());
        double c = Vector2.distance(line.getEnd(), circle.getPosition());
        double p = (a + b + c) / 2;
        double h = (2 * Math.sqrt(p * (p - a) * (p - b) * (p - c))) / a;
        return h <= circle.getRadius();
    }

    public boolean isIntersects(Circle circle, Line line){
        return isIntersects(line, circle);
    }

    public boolean isIntersects(Line line1, Line line2){
        Equation eq1 = getLineEquation(line1);
        Equation eq2 = getLineEquation(line2);
        // TODO: написать пересечение

        return false; //mock
    }
    public boolean isIntersects(Circle c1, Circle c2){
        return Vector2.distance(c1.getPosition(), c2.getPosition()) <= c1.getRadius() + c2.getRadius();
    }

}
