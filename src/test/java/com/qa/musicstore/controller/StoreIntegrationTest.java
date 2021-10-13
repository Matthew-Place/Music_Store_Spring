package com.qa.musicstore.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
import com.qa.musicstore.data.Store;
import com.qa.musicstore.dto.ItemDTO;
import com.qa.musicstore.dto.StoreDTO;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc // sets up the MockMVC object
@Sql(scripts = { "classpath:tables/test-schema.sql",
		"classpath:tables/test-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("test")
class StoreIntegrationTest {
	final private Store testStore = new Store(null, "Me", "Home", "000000000000");
	final private ItemDTO itemDTO = new ItemDTO(1, "Instrument", "String", "Guitar", "Fender", "Classic", 1000, 10, 1);
	final private List<ItemDTO> itemDTOs = new ArrayList<>(Arrays.asList(itemDTO));

	@Autowired
	private MockMvc mvc;

	@Autowired
	private ObjectMapper mapper;

	@Test
	void testCreate() throws Exception {
		String testStoreJSON = mapper.writeValueAsString(testStore);

		final StoreDTO savedStore = new StoreDTO(2, "Me", "Home", "000000000000");
		String savedStoreJSON = mapper.writeValueAsString(savedStore);

		RequestBuilder request = post("/Store/create").contentType(MediaType.APPLICATION_JSON).content(testStoreJSON);

		ResultMatcher checkStatus = status().isCreated();
		ResultMatcher checkContent = content().json(savedStoreJSON);

		mvc.perform(request).andExpect(checkStatus).andExpect(checkContent);
	}

	@Test
	void testFindById() throws Exception {
		final StoreDTO savedStore = new StoreDTO(1, "Me", "Home", "000000000000", itemDTOs);
		String savedStoreJSON = mapper.writeValueAsString(savedStore);

		RequestBuilder request = get("/Store/findById/{id}", savedStore.getId());

		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkContent = content().json(savedStoreJSON);

		mvc.perform(request).andExpect(checkStatus).andExpect(checkContent);
	}

	@Test
	void testFindAll() throws Exception {
		final StoreDTO savedStore = new StoreDTO(1, "Me", "Home", "000000000000", itemDTOs);
		String savedStoreJSON = mapper.writeValueAsString(List.of(savedStore));

		RequestBuilder request = get("/Store/findAll");

		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkContent = content().json(savedStoreJSON);

		mvc.perform(request).andExpect(checkStatus).andExpect(checkContent);
	}

	@Test
	void testFindByParameters() throws Exception {
		final StoreDTO savedStore = new StoreDTO(1, "Me", "Home", "000000000000", itemDTOs);
		String savedStoreJSON = mapper.writeValueAsString(List.of(savedStore));

		RequestBuilder request = get("/Store/findByParameters?" + "manager=" + savedStore.getManager() + "&address="
				+ savedStore.getAddress() + "&contactNumber=" + savedStore.getContactNumber());

		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkContent = content().json(savedStoreJSON);

		mvc.perform(request).andExpect(checkStatus).andExpect(checkContent);
	}
}
