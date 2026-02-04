package es.artyhub.tienda_back.domain.service;

import es.artyhub.tienda_back.domain.dto.PaymentDto;

public interface BancoService {
    PaymentDto procesarPago(PaymentDto request);
}
