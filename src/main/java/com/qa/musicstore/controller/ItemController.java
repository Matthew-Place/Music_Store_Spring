package com.qa.musicstore.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
}
