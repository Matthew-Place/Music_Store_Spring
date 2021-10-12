package com.qa.musicstore.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.musicstore.data.Item;
import com.qa.musicstore.data.Store;
import com.qa.musicstore.dto.ItemDTO;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc // sets up the MockMVC object
@Sql(scripts = { "classpath:tables/test-schema.sql",
		"classpath:tables/test-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("test")
class ItemIntegrationTest {
	final Store testStore = new Store(1, "Me", "Home", "000000000000");

	@Autowired
	private MockMvc mvc;

	@Autowired
	private ObjectMapper mapper;

	@Test
	void testCreate() throws Exception {
		final Item testItem = new Item(null, "Instrument", "String", "Guitar", "Fender", "Classic", 1000, 10,
				testStore);
		String testItemJSON = mapper.writeValueAsString(testItem);

		// Unsure why this expects 2 as the store ID
		final ItemDTO savedItem = new ItemDTO(2, "Instrument", "String", "Guitar", "Fender", "Classic", 1000, 10,
				testStore.getId() + 1, testStore.getManager(), testStore.getAddress(), testStore.getContactNumber());
		String savedItemJSON = mapper.writeValueAsString(savedItem);

		RequestBuilder request = post("/Item/create").contentType(MediaType.APPLICATION_JSON).content(testItemJSON);

		ResultMatcher checkStatus = status().isCreated();
		ResultMatcher checkContent = content().json(savedItemJSON);

		mvc.perform(request).andExpect(checkStatus).andExpect(checkContent);
	}
}
