package es.artyhub.tienda_back.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import es.artyhub.tienda_back.domain.model.Page;
import es.artyhub.tienda_back.domain.service.ArtworkService;
import es.artyhub.tienda_back.domain.dto.ArtworkDto;
import es.artyhub.tienda_back.domain.exception.ValidationException;
import es.artyhub.tienda_back.domain.validation.DtoValidator;


@RestController
@RequestMapping("/api/artworks")
public class ArtworkController {
    
    private final ArtworkService artworkService;
    
    public ArtworkController(ArtworkService artworkService) {
        this.artworkService = artworkService;
    }

    @GetMapping
    public ResponseEntity<Page<ArtworkDto>> getAllArtworks(@RequestParam(required = false, defaultValue = "1") int pageNumber,
                                                                       @RequestParam(required = false, defaultValue = "20") int pageSize) {
        Page<ArtworkDto> artworkDtoPage = artworkService.findAll(pageNumber, pageSize);

        List<ArtworkDto> artworkDetails = artworkDtoPage.data().stream()
            .toList();

        Page<ArtworkDto> artworkDetailPage = new Page<>(
            artworkDetails,
            artworkDtoPage.pageNumber(),
            artworkDtoPage.pageSize(),
            artworkDtoPage.totalElements()
        );    
        return new ResponseEntity<>(artworkDetailPage, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArtworkDto> getArtworkById(@PathVariable Long id) {
        ArtworkDto artworkDto = artworkService.findById(id);
        if (artworkDto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(artworkDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ArtworkDto> createArtwork(@RequestBody ArtworkDto artworkDto) {
        try {
            DtoValidator.validate(artworkDto);
            ArtworkDto createArtworkDto = artworkService.insert(artworkDto);
            return new ResponseEntity<>(createArtworkDto, HttpStatus.CREATED);
        } catch (ValidationException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ArtworkDto> updateArtwork(@PathVariable("id") Long id, @RequestBody ArtworkDto artworkDto) {
        try {
            if (!id.equals(artworkDto.getId())) {
                throw new ValidationException("ID in path and request body must match");
            }
            DtoValidator.validate(artworkDto);
            ArtworkDto updateArtworkDto = artworkService.update(artworkDto);
            return new ResponseEntity<>(updateArtworkDto, HttpStatus.OK);
        } catch (ValidationException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArtwork(@PathVariable Long id) {
        artworkService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}