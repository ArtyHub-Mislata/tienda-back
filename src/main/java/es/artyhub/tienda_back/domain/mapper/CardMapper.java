package es.artyhub.tienda_back.domain.mapper;

import es.artyhub.tienda_back.domain.dto.CardDto;
import es.artyhub.tienda_back.domain.model.Card;

public class CardMapper {
    private static CardMapper instance;

    public CardMapper() {
    }

    public static CardMapper getInstance() {
        if (instance == null) {
            instance = new CardMapper();
        }
        return instance;
    }

    public Card fromCardDtoToCard(CardDto cardDto) {
        if (cardDto == null) {
            return null;
        }
        return new Card(
            cardDto.getnTarget(),
            cardDto.getDateExpiration(),
            cardDto.getCvv(),
            cardDto.getHolderName()
        );
    }

    public CardDto fromCardToCardDto(Card card) {
        if (card == null) {
            return null;
        }
        return new CardDto(
            card.getnTarget(),
            card.getDateExpiration(),
            card.getCvv(),
            card.getHolderName()
        );
    }
}
