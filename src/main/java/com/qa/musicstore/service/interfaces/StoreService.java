package com.qa.musicstore.service.interfaces;

import java.util.List;

import com.qa.musicstore.data.Store;
import com.qa.musicstore.dto.StoreDTO;

public interface StoreService extends GenericService<StoreDTO, Store> {

	public List<StoreDTO> findByManagerOrAddressOrContactNumber(String manager, String address, String contactNumber);

}
