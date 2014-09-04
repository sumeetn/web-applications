package com.smnpuzzles.webservices.rest;

import java.nio.charset.Charset;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({ "classpath:restservices-servlet.xml" })
public class SudokuControllerTest {
	@Autowired
	WebApplicationContext wac;
	@Autowired
	MockHttpSession session;
	@Autowired
	MockHttpServletRequest request;

	private MockMvc mockMvc;

	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(
			MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	@Test
	public void testConfiguration() throws Exception {

		System.out.println(MediaType.APPLICATION_JSON);
		this.mockMvc.perform(
				get("/rest/sudoku").param("query", "1234").accept(
						MediaType.APPLICATION_JSON)).andExpect(status().isOk());

	}

	@Test
	public void testSuccessCallToAlgoUsingREST() throws Exception {

		String problem = "3,1,6,5,x,8,4,x,x,5,2,x,x,x,x,x,x,x,x,8,7,x,x,x,x,3,1,x,x,3,x,1,x,x,8,x,9,x,x,8,6,3,x,x,5,x,5,x,x,9,x,6,x,x,1,3,x,x,x,x,2,5,x,x,x,x,x,x,x,x,7,4,x,x,5,2,x,6,3,x,x";
		String solution="3,1,6,5,7,8,4,9,2,5,2,9,1,3,4,7,6,8,4,8,7,6,2,9,5,3,1,2,6,3,4,1,5,9,8,7,9,7,4,8,6,3,1,2,5,8,5,1,7,9,2,6,4,3,1,3,8,9,4,7,2,5,6,6,9,2,3,5,1,8,7,4,7,4,5,2,8,6,3,1,9";
		System.out.println(MediaType.APPLICATION_JSON);
		this.mockMvc
				.perform(
						get("/rest/sudoku").param("query", problem).accept(
								MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.TEXT_PLAIN))
				.andExpect(jsonPath("$.solution", is(solution)));

	}
	
	@Test
	public void testErrorCallToAlgoUsingREST() throws Exception {

		String problem ="x,x,9,x,2,8,7,x,x,8,x,6,x,x,4,x,x,5,x,x,3,x,x,x,x,x,4,6,x,x,x,x,x,x,x,x,x,2,x,7,1,3,4,5,x,x,x,x,x,x,x,x,x,2,3,x,x,x,x,x,5,x,x,9,x,x,4,x,x,8,x,7,x,x,1,2,5,x,3,x,x";
		String message="cannot be completed";
		System.out.println(MediaType.APPLICATION_JSON);
		this.mockMvc
				.perform(
						get("/rest/sudoku").param("query", problem).accept(
								MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.TEXT_PLAIN))
				.andExpect(jsonPath("$.error", is(message)));

	}
}
