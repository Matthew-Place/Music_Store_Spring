package com.qa.musicstore.service.interfaces;

import java.util.List;

import com.qa.musicstore.data.Item;
import com.qa.musicstore.dto.ItemDTO;

public interface ItemService extends GenericService<ItemDTO, Item> {

	public List<ItemDTO> findByCategoryOrTypeOrInstrumentOrBrandOrName(String type, String category,
			String instrument, String brand, String name);

	public List<ItemDTO> findByStockGreaterThanEqual(Integer stock);

	public List<ItemDTO> findByStockLessThanEqual(Integer stock);

	public List<ItemDTO> findByPriceGreaterThanEqual(Integer price);

	public List<ItemDTO> findByPriceLessThanEqual(Integer price);
}
