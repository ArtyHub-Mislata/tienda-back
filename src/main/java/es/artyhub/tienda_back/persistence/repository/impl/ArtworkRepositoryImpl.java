package es.artyhub.tienda_back.persistence.repository.impl;

import es.artyhub.tienda_back.domain.dto.ArtworkDto;
import es.artyhub.tienda_back.domain.model.Page;
import es.artyhub.tienda_back.domain.repository.ArtworkRepository;

import java.util.Optional;

public class ArtworkRepositoryImpl implements ArtworkRepository {
    @Override
    public Page<ArtworkDto> findAll(int pageNumber, int pageSize) {
        return null;
    }

    @Override
    public Optional<ArtworkDto> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public ArtworkDto save(ArtworkDto artwork) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
