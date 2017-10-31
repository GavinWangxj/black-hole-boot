package cn.cincout.tech.springcloudtask.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.task.listener.TaskExecutionListener;
import org.springframework.cloud.task.repository.TaskExecution;
import org.springframework.stereotype.Component;

/**
 * Created by zhaoyu on 17-9-14.
 *
 * @author zhaoyu
 * @sine 1.8
 */
@Component
public class TaskEventListener implements TaskExecutionListener {

    private final static Logger LOG = LoggerFactory.getLogger(TaskEventListener.class);

    @Override
    public void onTaskStartup(TaskExecution taskExecution) {
        LOG.info("onTaskStartup : " + taskExecution.toString());
    }

    @Override
    public void onTaskEnd(TaskExecution taskExecution) {
        LOG.info("onTaskEnd : " + taskExecution.toString());
    }

    @Override
    public void onTaskFailed(TaskExecution taskExecution, Throwable throwable) {
        LOG.info(taskExecution.toString());
    }
}
