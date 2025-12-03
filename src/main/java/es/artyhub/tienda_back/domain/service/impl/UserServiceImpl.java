package es.artyhub.tienda_back.domain.service.impl;

import es.artyhub.tienda_back.domain.dto.UserDto;
import es.artyhub.tienda_back.domain.exception.BusinessException;
import es.artyhub.tienda_back.domain.model.Page;
import es.artyhub.tienda_back.domain.repository.UserRepository;
import es.artyhub.tienda_back.domain.service.UserService;
import java.util.Optional;

public class UserServiceImpl implements UserService {
    
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Page<UserDto> findAll(int pageNumber, int pageSize) {
        return userRepository.findAll(pageNumber, pageSize);
    }

    @Override
    public Optional<UserDto> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public UserDto insert(UserDto userDto) {
        if (findById(userDto.id()).isPresent()) {
            throw new BusinessException("User with id " + userDto.id() + " already exists");
        }
        return userRepository.save(userDto);
    }

    @Override
    public UserDto update(UserDto userDto) {
        if (!findById(userDto.id()).isEmpty()) {
            throw new BusinessException("User with id " + userDto.id() + " does not exist");
        }
        return userRepository.save(userDto);
    }

    @Override
    public void delete(Long id) {
        userRepository.delete(id);
    }
}
