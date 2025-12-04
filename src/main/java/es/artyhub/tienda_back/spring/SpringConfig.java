package es.artyhub.tienda_back.spring;

import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

}

package es.artyhub.tienda_back.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import es.artyhub.tienda_back.domain.repository.ArtworkRepository;
import es.artyhub.tienda_back.domain.service.ArtworkService;
import es.artyhub.tienda_back.domain.service.impl.ArtworkServiceImpl;
import es.artyhub.tienda_back.persistence.dao.ArtworkDao;
import es.artyhub.tienda_back.persistence.dao.impl.ArtworkDaoJpa;
import es.artyhub.tienda_back.persistence.repository.impl.ArtworkRepositoryImpl;

@Configuration
public class SpringConfig {
    
    @Bean 
    public ArtworkDao artworkDao() {
        return new ArtworkDaoJpa();
    }

    @Bean 
    public ArtworkRepository artworkRepository() {
        return new ArtworkRepositoryImpl();
    }

    @Bean 
    public ArtworkService artworkService() {
        return new ArtworkServiceImpl();
    }

}
