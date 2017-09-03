package taskManager.activity;

import taskManager.dao.SqlLiteTaskDao;
import taskManager.dao.TaskDao;
import taskManager.exception.TaskManagerBackendNonRetryableException;
import taskManager.model.Task;
import taskManager.serDeser.LocalDateAdapter;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by vbbansal on 1/1/17.
 */
@Path("/getTasks")
public class GetTasks
{
    private TaskDao sqlLiteTaskDao = new SqlLiteTaskDao();
    private LocalDateAdapter localDateAdapter = new LocalDateAdapter();
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Task> getTasks(@QueryParam("date") String date)
    {
        System.out.println(date);
        
        if (date == null)
        {
            throw new UnsupportedOperationException();
        }

        try
        {
            List<Task> tasks = sqlLiteTaskDao.getTasksForDate(localDateAdapter.unmarshal(date));
            System.out.println(tasks);
            return tasks;
        }
        catch (Exception e)
        {
            throw new TaskManagerBackendNonRetryableException(e);
        }
    }
}
