package com.marcosvieirajr.caprepag.domain.card.usecase.validator;

import static org.apache.commons.lang3.ObjectUtils.isEmpty;
import static org.apache.commons.lang3.StringUtils.isBlank;

import java.math.BigDecimal;

import com.marcosvieirajr.caprepag.domain._base.exception.SystemException;
import com.marcosvieirajr.caprepag.domain.card.usecase.AddNewCardUseCase.Params;
import com.marcosvieirajr.caprepag.domain.card.usecase.exeptions.InvalidAddNewCardParamsException;

public final class AddNewCardValidator {
	
	private AddNewCardValidator() {}
	
	public static void validate(Params params) {
		
		if(isEmpty(params)) throw new SystemException("AddNewCard.Params não pode ser null");
		if(isBlank(params.getNome())) throw new InvalidAddNewCardParamsException("Nome não pode ser vazio");
		if(params.getSaldo().compareTo(BigDecimal.ZERO) <= 0) throw new InvalidAddNewCardParamsException("Saldo deve ser positivo");
	}
	
}
