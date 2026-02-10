package es.artyhub.tienda_back.persistence.repository.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import es.artyhub.tienda_back.domain.dto.CategoryDto;
import es.artyhub.tienda_back.domain.model.Page;
import es.artyhub.tienda_back.persistence.dao.jpa.CategoryJpaDao;
import es.artyhub.tienda_back.persistence.dao.jpa.entity.CategoryJpaEntity;
import es.artyhub.tienda_back.persistence.repository.mapper.CategoryMapper;

import org.mockito.InjectMocks;
import org.mockito.Mock;

@ExtendWith(MockitoExtension.class)
public class CategoryRepositoryImplTest {
    
    @Mock
    private CategoryJpaDao categoryJpaDao;

    @InjectMocks
    private CategoryRepositoryImpl categoryRepository;

    @Nested
    @DisplayName("findAll")
    class FindAllTest {
        
        @Test
        @DisplayName("Should return a page of categories")
        void shouldReturnPageOfCategories() {
            int page = 1;
            int size = 10;

            CategoryJpaEntity categoryJpaEntity = new CategoryJpaEntity();
            List<CategoryJpaEntity> categoryJpaEntityList = List.of(categoryJpaEntity);
            List<CategoryDto> categoryDtoList = categoryJpaEntityList.stream().map(CategoryMapper.getInstance()::fromCategoryJpaEntityToCategoryDto).toList();

            when(categoryJpaDao.findAll(page, size)).thenReturn(categoryJpaEntityList);
            when(categoryJpaDao.count()).thenReturn((long) categoryJpaEntityList.size());

            Page<CategoryDto> categoryDtoPage = new Page<>(categoryDtoList, page, size, categoryJpaEntityList.size());

            Page<CategoryDto> result = categoryRepository.findAll(page, size);

            assertEquals(categoryDtoPage.pageNumber(), result.pageNumber());
            assertEquals(categoryDtoPage.pageSize(), result.pageSize());
            assertEquals(categoryDtoPage.totalElements(), result.totalElements());
            assertEquals(categoryDtoPage.totalPages(), result.totalPages());
        }
    }

    @Nested
    @DisplayName("findById")
    class FindByIdTest {
        
        @Test
        @DisplayName("Should return a category")
        void shouldReturnCategory() {
            Long categoryId = 1L;

            CategoryJpaEntity categoryJpaEntity = new CategoryJpaEntity(categoryId, "Category");

            when(categoryJpaDao.findById(categoryId)).thenReturn(Optional.of(categoryJpaEntity));

            CategoryDto categoryDto = CategoryMapper.getInstance().fromCategoryJpaEntityToCategoryDto(categoryJpaEntity);

            Optional<CategoryDto> result = categoryRepository.findById(categoryId);

            assertEquals(categoryDto.getId(), result.get().getId());
            assertEquals(categoryDto.getName(), result.get().getName());
        }
    }

    @Nested
    @DisplayName("save")
    class SaveTest {
        
        @Test
        @DisplayName("Should insert a category if id is null")
        void shouldInsertCategory() {
            CategoryDto categoryDto = new CategoryDto(null, "Category");

            CategoryJpaEntity categoryJpaEntity = CategoryMapper.getInstance().fromCategoryDtoToCategoryJpaEntity(categoryDto);

            when(categoryJpaDao.insert(categoryJpaEntity)).thenReturn(categoryJpaEntity);

            CategoryDto result = categoryRepository.save(categoryDto);

            assertEquals(categoryDto.getId(), result.getId());
            assertEquals(categoryDto.getName(), result.getName());
        }

        @Test
        @DisplayName("Should update a category if id is not null")
        void shouldUpdateCategory() {
            CategoryDto categoryDto = new CategoryDto(1L, "Category");

            CategoryJpaEntity categoryJpaEntity = CategoryMapper.getInstance().fromCategoryDtoToCategoryJpaEntity(categoryDto);

            when(categoryJpaDao.update(categoryJpaEntity)).thenReturn(categoryJpaEntity);

            CategoryDto result = categoryRepository.save(categoryDto);

            assertEquals(categoryDto.getId(), result.getId());
            assertEquals(categoryDto.getName(), result.getName());
        }
    }

    @Nested
    @DisplayName("delete")
    class DeleteTest {
        
        @Test
        @DisplayName("Should delete a category")
        void shouldDeleteCategory() {
            Long categoryId = 1L;

            categoryRepository.delete(categoryId);

            verify(categoryJpaDao).deleteById(categoryId);
        }
    }
}
