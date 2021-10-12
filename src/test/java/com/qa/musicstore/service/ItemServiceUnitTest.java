package com.qa.musicstore.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.qa.musicstore.data.Item;
import com.qa.musicstore.data.Store;
import com.qa.musicstore.dto.ItemDTO;
import com.qa.musicstore.repo.ItemRepo;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class ItemServiceUnitTest {
	final Store testStore = new Store(1, "Me", "Home", "000000000000");

	@Autowired
	private ItemServiceDB service;

	@MockBean
	private ItemRepo repo;

	@Test
	void mapToDTOTest() {
		final Item item = new Item(1, "Instrument", "String", "Guitar", "Fender", "Classic", 1000, 10, testStore);
		final ItemDTO itemDTO = new ItemDTO(1, "Instrument", "String", "Guitar", "Fender", "Classic", 1000, 10);
		assertEquals(itemDTO, service.mapToDTO(item));
	}

	@Test
	void mapListToDTOTest() {
		final List<Item> items = new ArrayList<>(Arrays.asList(new Item(1, "Instrument", "String", "Guitar", "Fender", "Classic", 1000, 10, testStore)));
		final List<ItemDTO> itemDTOs = new ArrayList<>(Arrays.asList(new ItemDTO(1, "Instrument", "String", "Guitar", "Fender", "Classic", 1000, 10)));
		assertEquals(itemDTOs, service.mapToDTO(items));
	}

	@Test
	void testCreate() {
		final Item item = new Item(1, "Instrument", "String", "Guitar", "Fender", "Classic", 1000, 10, testStore);
		final ItemDTO itemDTO = new ItemDTO(1, "Instrument", "String", "Guitar", "Fender", "Classic", 1000, 10);

		Mockito.when(repo.save(item)).thenReturn(item);

		assertEquals(itemDTO, service.create(item));

		Mockito.verify(repo, Mockito.times(1)).save(item);
	}
}
