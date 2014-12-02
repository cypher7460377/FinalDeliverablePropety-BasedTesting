/*
 * This is a helper class to be used in testing. Users can use this class statically
 * to get lists of randomized values back to use in test cases.
 * 
 * Author: Matt O' Hanlon
 * Email: mao73@pitt.edu
 */
package com.example.unitconversion1.helper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class ListGenerator {
	private static Random rnd = new Random();

	public ListGenerator() {
		
	}

	/**
	 * 
	 * @param int numTests
	 * @return An ArrayList of random BigDecimal values.
	 */
	public static ArrayList<BigDecimal> getReals(int numTests) {
		ArrayList<BigDecimal> reals = new ArrayList<BigDecimal>();
		for(int i = numTests; i > 0; i--) {
			reals.add(BigDecimal.valueOf(rnd.nextDouble() * (double)(rnd.nextInt(2147483647)-1073741823)));
		}
		Collections.shuffle(reals);
		return reals;
	}
	
	/**
	 * 
	 * @param int numTests
	 * @param double floor
	 * @return An ArrayList of random BigDecimal values greater than the 
	 * specified floor value.
	 */
	public static ArrayList<BigDecimal> getRealsGreaterThan(int numTests, double floor) {
		ArrayList<BigDecimal> realsGreaterThan = new ArrayList<BigDecimal>();
		BigDecimal entry;
		if(floor < 0) {
			for(int i = numTests; i > 0; i--) {
				/*
				 * For floor values that are less than zero, I use the max value of a 32 bit 
				 * integer for nextInt and I add on another random nextDouble for more 
				 * randomness. Then I subtract the floor from the number generated from the 
				 * randomly generated value. This doesn't guarantee a number less than zero, 
				 * but the possibility still exists since Random can generate a zero value.
				 */
				entry = BigDecimal.valueOf((rnd.nextInt(2147483647) + rnd.nextDouble()) + floor);
				realsGreaterThan.add(BigDecimal.valueOf(entry.doubleValue()));
			}
		} else {
			for(int i = numTests; i > 0; i--) {
				/*
				 * For floor values greater than zero, I use the max value of a 32 bit integer
				 * for the nextInt method and I add on another random double for more randomness. 
				 * Then I add on the given floor, so that no numbers will be less than the given 
				 * floor.
				 */
				entry = BigDecimal.valueOf((double)(rnd.nextInt(2147483647) + rnd.nextDouble()) + floor);
				realsGreaterThan.add(BigDecimal.valueOf(entry.doubleValue()));
			}
		}
		Collections.shuffle(realsGreaterThan);
		return realsGreaterThan;
	}
	
	/**
	 * 
	 * @param int numTests
	 * @param double ceiling
	 * @return An ArrayList of random BigDecimal values less than the 
	 * specified floor value.
	 */
	public static ArrayList<BigDecimal> getRealsLessThan(int numTests, double ceiling) {
		ArrayList<BigDecimal> realsLessThan = new ArrayList<BigDecimal>();
		BigDecimal entry;
		
		if(ceiling < 0) {
			/*
			 * For ceiling values less than zero, you need to generate all negative values.
			 * I use the max value for a 32 bit integer as the upper bound, then I subtract
			 * that value from the random number generated. Then you can "add" ceiling to 
			 * the random value because it is possible the number generated could be 0.
			 * This way, you only get numbers at least the same or less than ceiling. 
			 */
			for(int i = numTests; i > 0; i--) {
				entry = BigDecimal.valueOf((rnd.nextInt(2147483647) - 2147483647) - rnd.nextDouble() + ceiling);
				realsLessThan.add(BigDecimal.valueOf(entry.doubleValue()));
			}
		} else {
			/*
			 * For ceiling values greater than zero, I use the ceiling value as the upper bound
			 * when generating a random value. I add a random double value to create some more
			 * randomness. Then I subtract another random integer from that value again using
			 * the max value for a 32 bit integer as the upper bound. This way, you can only 
			 * get values up to the number given for ceiling.
			 */
			for(int i = numTests; i > 0; i--) {
				entry = BigDecimal.valueOf((rnd.nextInt((int)ceiling) - rnd.nextDouble()) - rnd.nextInt(2147483647));
				realsLessThan.add(BigDecimal.valueOf(entry.doubleValue()));
			}
		}
		
		Collections.shuffle(realsLessThan);
		return realsLessThan;
	}
	
	/**
	 * 
	 * @param int numTests
	 * @param double max
	 * @param double min
	 * @return An ArrayList of random BigDecimal values in between the specified
	 * values of max and min.
	 * @throws Exception if min is greater than or equal to max.
	 */
	public static ArrayList<BigDecimal> getRealsInBetween(int numTests, double max, double min) throws Exception {
		ArrayList<BigDecimal> realsInBetween = new ArrayList<BigDecimal>();
		BigDecimal entry;
		
		/*
		 * Just some sanity checking before we do any calculation on max and min.
		 */
		if (min >= max) {
			throw new Exception("Min cannot be greater than or equeal to max");
		}
		
		if(max < 0) {
			/*
			 * To get numbers in between negative numbers, I do the same as I 
			 * would normally with positive numbers, except that I flip the 
			 * generated number to a negative before adding it to the 
			 * ArrayList. Of course, I add a random double from nextDouble
			 * for more randomness.
			 */
			for(int i = numTests; i > 0; i--) {
				entry = BigDecimal.valueOf(-1 * ((rnd.nextInt((int) Math.ceil(max) - (int) Math.floor(min) + 1) + min) + rnd.nextDouble()));
				realsInBetween.add(entry);
			}		
		} else if (min < 0) {
			/*
			 * If the min is negative, then I use max as the upperbound. Then
			 * I subtract the absolute value of the parameter "min" from the 
			 * generated number in order to get negative numbers if the random 
			 * number is close to zero. Of course, I add a random double from 
			 * nextDouble for more randomness.
			 */
			for(int i = numTests; i > 0; i--) {
				entry = BigDecimal.valueOf((rnd.nextInt((int) Math.ceil(max) + 1) - (int) Math.floor(Math.abs(min))) + rnd.nextDouble());
				realsInBetween.add(entry);
			}
		} else {
			/*
			 * This is pretty much the standard way to get random numbers in 
			 * a range. You get the difference between the bounds, then use
			 * that as the upper bound in the nextInt method call. Then you
			 * add the parameter "min" to make sure that you get at least that much in
			 * case the generated number is zero. Of course, I add a random 
			 * double from nextDouble for more randomness.
			 */
			for(int i = numTests; i > 0; i--) {
				entry = BigDecimal.valueOf((rnd.nextInt((int) Math.ceil(max) - (int) Math.floor(min) + 1) + min) + rnd.nextDouble());
				realsInBetween.add(entry);
			}
		}
		
		Collections.shuffle(realsInBetween);
		return realsInBetween;
	}
}
