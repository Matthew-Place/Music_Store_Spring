package com.qa.musicstore.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qa.musicstore.data.Store;

public interface StoreRepo extends JpaRepository<Store, Integer> {

}
