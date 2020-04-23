package com.marcosvieirajr.caprepag.domain.card.usecase;

import java.math.BigDecimal;

import com.marcosvieirajr.caprepag.domain.card.entities.Card;
import com.marcosvieirajr.caprepag.domain.card.usecase.AddNewCardUseCase.Params;
import com.marcosvieirajr.caprepag.domain.card.usecase.ports.out.SaveCardPort;
import com.marcosvieirajr.caprepag.domain.card.usecase.validator.AddNewCardValidator;
import com.marcosvieirajr.caprepag.domain.ports.in.UseCaseWithParameter;
import com.marcosvieirajr.caprepag.domain.ports.out.CompletablePort;
import com.marcosvieirajr.caprepag.domain.ports.out.EncodePasswordPort;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class AddNewCardUseCase implements UseCaseWithParameter<Params> {

	private final EncodePasswordPort encodePassword;
	private final SaveCardPort saveCardPort;
	private final CompletablePort<Card> completable;

	@Override
	public void execute(final Params request) {

		AddNewCardValidator.validate(request);
		
		var newCard = Card.builder(
				request.getNome(), 
				request.getSaldo(), 
				encodePassword)
				.build();
		
		saveCardPort.saveCard(newCard);
		completable.complete(newCard);
	}
	
	
	@Getter
	public static class Params {
		
		private String nome;
		private BigDecimal saldo;
		
		public Params(String nome, BigDecimal saldo) {
			super();
			this.nome = nome;
			this.saldo = saldo;
		}
		
	}

}
