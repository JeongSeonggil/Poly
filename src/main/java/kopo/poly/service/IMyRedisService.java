package kopo.poly.service;

import kopo.poly.dto.RedisDTO;

import java.util.List;

public interface IMyRedisService {
    int saveRedisString() throws Exception;

    RedisDTO getRedisString() throws Exception;

    int saveRedisStringJSON() throws Exception;

    RedisDTO getRedisStringJSON() throws Exception;

    int saveRedisList() throws Exception;

    List<String> getRedisList() throws Exception;
}
