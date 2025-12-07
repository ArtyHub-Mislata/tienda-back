package es.artyhub.tienda_back.spring.config;

import es.artyhub.tienda_back.domain.repository.ArtworkRepository;
import es.artyhub.tienda_back.domain.repository.CategoryRepository;
import es.artyhub.tienda_back.domain.repository.UserRepository;
import es.artyhub.tienda_back.domain.service.ArtworkService;
import es.artyhub.tienda_back.domain.service.CategoryService;
import es.artyhub.tienda_back.domain.service.UserService;
import es.artyhub.tienda_back.domain.service.impl.ArtworkServiceImpl;
import es.artyhub.tienda_back.domain.service.impl.CategoryServiceImpl;
import es.artyhub.tienda_back.domain.service.impl.UserServiceImpl;
import es.artyhub.tienda_back.persistence.dao.jpa.ArtworkJpaDao;
import es.artyhub.tienda_back.persistence.dao.jpa.CategoryJpaDao;
import es.artyhub.tienda_back.persistence.dao.jpa.UserJpaDao;
import es.artyhub.tienda_back.persistence.dao.jpa.impl.ArtworkJpaDaoImpl;
import es.artyhub.tienda_back.persistence.dao.jpa.impl.CategoryJpaDaoImpl;
import es.artyhub.tienda_back.persistence.dao.jpa.impl.UserJpaDaoImpl;
import es.artyhub.tienda_back.persistence.repository.impl.ArtworkRepositoryImpl;
import es.artyhub.tienda_back.persistence.repository.impl.CategoryRepositoryImpl;
import es.artyhub.tienda_back.persistence.repository.impl.UserRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    //BEANS DE ARTWORK
    @Bean
    public ArtworkJpaDao artworkJpaDao(){
        return new ArtworkJpaDaoImpl();
    }
    @Bean
    public ArtworkRepository artworkRepository(ArtworkJpaDao artworkJpaDao){
        return new ArtworkRepositoryImpl(artworkJpaDao);
    }
    @Bean
    public ArtworkService artworkService(ArtworkRepository artworkRepository){
        return new ArtworkServiceImpl(artworkRepository);
    }
    //BEANS DE USER
    @Bean
    public UserJpaDao userJpaDao(){
        return new UserJpaDaoImpl();
    }
    @Bean
    public UserRepository userRepository(UserJpaDao userJpaDao){
        return new UserRepositoryImpl(userJpaDao);
    }
    @Bean
    public UserService userService(UserRepository userRepository){
        return new UserServiceImpl(userRepository);
    }

    //BEANS DE CATEGORY
    @Bean
    public CategoryJpaDao categoryJpaDao(){
        return new CategoryJpaDaoImpl();
    }
    @Bean
    public CategoryRepository categoryRepository(CategoryJpaDao categoryJpaDao){
        return new CategoryRepositoryImpl(categoryJpaDao);
    }
    @Bean
    public CategoryService categoryService(CategoryRepository categoryRepository){
        return new CategoryServiceImpl(categoryRepository);
    }
}
