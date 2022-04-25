package kopo.poly.study.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@Service("AsyncService")
public class AsyncService {
    public static String Async;

    @Async
    public String AsyncMethod() throws InterruptedException {
        log.info("Async Service Start!");

        Thread.sleep(10000);

        log.info("Async Service End!");
        Async = "Async";

        return "Async";
    }

    public String NotAsyncMethod() throws InterruptedException {
        log.info("NotAsync Service Start!");

        log.info("NotAsync Service End!");

        return "NotAsync";
    }

}
