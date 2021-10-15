package com.qa.musicstore.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import com.qa.musicstore.data.Item;
import com.qa.musicstore.data.Store;
import com.qa.musicstore.dto.ItemDTO;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc // sets up the MockMVC object
@Sql(scripts = { "classpath:tables/test-schema.sql",
		"classpath:tables/test-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("test")
class ItemIntegrationTest {
	final private Store testStore = new Store(1, "Me", "Home", "000000000000");

	@Autowired
	private MockMvc mvc;

	@Autowired
	private ObjectMapper mapper;

	@Test
	void testCreate() throws Exception {
		final Item testItem = new Item(null, "Instrument", "String", "Guitar", "Fender", "Classic", 1000, 10,
				testStore);
		String testItemJSON = mapper.writeValueAsString(testItem);

		final ItemDTO savedItem = new ItemDTO(2, "Instrument", "String", "Guitar", "Fender", "Classic", 1000, 10,
				testStore.getId());
		String savedItemJSON = mapper.writeValueAsString(savedItem);

		RequestBuilder request = post("/Item/create").contentType(MediaType.APPLICATION_JSON).content(testItemJSON);

		ResultMatcher checkStatus = status().isCreated();
		ResultMatcher checkContent = content().json(savedItemJSON);

		mvc.perform(request).andExpect(checkStatus).andExpect(checkContent);
	}

	@Test
	void testUpdate() throws Exception {
		final Item testItem = new Item(1, "Instrument", "Keyboard", "Piano", "Bechstein", "Grand", 1000, 10, testStore);
		String testItemJSON = mapper.writeValueAsString(testItem);

		final ItemDTO savedItem = new ItemDTO(1, "Instrument", "Keyboard", "Piano", "Bechstein", "Grand", 1000, 10,
				testStore.getId());
		String savedItemJSON = mapper.writeValueAsString(savedItem);

		RequestBuilder request = put("/Item/update/{id}", 1).contentType(MediaType.APPLICATION_JSON)
				.content(testItemJSON);

		ResultMatcher checkStatus = status().isAccepted();
		ResultMatcher checkContent = content().json(savedItemJSON);

		mvc.perform(request).andExpect(checkStatus).andExpect(checkContent);
	}

	@Test
	void testDelete() throws Exception {
		RequestBuilder request = delete("/Item/delete/{id}", 1);

		ResultMatcher checkStatus = status().isNoContent();

		mvc.perform(request).andExpect(checkStatus);
	}

	@Test
	void testOrder() throws Exception {
		final Item testItem = new Item(1, "Instrument", "String", "Guitar", "Fender", "Classic", 1000, 9, testStore);

		RequestBuilder request = post("/Item/order/{id}", testItem.getId());

		String totalString = String.valueOf(testItem.getPrice());
		String string = "Order Successful!\n\nItems:" + "\n" + 1 + ": " + testItem.toReceipt() + "\n(from Store:"
				+ testItem.getStore().toReceipt() + ")" + "\nTotal: £"
				+ totalString.substring(0, totalString.length() - 2) + "."
				+ totalString.substring(totalString.length() - 2)
				+ "\n\nThanks for shopping at TheMusicStore.\nPlease visit again.";

		ResultMatcher checkContent = content().string(string);
		ResultMatcher checkStatus = status().isAccepted();

		mvc.perform(request).andExpect(checkStatus).andExpect(checkContent);
	}

	@Test
	void testFindById() throws Exception {
		final ItemDTO savedItem = new ItemDTO(1, "Instrument", "String", "Guitar", "Fender", "Classic", 1000, 10,
				testStore.getId());
		String savedItemJSON = mapper.writeValueAsString(List.of(savedItem));

		RequestBuilder request = get("/Item/findById/{id}", 1);

		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkContent = content().json(savedItemJSON);

		mvc.perform(request).andExpect(checkStatus).andExpect(checkContent);
	}

	@Test
	void testFindByParameters() throws Exception {
		final ItemDTO savedItem = new ItemDTO(1, "Instrument", "String", "Guitar", "Fender", "Classic", 1000, 10,
				testStore.getId());
		String savedItemJSON = mapper.writeValueAsString(List.of(savedItem));

		RequestBuilder request = get("/Item/findByParameters?" + "type=" + savedItem.getType() + "&category="
				+ savedItem.getCategory() + "&instrument=" + savedItem.getInstrument() + "&brand="
				+ savedItem.getBrand() + "&name=" + savedItem.getName());

		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkContent = content().json(savedItemJSON);

		mvc.perform(request).andExpect(checkStatus).andExpect(checkContent);
	}

	@Test
	void testFindByPriceLessThan() throws Exception {
		final ItemDTO savedItem = new ItemDTO(1, "Instrument", "String", "Guitar", "Fender", "Classic", 1000, 10,
				testStore.getId());
		String savedItemJSON = mapper.writeValueAsString(List.of(savedItem));

		RequestBuilder request = get("/Item/findByPriceLessThan/{price}", savedItem.getPrice());

		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkContent = content().json(savedItemJSON);

		mvc.perform(request).andExpect(checkStatus).andExpect(checkContent);
	}

	@Test
	void testFindByPriceGreaterThan() throws Exception {
		final ItemDTO savedItem = new ItemDTO(1, "Instrument", "String", "Guitar", "Fender", "Classic", 1000, 10,
				testStore.getId());
		String savedItemJSON = mapper.writeValueAsString(List.of(savedItem));

		RequestBuilder request = get("/Item/findByPriceGreaterThan/{price}", savedItem.getPrice());

		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkContent = content().json(savedItemJSON);

		mvc.perform(request).andExpect(checkStatus).andExpect(checkContent);
	}

	@Test
	void testFindByStockLessThan() throws Exception {
		final ItemDTO savedItem = new ItemDTO(1, "Instrument", "String", "Guitar", "Fender", "Classic", 1000, 10,
				testStore.getId());
		String savedItemJSON = mapper.writeValueAsString(List.of(savedItem));

		RequestBuilder request = get("/Item/findByStockLessThan/{stock}", savedItem.getStock());

		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkContent = content().json(savedItemJSON);

		mvc.perform(request).andExpect(checkStatus).andExpect(checkContent);
	}

	@Test
	void testFindByStockGreaterThan() throws Exception {
		final ItemDTO savedItem = new ItemDTO(1, "Instrument", "String", "Guitar", "Fender", "Classic", 1000, 10,
				testStore.getId());
		String savedItemJSON = mapper.writeValueAsString(List.of(savedItem));

		RequestBuilder request = get("/Item/findByStockGreaterThan/{stock}", savedItem.getStock());

		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkContent = content().json(savedItemJSON);

		mvc.perform(request).andExpect(checkStatus).andExpect(checkContent);
	}
}
