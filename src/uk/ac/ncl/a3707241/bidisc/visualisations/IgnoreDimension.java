package uk.ac.ncl.a3707241.bidisc.visualisations;

import java.awt.*;

import javax.swing.*;

import javax.media.j3d.*;
import javax.vecmath.*;

import uk.ac.ncl.a3707241.bidisc.tools.*;
import uk.ac.ncl.a3707241.bidisc.viewer.*;

/**
 * Creates a visualistaion of the disc where one of the
 * four dimensions is simply ignored.
 * 
 * The ignored dimension corressponds to the w value in 
 * the Point4d[] obtained from the call to getDisc on the 
 * DiscGenerator used.
 * 
 * @author M.R.Wildig
 * @version August 2004
 */
public class IgnoreDimension implements Visualisation
{
    private BranchGroup bg;
    private JPanel panel;

    public IgnoreDimension(DiscGenerator gen)
    {
        Point4d[] disc = gen.getDisc();
        
        Point3d[] points = new Point3d[disc.length];
        
        for (int i = 0; i < disc.length; i++)
        {
            points[i] = new Point3d(disc[i].x, disc[i].y, disc[i].z);
            disc[i] = null; //free that memory
        }
        disc = null;
        
        Shape3D shape = ShapeCreator.getShape(points);
        
        bg = new BranchGroup();
        bg.addChild(shape);
    }
    
    public void setUpUniverse(UniverseCustomiser uc)
    {
        uc.setAxisLength(2.0f);
        uc.setAxisLabelPosition(2.0f);
        uc.setDefaultViewDistance(8.0f);
        
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
