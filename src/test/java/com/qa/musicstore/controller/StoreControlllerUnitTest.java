package com.qa.musicstore.controller;

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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import com.qa.musicstore.data.Item;
import com.qa.musicstore.data.Store;
import com.qa.musicstore.dto.ItemDTO;
import com.qa.musicstore.dto.StoreDTO;
import com.qa.musicstore.service.StoreServiceDB;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class StoreControlllerUnitTest {
	final Item item = new Item(1, "Instrument", "String", "Guitar", "Fender", "Classic", 1000, 10, null);
	final List<Item> items = new ArrayList<>(Arrays.asList(item));
	final Store store = new Store(1, "Me", "Home", "000000000000", items);
	final ItemDTO itemDTO = new ItemDTO(1, "Instrument", "String", "Guitar", "Fender", "Classic", 1000, 10,
			store.getId());
	final List<ItemDTO> itemDTOs = new ArrayList<>(Arrays.asList(itemDTO));
	final StoreDTO storeDTO = new StoreDTO(1, "Me", "Home", "000000000000", itemDTOs);
	final List<Store> stores = new ArrayList<>(Arrays.asList(store));
	final List<StoreDTO> storeDTOs = new ArrayList<>(Arrays.asList(storeDTO));

	@Autowired
	private StoreController controller;

	@MockBean
	private StoreServiceDB service;

	@Test
	void testCreate() {
		Mockito.when(service.create(store)).thenReturn(storeDTO);

		assertEquals(new ResponseEntity<>(storeDTO, HttpStatus.CREATED), controller.create(store));

		Mockito.verify(service, Mockito.times(1)).create(store);
	}

	@Test
	void testUpdate() {
		Mockito.when(service.update(store.getId(), store)).thenReturn(storeDTO);

		assertEquals(new ResponseEntity<>(storeDTO, HttpStatus.ACCEPTED), controller.update(store.getId(), store));

		Mockito.verify(service, Mockito.times(1)).update(store.getId(), store);
	}

	@Test
	void testDelete() {
		Mockito.when(service.delete(store.getId())).thenReturn(true);

		assertEquals(new ResponseEntity<>(HttpStatus.NO_CONTENT), controller.delete(store.getId()));

		Mockito.verify(service, Mockito.times(1)).delete(store.getId());
	}

	@Test
	void testFindById() {
		Mockito.when(service.findById(store.getId())).thenReturn(storeDTO);

		assertEquals(storeDTO, controller.findById(store.getId()));

		Mockito.verify(service, Mockito.times(1)).findById(store.getId());
	}

	@Test
	void testFindAll() {
		Mockito.when(service.findAll()).thenReturn(storeDTOs);

		assertEquals(storeDTOs, controller.findAll());

		Mockito.verify(service, Mockito.times(1)).findAll();
	}

	@Test
	void findByParameters() {
		Mockito.when(service.findByManagerOrAddressOrContactNumber(store.getManager(), store.getAddress(),
				store.getContactNumber())).thenReturn(storeDTOs);

		assertEquals(storeDTOs,
				controller.findByParams(store.getManager(), store.getAddress(), store.getContactNumber()));

		Mockito.verify(service, Mockito.times(1)).findByManagerOrAddressOrContactNumber(store.getManager(),
				store.getAddress(), store.getContactNumber());

	}
}
