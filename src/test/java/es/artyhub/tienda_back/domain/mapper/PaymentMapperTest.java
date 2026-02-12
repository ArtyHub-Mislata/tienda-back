package es.artyhub.tienda_back.domain.mapper;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import es.artyhub.tienda_back.domain.dto.CardDto;
import es.artyhub.tienda_back.domain.dto.PaymentDto;
import es.artyhub.tienda_back.domain.enums.Status;
import es.artyhub.tienda_back.domain.exception.BusinessException;
import es.artyhub.tienda_back.domain.model.Card;
import es.artyhub.tienda_back.domain.model.Payment;

public class PaymentMapperTest {
    
    @Nested
    @DisplayName("Test fromPaymentToPaymentDto")
    class FromPaymentToPaymentDtoTest {

        @Test
        @DisplayName("Test fromPaymentToPaymentDto with null Payment should throw exception")
        void testFromPaymentToPaymentDto_NullInput() {
            assertThrows(BusinessException.class, () -> PaymentMapper.getInstance().fromPaymentToPaymentDto(null));
        }

        @Test
        @DisplayName("Test fromPaymentToPaymentDto with valid Payment should return PaymentDto")
        void testFromPaymentToPaymentDto_ValidInput() {
            Card card = new Card(

                    "1234567890123456",
                    "2025-12",
                    "123",
                    "John Doe"
            );
            Payment payment = new Payment(
                    1L,
                    card,
                    "cocept",
                    BigDecimal.valueOf(10.0),
                    Status.PENDING
            );

            PaymentDto paymentDto = PaymentMapper.getInstance().fromPaymentToPaymentDto(payment);

            assertAll(
                    () -> assertNotNull(paymentDto),
                    () -> assertEquals(1L, paymentDto.getId()),
                    () -> assertEquals(BigDecimal.valueOf(10.0), paymentDto.getAmount()),
                    () -> assertEquals(Status.PENDING, paymentDto.getStatus())
            );
        }
    }

    @Nested
    @DisplayName("Test fromPaymentDtoToPayment")
    class FromPaymentDtoToPaymentTest {

        @Test
        @DisplayName("Test fromPaymentDtoToPayment with null PaymentDto should throw exception")
        void testFromPaymentDtoToPayment_NullInput() {
            assertThrows(BusinessException.class, () -> PaymentMapper.getInstance().fromPaymentDtoToPayment(null));
        }

        @Test
        @DisplayName("Test fromPaymentDtoToPayment with valid PaymentDto should return Payment")
        void testFromPaymentDtoToPayment_ValidInput() {
            CardDto cardDto = new CardDto(

                    "1234567890123456",
                    "2025-12",
                    "123",
                    "John Doe"
            );
            PaymentDto paymentDto = new PaymentDto(
                    1L,
                    cardDto,
                    "Concept",
                    BigDecimal.valueOf(10.0),
                    Status.PENDING
            );

            Payment payment = PaymentMapper.getInstance().fromPaymentDtoToPayment(paymentDto);

            assertAll(
                    () -> assertNotNull(payment),
                    () -> assertEquals(1L, payment.getId()),
                    () -> assertEquals(BigDecimal.valueOf(10.0), payment.getAmount()),
                    () -> assertEquals(Status.PENDING, payment.getStatus())
            );
        }
    }
}
