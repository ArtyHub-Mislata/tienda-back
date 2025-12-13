package es.artyhub.tienda_back.domain.service.impl;

import es.artyhub.tienda_back.domain.dto.ArtworkDto;
import es.artyhub.tienda_back.domain.dto.UserDto;
import es.artyhub.tienda_back.domain.exception.BusinessException;
import es.artyhub.tienda_back.domain.model.Page;
import es.artyhub.tienda_back.domain.repository.ArtworkRepository;
import es.artyhub.tienda_back.domain.repository.UserRepository;
import es.artyhub.tienda_back.domain.service.UserService;
import jakarta.transaction.Transactional;

public class UserServiceImpl implements UserService {
    
    private final UserRepository userRepository;
    private final ArtworkRepository artworkRepository;

    public UserServiceImpl(UserRepository userRepository, ArtworkRepository artworkRepository) {
        this.userRepository = userRepository;
        this.artworkRepository = artworkRepository;
    }

    @Override
    public Page<UserDto> findAll(int pageNumber, int pageSize) {
        return userRepository.findAll(pageNumber, pageSize);
    }

    @Override
    public Page<ArtworkDto> findAllArtworks(Long id, int pageNumber, int pageSize) {
        return artworkRepository.findAllArtworksOfUser(id, pageNumber, pageSize);
    }

    @Override
    public UserDto findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new BusinessException("User with id " + id + " not found"));
    }

    @Override
    @Transactional
    public UserDto insert(UserDto userDto) {
        return userRepository.save(userDto);
    }

    @Override
    @Transactional
    public UserDto update(UserDto userDto) {
        if (userRepository.findById(userDto.getId()).isEmpty()) {
            throw new BusinessException("User with id " + userDto.getId() + " does not exist");
        } else {
            return userRepository.save(userDto);
        }
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (userRepository.findById(id).isEmpty()) {
            throw new BusinessException("User with id " + id + " does not exist");
        } else {
            userRepository.delete(id);
        }
    }

    @Override
    public UserDto findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
