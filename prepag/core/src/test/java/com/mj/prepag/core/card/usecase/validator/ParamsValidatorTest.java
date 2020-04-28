package com.mj.prepag.core.card.usecase.validator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import com.mj.prepag.core._base.exception.SystemException;
import com.mj.prepag.core.card.usecase.AddNewCardUseCase.CardRequest;
import com.mj.prepag.core.card.usecase.CardRequestValidator;
import com.mj.prepag.core.card.usecase.InvalidCardRequestException;

class ParamsValidatorTest {
	
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
