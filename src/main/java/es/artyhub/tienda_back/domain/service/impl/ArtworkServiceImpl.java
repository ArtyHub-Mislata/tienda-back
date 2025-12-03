package es.artyhub.tienda_back.domain.service.impl;

import es.artyhub.tienda_back.domain.dto.ArtworkDto;
import es.artyhub.tienda_back.domain.model.Page;
import es.artyhub.tienda_back.domain.repository.ArtworkRepository;
import es.artyhub.tienda_back.domain.service.ArtworkService;

public class ArtworkServiceImpl implements ArtworkService {
    
    private final ArtworkRepository artworkRepository;

    public ArtworkServiceImpl(ArtworkRepository artworkRepository) {
        this.artworkRepository = artworkRepository;
    }

    @Override
    public Page<ArtworkDto> findAll(int pageNumber, int pageSize) {
        return artworkRepository.findAll(pageNumber, pageSize);
    }

    @Override
    public ArtworkDto findById(Long id) {
        return artworkRepository.findById(id);
    }

    @Override
    public ArtworkDto save(ArtworkDto artwork) {
        return artworkRepository.save(artwork);
    }

    @Override
    public void delete(Long id) {
        artworkRepository.delete(id);
    }
}
