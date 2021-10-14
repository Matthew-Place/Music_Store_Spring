package com.qa.musicstore.dto;

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
class StoreDTOTest {
	final ItemDTO itemDTO = new ItemDTO(1, "Instrument", "String", "Guitar", "Fender", "Classic", 1000, 10, null);
	final List<ItemDTO> itemDTOs = new ArrayList<>(Arrays.asList(itemDTO));
	StoreDTO storeDTO;

	@BeforeAll
	static void beforeAll() {
	}

	@BeforeEach
	void beforeEach() {
		storeDTO = new StoreDTO(1, "Me", "Home", "000000000000", itemDTOs);
	}

	@Test
	void testContructors() {
		assertTrue(storeDTO instanceof StoreDTO);
		storeDTO = new StoreDTO(1, "Me", "Home", "000000000000");
		assertTrue(storeDTO instanceof StoreDTO);
	}

	@Test
	void testGetters() {
		assertEquals(1, storeDTO.getId());
		assertEquals("Me", storeDTO.getManager());
		assertEquals("Home", storeDTO.getAddress());
		assertEquals("000000000000", storeDTO.getContactNumber());
		assertEquals(itemDTOs, storeDTO.getItemDTOs());
	}

	@Test
	void testSetters() {
		StoreDTO teststoreDTO = new StoreDTO();
		storeDTO = new StoreDTO(null, "Me", "Home", "000000000000", itemDTOs);
		teststoreDTO.setAddress("Home");
		teststoreDTO.setContactNumber("000000000000");
		teststoreDTO.setManager("Me");
		teststoreDTO.setItemDTOs(itemDTOs);
		assertEquals(storeDTO, teststoreDTO);
	}

	@AfterEach
	void afterEach() {
	}

	@AfterAll
	static void afterAll() {
	}

}
