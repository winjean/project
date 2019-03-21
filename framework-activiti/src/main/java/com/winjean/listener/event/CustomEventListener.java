package com.winjean.listener.event;

import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.delegate.event.ActivitiEntityEvent;
import org.activiti.engine.delegate.event.ActivitiEvent;
import org.activiti.engine.delegate.event.ActivitiEventListener;
import org.activiti.engine.delegate.event.ActivitiEventType;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class CustomEventListener implements ActivitiEventListener {

    /**
     * Called when an event has been fired
     *
     * @param event the event
     */
    @Override
    public void onEvent(ActivitiEvent event) {
        log.debug("Event received: " + event.getType());

        if (event.getType() == ActivitiEventType.TASK_CREATED) {
            ActivitiEntityEvent entityEvent = (ActivitiEntityEvent) event;
            TaskEntity taskEntity = (TaskEntity) entityEvent.getEntity();
            log.debug(" taskEntity: {} " + taskEntity);
            log.info(" =============== TASK_CREATED ================== ");
        }

        if (event.getType() == ActivitiEventType.TASK_ASSIGNED) {
            ActivitiEntityEvent entityEvent = (ActivitiEntityEvent) event;
            TaskEntity taskEntity = (TaskEntity) entityEvent.getEntity();
            log.debug(" taskEntity: {} " + taskEntity);
            log.info(" =============== TASK_ASSIGNED ================== ");
        }

        if (event.getType() == ActivitiEventType.TASK_COMPLETED) {

            ActivitiEntityEvent entityEvent = (ActivitiEntityEvent) event;
            TaskEntity taskEntity = (TaskEntity) entityEvent.getEntity();
            log.debug(" taskEntity: {} " + taskEntity);
            log.info(" =============== TASK_COMPLETED ================== ");
        }

        if (event.getType() == ActivitiEventType.PROCESS_COMPLETED){
            log.info(" =============== PROCESS_COMPLETED ================== ");
        }
    }

    /**
     * @return whether or not the current operation should fail when this listeners execution
     * throws an exception.
     */
    @Override
    public boolean isFailOnException() {
        return false;
    }
}
