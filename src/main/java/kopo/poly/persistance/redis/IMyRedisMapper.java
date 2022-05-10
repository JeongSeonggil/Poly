package kopo.poly.persistance.redis;

import kopo.poly.dto.RedisDTO;

import java.util.List;
import java.util.Set;

public interface IMyRedisMapper {
    int saveRedisString(String redisKey, RedisDTO pDTO) throws Exception;

    RedisDTO getRedisString(String redisKey) throws Exception;

    int saveRedisStringJSON(String redisKey, RedisDTO pDTO) throws Exception;

    RedisDTO getRedisStringJSON(String redisKey) throws Exception;

    int saveRedisList(String redisKey, List<RedisDTO> pList) throws Exception;

    List<RedisDTO> getRedisList(String redisKey) throws Exception;

    int saveRedisHash(String redisKey, RedisDTO redisDTO) throws Exception;

    RedisDTO getRedisHash(String redisKey) throws Exception;

    int saveRedisSet(String redisKey, Set<RedisDTO> pSet) throws Exception;

}
