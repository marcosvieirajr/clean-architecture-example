package br.com.mvj.prepag.app.dataproviders.database;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.inject.Named;

import br.com.mvj.prepag.domain.card.entities.Card;
import br.com.mvj.prepag.domain.card.usecase.CardInserterPort;

@Named
//@NoArgsConstructor
public class InMemoryCardRepository implements CardInserterPort {

	private final Map<UUID, Card> inMemoryDb = new HashMap<>();

	@Override
	public Card save(Card card) {
		inMemoryDb.put(card.getUuid(), card);
		return card;
	}

//	@Override
//	public Optional<Card> findById(String id) {
//		return Optional.ofNullable(inMemoryDb.get(id));
//	}
//
//	@Override
//	public Optional<Card> findByEmail(String email) {
//		return inMemoryDb.values().stream()
//				.filter(card -> card.getEmail().equals(email))
//				.findAny();
//	}
//
//	@Override
//	public List<Card> findAllCards() {
//		return new ArrayList<>(inMemoryDb.values());
//	}

}