package com.qa.musicstore.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class StoreDTO {

	private Integer id;
	private String manager;
	private String address;
	private String contactNumber;
	List<ItemDTO> itemDTOs = new ArrayList<>();

	public StoreDTO() {
		super();
	}

	public StoreDTO(Integer id, String manager, String address, String contactNumber) {
		super();
		this.id = id;
		this.manager = manager;
		this.address = address;
		this.contactNumber = contactNumber;
	}

	public StoreDTO(Integer id, String manager, String address, String contactNumber, List<ItemDTO> itemDTOs) {
		super();
		this.id = id;
		this.manager = manager;
		this.address = address;
		this.contactNumber = contactNumber;
		this.itemDTOs = itemDTOs;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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

	public List<ItemDTO> getItemDTOs() {
		return itemDTOs;
	}

	public void setItemDTOs(List<ItemDTO> itemDTOs) {
		this.itemDTOs = itemDTOs;
	}

	@Override
	public int hashCode() {
		return Objects.hash(address, contactNumber, id, itemDTOs, manager);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof StoreDTO)) {
			return false;
		}
		StoreDTO other = (StoreDTO) obj;
		return Objects.equals(address, other.address) && Objects.equals(contactNumber, other.contactNumber)
				&& Objects.equals(id, other.id) && Objects.equals(itemDTOs, other.itemDTOs)
				&& Objects.equals(manager, other.manager);
	}

	@Override
	public String toString() {
		return "StoreDTO [id=" + id + ", manager=" + manager + ", address=" + address + ", contactNumber="
				+ contactNumber + ", itemDTOs=" + itemDTOs + "]";
	}

}
