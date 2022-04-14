package kopo.poly.study.service;

import kopo.poly.study.dto.UserDto;
import kopo.poly.study.vo.RequestUser;

public interface IUserService {
    UserDto createUser(RequestUser requestUser);
}
