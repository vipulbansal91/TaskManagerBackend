package taskManager.exception;

import lombok.experimental.ExtensionMethod;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

/**
 * Created by vbbansal on 3/25/17.
 */
@ExtensionMethod(taskManager.util.Extensions.class)
public class TaskManagerBackendExceptionMapper implements ExceptionMapper<Exception>
{
    @Override
    public Response toResponse(Exception e)
    {
        if (e instanceof TaskManagerBackendNonRetryableException)
        {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getStackTrace()).type("text/plain").build();
        }
        else if (e instanceof TaskManagerBackendRetryableException)
        {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getStackTrace()).type("text/plain")
                    .build();
        }
        else
        {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getStackTrace()).type("text/plain")
                    .build();
        }
    }
}
