package es.artyhub.tienda_back.persistence.repository.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import es.artyhub.tienda_back.domain.dto.SesionDto;
import es.artyhub.tienda_back.domain.dto.UserDto;
import es.artyhub.tienda_back.domain.enums.UserRole;
import es.artyhub.tienda_back.persistence.dao.jpa.SesionJpaDao;
import es.artyhub.tienda_back.persistence.dao.jpa.entity.SesionJpaEntity;
import es.artyhub.tienda_back.persistence.dao.jpa.entity.UserJpaEntity;
import es.artyhub.tienda_back.persistence.repository.mapper.SesionMapper;
import es.artyhub.tienda_back.persistence.repository.mapper.UserMapper;

import org.mockito.InjectMocks;
import org.mockito.Mock;

@ExtendWith(MockitoExtension.class)
public class SesionRepositoryImplTest {
    
    @Mock
    private SesionJpaDao sesionJpaDao;

    @InjectMocks
    private SesionRepositoryImpl sesionRepository;

    @Nested
    @DisplayName("findByToken")
    class FindByTokenTest {
        
        @Test
        @DisplayName("Should return a sesion")
        void shouldReturnSesion() {
            String token = "token";

            SesionJpaEntity sesionJpaEntity = new SesionJpaEntity(token, 1L, new Date());

            when(sesionJpaDao.findByToken(token)).thenReturn(Optional.of(sesionJpaEntity));

            SesionDto sesionDto = SesionMapper.getInstance().fromSesionJpaEntityToSesionDto(sesionJpaEntity);

            Optional<SesionDto> result = sesionRepository.findByToken(token);

            assertEquals(sesionDto.getToken(), result.get().getToken());
            assertEquals(sesionDto.getUserId(), result.get().getUserId());
            assertEquals(sesionDto.getCreatedAt(), result.get().getCreatedAt());
        }
    }

    @Nested
    @DisplayName("findUserByToken")
    class FindUserByTokenTest {
        
        @Test
        @DisplayName("Should return a user")
        void shouldReturnUser() {
            String token = "token";

            UserJpaEntity userJpaEntity = new UserJpaEntity(1L, "name", "email", "password", "description", "address", "image", UserRole.USER);

            when(sesionJpaDao.findUserByToken(token)).thenReturn(userJpaEntity);

            UserDto userDto = UserMapper.getInstance().fromUserJpaEntityToUserDto(userJpaEntity);

            UserDto result = sesionRepository.findUserByToken(token);

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
    @DisplayName("insert sesion")
    class InsertSesionTest {
        
        @Test
        @DisplayName("Should insert a sesion")
        void shouldInsertSesion() {
            String token = "token";

            SesionDto sesionDto = new SesionDto(token, 1L, new Date());

            SesionJpaEntity sesionJpaEntity = SesionMapper.getInstance().fromSesionDtoToSesionJpaEntity(sesionDto);

            when(sesionJpaDao.insertSesion(sesionJpaEntity)).thenReturn(sesionJpaEntity);

            SesionDto result = sesionRepository.insertSesion(sesionDto);

            assertEquals(sesionDto.getToken(), result.getToken());
            assertEquals(sesionDto.getUserId(), result.getUserId());
            assertEquals(sesionDto.getCreatedAt(), result.getCreatedAt());
        }
    }

    @Nested
    @DisplayName("delete sesion")
    class DeleteSesionTest {
        
        @Test
        @DisplayName("Should delete a sesion")
        void shouldDeleteSesion() {
            String token = "token";

            sesionRepository.deleteSesion(token);

            verify(sesionJpaDao).deleteSesion(token);
        }
    }
}
