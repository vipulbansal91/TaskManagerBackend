package taskManager.dao;

import taskManager.model.Task;
import taskManager.model.TaskState;
import taskManager.model.TaskType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vbbansal on 1/9/17.
 */
public class TaskDao
{
    public List<Task> getTasksForDate(LocalDate date)
    {
        return  getDummyTasks();
    }

    private List<Task> getDummyTasks()
    {
        List<Task> tasks = new ArrayList<Task>();

        Task task1 = new Task();
        task1.setTaskId(1);
        task1.setTaskType(TaskType.IMPORTANT_URGENT);
        task1.setTaskContent("I am important and urgent");
        task1.setLocalDate(LocalDate.of(2017,1,12));
        task1.setTaskState(TaskState.OPEN);
        tasks.add(task1);

        Task task2 = new Task();
        task2.setTaskId(2);
        task2.setTaskType(TaskType.IMPORTANT_NONURGENT);
        task2.setTaskContent("I am important and non-urgent");
        task2.setLocalDate(LocalDate.of(2017,1,12));
        task1.setTaskState(TaskState.STRIKED);
        tasks.add(task2);

        Task task3 = new Task();
        task3.setTaskId(3);
        task3.setTaskType(TaskType.NONIMPORTANT_URGENT);
        task3.setTaskContent("I am non-important and urgent");
        task3.setLocalDate(LocalDate.of(2017,1,12));
        task1.setTaskState(TaskState.STRIKED);
        tasks.add(task3);

        Task task4 = new Task();
        task4.setTaskId(4);
        task4.setTaskType(TaskType.NONIMPORTANT_NONURGENT);
        task4.setTaskContent("I am non-important and non-urgent");
        task4.setLocalDate(LocalDate.of(2017,1,12));
        task1.setTaskState(TaskState.DELETED);
        tasks.add(task4);

        return tasks;
    }
}
