package kopo.poly.dto;

import lombok.Data;

@Data
public class ChatDTO {
    private String roomKey;
    private String userNm;
    private String msg;
    private String dateTime;
}
