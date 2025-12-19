package es.artyhub.tienda_back.persistence.dao.jpa.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "categories")
public class CategoryJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;

    public CategoryJpaEntity(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public CategoryJpaEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CategoryJpaEntity that = (CategoryJpaEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(nombre, that.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre);
    }
}

