package com.mj.prepag.core.card.usecase;

import com.mj.prepag.core.card.entities.Card;

public interface SaveCardPort {

	Card save(Card card);
}
