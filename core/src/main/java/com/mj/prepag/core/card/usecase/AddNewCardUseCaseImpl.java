package com.mj.prepag.core.card.usecase;

import javax.inject.Named;

import com.mj.prepag.core.card.entities.Card;

import lombok.AllArgsConstructor;

@Named
@AllArgsConstructor
public class AddNewCardUseCaseImpl implements AddNewCardUseCase {

	private final EncodePasswordPort encodePassword;
	private final SaveCardPort saveCardPort;

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
