package com.mj.prepag.app.entrypoints.apis;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mj.prepag.core.card.usecase.AddNewCardUseCase;
import com.mj.prepag.core.card.usecase.AddNewCardUseCaseImpl;

@RestController("/")
public class CardController {
	
	AddNewCardUseCase addNewCardUseCase;
	
	@Autowired
	public CardController(AddNewCardUseCase addNewCardUseCase) {
		this.addNewCardUseCase = addNewCardUseCase;
	}

    @GetMapping
    public String helloGradle() {
    	var cardRequest = new AddNewCardUseCaseImpl.CardRequest("Hello Gradle!", BigDecimal.TEN);
    	var newCard = addNewCardUseCase.execute(cardRequest);
        return newCard.getNome();
    }

}
