package com.marcosvieirajr.caprepag.domain.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.marcosvieirajr.caprepag.domain.gatewais.PasswordEncoder;
import com.marcosvieirajr.caprepag.domain.models.Card;

class CardTest {
	
	Card card;
	
	PasswordEncoder passwordEncoder = Mockito.mock(PasswordEncoder.class);
	
	@BeforeEach
	void setsUp() throws Exception {
		
		when(passwordEncoder.encode(any())).thenReturn("*****");

		card = Card.builder(UUID.randomUUID(), passwordEncoder)
				.nome("Marcos")
				.saldo(BigDecimal.TEN)
				.build();
	}
	
	@Test
	void whenValidPreCard_2YearsExpiringDateGenerated() {
		
		assertNotNull(card.getValidade());
		var twoYearsExpiringDate = LocalDate.now().plusYears(2);
		assertEquals(card.getValidade(), twoYearsExpiringDate);
	}
	
	@Test 
	void whenValidPreCard_16digitsCardNumberGenerated() {
		
		assertNotNull(card.getNumero());
		assertEquals(card.getNumero().length(), 16);
	}
	
	@Test 
	void whenValidPreCard_3digitsCVVNumberGenerated() {
		
		assertNotNull(card.getCvv());
		assertEquals(card.getCvv().length(), 3);
	}
	
	@Test 
	void whenValidPreCard_4digitsPasswordGenerated() {
		
		assertNotNull(card.getSenhaPlana());
		assertEquals(card.getSenhaPlana().length(), 4);
	}
	
	@Test // TODO: revisar teste card.senhaCodificada
	void whenValidPreCard_encodedPasswordGenerated() {
		
		assertNotNull(card.getSenhaCodificada());
	}
	
	@Test // TODO: revisar teste card.uuid
	void whenValidPreCard_uuidV4IsGenerated() {
		
		assertNotNull(card.getUuid());
		assertEquals(card.getUuid().toString().length(), 36);
		assertEquals(card.getUuid().toString().substring(14, 15), "4");
	}
}
