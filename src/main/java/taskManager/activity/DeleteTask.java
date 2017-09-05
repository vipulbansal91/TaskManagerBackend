package taskManager.activity;

import taskManager.dao.SqlLiteTaskDao;
import taskManager.dao.TaskDao;
import taskManager.exception.TaskManagerBackendNonRetryableException;

import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by vbbansal on 3/14/17.
 */
@Path("/deleteTask/{taskId}")
public class DeleteTask
{
    private TaskDao sqlLiteTaskDao = new SqlLiteTaskDao();
    
    @DELETE
    @Produces(MediaType.TEXT_HTML)
    public Response deleteTask(@PathParam("taskId") int taskId)
    {
        try
        {
            sqlLiteTaskDao.deleteTask(taskId);
        }
        catch (Exception e)
        {
            throw new TaskManagerBackendNonRetryableException(e);
        }
        
        return Response.status(Response.Status.OK).build();
    }
}
