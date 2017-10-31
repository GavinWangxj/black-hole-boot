package cn.cincout.tech.quartzschedule.joblistener;

import org.quartz.jobs.FileScanListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.File;
import java.sql.Timestamp;

/**
 * Created by zhaoyu on 17-9-19.
 *
 * @author zhaoyu
 * @sine 1.8
 */
@Component
public class SimpleFileScanListener implements FileScanListener {
    private final static Logger LOG = LoggerFactory.getLogger(SimpleFileScanListener.class);

    @Override
    public void fileUpdated(String s) {
        File file = new File(s);
        Timestamp timestamp = new Timestamp(file.lastModified());
        LOG.info("{} was changed at {}", s, timestamp);
    }
}
