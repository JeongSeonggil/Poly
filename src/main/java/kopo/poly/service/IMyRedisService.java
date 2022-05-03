package kopo.poly.service;

import kopo.poly.dto.RedisDTO;

public interface IMyRedisService {
    int saveRedisString() throws Exception;

    RedisDTO getRedisString() throws Exception;

    int saveRedisStringJSON() throws Exception;

    RedisDTO getRedisStringJSON() throws Exception;
}
