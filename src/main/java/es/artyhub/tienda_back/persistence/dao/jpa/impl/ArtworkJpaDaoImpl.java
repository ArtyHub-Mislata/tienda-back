package es.artyhub.tienda_back.persistence.dao.jpa.impl;

import es.artyhub.tienda_back.domain.exception.ResourceNotFoundException;
import es.artyhub.tienda_back.persistence.dao.jpa.ArtworkJpaDao;
import es.artyhub.tienda_back.persistence.dao.jpa.entity.ArtworkJpaEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Optional;

public class ArtworkJpaDaoImpl implements ArtworkJpaDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<ArtworkJpaEntity> findAll(int page, int size) {
        int indicePagina = Math.max(page - 1, 0);

        String sql = "SELECT artwork FROM ArtworkJpaEntity artwork ORDER BY artwork.id ASC";

        TypedQuery<ArtworkJpaEntity> artworkJpaEntityPage =  entityManager
                .createQuery(sql, ArtworkJpaEntity.class)
                .setFirstResult(indicePagina * size)
                .setMaxResults(size);
        return artworkJpaEntityPage.getResultList();
    }

    @Override
    public Optional<ArtworkJpaEntity> findById(Long id) {
        return Optional.ofNullable(entityManager.find(ArtworkJpaEntity.class, id));
    }

    @Override
    public ArtworkJpaEntity insert(ArtworkJpaEntity jpaEntity) {
        entityManager.persist(jpaEntity);
        return jpaEntity;
    }

    @Override
    public ArtworkJpaEntity update(ArtworkJpaEntity jpaEntity) {
        ArtworkJpaEntity artworkJpaEntity = entityManager.find(ArtworkJpaEntity.class, jpaEntity.getId());
        if(artworkJpaEntity == null){
            throw new ResourceNotFoundException("Artwork with id " + jpaEntity.getId() + " not found, error when updating");
        }
        //hacemos la comprobación de si existe ya que si no existe en vez de hacer un update hará in insert
        return entityManager.merge(artworkJpaEntity);

    }

    @Override
    public void deleteById(Long id) {
        entityManager.remove(entityManager.find(ArtworkJpaEntity.class, id));
    }

    @Override
    public long count() {
        String jpql = "SELECT COUNT(a) FROM ArtworkJpaEntity a";
        return entityManager
                .createQuery(jpql, Long.class)
                .getSingleResult();
    }
}
