package br.com.mvj.prepag.domain.card.usecase;

import static org.apache.commons.lang3.ObjectUtils.isEmpty;
import static org.apache.commons.lang3.StringUtils.isBlank;

import java.math.BigDecimal;

import br.com.mvj.prepag.domain._base.exception.SystemException;
import br.com.mvj.prepag.domain.card.usecase.CardRequesterUseCase.CardRequest;

public final class CardRequestValidator {
	
	private CardRequestValidator() {}
	
	public static void validate(CardRequest cardRequest) {
		
		if(isEmpty(cardRequest)) throw new SystemException("AddNewCard.Params não pode ser null");
		if(isBlank(cardRequest.getNome())) throw new InvalidCardRequestException("Nome não pode ser vazio");
		if(cardRequest.getSaldo().compareTo(BigDecimal.ZERO) <= 0) throw new InvalidCardRequestException("Saldo deve ser positivo");
	}
	
}
