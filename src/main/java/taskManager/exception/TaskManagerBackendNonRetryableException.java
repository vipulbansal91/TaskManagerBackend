package taskManager.exception;

/**
 * Created by vbbansal on 3/21/17.
 */
public class TaskManagerBackendNonRetryableException extends RuntimeException
{
    public TaskManagerBackendNonRetryableException(Exception e)
    {
        super(e);
    }
    
    public TaskManagerBackendNonRetryableException(String message)
    {
        super(message);
    }

    public TaskManagerBackendNonRetryableException(String message, Exception e)
    {
        super(message, e);
    }
}
