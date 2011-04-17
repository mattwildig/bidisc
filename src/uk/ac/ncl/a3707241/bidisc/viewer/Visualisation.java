package uk.ac.ncl.a3707241.bidisc.viewer;

import javax.swing.*;
import javax.media.j3d.*;

/**
 * Interface all visualisations must implement to be viewable by
 * DiscViewer.
 * 
 * @author M.R.Wildig
 * @version August 2004
 */

public interface Visualisation
{
    /**
     * Set up the required details of the axis, viewing distances etc. for this
     * visualisation.
     */
    public void setUpUniverse(UniverseCustomiser uc);
    
    /**
     * @return The BranchGroup representing this visualusation
     */
    public BranchGroup getBranchGroup();
    
    /**
     * @return A panel with any GUI controls required by this visualisation.
     */
    public JPanel getGUI();
}
