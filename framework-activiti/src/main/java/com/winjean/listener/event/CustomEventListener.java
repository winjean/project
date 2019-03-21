package com.winjean.listener.event;

import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.delegate.event.ActivitiEntityEvent;
import org.activiti.engine.delegate.event.ActivitiEvent;
import org.activiti.engine.delegate.event.ActivitiEventListener;
import org.activiti.engine.delegate.event.ActivitiEventType;
import org.activiti.engine.impl.persistence.entity.TaskEntity;


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

            log.info(" =============== TASK_CREATED "+taskEntity.getName()+"================== ");
        }

        if (event.getType() == ActivitiEventType.TASK_ASSIGNED) {
            ActivitiEntityEvent entityEvent = (ActivitiEntityEvent) event;
            TaskEntity taskEntity = (TaskEntity) entityEvent.getEntity();
            log.info(" =============== TASK_ASSIGNED " + taskEntity.getAssignee()+"  ================== ");
        }

        if (event.getType() == ActivitiEventType.TASK_COMPLETED) {
            ActivitiEntityEvent entityEvent = (ActivitiEntityEvent) event;
            TaskEntity taskEntity = (TaskEntity) entityEvent.getEntity();
            log.info(" =============== TASK_COMPLETED " + taskEntity.getName()+"================== ");
        }

        if (event.getType() == ActivitiEventType.PROCESS_STARTED){
            log.info(" =============== PROCESS_STARTED ================== ");
        }

        if (event.getType() == ActivitiEventType.PROCESS_COMPLETED){
            log.info(" =============== PROCESS_COMPLETED ================== ");
        }

        if (event.getType() == ActivitiEventType.PROCESS_COMPLETED_WITH_ERROR_END_EVENT){
            log.info(" =============== PROCESS_COMPLETED_WITH_ERROR_END_EVENT ================== ");
        }

        if (event.getType() == ActivitiEventType.PROCESS_CANCELLED){
            log.info(" =============== PROCESS_CANCELLED ================== ");
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
