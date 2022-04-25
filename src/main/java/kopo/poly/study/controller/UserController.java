package kopo.poly.study.controller;

import kopo.poly.study.UserMapper;
import kopo.poly.study.dto.MailDto;
import kopo.poly.study.dto.UserDto;
import kopo.poly.study.service.impl.MailService;
import kopo.poly.study.service.impl.UserService;
import kopo.poly.study.vo.RequestUser;
import kopo.poly.study.vo.ResponseUser;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class UserController {
    private UserService userService;
    private ModelMapper mapper;
    private MailService mailService;

    @Autowired
    public UserController(UserService userService, ModelMapper mapper, MailService mailService) {
        this.userService = userService;
        this.mapper = mapper;
        this.mailService = mailService;
    }

    @PostMapping("/createUser")
    public ResponseUser createUser(@RequestBody RequestUser requestUser) throws Exception {
        log.info("#########################" + this.getClass().getName() + ".createUser Start! #########################");

        log.info("requestUser : " + requestUser);

        UserDto userDto = userService.createUser(requestUser);
        log.info(this.getClass().getName() + "userDTO : " + userDto);

        ResponseUser responseUser = UserMapper.INSTANCE.responseUserToUserDTO(userDto);

        log.info("#########################" + this.getClass().getName() + ".createUser End! #########################");

        return responseUser;
    }

    @GetMapping("/Test")
    public void test() throws Exception {
        log.info(this.getClass().getName() + "Test Start!");

        MailDto mailDto = new MailDto();
        mailDto.setMailAddress("dataofsg02@naver.com");
        mailDto.setTitle("Mail Title");
        mailDto.setMailMessage("Mail Massage");

        mailService.sendMail(mailDto);

        log.info(this.getClass().getName() + "Test End!");

    }
}
