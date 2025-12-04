package es.artyhub.tienda_back.persistence.repository.impl;

import es.artyhub.tienda_back.domain.dto.CategoryDto;
import es.artyhub.tienda_back.domain.model.Page;
import es.artyhub.tienda_back.domain.repository.CategoryRepositoy;

import java.util.Optional;

public class CategoryRepositoryImpl implements CategoryRepositoy {
    @Override
    public Page<CategoryDto> findAll(int pageNumber, int pageSize) {
        return null;
    }

    @Override
    public Optional<CategoryDto> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public CategoryDto save(CategoryDto categoryDto) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
