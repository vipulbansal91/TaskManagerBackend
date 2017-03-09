package taskManager.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDate;

/**
 * Created by vbbansal on 1/3/17.
 */
@Data
@NoArgsConstructor
@XmlRootElement
public class Task
{
    int taskId;
    String taskContent;
    TaskType taskType;
    LocalDate localDate;
}
