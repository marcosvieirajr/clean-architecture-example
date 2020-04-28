package com.mj.prepag.core.card.usecase;

import static org.apache.commons.lang3.ObjectUtils.isEmpty;
import static org.apache.commons.lang3.StringUtils.isBlank;

import java.math.BigDecimal;

import com.mj.prepag.core._base.exception.SystemException;
import com.mj.prepag.core.card.usecase.AddNewCardUseCase.CardRequest;

public final class CardRequestValidator {
	
	private CardRequestValidator() {}
	
	public static void validate(CardRequest cardRequest) {
		
		if(isEmpty(cardRequest)) throw new SystemException("AddNewCard.Params não pode ser null");
		if(isBlank(cardRequest.getNome())) throw new InvalidCardRequestException("Nome não pode ser vazio");
		if(cardRequest.getSaldo().compareTo(BigDecimal.ZERO) <= 0) throw new InvalidCardRequestException("Saldo deve ser positivo");
	}
	
}
