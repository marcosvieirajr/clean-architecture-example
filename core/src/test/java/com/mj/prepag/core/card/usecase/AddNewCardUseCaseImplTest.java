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

import com.mj.prepag.core.card.usecase.AddNewCardUseCase.CardRequest;

class AddNewCardUseCaseImplTest {
	
	AddNewCardUseCase addNewCardUseCase;
	
//	CompletablePort<Card> completable;
	EncodePasswordPort encodePasswordPort = Mockito.mock(EncodePasswordPort.class);
	SaveCardPort saveCardPort = Mockito.mock(SaveCardPort.class);
	
	
	@BeforeEach
	void setUp() {
		
		when(encodePasswordPort.encode(any())).thenReturn("*****");
		when(saveCardPort.save(any())).then(AdditionalAnswers.returnsFirstArg());
		
//		completable =  new Completable<Card>();
		addNewCardUseCase = new AddNewCardUseCaseImpl(encodePasswordPort, saveCardPort);
	}

	
	@Test
	void whenValidCardRequest_returnNewCard() throws InterruptedException, ExecutionException {
		
		var params = new CardRequest("Marcos", BigDecimal.TEN);
		
		var card = addNewCardUseCase.execute(params);
//		var cardOptional = completable.get();
//		var card = cardOptional.get();
		
		var allNotNull = ObjectUtils.allNotNull(card, card.getUuid(), card.getNome(), 
				card.getNumero(), card.getCvv(), card.getValidade(), card.getSaldo(), 
				card.getSenhaPlana(), card.getSenhaCodificada());
		
		assertTrue(allNotNull);
	}
}
