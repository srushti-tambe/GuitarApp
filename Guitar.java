package com.aurionpro.module;

public class Guitar {

	private String serialNumber;
//	private String builder, model, type, backWood, topWood;
//	private String model;
//	private Type type;
//	private Builder builder;
//	private Wood backWood;
//	private Wood topWood;
	private double price;
	int units;
	GuitarSpec spec;
		
	public Guitar(String serialNumber, double price, int units, GuitarSpec spec) {
		this.serialNumber = serialNumber;
		this.price = price;
		this.spec = spec;
		this.units = units;
		}
	
	public String getSerialNumber() {
		return serialNumber;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	public void setUnits(int units) {
		this.units = units;
	}

	public int getUnits() {
		return units;
	}
	
	public GuitarSpec getSpec() {
		return spec;
	}

	
}
