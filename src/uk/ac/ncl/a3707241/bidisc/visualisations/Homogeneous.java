package uk.ac.ncl.a3707241.bidisc.visualisations;

import java.awt.*;

import javax.swing.*;

import javax.media.j3d.*;
import javax.vecmath.*;

import uk.ac.ncl.a3707241.bidisc.tools.*;
import uk.ac.ncl.a3707241.bidisc.viewer.*;

/**
 * Visualisation where each Point4d representing a point on the disc is
 * projected to the 3d point (x/w, y/w, z/w).
 * 
 * @author M.R. Wildig
 * @version August 2004
 */
public class Homogeneous implements Visualisation
{
    private BranchGroup bg;
    
    private JPanel panel;

    /**
     * Constructor for objects of class Homogeneous
     */
    public Homogeneous(DiscGenerator gen)
    {
        Point4d[] disc = gen.getDisc();
        
        Point3d[] points = new Point3d[disc.length];

        Point4d temp = new Point4d();
        for (int i = 0; i < disc.length; i++)
        {
            temp.project(disc[i]);
            points[i] = new Point3d(temp.x, temp.y, temp.z);
            disc[i] = null;
        }
        disc = null;
        
        Shape3D shape = ShapeCreator.getShape(points);
        
        bg = new BranchGroup();
        bg.addChild(shape);
    }

    public void setUpUniverse(UniverseCustomiser uc)
    {
        panel = new JPanel(new GridLayout(3, 0));
        uc.addViewActionsToPanel(panel);
    }

    public BranchGroup getBranchGroup()
    {
        return bg;
    }
    
    public JPanel getGUI()
    {
        return panel;
    }
}
