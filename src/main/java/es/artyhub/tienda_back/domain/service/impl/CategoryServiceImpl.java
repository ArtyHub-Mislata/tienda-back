package es.artyhub.tienda_back.domain.service.impl;

import es.artyhub.tienda_back.domain.model.Page;
import es.artyhub.tienda_back.domain.dto.CategoryDto;
import es.artyhub.tienda_back.domain.exception.BusinessException;
import es.artyhub.tienda_back.domain.exception.ValidationException;
import es.artyhub.tienda_back.domain.repository.CategoryRepository;
import es.artyhub.tienda_back.domain.service.CategoryService;
import jakarta.transaction.Transactional;

public class CategoryServiceImpl implements CategoryService {
    
    private final CategoryRepository categoryRepositoy;

    public CategoryServiceImpl(CategoryRepository categoryRepositoy) {
        this.categoryRepositoy = categoryRepositoy;
    }

    @Override
    public Page<CategoryDto> findAll(int pageNumber, int pageSize) {
        return categoryRepositoy.findAll(pageNumber, pageSize);
    }

    @Override
    public CategoryDto findById(Long id) {
        return categoryRepositoy.findById(id).orElseThrow(() -> new BusinessException("Category with id " + id + " not found"));
    }

    @Override
    @Transactional
    public CategoryDto insert(CategoryDto categoryDto) {
        if (categoryDto.getName() == null || categoryDto.getName().trim().isEmpty()) {
            throw new ValidationException("Category name is required");
        } else {
            return categoryRepositoy.save(categoryDto);
        }
    }

    @Override
    @Transactional
    public CategoryDto update(CategoryDto categoryDto) {
        if (categoryRepositoy.findById(categoryDto.getId()).isEmpty()) {
            throw new BusinessException("Category with id " + categoryDto.getId() + " does not exist");
        } else {
            return categoryRepositoy.save(categoryDto);
        }
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (categoryRepositoy.findById(id).isEmpty()) {
            throw new BusinessException("Category with id " + id + " does not exist");
        } else {
            categoryRepositoy.delete(id);
        }
    }
}
