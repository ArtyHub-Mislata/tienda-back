package es.artyhub.tienda_back.domain.mapper;

import es.artyhub.tienda_back.domain.dto.PaymentDto;
import es.artyhub.tienda_back.domain.model.Payment;

public class PaymentMapper {

    public static PaymentDto fromPaymentToPaymentDto(Payment payment) {
        return new PaymentDto(
            payment.getId(),
            CardMapper.getInstance().fromCardToCardDto(payment.getCard()),
            payment.getConcept(),
            payment.getAmount(),
            payment.getStatus()
        );
    }

    public static Payment fromPaymentDtoToPayment(PaymentDto paymentDto) {
        return new Payment(
            paymentDto.getId(),
            CardMapper.getInstance().fromCardDtoToCard(paymentDto.getCardDto()),
            paymentDto.getConcept(),
            paymentDto.getAmount(),
            paymentDto.getStatus()
        );
    }
}
