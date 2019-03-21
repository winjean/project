package com.winjean.listener.execution;

import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CustomExecutionListener implements ExecutionListener {

    @Override
    public void notify(DelegateExecution execution) throws Exception {
        String name = execution.getEventName();
        log.info("execution listener,name={} ,id={}", execution.getEventName(),execution.getId());
        if(ExecutionListener.EVENTNAME_END.equals(name)) {

        }
    }
}
