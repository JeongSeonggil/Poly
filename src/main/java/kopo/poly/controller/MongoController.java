package kopo.poly.controller;

import kopo.poly.service.IMongoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
public class MongoController {
    @Resource(name = "MongoService")
    private IMongoService mongoService;

    @GetMapping("/mongo/test")
    public String test() throws Exception {
        log.info(this.getClass().getName() + ".test Start!");

        mongoService.mongoTest();

        log.info(this.getClass().getName() + ".test End!");

        return "MongoDB Test!";
    }
}
