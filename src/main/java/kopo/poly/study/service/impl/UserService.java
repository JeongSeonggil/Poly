package kopo.poly.study.service.impl;

import kopo.poly.study.UserMapper;
import kopo.poly.study.dto.UserDto;
import kopo.poly.study.jpa.UserEntity;
import kopo.poly.study.service.IUserService;
import kopo.poly.study.vo.RequestUser;
import kopo.poly.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService implements IUserService {
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    @Override
    public UserDto createUser(RequestUser requestUser) {
        log.info(this.getClass().getName() + "createUser Start!");
        UserDto pDTO = UserMapper.INSTANCE.userDTOToRequestUser(requestUser);
        pDTO.setCreateDt(DateUtil.getDateTime("yyyyMMdd"));
        pDTO.setUserPassword(bCryptPasswordEncoder.encode(pDTO.getUserPassword()));

        log.info("encPassword : ={}", pDTO.getUserPassword());

        log.info("UserDTO : " + pDTO);
        UserEntity pEntity = UserMapper.INSTANCE.userEntityToUserDTO(pDTO);
        log.info("UserEntity : " + pEntity);

        log.info(this.getClass().getName() + "createUser End!");

        return pDTO;
    }

}
