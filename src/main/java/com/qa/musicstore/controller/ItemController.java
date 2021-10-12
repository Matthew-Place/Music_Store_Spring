package com.qa.musicstore.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.musicstore.data.Item;
import com.qa.musicstore.dto.ItemDTO;
import com.qa.musicstore.service.interfaces.ItemService;

@RestController
@RequestMapping("/Item")
public class ItemController {

	private ItemService service;

	public ItemController(ItemService service) {
		super();
		this.service = service;
	}

	@PostMapping("/create")
	public ResponseEntity<ItemDTO> create(@RequestBody Item item) {
		return new ResponseEntity<>(service.create(item), HttpStatus.CREATED);
	}

	@GetMapping("/findById/{id}")
	public ItemDTO findById(@PathVariable Integer id) {
		return service.findById(id);
	}

	@GetMapping("/findAll")
	public List<ItemDTO> findAll() {
		return service.findAll();
	}

	@GetMapping("/findByParametersUsingAnd")
	public List<ItemDTO> findByParametersUsingAnd(@PathParam("type") String type,
			@PathParam("category") String category, @PathParam("instrument") String instrument,
			@PathParam("brand") String brand, @PathParam("name") String name) {
		return service.findByCategoryAndTypeAndInstrumentAndBrandAndName(type, category, instrument, brand,
				name);
	}

	@GetMapping("/findByParametersUsingOr")
	public List<ItemDTO> findByParametersUsingOr(@PathParam("type") String type, @PathParam("category") String category,
			@PathParam("instrument") String instrument, @PathParam("brand") String brand,
			@PathParam("name") String name) {
		return service.findByCategoryOrTypeOrInstrumentOrBrandOrName(type, category, instrument, brand, name);
	}

	@GetMapping("/findByPriceGreaterThan")
	public List<ItemDTO> findByPriceGreaterThan(@PathParam("price") Integer price) {
		return service.findByPriceGreaterThanEqual(price);
	}

	@GetMapping("/findByPriceLessThan")
	public List<ItemDTO> findByPriceLessThan(@PathParam("price") Integer price) {
		return service.findByPriceLessThanEqual(price);
	}

	@GetMapping("/findByStockGreaterThan")
	public List<ItemDTO> findByStockGreaterThan(@PathParam("stock") Integer stock) {
		return service.findByStockGreaterThanEqual(stock);
	}

	@GetMapping("/findByStockLessThan")
	public List<ItemDTO> findByStockLessThan(@PathParam("stock") Integer stock) {
		return service.findByStockLessThanEqual(stock);
	}
}
