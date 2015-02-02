package com.ervacon.spring2sample.domain;

public class Country implements Comparable<Country> {

	private String code;
	private String name;

	public Country(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}
	
	public int compareTo(Country other) {
		return this.getCode().compareTo(other.getCode());
	}
}
