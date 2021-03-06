package kopo.poly.service.impl;

import kopo.poly.dto.RedisDTO;
import kopo.poly.persistance.redis.IMyRedisMapper;
import kopo.poly.service.IMyRedisService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

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

    @Override
    public int saveRedisHash() throws Exception {
        log.info(this.getClass().getName() + ".saveRedisHash Start!");
        String redisKey = "myRedis_Hash";

        RedisDTO pDto = new RedisDTO();
        pDto.setName("정성길");
        pDto.setEmail("dataofsg02@naver.com");
        pDto.setAddr("서울");

        int res = myRedisMapper.saveRedisHash(redisKey, pDto);

        log.info(this.getClass().getName() + ".saveRedisHash End!");

        return res;

    }

    @Override
    public RedisDTO getRedisHash() throws Exception {
        log.info(this.getClass().getName() + "getRedisHash Start!");
        String RedisKey = "myRedis_Hash";

        RedisDTO rDto = myRedisMapper.getRedisHash(RedisKey);

        if (rDto == null) {
            throw new NotFoundException("Not Found Value");
        }


        return rDto;
    }

    @Override
    public int saveRedisSet() throws Exception {
        log.info(this.getClass().getName() + ".saveRedisSet Start!");
        Set<RedisDTO> pSet = new HashSet<>();

        String redisKey = "myRedis_Set";
        for (int i = 0; i < 10; i++) {
            RedisDTO pDto = new RedisDTO();
            pDto.setName("정성길");
            pDto.setEmail("email");
            pDto.setTest_text(i + ": Data");
            pDto.setAddr("서울");

            pSet.add(pDto);
        }
        log.info("for End");
        int res = myRedisMapper.saveRedisSet(redisKey, pSet);

        if (res == 0) {
            throw new RuntimeException("Mapper Error");
        }
        return res;
    }

    @Override
    public Set<RedisDTO> getRedisSet() throws Exception {
        log.info(this.getClass().getName() + ".getRedisSet Start!");
        String redisKey = "myRedis_Set";

        Set<RedisDTO> redisDTOSet = myRedisMapper.getRedisSet(redisKey);

        if (redisDTOSet.isEmpty()) {
            throw new NotFoundException("Mapper Error");
        }

        log.info(this.getClass().getName() + ".getRedisSet End!");
        return redisDTOSet;
    }

    @Override
    public int saveRedisZSetJson() throws Exception {
        log.info(this.getClass().getName() + ".saveRedisZSetJson");

        String redisKey = "myRedis_ZSet";

        int res = 0;

        List<RedisDTO> redisDTOList = new LinkedList<>();

        for (int i = 0; i < 10; i++) {
            RedisDTO redisDTO = new RedisDTO();
            redisDTO.setName("정성길");
            redisDTO.setEmail("seonggil110*@naver.cc");
            redisDTO.setTest_text("value : " + i);
            redisDTOList.add(redisDTO);
        }

        res = myRedisMapper.saveRedisZSetJson(redisKey, redisDTOList);

        log.info(this.getClass().getName() + ".saveRedisZSetJson End!");
        return res;
    }

    @Override
    public Set<RedisDTO> getRedisZSetJson() throws Exception {
        log.info(this.getClass().getName());
        String redisKey = "myRedis_ZSet";

        Set<RedisDTO> redisDTOSet = myRedisMapper.getRedisZSetJson(redisKey);

        if (redisDTOSet.isEmpty()) {
            throw new NotFoundException("ZSet Not Found");
        }


        log.info(this.getClass().getName());
        return redisDTOSet;
    }

    @Override
    public int deleteDataJSON(String redisKey) throws Exception {
        log.info(this.getClass().getName());
        log.info("RedisKey : " + redisKey);

        if (myRedisMapper.deleteDataJSON(redisKey)) {
            // Delete OK
            return 1;
        } else {
            log.info(this.getClass().getName());
            throw new NotFoundException("Cannot delete with " + redisKey);
        }
    }
}
