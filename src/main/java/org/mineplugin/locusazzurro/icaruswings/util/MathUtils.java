package org.mineplugin.locusazzurro.icaruswings.util;

import net.minecraft.util.RandomSource;
import net.minecraft.world.phys.Vec3;
import org.joml.Vector3d;
import org.joml.Vector3f;

import java.util.ArrayList;
import java.util.List;

public class MathUtils {

    /**
     * Generates a set of equidistant points on unit sphere using fibonacci sphere algorithm
     * @link <a href="https://stackoverflow.com/a/26127012">https://stackoverflow.com/a/26127012</a>
     * @param samples number of points
     * @return ArrayList of points in {@link org.joml.Vector3d}
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

    @SuppressWarnings("unused")
    public static List<Vector3f> fibonacciSphereFloat(int samples){
        List<Vector3d> pointsDouble = fibonacciSphere(samples);
        List<Vector3f> pointsFloat = new ArrayList<>();
        pointsDouble.forEach((p) -> pointsFloat.add(new Vector3f((float) p.x(), (float) p.y(), (float) p.z())));
        return pointsFloat;
    }

    public static List<Vec3> circlePoints(int numPoints, double offset){
        List<Vec3> points = new ArrayList<>();
        double t = Math.PI * 2 / numPoints;
        double x,y=0,z,cA;
        for (int i = 0; i < numPoints; i++){
            cA = offset + i * t;
            x = Math.cos(cA);
            z = Math.sin(cA);
            points.add(new Vec3(x,y,z));
        }
        return points;
    }

    public static List<Vec3> circlePoints(int numPoints){
        return circlePoints(numPoints, 0);
    }

    /**
     * Generates a list of randomly sampled points in given radius
     * @link <a href="https://stackoverflow.com/a/50746409">https://stackoverflow.com/a/50746409</a>
     * @param numPoints number of points
     * @param radius radius of the circle to sample points
     * @param random instance of RNG provider
     * @return ArrayList of points in {@link org.joml.Vector3d} with zero y value
     */
    public static List<Vec3> randomPointsInCircle(int numPoints, double radius, RandomSource random){
        List<Vec3> points = new ArrayList<>();
        double r,theta,x,y=0,z;
        for (int i = 0; i < numPoints; i++) {
            r = radius * Math.sqrt(random.nextDouble());
            theta = random.nextDouble() * Math.PI * 2;
            x = r * Math.cos(theta);
            z = r * Math.sin(theta);
            points.add(new Vec3(x,y,z));
        }
        return points;
    }

    public static List<Vec3> squareMatrixFrame(int subDivisions){
        List<Vec3> points = new ArrayList<>();
        double s = 2d/subDivisions;
        double x,y=0,z;
        for (int i = 1; i < subDivisions; i++){
            x = -1;
            for (int j = 0; j <= subDivisions; j++){
                z = -1 + s * j;
                points.add(new Vec3(x,y,z));
            }
            z = -1;
            for (int j = 1; j < subDivisions; j++){
                x = -1 + s * j;
                points.add(new Vec3(x,y,z));
            }
            z = 1;
            for (int j = 1; j < subDivisions; j++){
                x = -1 + s * j;
                points.add(new Vec3(x,y,z));
            }
            x = 1;
            for (int j = 0; j <= subDivisions; j++){
                z = -1 + s * j;
                points.add(new Vec3(x,y,z));
            }
        }
        return points;
    }

    /**
     * Generates a set of points on faces unit cube equally divided
     * @param subDivisions number of subdivisions (segments) for each side
     * @return ArrayList of points in {@link org.joml.Vector3d}
     */
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

    public static Vector3d mulCopy(Vector3d vec, double scalar){
        return new Vector3d(vec.x() * scalar, vec.y * scalar, vec.z * scalar);
    }

}

