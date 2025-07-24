package com.aurionpro.module;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Inventory {

	Scanner scanner = new Scanner(System.in);
	private ArrayList<Guitar> guitars;
	
	public Inventory() {
		guitars = new ArrayList<Guitar>();
	}
	
	public void addGuitar(String serialNumber,double price, int units, Builder builder, String model, Type type, Wood backWood, Wood topWood, int numString) {
		GuitarSpec guitarSpec = new GuitarSpec(builder, model, type, backWood, topWood,numString);
		Guitar guitar = new Guitar(serialNumber, price, units, guitarSpec);
		
		guitars.add(guitar);
	}
	public void addGuitarByAdmin() {
		System.out.println("Enter serial number: ");
		String serialNumber = scanner.nextLine();
		System.out.println("Enter Builder: ");
		String builderType = scanner.nextLine().toUpperCase();
		Builder builder = Builder.valueOf(builderType);
		System.out.println("Enter Type: ");
		String types = scanner.nextLine().toUpperCase();
		Type type = Type.valueOf(types);
		System.out.println("Enter Model: ");
		String model = scanner.nextLine();
		System.out.println("Enter Back Wood: ");
		String backwood = scanner.nextLine().toUpperCase();
		Wood backWood = Wood.valueOf(backwood);
		System.out.println("Enter Top Wood: ");
		String topwood = scanner.nextLine().toUpperCase();
		Wood topWood = Wood.valueOf(topwood);
		System.out.println("Enter number of strings: ");
		int numStrings = scanner.nextInt();
		System.out.println("Enter units: ");
		int units = scanner.nextInt();
		System.out.println("Enter price of each: ");
		double price = scanner.nextDouble();

		addGuitar(serialNumber, price, units, builder, model, type, backWood, topWood,
				numStrings);
		System.out.println("Guitar added successfully!");
	}
	public void displayGuitar() {
		//display all guitars
		if(guitars.isEmpty()) {
			System.out.println("No guitars in the inventory!");
			return;
		}
		
		int i=1;
		System.out.println("---- Guitar Details ---- ");
		for(Guitar guitar: guitars) {
			GuitarSpec spec = guitar.getSpec();
			System.out.println(i++ + ".");
	        System.out.println("|Serial Number|: " + guitar.getSerialNumber()+"|Price|: $" + guitar.getPrice()+"|Units|: " +guitar.getUnits()+"|Builder|: " + spec.getBuilder()+"|Model|: " + spec.getModel()+"|Type|: " + spec.getType()+"|Back Wood|: " + spec.getBackWood()+"|Top Wood|: " + spec.getTopWood()+"|Number of Strings|: " + spec.getNumstrings());
		}
	}
	
	public void printGuitar(Guitar guitar) {
		GuitarSpec spec = guitar.getSpec();
		System.out.println("We have a " + spec.getNumstrings() + "-string " +
                spec.getBuilder() + " " + spec.getModel() + " " + spec.getType() + " guitar:\n" +
                "  " + spec.getBackWood() + " back and sides,\n" +
                "  " + spec.getTopWood() + " top.\n" +
                "You can have it for only $" + guitar.getPrice() + "!");
		System.out.println("Hurry up only "+guitar.getUnits()+" peices are remaining!");
        System.out.println("-------------");
	}
	public void printGuitarAdmin(Guitar guitar) {
		GuitarSpec spec = guitar.getSpec();
		System.out.println("We have a " + spec.getNumstrings() + "-string " +
                spec.getBuilder() + " " + spec.getModel() + " " + spec.getType() + " guitar:\n" +
                "  " + spec.getBackWood() + " back and sides,\n" +
                "  " + spec.getTopWood() + " top.\n" +
                "Price: $" + guitar.getPrice() + "!");
		System.out.println(guitar.getUnits()+" pieces in inventory.");
        System.out.println("-------------");
	}
	
	public Guitar getGuitar(String serialNumber) {
		for(Iterator i = guitars.iterator(); i.hasNext();) {
			Guitar guitar = (Guitar)i.next();
			if(guitar.getSerialNumber().equals(serialNumber)) {
				return guitar;
			}
		}
		return null;
	}
	public void searchSpecific() {
		System.out.println("Enter Builder: ");
		String builderType = scanner.nextLine().toUpperCase();
		Builder builder = Builder.valueOf(builderType);
		System.out.println("Enter Type: ");
		String types = scanner.nextLine().toUpperCase();
		Type type = Type.valueOf(types);
		System.out.println("Enter Model: ");
		String model = scanner.nextLine();
		System.out.println("Enter Back Wood: ");
		String backwood = scanner.nextLine().toUpperCase();
		Wood backWood = Wood.valueOf(backwood);
		System.out.println("Enter Top Wood: ");
		String topwood = scanner.nextLine().toUpperCase();
		Wood topWood = Wood.valueOf(topwood);
		System.out.println("Enter number of strings: ");
		int numStrings = scanner.nextInt();
		
		GuitarSpec specs = new GuitarSpec(builder, model, type, backWood, topWood, numStrings);
		List recommendGuitars = search(specs);
		if (!recommendGuitars.isEmpty()) {
			System.out.println("Some recommended guitars for Erin: ");
			for (Iterator i = recommendGuitars.iterator(); i.hasNext();) {
				Guitar guitar = (Guitar) i.next();
				GuitarSpec spec = guitar.getSpec();
				System.out.println("We have a " + spec.getBuilder() + " " + spec.getModel() + " " + spec.getNumstrings()
						+ "-string " + spec.getType() + " guitar:\n " + spec.getBackWood() + " back and sides,\n "
						+ spec.getTopWood() + " top.\n You can have it for only $" + guitar.getPrice() + "\n its serial number: "+guitar.getSerialNumber() +"!\n ------------------");
			}
		}
		else {
			System.out.println("No guitar found with given specifiactions!");
		}
	}
	
	public boolean searchByFilters() {
		ArrayList<Guitar> filteredGuitars = new ArrayList<>(guitars);
		while(true) {
			System.out.println("Filter by? : \n1.Builder\n2.Type\n3.Model\n4.Back Wood\n5.Top Wood\n6.Number of Strings\n7.Buy\n8.Exit");
			int filter = scanner.nextInt();
			scanner.nextLine();
			if(filter==1) {
				System.out.println("Enter builder:");
				String builderType = scanner.nextLine().toUpperCase();
				Builder builder = Builder.valueOf(builderType);
				System.out.println("\nFiltered guitars are: ");
				
				//filter by builder
				filteredGuitars = filterByBuilder(filteredGuitars, builder);
				
				
				//print guitars filtered by builder
				diaplayByList(filteredGuitars);
				
			}
			else if(filter==2) {
				System.out.println("Enter type:");
				String types = scanner.nextLine().toUpperCase();
				Type type = Type.valueOf(types);
				System.out.println("\nFiltered guitars are: ");
				
				//filter by type
				filteredGuitars = filterByType(filteredGuitars,type);
				
				//print guitars filtered by builder
				diaplayByList(filteredGuitars);
			
			}

			else if(filter==3) {
				System.out.println("Enter model:");
				String model = scanner.nextLine();
				System.out.println("\nFiltered guitars are: ");
				
				//filter by type
				filteredGuitars = filterByModel(filteredGuitars,model);
				
				//print guitars filtered by builder
				diaplayByList(filteredGuitars);
				
			}
			else if(filter==4) {
				System.out.println("Enter back wood:");
				String backwood = scanner.nextLine().toUpperCase();
				Wood backWood = Wood.valueOf(backwood);
				System.out.println("\nFiltered guitars are: ");
				
				//filter by type
				filteredGuitars = filterByBackWood(filteredGuitars,backWood);
				
				//print guitars filtered by builder
				diaplayByList(filteredGuitars);
				
			}
			else if(filter==5) {
				System.out.println("Enter top wood:");
				String topwood = scanner.nextLine().toUpperCase();
				Wood topWood = Wood.valueOf(topwood);
				System.out.println("\nFiltered guitars are: ");
				
				//filter by type
				filteredGuitars = filterByTopWood(filteredGuitars,topWood);
				
				//print guitars filtered by builder
				diaplayByList(filteredGuitars);
				System.out.println();
			}
			else if(filter==6) {
				System.out.println("Enter number of strings:");
				int numStrings = scanner.nextInt();
				System.out.println("\nFiltered guitars are: ");
				
				//filter by type
				filteredGuitars = filterByStrings(filteredGuitars,numStrings);
				
				//print guitars filtered by builder
				diaplayByList(filteredGuitars);	
			}
			else if(filter==7) {
				while(true) {
					System.out.println("Do you want to buy?");
					char answer= scanner.next().charAt(0);
					scanner.nextLine();
					if(answer=='y') {
						System.out.println("Enter serial number: ");
						String serialNumber = scanner.nextLine();
						boolean get = buy(serialNumber);
						if(get==true) {
							break;
						}
						else {
							System.out.println("Insufficient guitars of this type!");
							break;
						}
					}
					if(answer=='n') {
						break;
					}
				}
			}
			else if(filter==8) {
				break;
			}
		}
	return false;
	}
	
	public Guitar searchByPrice(int choice) {
		for(Guitar guitar : guitars) {
			if(choice==1) {
				if(guitar.getPrice()>=500 && guitar.getPrice()<=1000)
					printGuitar(guitar);
			}
			if(choice==2) {
				if(guitar.getPrice()>1000 && guitar.getPrice()<=1500)
					printGuitar(guitar);
			}
			if(choice==3) {
				if(guitar.getPrice()>1500 && guitar.getPrice()<=2000)
					printGuitar(guitar);
			}
		}
		return null;
	}
	
	public boolean deleteBySerialNumber(String serialNumber) {
		for(Iterator i =guitars.iterator();i.hasNext();) {
			Guitar guitar = (Guitar)i.next();
			if(guitar.getSerialNumber().equalsIgnoreCase(serialNumber)) {
				i.remove();
				return true;
			}
		}
		return false;
	}
	public ArrayList<Guitar> filterByBuilder(ArrayList<Guitar> filteredGuitars,Builder builder){
		ArrayList<Guitar> guitarsbyBuilder = new ArrayList<>();
		for(Iterator i =filteredGuitars.iterator();i.hasNext();) {
			Guitar guitar = (Guitar)i.next();
			GuitarSpec guitarSpec = guitar.getSpec();
			if(guitarSpec.getBuilder().equals(builder)) {
				guitarsbyBuilder.add(guitar);
			}

		}
		return guitarsbyBuilder;
	}
	public ArrayList<Guitar> filterByType(ArrayList<Guitar> filteredGuitars,Type type){
		ArrayList<Guitar> guitarsbyType = new ArrayList<>();
		for(Iterator i =filteredGuitars.iterator();i.hasNext();) {
			Guitar guitar = (Guitar)i.next();
			GuitarSpec guitarSpec = guitar.getSpec();
			if(guitarSpec.getType().equals(type)) {
				guitarsbyType.add(guitar);
			}

		}
		return guitarsbyType;
	}
	public ArrayList<Guitar> filterByModel(ArrayList<Guitar> filteredGuitars,String model){
		ArrayList<Guitar> guitarsbyModel = new ArrayList<>();
		for(Iterator i =filteredGuitars.iterator();i.hasNext();) {
			Guitar guitar = (Guitar)i.next();
			GuitarSpec guitarSpec = guitar.getSpec();
			if(guitarSpec.getModel().equals(model)) {
				guitarsbyModel.add(guitar);
			}

		}
		return guitarsbyModel;
	}
	public ArrayList<Guitar> filterByBackWood(ArrayList<Guitar> filteredGuitars,Wood backWood){
		ArrayList<Guitar> guitarsbyBackWood = new ArrayList<>();
		for(Iterator i =filteredGuitars.iterator();i.hasNext();) {
			Guitar guitar = (Guitar)i.next();
			GuitarSpec guitarSpec = guitar.getSpec();
			if(guitarSpec.getBackWood().equals(backWood)) {
				guitarsbyBackWood.add(guitar);
			}

		}
		return guitarsbyBackWood;
	}
	public ArrayList<Guitar> filterByTopWood(ArrayList<Guitar> filteredGuitars,Wood topWood){
		ArrayList<Guitar> guitarsbyTopWood = new ArrayList<>();
		for(Iterator i =filteredGuitars.iterator();i.hasNext();) {
			Guitar guitar = (Guitar)i.next();
			GuitarSpec guitarSpec = guitar.getSpec();
			if(guitarSpec.getTopWood().equals(topWood)) {
				guitarsbyTopWood.add(guitar);
			}

		}
		return guitarsbyTopWood;
	}
	public ArrayList<Guitar> filterByStrings(ArrayList<Guitar> filteredGuitars,int numStrings){
		ArrayList<Guitar> guitarsbyStrings = new ArrayList<>();
		for(Iterator i =filteredGuitars.iterator();i.hasNext();) {
			Guitar guitar = (Guitar)i.next();
			GuitarSpec guitarSpec = guitar.getSpec();
			if(guitarSpec.getNumstrings()==numStrings) {
				guitarsbyStrings.add(guitar);
			}

		}
		return guitarsbyStrings;
	}
	public List search(GuitarSpec searchSpec) {
		List matchingGuitars = new LinkedList();
//		for(Iterator i = guitars.iterator(); i.hasNext();) {
//			Guitar guitar = (Guitar)i.next();
//			GuitarSpec guitarSpec = guitar.getSpec();
//			
//			if(guitar.getSpec().matches(searchSpec)) {
//				matchingGuitars.add(guitar);	
//			}
		for(Guitar guitar: guitars) {
			if(guitar.getSpec().matches(searchSpec)) {
				matchingGuitars.add(guitar);
			}
		}
		return matchingGuitars;
	}
	public List searchRecommend(GuitarSpec searchSpec) {
		List matchingGuitars = new LinkedList();
		for(Iterator i = guitars.iterator(); i.hasNext();) {
			Guitar guitar = (Guitar)i.next();
			GuitarSpec guitarSpec = guitar.getSpec();
			
			if(guitar.getSpec().recommend(searchSpec)) {
				matchingGuitars.add(guitar);	
			}
		}
		return matchingGuitars;
	}
	public void diaplayByList(ArrayList<Guitar> list) {
		if (!list.isEmpty()) {
			for (Iterator i = list.iterator(); i.hasNext();) {
				Guitar guitar = (Guitar) i.next();
				GuitarSpec spec = guitar.getSpec();
				System.out.println("|Serial Number|: "+guitar.getSerialNumber()+" |Price|: "+guitar.getPrice()+" |Builder|: "+spec.getBuilder()+" |Type|: "+spec.getType()+" |Model|: "+spec.getModel()+" |Back Wood|: "+spec.getBackWood()+" |Top Wood|: "+spec.getTopWood()+" |Strings|: "+spec.getNumstrings());
			}
		}
		
	}
	public boolean buy(String serialNumber) {
		for(Iterator i=guitars.iterator();i.hasNext();) {
			Guitar guitar = (Guitar)i.next();
			GuitarSpec guitarSpec = guitar.getSpec();
			if(guitar.getSerialNumber().equals(serialNumber) && guitar.getUnits()!=0) {
				guitar.setUnits(guitar.getUnits()-1);
				System.out.println("Guitar with serial number: "+serialNumber+",\nbuilder: "+guitarSpec.getBuilder()+",\ntype:"+guitarSpec.getType()+",\nmodel:"+guitarSpec.getModel()+",\nback wood:"+guitarSpec.getBackWood()+",\ntop wood:"+guitarSpec.getTopWood()+",\nand "+guitarSpec.getNumstrings()+"-string has been purchased!");
				System.out.println("Only "+guitar.getUnits()+" pieces remaining!");
				return true;
			}
		}
		return false;
	}
	
//	public Guitar search(Guitar searchGuitar) {
//		for(Iterator i = guitars.iterator(); i.hasNext();) {
//			Guitar guitar = (Guitar)i.next();
//			
////			String builder = searchGuitar.getBuilder();
////			if((builder!=null) && (!builder.equals("")) && (!builder.equalsIgnoreCase(guitar.getBuilder()))) {
////				continue;
////			}
//			if(searchGuitar.getBuilder()!= guitar.getBuilder()) {
//				continue;
//			}
//			
//			String model = searchGuitar.getModel().toLowerCase();
//			if((model!=null) && (!model.equals("")) && (!model.equals(guitar.getModel().toLowerCase()))) {
//				continue;
//			}
////			String type = searchGuitar.getType();
////			if((type!=null) && (!type.equals("")) && (!type.equals(guitar.getType()))) {
////				continue;
////			}
////			String topWood = searchGuitar.getTopWood();
////			if((topWood!=null) && (!topWood.equals("")) && (!topWood.equals(guitar.getTopWood()))) {
////				continue;
////			}
////			String backWood = searchGuitar.getBackWood();
////			if((backWood!=null) && (!backWood.equals("")) && (!backWood.equals(guitar.getBackWood()))) {
////				continue;
////			}
//			
//			if(searchGuitar.getType()!= guitar.getType()) {
//				continue;
//			}
//			if(searchGuitar.getBackWood()!= guitar.getBackWood()) {
//				continue;
//			}
//			if(searchGuitar.getTopWood()!= guitar.getBackWood()) {
//				continue;
//			}
//			return guitar;
//		}
//		return null;
//	}
	
//	public List search(Guitar searchGuitar) {
//		List matchingGuitars = new LinkedList();
//		for(Iterator i = guitars.iterator(); i.hasNext();) {
//			Guitar guitar = (Guitar)i.next();
//			
////			String builder = searchGuitar.getBuilder();
////			if((builder!=null) && (!builder.equals("")) && (!builder.equalsIgnoreCase(guitar.getBuilder()))) {
////				continue;
////			}
//			if(searchGuitar.getBuilder()!= guitar.getBuilder()) {
//				continue;
//			}
//			
//			String model = searchGuitar.getModel().toLowerCase();
//			if((model!=null) && (!model.equals("")) && (!model.equals(guitar.getModel().toLowerCase()))) {
//				continue;
//			}
////			String type = searchGuitar.getType();
////			if((type!=null) && (!type.equals("")) && (!type.equals(guitar.getType()))) {
////				continue;
////			}
////			String topWood = searchGuitar.getTopWood();
////			if((topWood!=null) && (!topWood.equals("")) && (!topWood.equals(guitar.getTopWood()))) {
////				continue;
////			}
////			String backWood = searchGuitar.getBackWood();
////			if((backWood!=null) && (!backWood.equals("")) && (!backWood.equals(guitar.getBackWood()))) {
////				continue;
////			}
//			
//			if(searchGuitar.getType()!= guitar.getType()) {
//				continue;
//			}
//			if(searchGuitar.getBackWood()!= guitar.getBackWood()) {
//				continue;
//			}
//			if(searchGuitar.getTopWood()!= guitar.getBackWood()) {
//				continue;
//			}
//			matchingGuitars.add(guitar);
//		}
//		return matchingGuitars;
//	}
}
