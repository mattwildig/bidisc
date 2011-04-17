package uk.ac.ncl.a3707241.bidisc.viewer;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.*;

import javax.swing.*;

import javax.media.j3d.*;

import com.sun.j3d.utils.universe.*;

import uk.ac.ncl.a3707241.utils.*;

/**
 * The main, visible part of the program.
 * 
 * @author M.R.Wildig
 * @version August 2004
 */
public class DiscViewer
{
    private static final String titleStub = "Symmetrised Bi-Disc";

    private Map visualisations;
    private String visualisationFile = "resources/visfile.txt";
    
    private Canvas3D can;
    private DiscUniverse universe;
    private UniverseCustomiser custom;
    
    private JFrame frame;
    private JPanel controls;
    
    public DiscViewer()
    {
        frame = new JFrame(titleStub);
        frame.addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                // this is to try to prevent D3DERR_OUTOFVIDEOMEMORY errors and otherwise allow Java3D to tidy itself up
                universe.removeAllLocales();
            }
        });
        
        try
        {
            // check if running as an applet:
            SecurityManager sm = System.getSecurityManager();
            if (sm != null)
            {
                sm.checkExit(0);
            }
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
        catch (SecurityException se)
        {
            // must be running as an applet or otherwise restricted
            frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        }
        
        visualisations = new HashMap();
        readVisualisationFile(visualisations);
        
        VisMenuListener listen = new VisMenuListener();
        
        //because canvas 3D is heavyweight we must do this
        JPopupMenu.setDefaultLightWeightPopupEnabled(false);
        //and this in case some controls use Tooltips
        ToolTipManager.sharedInstance().setLightWeightPopupEnabled(false);
        
        JMenuBar menuBar = new JMenuBar();
        
        Iterator it = visualisations.keySet().iterator();
        JMenu visMenu = new JMenu("Visualisations");
        
        menuBar.add(visMenu);

        while (it.hasNext())
        {
            JMenuItem visItem = new JMenuItem((String)it.next());
            visItem.addActionListener(listen);
            visMenu.add(visItem);
        }

        frame.setJMenuBar(menuBar);
        
        //set up universe
        can = new Canvas3D(SimpleUniverse.getPreferredConfiguration());
        can.setSize(550, 550);
        universe = new DiscUniverse(can);
        custom = new UniverseCustomiser(universe);
        
        frame.getContentPane().add(can);
        
        frame.pack();
        frame.setVisible(true);
    }
    
    /**
     * Read the visualisations file into the HashMap
     */
    private void readVisualisationFile(Map map)
    {
        InputStream is = getClass().getClassLoader().getResourceAsStream(visualisationFile);
        BufferedReader in = new BufferedReader(new InputStreamReader(is));

        StringTokenizer tokens;
        String line = null, name, className;
        while (true)
        {
            try
            {
                line = in.readLine();
            }
            catch (IOException ioe)
            {
                Exceptions.raiseFatal("Error reading visualisations file", ioe);
            }
            
            if (line == null)
            {
                break;
            }
            
            tokens = new StringTokenizer(line);
            
            try
            {
                name = tokens.nextToken();
                className = tokens.nextToken();
                
                map.put(name, className);
            }
            catch (NoSuchElementException nsee)
            {
                Exceptions.raise("Visualisation file format incorrect", nsee);
            }
        }
    }
    
    /**
     * Instantiate the correct Visualisation and display it when selecting from 
     * the Visualisation menu.
     */
    private class VisMenuListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            String name = ((JMenuItem)e.getSource()).getText();
            String className = (String)visualisations.get(name);
            Visualisation vis = null;
            try
            {
                vis = (Visualisation)Class.forName(className).newInstance();
            }
            catch (ClassNotFoundException cnfe)
            {
                Exceptions.raise("Cannot find class file for visualisation: " + name, cnfe);
            }
            catch (InstantiationException ie)
            {
                Exceptions.raise("InstantiationException when instantiating " + className, ie);
            }
            catch (IllegalAccessException iae)
            {
                Exceptions.raise("IllegalAccessException when instantiating " +className, iae);
            }
            
            if (vis != null)
            {
                if (controls != null)
                {
                    frame.remove(controls);
                }
                vis.setUpUniverse(custom);
                controls = vis.getGUI();
                
                if (controls != null)
                {
                    frame.getContentPane().add(controls, BorderLayout.WEST);
                }
                frame.pack();
                universe.setContent(vis.getBranchGroup());
                
                frame.setTitle(titleStub + " - " + name);
            }
        }
    }
    
    public static void main(String[] args)
    {
        //Uncomment next line to enable debugging messages
        //Debug.enableDebugging(true);
        new DiscViewer();
    }
}
