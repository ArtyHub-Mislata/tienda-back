package es.artyhub.tienda_back.domain.mapper;

import es.artyhub.tienda_back.domain.dto.PaymentDto;
import es.artyhub.tienda_back.domain.exception.BusinessException;
import es.artyhub.tienda_back.domain.model.Payment;

public class PaymentMapper {

    private static PaymentMapper instance;

    private PaymentMapper() {
    }

    public static PaymentMapper getInstance() {
        if (instance == null) {
            instance = new PaymentMapper();
        }
        return instance;
    }

    public PaymentDto fromPaymentToPaymentDto(Payment payment) {
        if (payment == null) {
            throw new BusinessException("Payment cannot be null");
        }
        return new PaymentDto(
            payment.getId(),
            CardMapper.getInstance().fromCardToCardDto(payment.getCard()),
            payment.getConcept(),
            payment.getAmount(),
            payment.getStatus()
        );
    }

    public Payment fromPaymentDtoToPayment(PaymentDto paymentDto) {
        if (paymentDto == null) {
            throw new BusinessException("PaymentDto cannot be null");
        }
        return new Payment(
            paymentDto.getId(),
            CardMapper.getInstance().fromCardDtoToCard(paymentDto.getCardDto()),
            paymentDto.getConcept(),
            paymentDto.getAmount(),
            paymentDto.getStatus()
        );
    }
}
