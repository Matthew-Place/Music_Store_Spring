package com.qa.musicstore.controller;

import java.util.Arrays;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

	@PutMapping("/update/{id}")
	public ResponseEntity<ItemDTO> update(@PathVariable Integer id, @RequestBody Item item) {
		return new ResponseEntity<>(service.update(id, item), HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<HttpStatus> delete(@PathVariable Integer id) {
		if (service.delete(id)) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/order/{ids}")
	public ResponseEntity<String> order(@PathVariable Integer[] ids) {
		return new ResponseEntity<>(service.order(Arrays.asList(ids)), HttpStatus.OK);
	}

	@GetMapping("/findById/{id}")
	public ItemDTO findById(@PathVariable Integer id) {
		return service.findById(id);
	}

	@GetMapping("/findAll")
	public List<ItemDTO> findAll() {
		return service.findAll();
	}

	@GetMapping("/findByParameters")
	public List<ItemDTO> findByParameters(@PathParam("type") String type, @PathParam("category") String category,
			@PathParam("instrument") String instrument, @PathParam("brand") String brand,
			@PathParam("name") String name) {
		return service.findByCategoryOrTypeOrInstrumentOrBrandOrName(type, category, instrument, brand, name);
	}

	@GetMapping("/findByPriceGreaterThan/{price}")
	public List<ItemDTO> findByPriceGreaterThan(@PathVariable Integer price) {
		return service.findByPriceGreaterThanEqual(price);
	}

	@GetMapping("/findByPriceLessThan/{price}")
	public List<ItemDTO> findByPriceLessThan(@PathVariable Integer price) {
		return service.findByPriceLessThanEqual(price);
	}

	@GetMapping("/findByStockGreaterThan/{stock}")
	public List<ItemDTO> findByStockGreaterThan(@PathVariable Integer stock) {
		return service.findByStockGreaterThanEqual(stock);
	}

	@GetMapping("/findByStockLessThan/{stock}")
	public List<ItemDTO> findByStockLessThan(@PathVariable Integer stock) {
		return service.findByStockLessThanEqual(stock);
	}
}
