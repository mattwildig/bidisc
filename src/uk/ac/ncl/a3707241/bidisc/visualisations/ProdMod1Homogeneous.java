package uk.ac.ncl.a3707241.bidisc.visualisations;

import uk.ac.ncl.a3707241.bidisc.tools.*;
import uk.ac.ncl.a3707241.bidisc.viewer.*;

/**
 * The Homogeneous visualisation from a complex numbers
 * with mod 1 and dividing by one of the s values.
 * 
 * @author M.R.Wildig
 * @version August 2004
 */
public class ProdMod1Homogeneous extends Homogeneous
{
	/**
	 * Constructor for objects of class ProdMod1Homogeneous
	 */
	public ProdMod1Homogeneous()
	{
		super(new SimpleDiscGenerator(new Mod1ZGenerator(), DiscGenerator.PROD_FIRST));
	}

	public void setUpUniverse(UniverseCustomiser uc)
    {
        super.setUpUniverse(uc);
        uc.setAxisLength(6.0f);
        uc.setAxisLabelPosition(6.0f);
        uc.setDefaultViewDistance(18.0f);
        uc.setAxisLabels("pr/si", "pi/si", "sr/si");
    }
}
