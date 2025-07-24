package com.aurionpro.module;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class FindGuitarFacade {

	private Scanner scanner;
	private Inventory inventory;

	public FindGuitarFacade() {
		scanner = new Scanner(System.in);
		inventory = new Inventory();
		initializeInventory(inventory);
	}

	public void start() {
		System.out.println("------Welcome to Rick's Guitar Store!------");
		while (true) {
			System.out.println("Login to: \n1.ADMIN\n2.CUSTOMER\n3.EXIT");
			int login = scanner.nextInt();
			if (login == 1) {
				System.out.println("----ADMIN----");
				while (true) {
					System.out.println(
							"1.Add Guitar\n2.Display all guitars\n3.Search guitar by serial number\n4.Delete Guitar\n5.Back to login.");
					int admin = scanner.nextInt();
					scanner.nextLine();
					if (admin == 1) {
						// add guitar
						inventory.addGuitarByAdmin();
						continue;
					}
					if (admin == 2) {
						// display all
						inventory.displayGuitar();
						continue;
					}
					if (admin == 3) {
						// search by serial number
						System.out.println("Enter serial number of guitar: ");
						String serialNumber = scanner.nextLine();
						Guitar guitar = inventory.getGuitar(serialNumber);
						if (guitar != null) {
							inventory.printGuitarAdmin(guitar);
						} else {
							System.out.println("No such guitar in inventory!");
						}
						continue;
					}
					if (admin == 4) {
						// delete
						System.out.println("Enter serial number of the guitar: ");
						String serialNumber = scanner.nextLine();
						if (inventory.deleteBySerialNumber(serialNumber)) {
							System.out.println(
									"Guitar with serial number: " + serialNumber + " was successfully deleted!");
						} else {
							System.out.println("Guitar with serial number: " + serialNumber + " not found!");
						}
						continue;

					}
					if (admin == 5) {
						break;
					}
				}
			}

			if (login == 2) {
				System.out.println("----CUSTOMER----");
				while (true) {
					System.out.println(
							"1.Display all guitars\n2.Search using filter\n3.Search specific guitar\n4.Buy guitar\n5.Filter by price\n6.Back to login.");
					int customer = scanner.nextInt();
					scanner.nextLine();
					if (customer == 1) {
						// display all
						inventory.displayGuitar();
						while (true) {
							System.out.println("Do you want to buy?");
							char answer = scanner.next().charAt(0);
							scanner.nextLine();
							if (answer == 'y') {
								System.out.println("Enter serial number: ");
								String serialNumber = scanner.nextLine();
								inventory.buy(serialNumber);
							}
							if (answer == 'n') {
								break;
							}
						}

						continue;
					}
					if (customer == 2) {
						// search using filters
						boolean ans = inventory.searchByFilters();
						if(ans==false) {
							continue;
						}
					}
					if (customer == 3) {
						// specific guitar
						inventory.searchSpecific();
						continue;
					}
					if (customer == 4) {
						// buy guitar
						System.out.println("1. Search by filter\n2.Search specific guitar");
						int filter = scanner.nextInt();
						if (filter == 1) {
							inventory.searchByFilters();
						}
						if (filter == 2) {
							inventory.searchSpecific();
						}

						System.out.println("Do you want to buy?");
						char answer = scanner.next().charAt(0);
						scanner.nextLine();
						if (answer == 'y') {
							System.out.println("Enter serial number: ");
							String serialNumber = scanner.nextLine();
							inventory.buy(serialNumber);
						}
						if (answer == 'n') {
							break;
						}
						continue;
					}
					if (customer == 5) {
						// filter by price
						System.out.println("What price range? \n1. $500-$1000\n2. $1000-$1500\n3. $1500-$2000");
						int priceChoice = scanner.nextInt();
						if (priceChoice < 1 || priceChoice > 3) {
							System.out.println("Invalid price range!");
						}
						inventory.searchByPrice(priceChoice);
						continue;
					}
					if (customer == 6) {
						break;
					}
				}
			}
			if (login == 3) {
				break;
			}

		}
	}

	private static void initializeInventory(Inventory inventory) {
		inventory.addGuitar("V9656", 1499.95, 10, Builder.FENDER, "Stratocastor", Type.ELECTRIC, Wood.ALDER, Wood.ALDER,
				12);
		inventory.addGuitar("V5691", 1549.95, 5, Builder.FENDER, "Stratocastor", Type.ELECTRIC, Wood.ALDER, Wood.ALDER,
				6);
		inventory.addGuitar("V552", 1529.95, 3, Builder.FENDER, "Telecastor", Type.ELECTRIC, Wood.ALDER, Wood.ALDER,
				12);
		inventory.addGuitar("V569", 1599.99, 2, Builder.GIBSON, "Telecastor", Type.ACOUSTIC, Wood.MAHOGANY,
				Wood.MAHOGANY, 12);
		inventory.addGuitar("V95693", 1499.0, 8, Builder.FENDER, "Startocastor", Type.ELECTRIC, Wood.ALDER, Wood.ALDER,
				12);
		inventory.addGuitar("V9512", 1549.0, 6, Builder.FENDER, "Startocastor", Type.ELECTRIC, Wood.ALDER, Wood.ALDER,
				6);
		inventory.addGuitar("V95819", 2499.0, 5, Builder.COLLINGS, "Jazzmaster", Type.ACOUSTIC, Wood.ALDER,
				Wood.BRAZILIAN_ROSEWOOD, 10);
		inventory.addGuitar("V95819", 2499.0, 5, Builder.PRS, "Les Paul", Type.ACOUSTIC, Wood.INDIAN_ROSEWOOD,
				Wood.BRAZILIAN_ROSEWOOD, 10);
		inventory.addGuitar("V95820", 1999.0, 3, Builder.GIBSON, "SG", Type.ELECTRIC, Wood.MAHOGANY, Wood.MAPLE, 6);
		inventory.addGuitar("V95821", 2899.0, 1, Builder.MARTIN, "D-28", Type.ACOUSTIC, Wood.BRAZILIAN_ROSEWOOD,
				Wood.ADIRONDACK, 12);
		inventory.addGuitar("V95822", 1599.0, 2, Builder.COLLINGS, "OM2H", Type.ACOUSTIC, Wood.MAPLE, Wood.STIKA, 6);
		inventory.addGuitar("V95823", 2199.0, 4, Builder.OLSON, "SJ", Type.ACOUSTIC, Wood.COCOBOLO, Wood.CEDAR, 12);
		inventory.addGuitar("V95824", 1799.0, 4, Builder.RYAN, "Nightingale", Type.ELECTRIC, Wood.ALDER, Wood.MAHOGANY,
				6);
		inventory.addGuitar("V95825", 2299.0, 7, Builder.PRS, "Custom 24", Type.ELECTRIC, Wood.MAPLE, Wood.MAPLE, 6);
		inventory.addGuitar("V95826", 2499.0, 1, Builder.MARTIN, "OM-45", Type.ACOUSTIC, Wood.INDIAN_ROSEWOOD,
				Wood.STIKA, 12);
		inventory.addGuitar("V95827", 1999.0, 3, Builder.GIBSON, "ES-335", Type.ELECTRIC, Wood.MAHOGANY, Wood.MAPLE, 6);
		inventory.addGuitar("V95828", 1699.0, 1, Builder.COLLINGS, "00-2H", Type.ACOUSTIC, Wood.COCOBOLO,
				Wood.ADIRONDACK, 6);
		inventory.addGuitar("V95829", 1899.0, 8, Builder.FENDER, "Telecastor", Type.ELECTRIC, Wood.ALDER, Wood.ALDER,
				6);
	}
}
