package com.winjean.listener.event;

import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.impl.event.AbstractEventHandler;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.impl.persistence.entity.EventSubscriptionEntity;

/**
 * @author ：winjean
 * @date ：Created in 2019/3/21 13:20
 * @description：${description}
 * @modified By：
 * @version: $version$
 */
@Slf4j
public class CustomEventHandler extends AbstractEventHandler {

    public final static String EVENT_HANDLER_TYPE = "custom-event";
//    public final static String EVENT_HANDLER_TYPE = ActivitiEventType.TASK_COMPLETED.toString();

    @Override
    public String getEventHandlerType() {
        return EVENT_HANDLER_TYPE;
    }

    @Override
    public void handleEvent(EventSubscriptionEntity eventSubscription, Object payload, CommandContext commandContext) {
        super.handleEvent(eventSubscription, payload, commandContext);

        log.info("CustomEventHandler");
    }
}
