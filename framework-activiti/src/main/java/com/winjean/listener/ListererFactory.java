package com.winjean.listener;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class ListererFactory {

    private List<Listener> executionListeners = new ArrayList<>();

    private List<Listener> taskListeners = new ArrayList<>();

    private List<Listener> globalListeners = new ArrayList<>();

    public static final String LISTENER_TYPE_TASK = "task";

    public static final String LISTENER_TYPE_EXECUTION = "execution";

    public static final String LISTENER_TYPE_GLOBAL = "global";

    @PostConstruct
    private void init() {
        initExecutionListener();

        initTaskListerner();

        initGlobalListerner();
    }

    /**
     * 全局事件监听维护
     */
    private void initGlobalListerner() {
        Listener globalTaskListener = new Listener("${globalTaskListener}", "通用全局监听器", null);
        globalListeners.add(globalTaskListener);
    }

    /**
     * 任务监听维护
     */
    private void initTaskListerner() {
        Listener bmpTaskListener = new Listener("${bmpTaskListener}", "通用任务监听器", null);
        taskListeners.add(bmpTaskListener);
    }

    /**
     * 事件监听维护
     */
    private void initExecutionListener() {
        Listener bpmExecutionListener = new Listener("${bpmExecutionListener}", "通用事件监听器", null);

        executionListeners.add(bpmExecutionListener);
    }

    public List<Listener> getListenersByType(String type) {
        if (LISTENER_TYPE_TASK.equalsIgnoreCase(type)) {
            return taskListeners;
        }
        if (LISTENER_TYPE_EXECUTION.equalsIgnoreCase(type)) {
            return executionListeners;
        }
        if (LISTENER_TYPE_GLOBAL.equalsIgnoreCase(type)) {
            return globalListeners;
        }
        return null;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    class Listener {
        private String key;
        private String name;
        private List<ListenerField> fields;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    class ListenerField {
        private String name;

        /**
         * 1:字符串 2:表达式
         */
        private String type;
    }
}
