package com.example.auth;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import java.util.ArrayList;

@SpringBootTest
@AutoConfigureMockMvc

class AuthApplicationTests {


	@Autowired
	private MockMvc mockMvc;
	@Test
	public void testSignUp() throws Exception {
		this.mockMvc.perform(get("/signup")).andDo(print()).andExpect(status().isOk());
	}
	@Test
	public void testLogIn() throws Exception {
		this.mockMvc.perform(get("/login")).andDo(print()).andExpect(status().isOk());
	}
	@Test
	public void testHomePage() throws Exception {
		this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk());
	}

}
