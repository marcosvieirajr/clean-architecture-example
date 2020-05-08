package br.com.mvj.prepag.domain.card.usecase;

import javax.inject.Named;

import br.com.mvj.prepag.domain.card.entities.Card;
import lombok.AllArgsConstructor;

@Named
@AllArgsConstructor
public class RequestNewCardUseCase implements CardRequesterUseCase {

	private final PasswordEncoderPort encodePassword;
	private final CardInserterPort saveCardPort;

	@Override
	public Card execute(final CardRequest request) {

		CardRequestValidator.validate(request);
		
		var newCard = Card.builder(
				request.getNome(), 
				request.getSaldo(), 
				encodePassword)
				.build();
		
		newCard = saveCardPort.save(newCard);
		return newCard;
	}

}
