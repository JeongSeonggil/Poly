package kopo.poly.controller;

import kopo.poly.dto.RedisDTO;
import kopo.poly.service.IMyRedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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


    @GetMapping("/redis/saveRedisList")
    public ResponseEntity<String> saveRedisList() throws Exception {
        log.info(this.getClass().getName() + ".saveRedisList Start!");
        String msg = "";
        int res = myRedisService.saveRedisList();

        log.info(this.getClass().getName() + ".saveRedisList End!");
        if (res == 1) {
            // 성공
            return ResponseEntity.status(HttpStatus.CREATED).body("Success");
        }

        return ResponseEntity.status(500).body("fail");
    }

}
