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
	
	
	public static class Inches {
		private final double inch;
		
		public Inches(double inch) {
			this.inch = inch;
		}
		
		
		@Override
		public boolean equals(Object obj) {
			if(this == obj) return true;
			
			if(obj == null || this.getClass() != obj.getClass()) {
				return false; 
			}
			
			return Double.compare(this.inch, ((Inches)obj).inch) == 0; 
		}
	}
	
	public static void demonstrateFeetEquality() {
		Feet feet1 = new Feet(1.0);
		Feet feet2 = new Feet(1.0);
		
		boolean isEqual = feet1.equals(feet2);
		System.out.println("Equal (" + isEqual +  ")");  
	}
	
	public static void demonstrateInchEquality() {
		Inches inch1 = new Inches(1.0);
		Inches inch2 = new Inches(1.0);
		
		boolean isEqual = inch1.equals(inch2);
		System.out.println("Equal (" + isEqual + ")"); 
	}
	
	public static void main(String args[]) {
		demonstrateFeetEquality();
		demonstrateInchEquality();
	}
}