package es.artyhub.tienda_back.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import es.artyhub.tienda_back.domain.model.Page;
import es.artyhub.tienda_back.domain.service.ArtworkService;
import es.artyhub.tienda_back.domain.dto.ArtworkDto;
import es.artyhub.tienda_back.controller.webmodel.response.ArtworkDetailResponse;
import es.artyhub.tienda_back.controller.mapper.ArtworkMapper;
import es.artyhub.tienda_back.domain.validation.DtoValidator;

@RestController
@RequestMapping("/api/artworks")
public class ArtworkController {
    
    private final ArtworkService artworkService;
    
    public ArtworkController(ArtworkService artworkService) {
        this.artworkService = artworkService;
    }

    @GetMapping
    public ResponseEntity<Page<ArtworkDetailResponse>> getAllArtworks(@RequestParam(required = false, defaultValue = "1") int pageNumber,
                                                                       @RequestParam(required = false, defaultValue = "20") int pageSize) {
        Page<ArtworkDto> artworkDtoPage = artworkService.findAll(pageNumber, pageSize);

        List<ArtworkDetailResponse> artworkDetails = artworkDtoPage.data().stream()
            .map(ArtworkMapper::fromArtworkDtoToArtworkDetailResponse)
            .toList();

        Page<ArtworkDetailResponse> artworkDetailPage = new Page<>(
            artworkDetails,
            artworkDtoPage.pageNumber(),
            artworkDtoPage.pageSize(),
            artworkDtoPage.totalElements()
        );    
        return new ResponseEntity<>(artworkDetailPage, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArtworkDetailResponse> getArtworkById(@PathVariable Long id) {
        ArtworkDto artworkDto = artworkService.findById(id);
        if (artworkDto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        ArtworkDetailResponse artworkDetailResponse = ArtworkMapper.fromArtworkDtoToArtworkDetailResponse(artworkDto);
        return new ResponseEntity<>(artworkDetailResponse, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ArtworkDetailResponse> createArtwork(@RequestBody ArtworkDto artworkDto) {
        try {
            DtoValidator.validate(artworkDto);
            ArtworkDto createArtworkDto = artworkService.insert(artworkDto);
            ArtworkDetailResponse artworkDetailResponse = ArtworkMapper.fromArtworkDtoToArtworkDetailResponse(createArtworkDto);
            return new ResponseEntity<>(artworkDetailResponse, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PutMapping()
    public ResponseEntity<ArtworkDetailResponse> updateArtwork(@RequestBody ArtworkDto artworkDto) {
        try {
            DtoValidator.validate(artworkDto);
            ArtworkDto updateArtworkDto = artworkService.update(artworkDto);
            ArtworkDetailResponse artworkDetailResponse = ArtworkMapper.fromArtworkDtoToArtworkDetailResponse(updateArtworkDto);
            return new ResponseEntity<>(artworkDetailResponse, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArtwork(@PathVariable Long id) {
        artworkService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}