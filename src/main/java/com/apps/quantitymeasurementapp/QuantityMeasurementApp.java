package com.apps.quantitymeasurementapp;

import java.util.Scanner;

public class QuantityMeasurementApp {
	
	public static class Feet {
		private final double feet;
		
		public Feet(double feet) {
			this.feet = feet;
		}


		@Override
		public boolean equals(Object obj) {
			// Same object reference
			if(this == obj) return true;
			 
			// Null Pointer
			if(obj == null || this.getClass() != obj.getClass()) {
				return false;
			}
			
			// Compare double values safety
			return Double.compare(this.feet, ((Feet)obj).feet) == 0; 
		}
	}
	
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter 2 feet: ");
		
		double input1 = sc.nextDouble();
		double input2 = sc.nextDouble();
		
		Feet feet1 = new Feet(input1);
		Feet feet2 = new Feet(input2);
		
		System.out.println("Equal (" + feet1.equals(feet2) + ")"); 
	}
}