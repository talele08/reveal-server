package com.revealprecision.revealserver.api.v1.facade.factory;

import static com.revealprecision.revealserver.api.v1.facade.constants.JDBCHelperConstants.LOCATION_ENTITY_NAME;
import static com.revealprecision.revealserver.api.v1.facade.constants.JDBCHelperConstants.PERSON_ENTITY_NAME;

import com.revealprecision.revealserver.api.v1.facade.models.Period;
import com.revealprecision.revealserver.api.v1.facade.models.TaskFacade;
import com.revealprecision.revealserver.api.v1.facade.models.TaskFacade.TaskPriority;
import com.revealprecision.revealserver.api.v1.facade.models.TaskFacade.TaskStatus;
import com.revealprecision.revealserver.api.v1.facade.util.DateTimeFormatter;
import com.revealprecision.revealserver.persistence.domain.Task;

public class TaskFacadeFactory {

  public static TaskFacade getEntity(Task task, String businessStatus, String userName,
      String group) {

    String entity;
    if (task.getAction().getLookupEntityType().getCode().equals(PERSON_ENTITY_NAME)) {
      entity = task.getPerson().getIdentifier().toString();
    } else if (task.getAction().getLookupEntityType().getCode().equals(LOCATION_ENTITY_NAME)) {
      entity = task.getLocation().getIdentifier().toString();
    } else {
      entity = task.getLocation().getIdentifier().toString();
    }

    return TaskFacade.builder()
        .code(task.getAction().getTitle())
        .authoredOn(
            DateTimeFormatter.getDateTimeFacadeStringFromLocalDateTime(task.getAuthoredOn()))
        .description(task.getAction().getDescription())
        .executionPeriod(Period
            .between(DateTimeFormatter.getDateTimeFacadeStringFromLocalDateTime(
                    task.getExecutionPeriodStart().atStartOfDay())
                , DateTimeFormatter.getDateTimeFacadeStringFromLocalDateTime(
                    task.getExecutionPeriodEnd().atStartOfDay())))
        .focus(task.getAction().getIdentifier().toString())
        .forEntity(entity)
        .identifier(task.getIdentifier().toString())
        .planIdentifier(task.getAction().getGoal().getPlan().getIdentifier().toString())
        .priority(TaskPriority.get(task.getPriority().name().toLowerCase()))
        .lastModified(
            DateTimeFormatter.getDateTimeFacadeStringFromLocalDateTime(task.getLastModified()))
        .status(TaskStatus.get(task.getLookupTaskStatus().getCode().toLowerCase()))
        .businessStatus(businessStatus)
        .owner(userName)
        .requester(userName)
        .groupIdentifier(group)
        .build();
  }


}
