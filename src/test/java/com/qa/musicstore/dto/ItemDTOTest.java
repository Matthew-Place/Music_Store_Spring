package com.qa.musicstore.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ItemDTOTest {
	ItemDTO itemDTO;

	@BeforeAll
	static void beforeAll() {
	}

	@BeforeEach
	void beforeEach() {
		itemDTO = new ItemDTO(1, "Instrument", "String", "Guitar", "Fender", "Classic", 1000, 10, 1);
	}

	@Test
	void testContructors() {
		assertTrue(itemDTO instanceof ItemDTO);
	}

	@Test
	void testGetters() {
		assertEquals(1, itemDTO.getId());
		assertEquals("Instrument", itemDTO.getType());
		assertEquals("String", itemDTO.getCategory());
		assertEquals("Guitar", itemDTO.getInstrument());
		assertEquals("Fender", itemDTO.getBrand());
		assertEquals("Classic", itemDTO.getName());
		assertEquals(1000, itemDTO.getPrice());
		assertEquals(10, itemDTO.getStock());
		assertEquals(1, itemDTO.getStoreId());

	}

	@Test
	void testSetters() {
		ItemDTO testitemDTO = new ItemDTO();
		itemDTO = new ItemDTO(null, "Instrument", "String", "Guitar", "Fender", "Classic", 1000, 10, 1);
		testitemDTO.setType("Instrument");
		testitemDTO.setCategory("String");
		testitemDTO.setInstrument("Guitar");
		testitemDTO.setBrand("Fender");
		testitemDTO.setName("Classic");
		testitemDTO.setPrice(1000);
		testitemDTO.setStock(10);
		testitemDTO.setStoreId(1);
		assertEquals(itemDTO, testitemDTO);
	}

	@AfterEach
	void afterEach() {
	}

	@AfterAll
	static void afterAll() {
	}

}
