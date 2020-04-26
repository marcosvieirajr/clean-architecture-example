package com.marcosvieirajr.caprepag.domain.card.usecase;

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

import com.marcosvieirajr.caprepag.domain._base.Completable;
import com.marcosvieirajr.caprepag.domain.card.entities.Card;
import com.marcosvieirajr.caprepag.domain.card.usecase.AddNewCardUseCase.Params;
import com.marcosvieirajr.caprepag.domain.card.usecase.ports.out.SaveCardPort;
import com.marcosvieirajr.caprepag.domain.ports.in.UseCaseWithParameter;
import com.marcosvieirajr.caprepag.domain.ports.out.CompletablePort;
import com.marcosvieirajr.caprepag.domain.ports.out.EncodePasswordPort;

class AddNewCardUseCaseTest {
	
	UseCaseWithParameter<AddNewCardUseCase.Params> addNewCard;
	
	CompletablePort<Card> completable;
	EncodePasswordPort encodePasswordPort = Mockito.mock(EncodePasswordPort.class);
	SaveCardPort saveCardPort = Mockito.mock(SaveCardPort.class);
	
	
	@BeforeEach
	void setUp() {
		
		when(encodePasswordPort.encode(any())).thenReturn("*****");
		when(saveCardPort.saveCard(any())).then(AdditionalAnswers.returnsFirstArg());
		
		completable =  new Completable<Card>();
		addNewCard = new AddNewCardUseCase(encodePasswordPort, saveCardPort, completable);
	}

	
	@Test
	void whenValidCardRequest_returnNewCard() throws InterruptedException, ExecutionException {
		
		var params = new Params("Marcos", BigDecimal.TEN);
		
		addNewCard.execute(params);
		var cardOptional = completable.get();
		var card = cardOptional.get();
		
		var allNotNull = ObjectUtils.allNotNull(card, card.getUuid(), card.getNome(), 
				card.getNumero(), card.getCvv(), card.getValidade(), card.getSaldo(), 
				card.getSenhaPlana(), card.getSenhaCodificada());
		
		assertTrue(allNotNull);
	}
}
