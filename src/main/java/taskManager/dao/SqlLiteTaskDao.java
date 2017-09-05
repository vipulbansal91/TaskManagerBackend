package taskManager.dao;

import org.springframework.stereotype.Component;
import taskManager.exception.TaskManagerBackendNonRetryableException;
import taskManager.model.Task;
import taskManager.model.TaskState;
import taskManager.model.TaskType;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vbbansal on 1/9/17.
 */
@Component
public class SqlLiteTaskDao implements TaskDao
{
    private static String ID_COLUMN_LABEL = "ID";
    private static String CONTENT_COLUMN_LABEL = "CONTENT";
    private static String TYPE_COLUMN_LABEL = "TYPE";
    private static String STATE_COLUMN_LABEL = "STATE";
    private static String DATE_COLUMN_LABEL = "DATE";
    
    public boolean setup()
    {
        try
        {
            Connection c = getConnection();
            createTable(c);
            c.close();
        }
        catch (SQLException e)
        {
            throw new TaskManagerBackendNonRetryableException(e);
        }

        return true;
    }

    private Connection getConnection()
    {
        Connection c = null;

        try
        {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:/Users/vbbansal/Documents/TaskManagerBackend/test.db");
        }
        catch (ClassNotFoundException | SQLException e)
        {
            throw new TaskManagerBackendNonRetryableException(e);
        }

        return c;
    }

    private void createTable(Connection c)
    {
        Statement stmt = null;
        try
        {
            stmt = c.createStatement();

            String sql = "CREATE TABLE IF NOT EXISTS TASKS " +
                    "(ID INTEGER PRIMARY KEY NOT NULL," +
                    " CONTENT      TEXT NOT NULL, " +
                    " TYPE         TEXT NOT NULL, " +
                    " STATE        TEXT NOT NULL, " +
                    " DATE         TEXT NOT NULL)";
            stmt.executeUpdate(sql);
            stmt.close();
        }
        catch (SQLException e)
        {
            throw new TaskManagerBackendNonRetryableException(e);
        }
    }

    @Override
    public void deleteTask(int taskId)
    {
        try
        {
            Connection c = getConnection();
            this.deleteFromTasks(c, taskId);
            c.close();
        }
        catch (SQLException e)
        {
            throw new TaskManagerBackendNonRetryableException(e);
        }

    }
    
    private void deleteFromTasks(Connection c, int id)
    {
        Statement stmt;
        try
        {
            stmt = c.createStatement();
            
            String sql = "DELETE FROM TASKS " +
                    "WHERE " + ID_COLUMN_LABEL + "=" + id;
            stmt.execute(sql);
            stmt.close();
        }
        catch (SQLException e)
        {
            throw new TaskManagerBackendNonRetryableException(e);
        }
    }
    
    @Override
    public void createTask(Task task)
    {
        this.insertIntoTasks(task);
    }

    private void insertIntoTasks(Task task)
    {
        try
        {
            Connection c = getConnection();
            this.insertIntoTable(c, task);
            c.close();
        }
        catch (SQLException e)
        {
            throw new TaskManagerBackendNonRetryableException(e);
        }
    }

    private void insertIntoTable(Connection c, Task task)
    {
        Statement stmt = null;
        try
        {
            stmt = c.createStatement();

            String sql = "INSERT INTO TASKS(CONTENT, TYPE, STATE, DATE) " +
                    "VALUES ('" + task.getTaskContent() 
                    + "', '" + task.getTaskType().toString() 
                    + "', '" + task.getTaskState().name() 
                    + "', date('" + task.getLocalDate().format(DateTimeFormatter.ISO_DATE) + "'))";
            stmt.executeUpdate(sql);
            stmt.close();
        }
        catch (SQLException e)
        {
            throw new TaskManagerBackendNonRetryableException(e);
        }
    }
    
    @Override
    public List<Task> getTasksForDate(LocalDate date)
    {
        if (this.setup())
        {
            List<Task> dbTasks;
            
            try
            {
                Connection c = getConnection();
                dbTasks = this.getTasksForDate(c, date);
                c.close();
            }
            catch (SQLException e)
            {
                throw new TaskManagerBackendNonRetryableException(e);
            }

            return dbTasks;
        }

        throw new TaskManagerBackendNonRetryableException("Dude, you forgot to setup the db !!");
    }

    private List<Task> getTasksForDate(Connection c, LocalDate localDate)
    {
        List<Task> taskList = new ArrayList<>();
        
        Statement stmt;

        try
        {
            stmt = c.createStatement();
            String sql = "SELECT * FROM TASKS " +
                    "WHERE DATE = '" + localDate.format(DateTimeFormatter.ISO_DATE) + "'";

            ResultSet resultSet = stmt.executeQuery(sql);

            System.out.println(resultSet);

            while (resultSet.next())
            {
                int id = resultSet.getInt(ID_COLUMN_LABEL);
                String content = resultSet.getString(CONTENT_COLUMN_LABEL);
                String type = resultSet.getString(TYPE_COLUMN_LABEL);
                String state = resultSet.getString(STATE_COLUMN_LABEL);
                
                Task task = new Task(id, content, TaskType.valueOf(type), TaskState.valueOf(state), localDate);
                
                taskList.add(task);
            }
            
            stmt.close();
        }
        catch (SQLException e)
        {
            throw new TaskManagerBackendNonRetryableException(e);
        }

        return taskList;
    }
}
