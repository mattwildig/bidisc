package uk.ac.ncl.a3707241.bidisc.visualisations;

import java.awt.*;
import java.util.*;

import javax.swing.*;

import javax.media.j3d.*;
import javax.vecmath.*;

import uk.ac.ncl.a3707241.bidisc.tools.*;
import uk.ac.ncl.a3707241.bidisc.viewer.*;
import uk.ac.ncl.a3707241.utils.*;
import uk.ac.ncl.a3707241.j3d.swing.*;

/**
 * The disc split into different shapes dependent on si value.
 * 
 * @author M.R.Wildig
 * @version August 2004
 */
public class SwitchVisualisation implements Visualisation
{
    private BranchGroup bg;

    private SwitchSlider sl;
    private JPanel panel;
    
    private double step = 0.05;

    public SwitchVisualisation()
    {
        Debug.message("Starting constructor");
        DiscGenerator gen = new SimpleDiscGenerator(new GridZGenerator(step), DiscGenerator.PROD_FIRST);
        
        Debug.message("About to get disc");
        Point4d[] disc = gen.getDisc();

        Switch sw = new Switch();
        sw.setCapability(Switch.ALLOW_SWITCH_READ);
        sw.setCapability(Switch.ALLOW_SWITCH_WRITE);
        sw.setCapability(Switch.ALLOW_CHILDREN_READ);
        
        int progTotal = 2 * (int)(2.0/step);
        int progress = 0;
        
        ProgressMonitor prog = new ProgressMonitor(null, "Generating Data", null, 0, progTotal);
        prog.setProgress(0);

        java.util.List pointsList = new ArrayList();
        
        Debug.message("Starting loop");
        for (double fixSi = -2.0; fixSi < (2 + (step/2)); fixSi+= step)
        {
            for (int i = 0; i < disc.length; i++)
            {
                if ((disc[i].w < (fixSi + step/2)) && (disc[i].w > (fixSi - step/2)))
                {
                    pointsList.add(new Point3d(disc[i].x, disc[i].y, disc[i].z));
                }
            }
            
            if (pointsList.size() > 0)
            {
                Point3d[] points = new Point3d[pointsList.size()];
                points = (Point3d[])pointsList.toArray(points);
                
                sw.addChild(ShapeCreator.getShape(points));
            }
            
            pointsList.clear();
            prog.setProgress(++progress);
        }
        
        disc = null; //keep freeing memory!
        
        sl = new SwitchSlider(sw);
        sl.setToolTipText("Alter the si value viewed by moving the slider");
        
        bg = new BranchGroup();
        bg.addChild(sw);
    }
    
    public void setUpUniverse(UniverseCustomiser uc)
    {
        uc.setAxisLabels("pr", "pi", "sr");
        uc.setAxisLength(2.0f);
        uc.setAxisLabelPosition(2.0f);
        uc.setDefaultViewDistance(8.0f);
        
        panel = new JPanel(new GridLayout(4, 0));
        uc.addViewActionsToPanel(panel);
        panel.add(sl);
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
