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

	@PutMapping("/update/{id}")
	public ResponseEntity<StoreDTO> update(@PathVariable Integer id, @RequestBody Store store) {
		return new ResponseEntity<>(service.update(id, store), HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/close/{ids}")
	public ResponseEntity<HttpStatus> delete(@PathVariable Integer[] ids) {
		if (service.delete(Arrays.asList(ids))) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/findById/{ids}")
	public List<StoreDTO> findById(@PathVariable Integer[] ids) {
		return service.findById(Arrays.asList(ids));
	}

	@GetMapping("/findAll")
	public List<StoreDTO> findAll() {
		return service.findAll();
	}

	@GetMapping("/findByParameters")
	public List<StoreDTO> findByParams(@PathParam("manager") String manager, @PathParam("address") String address,
			@PathParam("contactNumber") String contactNumber) {
		return service.findByManagerOrAddressOrContactNumber(manager, address, contactNumber);
	}
}
