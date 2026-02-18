package es.artyhub.tienda_back.domain.service;

import es.artyhub.tienda_back.domain.dto.ArtworkDto;
import es.artyhub.tienda_back.domain.model.Page;

public interface ArtworkService {
    
    Page<ArtworkDto> findAll(int pageNumber, int pageSize);

    Page<ArtworkDto> findAllArtworksByCategoryId(int pageNumber, int pageSize, Long id);

    ArtworkDto findById(Long id);

    ArtworkDto insert(ArtworkDto artworkDto);

    ArtworkDto update(ArtworkDto artworkDto);

    void delete(Long id);
}
