package es.artyhub.tienda_back.domain.service.impl;

import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.when;

import es.artyhub.tienda_back.domain.dto.SesionDto;
import es.artyhub.tienda_back.domain.exception.BusinessException;
import es.artyhub.tienda_back.domain.repository.SesionRepository;

@ExtendWith(MockitoExtension.class)
public class SesionServiceImplTest {
    
    @Mock
    private SesionRepository sesionRepository;

    @InjectMocks
    private SesionServiceImpl sesionService;

    @Nested
    @DisplayName("Insert sesion")
    class InsertSesion {
        @Test
        @DisplayName("While sesion is valid should insert sesion")
        void whileSesionIsValid_ShouldInsertSesion() {
            String token = "token";
            SesionDto sesionDto = new SesionDto(token, 1L, new Date());

            when(sesionRepository.findByToken(token)).thenReturn(Optional.empty());

            assertDoesNotThrow(() -> sesionService.insertSesion(sesionDto));

            Mockito.verify(sesionRepository).findByToken(token);
            Mockito.verify(sesionRepository).insertSesion(sesionDto);
        }

        @Test
        @DisplayName("While sesion exists should throw exception")
        void whileSesionExists_ShouldThrowException() {
            SesionDto sesionDto = new SesionDto("token", 1L, new Date());

            when(sesionRepository.findByToken(sesionDto.getToken())).thenReturn(Optional.of(sesionDto));

            assertThrows(BusinessException.class, () -> sesionService.insertSesion(sesionDto));

            Mockito.verify(sesionRepository).findByToken(sesionDto.getToken());
            Mockito.verify(sesionRepository, never()).insertSesion(sesionDto);
        }
    }

    @Nested
    @DisplayName("Delete sesion")
    class DeleteSesion {
        @Test
        @DisplayName("While token is valid should delete sesion")
        void whileTokenIsValid_ShouldDeleteSesion() {
            String token = "token";

            SesionDto sesionDto = new SesionDto(token, 1L, new Date());

            when(sesionRepository.findByToken(token)).thenReturn(Optional.of(sesionDto));

            assertDoesNotThrow(() -> sesionService.deleteSesion(token));

            Mockito.verify(sesionRepository).findByToken(token);
            Mockito.verify(sesionRepository).deleteSesion(token);
        }

        @Test
        @DisplayName("While token is not valid should throw exception")
        void whileTokenIsNotValid_ShouldThrowException() {
            String token = "token";

            when(sesionRepository.findByToken(token)).thenReturn(Optional.empty());

            assertThrows(BusinessException.class, () -> sesionService.deleteSesion(token));

            Mockito.verify(sesionRepository).findByToken(token);
            Mockito.verify(sesionRepository, never()).deleteSesion(token);
        }
    }

    @Nested
    @DisplayName("Is valid token")
    class IsValidToken {
        @Test
        @DisplayName("While token is valid should return true")
        void whileTokenIsValid_ShouldReturnTrue() {
            String token = "token";

            SesionDto sesionDto = new SesionDto(token, 1L, new Date());

            when(sesionRepository.findByToken(token)).thenReturn(Optional.of(sesionDto));

            assertDoesNotThrow(() -> sesionService.isValidToken(token));

            Mockito.verify(sesionRepository).findByToken(token);
        }
    }
}
