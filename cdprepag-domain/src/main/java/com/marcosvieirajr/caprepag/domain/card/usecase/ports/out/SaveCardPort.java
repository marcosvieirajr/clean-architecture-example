package com.marcosvieirajr.caprepag.domain.card.usecase.ports.out;

import com.marcosvieirajr.caprepag.domain.card.entities.Card;

public interface SaveCardPort {

	Card saveCard(Card card);
}
