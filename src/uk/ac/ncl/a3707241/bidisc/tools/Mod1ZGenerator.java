package uk.ac.ncl.a3707241.bidisc.tools;

import uk.ac.ncl.a3707241.math.*;
/**
 * Generate an array of {@link uk.ac.ncl.a3707241.math.ComplexNumber}s.  All numbers generated
 * have a modulus of 1.
 * 
 * @author M.R.Wildig
 * @version August 2004
 */
public class Mod1ZGenerator implements ZGenerator
{
    private static final double ONE = 1.0;
    private double gap = Math.PI/50;

    /**
     * Creates a Mod1ZGenerator with the default spacing (angle between successive points) of &pi;/50.
     */
    public Mod1ZGenerator(){}
    
    /**
     * Creates a Mod1ZGenerator with the specified spacing.
     * 
     * @param newGap the spacing to use (in Radians).
     */
    public Mod1ZGenerator(double newGap)
    {
        gap = newGap;
    }
    
    public ComplexNumber[] generateArray()
    {
        ComplexNumber[] numbers = new ComplexNumber[100];
        
        for (int i = 0; i<100; i++)
        {
            numbers[i] = new ComplexNumber(ONE, (i*gap), true);
        }
        return numbers;
    }

}
