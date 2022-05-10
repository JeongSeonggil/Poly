package kopo.poly.service;

import kopo.poly.dto.RedisDTO;

import java.util.List;

public interface IMyRedisService {
    int saveRedisString() throws Exception;

    RedisDTO getRedisString() throws Exception;

    int saveRedisStringJSON() throws Exception;

    RedisDTO getRedisStringJSON() throws Exception;

    int saveRedisList() throws Exception;

    List<RedisDTO> getRedisList(String redisKey) throws Exception;

    int saveRedisHash() throws Exception;

    RedisDTO getRedisHash() throws Exception;

    int saveRedisSet() throws Exception;
}
