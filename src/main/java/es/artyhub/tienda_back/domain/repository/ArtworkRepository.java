package es.artyhub.tienda_back.domain.repository;

import es.artyhub.tienda_back.domain.dto.ArtworkDto;
import es.artyhub.tienda_back.domain.model.Page;

public interface ArtworkRepository {
    Page<ArtworkDto> findAll(int pageNumber, int pageSize);

    ArtworkDto findById(Long id);

    ArtworkDto save(ArtworkDto artwork);

    void delete(Long id);
}
