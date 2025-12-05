package es.artyhub.tienda_back.domain.service.impl;

import es.artyhub.tienda_back.domain.dto.UserDto;
import es.artyhub.tienda_back.domain.exception.BusinessException;
import es.artyhub.tienda_back.domain.exception.ValidationException;
import es.artyhub.tienda_back.domain.model.Page;
import es.artyhub.tienda_back.domain.repository.UserRepository;
import es.artyhub.tienda_back.domain.service.UserService;

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
    public UserDto findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new BusinessException("User with id " + id + " not found"));
    }

    @Override
    public UserDto insert(UserDto userDto) {
        if (userRepository.findById(userDto.id()).isPresent()) {
            throw new BusinessException("User with id " + userDto.id() + " already exists");
        } else if (userDto.name() == null || userDto.name().trim().isEmpty()) {
            throw new ValidationException("User name is required");
        } else if (userDto.email() == null || userDto.email().trim().isEmpty()) {
            throw new ValidationException("User email is required");
        } else if (userDto.password() == null || userDto.password().trim().isEmpty()) {
            throw new ValidationException("User password is required");
        } else if (userDto.nAccount() == null || userDto.nAccount().trim().isEmpty()) {
            throw new ValidationException("User nAccount is required");
        } else if (userDto.nAccount().length() != 16) {
            throw new ValidationException("User nAccount must have 16 characters");
        } else if (userDto.address() == null || userDto.address().trim().isEmpty()) {
            throw new ValidationException("User address is required");
        } else if (userDto.imageProfileUrl() == null) {
            throw new ValidationException("User imageProfileUrl cannot be null");
        } else {
            return userRepository.save(userDto);
        }
    }

    @Override
    public UserDto update(UserDto userDto) {
        if (userRepository.findById(userDto.id()).isEmpty()) {
            throw new BusinessException("User with id " + userDto.id() + " does not exist");
        } else {
            return userRepository.save(userDto);
        }
    }

    @Override
    public void delete(Long id) {
        if (userRepository.findById(id).isEmpty()) {
            throw new BusinessException("User with id " + id + " does not exist");
        } else {
            userRepository.delete(id);
        }
    }
}
