package com.qa.musicstore.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.musicstore.data.Store;
import com.qa.musicstore.dto.StoreDTO;
import com.qa.musicstore.service.interfaces.StoreService;

@RestController
@RequestMapping("/Store")
public class StoreController {

	private StoreService service;

	public StoreController(StoreService service) {
		super();
		this.service = service;
	}

	@PostMapping("/create")
	public ResponseEntity<StoreDTO> create(@RequestBody Store store) {
		return new ResponseEntity<>(service.create(store), HttpStatus.CREATED);
	}

	@GetMapping("/findById/{id}")
	public StoreDTO findById(@PathVariable Integer id) {
		return service.findById(id);
	}

	@GetMapping("/findAll")
	public List<StoreDTO> findAll() {
		return service.findAll();
	}
}
