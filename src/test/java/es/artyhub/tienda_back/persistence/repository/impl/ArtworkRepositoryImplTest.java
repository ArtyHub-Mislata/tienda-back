package es.artyhub.tienda_back.persistence.repository.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import es.artyhub.tienda_back.domain.dto.ArtworkDto;
import es.artyhub.tienda_back.domain.dto.CategoryDto;
import es.artyhub.tienda_back.domain.dto.UserDto;
import es.artyhub.tienda_back.domain.enums.UserRole;
import es.artyhub.tienda_back.persistence.repository.mapper.ArtworkMapper;
import es.artyhub.tienda_back.domain.model.Page;
import es.artyhub.tienda_back.persistence.dao.jpa.ArtworkJpaDao;
import es.artyhub.tienda_back.persistence.dao.jpa.entity.ArtworkJpaEntity;
import es.artyhub.tienda_back.persistence.dao.jpa.entity.CategoryJpaEntity;
import es.artyhub.tienda_back.persistence.dao.jpa.entity.UserJpaEntity;

import org.mockito.InjectMocks;
import org.mockito.Mock;

@ExtendWith(MockitoExtension.class)
public class ArtworkRepositoryImplTest {
    
    @Mock
    private ArtworkJpaDao artworkJpaDao;

    @InjectMocks
    private ArtworkRepositoryImpl artworkRepository;

    @Nested
    @DisplayName("findAll")
    class FindAllTest {
        
        @Test
        @DisplayName("Should return a page of artworks")
        void shouldReturnPageOfArtworks() {
            int page = 1;
            int size = 10;

            ArtworkJpaEntity artworkJpaEntity = new ArtworkJpaEntity();
            List<ArtworkJpaEntity> artworkJpaEntityList = List.of(artworkJpaEntity);
            List<ArtworkDto> artworkDtoList = artworkJpaEntityList.stream().map(ArtworkMapper.getInstance()::fromArtworkJpaEntityToArtworkDto).toList();

            when(artworkJpaDao.findAll(page, size)).thenReturn(artworkJpaEntityList);
            when(artworkJpaDao.count()).thenReturn((long) artworkJpaEntityList.size());

            Page<ArtworkDto> artworkDtoPage = new Page<>(artworkDtoList, page, size, artworkJpaEntityList.size(), 1);

            Page<ArtworkDto> result = artworkRepository.findAll(page, size);

            assertEquals(artworkDtoPage.pageNumber(), result.pageNumber());
            assertEquals(artworkDtoPage.pageSize(), result.pageSize());
            assertEquals(artworkDtoPage.totalElements(), result.totalElements());
            assertEquals(artworkDtoPage.totalPages(), result.totalPages());
        }
    }

    @Nested
    @DisplayName("findAllArtworksByCategory")
    class FindAllArtworksByCategoryTest {
        
        @Test
        @DisplayName("Should return a page of artworks")
        void shouldReturnPageOfArtworks() {
            int page = 1;
            int size = 10;
            Long categoryId = 1L;

            CategoryJpaEntity categoryJpaEntity = new CategoryJpaEntity(categoryId, "Category");

            UserJpaEntity userJpaEntity = new UserJpaEntity(1L, "User", "Email", "Password", "description", "address", "image", UserRole.ADMIN);

            ArtworkJpaEntity artworkJpaEntity = new ArtworkJpaEntity(1L, "Artwork", "Description", "image", new BigDecimal(10), categoryJpaEntity, userJpaEntity, 10L);
            List<ArtworkJpaEntity> artworkJpaEntityList = List.of(artworkJpaEntity);
            List<ArtworkDto> artworkDtoList = artworkJpaEntityList.stream().map(ArtworkMapper.getInstance()::fromArtworkJpaEntityToArtworkDto).toList();

            when(artworkJpaDao.findAllArtworksByCategory(categoryId, page, size)).thenReturn(artworkJpaEntityList);
            when(artworkJpaDao.count()).thenReturn((long) artworkJpaEntityList.size());

            Page<ArtworkDto> artworkDtoPage = new Page<>(artworkDtoList, page, size, artworkJpaEntityList.size());

            Page<ArtworkDto> result = artworkRepository.findAllArtworksByCategory(page, size, categoryId);

            assertEquals(artworkDtoPage.pageNumber(), result.pageNumber());
            assertEquals(artworkDtoPage.pageSize(), result.pageSize());
            assertEquals(artworkDtoPage.totalElements(), result.totalElements());
            assertEquals(artworkDtoPage.totalPages(), result.totalPages());
        }
    }

    @Nested
    @DisplayName("findAllArtworksOfUser")
    class FindAllArtworksOfUserTest {
        
        @Test
        @DisplayName("Should return a page of artworks")
        void shouldReturnPageOfArtworks() {
            int page = 1;
            int size = 10;
            Long userId = 1L;

            CategoryJpaEntity categoryJpaEntity = new CategoryJpaEntity(1L, "Category");

            UserJpaEntity userJpaEntity = new UserJpaEntity(userId, "User", "Email", "Password", "description", "address", "image", UserRole.ADMIN);

            ArtworkJpaEntity artworkJpaEntity = new ArtworkJpaEntity(1L, "Artwork", "Description", "image", new BigDecimal(10), categoryJpaEntity, userJpaEntity, 10L);
            List<ArtworkJpaEntity> artworkJpaEntityList = List.of(artworkJpaEntity);
            List<ArtworkDto> artworkDtoList = artworkJpaEntityList.stream().map(ArtworkMapper.getInstance()::fromArtworkJpaEntityToArtworkDto).toList();

            when(artworkJpaDao.findAllArtworksOfUser(userId, page, size)).thenReturn(artworkJpaEntityList);
            when(artworkJpaDao.count()).thenReturn((long) artworkJpaEntityList.size());

            Page<ArtworkDto> artworkDtoPage = new Page<>(artworkDtoList, page, size, artworkJpaEntityList.size());

            Page<ArtworkDto> result = artworkRepository.findAllArtworksOfUser(userId, page, size);

            assertEquals(artworkDtoPage.pageNumber(), result.pageNumber());
            assertEquals(artworkDtoPage.pageSize(), result.pageSize());
            assertEquals(artworkDtoPage.totalElements(), result.totalElements());
            assertEquals(artworkDtoPage.totalPages(), result.totalPages());
        }
    }

    @Nested
    @DisplayName("findById")
    class FindByIdTest {
        
        @Test
        @DisplayName("Should return an artwork")
        void shouldReturnArtwork() {
            Long artworkId = 1L;

            CategoryJpaEntity categoryJpaEntity = new CategoryJpaEntity(1L, "Category");

            UserJpaEntity userJpaEntity = new UserJpaEntity(1L, "User", "Email", "Password", "description", "address", "image", UserRole.ADMIN);

            ArtworkJpaEntity artworkJpaEntity = new ArtworkJpaEntity(artworkId, "Artwork", "Description", "image", new BigDecimal(10), categoryJpaEntity, userJpaEntity, 10L);

            when(artworkJpaDao.findById(artworkId)).thenReturn(Optional.of(artworkJpaEntity));

            ArtworkDto artworkDto = ArtworkMapper.getInstance().fromArtworkJpaEntityToArtworkDto(artworkJpaEntity);

            Optional<ArtworkDto> result = artworkRepository.findById(artworkId);

            assertEquals(artworkDto.getCategoryDto().getId(), result.get().getCategoryDto().getId());
            assertEquals(artworkDto.getUserDto().getId(), result.get().getUserDto().getId());
            assertEquals(artworkDto.getName(), result.get().getName());
            assertEquals(artworkDto.getDescription(), result.get().getDescription());
            assertEquals(artworkDto.getImage(), result.get().getImage());
            assertEquals(artworkDto.getPrice(), result.get().getPrice());
            assertEquals(artworkDto.getStock(), result.get().getStock());
        }
    }

    @Nested
    @DisplayName("save")
    class SaveTest {
        
        @Test
        @DisplayName("Should insert an artwork if id is null")
        void shouldInsertArtwork() {
            CategoryDto categoryDto = new CategoryDto(1L, "Category");
            UserDto userDto = new UserDto(1L, "User", "Email", "Password", "description", "address", "image", UserRole.ADMIN);
            
            ArtworkDto artworkDto = new ArtworkDto(null, "Artwork", "Description", "image", new BigDecimal(10), categoryDto, userDto, 10L);

            ArtworkJpaEntity artworkJpaEntity = ArtworkMapper.getInstance().fromArtworkDtoToArtworkJpaEntity(artworkDto);

            when(artworkJpaDao.insert(artworkJpaEntity)).thenReturn(artworkJpaEntity);

            ArtworkDto result = artworkRepository.save(artworkDto);

            assertEquals(artworkDto.getCategoryDto().getId(), result.getCategoryDto().getId());
            assertEquals(artworkDto.getUserDto().getId(), result.getUserDto().getId());
            assertEquals(artworkDto.getName(), result.getName());
            assertEquals(artworkDto.getDescription(), result.getDescription());
            assertEquals(artworkDto.getImage(), result.getImage());
            assertEquals(artworkDto.getPrice(), result.getPrice());
            assertEquals(artworkDto.getStock(), result.getStock());
        }

        @Test
        @DisplayName("Should update an artwork if id is not null")
        void shouldUpdateArtwork() {
            CategoryDto categoryDto = new CategoryDto(1L, "Category");
            UserDto userDto = new UserDto(1L, "User", "Email", "Password", "description", "address", "image", UserRole.ADMIN);
            
            ArtworkDto artworkDto = new ArtworkDto(1L, "Artwork", "Description", "image", new BigDecimal(10), categoryDto, userDto, 10L);

            ArtworkJpaEntity artworkJpaEntity = ArtworkMapper.getInstance().fromArtworkDtoToArtworkJpaEntity(artworkDto);

            when(artworkJpaDao.update(artworkJpaEntity)).thenReturn(artworkJpaEntity);

            ArtworkDto result = artworkRepository.save(artworkDto);

            assertEquals(artworkDto.getCategoryDto().getId(), result.getCategoryDto().getId());
            assertEquals(artworkDto.getUserDto().getId(), result.getUserDto().getId());
            assertEquals(artworkDto.getName(), result.getName());
            assertEquals(artworkDto.getDescription(), result.getDescription());
            assertEquals(artworkDto.getImage(), result.getImage());
            assertEquals(artworkDto.getPrice(), result.getPrice());
            assertEquals(artworkDto.getStock(), result.getStock());
        }
    }

    @Nested
    @DisplayName("delete")
    class DeleteTest {
        
        @Test
        @DisplayName("Should delete an artwork")
        void shouldDeleteArtwork() {
            Long artworkId = 1L;

            artworkRepository.delete(artworkId);

            verify(artworkJpaDao).deleteById(artworkId);
        }
    }
}
