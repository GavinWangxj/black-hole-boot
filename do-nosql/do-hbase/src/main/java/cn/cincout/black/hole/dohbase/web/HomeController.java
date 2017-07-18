package cn.cincout.black.hole.dohbase.web;

import cn.cincout.black.hole.dohbase.application.InsertService;
import cn.cincout.black.hole.dohbase.application.InsertServiceImpl;
import cn.cincout.black.hole.dohbase.application.InsertTaskThread;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhaoyu on 16-9-14.
 *
 * @author zhaoyu
 * @date 17-7-10
 * @sine 1.8
 */
@RestController
public class HomeController {

    @GetMapping(value = "/insert/{threadNum}")
    public String insert(@PathVariable("threadNum") int threadNum) {
        InsertService insertService = new InsertServiceImpl();

        long insertNum = 10000;
        long begin = System.currentTimeMillis();
        for (int i = 0; i < threadNum; i++) {
            InsertTaskThread insertTaskThread = new InsertTaskThread(insertService, i * 10000, insertNum, begin);
            Thread thread = new Thread(insertTaskThread);
            thread.start();
        }
        return "success";
    }

}
