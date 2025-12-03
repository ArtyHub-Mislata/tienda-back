package es.artyhub.tienda_back.domain.repository;

import es.artyhub.tienda_back.domain.dto.ArtworkDto;
import es.artyhub.tienda_back.domain.model.Page;
import java.util.Optional;

public interface ArtworkRepository {
    Page<ArtworkDto> findAll(int pageNumber, int pageSize);

    Optional<ArtworkDto> findById(Long id);

    ArtworkDto save(ArtworkDto artwork);

    void delete(Long id);
}
