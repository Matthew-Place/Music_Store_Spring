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

	public ItemDTO(Integer id, String type, String category, String instrument, String brand, String name,
			Integer price, Integer stock) {
		super();
		this.id = id;
		this.type = type;
		this.category = category;
		this.instrument = instrument;
		this.brand = brand;
		this.name = name;
		this.price = price;
		this.stock = stock;
	}

	public ItemDTO() {
		super();
	}

	public String getType() {
		return type;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	@Override
	public int hashCode() {
		return Objects.hash(brand, category, id, instrument, name, price, stock, type);
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
		return Objects.equals(brand, other.brand) && Objects.equals(category, other.category)
				&& Objects.equals(id, other.id) && Objects.equals(instrument, other.instrument)
				&& Objects.equals(name, other.name) && Objects.equals(price, other.price)
				&& Objects.equals(stock, other.stock) && Objects.equals(type, other.type);
	}
}
