package kopo.poly.controller;

import kopo.poly.dto.ChatDTO;
import kopo.poly.service.impl.ChatService;
import kopo.poly.util.CmmUtil;
import kopo.poly.util.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Controller
@Slf4j
@RequiredArgsConstructor
public class ChatController {
    private final ChatService chatService;

    @GetMapping("/chat/index")
    public String index() throws Exception {
        log.info(this.getClass().getName() + "index Start!");


        log.info(this.getClass().getName() + "index End!");

        return "/chat/index";
    }

    @PostMapping("/chat/intro")
    public String intro(HttpServletRequest request, HttpSession session) throws Exception {
        log.info(this.getClass().getName() + ".intro Start!");

        session.setAttribute("SS_ROOM_NAME", "");
        session.setAttribute("SS_USER_NAME", "");

        String room_name = CmmUtil.nvl(request.getParameter("room_name"));
        String userNm = CmmUtil.nvl(request.getParameter("user_name"));

        session.setAttribute("SS_ROOM_NAME", room_name);
        session.setAttribute("SS_USER_NAME", userNm);

        ChatDTO chatDTO = new ChatDTO();

        chatDTO.setRoomKey("Chat_" + room_name);
        chatDTO.setUserNm("관리자");
        chatDTO.setMsg(userNm + "님![" + room_name + "] 채팅방 입장을 환영합니다");
        chatDTO.setDateTime(DateUtil.getDateTime("yyyy.MM.dd hh:mm:ss"));

        chatService.insertChat(chatDTO);

        log.info(this.getClass().getName() + ".intro End!");

        return "/chat/intro";
    }

    @PostMapping("/chat/roomList")
    @ResponseBody
    public Set<String> roomList() throws Exception {
        log.info(this.getClass().getName() + "roomList Start!");

        Set<String> roomList = chatService.getRoomList();

        if (roomList == null) {
            log.info(this.getClass().getName() + ".RoomList Null");
            roomList = new LinkedHashSet<>();
        }

        log.info("roomList[0]" + roomList);

        log.info(this.getClass().getName() + "roomList End!");

        return roomList;
    }

    @PostMapping("/chat/msg")
    @ResponseBody
    public List<ChatDTO> msg(HttpServletRequest request, HttpSession session) throws Exception {
        log.info(this.getClass().getName() + ".msg Start!");

        String room_name = CmmUtil.nvl((String) session.getAttribute("SS_ROOM_NAME"));
        String userNm = CmmUtil.nvl((String) session.getAttribute("SS_USER_NAME"));

        String msg = CmmUtil.nvl(request.getParameter("send_msg"));

        log.info("userNm : " + userNm);
        log.info("room_name : " + room_name);
        log.info("msg : " + msg);

        log.info(this.getClass().getName() + ".msg End!");

        List<ChatDTO> chatDTOList = null;

        if (msg.length() > 0) {
            ChatDTO chatDTO = new ChatDTO();
            chatDTO.setRoomKey("Chat_" + room_name);
            chatDTO.setUserNm(userNm);
            chatDTO.setMsg(msg);
            chatDTO.setDateTime(DateUtil.getDateTime("yyyy.MM.dd hh:mm:ss"));

            chatDTOList = chatService.insertChat(chatDTO);
        }

        return chatDTOList;
    }

    @PostMapping("/chat/getMsg")
    @ResponseBody
    public List<ChatDTO> getMsg(HttpSession session) throws Exception {
        log.info(this.getClass().getName() + ".getMsg Start!");
        String room_name = CmmUtil.nvl((String) session.getAttribute("SS_ROOM_NAME"));

        log.info("room_name : " + room_name);

        ChatDTO chatDTO = new ChatDTO();

        chatDTO.setRoomKey("Chat_" + room_name);

        List<ChatDTO> chatDTOList = chatService.getChat(chatDTO);

        if (chatDTOList == null) {
            chatDTOList = new LinkedList<>();
        }

        chatDTO = null;

        log.info(this.getClass().getName() + ".getMsg End!");


        return chatDTOList;
    }
}
