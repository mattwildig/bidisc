package uk.ac.ncl.a3707241.utils;

/**
 * A Class to replace System.out.println().
 * Enables all debug statements to be turned off - so it is
 * impossible to forget to remove any
 * 
 * @author M.R.Wildig
 * @version August 2004
 */
public class Debug
{
    static boolean debugEnabled = false;
    
    static ClassFinder cf;

    /**
     * Writes a message to stout.
     * Prints out the name of the class this method was invoked from
     * followed by the message passed.
     * 
     * @param msg The message to write.
     */
    public static void message(String msg)
    {
        if (debugEnabled)
        {
            if (cf == null)
            {
                cf = new ClassFinder();
            }
            
            System.out.println(getCallingClass() + ": " + msg);
        }
    }

    /**
     * Turn on or off all debugging statements for the application.
     * 
     * @param enable Whether to enable debug messages or not.
     */
    public static void enableDebugging(boolean enable)
    {
        debugEnabled = enable;
    }
    
    private static String getCallingClass()
    {
        String caller;
        
        if (cf == null)
        {
            caller =  "Applet - no class info";
        }
        else
        {
            caller = cf.getCallingClass();
        }
        return caller;
    }

    private static class ClassFinder extends SecurityManager
    {
        ClassFinder(){};
        
        String getCallingClass()
        {
            return getClassContext()[2].getName();
        }
    }
}