package es.artyhub.tienda_back.domain.repository;

import es.artyhub.tienda_back.domain.dto.UserDto;
import es.artyhub.tienda_back.domain.model.Page;

public interface UserRepository {
    
    Page<UserDto> findAll(int pageNumber, int pageSize);

    UserDto findById(Long id);

    UserDto save(UserDto user);

    void delete(Long id);
}
