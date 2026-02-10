package es.artyhub.tienda_back.persistence.dao.jpa.impl;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

import es.artyhub.tienda_back.domain.enums.UserRole;
import es.artyhub.tienda_back.domain.exception.ResourceNotFoundException;
import es.artyhub.tienda_back.persistence.TestConfig;
import es.artyhub.tienda_back.persistence.dao.jpa.ArtworkJpaDao;
import es.artyhub.tienda_back.persistence.dao.jpa.entity.ArtworkJpaEntity;
import es.artyhub.tienda_back.persistence.dao.jpa.entity.CategoryJpaEntity;
import es.artyhub.tienda_back.persistence.dao.jpa.entity.UserJpaEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@DataJpaTest
@ContextConfiguration(classes = TestConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ArtworkJpaDaoImplTest {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private ArtworkJpaDao artworkJpaDao;

    @Nested
    @DisplayName("findAll")
    public class FindAll {
        @Test
        @DisplayName("should return all artworks")
        public void shouldReturnAllArtworks() {
            List<ArtworkJpaEntity> result = artworkJpaDao.findAll(1, 10);
            assertEquals(3, result.size());
        }
    }

    @Nested
    @DisplayName("findAllArtworksOfUser")
    public class FindAllArtworksOfUser {
        @Test
        @DisplayName("should return all artworks of user")
        public void shouldReturnAllArtworksOfUser() {
            Long userId = 2L;
            int page = 1;
            int size = 10;

            List<ArtworkJpaEntity> result = artworkJpaDao.findAllArtworksOfUser(userId, page, size);
            assertEquals(2, result.size());
        }
    }

    @Nested
    @DisplayName("findAllArtworksByCategory")
    public class FindAllArtworksByCategory {
        @Test
        @DisplayName("should return all artworks of category")
        public void shouldReturnAllArtworksOfCategory() {
            Long categoryId = 1L;
            int page = 1;
            int size = 10;

            List<ArtworkJpaEntity> result = artworkJpaDao.findAllArtworksByCategory(categoryId, page, size);
            assertEquals(2, result.size());
        }
    }

    @Nested
    @DisplayName("findById")
    public class FindById {
        @Test
        @DisplayName("should return artwork when id exists")
        public void shouldReturnArtworkWhenIdExists() {
            Long artworkId = 1L;

            Optional<ArtworkJpaEntity> result = artworkJpaDao.findById(artworkId);

            assertEquals(1L, result.get().getId());
        }

        @Test
        @DisplayName("should return empty when id does not exist")
        public void shouldReturnEmptyWhenIdDoesNotExist() {
            Long artworkId = 20L;

            Optional<ArtworkJpaEntity> result = artworkJpaDao.findById(artworkId);

            assertEquals(Optional.empty(), result);
        }
    }

    @Nested
    @DisplayName("insert")
    public class Insert {
        @Test
        @DisplayName("should insert artwork")
        public void shouldInsertArtwork() {

            CategoryJpaEntity categoryJpaEntity = new CategoryJpaEntity(null, "Category 1");
            entityManager.persist(categoryJpaEntity);

            UserJpaEntity userJpaEntity = new UserJpaEntity(null, "User 1", "Email 1", "Password 1", "Description 1", "Address 1", "Image 1", UserRole.ADMIN);
            entityManager.persist(userJpaEntity);
           
            ArtworkJpaEntity art1 = new ArtworkJpaEntity(null, "Artwork 1", "Description 1", "Image 1", new BigDecimal(10.0), categoryJpaEntity, userJpaEntity, 10L);

            long countBefore = entityManager.createQuery("SELECT COUNT(a) FROM ArtworkJpaEntity a", Long.class)
            .getSingleResult();

            ArtworkJpaEntity result = artworkJpaDao.insert(art1);
            entityManager.flush();

            long countAfter = entityManager.createQuery("SELECT COUNT(a) FROM ArtworkJpaEntity a", Long.class)
            .getSingleResult();

            assertAll(
                () -> assertNotNull(result.getId(), "El ID no debería ser nulo tras insertar"),
                () -> assertEquals("Artwork 1", result.getName()),
                () -> assertEquals(categoryJpaEntity.getId(), result.getCategory().getId()),
                () -> assertEquals(userJpaEntity.getId(), result.getUserJpaEntity().getId()),
                () -> assertEquals(countBefore + 1, countAfter, "El número total de registros debería haber aumentado en 1")
            );
        }
    }

    @Nested
    @DisplayName("update")
    public class Update {
        @Test
        @DisplayName("should update artwork")
        public void shouldUpdateArtwork() {
            Long artworkId = 1L;

            Optional<ArtworkJpaEntity> result = artworkJpaDao.findById(artworkId);

            CategoryJpaEntity categoryJpaEntity = new CategoryJpaEntity(1L, "Pintura");
            UserJpaEntity userJpaEntity = new UserJpaEntity(1L, "User 1", "Email 1", "Password 1", "Description 1", "Address 1", "Image 1", UserRole.ADMIN);

            ArtworkJpaEntity newArtwork = new ArtworkJpaEntity(artworkId, "New Artwork", "New Description", "New Image", new BigDecimal(20.0), categoryJpaEntity, userJpaEntity, 20L);
            entityManager.merge(newArtwork);
            entityManager.flush();

            assertEquals(newArtwork.getId(), result.get().getId());
            assertEquals("New Artwork", newArtwork.getName());
            assertEquals(result.get().getCategory().getNombre(), newArtwork.getCategory().getNombre());
        }

        @Test
        @DisplayName("should throw resource not found exception when artwork does not exist")
        public void shouldNotUpdateArtwork() {
            Long artworkId = 20L;

            CategoryJpaEntity categoryJpaEntity = new CategoryJpaEntity(1L, "Category 1");
            UserJpaEntity userJpaEntity = new UserJpaEntity(1L, "User 1", "Email 1", "Password 1", "Description 1", "Address 1", "Image 1", UserRole.ADMIN);
           
            ArtworkJpaEntity newArtwork = new ArtworkJpaEntity(artworkId, "New Artwork", "New Description", "New Image", new BigDecimal(20.0), categoryJpaEntity, userJpaEntity, 20L);

            assertThrows(ResourceNotFoundException.class, () -> artworkJpaDao.update(newArtwork));
        }
    }

    @Nested
    @DisplayName("deleteById")
    public class DeleteById {
        @Test
        @DisplayName("should delete artwork")
        public void shouldDeleteArtwork() {
            Long artworkId = 1L;

            artworkJpaDao.deleteById(artworkId);

            Optional<ArtworkJpaEntity> result = artworkJpaDao.findById(artworkId);

            assertEquals(Optional.empty(), result);
        }
    }

    @Nested
    @DisplayName("count")
    public class Count {
        @Test
        @DisplayName("should return count of artworks")
        public void shouldReturnCountOfArtworks() {
            Long count = artworkJpaDao.count();

            assertEquals(3L, count);
        }
    }
}
