package com.qa.musicstore.data.extra;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Price {
	private String pounds = "0";
	private String pennies = "00";

	public Price(String price) {
		List<String> priceList = new ArrayList<>(Arrays.asList(price.strip().replaceAll("[^\\d.]", "").split(".")));
		this.pounds = priceList.get(0);
		this.pennies = priceList.get(1).substring(0, 2);
	}

	@Override
	public String toString() {
		if (getPounds() >= 1 || getPennies() == 0) {
			return "£ " + pounds + "." + pennies;
		} else {
			return pennies + " p";
		}
	}

	private Integer getPounds() {
		return Integer.parseInt(pounds);
	}

	private Integer getPennies() {
		return Integer.parseInt(pennies);
	}

	@Override
	public int hashCode() {
		return Objects.hash(pennies, pounds);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Price)) {
			return false;
		}
		Price other = (Price) obj;
		return Objects.equals(pennies, other.pennies) && Objects.equals(pounds, other.pounds);
	}
}
