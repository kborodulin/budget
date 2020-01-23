package ru.innopolis.service;

import ru.innopolis.service.dto.UserDto;

/**
 * RegService
 *
 * @author Ekaterina Belolipetskaya
 */
public interface RegService {
    UserDto registerUser(UserDto userDto);
}
