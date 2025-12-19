package es.artyhub.tienda_back.persistence.repository.impl;

import es.artyhub.tienda_back.domain.dto.CategoryDto;
import es.artyhub.tienda_back.domain.model.Page;
import es.artyhub.tienda_back.domain.repository.CategoryRepository;
import es.artyhub.tienda_back.persistence.dao.jpa.CategoryJpaDao;
import es.artyhub.tienda_back.persistence.dao.jpa.entity.CategoryJpaEntity;
import es.artyhub.tienda_back.persistence.repository.mapper.CategoryMapper;

import java.util.List;
import java.util.Optional;

public class CategoryRepositoryImpl implements CategoryRepository {

    private final CategoryJpaDao categoryJpaDao;

    public CategoryRepositoryImpl(CategoryJpaDao categoryJpaDao) {
        this.categoryJpaDao = categoryJpaDao;
    }

    @Override
    public Page<CategoryDto> findAll(int pageNumber, int pageSize) {
        List<CategoryDto> listaCategoryDto = categoryJpaDao
                .findAll(pageNumber, pageSize)
                .stream()
                .map(CategoryMapper.getInstance()::fromCategoryJpaEntityToCategoryDto)
                .toList();
        long totalElements = categoryJpaDao.count();

        return new Page<>(
                listaCategoryDto,
                pageNumber,
                pageSize,
                totalElements
        );
    }

    @Override
    public Optional<CategoryDto> findById(Long id) {
        return categoryJpaDao
                .findById(id)
                .map(CategoryMapper.getInstance()::fromCategoryJpaEntityToCategoryDto);
    }

    @Override
    public CategoryDto save(CategoryDto categoryDto) {
        CategoryJpaEntity categoryJpaEntity = CategoryMapper.getInstance().fromCategoryDtoToCategoryJpaEntity(categoryDto);
        if (categoryDto.getId() == null) {
            return CategoryMapper.getInstance().fromCategoryJpaEntityToCategoryDto(categoryJpaDao.insert(categoryJpaEntity));
        }
        return CategoryMapper.getInstance().fromCategoryJpaEntityToCategoryDto(categoryJpaDao.update(categoryJpaEntity));

    }

    @Override
    public void delete(Long id) {
        categoryJpaDao.deleteById(id);
    }
}
