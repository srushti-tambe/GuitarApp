package com.aurionpro.module;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class GuitarSpec {

	private String model;
	private Type type;
	private Builder builder;
	private Wood backWood;
	private Wood topWood;
	private int numStrings;
	
	public GuitarSpec(Builder builder, String model, Type type, Wood backWood, Wood topWood, int numStrings) {
		this.builder = builder;
		this.model = model;
		this.type = type;
		this.backWood = backWood;
		this.topWood = topWood;
		this.numStrings = numStrings;
	}
	
	public boolean matches(GuitarSpec otherSpec) {
		if(builder!= otherSpec.builder) {
			return false;
		}
		if(!model.equals(otherSpec.model)) {
			return false;
		}
		if(type!= otherSpec.type) {
			return false;
		}
		if(backWood!= otherSpec.backWood) {
			return false;
		}
		if(topWood!= otherSpec.topWood) {
			return false;
		}
		if(numStrings!= otherSpec.numStrings) {
			return false;
		}
		return true;
	}
	
	public boolean recommend(GuitarSpec otherSpec) {
		int matchCount =0;
		if(builder == otherSpec.builder) {
			matchCount++;
		}
		if(model == otherSpec.model) {
			matchCount++;
		}
		if(type == otherSpec.type) {
			matchCount++;
		}
		if(backWood == otherSpec.backWood) {
			matchCount++;
		}
		if(topWood == otherSpec.topWood) {
			matchCount++;
		}
		if(numStrings == otherSpec.numStrings) {
			matchCount++;
		}
		return matchCount>=3;
	}
	
	
	public Builder getBuilder() {
		return builder;
	}

	public String getModel() {
		return model;
	}

	public Type getType() {
		return type;
	}

	public Wood getBackWood() {
		return backWood;
	}

	public Wood getTopWood() {
		return topWood;
	}
	
	public int getNumstrings() {
		return numStrings;
	}
}
