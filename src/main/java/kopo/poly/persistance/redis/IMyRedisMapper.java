package kopo.poly.persistance.redis;

import kopo.poly.dto.RedisDTO;

import java.util.List;

public interface IMyRedisMapper {
    int saveRedisString(String redisKey, RedisDTO pDTO) throws Exception;

    RedisDTO getRedisString(String redisKey) throws Exception;

    int saveRedisStringJSON(String redisKey, RedisDTO pDTO) throws Exception;

    RedisDTO getRedisStringJSON(String redisKey) throws Exception;

    int saveRedisList(String redisKey, List<RedisDTO> pList) throws Exception;

    List<String> getRedisList(String redisKey) throws Exception;
}
