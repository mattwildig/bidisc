package uk.ac.ncl.a3707241.bidisc.visualisations;

import uk.ac.ncl.a3707241.bidisc.tools.*;
import uk.ac.ncl.a3707241.bidisc.viewer.*;

/**
 * Provides a visualisation of the disc, generated from
 * a regular grid of points on the unit disc and ignoring
 * one dimension - in this case the imaginary part of the 
 * sum.
 * 
 * @author M.R. Wildig
 * @version August 2004
 */
public class ProdMod1IgnoreDimension extends IgnoreDimension
{
    public ProdMod1IgnoreDimension()
    {
        super(new SimpleDiscGenerator(new Mod1ZGenerator(), DiscGenerator.PROD_FIRST));
    }

    public void setUpUniverse(UniverseCustomiser uc)
    {
        super.setUpUniverse(uc);
        uc.setAxisLabels("pr", "pi", "sr");
    }
}
