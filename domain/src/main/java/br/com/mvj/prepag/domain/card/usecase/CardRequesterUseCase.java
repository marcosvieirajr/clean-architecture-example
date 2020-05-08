package br.com.mvj.prepag.domain.card.usecase;

import java.math.BigDecimal;

import br.com.mvj.prepag.domain.card.entities.Card;
import lombok.Getter;
import lombok.NoArgsConstructor;

public interface CardRequesterUseCase {
	
	public Card execute(final CardRequest cardRequest);
	
	@Getter
	@NoArgsConstructor
	public static class CardRequest {
		
		private String nome;
		private BigDecimal saldo;
		
		public CardRequest(String nome, BigDecimal saldo) {
			super();
			this.nome = nome;
			this.saldo = saldo;
		}
	}
}
