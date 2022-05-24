package kopo.poly.persistance.redis.impl;

import kopo.poly.dto.ChatDTO;
import kopo.poly.persistance.redis.IChatMapper;
import kopo.poly.util.CmmUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static kopo.poly.util.CmmUtil.*;

@Slf4j
@Component("ChatMapper")
public class ChatMapper implements IChatMapper {

    @Autowired
    public final RedisTemplate<String, Object> redisDB;

    public ChatMapper(RedisTemplate<String, Object> redisDB) {
        this.redisDB = redisDB;
    }


    @Override
    public Set<String> getRoomList() throws Exception {
        log.info(this.getClass().getName() + "getRoomList Start!");

        Set<String> rSet = (Set) redisDB.keys("Chat*");

        log.info(this.getClass().getName() + "getRoomList End!");

        return rSet;
    }

    @Override
    public int insertChat(ChatDTO chatDTO) throws Exception {
        log.info(this.getClass().getName() + ".insertChat Start!");

        int res = 0;
        String roomKey = nvl(chatDTO.getRoomKey());

        redisDB.setKeySerializer(new StringRedisSerializer());
        redisDB.setValueSerializer(new Jackson2JsonRedisSerializer<>(ChatDTO.class));

        redisDB.opsForList().leftPush(roomKey, chatDTO);

        res = 1;

        log.info(this.getClass().getName() + ".insertChat End!");

        return res;
    }

    @Override
    public List<ChatDTO> getChat(ChatDTO chatDTO) throws Exception {
        log.info(this.getClass().getName() + ".getChat Start!");

        String roomKey = CmmUtil.nvl(chatDTO.getRoomKey());

        List<ChatDTO> chatDTOList = null;
        redisDB.setKeySerializer(new StringRedisSerializer());
        redisDB.setValueSerializer(new Jackson2JsonRedisSerializer<>(ChatDTO.class));

        if (redisDB.hasKey(roomKey)) {
            chatDTOList = (List) redisDB.opsForList().range(roomKey, 0, -1);
        }

        log.info(this.getClass().getName() + ".getChat End!");
        return chatDTOList;
    }

    @Override
    public boolean setTimeOutHour(String roomKey, int hours) throws Exception {
        return redisDB.expire(roomKey, hours, TimeUnit.HOURS);
    }

    @Override
    public boolean setTimeOutMinute(String roomKey, int minutes) throws Exception {
        return redisDB.expire(roomKey, minutes, TimeUnit.MINUTES);
    }
}
