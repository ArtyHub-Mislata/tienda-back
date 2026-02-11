package es.artyhub.tienda_back.controller;

import es.artyhub.tienda_back.domain.dto.OrderDto;
import es.artyhub.tienda_back.domain.dto.UserDto;
import es.artyhub.tienda_back.domain.service.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<List<OrderDto>> getOrdersOfUser(HttpServletRequest request){
        UserDto userDto = (UserDto) request.getAttribute("USER_DTO");

        List<OrderDto> orders = orderService.getOrdersOfUser(userDto.getId());
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<OrderDto> createOrder(@RequestBody OrderDto orderDto){
        OrderDto order = orderService.insertOrder(orderDto);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }
}
