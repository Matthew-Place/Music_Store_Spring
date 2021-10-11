package com.qa.musicstore.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.musicstore.data.Item;

@Repository
public interface ItemRepo extends JpaRepository<Item, Integer> {

}
