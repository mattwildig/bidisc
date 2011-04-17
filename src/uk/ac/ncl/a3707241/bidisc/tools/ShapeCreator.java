package uk.ac.ncl.a3707241.bidisc.tools;

import javax.media.j3d.*;
import javax.vecmath.*;

/**
 * Use to generate Shape3D objects from arrays of Point3ds,
 * and to get an array of Color3d of required size.
 * 
 * @author M.W. Wildig
 * @version August 2004
 */
public class ShapeCreator
{
    // no reason to instantiate this class
    private ShapeCreator()
    {}
    
    public static Shape3D getShape(Point3d[] points)
    {
        PointArray geom = new PointArray(points.length, GeometryArray.COORDINATES | GeometryArray.COLOR_3);
        geom.setCoordinates(0, points);
        geom.setColors(0, getColors(points.length));
        
        Shape3D shape = new Shape3D(geom);
        
        return shape;
    }

    public static Color3f[] getColors(int size)
    {
        return getColors(size, new Color3f(0.0f, 0.0f, 0.0f));//default to black
    }
    
    public static Color3f[] getColors(int size, Color3f col)
    {
        Color3f[] colors = new Color3f[size];
        for (int i = 0; i< size; i++)
        {
            colors[i] = col;
        }

        return colors;
    }
}
