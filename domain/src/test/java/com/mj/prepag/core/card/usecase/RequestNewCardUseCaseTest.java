package com.mj.prepag.core.card.usecase;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.concurrent.ExecutionException;

import org.apache.commons.lang3.ObjectUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.AdditionalAnswers;
import org.mockito.Mockito;

import br.com.mvj.prepag.domain.card.usecase.CardInserterPort;
import br.com.mvj.prepag.domain.card.usecase.CardRequesterUseCase;
import br.com.mvj.prepag.domain.card.usecase.PasswordEncoderPort;
import br.com.mvj.prepag.domain.card.usecase.RequestNewCardUseCase;
import br.com.mvj.prepag.domain.card.usecase.CardRequesterUseCase.CardRequest;

class RequestNewCardUseCaseTest {
	
	CardRequesterUseCase useCase;
	
	PasswordEncoderPort passwordEncoder = Mockito.mock(PasswordEncoderPort.class);
	CardInserterPort cardInserter = Mockito.mock(CardInserterPort.class);
	
	
	@BeforeEach
	void setUp() {
		
		when(passwordEncoder.encode(any())).thenReturn("*****");
		when(cardInserter.save(any())).then(AdditionalAnswers.returnsFirstArg());
		
		useCase = new RequestNewCardUseCase(passwordEncoder, cardInserter);
	}

	
	@Test
	void whenValidCardRequest_returnNewCard() throws InterruptedException, ExecutionException {
		
		var params = new CardRequest("Marcos", BigDecimal.TEN);
		
		var card = useCase.execute(params);
		
		var allNotNull = ObjectUtils.allNotNull(card, card.getUuid(), card.getNome(), 
				card.getNumero(), card.getCvv(), card.getValidade(), card.getSaldo(), 
				card.getSenhaPlana(), card.getSenhaCodificada());
		
		assertTrue(allNotNull);
	}
}
