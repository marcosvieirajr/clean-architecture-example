package com.mj.prepag.app;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(classes = App.class)
@AutoConfigureMockMvc
public class AppTests {

  @Autowired
  private MockMvc mvc;

  @Test
  public void helloGradle() throws Exception {
      mvc.perform(get("/"))
          .andExpect(status().isOk())
          .andExpect(content().string("Hello Gradle!"));
  }
}
