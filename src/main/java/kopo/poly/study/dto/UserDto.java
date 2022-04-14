package kopo.poly.study.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private String userId;
    private String userPassword;
    private String userName;
    private String createDt; // Add in Service

    @Builder
    private UserDto(String userId, String userPassword, String userName) {
        this.userId = userId;
        this.userPassword = userPassword;
        this.userName = userName;
        this.createDt = createDt;
    }
}
