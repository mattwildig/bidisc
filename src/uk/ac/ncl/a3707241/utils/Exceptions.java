package uk.ac.ncl.a3707241.utils;

/**
 * A class to make dealing with exceptions easier.
 * 
 * @author M.R. Wildig
 * @version August 2004
 */
public class Exceptions
{
    /**
     * Write a message to System.err and continue.
     */
    public static void raise(String msg, Throwable e)
    {
        System.err.println(msg + " " + e);
    }
    
    /**
     * Write a message to System.err and terminate with status of 1.
     */
    public static void raiseFatal(String msg, Throwable e)
    {
        System.err.println(msg + " " + e);
        exit(1);
    }
    
    /**
     * Write a message to System.err and terminate given status.
     */
    public static void raiseFatal(String msg, Throwable e, int status)
    {
        System.err.println(msg + " " + e);
        exit(status);
    }
    
    /**
     * If not running in as an applet then calls System.exit().
     * Else ...
     * 
     */
    public static void exit(int status)
    {
         try
         {
             SecurityManager sm = System.getSecurityManager();
             if (sm != null)
             {
                 System.getSecurityManager().checkExit(status);
             }
            System.exit(status);
         }
         catch (SecurityException se)
         {
             
         }
    }

}
