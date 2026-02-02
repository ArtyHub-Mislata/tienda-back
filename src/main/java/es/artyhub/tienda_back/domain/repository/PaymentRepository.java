package es.artyhub.tienda_back.domain.repository;

import es.artyhub.tienda_back.domain.dto.PaymentDto;
import es.artyhub.tienda_back.domain.enums.Status;

public interface PaymentRepository {
    Status pay(PaymentDto paymentDto);
}
