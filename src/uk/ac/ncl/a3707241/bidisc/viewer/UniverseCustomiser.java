package uk.ac.ncl.a3707241.bidisc.viewer;

import java.awt.event.*;

import javax.swing.*;

import javax.vecmath.*;

import uk.ac.ncl.a3707241.j3d.utils.*;

/**
 * Used to set up a DiscUniverse to the demands of the visualisation.
 * 
 * @author M.R. Wildig
 * @version August 2004
 */
public class UniverseCustomiser
{
    //the universe to alter
    private DiscUniverse universe;
    //the axis therein
    private Axis axis;
    
    private Action viewX, viewY, viewZ;
    
    private String axisActionStub = "View down ";
    private String axisActionEnd = " axis";

    public UniverseCustomiser(DiscUniverse u)
    {
        universe = u;
        axis = universe.getAxis();
        setUpActions();
    }
    
    /**
     * Change the labels ont the axis to something suitable.
     *
     * @param x the new label for the x axis.
     * @param y the new label for the y axis.
     * @param z the new label for the z axis.
     */
    public void setAxisLabels(String x, String y, String z)
    {
        axis.setLabels(x, y, z);
        viewX.putValue(Action.NAME, axisActionStub + x + axisActionEnd);
        viewY.putValue(Action.NAME, axisActionStub + y + axisActionEnd);
        viewZ.putValue(Action.NAME, axisActionStub + z + axisActionEnd);
    }
    
    /**
     * Change the length of the axis.
     *
     * @param length the length of the axis from origin to the end.
     */
    public void setAxisLength(float length)
    {
        axis.setLength(length);
    }
    
    /**
     * Set the position of the label on the axis.  This should
     * normally be the same as the axis length.
     *
     * @param distance the distance from the origin the label are to be positioned.
     */
    public void setAxisLabelPosition(float distance)
    {
        axis.setLabelDistance(distance);
    }
    
    /**
     * Change the colour of the axis
     *
     * @param col the new colour of the axis.
     */
    public void setAxisColor(Color3f col)
    {
        //not yet implemented - do this rather than leave empty
        throw new UnsupportedOperationException("setAxisColor() is not implemented (yet!)");
    }
    
    /**
     * Set the distance the view returns to when the visualisation
     * is first displayed and when one of the View down xx
     * buttons is pressed.
     *
     * @param distance the new default viewing distance.
     */
    public void setDefaultViewDistance(float distance)
    {
        universe.setViewingDistance(distance);
    }
    
    public JButton getXButton()
    {
        return new JButton(viewX);
    }
    
    public JButton getYButton()
    {
        return new JButton(viewY);
    }
    
    public JButton getZButton()
    {
        return new JButton(viewZ);
    }

    /**
     * Add three buttons to a panel for resetting the view position
     * to be straight down each axis.  Call this method after
     * calling setAxisLabels().
     *
     * @param panel the JPanel the buttons will be added to.
     */
    public void addViewActionsToPanel(JPanel panel)
    {
        panel.add(getXButton());
        panel.add(getYButton());
        panel.add(getZButton());
    }

    /**
     * Sets up the actions for the "View down xx axis" buttons
     */
    private void setUpActions()
    {
        viewX = new AbstractAction(axisActionStub + "x" + axisActionEnd)
        {
            public void actionPerformed(ActionEvent e)
            {
                universe.viewX();
            }
        };
        
        viewY = new AbstractAction(axisActionStub + "x" + axisActionEnd)
        {
            public void actionPerformed(ActionEvent e)
            {
                universe.viewY();
            }
        };
        
        viewZ = new AbstractAction(axisActionStub + "x" + axisActionEnd)
        {
            public void actionPerformed(ActionEvent e)
            {
                universe.viewZ();
            }
        };
    }
}
