package kopo.poly.controller;

import kopo.poly.dto.RedisDTO;
import kopo.poly.service.IMyRedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class RedisController {

    @Resource(name = "MyRedisService")
    private IMyRedisService myRedisService;


    @GetMapping("/redis/saveRedisString")
    public String saveRedisString() throws Exception {
        log.info(this.getClass().getName() + ".saveRedisString Start!");

        String msg;

        int res = myRedisService.saveRedisString();

        if (res == 1) {
            msg = "Success";
        } else {
            msg = "fail";
        }

        log.info(this.getClass().getName() + ".saveRedisString End!");

        return msg;
    }

    @GetMapping(value = "/redis/getRedisString")
    public RedisDTO getRedisString() throws Exception {
        RedisDTO rDTO = myRedisService.getRedisString();

        return rDTO;
    }

    @GetMapping(value = "/redis/saveRedisStringJSON")
    public String saveRedisStringJSON() throws Exception {
        log.info(this.getClass().getName() + ".saveRedisStringJSON Start!");

        String msg;

        int res = myRedisService.saveRedisStringJSON();

        if (res == 1) {
            msg = "success";
        } else {
            msg = "fail";
        }

        log.info(this.getClass().getName() + ".saveRedisStringJSON End!");

        return msg;
    }

    @GetMapping(value = "/redis/getRedisStringJSON")
    public RedisDTO getRedisStringJSON() throws Exception {
        RedisDTO redisDTO = myRedisService.getRedisStringJSON();

        return redisDTO;
    }
}
