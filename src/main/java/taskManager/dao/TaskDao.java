package taskManager.dao;

import taskManager.model.Task;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by vbbansal on 8/30/17.
 */
public interface TaskDao
{
    void deleteTask(int taskId);
    void createTask(Task task);
    List<Task> getTasksForDate(LocalDate date);
}
