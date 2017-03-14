package taskManager.activity;

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
        if (taskToSave.getTaskId() == null)
        {
            //create task
        }
        else
        {
            //update task
        }
        
        return Response.status(Response.Status.OK).build();
    }
}
