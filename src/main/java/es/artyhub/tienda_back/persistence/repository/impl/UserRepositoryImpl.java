package es.artyhub.tienda_back.persistence.repository.impl;

import es.artyhub.tienda_back.domain.dto.UserDto;
import es.artyhub.tienda_back.domain.model.Page;
import es.artyhub.tienda_back.domain.repository.UserRepository;
import es.artyhub.tienda_back.persistence.dao.jpa.UserJpaDao;
import es.artyhub.tienda_back.persistence.dao.jpa.entity.UserJpaEntity;
import es.artyhub.tienda_back.persistence.repository.mapper.UserMapper;

import java.util.List;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {
    private final UserJpaDao userJpaDao;

    public UserRepositoryImpl(UserJpaDao userJpaDao) {
        this.userJpaDao = userJpaDao;
    }

    @Override
    public Page<UserDto> findAll(int pageNumber, int pageSize) {
        List<UserDto> listaUserDto = userJpaDao
                .findAll(pageNumber, pageSize)
                .stream()
                .map(UserMapper.getInstance()::fromUserJpaEntityToUserDto)
                .toList();
        long totalElements = userJpaDao.count();

        return new Page<>(
                listaUserDto,
                pageNumber,
                pageSize,
                totalElements
        );
    }

    @Override
    public Optional<UserDto> findById(Long id) {
        return userJpaDao
                .findById(id)
                .map(UserMapper.getInstance()::fromUserJpaEntityToUserDto);

    }

    @Override
    public UserDto save(UserDto user) {
        UserJpaEntity userJpaEntity = UserMapper.getInstance().fromUserDtoToUserJpaEntity(user);
        if (user.getId() == null) {
            return UserMapper.getInstance().fromUserJpaEntityToUserDto(userJpaDao.insert(userJpaEntity));
        }
        return UserMapper.getInstance().fromUserJpaEntityToUserDto(userJpaDao.update(userJpaEntity));

    }

    @Override
    public void delete(Long id) {
        userJpaDao.deleteById(id);
    }
}
