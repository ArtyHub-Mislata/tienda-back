package es.artyhub.tienda_back.persistence.repository.mapper;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Date;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import es.artyhub.tienda_back.domain.dto.SesionDto;
import es.artyhub.tienda_back.persistence.dao.jpa.entity.SesionJpaEntity;

public class SesionMapperTest {
    
    @Nested
    @DisplayName("Test fromSesionJpaEntityToSesionDto")
    class FromSesionJpaEntityToSesionDtoTest {

        @Test
        @DisplayName("Test fromSesionJpaEntityToSesionDto with null SesionJpaEntity should return null")
        void testFromSesionJpaEntityToSesionDto_NullInput() {
            SesionDto result = SesionMapper.getInstance().fromSesionJpaEntityToSesionDto(null);
            assertNull(result);
        }

        @Test
        @DisplayName("Test fromSesionJpaEntityToSesionDto with valid SesionJpaEntity should return SesionDto")
        void testFromSesionJpaEntityToSesionDto_ValidInput() {
            SesionJpaEntity sesionJpaEntity = new SesionJpaEntity(
                "token",
                1L,
                new Date()
            );

            SesionDto sesionDto = SesionMapper.getInstance().fromSesionJpaEntityToSesionDto(sesionJpaEntity);

            assertAll(
                    () -> assertNotNull(sesionDto),
                    () -> assertEquals("token", sesionDto.getToken()),
                    () -> assertEquals(1L, sesionDto.getUserId()));
        }
    }

    @Nested
    @DisplayName("Test fromSesionDtoToSesionJpaEntity")
    class FromSesionDtoToSesionJpaEntityTest {

        @Test
        @DisplayName("Test fromSesionDtoToSesionJpaEntity with null SesionDto should return null")
        void testFromSesionDtoToSesionJpaEntity_NullInput() {
            SesionJpaEntity result = SesionMapper.getInstance().fromSesionDtoToSesionJpaEntity(null);
            assertNull(result);
        }

        @Test
        @DisplayName("Test fromSesionDtoToSesionJpaEntity with valid SesionDto should return SesionJpaEntity")
        void testFromSesionDtoToSesionJpaEntity_ValidInput() {
            SesionDto sesionDto = new SesionDto(
                    "token",
                    1L,
                    new Date()
            );

            SesionJpaEntity sesionJpaEntity = SesionMapper.getInstance().fromSesionDtoToSesionJpaEntity(sesionDto);

            assertAll(
                    () -> assertNotNull(sesionJpaEntity),
                    () -> assertEquals("token", sesionJpaEntity.getToken()),
                    () -> assertEquals(1L, sesionJpaEntity.getUserId()));
        }
    }
}
