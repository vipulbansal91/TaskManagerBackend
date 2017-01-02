package taskManager.activity;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

/**
 * Created by vbbansal on 1/1/17.
 */
@Path("/getTasks")
public class GetTasks
{
    @GET
    @Produces("text/plain")
    public String getTasks(@QueryParam("date") String date)
    {
        if (date == null)
        {
            throw new UnsupportedOperationException();
        }

        return "hola" + date;
    }
}
