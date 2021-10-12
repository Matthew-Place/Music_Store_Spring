package com.qa.musicstore.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.musicstore.data.Item;

@Repository
public interface ItemRepo extends JpaRepository<Item, Integer> {

	public List<Item> findByCategoryOrTypeOrInstrumentOrBrandOrName(String type, String category,
			String instrument, String brand, String name);

	public List<Item> findByStockGreaterThanEqual(Integer stock);

	public List<Item> findByStockLessThanEqual(Integer stock);

	public List<Item> findByPriceGreaterThanEqual(Integer price);

	public List<Item> findByPriceLessThanEqual(Integer price);
}
