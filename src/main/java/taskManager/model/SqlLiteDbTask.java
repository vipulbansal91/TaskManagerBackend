package taskManager.model;

import lombok.Data;
import taskManager.exception.TaskManagerBackendNonRetryableException;

/**
 * Created by vbbansal on 3/20/17.
 */
@Data
public class SqlLiteDbTask implements DbTask
{
    int id;
    String content;
    String type;
    String state;
    String date;
    
    public SqlLiteDbTask(Task applicationTask)
    {
        if ((applicationTask.getTaskId() != null) && (applicationTask.getTaskId().isEmpty() == false))
        {
            try
            {
                this.setId(Integer.parseInt(applicationTask.getTaskId()));
            }
            catch (NumberFormatException e)
            {
                throw new TaskManagerBackendNonRetryableException("TaskId should be a valid integer");
            }
        }
        this.setContent(applicationTask.getTaskContent());
        this.setType(applicationTask.getTaskType().toString());
        this.setState(applicationTask.getTaskState().toString());
        this.setDate(applicationTask.getLocalDate().toString());
    }
}
