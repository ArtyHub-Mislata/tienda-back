package es.artyhub.tienda_back.domain.mapper;

import es.artyhub.tienda_back.domain.dto.ArtworkDto;
import es.artyhub.tienda_back.domain.exception.BusinessException;
import es.artyhub.tienda_back.domain.model.Artwork;

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

    public ArtworkDto fromArtworkToArtworkDto(Artwork artwork) {
        if (artwork == null) {
            throw new BusinessException("Artwork cannot be null");
        }
        return new ArtworkDto(
                artwork.getId(),
                artwork.getName(),
                artwork.getDescription(),
                artwork.getImageUrl(),
                artwork.getPrice(),
                CategoryMapper.getInstance().fromCategoryToCategoryDto(artwork.getCategory()),
                UserMapper.getInstance().fromUserToUserDto(artwork.getUser())
        );
    }

    public Artwork fromArtworkDtoToArtwork(ArtworkDto artworkDto) {
        if (artworkDto == null) {
            throw new BusinessException("ArtworkDto cannot be null");
        }
        return new Artwork(
                artworkDto.getId(),
                artworkDto.getName(),
                artworkDto.getDescription(),
                artworkDto.getImage(),
                artworkDto.getPrice(),
                CategoryMapper.getInstance().fromCategoryDtoToCategory(artworkDto.getCategoryDto()),
                UserMapper.getInstance().fromUserDtoToUser(artworkDto.getUserDto())
        );
    }
}
