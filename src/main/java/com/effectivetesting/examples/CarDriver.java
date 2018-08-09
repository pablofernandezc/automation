package com.effectivetesting.examples;

public class CarDriver {
	public static void main(String[] args) {
		Car myCar = new Car();
		System.out.println(myCar.type);
		
		switch (myCar.segment) {
		case "A":
			System.out.println("City Car");
			break;
		case "B":
			System.out.println("Compact");
			break;
		case "SUV":
			System.out.println("Sport Utility Vehicle");
		}
	}
}
