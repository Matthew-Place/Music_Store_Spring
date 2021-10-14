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
	void testUpdate() {
		Mockito.when(repo.findById(item.getId())).thenReturn(Optional.of(item));
		Mockito.when(repo.save(item)).thenReturn(item);

		assertEquals(itemDTO, service.update(item.getId(), item));

		Mockito.verify(repo, Mockito.times(1)).save(item);
		Mockito.verify(repo, Mockito.times(1)).findById(item.getId());
	}

	@Test
	void testDelete() {
		Mockito.when(repo.existsById(item.getId())).thenReturn(false);

		assertEquals(true, service.delete(item.getId()));

		Mockito.verify(repo, Mockito.times(1)).deleteById(item.getId());
		Mockito.verify(repo, Mockito.times(1)).existsById(item.getId());
	}

	@Test
	void testOrder() {
		List<Integer> ids = items.stream().map(n -> n.getId()).toList();
		Mockito.when(repo.findAllById(ids)).thenReturn(items);

		String totalString = String.valueOf(items.stream().map(n -> n.getPrice()).reduce((n, m) -> n + m).orElse(0));
		String string = "Order Successful!\n\nItems:" + "\n" + 1 + ": " + item.toReceipt() + "\n(from Store:"
				+ item.getStore().toReceipt() + ")" + "\nTotal: £" + totalString.substring(0, totalString.length() - 2)
				+ "." + totalString.substring(totalString.length() - 2)
				+ "\n\nThanks for shopping at TheMusicStore.\nPlease visit again.";
		assertEquals(string, service.order(Arrays.asList(item.getId())));

		Mockito.verify(repo, Mockito.times(1)).findAllById(List.of(item.getId()));
		Mockito.verify(repo, Mockito.times(1)).deleteAllById(List.of(item.getId()));
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
