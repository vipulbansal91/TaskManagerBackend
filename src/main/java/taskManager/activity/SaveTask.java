package taskManager.activity;

import taskManager.dao.TaskDao;
import taskManager.model.Task;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by vbbansal on 3/10/17.
 */
@Path("/saveTask")
public class SaveTask
{
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.TEXT_HTML})
    public Response saveTask(Task taskToSave)
    {
        TaskDao taskDao = new TaskDao();
        
        if ((taskToSave.getTaskId() == null) || taskToSave.getTaskId().isEmpty())
        {
            //create task
            taskDao.createTask(taskToSave);

            return Response.status(Response.Status.CREATED).build();
        }
        else
        {
            //update task
        }
        
        return Response.status(Response.Status.OK).build();
    }
}
