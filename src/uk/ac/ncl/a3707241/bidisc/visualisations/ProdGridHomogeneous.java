package uk.ac.ncl.a3707241.bidisc.visualisations;

import uk.ac.ncl.a3707241.bidisc.tools.*;
import uk.ac.ncl.a3707241.bidisc.viewer.*;

/**
 * The Homogeneous visualisation from a regular grid
 * of complex numbers and dividing by one of the s values.
 * 
 * @author M.R.Wildig
 * @version August 2004
 */
public class ProdGridHomogeneous extends Homogeneous
{
	/**
	 * Constructor for objects of class ProdGridHomogeneous
	 */
	public ProdGridHomogeneous()
	{
		super(new SimpleDiscGenerator(new GridZGenerator(), DiscGenerator.PROD_FIRST));
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
