package com.qa.musicstore.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Store {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String manager;
	private String address;
	private String contactNumber;

	@JsonIgnore
	@OneToMany(mappedBy = "store")
	List<Item> items = new ArrayList<>();

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public Integer getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Store [id=" + id + ", manager=" + manager + ", address=" + address + ", contactNumber=" + contactNumber
				+ ", items=" + items + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(address, contactNumber, id, items, manager);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Store)) {
			return false;
		}
		Store other = (Store) obj;
		return Objects.equals(address, other.address) && Objects.equals(contactNumber, other.contactNumber)
				&& Objects.equals(id, other.id) && Objects.equals(items, other.items)
				&& Objects.equals(manager, other.manager);
	}

}
