package es.artyhub.tienda_back.persistence.repository.impl;

import es.artyhub.tienda_back.domain.dto.UserDto;
import es.artyhub.tienda_back.domain.model.Page;
import es.artyhub.tienda_back.domain.model.User;
import es.artyhub.tienda_back.domain.repository.UserRepository;
import es.artyhub.tienda_back.persistence.dao.jpa.UserJpaDao;
import es.artyhub.tienda_back.persistence.repository.mapper.UserMapper;

import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {
    private final UserJpaDao userJpaDao;

    public UserRepositoryImpl(UserJpaDao userJpaDao) {
        this.userJpaDao = userJpaDao;
    }

    @Override
    public Page<UserDto> findAll(int pageNumber, int pageSize) {
        userJpaDao.findAll(pageNumber, pageSize);
    }

    @Override
    public Optional<UserDto> findById(Long id) {
        return userJpaDao
                .findById(id)
                .map(UserMapper.getInstance()::fromUserJpaEntityToUserDto);

    }

    @Override
    public UserDto save(UserDto user) {
        return UserMapper.getInstance().fromUserJpaEntityToUserDto(
                userJpaDao.insert(
                        UserMapper.getInstance().fromUserDtoToUserJpaEntity(user)
                )
        );
    }

    @Override
    public void delete(Long id) {
        userJpaDao.deleteById(id);
    }
}
