package es.artyhub.tienda_back.domain.service;

import es.artyhub.tienda_back.domain.model.Page;
import es.artyhub.tienda_back.domain.dto.CategoryDto;
import java.util.Optional;

public interface CategoryService {
    
    Page<CategoryDto> findAll(int pageNumber, int pageSize);

    Optional<CategoryDto> findById(Long id);

    CategoryDto insert(CategoryDto categoryDto);

    CategoryDto update(CategoryDto categoryDto);

    void delete(Long id);
}
