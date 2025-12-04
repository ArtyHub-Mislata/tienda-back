package es.artyhub.tienda_back.domain.service.impl;

import es.artyhub.tienda_back.domain.model.Page;
import es.artyhub.tienda_back.domain.dto.CategoryDto;
import es.artyhub.tienda_back.domain.exception.BusinessException;
import es.artyhub.tienda_back.domain.repository.CategoryRepositoy;
import es.artyhub.tienda_back.domain.service.CategoryService;
import java.util.Optional;

public class CategoryServiceImpl implements CategoryService {
    
    private final CategoryRepositoy categoryRepositoy;

    public CategoryServiceImpl(CategoryRepositoy categoryRepositoy) {
        this.categoryRepositoy = categoryRepositoy;
    }

    @Override
    public Page<CategoryDto> findAll(int pageNumber, int pageSize) {
        return categoryRepositoy.findAll(pageNumber, pageSize);
    }

    @Override
    public Optional<CategoryDto> findById(Long id) {
        return categoryRepositoy.findById(id);
    }

    @Override
    public CategoryDto insert(CategoryDto categoryDto) {
        if (findById(categoryDto.id()).isPresent()) {
            throw new BusinessException("Category with id " + categoryDto.id() + " already exists");
        }
        return categoryRepositoy.save(categoryDto);
    }

    @Override
    public CategoryDto update(CategoryDto categoryDto) {
        if (findById(categoryDto.id()).isEmpty()) {
            throw new BusinessException("Category with id " + categoryDto.id() + " does not exist");
        }
        return categoryRepositoy.save(categoryDto);
    }

    @Override
    public void delete(Long id) {
        categoryRepositoy.delete(id);
    }
}
