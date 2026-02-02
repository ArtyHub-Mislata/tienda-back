package es.artyhub.tienda_back.domain.service;

import es.artyhub.tienda_back.domain.dto.PaymentDto;
import es.artyhub.tienda_back.domain.enums.Status;

public interface PaymentService {
    Status pay(PaymentDto paymentDto);
}
