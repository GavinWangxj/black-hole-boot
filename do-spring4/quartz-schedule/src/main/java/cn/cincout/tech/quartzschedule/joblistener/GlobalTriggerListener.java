package cn.cincout.tech.quartzschedule.joblistener;

import org.quartz.JobExecutionContext;
import org.quartz.Trigger;
import org.quartz.TriggerListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by zhaoyu on 17-9-19.
 *
 * @author zhaoyu
 * @sine 1.8
 */
@Component
public class GlobalTriggerListener implements TriggerListener {
    private final static Logger LOG = LoggerFactory.getLogger(GlobalTriggerListener.class);

    @Override
    public String getName() {
        return this.getClass().getName();
    }

    @Override
    public void triggerFired(Trigger trigger, JobExecutionContext context) {
        LOG.info("triggerFired " + trigger.getKey().toString());
    }

    @Override
    public boolean vetoJobExecution(Trigger trigger, JobExecutionContext context) {
        return true;
    }

    @Override
    public void triggerMisfired(Trigger trigger) {
        LOG.info(trigger.getKey() + " misfired.");
    }

    @Override
    public void triggerComplete(Trigger trigger, JobExecutionContext context, Trigger.CompletedExecutionInstruction triggerInstructionCode) {
        LOG.info(trigger.getKey() + " completed");
    }
}
