package cn.cincout.tech.quartzschedule.joblistener;

import org.quartz.*;
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
public class SimpleScheduleListener implements SchedulerListener {
    private final static Logger LOG = LoggerFactory.getLogger(SimpleScheduleListener.class);

    @Override
    public void jobScheduled(Trigger trigger) {
        LOG.info("jobScheduled {}", trigger.getKey());
    }

    @Override
    public void jobUnscheduled(TriggerKey triggerKey) {
        LOG.info("jobUnscheduled {}", triggerKey.toString());
    }

    @Override
    public void triggerFinalized(Trigger trigger) {
        LOG.info("triggerFinalized {}", trigger.getKey());
    }

    @Override
    public void triggerPaused(TriggerKey triggerKey) {
        LOG.info("triggerPaused");
    }

    @Override
    public void triggersPaused(String triggerGroup) {
        LOG.info("triggersPaused");
    }

    @Override
    public void triggerResumed(TriggerKey triggerKey) {
        LOG.info("triggerResumed");
    }

    @Override
    public void triggersResumed(String triggerGroup) {
        LOG.info("triggersResumed");
    }

    @Override
    public void jobAdded(JobDetail jobDetail) {
        LOG.info("jobAdded {}", jobDetail.getKey());
    }

    @Override
    public void jobDeleted(JobKey jobKey) {

    }

    @Override
    public void jobPaused(JobKey jobKey) {

    }

    @Override
    public void jobsPaused(String jobGroup) {

    }

    @Override
    public void jobResumed(JobKey jobKey) {

    }

    @Override
    public void jobsResumed(String jobGroup) {

    }

    @Override
    public void schedulerError(String msg, SchedulerException cause) {

    }

    @Override
    public void schedulerInStandbyMode() {

    }

    @Override
    public void schedulerStarted() {
        LOG.info("schedulerStarted");
    }

    @Override
    public void schedulerStarting() {
        LOG.info("schedulerStarting");
    }

    @Override
    public void schedulerShutdown() {
        LOG.info("schedulerShutdown");
    }

    @Override
    public void schedulerShuttingdown() {
        LOG.info("schedulerShuttingdown");
    }

    @Override
    public void schedulingDataCleared() {
        LOG.info("schedulingDataCleared");
    }
}
