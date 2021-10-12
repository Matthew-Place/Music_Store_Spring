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

import com.qa.musicstore.data.Store;
import com.qa.musicstore.dto.StoreDTO;
import com.qa.musicstore.repo.StoreRepo;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class StoreServiceUnitTest {

	@Autowired
	private StoreServiceDB service;

	@MockBean
	private StoreRepo repo;

	@Test
	void mapToDTOTest() {
		final Store store = new Store(1, "Me", "Home", "000000000000");
		final StoreDTO storeDTO = new StoreDTO(1, "Me", "Home", "000000000000");
		assertEquals(storeDTO, service.mapToDTO(store));
	}

	@Test
	void mapListToDTOTest() {
		final List<Store> stores = new ArrayList<>(Arrays.asList(new Store(1, "Me", "Home", "000000000000")));
		final List<StoreDTO> storeDTOs = new ArrayList<>(Arrays.asList(new StoreDTO(1, "Me", "Home", "000000000000")));
		assertEquals(storeDTOs, service.mapToDTO(stores));
	}

	@Test
	void testCreate() {
		final Store store = new Store(1, "Me", "Home", "000000000000");
		final StoreDTO storeDTO = new StoreDTO(1, "Me", "Home", "000000000000");

		Mockito.when(repo.save(store)).thenReturn(store);

		assertEquals(storeDTO, service.create(store));

		Mockito.verify(repo, Mockito.times(1)).save(store);
	}
}
