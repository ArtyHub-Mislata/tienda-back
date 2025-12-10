package es.artyhub.tienda_back.domain.repository;

import es.artyhub.tienda_back.domain.dto.UserDto;
import es.artyhub.tienda_back.domain.model.Page;

import java.util.Optional;

public interface UserRepository {
    
    Page<UserDto> findAll(int pageNumber, int pageSize);

    Optional<UserDto> findById(Long id);

    UserDto save(UserDto user);

    void delete(Long id);

    UserDto findByEmail(String email);
    //TODO Metodo para obtener todas las obras de un user
    //Page<ArtworkDto> findAllArtworks(int pageNumber, int pageSize)
}
