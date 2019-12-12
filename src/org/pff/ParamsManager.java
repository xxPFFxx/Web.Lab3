package org.pff;

public class ParamsManager {
    public static boolean isInArea(double x, double y, double r){
        return  (x >= 0 && y>= 0 && (x*x)+(y*y) <= r*r)||
                (x <=0 && y <= 0 && x >= -(r/2) && y >= -r)||
                (x <= 0 && y >=0 && y<= (x + (r/2)));
    }
    public static PointState getPointState(double x, double y, double r){
        if(isValidX(x) && isValidY(y)){
            if(isInArea(x, y, r)) return PointState.IN;
            else return PointState.OUT;
        }
        else{
            return PointState.ODZ;
        }

    }
    public static boolean isValidX(double value){
        return value * value <= 9;
    }

    public static boolean isValidY(double value){
        return (value +1)*(value +1) <= 16;
    }

    public static boolean isValidR(double value){
        return value >= 1.0 && value <= 3.0;
    }
    public static boolean graphAvailable(double value){
        return value*value <= 36;
    }

}
