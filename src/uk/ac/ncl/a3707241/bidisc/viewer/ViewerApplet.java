package uk.ac.ncl.a3707241.bidisc.viewer;

import javax.swing.*;

/**
 * Enables the DiscViewer to appear as an applet.
 *
 * @author M.R.Wildig
 * @version August 2004
 */
public class ViewerApplet extends JApplet
{
	public void init()
	{
	    JLabel label = new JLabel("Viewer should appear in separate window");
	    label.setHorizontalAlignment(JLabel.CENTER);
	    getContentPane().add(label);
		new DiscViewer();

	}
}
