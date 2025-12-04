package es.artyhub.tienda_back.domain.repository;

import es.artyhub.tienda_back.domain.dto.CategoryDto;
import org.springframework.data.domain.Page;
import java.util.Optional;

public interface CategoryRepositoy {

    Page<CategoryDto> findAll(int pageNumber, int pageSize);

    Optional<CategoryDto> findById(Long id);

    CategoryDto save(CategoryDto categoryDto);

    void delete(Long id);
}
