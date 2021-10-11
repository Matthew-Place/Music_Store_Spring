package com.qa.musicstore.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.qa.musicstore.data.Item;
import com.qa.musicstore.dto.ItemDTO;
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
}
