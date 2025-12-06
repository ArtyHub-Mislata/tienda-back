package es.artyhub.tienda_back.persistence.repository.mapper;

import es.artyhub.tienda_back.domain.dto.ArtworkDto;
import es.artyhub.tienda_back.persistence.dao.jpa.entity.ArtworkJpaEntity;

public class ArtworkMapper {
    private static ArtworkMapper instance;

    private ArtworkMapper() {
    }

    public static ArtworkMapper getInstance() {
        if (instance == null) {
            instance = new ArtworkMapper();
        }
        return instance;
    }

    public ArtworkDto fromArtworkJpaEntityToArtworkDto(ArtworkJpaEntity artworkJpaEntity) {
        if (artworkJpaEntity == null) {
            return null;
        }

        return new ArtworkDto(
                artworkJpaEntity.getId(),
                artworkJpaEntity.getName(),
                artworkJpaEntity.getDescription(),
                artworkJpaEntity.getImageUrl(),
                artworkJpaEntity.getPrice(),
                artworkJpaEntity.getStock(),
                CategoryMapper
                        .getInstance()
                        .fromCategoryJpaEntityToCategoryDto(artworkJpaEntity.getCategory())
        );
    }

    public ArtworkJpaEntity fromArtworkDtoToArtworkJpaEntity(ArtworkDto artworkDto) {
        if (artworkDto == null) {
            return null;
        }
        return new ArtworkJpaEntity(
                artworkDto.getId(),
                artworkDto.getName(),
                artworkDto.getDescription(),
                artworkDto.getImage(),
                artworkDto.getPrice(),
                artworkDto.getStock(),
                CategoryMapper
                        .getInstance()
                        .fromCategoryDtoToCategoryJpaEntity(artworkDto.getCategoryDto())

        );
    }
}
