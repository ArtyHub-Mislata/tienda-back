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
import es.artyhub.tienda_back.domain.exception.BusinessException;
import es.artyhub.tienda_back.domain.exception.ValidationException;
import es.artyhub.tienda_back.domain.repository.ArtworkRepository;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.when;
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
                new ArtworkDto(1L, "name1", "description1", "image1", 1.0, 1, new CategoryDto(1L, "category1")),
                new ArtworkDto(2L, "name2", "description2", "image2", 2.0, 2, new CategoryDto(2L, "category2")),
                new ArtworkDto(3L, "name3", "description3", "image3", 3.0, 3, new CategoryDto(3L, "category3")),
                new ArtworkDto(4L, "name4", "description4", "image4", 4.0, 4, new CategoryDto(4L, "category4")),
                new ArtworkDto(5L, "name5", "description5", "image5", 5.0, 5, new CategoryDto(5L, "category5"))
            );

            when(artworkRepository.findAll(page, size)).thenReturn(new Page<>(artworks, page, size, artworks.size()));

            Page<ArtworkDto> result = artworkService.findAll(page, size);

            assertAll(
                () -> assertNotNull(result, "Result should not be null"),
                () -> assertEquals(artworks.size(), result.data().size(), "Result size should be equal to artworks size"),
                () -> assertEquals(artworks.getFirst().id(), result.data().getFirst().id(), "Result first id should be equal to artworks first id"),
                () -> assertEquals(artworks.get(2).id(), result.data().get(2).id(), "Result second id should be equal to artworks second id"),
                () -> assertEquals(artworks.get(3).id(), result.data().get(3).id(), "Result third id should be equal to artworks third id"),
                () -> assertEquals(artworks.get(4).id(), result.data().get(4).id(), "Result fourth id should be equal to artworks fourth id"),
                () -> assertEquals(artworks.getLast().id(), result.data().getLast().id(), "Result last id should be equal to artworks last id")
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
                () -> assertEquals(artworks.data().size(), result.data().size(), "Result size should be equal to artworks size"),
                () -> assertEquals(artworks.data().getFirst().id(), result.data().getFirst().id(), "Result first id should be equal to artworks first id"),
                () -> assertEquals(artworks.data().get(2).id(), result.data().get(2).id(), "Result second id should be equal to artworks second id"),
                () -> assertEquals(artworks.data().get(3).id(), result.data().get(3).id(), "Result third id should be equal to artworks third id"),
                () -> assertEquals(artworks.data().get(4).id(), result.data().get(4).id(), "Result fourth id should be equal to artworks fourth id"),
                () -> assertEquals(artworks.data().getLast().id(), result.data().getLast().id(), "Result last id should be equal to artworks last id")
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

            when(artworkRepository.findById(id)).thenReturn(Optional.of(new ArtworkDto(id, "name", "description", "image", 1.0, 1, new CategoryDto(1L, "category"))));

            Optional<ArtworkDto> result = artworkService.findById(id);

            assertAll(
                () -> assertNotNull(result, "Result should not be null"),
                () -> assertEquals(id, result.get().id(), "Result id should be equal to artwork id"),
                () -> assertEquals("name", result.get().name(), "Result name should be equal to artwork name"),
                () -> assertEquals("description", result.get().description(), "Result description should be equal to artwork description"),
                () -> assertEquals("image", result.get().image(), "Result image should be equal to artwork image"),
                () -> assertEquals(1.0, result.get().price(), "Result price should be equal to artwork price"),
                () -> assertEquals(1, result.get().stock(), "Result stock should be equal to artwork stock"),
                () -> assertEquals(1L, result.get().categoryDto().id(), "Result category id should be equal to artwork category id")
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
            ArtworkDto artworkDto = new ArtworkDto(1L, "name", "description", "image", 1.0, 1, new CategoryDto(1L, "category"));

            when(artworkRepository.findById(artworkDto.id())).thenReturn(Optional.empty());
            when(artworkRepository.save(artworkDto)).thenReturn(artworkDto);

            ArtworkDto createArtworkDto = artworkService.insert(artworkDto);

            assertAll(
                () -> assertNotNull(createArtworkDto, "Create artwork should not be null"),
                () -> assertEquals(artworkDto.id(), createArtworkDto.id(), "Create artwork id should be equal to artwork id"),
                () -> assertEquals(artworkDto.name(), createArtworkDto.name(), "Create artwork name should be equal to artwork name"),
                () -> assertEquals(artworkDto.description(), createArtworkDto.description(), "Create artwork description should be equal to artwork description"),
                () -> assertEquals(artworkDto.image(), createArtworkDto.image(), "Create artwork image should be equal to artwork image"),
                () -> assertEquals(artworkDto.price(), createArtworkDto.price(), "Create artwork price should be equal to artwork price"),
                () -> assertEquals(artworkDto.stock(), createArtworkDto.stock(), "Create artwork stock should be equal to artwork stock"),
                () -> assertEquals(artworkDto.categoryDto().id(), createArtworkDto.categoryDto().id(), "Create artwork category id should be equal to artwork category id")
            );

            Mockito.verify(artworkRepository).save(artworkDto);
        }

        @Test
        @DisplayName("While artwork exists should throw BusinessException")
        void whileArtworkExists_ShouldThrowBusinessException() {
            ArtworkDto existingArtworkDto = new ArtworkDto(1L, "name", "description", "image", 1.0, 1, new CategoryDto(1L, "category"));

            when(artworkRepository.findById(existingArtworkDto.id())).thenReturn(Optional.of(existingArtworkDto));

            assertThrows(BusinessException.class, () -> artworkService.insert(existingArtworkDto));

            Mockito.verify(artworkRepository).findById(existingArtworkDto.id());
            Mockito.verify(artworkRepository, never()).save(existingArtworkDto);
        }

        @Test
        @DisplayName("While artwork have no valid name should throw ValidationException")
        void whileArtworkHaveNoValidName_ShouldThrowValidationException() {
            ArtworkDto artworkDto = new ArtworkDto(1L, "", "description", "image", 1.0, 1, new CategoryDto(1L, "category"));

            assertThrows(ValidationException.class, () -> artworkService.insert(artworkDto));

            Mockito.verify(artworkRepository, never()).save(artworkDto);
        }

        @Test
        @DisplayName("While artwork have null description should throw ValidationException")
        void whileArtworkHaveNullDescription_ShouldThrowValidationException() {
            ArtworkDto artworkDto = new ArtworkDto(1L, "name", null, "image", 1.0, 1, new CategoryDto(1L, "category"));

            assertThrows(ValidationException.class, () -> artworkService.insert(artworkDto));

            Mockito.verify(artworkRepository, never()).save(artworkDto);
        }

        @Test
        @DisplayName("While artwork have no valid image should throw ValidationException")
        void whileArtworkHaveNoValidImage_ShouldThrowValidationException() {
            ArtworkDto artworkDto = new ArtworkDto(1L, "name", "description", "", 1.0, 1, new CategoryDto(1L, "category"));

            assertThrows(ValidationException.class, () -> artworkService.insert(artworkDto));

            Mockito.verify(artworkRepository, never()).save(artworkDto);
        }

        @Test
        @DisplayName("While artwork have null price should throw ValidationException")
        void whileArtworkHaveNullPrice_ShouldThrowValidationException() {
            ArtworkDto artworkDto = new ArtworkDto(1L, "name", "description", "image", null, 1, new CategoryDto(1L, "category"));

            assertThrows(ValidationException.class, () -> artworkService.insert(artworkDto));

            Mockito.verify(artworkRepository, never()).save(artworkDto);
        }

        @Test
        @DisplayName("While artwork have negative price should throw ValidationException")
        void whileArtworkHaveNegativePrice_ShouldThrowValidationException() {
            ArtworkDto artworkDto = new ArtworkDto(1L, "name", "description", "image", -1.0, 1, new CategoryDto(1L, "category"));

            assertThrows(ValidationException.class, () -> artworkService.insert(artworkDto));

            Mockito.verify(artworkRepository, never()).save(artworkDto);
        }

        @Test
        @DisplayName("While artwork have null stock should throw ValidationException")
        void whileArtworkHaveNullStock_ShouldThrowValidationException() {
            ArtworkDto artworkDto = new ArtworkDto(1L, "name", "description", "image", 1.0, null, new CategoryDto(1L, "category"));

            assertThrows(ValidationException.class, () -> artworkService.insert(artworkDto));

            Mockito.verify(artworkRepository, never()).save(artworkDto);
        }

        @Test
        @DisplayName("While artwork have negative stock should throw ValidationException")
        void whileArtworkHaveNegativeStock_ShouldThrowValidationException() {
            ArtworkDto artworkDto = new ArtworkDto(1L, "name", "description", "image", 1.0, -1, new CategoryDto(1L, "category"));

            assertThrows(ValidationException.class, () -> artworkService.insert(artworkDto));

            Mockito.verify(artworkRepository, never()).save(artworkDto);
        }

        @Test
        @DisplayName("While artwork have no valid category should throw ValidationException")
        void whileArtworkHaveNoValidCategory_ShouldThrowValidationException() {
            ArtworkDto artworkDto = new ArtworkDto(1L, "name", "description", "image", 1.0, 1, null);

            assertThrows(ValidationException.class, () -> artworkService.insert(artworkDto));

            Mockito.verify(artworkRepository, never()).save(artworkDto);
        }
    }

    @Nested
    @DisplayName("Update artwork")
    class UpdateArtwork {
        
        @Test
        @DisplayName("While artwork exists should update artwork")
        void whileArtworkExists_ShouldUpdateArtwork() {
            ArtworkDto artworkDto = new ArtworkDto(1L, "name", "description", "image", 1.0, 1, new CategoryDto(1L, "category"));
            ArtworkDto updatedArtworkDto = new ArtworkDto(1L, "updatedName", "updatedDescription", "image", 1.0, 1, new CategoryDto(1L, "category"));

            when(artworkRepository.findById(artworkDto.id())).thenReturn(Optional.of(artworkDto));
            when(artworkRepository.save(updatedArtworkDto)).thenReturn(updatedArtworkDto);

            ArtworkDto result = artworkService.update(updatedArtworkDto);

            assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(artworkDto.id(), result.id()),
                () -> assertEquals("updatedName", result.name()),
                () -> assertEquals("updatedDescription", result.description())
            );

            Mockito.verify(artworkRepository).save(updatedArtworkDto);
        }

        @Test
        @DisplayName("While artwork doesn't exist should throw BusinessException")
        void whileArtworkDoesntExist_ShouldThrowBusinessException() {
            ArtworkDto artworkDto = new ArtworkDto(1L, "name", "description", "image", 1.0, 1, new CategoryDto(1L, "category"));

            when(artworkRepository.findById(artworkDto.id())).thenReturn(Optional.empty());

            assertThrows(BusinessException.class, () -> artworkService.update(artworkDto));

            Mockito.verify(artworkRepository).findById(artworkDto.id());
            Mockito.verify(artworkRepository, never()).save(artworkDto);
        }
    }

    @Nested
    @DisplayName("Delete artwork")
    class DeleteArtwork {
        
        @Test
        @DisplayName("While artwork exists should delete artwork")
        void whileArtworkExists_ShouldDeleteArtwork() {
            
            ArtworkDto artworkDto = new ArtworkDto(1L, "name", "description", "image", 1.0, 1, new CategoryDto(1L, "category"));
            
            when(artworkRepository.findById(artworkDto.id())).thenReturn(Optional.of(artworkDto));
            
            assertAll(
                () -> assertNotNull(artworkDto),
                () -> assertEquals(artworkDto.id(), artworkDto.id()),
                () -> assertDoesNotThrow(() -> artworkService.delete(artworkDto.id()))
            );
            
            Mockito.verify(artworkRepository).delete(artworkDto.id());
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
