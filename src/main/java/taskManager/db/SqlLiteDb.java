package taskManager.db;

import taskManager.exception.TaskManagerBackendNonRetryableException;
import taskManager.model.DbTask;
import taskManager.model.SqlLiteDbTask;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by vbbansal on 3/19/17.
 */
public class SqlLiteDb implements Db
{
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
    
    public void insertIntoTasks(DbTask task)
    {
        if (!(task instanceof SqlLiteDbTask))
        {
            throw new TaskManagerBackendNonRetryableException("Bug in service !!");
        }
            
        try
        {
            Connection c = getConnection();
            this.insertIntoTable(c, (SqlLiteDbTask) task);
            c.close();
        }
        catch (SQLException e)
        {
            throw new TaskManagerBackendNonRetryableException(e);
        }
    }

    private void insertIntoTable(Connection c, SqlLiteDbTask task)
    {
        Statement stmt = null;
        try
        {
            stmt = c.createStatement();

            String sql = "INSERT INTO TASKS(CONTENT, TYPE, STATE, DATE) " +
                    "VALUES ('" + task.getContent() + "', '" + task.getType() + "', '" + task.getState() + "', '" +
                    task.getDate() + "')";
            stmt.executeUpdate(sql);
            stmt.close();
        }
        catch (SQLException e)
        {
            throw new TaskManagerBackendNonRetryableException(e);
        }
    }
}
