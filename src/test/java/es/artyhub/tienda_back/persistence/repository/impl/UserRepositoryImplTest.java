package es.artyhub.tienda_back.persistence.repository.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import es.artyhub.tienda_back.domain.dto.UserDto;
import es.artyhub.tienda_back.domain.enums.UserRole;
import es.artyhub.tienda_back.domain.model.Page;
import es.artyhub.tienda_back.persistence.dao.jpa.UserJpaDao;
import es.artyhub.tienda_back.persistence.dao.jpa.entity.UserJpaEntity;
import es.artyhub.tienda_back.persistence.repository.mapper.UserMapper;

import org.mockito.InjectMocks;
import org.mockito.Mock;

@ExtendWith(MockitoExtension.class)
public class UserRepositoryImplTest {
    
    @Mock
    private UserJpaDao userJpaDao;

    @InjectMocks
    private UserRepositoryImpl userRepository;

    @Nested
    @DisplayName("findAll")
    class FindAllTest {
        
        @Test
        @DisplayName("Should return a page of users")
        void shouldReturnPageOfUsers() {
            int page = 1;
            int size = 10;

            UserJpaEntity userJpaEntity = new UserJpaEntity();
            List<UserJpaEntity> userJpaEntityList = List.of(userJpaEntity);
            List<UserDto> userDtoList = userJpaEntityList.stream().map(UserMapper.getInstance()::fromUserJpaEntityToUserDto).toList();

            when(userJpaDao.findAll(page, size)).thenReturn(userJpaEntityList);
            when(userJpaDao.count()).thenReturn((long) userJpaEntityList.size());

            Page<UserDto> userDtoPage = new Page<>(userDtoList, page, size, userJpaEntityList.size());

            Page<UserDto> result = userRepository.findAll(page, size);

            assertEquals(userDtoPage.pageNumber(), result.pageNumber());
            assertEquals(userDtoPage.pageSize(), result.pageSize());
            assertEquals(userDtoPage.totalElements(), result.totalElements());
            assertEquals(userDtoPage.totalPages(), result.totalPages());
        }
    }

    @Nested
    @DisplayName("findById")
    class FindByIdTest {
        
        @Test
        @DisplayName("Should return a user")
        void shouldReturnUser() {
            Long userId = 1L;

            UserJpaEntity userJpaEntity = new UserJpaEntity(userId, "Name", "Email", "Password", "Description", "Address", "Image", UserRole.USER);

            when(userJpaDao.findById(userId)).thenReturn(Optional.of(userJpaEntity));

            UserDto userDto = UserMapper.getInstance().fromUserJpaEntityToUserDto(userJpaEntity);

            Optional<UserDto> result = userRepository.findById(userId);

            assertEquals(userDto.getId(), result.get().getId());
            assertEquals(userDto.getName(), result.get().getName());
            assertEquals(userDto.getEmail(), result.get().getEmail());
            assertEquals(userDto.getPassword(), result.get().getPassword());
            assertEquals(userDto.getDescription(), result.get().getDescription());
            assertEquals(userDto.getAddress(), result.get().getAddress());
            assertEquals(userDto.getRole(), result.get().getRole());
        }
    }

    @Nested
    @DisplayName("save")
    class SaveTest {
        
        @Test
        @DisplayName("Should insert a user if id is null")
        void shouldInsertUser() {
            UserDto userDto = new UserDto(null, "Name", "Email", "Password", "Description", "Address", "Image", UserRole.USER);

            UserJpaEntity userJpaEntity = UserMapper.getInstance().fromUserDtoToUserJpaEntity(userDto);

            when(userJpaDao.insert(userJpaEntity)).thenReturn(userJpaEntity);

            UserDto result = userRepository.save(userDto);

            assertEquals(userDto.getId(), result.getId());
            assertEquals(userDto.getName(), result.getName());
            assertEquals(userDto.getEmail(), result.getEmail());
            assertEquals(userDto.getPassword(), result.getPassword());
            assertEquals(userDto.getDescription(), result.getDescription());
            assertEquals(userDto.getAddress(), result.getAddress());
            assertEquals(userDto.getRole(), result.getRole());
        }

        @Test
        @DisplayName("Should update a user if id is not null")
        void shouldUpdateUser() {
            UserDto userDto = new UserDto(1L, "Name", "Email", "Password", "Description", "Address", "Image", UserRole.USER);

            UserJpaEntity userJpaEntity = UserMapper.getInstance().fromUserDtoToUserJpaEntity(userDto);

            when(userJpaDao.update(userJpaEntity)).thenReturn(userJpaEntity);

            UserDto result = userRepository.save(userDto);

            assertEquals(userDto.getId(), result.getId());
            assertEquals(userDto.getName(), result.getName());
            assertEquals(userDto.getEmail(), result.getEmail());
            assertEquals(userDto.getPassword(), result.getPassword());
            assertEquals(userDto.getDescription(), result.getDescription());
            assertEquals(userDto.getAddress(), result.getAddress());
            assertEquals(userDto.getRole(), result.getRole());
        }
    }

    @Nested
    @DisplayName("delete")
    class DeleteTest {
        
        @Test
        @DisplayName("Should delete a user")
        void shouldDeleteUser() {
            Long userId = 1L;

            userRepository.delete(userId);

            verify(userJpaDao).deleteById(userId);
        }
    }

    @Nested
    @DisplayName("findByEmail")
    class FindByEmailTest {
        
        @Test
        @DisplayName("Should return a user")
        void shouldReturnUser() {
            String userEmail = "Email";

            UserJpaEntity userJpaEntity = new UserJpaEntity(1L, "Name", userEmail, "Password", "Description", "Address", "Image", UserRole.USER);

            when(userJpaDao.findByEmail(userEmail)).thenReturn(userJpaEntity);

            UserDto userDto = UserMapper.getInstance().fromUserJpaEntityToUserDto(userJpaEntity);

            UserDto result = userRepository.findByEmail(userEmail);

            assertEquals(userDto.getId(), result.getId());
            assertEquals(userDto.getName(), result.getName());
            assertEquals(userDto.getEmail(), result.getEmail());
            assertEquals(userDto.getPassword(), result.getPassword());
            assertEquals(userDto.getDescription(), result.getDescription());
            assertEquals(userDto.getAddress(), result.getAddress());
            assertEquals(userDto.getRole(), result.getRole());
        }
    }
}
