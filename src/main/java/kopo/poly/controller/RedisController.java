package kopo.poly.controller;

import kopo.poly.dto.RedisDTO;
import kopo.poly.service.IMyRedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

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

    @GetMapping("/redis/getRedisList/{redisKey}")
    public ResponseEntity<List<RedisDTO>> getRedisList(@PathVariable("redisKey") String redisKey) throws Exception {
        log.info(this.getClass().getName() + ".getRedisList Start!");

        List<RedisDTO> rList = myRedisService.getRedisList(redisKey);

        log.info(this.getClass().getName() + ".getRedisList End!");

        return ResponseEntity.status(HttpStatus.OK).body(rList);
    }

    @GetMapping("/redis/saveRedisHash")
    private ResponseEntity<String> saveRedisHash() throws Exception {
        log.info(this.getClass().getName() + ".saveRedisHash Start!");

        String msg;

        int res = myRedisService.saveRedisHash();

        log.info(this.getClass().getName() + ".saveRedisHash End!");
        if (res == 1) {
            msg = "success";
            return ResponseEntity.status(HttpStatus.CREATED).body(msg);
        }

        msg = "fail";

        return ResponseEntity.status(500).body(msg);


    }

    @GetMapping("/redis/getRedisHash")
    public ResponseEntity<RedisDTO> getRedisHash() throws Exception {
        log.info(this.getClass().getName() + ".getRedis Start!");


        RedisDTO rDto = myRedisService.getRedisHash();


        return ResponseEntity.ok().body(rDto);
    }

    @GetMapping("/redis/saveRedisSet")
    public ResponseEntity<String> saveRedisSet() throws Exception {
        log.info(this.getClass().getName() + ".saveRedisSet Start!");

        int res = myRedisService.saveRedisSet();

        log.info(this.getClass().getName() + ".saveRedisSet End!");
        if (res == 0) {
            return ResponseEntity.status(500).body("fail");
        } else {
            return ResponseEntity.status(HttpStatus.CREATED).body("success");
        }

    }

    @GetMapping("/redis/getRedisSet")
    public ResponseEntity<Set<RedisDTO>> getRedisSet() throws Exception {
        log.info(this.getClass().getName() + ".getRedisSet Start!");

        Set<RedisDTO> redisDTOSet = myRedisService.getRedisSet();

        log.info(this.getClass().getName() + ".getRedisSet End!");
        if (redisDTOSet.isEmpty()) {
            return ResponseEntity.status(500).body(null);
        } else {
            return ResponseEntity.ok().body(redisDTOSet);
        }


    }

    @GetMapping("/redis/saveRedisZSet")
    public ResponseEntity<String> saveRedisZSet() throws Exception {
        log.info(this.getClass().getName() + ".saveRedisZSet Start!");

        int res = myRedisService.saveRedisZSetJson();

        log.info(this.getClass().getName() + ".saveRedisZSet End!");

        if (res != 1) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("fail");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body("success");
    }

    @GetMapping("/redis/getRedisZSetJson")
    public ResponseEntity<Set<RedisDTO>> getRedisZSetJson() throws Exception {
        log.info(this.getClass().getName());

        Set<RedisDTO> redisDTOSet = myRedisService.getRedisZSetJson();

        log.info(this.getClass().getName());

        return ResponseEntity.ok().body(redisDTOSet);
    }

    @DeleteMapping("/redis/deleteDataJSON/{redisKey}")
    public ResponseEntity<String> deleteDataJSON(@PathVariable String redisKey) throws Exception {
        log.info(this.getClass().getName());

        int res = myRedisService.deleteDataJSON(redisKey);


        if (res != 1) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Key Error");
        }

        log.info(this.getClass().getName());

        return ResponseEntity.status(HttpStatus.OK).body("DELETE END");
    }

}
