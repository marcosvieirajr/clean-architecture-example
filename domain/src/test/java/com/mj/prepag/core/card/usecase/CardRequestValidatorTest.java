package com.mj.prepag.core.card.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import br.com.mvj.prepag.domain._base.exception.SystemException;
import br.com.mvj.prepag.domain.card.usecase.CardRequestValidator;
import br.com.mvj.prepag.domain.card.usecase.InvalidCardRequestException;
import br.com.mvj.prepag.domain.card.usecase.CardRequesterUseCase.CardRequest;

class CardRequestValidatorTest {
	
	@Test
	void whenNullParams_throwsException() {
		
		Exception exception = assertThrows(SystemException.class, () -> CardRequestValidator.validate(null));
		assertEquals("AddNewCard.Params não pode ser null", exception.getMessage());
	}

	@Test
	void whenEmptyName_throwsException() {
		
		Exception exception = assertThrows(InvalidCardRequestException.class, () -> {
			final var params = new CardRequest("", BigDecimal.TEN);
			CardRequestValidator.validate(params);
		});
		assertEquals("[01]Nome não pode ser vazio", exception.getMessage());
	}
	
	@Test
	void whenSaldoLessThanOrEqualsToZero_throwsException() {
		
		Exception exception = assertThrows(InvalidCardRequestException.class, () -> {
			final var params = new CardRequest("Marcos", BigDecimal.ZERO);
			CardRequestValidator.validate(params);
		});
		assertEquals("[01]Saldo deve ser positivo", exception.getMessage());
	}

}
