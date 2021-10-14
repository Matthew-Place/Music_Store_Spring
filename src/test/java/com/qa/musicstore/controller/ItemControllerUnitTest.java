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
import com.qa.musicstore.service.ItemServiceDB;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class ItemControllerUnitTest {
	final private Store testStore = new Store(1, "Me", "Home", "000000000000");
	final private Item item = new Item(1, "Instrument", "String", "Guitar", "Fender", "Classic", 1000, 10, testStore);
	final private ItemDTO itemDTO = new ItemDTO(1, "Instrument", "String", "Guitar", "Fender", "Classic", 1000, 10,
			testStore.getId());
	final private List<ItemDTO> itemDTOs = new ArrayList<>(Arrays.asList(itemDTO));

	@Autowired
	private ItemController controller;

	@MockBean
	private ItemServiceDB service;

	@Test
	void testCreate() {
		Mockito.when(service.create(item)).thenReturn(itemDTO);

		assertEquals(new ResponseEntity<>(itemDTO, HttpStatus.CREATED), controller.create(item));

		Mockito.verify(service, Mockito.times(1)).create(item);
	}

	@Test
	void testUpdate() {
		Mockito.when(service.update(item.getId(), item)).thenReturn(itemDTO);

		assertEquals(new ResponseEntity<>(itemDTO, HttpStatus.ACCEPTED), controller.update(item.getId(), item));

		Mockito.verify(service, Mockito.times(1)).update(item.getId(), item);
	}

	@Test
	void testDelete() {
		Mockito.when(service.delete(item.getId())).thenReturn(true);

		assertEquals(new ResponseEntity<>(HttpStatus.NO_CONTENT), controller.delete(item.getId()));

		Mockito.verify(service, Mockito.times(1)).delete(item.getId());
	}

	@Test
	void testOrder() {
		String totalString = String.valueOf(item.getPrice());
		String string = "Order Successful!\n\nItems:" + "\n" + 1 + ": " + item.toReceipt() + "\n(from Store:"
				+ item.getStore().toReceipt() + ")" + "\nTotal: £" + totalString.substring(0, totalString.length() - 2)
				+ "." + totalString.substring(totalString.length() - 2)
				+ "\n\nThanks for shopping at TheMusicStore.\nPlease visit again.";

		Mockito.when(service.order(Arrays.asList(item.getId()))).thenReturn(string);

		Integer[] ids = { item.getId() };

		assertEquals(new ResponseEntity<>(string, HttpStatus.OK), controller.order(ids));

		Mockito.verify(service, Mockito.times(1)).order(List.of(item.getId()));
	}

	@Test
	void testFindById() {
		Mockito.when(service.findById(item.getId())).thenReturn(itemDTO);

		assertEquals(itemDTO, controller.findById(item.getId()));

		Mockito.verify(service, Mockito.times(1)).findById(item.getId());
	}

	@Test
	void testFindAll() {
		Mockito.when(service.findAll()).thenReturn(itemDTOs);

		assertEquals(itemDTOs, controller.findAll());

		Mockito.verify(service, Mockito.times(1)).findAll();
	}

	@Test
	void testFindByParameters() {
		Mockito.when(service.findByCategoryOrTypeOrInstrumentOrBrandOrName(item.getType(), item.getCategory(),
				item.getInstrument(), item.getBrand(), item.getName())).thenReturn(itemDTOs);

		assertEquals(itemDTOs, controller.findByParameters(item.getType(), item.getCategory(), item.getInstrument(),
				item.getBrand(), item.getName()));

		Mockito.verify(service, Mockito.times(1)).findByCategoryOrTypeOrInstrumentOrBrandOrName(item.getType(),
				item.getCategory(), item.getInstrument(), item.getBrand(), item.getName());
	}

	@Test
	void testFindByStockGreaterThanEqual() {
		Mockito.when(service.findByStockGreaterThanEqual(item.getStock())).thenReturn(itemDTOs);

		assertEquals(itemDTOs, controller.findByStockGreaterThan(item.getStock()));

		Mockito.verify(service, Mockito.times(1)).findByStockGreaterThanEqual(item.getStock());
	}

	@Test
	void testFindByStockLessThanEqual() {
		Mockito.when(service.findByStockLessThanEqual(item.getStock())).thenReturn(itemDTOs);

		assertEquals(itemDTOs, controller.findByStockLessThan(item.getStock()));

		Mockito.verify(service, Mockito.times(1)).findByStockLessThanEqual(item.getStock());
	}

	@Test
	void testFindByPriceGreaterThanEqual() {
		Mockito.when(service.findByPriceGreaterThanEqual(item.getPrice())).thenReturn(itemDTOs);

		assertEquals(itemDTOs, controller.findByPriceGreaterThan(item.getPrice()));

		Mockito.verify(service, Mockito.times(1)).findByPriceGreaterThanEqual(item.getPrice());
	}

	@Test
	void testFindByPriceLessThanEqual() {
		Mockito.when(service.findByPriceLessThanEqual(item.getPrice())).thenReturn(itemDTOs);

		assertEquals(itemDTOs, controller.findByPriceLessThan(item.getPrice()));

		Mockito.verify(service, Mockito.times(1)).findByPriceLessThanEqual(item.getPrice());
	}

}
