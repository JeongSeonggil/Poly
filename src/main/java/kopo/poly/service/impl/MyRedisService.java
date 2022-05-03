package kopo.poly.service.impl;

import kopo.poly.dto.RedisDTO;
import kopo.poly.persistance.redis.IMyRedisMapper;
import kopo.poly.service.IMyRedisService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

@Slf4j
@Service("MyRedisService")
public class MyRedisService implements IMyRedisService {
    @Resource(name = "MyRedisMapper")
    private IMyRedisMapper myRedisMapper;

    @Override
    public int saveRedisString() throws Exception {
        log.info(this.getClass().getName() + "saveRedisString Start!");

        String redisKey = "myRedis_String";

        RedisDTO pDTO = new RedisDTO();

        pDTO.setTest_text("String타입으로 저장할 일반 문자열이다");

        int res = myRedisMapper.saveRedisString(redisKey, pDTO);

        log.info(this.getClass().getName() + "saveRedisString End!");
        return res;
    }

    @Override
    public RedisDTO getRedisString() throws Exception {

        String redisKey = "myRedis_String";

        RedisDTO rDTO = myRedisMapper.getRedisString(redisKey);

        if (rDTO == null) {
            rDTO = new RedisDTO();
        }

        return rDTO;
    }

    @Override
    public int saveRedisStringJSON() throws Exception {
        log.info(this.getClass().getName() + "getRedisStringJSON Start!");

        String redisKey = "myRedis_String_JSON";
        RedisDTO pDTO = new RedisDTO();
        pDTO.setTest_text("난 String타입에 JSON 구조로 저장할 일반 문자열이다.");
        pDTO.setName("정성길");
        pDTO.setAddr("서울");
        pDTO.setEmail("seonggil@email.com");

        int res = myRedisMapper.saveRedisStringJSON(redisKey, pDTO);

        log.info(this.getClass().getName() + "saveRedisStringJSON End!");

        return res;
    }

    @Override
    public RedisDTO getRedisStringJSON() throws Exception {
        String redisKey = "Key_String_JSON";

        RedisDTO redisDTO = myRedisMapper.getRedisStringJSON(redisKey);

        if (redisDTO == null) {
            redisDTO = new RedisDTO();
        }


        return redisDTO;
    }

    @Override
    public int saveRedisList() throws Exception {
        log.info(this.getClass().getName() + ".saveRedisList Start!");

        String redisKey = "myRedis_List";
        List<RedisDTO> redisDTOList = new LinkedList<>();

        for (int i = 0; i < 10; i++) {
            RedisDTO redisDTO = new RedisDTO();
            redisDTO.setTest_text(i + "번째 데이터 입니다.");
            redisDTO.setName("정성길");
            redisDTO.setAddr("20");
            redisDTOList.add(redisDTO);

            redisDTO = null;
        }

        int res = myRedisMapper.saveRedisList(redisKey, redisDTOList);

        log.info(this.getClass().getName() + ".saveRedisList End!");


        return res;
    }

    @Override
    public List<RedisDTO> getRedisList(String redisKey) throws Exception {

        log.info(this.getClass().getName() + ".getRedisList Start!");
        List<RedisDTO> rList = myRedisMapper.getRedisList(redisKey);

        if (rList == null) {
            throw new NotFoundException("List Not Found");
        }

        log.info(this.getClass().getName() + ".getRedisList End!");


        return rList;
    }
}
