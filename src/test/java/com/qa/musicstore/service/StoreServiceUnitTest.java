package com.qa.musicstore.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
import com.qa.musicstore.dto.StoreDTO;
import com.qa.musicstore.exceptions.StoreNotFoundException;
import com.qa.musicstore.repo.StoreRepo;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class StoreServiceUnitTest {
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
	private StoreServiceDB service;

	@MockBean
	private StoreRepo repo;

	@Test
	void mapToDTOTest() {
		assertEquals(storeDTO, service.mapToDTO(store));
	}

	@Test
	void mapListToDTOTest() {
		assertEquals(storeDTOs, service.mapToDTO(stores));
	}

	@Test
	void testCreate() {
		Mockito.when(repo.save(store)).thenReturn(store);

		assertEquals(storeDTO, service.create(store));

		Mockito.verify(repo, Mockito.times(1)).save(store);
	}

	@Test
	void testUpdateSuccess() {
		Mockito.when(repo.save(store)).thenReturn(store);
		Mockito.when(repo.findById(store.getId())).thenReturn(Optional.of(store));

		assertEquals(storeDTO, service.update(store.getId(), store));

		Mockito.verify(repo, Mockito.times(1)).findById(store.getId());
		Mockito.verify(repo, Mockito.times(1)).save(store);
	}

	@Test
	void testUpdateFail() {
		Mockito.when(repo.save(store)).thenReturn(store);
		Mockito.when(repo.findById(0)).thenThrow(StoreNotFoundException.class);

		assertThrows(StoreNotFoundException.class, () -> service.update(0, store));

		Mockito.verify(repo, Mockito.times(1)).findById(0);
		Mockito.verify(repo, Mockito.times(0)).save(store);
	}


	@Test
	void testDeleteSuccess() {
		Mockito.when(repo.existsById(store.getId())).thenReturn(false);
		Mockito.when(repo.findById(store.getId())).thenReturn(Optional.of(store));

		assertEquals(true, service.delete(Arrays.asList(store.getId())));

		Mockito.verify(repo, Mockito.times(1)).deleteAllById(List.of(store.getId()));
		Mockito.verify(repo, Mockito.times(1)).existsById(store.getId());
		Mockito.verify(repo, Mockito.times(1)).findById(store.getId());

	}

	@Test
	void testDeleteFail() {
		Mockito.when(repo.existsById(store.getId())).thenReturn(true);
		Mockito.when(repo.findById(store.getId())).thenReturn(Optional.of(store));

		assertEquals(false, service.delete(Arrays.asList(store.getId())));

		Mockito.verify(repo, Mockito.times(1)).deleteAllById(List.of(store.getId()));
		Mockito.verify(repo, Mockito.times(1)).existsById(store.getId());
		Mockito.verify(repo, Mockito.times(1)).findById(store.getId());
	}

	@Test
	void testDeleteNotFound() {
		Mockito.when(repo.existsById(0)).thenReturn(false);
		Mockito.when(repo.findById(0)).thenThrow(StoreNotFoundException.class);

		List<Integer> ids = new ArrayList<>(Arrays.asList(0));
		assertThrows(StoreNotFoundException.class, () -> service.delete(ids));

		Mockito.verify(repo, Mockito.times(0)).deleteAllById(List.of(0));
		Mockito.verify(repo, Mockito.times(0)).existsById(0);
		Mockito.verify(repo, Mockito.times(1)).findById(0);
	}

	@Test
	void testFindById() {
		Mockito.when(repo.findAllById(List.of(store.getId()))).thenReturn(stores);

		assertEquals(storeDTOs, service.findById(Arrays.asList(store.getId())));

		Mockito.verify(repo, Mockito.times(1)).findAllById(List.of(store.getId()));
	}

	@Test
	void testFindAll() {
		Mockito.when(repo.findAll()).thenReturn(stores);

		assertEquals(storeDTOs, service.findAll());

		Mockito.verify(repo, Mockito.times(1)).findAll();
	}

	@Test
	void findByParameters() {
		Mockito.when(repo.findByManagerOrAddressOrContactNumber(store.getManager(), store.getAddress(),
				store.getContactNumber())).thenReturn(stores);

		assertEquals(storeDTOs, service.findByManagerOrAddressOrContactNumber(store.getManager(), store.getAddress(),
				store.getContactNumber()));

		Mockito.verify(repo, Mockito.times(1)).findByManagerOrAddressOrContactNumber(store.getManager(),
				store.getAddress(), store.getContactNumber());

	}
}
