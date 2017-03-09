package taskManager.activity;

import taskManager.dao.TaskDao;
import taskManager.model.Task;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by vbbansal on 1/1/17.
 */
@Path("/getTasks")
public class GetTasks
{
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Task> getTasks(@QueryParam("date") String date)
    {
        if (date == null)
        {
            throw new UnsupportedOperationException();
        }

        TaskDao taskDao = new TaskDao();
        return taskDao.getTasksForDate(LocalDate.of(2017, 1, 12));
    }
}
