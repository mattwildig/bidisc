package uk.ac.ncl.a3707241.j3d.swing;

/**
 * Interface to be implemented by programs that take a while to initialise.
 * Allows initialisation to be done in a separate thread which can use this interface to inform 
 * GUI and allow it to indicate to the user the program is ready.
 * 
 * @author M.R. Wildig
 * @version July 2004
 */

public interface DelayedStartGUI
{
    void enableGUI();
}
