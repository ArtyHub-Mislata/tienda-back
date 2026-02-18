package es.artyhub.tienda_back.controller;

import es.artyhub.tienda_back.domain.dto.CartDto;
import es.artyhub.tienda_back.domain.dto.CartItemDto;
import es.artyhub.tienda_back.domain.dto.UserDto;
import es.artyhub.tienda_back.domain.exception.ValidationException;
import es.artyhub.tienda_back.domain.service.CartService;
import es.artyhub.tienda_back.domain.validation.DtoValidator;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/cart")
@RestController
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PutMapping("/{id}")
    public ResponseEntity<CartDto> actualizarCarrito(@PathVariable("id") Long id, @RequestBody CartDto cartDto) {

        if (!id.equals(cartDto.getId())) {
            throw new ValidationException("El id del path y el de el carrito no coinciden");
        }
        DtoValidator.validate(cartDto);
        CartDto cartResponse = cartService.updateCart(cartDto);
        return new ResponseEntity<>(cartResponse, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> vaciarCarrito(@PathVariable("id") Long id){
        cartService.vaciarCarrito(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("/item/{id}")
    public ResponseEntity<CartItemDto> addItemToCart(@PathVariable("id") Long idArtwork, HttpServletRequest request){
        UserDto userDto = (UserDto) request.getAttribute("USER_DTO");
        CartDto cart = cartService.getCartOfUser(userDto.getId());
        CartItemDto cartItemDto = cartService.addItemToCart(cart, idArtwork);

        return new ResponseEntity<>(cartItemDto, HttpStatus.OK);
    }
}
