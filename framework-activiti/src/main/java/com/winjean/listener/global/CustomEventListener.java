package com.winjean.listener.global;

import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.delegate.event.ActivitiEntityEvent;
import org.activiti.engine.delegate.event.ActivitiEvent;
import org.activiti.engine.delegate.event.ActivitiEventListener;
import org.activiti.engine.delegate.event.ActivitiEventType;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

        if (event.getType() == ActivitiEventType.TASK_CREATED
                || event.getType() == ActivitiEventType.TASK_ASSIGNED
                || event.getType() == ActivitiEventType.TASK_COMPLETED) {

            ActivitiEntityEvent entityEvent = (ActivitiEntityEvent) event;
            TaskEntity taskEntity = (TaskEntity) entityEvent.getEntity();
            log.debug(" taskEntity: {} " + taskEntity);
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
