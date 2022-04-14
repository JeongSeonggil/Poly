package kopo.poly.study.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestUser {
    @NotNull(message = "userId cannot be Null")
    private String userId; // 필수
    @NotNull(message = "userPassword cannot be Null")
    private String userPassword; // 필수
    private String userName; // 선택
}
