package kopo.poly.study.jpa;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserEntity { // throw DB
    private String userId;
    private String userPassword;
    private String userName;
    private String createDt;


        @Builder
        private UserEntity(String userId, String userPassword, String userName, String createDt) {
        this.userId = userId;
        this.userPassword = userPassword;
        this.userName = userName;
        this.createDt = createDt;
    }
}
