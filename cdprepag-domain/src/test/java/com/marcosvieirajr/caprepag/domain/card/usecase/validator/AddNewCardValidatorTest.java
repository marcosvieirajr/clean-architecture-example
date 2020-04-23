package com.marcosvieirajr.caprepag.domain.card.usecase.validator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import com.marcosvieirajr.caprepag.domain._base.exception.SystemException;
import com.marcosvieirajr.caprepag.domain.card.usecase.AddNewCardUseCase.Params;
import com.marcosvieirajr.caprepag.domain.card.usecase.exeptions.InvalidAddNewCardParamsException;

class AddNewCardValidatorTest {
	
	@Test
	void whenNullParams_throwsException() {
		
		Exception exception = assertThrows(SystemException.class, () -> AddNewCardValidator.validate(null));
		assertEquals("AddNewCard.Params não pode ser null", exception.getMessage());
	}

	@Test
	void whenEmptyName_throwsException() {
		
		Exception exception = assertThrows(InvalidAddNewCardParamsException.class, () -> {
			final var params = new Params("", BigDecimal.TEN);
			AddNewCardValidator.validate(params);
		});
		assertEquals("[01]Nome não pode ser vazio", exception.getMessage());
	}
	
	@Test
	void whenSaldoLessThanOrEqualsToZero_throwsException() {
		
		Exception exception = assertThrows(InvalidAddNewCardParamsException.class, () -> {
			final var params = new Params("Marcos", BigDecimal.ZERO);
			AddNewCardValidator.validate(params);
		});
		assertEquals("[01]Saldo deve ser positivo", exception.getMessage());
	}

}
