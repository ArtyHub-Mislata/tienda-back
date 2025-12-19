package es.artyhub.tienda_back.util;

import es.artyhub.tienda_back.persistence.dao.jpa.ArtworkJpaDao;
import es.artyhub.tienda_back.persistence.dao.jpa.impl.ArtworkJpaDaoImpl;
import org.springframework.context.annotation.Bean;

public class SpringTestConfig {
    @Bean
    public ArtworkJpaDao artworkJpaDao(){
        return new ArtworkJpaDaoImpl();
    }
}
