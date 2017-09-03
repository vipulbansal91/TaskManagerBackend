package taskManager.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import taskManager.exception.TaskManagerBackendNonRetryableException;
import taskManager.serDeser.LocalDateAdapter;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;

/**
 * Created by vbbansal on 1/3/17.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task
{
    int taskId;
    String taskContent;
    TaskType taskType;
    TaskState taskState;
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    LocalDate localDate;
}
