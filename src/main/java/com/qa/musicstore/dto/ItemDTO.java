package com.qa.musicstore.dto;

import java.util.Objects;

public class ItemDTO {

	private Integer id;
	private String type; // Instrument, accessory, miscellaneous
	private String category; // Bowed String, String, Woodwind, Brass, Percussion, Keyboard
	private String instrument; // Piano, Guitar, etc.
	private String brand; // brand, like make.
	private String name; // Custom product name, like Pro guitar
	private Integer price; // Price class which handles pounds and pennies
	private Integer stock; // Stock of item - store specific (that's why it uses one to many, not many to
							// many)
	private Integer Store_id;
	private String manager;
	private String address;
	private String contactNumber;

	public ItemDTO(Integer id, String type, String category, String instrument, String brand, String name,
			Integer price, Integer stock, Integer store_id, String manager, String address, String contactNumber) {
		super();
		this.id = id;
		this.type = type;
		this.category = category;
		this.instrument = instrument;
		this.brand = brand;
		this.name = name;
		this.price = price;
		this.stock = stock;
		Store_id = store_id;
		this.manager = manager;
		this.address = address;
		this.contactNumber = contactNumber;
	}

	public ItemDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getInstrument() {
		return instrument;
	}

	public void setInstrument(String instrument) {
		this.instrument = instrument;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public Integer getStore_id() {
		return Store_id;
	}

	public void setStore_id(Integer store_id) {
		Store_id = store_id;
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

	@Override
	public int hashCode() {
		return Objects.hash(Store_id, address, brand, category, contactNumber, id, instrument, manager, name, price,
				stock, type);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof ItemDTO)) {
			return false;
		}
		ItemDTO other = (ItemDTO) obj;
		return Objects.equals(Store_id, other.Store_id) && Objects.equals(address, other.address)
				&& Objects.equals(brand, other.brand) && Objects.equals(category, other.category)
				&& Objects.equals(contactNumber, other.contactNumber) && Objects.equals(id, other.id)
				&& Objects.equals(instrument, other.instrument) && Objects.equals(manager, other.manager)
				&& Objects.equals(name, other.name) && Objects.equals(price, other.price)
				&& Objects.equals(stock, other.stock) && Objects.equals(type, other.type);
	}

}
