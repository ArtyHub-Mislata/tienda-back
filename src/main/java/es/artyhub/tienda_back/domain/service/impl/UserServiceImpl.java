package es.artyhub.tienda_back.domain.service.impl;

import es.artyhub.tienda_back.domain.dto.UserDto;
import es.artyhub.tienda_back.domain.exception.BusinessException;
import es.artyhub.tienda_back.domain.exception.ValidationException;
import es.artyhub.tienda_back.domain.model.Page;
import es.artyhub.tienda_back.domain.repository.UserRepository;
import es.artyhub.tienda_back.domain.service.UserService;
import jakarta.transaction.Transactional;

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
    @Transactional
    public UserDto insert(UserDto userDto) {
        if (userDto.getName() == null || userDto.getName().trim().isEmpty()) {
            throw new ValidationException("User name is required");
        }

        if (userDto.getEmail() == null || userDto.getEmail().trim().isEmpty()) {
            throw new ValidationException("User email is required");
        }

        if (userDto.getPassword() == null || userDto.getPassword().trim().isEmpty()) {
            throw new ValidationException("User password is required");
        }

        if (userDto.getnAccount() == null || userDto.getnAccount().trim().isEmpty()) {
            throw new ValidationException("User nAccount is required");
        }

        if (userDto.getnAccount().length() != 16) {
            throw new ValidationException("User nAccount must have 16 characters");
        }

        if (userDto.getAddress() == null || userDto.getAddress().trim().isEmpty()) {
            throw new ValidationException("User address is required");
        }

        if (userDto.getImageProfileUrl() == null) {
            throw new ValidationException("User imageProfileUrl cannot be null");
        }

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
