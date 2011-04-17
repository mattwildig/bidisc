package uk.ac.ncl.a3707241.bidisc.visualisations;

import uk.ac.ncl.a3707241.bidisc.tools.*;
import uk.ac.ncl.a3707241.bidisc.viewer.*;

/**
 * The Homogeneous visualisation from a complex numbers
 * with mod 1 and dividing by one of the p values.
 * 
 * @author M.R.Wildig
 * @version August 2004
 */
public class Mod1Homogeneous extends Homogeneous
{
    /**
     * Constructor for objects of class GridHomogeneous
     */
    public Mod1Homogeneous()
    {
        super(new SimpleDiscGenerator(new Mod1ZGenerator()));
    }

    public void setUpUniverse(UniverseCustomiser uc)
    {
        super.setUpUniverse(uc);
        uc.setAxisLength(8.0f);
        uc.setAxisLabelPosition(8.0f);
        uc.setDefaultViewDistance(20.0f);
        uc.setAxisLabels("sr/pi", "si/pi", "pr/pi");
    }
}
