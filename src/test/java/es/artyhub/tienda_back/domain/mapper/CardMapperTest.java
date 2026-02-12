package es.artyhub.tienda_back.domain.mapper;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import es.artyhub.tienda_back.domain.dto.CardDto;
import es.artyhub.tienda_back.domain.exception.BusinessException;
import es.artyhub.tienda_back.domain.model.Card;

public class CardMapperTest {
    
    @Nested
    @DisplayName("Test fromCardToCardDto")
    class FromCardToCardDtoTest {

        @Test
        @DisplayName("Test fromCardToCardDto with null Card should throw exception")
        void testFromCardToCardDto_NullInput() {
            assertThrows(BusinessException.class, () -> CardMapper.getInstance().fromCardToCardDto(null));
        }

        @Test
        @DisplayName("Test fromCardToCardDto with valid Card should return CardDto")
        void testFromCardToCardDto_ValidInput() {
            Card card = new Card(
                    "1234567890123456",
                    "12/25",
                    "123",
                    "John Doe"
            );

            CardDto cardDto = CardMapper.getInstance().fromCardToCardDto(card);

            assertAll(
                    () -> assertNotNull(cardDto),
                    () -> assertEquals("1234567890123456", cardDto.getnTarget()),
                    () -> assertEquals("12/25", cardDto.getDateExpiration()),
                    () -> assertEquals("123", cardDto.getCvv()),
                    () -> assertEquals("John Doe", cardDto.getHolderName()));
        }
    }

    @Nested
    @DisplayName("Test fromCardDtoToCard")
    class FromCardDtoToCardTest {

        @Test
        @DisplayName("Test fromCardDtoToCard with null CardDto should throw exception")
        void testFromCardDtoToCard_NullInput() {
            assertThrows(BusinessException.class, () -> CardMapper.getInstance().fromCardDtoToCard(null));
        }

        @Test
        @DisplayName("Test fromCardDtoToCard with valid CardDto should return Card")
        void testFromCardDtoToCard_ValidInput() {
            CardDto cardDto = new CardDto(
                    "1234567890123456",
                    "12/25",
                    "123",
                    "John Doe"
            );

            Card card = CardMapper.getInstance().fromCardDtoToCard(cardDto);

            assertAll(
                    () -> assertNotNull(card),
                    () -> assertEquals("1234567890123456", card.getnTarget()),
                    () -> assertEquals("12/25", card.getDateExpiration()),
                    () -> assertEquals("123", card.getCvv()),
                    () -> assertEquals("John Doe", card.getHolderName()));
        }
    }
}