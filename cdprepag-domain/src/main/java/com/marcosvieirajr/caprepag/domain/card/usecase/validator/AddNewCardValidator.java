package com.marcosvieirajr.caprepag.domain.models.validator;

import static org.apache.commons.lang3.StringUtils.isBlank;

import com.marcosvieirajr.caprepag.domain.exceptions.InvalidCardException;
import com.marcosvieirajr.caprepag.domain.models.Card;

public final class CardValidator {
	
	private CardValidator() {}
	
	public static void validate(Card card) {
		
		if(card == null) throw new InvalidCardException("PreCard não pode ser null");
		if(card.getUuid() == null) throw new InvalidCardException("UUID não pode ser null");
		if(isBlank(card.getNome())) throw new InvalidCardException("Nome não pode ser vazio");
		if(card.getSaldo().doubleValue() <= 0) throw new InvalidCardException("Saldo deve ser positivo");
	}
}
