package es.artyhub.tienda_back.domain.service.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.InjectMocks;
import es.artyhub.tienda_back.domain.model.Page;
import es.artyhub.tienda_back.domain.dto.ArtworkDto;
import es.artyhub.tienda_back.domain.dto.CategoryDto;
import es.artyhub.tienda_back.domain.dto.UserDto;
import es.artyhub.tienda_back.domain.enums.UserRole;
import es.artyhub.tienda_back.domain.exception.BusinessException;
import es.artyhub.tienda_back.domain.repository.ArtworkRepository;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.when;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class ArtworkServiceImplTest {

    @Mock
    private ArtworkRepository artworkRepository;

    @InjectMocks
    private ArtworkServiceImpl artworkService;
    
    @Nested
    @DisplayName("Find all artworks")
    class FindAllArtworks {
        
        @Test
        @DisplayName("While artworks exist should return list of artworks")
        void whileArtworksExist_ShouldReturnListOfArtworks() {
            int page = 1;
            int size = 10;

            List<ArtworkDto> artworks = List.of(
                new ArtworkDto(1L, "name1", "description1", "image1", new BigDecimal(1.0), new CategoryDto(1L, "category1"), new UserDto(1L, "name", "email", "password", "nAccount", "description", "address", "imageProfileUrl", UserRole.ADMIN)),
                new ArtworkDto(2L, "name2", "description2", "image2", new BigDecimal(2.0), new CategoryDto(2L, "category2"), new UserDto(2L, "name", "email", "password", "nAccount", "description", "address", "imageProfileUrl", UserRole.ADMIN)),
                new ArtworkDto(3L, "name3", "description3", "image3", new BigDecimal(3.0), new CategoryDto(3L, "category3"), new UserDto(3L, "name", "email", "password", "nAccount", "description", "address", "imageProfileUrl", UserRole.ADMIN)),
                new ArtworkDto(4L, "name4", "description4", "image4", new BigDecimal(4.0), new CategoryDto(4L, "category4"), new UserDto(4L, "name", "email", "password", "nAccount", "description", "address", "imageProfileUrl", UserRole.ADMIN)),
                new ArtworkDto(5L, "name5", "description5", "image5", new BigDecimal(5.0), new CategoryDto(5L, "category5"), new UserDto(5L, "name", "email", "password", "nAccount", "description", "address", "imageProfileUrl", UserRole.ADMIN))
            );

            when(artworkRepository.findAll(page, size)).thenReturn(new Page<>(artworks, page, size, artworks.size()));

            Page<ArtworkDto> result = artworkService.findAll(page, size);

            assertAll(
                () -> assertNotNull(result, "Result should not be null"),
                () -> assertEquals(artworks.size(), result.data().size(), "Result size should be equal to artworks size"),
                () -> assertEquals(artworks.getFirst().getId(), result.data().getFirst().getId(), "Result first id should be equal to artworks first id"),
                () -> assertEquals(artworks.get(2).getId(), result.data().get(2).getId(), "Result second id should be equal to artworks second id"),
                () -> assertEquals(artworks.get(3).getId(), result.data().get(3).getId(), "Result third id should be equal to artworks third id"),
                () -> assertEquals(artworks.get(4).getId(), result.data().get(4).getId(), "Result fourth id should be equal to artworks fourth id"),
                () -> assertEquals(artworks.getLast().getId(), result.data().getLast().getId(), "Result last id should be equal to artworks last id")
            );

            Mockito.verify(artworkRepository).findAll(page, size);
        }

        @Test
        @DisplayName("While no artworks exist should return empty list")
        void whileNoArtworksExist_ShouldReturnEmptyList() {
            int page = 1;
            int size = 10;

            Page<ArtworkDto> artworks = new Page<>(List.of(), page, size, 0);

            when(artworkRepository.findAll(page, size)).thenReturn(artworks);

            Page<ArtworkDto> result = artworkService.findAll(page, size);

            assertAll(
                () -> assertNotNull(result, "Result should not be null"),
                () -> assertEquals(artworks.data().size(), result.data().size(), "Result size should be equal to artworks size")
            );

            Mockito.verify(artworkRepository).findAll(page, size);
        }
    }
    
    @Nested
    @DisplayName("Find artwork by id")
    class FindArtworkById {
        
        @Test
        @DisplayName("While artwork exists should return artwork")
        void whileArtworkExists_ShouldReturnArtwork() {
            Long id = 1L;

            when(artworkRepository.findById(id)).thenReturn(Optional.of(new ArtworkDto(id, "name", "description", "image", new BigDecimal(1.0), new CategoryDto(1L, "category"), new UserDto(1L, "name", "email", "password", "nAccount", "description", "address", "imageProfileUrl", UserRole.ADMIN))));

            ArtworkDto result = artworkService.findById(id);

            assertAll(
                () -> assertNotNull(result, "Result should not be null"),
                () -> assertEquals(id, result.getId(), "Result id should be equal to artwork id"),
                () -> assertEquals("name", result.getName(), "Result name should be equal to artwork name"),
                () -> assertEquals("description", result.getDescription(), "Result description should be equal to artwork description"),
                () -> assertEquals("image", result.getImage(), "Result image should be equal to artwork image"),
                () -> assertEquals(new BigDecimal(1.0), result.getPrice(), "Result price should be equal to artwork price"),
                () -> assertEquals(1L, result.getCategoryDto().getId(), "Result category id should be equal to artwork category id"),
                () -> assertEquals(1L, result.getUserDto().getId(), "Result user id should be equal to artwork user id")
            );

            Mockito.verify(artworkRepository).findById(id);
        }

        @Test
        @DisplayName("While id doesn't exist should throw BusinessException")
        void whileIdDoesntExist_ShouldThrowBusinessException() {
            Long id = 1L;

            when(artworkRepository.findById(id)).thenReturn(Optional.empty());

            assertThrows(BusinessException.class, () -> artworkService.findById(id));

            Mockito.verify(artworkRepository).findById(id);
        }
    }

    @Nested
    @DisplayName("Create artwork")
    class CreateArtwork {
        
        @Test
        @DisplayName("While artwork is valid should create artwork")
        void whileArtworkIsValid_ShouldCreateArtwork() {
            ArtworkDto artworkDto = new ArtworkDto(1L, "name", "description", "image", new BigDecimal(1.0), new CategoryDto(1L, "category"), new UserDto(1L, "name", "email", "password", "nAccount", "description", "address", "imageProfileUrl", UserRole.ADMIN));

            when(artworkRepository.findById(artworkDto.getId())).thenReturn(Optional.empty());
            when(artworkRepository.save(artworkDto)).thenReturn(artworkDto);

            ArtworkDto createArtworkDto = artworkService.insert(artworkDto);

            assertAll(
                () -> assertNotNull(createArtworkDto, "Create artwork should not be null"),
                () -> assertEquals(artworkDto.getId(), createArtworkDto.getId(), "Create artwork id should be equal to artwork id"),
                () -> assertEquals(artworkDto.getName(), createArtworkDto.getName(), "Create artwork name should be equal to artwork name"),
                () -> assertEquals(artworkDto.getDescription(), createArtworkDto.getDescription(), "Create artwork description should be equal to artwork description"),
                () -> assertEquals(artworkDto.getImage(), createArtworkDto.getImage(), "Create artwork image should be equal to artwork image"),
                () -> assertEquals(artworkDto.getPrice(), createArtworkDto.getPrice(), "Create artwork price should be equal to artwork price"),
                () -> assertEquals(artworkDto.getCategoryDto().getId(), createArtworkDto.getCategoryDto().getId(), "Create artwork category id should be equal to artwork category id"),
                () -> assertEquals(artworkDto.getUserDto().getId(), createArtworkDto.getUserDto().getId(), "Create artwork user id should be equal to artwork user id")
            );

            Mockito.verify(artworkRepository).save(artworkDto);
        }

        @Test
        @DisplayName("While artwork exists should throw BusinessException")
        void whileArtworkExists_ShouldThrowBusinessException() {
            ArtworkDto existingArtworkDto = new ArtworkDto(1L, "name", "description", "image", new BigDecimal(1.0), new CategoryDto(1L, "category"), new UserDto(1L, "name", "email", "password", "nAccount", "description", "address", "imageProfileUrl", UserRole.ADMIN));

            when(artworkRepository.findById(existingArtworkDto.getId())).thenReturn(Optional.of(existingArtworkDto));

            assertThrows(BusinessException.class, () -> artworkService.insert(existingArtworkDto));

            Mockito.verify(artworkRepository).findById(existingArtworkDto.getId());
            Mockito.verify(artworkRepository, never()).save(existingArtworkDto);
        }
    }

    @Nested
    @DisplayName("Update artwork")
    class UpdateArtwork {
        
        @Test
        @DisplayName("While artwork exists should update artwork")
        void whileArtworkExists_ShouldUpdateArtwork() {
            ArtworkDto artworkDto = new ArtworkDto(1L, "name", "description", "image", new BigDecimal(1.0), new CategoryDto(1L, "category"), new UserDto(1L, "name", "email", "password", "nAccount", "description", "address", "imageProfileUrl", UserRole.ADMIN));
            ArtworkDto updatedArtworkDto = new ArtworkDto(1L, "updatedName", "updatedDescription", "image", new BigDecimal(1.0), new CategoryDto(1L, "category"), new UserDto(1L, "name", "email", "password", "nAccount", "description", "address", "imageProfileUrl", UserRole.ADMIN));

            when(artworkRepository.findById(artworkDto.getId())).thenReturn(Optional.of(artworkDto));
            when(artworkRepository.save(updatedArtworkDto)).thenReturn(updatedArtworkDto);

            ArtworkDto result = artworkService.update(updatedArtworkDto);

            assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(artworkDto.getId(), result.getId()),
                () -> assertEquals("updatedName", result.getName()),
                () -> assertEquals("updatedDescription", result.getDescription())
            );

            Mockito.verify(artworkRepository).save(updatedArtworkDto);
        }

        @Test
        @DisplayName("While artwork doesn't exist should throw BusinessException")
        void whileArtworkDoesntExist_ShouldThrowBusinessException() {
            ArtworkDto artworkDto = new ArtworkDto(1L, "name", "description", "image", new BigDecimal(1.0), new CategoryDto(1L, "category"), new UserDto(1L, "name", "email", "password", "nAccount", "description", "address", "imageProfileUrl", UserRole.ADMIN));

            when(artworkRepository.findById(artworkDto.getId())).thenReturn(Optional.empty());

            assertThrows(BusinessException.class, () -> artworkService.update(artworkDto));

            Mockito.verify(artworkRepository).findById(artworkDto.getId());
            Mockito.verify(artworkRepository, never()).save(artworkDto);
        }
    }

    @Nested
    @DisplayName("Delete artwork")
    class DeleteArtwork {
        
        @Test
        @DisplayName("While artwork exists should delete artwork")
        void whileArtworkExists_ShouldDeleteArtwork() {
            Long id = 1L;
            
            ArtworkDto artworkDto = new ArtworkDto(id, "name", "description", "image", new BigDecimal(1.0), new CategoryDto(1L, "category"), new UserDto(1L, "name", "email", "password", "nAccount", "description", "address", "imageProfileUrl", UserRole.ADMIN));
            
            when(artworkRepository.findById(id)).thenReturn(Optional.of(artworkDto));
            
            assertAll(
                () -> assertNotNull(artworkDto),
                () -> assertEquals(artworkDto.getId(), artworkDto.getId()),
                () -> assertDoesNotThrow(() -> artworkService.delete(artworkDto.getId()))
            );
            
            Mockito.verify(artworkRepository).delete(artworkDto.getId());
        }

        @Test
        @DisplayName("While artwork doesn't exist should throw BusinessException")
        void whileArtworkDoesntExist_ShouldThrowBusinessException() {
            
            Long id = 1L;
            
            when(artworkRepository.findById(id)).thenReturn(Optional.empty());
            
            assertThrows(BusinessException.class, () -> artworkService.delete(id));

            Mockito.verify(artworkRepository).findById(id);
            Mockito.verify(artworkRepository, never()).delete(id);
        }
    }

}
