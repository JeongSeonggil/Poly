package kopo.poly.service.impl;

import kopo.poly.dto.ChatDTO;
import kopo.poly.persistance.redis.impl.ChatMapper;
import kopo.poly.service.IChatService;
import kopo.poly.util.CmmUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Slf4j
@Service("ChatService")
@RequiredArgsConstructor
public class ChatService implements IChatService {
    private final ChatMapper chatMapper;

    @Override
    public Set<String> getRoomList() throws Exception {
        log.info(this.getClass().getName() + "getRoomList Start!");

        log.info(this.getClass().getName() + "getRoomList End!");
        return chatMapper.getRoomList();
    }

    @Override
    public List<ChatDTO> insertChat(ChatDTO chatDTO) throws Exception {
        log.info(this.getClass().getName() + "insertChat Start!");

        int res = chatMapper.insertChat(chatDTO);
        List<ChatDTO> chatDTOList = null;

        if (res == 1) {
            log.info("insert OK");
            chatMapper.setTimeOutMinute(CmmUtil.nvl(chatDTO.getRoomKey()), 5);
            chatDTOList = this.getChat(chatDTO);
        }
        return chatDTOList;
    }

    @Override
    public List<ChatDTO> getChat(ChatDTO chatDTO) throws Exception {
        log.info(this.getClass().getName() + "getChat Start!");

        return chatMapper.getChat(chatDTO);
    }

    @Override
    public boolean setTimeOutHour(String roomKey, int hours) throws Exception {
        log.info(this.getClass().getName() + ".setTimeOutHour Start!");
        return chatMapper.setTimeOutHour(roomKey, hours);
    }

    @Override
    public boolean setTimeOutMinute(String roomKey, int minutes) throws Exception {
        log.info(this.getClass().getName() + "setTimeOutMinute Start!");
        return chatMapper.setTimeOutMinute(roomKey, minutes);
    }
}
