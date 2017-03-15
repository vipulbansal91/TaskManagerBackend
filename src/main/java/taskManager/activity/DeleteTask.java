package taskManager.activity;

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
    @DELETE
    @Produces(MediaType.TEXT_HTML)
    public Response deleteTask(@PathParam("taskId") String taskId)
    {
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
