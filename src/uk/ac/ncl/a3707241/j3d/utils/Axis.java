package uk.ac.ncl.a3707241.j3d.utils;

import javax.media.j3d.*;
import javax.vecmath.*;
//import com.sun.j3d.utils.geometry.*;
import java.awt.Font;

/**
 * A set of x, y and z axis in the color of your choice.
 * 
 * @author M.R.Wildig
 * @version June / July 2004
 */
public class Axis extends BranchGroup
{
    private static final Point3f zero = new Point3f(0.0f, 0.0f, 0.0f);
    private static final Bounds bounds = new BoundingSphere();

    private Shape3D lines;
    private LineArray lineData;
    
    //default labels
    private String xLabel = "x";
    private String yLabel = "y";
    private String zLabel = "z";
    
    private Text2D x;
    private Text2D y;
    private Text2D z;
    
    private TransformGroup xtg;
    private TransformGroup ytg;
    private TransformGroup ztg;
    
    private float axisLength = 2.0f; //length of axis from origin
    private Color3f col = new Color3f(0.0f, 0.0f, 0.0f); //default to black
    private Color3f labelCol = col; //also default to black

    /**
     * No arg constructor, uses defaults: black, length 2.0, labels x, y & z
     */
    public Axis()
    {
        createShape();
    }

    public Axis(Color3f col)
    {
        this.col = col;
        createShape();
    }

    public Axis(Color3f col, String x, String y, String z)
    {
        this.col = col;
        xLabel = x;
        yLabel = y;
        zLabel = z;
        createShape();
    }
    
    private void createShape()
    {
        lineData = new LineArray(6, GeometryArray.COORDINATES | GeometryArray.COLOR_3);
        lineData.setCapability(GeometryArray.ALLOW_COORDINATE_WRITE);
        lineData.setCoordinates(0, getCoords());
        lineData.setColors(0, getColors(col));
        lines = new Shape3D(lineData);
        lines.setCapability(Shape3D.ALLOW_GEOMETRY_WRITE);
        addChild(lines);
        
        x = getText2D(xLabel);
        xtg = setUpLabelPosition(x, new Vector3f(axisLength, 0.0f, 0.0f));
        setLabelBillboard(xtg, x);
        
        y = getText2D(yLabel);
        ytg = setUpLabelPosition(y, new Vector3f(0.0f, axisLength, 0.0f));
        setLabelBillboard(ytg, y);
        
        z = getText2D(zLabel);
        ztg = setUpLabelPosition(z, new Vector3f(0.0f, 0.0f, axisLength));
        setLabelBillboard(ztg, z);
        
        /* removed to separate method
        x = new Text2D(xLabel, col, "SansSerif", 50, Font.PLAIN);
        x.setCapability(Shape3D.ALLOW_APPEARANCE_READ);
        x.setCapability(Shape3D.ALLOW_APPEARANCE_WRITE);
        xtg = new TransformGroup();
        xtg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        Transform3D xt = new Transform3D();
        xt.set(new Vector3f(axisLength, 0.0f, 0.0f));
        xtg.setTransform(xt);
        TransformGroup xBillTran = new TransformGroup();
        xBillTran.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        Billboard xBill = new Billboard(xBillTran, Billboard.ROTATE_ABOUT_POINT, zero);
        xBill.setSchedulingBounds(bounds);
        xBillTran.addChild(xBill);
        xtg.addChild(xBillTran);
        xBillTran.addChild(x);
        addChild(xtg);
        
        y = new Text2D(yLabel, col, "SansSerif", 50, Font.PLAIN);
        ytg = new TransformGroup();
        ytg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        Transform3D yt = new Transform3D();
        yt.set(new Vector3f(0.0f, axisLength, 0.0f));
        ytg.setTransform(yt);
        TransformGroup yBillTran = new TransformGroup();
        yBillTran.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        Billboard yBill = new Billboard(yBillTran, Billboard.ROTATE_ABOUT_POINT, zero);
        yBill.setSchedulingBounds(bounds);
        yBillTran.addChild(yBill);
        ytg.addChild(yBillTran);
        yBillTran.addChild(y);
        addChild(ytg);
        
        z = new Text2D(zLabel, col, "SansSerif", 50, Font.PLAIN);
        ztg = new TransformGroup();
        Transform3D zt = new Transform3D();
        ztg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        zt.set(new Vector3f(0.0f, 0.0f, axisLength));
        ztg.setTransform(zt);
        TransformGroup zBillTran = new TransformGroup();
        zBillTran.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        Billboard zBill = new Billboard(zBillTran, Billboard.ROTATE_ABOUT_POINT, zero);
        zBill.setSchedulingBounds(bounds);
        zBillTran.addChild(zBill);
        ztg.addChild(zBillTran);
        zBillTran.addChild(z);
        addChild(ztg);
        */
    }
    
    private Text2D getText2D(String label)
    {
        Text2D text = new Text2D(label, labelCol, "SansSerif", 50, Font.PLAIN);
        
        text.setCapability(Text2D.ALLOW_GEOMETRY_WRITE);
        text.setCapability(Text2D.ALLOW_APPEARANCE_READ);
        text.setCapability(Text2D.ALLOW_APPEARANCE_WRITE);
        text.getAppearance().setCapability(Appearance.ALLOW_TEXTURE_READ);
        text.getAppearance().setCapability(Appearance.ALLOW_TEXTURE_WRITE);
        text.getAppearance().getTexture().setCapability(Texture.ALLOW_IMAGE_READ);
        text.getAppearance().getTexture().setCapability(Texture.ALLOW_IMAGE_WRITE); 
        
        return text;
    }
    
    private TransformGroup setUpLabelPosition(Text2D text, Vector3f posVec)
    {
        TransformGroup tg = new TransformGroup();
        tg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        Transform3D t3d = new Transform3D();
        t3d.set(posVec);
        tg.setTransform(t3d);
        
        return tg;
    }

    private void setLabelBillboard(TransformGroup tg, Text2D text)
    {
        TransformGroup billTran = new TransformGroup();
        billTran.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        
        Billboard bill = new Billboard(billTran, Billboard.ROTATE_ABOUT_POINT, zero);
        bill.setSchedulingBounds(bounds);
        billTran.addChild(bill);
        tg.addChild(billTran);
        billTran.addChild(text);
        addChild(tg);
    }

    public void setLength(float newLength)
    {
        axisLength = newLength;
        lineData.setCoordinates(0, getCoords());
        //Is this line required?
        lines.setGeometry(lineData);
    }
    
    public void setLabels(String newX, String newY, String newZ)
    {
        x.setString(newX);
        y.setString(newY);
        z.setString(newZ);
    }
    
    public void setLabelDistance(float distance)
    {
        Transform3D t = new Transform3D();
        
        t.set(new Vector3f(axisLength, 0.0f, 0.0f));
        xtg.setTransform(t);
        
        t.set(new Vector3f(0.0f, axisLength, 0.0f));
        ytg.setTransform(t);
        
        t.set(new Vector3f(0.0f, 0.0f, axisLength));
        ztg.setTransform(t);
    }

    private float[] getCoords()
    {
        float[] points =
        {
            -axisLength, 0.0f, 0.0f,  //x-axis
            axisLength, 0.0f, 0.0f,
            0.0f, -axisLength, 0.0f,  //y-axis
            0.0f, axisLength, 0.0f,
            0.0f, 0.0f, -axisLength,  //z-axis
            0.0f, 0.0f, axisLength
        };
        
        return points;
    }

    private Color3f[] getColors(Color3f col)
    {
        Color3f[] colors = new Color3f[6];
        
        for (int i = 0; i < colors.length; i++)
        {
            colors[i] = col;
        }

        return colors;
    }
}
