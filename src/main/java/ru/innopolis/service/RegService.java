package ru.innopolis.service;

import ru.innopolis.service.dto.UserDto;

/**
 * RegisterService
 *
 * @author Ekaterina Belolipetskaya
 */
public interface RegService {
    void registerUser(UserDto userDto);
}
