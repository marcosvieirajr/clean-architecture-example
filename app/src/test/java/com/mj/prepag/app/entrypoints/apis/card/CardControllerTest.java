package com.mj.prepag.app.entrypoints.apis.card;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import com.jayway.jsonpath.JsonPath;
import com.mj.prepag.app.AppTests;

import br.com.mvj.prepag.domain.card.usecase.CardRequesterUseCase.CardRequest;

class CardControllerTest extends AppTests {
	
	final private String BASE_URL = "/v1/cards";
	
	@BeforeEach
	public void serUp() {
	}
	
	@Test
	@Disabled
	public void testRequestCard() throws Exception {
		this.mvc.perform(get(BASE_URL))
			.andExpect(status().isOk());
//			.andExpect(content().string("Hello Gradle!"));
	}
	
	@Test
	public void testeCreateCardController() throws Exception {
		
		final var redirectedUrl = "http://localhost" +BASE_URL +"/";
		
		var request = new CardRequest("Hello Gradle!", BigDecimal.TEN);
		var requestJson = this.toJson(request);
		
		var result = this.mvc.perform(post(BASE_URL).content(requestJson).contentType(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isCreated())
				
				.andExpect(jsonPath("$.nome", is(request.getNome())))
				.andExpect(jsonPath("$.numero", notNullValue()))
				.andExpect(jsonPath("$.cvv", notNullValue()))
				.andExpect(header().string("Location", startsWith(redirectedUrl)))
				.andReturn()
				;
		
		var response = result.getResponse();
		var jsonFile = response.getContentAsString();
		var uuid = JsonPath.read(jsonFile, "$.uuid");
		
		assertEquals(redirectedUrl + uuid, response.getRedirectedUrl());
		
//		var headerLocation = response.getHeader("Location");
//		assertEquals(headerLocation, partLocation +uuid);
		
//		verify(todoServiceMock, times(1)).findById(1L);
//        verifyNoMoreInteractions(todoServiceMock);
	}

}
