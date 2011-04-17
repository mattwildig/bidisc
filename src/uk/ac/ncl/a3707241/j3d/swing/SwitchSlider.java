package uk.ac.ncl.a3707241.j3d.swing;

import javax.media.j3d.*;
import javax.swing.*;
import javax.swing.event.*;

/**
 * A JSlider component that controls which child of a Switch group is to be rendered.
 * 
 * @author M.R.Wildig
 * @version July 2004
 */
public class SwitchSlider extends JSlider
{
    private Switch controlledSwitch;

    public SwitchSlider()
    {}

    /**
     * Pass a javax.media.j3d.Switch object for the JSlider to control.
     */
    public SwitchSlider(Switch sw)
    {
        super(0, sw.numChildren());
        controlledSwitch = sw;
        
        setUpListener();
        fireStateChanged();
    }
    
    public void attachSwitch(Switch sw)
    {
        controlledSwitch = sw;
        setMinimum(0);
        setMaximum(controlledSwitch.numChildren());
        setUpListener();
        fireStateChanged();
    }
    
    private void setUpListener()
    {
        addChangeListener(new ChangeListener()
        {
            public void stateChanged(ChangeEvent e)
            {
                controlledSwitch.setWhichChild(getValue());
            }
        });
    }


}
