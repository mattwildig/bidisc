package uk.ac.ncl.a3707241.bidisc.viewer;

import com.sun.j3d.utils.universe.*;
import com.sun.j3d.utils.behaviors.vp.*;
import javax.media.j3d.*;
import javax.vecmath.*;
import uk.ac.ncl.a3707241.j3d.utils.*;

/**
 * A SimpleUniverse with the ViewPlatform positioning and axis.
 * 
 * @author M.R. Wildig
 * @version July 2004
 */
public class DiscUniverse extends SimpleUniverse
{
    private Transform3D viewX;
    private Transform3D viewY;
    private Transform3D viewZ;
    
    private float viewDistance = 6.0f;
    private float axisLength = 2.0f;
    
    private Axis axis;
    private String xLabel = "x";
    private String yLabel = "y";
    private String zLabel = "z";
    
    private BranchGroup content;
    private Bounds bounds;
    
    public DiscUniverse(Canvas3D can)
    {
        super(can);
        bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 6.0);
        
        setViewTransforms();
        
        //behaviours
        OrbitBehavior orbit = new OrbitBehavior(can, OrbitBehavior.REVERSE_TRANSLATE | OrbitBehavior.REVERSE_ROTATE);
        orbit.setSchedulingBounds(bounds);
        getViewingPlatform().setViewPlatformBehavior(orbit);
        
        //add a background - white
        Background bkgd = new Background(1.0f, 1.0f, 1.0f);
        bkgd.setApplicationBounds(bounds);
        BranchGroup bg = new BranchGroup();
        bg.addChild(bkgd);
        addBranchGraph(bg);
        
        //create axis
        axis = new Axis(new Color3f(0.7f, 0.7f, 0.7f));
        addBranchGraph(axis);
    }
    
    /**
     * getter method
     */
    public Axis getAxis()
    {
        return axis;
    }
    
    private void setViewTransforms()
    {
        viewZ = new Transform3D();
        viewZ.setTranslation(new Vector3f(0.0f, 0.0f, viewDistance));
        
        Transform3D temp = new Transform3D();
        
        temp.rotY(Math.PI / 2);
        viewX = new Transform3D();
        viewX.setTranslation(new Vector3f(viewDistance, 0.0f, 0.0f));
        viewX.mul(temp);
        
        temp.rotX(-Math.PI / 2);
        viewY = new Transform3D();
        viewY.setTranslation(new Vector3f(0.0f, viewDistance, 0.0f));
        viewY.mul(temp);
        
        //start with view down z axis
        getViewingPlatform().getViewPlatformTransform().setTransform(viewZ);
    }
    
    /**
     * Sets the distance of the viewing plane to the origin.
     *
     * @param distance the new distance of the ViewPlatform
     */
    public void setViewingDistance(float distance)
    {
        viewDistance = distance;
        setViewTransforms();
    }
    
    /**
     * Changes the content branch of the scene graph.
     */
    public void setContent(BranchGroup bg)
    {
        bg.setCapability(BranchGroup.ALLOW_DETACH);
        if (content != null)
        {
            content.detach();
        }
        content = bg;
        addBranchGraph(bg);
    }
    
    /**
     * view*() methods all set the viewing platform to be from appropriate axis, at a distance of viewingDistance.
     */
    public void viewX()
    {
        getViewingPlatform().getViewPlatformTransform().setTransform(viewX);
    }

    public void viewY()
    {
        getViewingPlatform().getViewPlatformTransform().setTransform(viewY);
    }

    public void viewZ()
    {
        getViewingPlatform().getViewPlatformTransform().setTransform(viewZ);
    }
}
