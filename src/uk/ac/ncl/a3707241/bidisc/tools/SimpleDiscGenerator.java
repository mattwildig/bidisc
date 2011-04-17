package uk.ac.ncl.a3707241.bidisc.tools;

import java.util.*;

import javax.vecmath.*;

import uk.ac.ncl.a3707241.math.*;
import uk.ac.ncl.a3707241.utils.*;

/**
 * Generates a Point4d[] from a single array of ComplexNumbers, with the
 * sum values as the first two values in each Point4d.
 * 
 * @author M.R. Wildig
 * @version August 2004
 */
public class SimpleDiscGenerator implements DiscGenerator
{
    private ZGenerator gen;
    private int order = SUM_FIRST;

    public SimpleDiscGenerator(ZGenerator zGen)
    {
        gen = zGen;
    }
    
    public SimpleDiscGenerator(ZGenerator zGen, int order)
    {
        this(zGen);
        this.order = order;
    }
    
    public Point4d[] getDisc()
    {
        Debug.message("About to get ComplexNumber[]");
        ComplexNumber[] numbers = gen.generateArray();
        Debug.message("Got array - length is " + numbers.length);
        
        List pointsList = new ArrayList();
        
        ComplexNumber s;
        ComplexNumber p;

        int iters = 0;        
        try
        {
            Debug.message("Starting loop");
        
        System.gc();
            
        for (int i=0; i<(numbers.length - 1); i++)
        {
            for (int j = i + 1; j < numbers.length; j++)
            {
                s = numbers[i].add(numbers[j]);
                p = numbers[i].times(numbers[j]);
                
                if (order == SUM_FIRST)
                {
                    pointsList.add(new Point4d(s.getReal(), s.getImg(), p.getReal(), p.getImg()));
                }
                else if (order == PROD_FIRST)
                {
                    pointsList.add(new Point4d(p.getReal(), p.getImg(), s.getReal(), s.getImg()));
                }
                else
                {
                    throw new RuntimeException("order must be DiscGenerator.SUM_FIRST or DiscGenerator.PROD_FIRST");
                }
            }
            
            numbers[i] = null;
            iters++;
        }
        
        }
        catch (OutOfMemoryError e)
        {
            Debug.message("Loops before error: " + iters);
        }
        
        
        numbers = null;

        Point4d[] points = new Point4d[pointsList.size()];
        points = (Point4d[])pointsList.toArray(points);
        pointsList = null;
        return points;
    }
    
    
}
