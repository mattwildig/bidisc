package uk.ac.ncl.a3707241.math;   

/**
 * An implementation a complex number, using doubles to represent the real and imaginary components.
 * 
 * @author M.R. Wildig
 * @version June 2004
 */
public class ComplexNumber
{
    /**
     * The real and imaginary components of the complex number
     */
    private double real, img;
    
    /**
     * Create a complex number.
     * 
     * @param r the real component.
     * @param i the imaginary component.
     */
    public ComplexNumber(double r, double i)
    {
        real = r;
        img = i;
    }
    
    /**
     * Construct a ComplexNumber by specifying polar coordinates.
     * These are converted into real and imaginary components.
     * 
     * @param m the modulus
     * @param arg the argument
     * @param polar this shoud be true.  Using false here will create 
     * a ComplexNumber with real = m and imaginary = arg.
     */
    public ComplexNumber(double m, double arg, boolean polar)
    {
        if (polar) //need to check this !
        {
            real = m * Math.cos(arg);
            img = m * Math.sin(arg);
        }
        else //probably wont be used, but someone may specify false for the polar arg
        {
            real = m;
            img = arg;
        }
    }
    
    //getters:
    /**
     * Get the real component of this number.
     * 
     * @return the real component of this number.
     */
    public double getReal() {return real;}
    
    /**
     * Get the imaginary component of this number.
     * 
     * @return the imaginary component of this number.
     */
    public double getImg() {return img;}
    
    /**
     * Add this complex number to another, and return the result as 
     * a new ComplexNumber.
     * 
     * N.B. The value of this ComplexNumber is not altered.
     *
     * @param other the ComplexNumber to add to this.
     *
     * @return the sum of this ComplexNumber and <code>other</code>.
     */
    public ComplexNumber add(ComplexNumber other)
    {
        return new ComplexNumber(real + other.real, img + other.img);
    }
    
    /**
     * Multiply this complex number with another, and return the result as 
     * a new ComplexNumber.
     * <br>
     * <br>
     * N.B. The value of this ComplexNumber is not altered.
     *
     * @param other the ComplexNumber to multiply with this.
     *
     * @return the product of this ComplexNumber and <code>other</code>.
     */
    public ComplexNumber times(ComplexNumber other)
    {
        double newReal = (real * other.real) - (img * other.img);
        double newImg = (real * other.img) + (img * other.real);
        
        return new ComplexNumber(newReal, newImg);
    }
    
    /**
     * Get the modulus of this ComplexNumber.
     * 
     * @return the modulus of this ComplexNumber.
     */
    public double modulus()
    {
        return Math.sqrt((real * real) + (img * img));
    }
    
    /**
     * Get the sqaure of the modulus.  Intended to avoid calculating a square root when it can be avoided.
     * 
     * @return the square of the modulus of this number.
     */
    public double modSquared()
    {
        return (real * real) + (img * img);
    }

}
