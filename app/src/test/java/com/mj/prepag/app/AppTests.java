package com.mj.prepag.app;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.mvj.prepag.app.App;

@SpringBootTest(classes = App.class)
@AutoConfigureMockMvc
//@TestPropertySource(locations="classpath:test.yml")
//@ActiveProfiles("test")
public class AppTests {

	@Autowired
	public MockMvc mvc;

	private ObjectMapper mapper = new ObjectMapper();

	protected String toJson(Object object) throws Exception {
		return this.mapper.writeValueAsString(object);
	}

//	<T> T fromJsonResult(MvcResult result, Class<T> tClass) throws Exception {
//		return this.mapper.readValue(result.getResponse().getContentAsString(), tClass);
//	}

	@Test
	public void contextLoads() {
	}

}
