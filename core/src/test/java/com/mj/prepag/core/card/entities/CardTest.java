package com.mj.prepag.core.card.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.mj.prepag.core.card.usecase.EncodePasswordPort;

class CardTest {
	
	Card card;
	
	EncodePasswordPort encodePasswordPort = Mockito.mock(EncodePasswordPort.class);
	
	@BeforeEach
	void setsUp() throws Exception {
		
		when(encodePasswordPort.encode(any())).thenReturn("*****");

		card = Card.builder("Marcos", BigDecimal.TEN, encodePasswordPort).build();
	}
	
	@Test
	void should2YearsExpiringDateGenerated() {
		
		assertNotNull(card.getValidade());
		var twoYearsExpiringDate = LocalDate.now().plusYears(2);
		assertEquals(card.getValidade(), twoYearsExpiringDate);
	}
	
	@Test 
	void should16digitsCardNumberGenerated() {
		
		assertNotNull(card.getNumero());
		assertEquals(card.getNumero().length(), 16);
	}
	
	@Test 
	void should3digitsCVVNumberGenerated() {
		
		assertNotNull(card.getCvv());
		assertEquals(card.getCvv().length(), 3);
	}
	
	@Test 
	void should4digitsPasswordGenerated() {
		
		assertNotNull(card.getSenhaPlana());
		assertEquals(card.getSenhaPlana().length(), 4);
	}
	
	@Test // TODO: revisar teste card.senhaCodificada
	void shouldencodedPasswordGenerated() {
		
		assertNotNull(card.getSenhaCodificada());
	}
	
	@Test // TODO: revisar teste card.uuid
	void shoulduuidV4IsGenerated() {
		
		assertNotNull(card.getUuid());
		assertEquals(card.getUuid().toString().length(), 36);
		assertEquals(card.getUuid().toString().substring(14, 15), "4");
	}

}
