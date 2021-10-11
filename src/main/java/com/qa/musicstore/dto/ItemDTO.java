package com.qa.musicstore.dto;

public class ItemDTO {

	private String type; // Instrument, accessory, miscellaneous
	private String category; // Bowed String, String, Woodwind, Brass, Percussion, Keyboard
	private String instrument; // Piano, Guitar, etc.
	private String brand; // brand, like make.
	private String name; // Custom product name, like Pro guitar
	private Integer price; // Price class which handles pounds and pennies
	private Integer stock; // Stock of item - store specific (that's why it uses one to many, not many to
							// many)

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
}
