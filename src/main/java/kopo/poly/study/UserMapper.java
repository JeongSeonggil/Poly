package kopo.poly.study;

import kopo.poly.study.dto.UserDto;
import kopo.poly.study.jpa.UserEntity;
import kopo.poly.study.vo.RequestUser;
import kopo.poly.study.vo.ResponseUser;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserEntity userEntityToUserDTO(UserDto userDto);

    UserDto userDTOToUserEntity(UserEntity userEntity);

    RequestUser requestUserToUserDTO(UserDto userDto);

    ResponseUser responseUserToUserDTO(UserDto userDto);

    UserDto userDTOToRequestUser(RequestUser requestUser);

    UserDto userDtoToResponseUser(ResponseUser requestUser);
}
