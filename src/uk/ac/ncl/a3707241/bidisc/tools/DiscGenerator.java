package uk.ac.ncl.a3707241.bidisc.tools;

import javax.vecmath.*;
/**
 * Creates an array of {@link javax.vecmath.Point4d}s to represent
 * the bidisc.
 * 
 * @author M.R. Wildig
 * @version August 2004
 */

public interface DiscGenerator
{
    /**
     * Used to specify the first two values of each Point4d represent 
     * the sum (real and imaginary) dimension in the disc.
     */
    public static final int SUM_FIRST = 0;
    
    /**
     * Used to specify the first two values of each Point4d represent 
     * the product (real and imaginary) dimension in the disc.
     */
    public static final int PROD_FIRST = 1;

    /**
     * Gets an array representing the disc.
     * 
     * @return an array of {@link javax.vecmath.Point4d}s representing the disc.
     */
    public Point4d[] getDisc();
}
