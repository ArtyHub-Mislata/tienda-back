package es.artyhub.tienda_back.persistence.dao.jpa.impl;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

import es.artyhub.tienda_back.domain.exception.ResourceNotFoundException;
import es.artyhub.tienda_back.persistence.TestConfig;
import es.artyhub.tienda_back.persistence.dao.jpa.CategoryJpaDao;
import es.artyhub.tienda_back.persistence.dao.jpa.entity.CategoryJpaEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@DataJpaTest
@ContextConfiguration(classes = TestConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CategoryJpaDaoImplTest {
    
    @PersistenceContext
    private EntityManager entityManager;
    
    @Autowired
    private CategoryJpaDao categoryJpaDao;


    @Nested
    @DisplayName("findAll")
    public class FindAll {
        @Test
        @DisplayName("should return all categories")
        public void shouldReturnAllCategories() {
            List<CategoryJpaEntity> result = categoryJpaDao.findAll(1, 10);
            assertEquals(5, result.size());
        }
    }

    @Nested
    @DisplayName("findById")
    public class FindById {
        @Test
        @DisplayName("should return category when id exists")
        public void shouldReturnCategoryWhenIdExists() {
            Long categoryId = 1L;

            Optional<CategoryJpaEntity> result = categoryJpaDao.findById(categoryId);

            assertEquals(categoryId, result.get().getId());
        }

        @Test
        @DisplayName("should return empty when id does not exist")
        public void shouldReturnEmptyWhenIdDoesNotExist() {
            Long categoryId = 20L;

            Optional<CategoryJpaEntity> result = categoryJpaDao.findById(categoryId);

            assertEquals(Optional.empty(), result);
        }
    }

    @Nested
    @DisplayName("insert")
    public class Insert {
        @Test
        @DisplayName("should insert category")
        public void shouldInsertCategory() {

            CategoryJpaEntity categoryJpaEntity = new CategoryJpaEntity(null, "Category 1");
           
            long countBefore = entityManager.createQuery("SELECT COUNT(c) FROM CategoryJpaEntity c", Long.class)
            .getSingleResult();

            CategoryJpaEntity result = categoryJpaDao.insert(categoryJpaEntity);
            entityManager.flush();

            long countAfter = entityManager.createQuery("SELECT COUNT(c) FROM CategoryJpaEntity c", Long.class)
            .getSingleResult();

            assertAll(
                () -> assertNotNull(result.getId(), "El ID no debería ser nulo tras insertar"),
                () -> assertEquals("Category 1", result.getNombre()),
                () -> assertEquals(countBefore + 1, countAfter, "El número total de registros debería haber aumentado en 1")
            );
        }
    }

    @Nested
    @DisplayName("update")
    public class Update {
        @Test
        @DisplayName("should update category")
        public void shouldUpdateCategory() {
            Long categoryId = 1L;
            
            CategoryJpaEntity newCategoryJpaEntity = new CategoryJpaEntity(categoryId, "New Category");
            entityManager.merge(newCategoryJpaEntity);
            entityManager.flush();

            Optional<CategoryJpaEntity> updatedCategory = categoryJpaDao.findById(categoryId);

            assertEquals(newCategoryJpaEntity, updatedCategory.get());
        }

        @Test
        @DisplayName("should throw resource not found exception when category does not exist")
        public void shouldNotUpdateCategory() {
            Long categoryId = 20L;
            CategoryJpaEntity newCategory = new CategoryJpaEntity(categoryId, "New Category");

            assertThrows(ResourceNotFoundException.class, () -> categoryJpaDao.update(newCategory));
        }
    }

    @Nested
    @DisplayName("deleteById")
    public class DeleteById {
        @Test
        @DisplayName("should delete category")
        public void shouldDeleteCategory() {
            Long categoryId = 1L;

            categoryJpaDao.deleteById(categoryId);

            Optional<CategoryJpaEntity> result = categoryJpaDao.findById(categoryId);

            assertEquals(Optional.empty(), result);
        }
    }

    @Nested
    @DisplayName("count")
    public class Count {
        @Test
        @DisplayName("should return count of categories")
        public void shouldReturnCountOfCategories() {
            Long count = categoryJpaDao.count();

            assertEquals(5, count);
        }
    }
}
