package com.deniska;

public class Equation{
    public Double kx, ky, c;
    Equation(Double kx, Double ky, Double c){
        this.kx = kx;
        this.ky = ky;
        this.c = c;
    }

    public String toString(){
        return String.format("%.2fx%+.2fy%+.2fc=0", kx, ky, c);
        /*
        String x = "", y = "", c = "";
        if (this.kx != 0)
            x = (this.kx > 0 ? "+":"") + this.kx + "x";
        if (this.ky != 0)
            y = (this.ky > 0 ? "+":"") + this.kx + "y";
        if (this.c != 0)
            c = (this.c > 0 ? "+":"") + this.c;
        return "" + x + y + c + "=0";
        */
    }
}
