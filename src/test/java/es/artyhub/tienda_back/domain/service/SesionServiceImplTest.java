package es.artyhub.tienda_back.domain.service;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import es.artyhub.tienda_back.domain.dto.SesionDto;
import es.artyhub.tienda_back.domain.dto.UserDto;
import es.artyhub.tienda_back.domain.enums.UserRole;
import es.artyhub.tienda_back.domain.exception.BusinessException;
import es.artyhub.tienda_back.domain.repository.SesionRepository;
import es.artyhub.tienda_back.domain.service.impl.SesionServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
public class SesionServiceImplTest {
    
    @Mock
    private SesionRepository sesionRepository;

    @InjectMocks
    private SesionServiceImpl sesionService;

    @Nested
    @DisplayName("insertSesion")
    public class Insert {
        @Test
        @DisplayName("should return created sesion when sesion does not exist")
        public void insert_WhenSesionDoesNotExist() {
            SesionDto sesionDto = new SesionDto("token", 1L, new Date());

            when(sesionService.findSesionByToken("token")).thenReturn(Optional.empty());
            when(sesionRepository.insertSesion(sesionDto)).thenReturn(sesionDto);

            SesionDto result = sesionService.insertSesion(sesionDto);

            assertNotNull(result);
            assertEquals(sesionDto, result);
        }

        @Test
        @DisplayName("should throw BusinessException when sesion exists")
        public void insert_WhenSesionExists() {
            SesionDto sesionDto = new SesionDto("token", 1L, new Date());

            when(sesionService.findSesionByToken(sesionDto.getToken())).thenReturn(Optional.of(sesionDto));

            assertThrows(BusinessException.class, () -> sesionService.insertSesion(sesionDto));
        }
    }

    @Nested
    @DisplayName("deleteSesion")
    public class Delete {
        @Test
        @DisplayName("should delete sesion when sesion exists")
        public void delete_WhenSesionExists() {
            String token = "token";
            SesionDto sesionDto = new SesionDto(token, 1L, new Date());

            when(sesionService.findSesionByToken(token)).thenReturn(Optional.of(sesionDto));

            sesionService.deleteSesion(token);

            verify(sesionRepository).deleteSesion(token);
        }

        @Test
        @DisplayName("should throw BusinessException when sesion does not exist")
        public void delete_WhenSesionDoesNotExist() {
            String token = "token";

            when(sesionService.findSesionByToken(token)).thenReturn(Optional.empty());

            assertThrows(BusinessException.class, () -> sesionService.deleteSesion(token));
        }
    }
    
    @Nested
    @DisplayName("findSesionByToken")
    public class FindByToken {
        @Test
        @DisplayName("should return sesion when sesion exists")
        public void findByToken_WhenSesionExists() {
            String token = "token";

            SesionDto sesionDto = new SesionDto(token, 1L, new Date());

            when(sesionRepository.findByToken(token)).thenReturn(Optional.of(sesionDto));

            Optional<SesionDto> result = sesionService.findSesionByToken(token);

            assertNotNull(result);
            assertEquals(sesionDto, result.get());
        }

        @Test
        @DisplayName("should return null when sesion does not exist")
        public void findByToken_WhenSesionDoesNotExist() {
            String token = "token";

            when(sesionRepository.findByToken(token)).thenReturn(Optional.empty());

            Optional<SesionDto> result = sesionService.findSesionByToken(token);

            assertFalse(result.isPresent());
        }
    }


    @Test
    @DisplayName("should return user when findUserByToken exists")
    public void findUserByToken_WhenUserExists() {
        String token = "token";

        UserDto userDto = new UserDto(1L, "name", "email", "password", "description", "address", "image", UserRole.ADMIN);

        when(sesionRepository.findUserByToken(token)).thenReturn(userDto);

        UserDto result = sesionService.findUserByToken(token);

        assertNotNull(result);
        assertEquals(userDto.getId(), result.getId());
    }
}