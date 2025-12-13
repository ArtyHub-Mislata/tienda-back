package es.artyhub.tienda_back.domain.service.impl;

import es.artyhub.tienda_back.domain.dto.ArtworkDto;
import es.artyhub.tienda_back.domain.exception.BusinessException;
import es.artyhub.tienda_back.domain.model.Page;
import es.artyhub.tienda_back.domain.repository.ArtworkRepository;
import es.artyhub.tienda_back.domain.service.ArtworkService;
import jakarta.transaction.Transactional;

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
        return artworkRepository.findById(id).orElseThrow(() -> new BusinessException("Artwork with id " + id + " not found"));
    }

    @Override
    @Transactional
    public ArtworkDto insert(ArtworkDto artworkDto) {
        //No lo borro por si acaso, pero no tiene sentido comprobar el id si estamos insertando
//        if (artworkRepository.findById(artworkDto.getId()).isPresent()) {
//            throw new BusinessException("Artwork with id " + artworkDto.getId() + " already exists");
//        }
        return artworkRepository.save(artworkDto);
    }

    @Override
    @Transactional
    public ArtworkDto update(ArtworkDto artworkDto) {
        //Aqui por ejemplo si que tiene sentido, porque estas modificando algo que existe en bd y por tanto tiene id asignado
        if (artworkRepository.findById(artworkDto.getId()).isEmpty()) {
            throw new BusinessException("Artwork with id " + artworkDto.getId() + " not found");
        }
        return artworkRepository.save(artworkDto);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (artworkRepository.findById(id).isEmpty()) {
            throw new BusinessException("Artwork with id " + id + " not found");
        }
        artworkRepository.delete(id);
    }
}
