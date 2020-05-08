
package br.com.mvj.prepag.app.entrypoints.apis.card;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.mvj.prepag.app.dataproviders.CardEntity;
import br.com.mvj.prepag.app.dataproviders.database.CardRepository;
import br.com.mvj.prepag.domain.card.entities.Card;
import br.com.mvj.prepag.domain.card.usecase.CardRequesterUseCase;
import br.com.mvj.prepag.domain.card.usecase.CardRequesterUseCase.CardRequest;

@RestController
@RequestMapping("/v1/cards")
public class CardController {

	private CardRequesterUseCase cardRequesterUseCase;

	private CardRepository repository;

	@Autowired
	public CardController(CardRequesterUseCase cardRequesterUseCase, CardRepository repository) {
		this.cardRequesterUseCase = cardRequesterUseCase;
		this.repository = repository;
	}

//	@GetMapping()
//	public CardWeb requestCard(CardRequest request) {
//		var cardRequest = new RequestNewCardUseCase.CardRequest("Hello Gradle!", BigDecimal.TEN);
//		var newCard = addNewCardUseCase.execute(cardRequest);
//		return new CardWeb(newCard);
//	}
	@PostMapping
	public ResponseEntity<CardWeb> post(@RequestBody CardRequest request, UriComponentsBuilder uriComponentsBuilder) {

		var created = cardRequesterUseCase.execute(request);
		var body = new CardWeb(created);

		UriComponents uriComponents = uriComponentsBuilder.path("/v1/cards/{id}").buildAndExpand(body.getUuid());
		var location = uriComponents.toUri();

		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.put("Location", List.of(location.toString()));

		repository.save(new CardEntity(created));

//        return ResponseEntity.created(location).build();
		return new ResponseEntity<CardWeb>(body, headers, HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<CardEntity>> get() {
		List<CardEntity> cards = repository.findAll();
		return ResponseEntity.ok(cards);
	}

	@GetMapping("/{id}")
	public ResponseEntity<CardEntity> get(@PathVariable UUID id) {
		Optional<CardEntity> card = repository.findById(id);
		if (card.isPresent()) return ResponseEntity.ok(card.get());

		return ResponseEntity.notFound().build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<CardEntity> put(@PathVariable UUID id, @Valid @RequestBody CardEntity request) {
		
		if (!repository.existsById(id)) return ResponseEntity.notFound().build();
		
//		var card = new CardEntity(request);
		request.setUuid(id);
		
		request = repository.save(request);
		return ResponseEntity.ok(request);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable UUID id) {
		if(!repository.existsById(id)) return ResponseEntity.notFound().build();
		repository.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}
