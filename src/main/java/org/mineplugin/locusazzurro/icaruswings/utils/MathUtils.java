package org.mineplugin.locusazzurro.icaruswings.utils;

import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;

import java.util.ArrayList;
import java.util.List;

public class MathUtils {

/*
Fibonacci Sphere
https://stackoverflow.com/a/26127012
 */
    public static List<Vector3d> fibonacciSphere(int samples){

        List<Vector3d> points = new ArrayList<>();
        double phi = Math.PI * (3.0d - Math.sqrt(5.0d));
        double theta,x,y,z,r;
        for (int i = 0; i < samples; i++){
            y = 1 - (i / (float)(samples - 1)) * 2;
            r = Math.sqrt(1 - y * y);
            theta = phi * i;
            x = Math.cos(theta) * r;
            z = Math.sin(theta) * r;
            points.add(new Vector3d(x,y,z));
        }
        return points;
    }

    public static List<Vector3f> fibonacciSphereFloat(int samples){
        List<Vector3d> pointsDouble = fibonacciSphere(samples);
        List<Vector3f> pointsFloat = new ArrayList<>();
        pointsDouble.forEach((p) -> pointsFloat.add(new Vector3f(p)));
        return pointsFloat;
    }

    public static List<Vector3d> cubeMatrixFrame(int subDivisions){
        List<Vector3d> points = new ArrayList<>();
        double x,y,z,s;
        s = 2d / subDivisions;
        y = -1;
        for (int i = 0; i <= subDivisions; i++){
            z = -1 + s * i;
            for (int j = 0; j <= subDivisions; j++){
                x = -1 + s * j;
                points.add(new Vector3d(x,y,z));
            }
        }
        for (int i = 1; i < subDivisions; i++){
            y = -1 + s * i;
            x = -1;
            for (int j = 0; j <= subDivisions; j++){
                z = -1 + s * j;
                points.add(new Vector3d(x,y,z));
            }
            z = -1;
            for (int j = 1; j < subDivisions; j++){
                x = -1 + s * j;
                points.add(new Vector3d(x,y,z));
            }
            z = 1;
            for (int j = 1; j < subDivisions; j++){
                x = -1 + s * j;
                points.add(new Vector3d(x,y,z));
            }
            x = 1;
            for (int j = 0; j <= subDivisions; j++){
                z = -1 + s * j;
                points.add(new Vector3d(x,y,z));
            }
        }
        y = 1;
        for (int i = 0; i <= subDivisions; i++){
            z = -1 + s * i;
            for (int j = 0; j <= subDivisions; j++){
                x = -1 + s * j;
                points.add(new Vector3d(x,y,z));
            }
        }
        return points;
    }

}

