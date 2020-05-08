package br.com.mvj.prepag.domain.card.usecase;

import br.com.mvj.prepag.domain.card.entities.Card;

public interface CardInserterPort {

	Card save(Card card);
}
