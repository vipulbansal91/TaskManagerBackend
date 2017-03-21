package taskManager.exception;

/**
 * Created by vbbansal on 3/21/17.
 */
public class TaskManagerBackendRetryableException extends RuntimeException
{
    public TaskManagerBackendRetryableException(Exception e)
    {
        super(e);
    }

    public TaskManagerBackendRetryableException(String message)
    {
        super(message);
    }

    public TaskManagerBackendRetryableException(String message, Exception e)
    {
        super(message, e);
    }
}
