package es.artyhub.tienda_back.persistence.dao.jpa.impl;

import es.artyhub.tienda_back.domain.enums.UserRole;
import es.artyhub.tienda_back.persistence.dao.jpa.ArtworkJpaDao;
import es.artyhub.tienda_back.persistence.dao.jpa.entity.ArtworkJpaEntity;
import es.artyhub.tienda_back.persistence.dao.jpa.entity.CategoryJpaEntity;
import es.artyhub.tienda_back.persistence.dao.jpa.entity.UserJpaEntity;
import es.artyhub.tienda_back.spring.config.SpringConfig;
import es.artyhub.tienda_back.util.SpringTestConfig;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
//
@DataJpaTest
@ActiveProfiles("test")
@Import(SpringTestConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ArtworkJpaDaoImplTest {

    @Autowired
    private ArtworkJpaDao artworkJpaDao;

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    void findAll() {

    }

    @Test
    void findAllArtworksOfUser() {
    }

    @Test
    void findById() {
    }
    @Nested
    @Test
    void insert() {
        // 1. Crear categoría REAL en la BD
        CategoryJpaEntity category = new CategoryJpaEntity();
        category.setNombre("Categoría test");
        entityManager.persist(category);

        // 2. Crear user REAL en la BD
        UserJpaEntity user = new UserJpaEntity();
        user.setName("Nombre user prueba");
        user.setDescription("Descripcion user prueba");
        user.setEmail("userprueba@gmail.com");
        user.setAddress("Direccion");
        user.setImageProfileUrl("url");
        user.setnAccount("1234123412341234");
        user.setPassword("root root");
        user.setRole(UserRole.USER);
        entityManager.persist(user);

        // 3. Crear el Artwork usándolos
        ArtworkJpaEntity artwork = new ArtworkJpaEntity();
        artwork.setName("Nombre prueba");
        artwork.setDescription("Descripcion prueba");
        artwork.setImageUrl("image");
        artwork.setPrice(new BigDecimal("10.00"));
        artwork.setCategory(category);  // ⚠️ Ya está persistida
        artwork.setUserJpaEntity(user); // ⚠️ Ya está persistido

        artwork = artworkJpaDao.insert(artwork);

        // 4. Comprobar que se generó un ID
        assertNotNull(artwork.getId());

        // 5. Verificar que realmente está en la base de datos
        ArtworkJpaEntity stored = artworkJpaDao.findById(artwork.getId()).orElse(null);
        assertNotNull(stored);
        assertEquals("Nombre prueba", stored.getName());
    }

    private static CategoryJpaEntity getCategoryJpaEntityWithCorrectData(){
        CategoryJpaEntity categoryJpaEntity = new CategoryJpaEntity();
        categoryJpaEntity.setNombre("Categoria prueba insert");
        categoryJpaEntity.setId(1L);
        return  categoryJpaEntity;
    }
    private static UserJpaEntity getUserJpaEntityWithAllCorrectData() {
        UserJpaEntity userJpaEntity = new UserJpaEntity();
        userJpaEntity.setId(1L);
        userJpaEntity.setName("Nombre user prueba");
        userJpaEntity.setDescription("Descripcion user prueba");
        userJpaEntity.setEmail("Userprueba@gmail.com");
        userJpaEntity.setAddress("La casa de prueba del pavo este");
        userJpaEntity.setImageProfileUrl("Url user prueba");
        userJpaEntity.setnAccount("1234123412341234");
        userJpaEntity.setPassword("root root");
        userJpaEntity.setRole(UserRole.USER);
        return userJpaEntity;
    }

    @Test
    void update() {
    }

    @Test
    void deleteById() {
    }

    @Test
    void count() {
    }
}