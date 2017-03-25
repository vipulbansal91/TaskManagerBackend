package taskManager.db;

import taskManager.model.DbTask;

/**
 * Created by vbbansal on 3/19/17.
 */
public interface Db
{
    boolean setup();
    void insertIntoTasks(DbTask task);
}
