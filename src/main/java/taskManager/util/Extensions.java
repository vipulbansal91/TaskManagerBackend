package taskManager.util;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by vbbansal on 3/28/17.
 */
public class Extensions
{
    public static String getStackTrace(Exception e)
    {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        
        return sw.toString();
    }
}
