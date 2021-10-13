package com.qa.musicstore.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
import com.qa.musicstore.repo.ItemRepo;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class ItemServiceUnitTest {
	final private Store testStore = new Store(1, "Me", "Home", "000000000000");
	final private Item item = new Item(1, "Instrument", "String", "Guitar", "Fender", "Classic", 1000, 10, testStore);
	final private ItemDTO itemDTO = new ItemDTO(1, "Instrument", "String", "Guitar", "Fender", "Classic", 1000, 10, testStore.getId());
	final private List<Item> items = new ArrayList<>(Arrays.asList(item));
	final private List<ItemDTO> itemDTOs = new ArrayList<>(Arrays.asList(itemDTO));

	@Autowired
	private ItemServiceDB service;

	@MockBean
	private ItemRepo repo;

	@Test
	void mapToDTOTest() {
		assertEquals(itemDTO, service.mapToDTO(item));
	}

	@Test
	void mapListToDTOTest() {
		assertEquals(itemDTOs, service.mapToDTO(items));
	}

	@Test
	void testCreate() {
		Mockito.when(repo.save(item)).thenReturn(item);

		assertEquals(itemDTO, service.create(item));

		Mockito.verify(repo, Mockito.times(1)).save(item);
	}

	@Test
	void testFindById() {
		Mockito.when(repo.findById(item.getId())).thenReturn(Optional.of(item));

		assertEquals(itemDTO, service.findById(item.getId()));

		Mockito.verify(repo, Mockito.times(1)).findById(item.getId());
	}

	@Test
	void testFindAll() {
		Mockito.when(repo.findAll()).thenReturn(items);

		assertEquals(itemDTOs, service.findAll());

		Mockito.verify(repo, Mockito.times(1)).findAll();
	}

	@Test
	void testFindByParameters() {
		Mockito.when(repo.findByCategoryOrTypeOrInstrumentOrBrandOrName(item.getType(), item.getCategory(), item.getInstrument(), item.getBrand(), item.getName())).thenReturn(items);

		assertEquals(itemDTOs, service.findByCategoryOrTypeOrInstrumentOrBrandOrName(item.getType(), item.getCategory(), item.getInstrument(), item.getBrand(), item.getName()));

		Mockito.verify(repo, Mockito.times(1)).findByCategoryOrTypeOrInstrumentOrBrandOrName(item.getType(), item.getCategory(), item.getInstrument(), item.getBrand(), item.getName());
	}

	@Test
	void testFindByStockGreaterThanEqual() {
		Mockito.when(repo.findByStockGreaterThanEqual(item.getStock())).thenReturn(items);

		assertEquals(itemDTOs, service.findByStockGreaterThanEqual(item.getStock()));

		Mockito.verify(repo, Mockito.times(1)).findByStockGreaterThanEqual(item.getStock());
	}

	@Test
	void testFindByStockLessThanEqual() {
		Mockito.when(repo.findByStockLessThanEqual(item.getStock())).thenReturn(items);

		assertEquals(itemDTOs, service.findByStockLessThanEqual(item.getStock()));

		Mockito.verify(repo, Mockito.times(1)).findByStockLessThanEqual(item.getStock());
	}

	@Test
	void testFindByPriceGreaterThanEqual() {
		Mockito.when(repo.findByPriceGreaterThanEqual(item.getPrice())).thenReturn(items);

		assertEquals(itemDTOs, service.findByPriceGreaterThanEqual(item.getPrice()));

		Mockito.verify(repo, Mockito.times(1)).findByPriceGreaterThanEqual(item.getPrice());
	}

	@Test
	void testFindByPriceLessThanEqual() {
		Mockito.when(repo.findByPriceLessThanEqual(item.getPrice())).thenReturn(items);

		assertEquals(itemDTOs, service.findByPriceLessThanEqual(item.getPrice()));

		Mockito.verify(repo, Mockito.times(1)).findByPriceLessThanEqual(item.getPrice());
	}


}
