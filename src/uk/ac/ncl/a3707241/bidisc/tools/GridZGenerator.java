package uk.ac.ncl.a3707241.bidisc.tools;

import java.util.*;

import uk.ac.ncl.a3707241.math.*;

/**
 * Create an array of {@link uk.ac.ncl.a3707241.math.ComplexNumber}s for use in generating members of the set.
 * All points are in the closed unit disc (all have modulus <= 1), and form a regularly spaced grid in the 
 * complex plane.
 * 
 * @author M.R.Wildig
 * @version July 2004
 */
public class GridZGenerator implements ZGenerator
{
    /**
     * The gap between successive values in the two loops.
     * Setting this value too low can cause OutOfMemoryErrors.
     */
    private double step = 0.1;
    
    /**
     * Constructs a GridZGenerator with the default grid spacing (0.1).
     */
    public GridZGenerator(){}
    
    /**
     * Constructs a GridZGenerator with the specified spacing.
     * 
     * @param s the spacing to use between points.
     */
    public GridZGenerator(double s)
    {
        step = s;
    }
    
    /**
     * Generates and returns an array of {@link uk.ac.ncl.a3707241.math.ComplexNumber}s
     * using the current spacing.
     * 
     * @return an array of <code>ComplexNumber</code>s
     */
    public ComplexNumber[] generateArray()
    {
        List numberList = new ArrayList();
        
        for (double r = -1.0; r < (1.0 + step); r += step)
        {
            for (double i = -1.0; i < (1.0 + step); i += step)
            {
                ComplexNumber c = new ComplexNumber(r, i);
                
                if (c.modSquared() <= 1.0)
                {
                    numberList.add(c);
                }
            }
        }
        
        ComplexNumber[] numArray = new ComplexNumber[numberList.size()];
        numArray = (ComplexNumber[])numberList.toArray(numArray);
        return numArray;
    }
    
    /**
     * Changes the spacing between points generated in the array.
     * 
     * @param newStep the new spacing to use.
     */
    public void setStep(double newStep)
    {
        step = newStep;
    }
}
