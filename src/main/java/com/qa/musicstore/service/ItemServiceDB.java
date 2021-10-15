package com.qa.musicstore.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.qa.musicstore.data.Item;
import com.qa.musicstore.dto.ItemDTO;
import com.qa.musicstore.exceptions.InsufficientStockException;
import com.qa.musicstore.exceptions.ItemNotFoundException;
import com.qa.musicstore.repo.ItemRepo;
import com.qa.musicstore.service.interfaces.ItemService;

@Primary
@Service
public class ItemServiceDB implements ItemService {

	private ItemRepo repo;

	public ItemServiceDB(ItemRepo repo) {
		super();
		this.repo = repo;
	}

	@Override
	public ItemDTO mapToDTO(Item item) {
		ItemDTO dto = new ItemDTO();
		dto.setId(item.getId());
		dto.setBrand(item.getBrand());
		dto.setCategory(item.getCategory());
		dto.setInstrument(item.getInstrument());
		dto.setName(item.getName());
		dto.setPrice(item.getPrice());
		dto.setStock(item.getStock());
		dto.setType(item.getType());
		dto.setStoreId(item.getStore().getId());
		return dto;
	}

	@Override
	public List<ItemDTO> mapToDTO(List<Item> items) {
		List<ItemDTO> itemDTOs = new ArrayList<>();
		for (Item item : items) {
			itemDTOs.add(mapToDTO(item));
		}
		return itemDTOs;
	}

	@Override
	public ItemDTO create(Item item) {
		return mapToDTO(repo.save(item));
	}

	@Override
	public ItemDTO update(Integer id, Item item) {
		Item updatedItem = repo.findById(id).orElseThrow(ItemNotFoundException::new);
		updatedItem.setBrand(item.getBrand());
		updatedItem.setCategory(item.getCategory());
		updatedItem.setInstrument(item.getInstrument());
		updatedItem.setName(item.getName());
		updatedItem.setType(item.getType());
		updatedItem.setPrice(item.getPrice());
		updatedItem.setStock(item.getStock());
		updatedItem.setStore(item.getStore());
		return mapToDTO(repo.save(updatedItem));
	}

	@Override
	public boolean delete(List<Integer> ids) {
		ids.forEach(id -> repo.findById(id).orElseThrow(ItemNotFoundException::new));
		repo.deleteAllById(ids);
		return !ids.stream().map(n -> repo.existsById(n)).toList().contains(true);
	}

	@Override
	public String order(List<Integer> ids) {
		List<Item> items = repo.findAllById(ids);
		if (items.stream().map(n -> n.getStock()).anyMatch(n -> n.equals(0))) {
			throw new InsufficientStockException();
		}
		StringBuilder string = new StringBuilder("Order Successful!\n\nItems:");
		Integer total = 0;
		Integer it = 1;
		for (Item item : items) {
			item.setStock(item.getStock()-1);
			repo.save(item);
			string.append("\n" + it + ": " + item.toReceipt() + "\n(from Store:" + item.getStore().toReceipt() + ")");
			total += item.getPrice();
			it++;
		}
		String totalString = String.valueOf(total);
		string.append("\nTotal: £"
				+ totalString.substring(0, totalString.length() - 2) + "."
				+ totalString.substring(totalString.length() - 2) + "\n\nThanks for shopping at TheMusicStore.\nPlease visit again.");
		return string.toString();
	}

	@Override
	public List<ItemDTO> findById(List<Integer> ids) {
		return mapToDTO(repo.findAllById(ids));
	}

	@Override
	public List<ItemDTO> findAll() {
		return mapToDTO(repo.findAll());
	}

	@Override
	public List<ItemDTO> findByCategoryOrTypeOrInstrumentOrBrandOrName(String type, String category, String instrument,
			String brand, String name) {
		return mapToDTO(repo.findByCategoryOrTypeOrInstrumentOrBrandOrName(type, category, instrument, brand, name));
	}

	@Override
	public List<ItemDTO> findByStockGreaterThanEqual(Integer stock) {
		return mapToDTO(repo.findByStockGreaterThanEqual(stock));
	}

	@Override
	public List<ItemDTO> findByStockLessThanEqual(Integer stock) {
		return mapToDTO(repo.findByStockLessThanEqual(stock));
	}

	@Override
	public List<ItemDTO> findByPriceGreaterThanEqual(Integer price) {
		return mapToDTO(repo.findByPriceGreaterThanEqual(price));
	}

	@Override
	public List<ItemDTO> findByPriceLessThanEqual(Integer price) {
		return mapToDTO(repo.findByPriceLessThanEqual(price));
	}

}
