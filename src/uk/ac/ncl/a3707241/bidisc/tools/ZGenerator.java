package uk.ac.ncl.a3707241.bidisc.tools;

import uk.ac.ncl.a3707241.math.*;

/**
 * Provides an array of {@link uk.ac.ncl.a3707241.math.ComplexNumber}s for
 * generating visualisations of the disc.
 * 
 * @author M.R. Wildig
 * @version August 2004
 */

public interface ZGenerator
{
    /**
     * Gets an array of {@link uk.ac.ncl.a3707241.math.ComplexNumber}s.
     * 
     * @return an array of ComplexNumbers.
     */
    public ComplexNumber[] generateArray();
}
