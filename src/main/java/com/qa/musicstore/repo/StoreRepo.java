package com.qa.musicstore.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.musicstore.data.Store;

@Repository
public interface StoreRepo extends JpaRepository<Store, Integer> {

	public List<Store> findByManagerOrAddressOrContactNumber(String manager, String address, String contactNumber);

}
