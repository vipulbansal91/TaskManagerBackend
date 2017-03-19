package taskManager.db;

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
        Connection c = getConnection();
        createTable(c);

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
            throw new RuntimeException(e);
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
            c.close();
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }

    }
}
