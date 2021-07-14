package util;

public class MathUtil {
    public static double calcPointDistance(double x1, double y1, double x2, double y2){
        return Math.sqrt(Math.pow(x2-x1, 2) + Math.pow(y2-y1, 2));
    }


    public static double calcTriangleCHeight(double a, double b, double c){
        double p = (a+b+c)/2;
        return (2/c) * Math.sqrt(p*(p-a)*(p-b)*(p-c));
    }


}
