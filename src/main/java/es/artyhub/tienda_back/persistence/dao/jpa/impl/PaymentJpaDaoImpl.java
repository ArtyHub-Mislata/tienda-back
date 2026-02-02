package es.artyhub.tienda_back.persistence.dao.jpa.impl;

import java.math.BigDecimal;

import org.springframework.web.client.RestTemplate;

import es.artyhub.tienda_back.domain.dto.PaymentDto;
import es.artyhub.tienda_back.domain.enums.Status;
import es.artyhub.tienda_back.domain.exception.ValidationException;
import es.artyhub.tienda_back.persistence.dao.jpa.PaymentJpaDao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class PaymentJpaDaoImpl implements PaymentJpaDao {

    @PersistenceContext
    private EntityManager entityManager;
    
    @Override
    public Status pay(PaymentDto paymentDto) {
        RestTemplate restTemplate = new RestTemplate();
        String urlBanco = "http://localhost:8081/api/pagos/pago_tarjeta";

        BigDecimal amount = paymentDto.getAmount();

        if (amount == null || amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new ValidationException("AMOUNT_CANNOT_BE_EMPTY");
        }

        try {
            restTemplate.postForEntity(urlBanco, paymentDto, Status.class);
            paymentDto.setStatus(Status.COMPLETED);
            entityManager.merge(paymentDto);
            return Status.COMPLETED;
        } catch (Exception e) {
            paymentDto.setStatus(Status.FAILED);
            entityManager.merge(paymentDto);
            return Status.FAILED;
        }
    }
}
