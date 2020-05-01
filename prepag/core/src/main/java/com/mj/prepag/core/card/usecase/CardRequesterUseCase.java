package com.mj.prepag.core.card.usecase;

import java.math.BigDecimal;

import com.mj.prepag.core.card.entities.Card;

import lombok.Getter;

public interface AddNewCardUseCase {
	
	public Card execute(final CardRequest cardRequest);
	
	@Getter
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
