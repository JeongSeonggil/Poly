package kopo.poly.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Data
public class RedisDTO {
    private String name = "";
    private String email = "";
    private String addr = "";
    private String age = "";
    private String test_text = "";
}
