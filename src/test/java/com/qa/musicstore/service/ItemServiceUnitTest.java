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
import com.qa.musicstore.exceptions.InsufficientStockException;
import com.qa.musicstore.exceptions.ItemNotFoundException;
import com.qa.musicstore.repo.ItemRepo;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class ItemServiceUnitTest {
	final private Store testStore = new Store(1, "Me", "Home", "000000000000");
	final private Item item = new Item(1, "Instrument", "String", "Guitar", "Fender", "Classic", 1000, 10, testStore);
	final private ItemDTO itemDTO = new ItemDTO(1, "Instrument", "String", "Guitar", "Fender", "Classic", 1000, 10,
			testStore.getId());
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
	void testUpdateSuccess() {
		Mockito.when(repo.findById(item.getId())).thenReturn(Optional.of(item));
		Mockito.when(repo.save(item)).thenReturn(item);

		assertEquals(itemDTO, service.update(item.getId(), item));

		Mockito.verify(repo, Mockito.times(1)).save(item);
		Mockito.verify(repo, Mockito.times(1)).findById(item.getId());
	}

	@Test
	void testUpdateFail() {
		Mockito.when(repo.findById(0)).thenThrow(ItemNotFoundException.class);
		Mockito.when(repo.save(item)).thenReturn(item);

		assertThrows(ItemNotFoundException.class, () -> service.update(0, item));
		Mockito.verify(repo, Mockito.times(0)).save(item);
		Mockito.verify(repo, Mockito.times(1)).findById(0);
	}

	@Test
	void testDeleteSuccess() {
		Mockito.when(repo.existsById(item.getId())).thenReturn(false);
		Mockito.when(repo.findById(item.getId())).thenReturn(Optional.of(item));

		assertEquals(true, service.delete(Arrays.asList(item.getId())));

		Mockito.verify(repo, Mockito.times(1)).deleteAllById(List.of(item.getId()));
		Mockito.verify(repo, Mockito.times(1)).existsById(item.getId());
		Mockito.verify(repo, Mockito.times(1)).findById(item.getId());
	}

	@Test
	void testDeleteFail() {
		Mockito.when(repo.existsById(item.getId())).thenReturn(true);
		Mockito.when(repo.findById(item.getId())).thenReturn(Optional.of(item));

		assertEquals(false, service.delete(Arrays.asList(item.getId())));

		Mockito.verify(repo, Mockito.times(1)).deleteAllById(List.of(item.getId()));
		Mockito.verify(repo, Mockito.times(1)).existsById(item.getId());
		Mockito.verify(repo, Mockito.times(1)).findById(item.getId());
	}

	@Test
	void testDeleteNotFound() {
		Mockito.when(repo.existsById(0)).thenReturn(false);
		Mockito.when(repo.findById(0)).thenThrow(ItemNotFoundException.class);

		List<Integer> ids = new ArrayList<>(Arrays.asList(0));
		assertThrows(ItemNotFoundException.class, () -> service.delete(ids));

		Mockito.verify(repo, Mockito.times(0)).deleteAllById(List.of(0));
		Mockito.verify(repo, Mockito.times(0)).existsById(0);
	}

	@Test
	void testOrderSuccess() {
		Mockito.when(repo.findAllById(List.of(item.getId()))).thenReturn(items);
		Mockito.when(repo.save(item)).thenReturn(item);

		item.setStock(item.getStock() - 1);
		String totalString = String.valueOf(item.getPrice());
		String string = "Order Successful!\n\nItems:" + "\n" + 1 + ": " + item.toReceipt() + "\n(from Store:"
				+ item.getStore().toReceipt() + ")" + "\nTotal: ??" + totalString.substring(0, totalString.length() - 2)
				+ "." + totalString.substring(totalString.length() - 2)
				+ "\n\nThanks for shopping at TheMusicStore.\nPlease visit again.";
		item.setStock(item.getStock() + 1);
		assertEquals(string, service.order(Arrays.asList(item.getId())));

		Mockito.verify(repo, Mockito.times(1)).findAllById(List.of(item.getId()));
		Mockito.verify(repo, Mockito.times(1)).save(item);
	}

	@Test
	void testOrderFail() {
		item.setStock(0);
		Mockito.when(repo.findAllById(List.of(item.getId()))).thenReturn(items);
		Mockito.when(repo.save(item)).thenReturn(item);

		List<Integer> ids = new ArrayList<>(Arrays.asList(item.getId()));
		assertThrows(InsufficientStockException.class, () -> service.order(ids));

		Mockito.verify(repo, Mockito.times(1)).findAllById(List.of(item.getId()));
		Mockito.verify(repo, Mockito.times(0)).save(item);
	}

	@Test
	void testFindById() {
		Mockito.when(repo.findAllById(List.of(item.getId()))).thenReturn(items);

		assertEquals(itemDTOs, service.findById(Arrays.asList(item.getId())));

		Mockito.verify(repo, Mockito.times(1)).findAllById(List.of(item.getId()));
	}

	@Test
	void testFindAll() {
		Mockito.when(repo.findAll()).thenReturn(items);

		assertEquals(itemDTOs, service.findAll());

		Mockito.verify(repo, Mockito.times(1)).findAll();
	}

	@Test
	void testFindByParameters() {
		Mockito.when(repo.findByCategoryOrTypeOrInstrumentOrBrandOrName(item.getType(), item.getCategory(),
				item.getInstrument(), item.getBrand(), item.getName())).thenReturn(items);

		assertEquals(itemDTOs, service.findByCategoryOrTypeOrInstrumentOrBrandOrName(item.getType(), item.getCategory(),
				item.getInstrument(), item.getBrand(), item.getName()));

		Mockito.verify(repo, Mockito.times(1)).findByCategoryOrTypeOrInstrumentOrBrandOrName(item.getType(),
				item.getCategory(), item.getInstrument(), item.getBrand(), item.getName());
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
