package com.winjean.listener.task;

import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.Expression;
import org.activiti.engine.delegate.TaskListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CustomTaskListener implements TaskListener {

    private Expression level;

    @Override
    public void notify(DelegateTask delegateTask) {
        String name = delegateTask.getEventName();
        log.info("task listener ,id ={} ,name ={}", delegateTask.getId(), name);

        level.getValue(delegateTask);

        if(TaskListener.EVENTNAME_COMPLETE.equals(name)) {

        }

    }
}
