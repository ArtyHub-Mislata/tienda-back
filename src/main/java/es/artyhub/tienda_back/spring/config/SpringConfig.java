package es.artyhub.tienda_back.spring.config;

import es.artyhub.tienda_back.domain.repository.*;
import es.artyhub.tienda_back.domain.service.*;
import es.artyhub.tienda_back.domain.service.impl.*;
import es.artyhub.tienda_back.persistence.dao.jpa.*;
import es.artyhub.tienda_back.persistence.dao.jpa.impl.*;
import es.artyhub.tienda_back.persistence.repository.impl.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import es.artyhub.tienda_back.domain.service.impl.SesionServiceImpl;

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
    public UserService userService(UserRepository userRepository, ArtworkRepository artworkRepository){
        return new UserServiceImpl(userRepository, artworkRepository);
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

    //BEANS DE SESION
    @Bean
    public SesionJpaDao sesionJpaDao(){
        return new SesionJpaDaoImpl();
    }
    @Bean
    public SesionRepository sesionRepository(SesionJpaDao sesionJpaDao){
        return new SesionRepositoryImpl(sesionJpaDao);
    }
    @Bean
    public SesionService sesionService(SesionRepository sesionRepository){
        return new SesionServiceImpl(sesionRepository);
    }

    //BEANS DE LOGIN
    @Bean
    public LoginJpaDao loginJpaDao(){
        return new LoginJpaDaoImpl(userJpaDao(), sesionJpaDao());
    }
    @Bean
    public LoginRepository loginRepository(LoginJpaDao loginJpaDao){
        return new LoginRepositoryImpl(loginJpaDao);
    }
    @Bean
    public LoginService loginService(LoginRepository loginRepository){
        return new LoginServiceImpl(loginRepository);
    }

    //BEANS DEL CARRO
    @Bean
    public CartJpaDao cartJpaDao(){
        return new CartJpaDaoImpl();
    }
    @Bean
    public CartRepository cartRepository(CartJpaDao cartJpaDao){
        return new CartRepositoryImpl(cartJpaDao);
    }

    @Bean
    public CartService cartService(CartRepository cartRepository){
        return new CartServiceImpl(cartRepository);
    }
    //BEANS DE PAYMENT
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public OrderJpaDao orderJpaDao(){
        return new OrderJpaDaoImpl();
    }
    @Bean
    public OrderRepository orderRepository(OrderJpaDao orderJpaDao){
        return new OrderRepositoryImpl(orderJpaDao);
    }
    @Bean
    public OrderService orderService(OrderRepository orderRepository){
        return new OrderServiceImpl(orderRepository);
    }
}
