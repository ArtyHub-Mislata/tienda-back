package es.artyhub.tienda_back.domain.service;

import es.artyhub.tienda_back.domain.dto.UserDto;
import es.artyhub.tienda_back.domain.model.Page;

import java.util.Optional;

public interface UserService {
    
    Page<UserDto> findAll(int pageNumber, int pageSize);

    Optional<UserDto> findById(Long id);

    UserDto insert(UserDto userDto);

    UserDto update(UserDto userDto);

    void delete(Long id);
}
