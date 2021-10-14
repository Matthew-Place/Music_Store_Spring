package com.qa.musicstore.data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ItemTest {
	Item item;

	@BeforeAll
	static void beforeAll() {
	}

	@BeforeEach
	void beforeEach() {
		item = new Item(1, "Instrument", "String", "Guitar", "Fender", "Classic", 1000, 10, null);
	}

	@Test
	void testContructors() {
		assertTrue(item instanceof Item);
	}

	@Test
	void testGetters() {
		assertEquals(1, item.getId());
		assertEquals("Instrument", item.getType());
		assertEquals("String", item.getCategory());
		assertEquals("Guitar", item.getInstrument());
		assertEquals("Fender", item.getBrand());
		assertEquals("Classic", item.getName());
		assertEquals(1000, item.getPrice());
		assertEquals(10, item.getStock());
		assertNull(item.getStore());
	}

	@Test
	void testSetters() {
		Item testitem = new Item();
		Store store = new Store(1, "Me", "Home", "000000000000", null);
		item = new Item(null, "Instrument", "String", "Guitar", "Fender", "Classic", 1000, 10, store);
		testitem.setType("Instrument");
		testitem.setCategory("String");
		testitem.setInstrument("Guitar");
		testitem.setBrand("Fender");
		testitem.setName("Classic");
		testitem.setPrice(1000);
		testitem.setStock(10);
		testitem.setStore(store);
		assertEquals(item, testitem);
	}

	@AfterEach
	void afterEach() {
	}

	@AfterAll
	static void afterAll() {
	}

}
