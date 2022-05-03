package kopo.poly.persistance.redis.impl;

import kopo.poly.dto.RedisDTO;
import kopo.poly.persistance.redis.IMyRedisMapper;
import kopo.poly.util.CmmUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component("MyRedisMapper")
public class MyRedisMapper implements IMyRedisMapper{

    public final RedisTemplate<String, Object> redisDB;

    public MyRedisMapper(RedisTemplate<String, Object> redisDB) {
        this.redisDB = redisDB;
    }

    @Override
    public int saveRedisString(String redisKey, RedisDTO pDTO) throws Exception {
        log.info(this.getClass().getName() + ".SaveRedisString Start!");

        int res = 0;

        String saveData = CmmUtil.nvl(pDTO.getTest_text()); //저장할 값

        /**
         * redis 저장 및 읽기에 대한 데이터 타입 지정(String 타입으로 지정)
         */
        redisDB.setKeySerializer(new StringRedisSerializer()); //String 타입
        redisDB.setValueSerializer(new StringRedisSerializer()); //String 타입

        if (!redisDB.hasKey(redisKey)) { // 데이터가 존재하지 않으면 저장하기

            //데이터 저장하기
            redisDB.opsForValue().set(redisKey, saveData);

            //ReisDB에 저장되는 데이터의 유효시간 설정(TTL설정)
            //2일이 지나면, 자동으로 데이터가 삭제되도록 설정함
            redisDB.expire(redisKey, 2, TimeUnit.DAYS);

            log.info("Save Data!!");

            res = 1;

        }
        log.info(this.getClass().getName() + ".saveRedisString End!");

        return res;
    }

    @Override
    public RedisDTO getRedisString(String redisKey) throws Exception {
        RedisDTO rDTO = new RedisDTO();

        redisDB.setKeySerializer(new StringRedisSerializer()); //String 타입
        redisDB.setValueSerializer(new StringRedisSerializer()); //String 타입

        if (redisDB.hasKey(redisKey)) {
            String res = (String) redisDB.opsForValue().get(redisKey);

            log.info("res : " + res);
            rDTO.setTest_text(res);
        }

        return rDTO;
    }

    @Override
    public int saveRedisStringJSON(String redisKey, RedisDTO pDTO) throws Exception {
        log.info(this.getClass().getName() + ".saveRedisStringJSON Start!");

        int res = 0;

        redisDB.setKeySerializer(new StringRedisSerializer());
        redisDB.setValueSerializer(new Jackson2JsonRedisSerializer<>(RedisDTO.class));

        if (!redisDB.hasKey(redisKey)) {
            redisDB.opsForValue().set(redisKey, pDTO);

            redisDB.expire(redisKey, 2, TimeUnit.DAYS);

            log.info("Save Data");
            res = 1;
        }
        log.info(this.getClass().getName() + ".saveRedisStringJSON End!");
        return res;

    }

    @Override
    public RedisDTO getRedisStringJSON(String redisKey) throws Exception {
        RedisDTO redisDTO = null;

        redisDB.setKeySerializer(new StringRedisSerializer());

        redisDB.setValueSerializer(new Jackson2JsonRedisSerializer<>(RedisDTO.class));

        if (redisDB.hasKey(redisKey)) {
            redisDTO = (RedisDTO) redisDB.opsForValue().get(redisKey);
        }


        return redisDTO;
    }

    @Override
    public int saveRedisList(String redisKey, List<RedisDTO> pList) throws Exception {
        log.info(this.getClass().getName() + ".saveRedisList Start!");
        int res = 0;

        redisDB.setValueSerializer(new StringRedisSerializer());
        redisDB.setValueSerializer(new Jackson2JsonRedisSerializer<>(RedisDTO.class));

        pList.forEach(e -> {
            // 오름차순 정렬
            redisDB.opsForList().rightPush(redisKey, CmmUtil.nvl(e.getTest_text()));

            // 내림차순 정렬
//            redisDB.opsForList().leftPush(redisKey, CmmUtil.nvl(e.getTest_text()));
        });

        redisDB.expire(redisKey, 5, TimeUnit.HOURS);


        res = 1;

        log.info(this.getClass().getName() + ".saveRedisList End!");

        return res;
    }

    @Override
    public List<String> getRedisList(String redisKey) throws Exception {
        log.info(this.getClass().getName() + ".getRedisList Start!");

        List<String> rList = null;

        redisDB.setKeySerializer(new StringRedisSerializer());
        redisDB.setValueSerializer(new StringRedisSerializer());

        if (redisDB.hasKey(redisKey)) {
            rList = (List) redisDB.opsForList().range(redisKey, 0, -1);

        }

        log.info(this.getClass().getName() + ".getRedisList End!");
        return rList;
    }
}
