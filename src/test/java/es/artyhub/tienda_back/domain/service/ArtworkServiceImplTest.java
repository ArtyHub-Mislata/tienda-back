package es.artyhub.tienda_back.domain.service;

import org.mockito.junit.jupiter.MockitoExtension;

import es.artyhub.tienda_back.domain.dto.ArtworkDto;
import es.artyhub.tienda_back.domain.dto.CategoryDto;
import es.artyhub.tienda_back.domain.dto.UserDto;
import es.artyhub.tienda_back.domain.exception.BusinessException;
import es.artyhub.tienda_back.domain.model.Page;
import es.artyhub.tienda_back.domain.repository.ArtworkRepository;
import es.artyhub.tienda_back.domain.service.impl.ArtworkServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

@ExtendWith(MockitoExtension.class)
public class ArtworkServiceImplTest {

    @Mock
    private ArtworkRepository artworkRepository;
    
    @InjectMocks
    private ArtworkServiceImpl artworkService;

    @Nested
    @DisplayName("findAll")
    public class FindAll {
        @Test
        @DisplayName("should return page of artworks when getAll is called with valid arguments")
        public void findAll() {
            int pageNumber = 1;
            int pageSize = 10;

            ArtworkDto artworkDto = new ArtworkDto();
            List<ArtworkDto> artworks = List.of(artworkDto);
            Page<ArtworkDto> page = new Page<>(artworks, pageNumber, pageSize, artworks.size());

            when(artworkRepository.findAll(pageNumber, pageSize)).thenReturn(page);

            Page<ArtworkDto> result = artworkService.findAll(pageNumber, pageSize);

            assertNotNull(result);
            assertEquals(page, result);
        }
    }

    @Nested
    @DisplayName("findAllArtworksByCategoryId")
    public class FindAllArtworksByCategoryId {
        @Test
        @DisplayName("should return page of artworks when getAllArtworksByCategoryId is called with valid arguments")
        public void findAllArtworksByCategoryId() {
            int pageNumber = 1;
            int pageSize = 10;
            Long categoryId = 1L;

            ArtworkDto artworkDto = new ArtworkDto(1L, "Name", "Description", "Image", new BigDecimal(10.0), new CategoryDto(categoryId, "Name"), new UserDto(), 10L);
            List<ArtworkDto> artworks = List.of(artworkDto);
            Page<ArtworkDto> page = new Page<>(artworks, pageNumber, pageSize, artworks.size());

            when(artworkRepository.findAllArtworksByCategory(pageNumber, pageSize, categoryId)).thenReturn(page);

            Page<ArtworkDto> result = artworkService.findAllArtworksByCategoryId(pageNumber, pageSize, categoryId);

            assertNotNull(result);
            assertEquals(page, result);
        }
    }

    @Nested
    @DisplayName("findById")
    public class FindById {
        @Test
        @DisplayName("should return artwork when artwork exists")
        public void findById_WhenArtworkExists() {
            Long artworkId = 1L;

            ArtworkDto artworkDto = new ArtworkDto();
            artworkDto.setId(artworkId);

            when(artworkRepository.findById(artworkId)).thenReturn(Optional.of(artworkDto));

            ArtworkDto result = artworkService.findById(artworkId);

            assertNotNull(result);
            assertEquals(artworkDto, result);
        }

        @Test
        @DisplayName("should throw BusinessException when artwork does not exist")
        public void findById_WhenArtworkDoesNotExist() {
            Long artworkId = 1L;

            when(artworkRepository.findById(artworkId)).thenReturn(Optional.empty());

            assertThrows(BusinessException.class, () -> artworkService.findById(artworkId));
        }
    }

    @Nested
    @DisplayName("insert")
    public class Insert {
        @Test
        @DisplayName("should return created artwork when insert is called with valid arguments")
        public void insert() {
            ArtworkDto artworkDto = new ArtworkDto(1L, "Name", "Description", "Image", new BigDecimal(10.0), new CategoryDto(1L, "Name"), new UserDto(), 10L);

            when(artworkRepository.save(artworkDto)).thenReturn(artworkDto);

            ArtworkDto result = artworkService.insert(artworkDto);

            assertNotNull(result);
            assertEquals(artworkDto, result);
        }
    }

    @Nested
    @DisplayName("update")
    public class Update {
        @Test
        @DisplayName("should return updated artwork when update is called with valid arguments")
        public void update_WhenArtworkExists() {
            ArtworkDto artworkDto = new ArtworkDto(1L, "Name", "Description", "Image", new BigDecimal(10.0), new CategoryDto(1L, "Name"), new UserDto(), 10L);

            when(artworkRepository.findById(artworkDto.getId())).thenReturn(Optional.of(artworkDto));
            when(artworkRepository.save(artworkDto)).thenReturn(artworkDto);

            ArtworkDto result = artworkService.update(artworkDto);

            assertNotNull(result);
            assertEquals(artworkDto, result);
        }

        @Test
        @DisplayName("should throw BusinessException when artwork does not exist")
        public void update_WhenArtworkDoesNotExist() {
            ArtworkDto artworkDto = new ArtworkDto(1L, "Name", "Description", "Image", new BigDecimal(10.0), new CategoryDto(1L, "Name"), new UserDto(), 10L);

            when(artworkRepository.findById(artworkDto.getId())).thenReturn(Optional.empty());

            assertThrows(BusinessException.class, () -> artworkService.update(artworkDto));
        }
    }

    @Nested
    @DisplayName("delete")
    public class Delete {
        @Test
        @DisplayName("should delete artwork when delete is called with valid arguments")
        public void delete_WhenArtworkExists() {
            Long artworkId = 1L;
            ArtworkDto artworkDto = new ArtworkDto(artworkId, "Name", "Description", "Image", new BigDecimal(10.0), new CategoryDto(1L, "Name"), new UserDto(), 10L);

            when(artworkRepository.findById(artworkId)).thenReturn(Optional.of(artworkDto));

            artworkService.delete(artworkId);

            verify(artworkRepository).delete(artworkId);
        }

        @Test
        @DisplayName("should throw BusinessException when artwork does not exist")
        public void delete_WhenArtworkDoesNotExist() {
            Long artworkId = 1L;

            when(artworkRepository.findById(artworkId)).thenReturn(Optional.empty());

            assertThrows(BusinessException.class, () -> artworkService.delete(artworkId));
        }
    }
}
