package com.qa.musicstore.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.qa.musicstore.data.Item;
import com.qa.musicstore.dto.ItemDTO;
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
	public boolean delete(Integer id) {
		repo.deleteById(id);
		return !repo.existsById(id);
	}

	@Override
	public ItemDTO findById(Integer id) {
		return mapToDTO(repo.findById(id).orElseThrow(ItemNotFoundException::new));
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
