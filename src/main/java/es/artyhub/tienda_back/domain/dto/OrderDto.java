package es.artyhub.tienda_back.domain.dto;

import es.artyhub.tienda_back.domain.model.OrderItem;
import es.artyhub.tienda_back.domain.model.User;

import java.util.List;
import java.util.Objects;

public class OrderDto {
    private Long id;
    private List<OrderItemDto> orderItems;
    private UserDto user;

    public OrderDto(Long id, List<OrderItemDto> orderItems, UserDto user) {
        this.id = id;
        this.orderItems = orderItems;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<OrderItemDto> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemDto> orderItems) {
        this.orderItems = orderItems;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass()) return false;
        OrderDto orderDto = (OrderDto) o;
        return Objects.equals(id, orderDto.id) && Objects.equals(orderItems, orderDto.orderItems) && Objects.equals(user, orderDto.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, orderItems, user);
    }
}
