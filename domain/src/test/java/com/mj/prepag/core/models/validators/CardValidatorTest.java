package com.mj.prepag.core.models.validators;
//package com.marcosvieirajr.caprepag.domain.models.validators;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//
//import java.math.BigDecimal;
//import java.util.UUID;
//
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//
//import com.marcosvieirajr.caprepag.domain.card.entities.Card;
//import com.marcosvieirajr.caprepag.domain.card.entities.validator.CardRequestValidator;
//import com.marcosvieirajr.caprepag.domain.exceptions.InvalidCardException;
//import com.marcosvieirajr.caprepag.domain.gatewais.PasswordEncoder;
//
//class CardValidatorTest {
//	
//	PasswordEncoder passwordEncoder = Mockito.mock(PasswordEncoder.class);
//	
//	@Test
//	void whenNullCard_throwsInvalidCardException() {
//		
//		Exception exception = assertThrows(InvalidCardException.class, () -> CardRequestValidator.validate(null));
//		assertEquals("[01]PreCard não pode ser null", exception.getMessage());
//	}
//
//	@Test
//	void whenNullUUID_throwsInvalidCardException() {
//		
//		Exception exception = assertThrows(InvalidCardException.class, () -> {
//			
//			var card = Card.builder(null, passwordEncoder)
//					.nome("Marcos")
//					.saldo(BigDecimal.TEN)
//					.build();
//			
//			CardRequestValidator.validate(card);
//		});
//		assertEquals("[01]UUID não pode ser null", exception.getMessage());
//	}
//	
//	@Test
//	void whenEmptyName_throwsInvalidCardException() {
//		
//		Exception exception = assertThrows(InvalidCardException.class, () -> {
//			
//			var card = Card.builder(UUID.randomUUID(), passwordEncoder)
//					.saldo(BigDecimal.TEN)
//					.build();
//			
//			CardRequestValidator.validate(card);
//		});
//		assertEquals("[01]Nome não pode ser vazio", exception.getMessage());
//	}
//	
//	@Test
//	void whenSaldoLessThanOrEqualsToZero_throwsInvalidCardException() {
//		
//		Exception exception = assertThrows(InvalidCardException.class, () -> {
//
//			var card = Card.builder(UUID.randomUUID(), passwordEncoder)
//					.nome("Marcos")
//					.saldo(BigDecimal.ZERO)
//					.build();
//			
//			CardRequestValidator.validate(card);
//		});
//		assertEquals("[01]Saldo deve ser positivo", exception.getMessage());
//	}
//
//}
