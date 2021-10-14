package com.qa.musicstore.data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Entity;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@Entity
class StoreTest {
	final Item item = new Item(1, "Instrument", "String", "Guitar", "Fender", "Classic", 1000, 10, null);
	final List<Item> items = new ArrayList<>(Arrays.asList(item));
	Store store;

	@BeforeAll
	static void beforeAll() {
	}

	@BeforeEach
	void beforeEach() {
		store = new Store(1, "Me", "Home", "000000000000", items);
	}

	@Test
	void testContructors() {
		assertTrue(store instanceof Store);
		store = new Store(1, "Me", "Home", "000000000000");
		assertTrue(store instanceof Store);
	}

	@Test
	void testGetters() {
		assertEquals(1, store.getId());
		assertEquals("Me", store.getManager());
		assertEquals("Home", store.getAddress());
		assertEquals("000000000000", store.getContactNumber());
		assertEquals(items, store.getItems());
	}

	@Test
	void testSetters() {
		Store teststore = new Store();
		store = new Store(null, "Me", "Home", "000000000000", items);
		teststore.setAddress("Home");
		teststore.setContactNumber("000000000000");
		teststore.setManager("Me");
		teststore.setItems(items);
		assertEquals(store, teststore);
	}

	@AfterEach
	void afterEach() {
	}

	@AfterAll
	static void afterAll() {
	}

}
