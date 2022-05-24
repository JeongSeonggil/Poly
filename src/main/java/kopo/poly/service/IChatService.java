package kopo.poly.service;

import kopo.poly.dto.ChatDTO;

import java.util.List;
import java.util.Set;

public interface IChatService {
    String roomKey = "myRoomKey";

    Set<String> getRoomList() throws Exception;

    List<ChatDTO> insertChat(ChatDTO chatDTO) throws Exception;

    List<ChatDTO> getChat(ChatDTO chatDTO) throws Exception;

    boolean setTimeOutHour(String roomKey, int hours) throws Exception;

    boolean setTimeOutMinute(String roomKey, int minutes) throws Exception;
}
