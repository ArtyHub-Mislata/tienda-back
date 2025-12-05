package es.artyhub.tienda_back.domain.repository;

import es.artyhub.tienda_back.domain.dto.CategoryDto;
import es.artyhub.tienda_back.domain.model.Page;
import java.util.Optional;

public interface CategoryRepository {

    Page<CategoryDto> findAll(int pageNumber, int pageSize);

    Optional<CategoryDto> findById(Long id);

    CategoryDto save(CategoryDto categoryDto);

    void delete(Long id);
}
