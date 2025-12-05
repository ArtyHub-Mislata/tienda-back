package es.artyhub.tienda_back.domain.mapper;

import es.artyhub.tienda_back.domain.dto.ArtworkDto;
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
            return null;
        }
        return new ArtworkDto(
                artwork.getId(),
                artwork.getName(),
                artwork.getDescription(),
                artwork.getImageUrl(),
                artwork.getPrice(),
                artwork.getStock(),
                artwork.getCategoryDto()
        );
    }

    public Artwork fromArtworkDtoToArtwork(ArtworkDto artworkDto) {
        if (artworkDto == null) {
            return null;
        }
        return new Artwork(
                artworkDto.getId(),
                artworkDto.getName(),
                artworkDto.getDescription(),
                artworkDto.getImage(),
                artworkDto.getPrice(),
                artworkDto.getStock(),
                artworkDto.getCategoryDto()
        );
    }
}
