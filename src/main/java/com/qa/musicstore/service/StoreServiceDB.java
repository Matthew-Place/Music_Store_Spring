package com.qa.musicstore.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.qa.musicstore.data.Item;
import com.qa.musicstore.data.Store;
import com.qa.musicstore.dto.ItemDTO;
import com.qa.musicstore.dto.StoreDTO;
import com.qa.musicstore.exceptions.StoreNotFoundException;
import com.qa.musicstore.repo.StoreRepo;
import com.qa.musicstore.service.interfaces.StoreService;

@Primary
@Service
public class StoreServiceDB implements StoreService {

	private StoreRepo repo;

	public StoreServiceDB(StoreRepo repo) {
		super();
		this.repo = repo;
	}

	@Override
	public StoreDTO mapToDTO(Store store) {
		StoreDTO dto = new StoreDTO();
		dto.setAddress(store.getAddress());
		dto.setId(store.getId());
		dto.setContactNumber(store.getContactNumber());
		dto.setManager(store.getManager());

		List<ItemDTO> itemDTOs = new ArrayList<>();
		for (Item item : store.getItems()) {
			ItemDTO itemDTO = new ItemDTO();
			itemDTO.setId(item.getId());
			itemDTO.setBrand(item.getBrand());
			itemDTO.setCategory(item.getCategory());
			itemDTO.setInstrument(item.getInstrument());
			itemDTO.setName(item.getName());
			itemDTO.setPrice(item.getPrice());
			itemDTO.setStock(item.getStock());
			itemDTO.setType(item.getType());
			itemDTO.setStoreId(store.getId());
			itemDTOs.add(itemDTO);
		}

		dto.setItemDTOs(itemDTOs);
		return dto;
	}

	@Override
	public List<StoreDTO> mapToDTO(List<Store> stores) {
		List<StoreDTO> storeDTOs = new ArrayList<>();
		for (Store store : stores) {
			storeDTOs.add(mapToDTO(store));
		}
		return storeDTOs;
	}

	@Override
	public StoreDTO create(Store store) {
		return mapToDTO(repo.save(store));
	}

	@Override
	public StoreDTO update(Integer id, Store store) {
		Store updatedStore = repo.findById(id).orElseThrow(StoreNotFoundException::new);
		updatedStore.setAddress(store.getAddress());
		updatedStore.setContactNumber(store.getContactNumber());
		updatedStore.setManager(store.getManager());
		updatedStore.setItems(store.getItems());
		return mapToDTO(repo.save(updatedStore));
	}

	@Override
	public boolean delete(Integer id) {
		repo.deleteById(id);
		return !repo.existsById(id);
	}

	@Override
	public StoreDTO findById(Integer id) {
		return mapToDTO(repo.findById(id).orElseThrow(StoreNotFoundException::new));
	}

	@Override
	public List<StoreDTO> findAll() {
		return mapToDTO(repo.findAll());
	}

	@Override
	public List<StoreDTO> findByManagerOrAddressOrContactNumber(String manager, String address, String contactNumber) {
		return mapToDTO(repo.findByManagerOrAddressOrContactNumber(manager, address, contactNumber));
	}
}
