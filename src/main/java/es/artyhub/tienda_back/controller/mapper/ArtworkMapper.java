package es.artyhub.tienda_back.controller.mapper;

import es.artyhub.tienda_back.controller.webmodel.response.ArtworkDetailResponse;
import es.artyhub.tienda_back.controller.webmodel.response.ArtworkSummaryResponse;
import es.artyhub.tienda_back.domain.dto.ArtworkDto;

public class ArtworkMapper {

    public static ArtworkSummaryResponse fromArtworkDtoToArtworkSummaryResponse(ArtworkDto artworkDto) {
        if (artworkDto == null) {
            return null;
        }
        return new ArtworkSummaryResponse(
            artworkDto.id(),
            artworkDto.name(),
            artworkDto.price()
        );
    }

    public static ArtworkDetailResponse fromArtworkDtoToArtworkDetailResponse(ArtworkDto artworkDto) {
        if (artworkDto == null) {
            return null;
        }
        return new ArtworkDetailResponse(
            artworkDto.id(),
            artworkDto.name(),
            artworkDto.description(),
            artworkDto.image(),
            artworkDto.price(),
            artworkDto.stock(),
            CategoryMapper.fromCategoryDtoToCategoryDetailResponse(artworkDto.categoryDto())
        );
    }
}
