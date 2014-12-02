/*
 * This is a class to test various properties of temperature conversions. There are 
 * 3 widely used temperature scales, Fahrenheit, Celsius, and Kelvin. This class
 * will test the various properties of the conversions from one temperature to another.
 * 
 * The properties this class will test are as follows:
 * 1. No zero temperature of any one temperature will convert to a zero temperature
 * 	  in another scale.
 * 2. No temperature in the Celsius scale will convert to an equal temperature in 
 *    the Kelvin scale.
 * 3. For all temperatures above 574.6 degrees Fahrenheit, all temperatures in the
 *    Fahrenheit scale will be greater than the converted values in the Kelvin and
 *    Celsius scales.
 * 4. For all Fahrenheit temperatures below -40 degrees Fahrenheit, the Fahrenheit
 *    temperatures will be less than their converted values in the Kelvin and 
 *    Celsius scales.
 * 5. For all temperatures in the range of {-40, ..., 574.6} degrees Fahrenheit, all
 *    temperatures in Fahrenheit will be less than or equal to the converted 
 *    values in the Kelvin scale and less than or equal to the converted value in 
 *    the Celsius scale.
 *    
 * Author: Matt O' Hanlon
 * Email: mao73@pitt.edu
 */

package com.example.unitconversion1.test;

import java.math.BigDecimal;
import java.util.ArrayList;

import com.example.unitconversion1.UnitConversion;
import com.example.unitconversion1.R;
import com.example.unitconversion1.helper.ListGenerator;

import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class PropertyBasedTest extends ActivityInstrumentationTestCase2<UnitConversion> {

	private EditText from, to;
	private Spinner spinnerFrom, spinnerTo;
	private Button convertButton;
	private UnitConversion unitConversion;
	public static final int CELSIUS = 0, FARENHEIT = 1, KELVIN = 2, NUMTESTS = 10;
	
	public PropertyBasedTest() {
		super("com.example.unitconversion1.test", UnitConversion.class);
	}
	
	public PropertyBasedTest(Class<UnitConversion> activityClass) {
		super(activityClass);
		// TODO Auto-generated constructor stub
	}
	
	protected void setUp() throws Exception {
		super.setUp();
		unitConversion = getActivity();
		from = (EditText) unitConversion.findViewById(R.id.value01);
		to = (EditText) unitConversion.findViewById(R.id.value02);
		spinnerFrom = (Spinner) unitConversion.findViewById(R.id.unit01);
		spinnerTo = (Spinner) unitConversion.findViewById(R.id.unit02);
		convertButton = (Button)unitConversion.findViewById(R.id.button1);
	}
	
	/*
	 * This test checks to make sure that no zero temperature in any given scale
	 * will convert to a zero value in another scale.
	 */
	/*
	public void testNonZeroConversion() {
		getActivity().runOnUiThread(new Runnable() {
			@Override 
			public void run() {
				from.setText("0");
				spinnerFrom.requestFocus();
				spinnerFrom.setSelection(KELVIN);
				convertButton.performClick();
				assertTrue(Double.parseDouble(to.getText().toString()) != 0);
				spinnerFrom.requestFocus();
				spinnerFrom.setSelection(CELSIUS);
				convertButton.performClick();
				assertTrue(Double.parseDouble(to.getText().toString()) != 0);
				spinnerFrom.requestFocus();
				spinnerFrom.setSelection(FARENHEIT);
				convertButton.performClick();
				assertTrue(Double.parseDouble(to.getText().toString()) != 0);
			}
			
		});
	}
	*/
	
	/*
	 * This test will check that no temperature in the Kelvin scale will convert
	 * to an equal value in the Celsius scale. The helper class ListGenerator
	 * will be used to generate random values in Kelvin. Then the test will convert
	 * the values to Celsius and compare the two.
	 */
	
	public void testNotEqualTemperatures() {
		getActivity().runOnUiThread(new Runnable() { 
			public void run() {
				ArrayList<BigDecimal> testNotEqualTemps = ListGenerator.getReals(NUMTESTS);
				spinnerFrom.requestFocus();
				spinnerFrom.setSelection(KELVIN);
				spinnerTo.requestFocus();
				spinnerTo.setSelection(CELSIUS);
				for(BigDecimal rand: testNotEqualTemps){
					from.setText(String.valueOf(rand.doubleValue()));
					convertButton.performClick();
					assertFalse(from.getText().toString() + " and " + to.getText().toString() + " caused the error", 
							from.getText().toString().equals(to.getText().toString()));
				}
			}
			
		});
	}
	
	/*
	 * This test checks that any temp in Fahrenheit above 574.6 will always yield a result
	 * in Kelvin and Celsius values that are less than the given Fahrenheit value.
	 */
	/*
	public void testFahrenheitZGreater() {

		getActivity().runOnUiThread(new Runnable() {
			public void run() {
				ArrayList<BigDecimal> testGreaterTemps = ListGenerator.getRealsGreaterThan(NUMTESTS,574.6);
				BigDecimal fromVal;
				BigDecimal toVal;
				spinnerFrom.requestFocus();
				spinnerFrom.setSelection(FARENHEIT);
				assertNotNull("Check to see button is not null",convertButton);
				for(BigDecimal rand: testGreaterTemps){
					assertNotNull("Check to see rand value is not null.", rand);
					from.setText(String.valueOf(rand.doubleValue()));
					spinnerTo.setSelection(CELSIUS);
					convertButton.performClick();
					fromVal = new BigDecimal(from.getText().toString());
					toVal = new BigDecimal(to.getText().toString());
					assertTrue("Fahrenheit is always bigger", fromVal.compareTo(toVal) > 0);
					spinnerTo.setSelection(KELVIN);
					convertButton.performClick();
					toVal = new BigDecimal(to.getText().toString());
					assertTrue("Fahrenheit is always bigger", fromVal.compareTo(toVal) > 0);
				}
			}
		});
	}
	*/
	/*
	 * This test checks that any temp in Fahrenheit below -40 will always yield a result
	 * in Kelvin and Celsius values that are greater than the given Fahrenheit value.
	 */
	/*
	public void testFahrenheitLessThan() {

		getActivity().runOnUiThread(new Runnable() {
			public void run() {
				ArrayList<BigDecimal> testLesserTemps = ListGenerator.getRealsGreaterThan(NUMTESTS,-40.0d);
				BigDecimal fromVal;
				BigDecimal toVal;
				spinnerFrom.requestFocus();
				spinnerFrom.setSelection(FARENHEIT);
				for(BigDecimal rand: testLesserTemps){
					from.setText(String.valueOf(rand.doubleValue()));
					spinnerTo.setSelection(CELSIUS);
					convertButton.performClick();
					fromVal = new BigDecimal(from.getText().toString());
					toVal = new BigDecimal(to.getText().toString());
					assertTrue("Fahrenheit is always bigger", fromVal.compareTo(toVal) > 0);
					spinnerTo.setSelection(KELVIN);
					convertButton.performClick();
					toVal = new BigDecimal(to.getText().toString());
					assertTrue("Fahrenheit is always bigger", fromVal.compareTo(toVal) > 0);
				}
			}
		});
	}
	*/
	/*
	 * This test will check values of Fahrenheit between -40 and 574.6. For all temps
	 * in this range, the value should be larger than it's converted value in Celsius, 
	 * and it should be less than th converted value in Kelvin.
	 */
	/*
	public void testFahrenheitInBetween() throws InterruptedException {
		getActivity().runOnUiThread(new Runnable() {
			public void run() {
				ArrayList<BigDecimal> testInBetweenTemps = null;
				try {
					testInBetweenTemps = ListGenerator.getRealsInBetween(NUMTESTS, 574.6d, -40d);
					Log.i(getName(), "ArrayList has been created for In between");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					Log.e(getName(), "Something went wrong creating the ArrayList for Inbetween");
				}
				BigDecimal fromVal;
				BigDecimal toVal;
				spinnerFrom.requestFocus();
				spinnerFrom.setSelection(FARENHEIT);
				for(BigDecimal rand: testInBetweenTemps){
					from.setText(String.valueOf(rand.doubleValue()));
					spinnerTo.setSelection(CELSIUS);
					convertButton.performClick();
					fromVal = new BigDecimal(from.getText().toString());
					toVal = new BigDecimal(to.getText().toString());
					assertTrue("Fahrenheit is bigger than Celsius", fromVal.compareTo(toVal) > 0);
					spinnerTo.setSelection(KELVIN);
					convertButton.performClick();
					toVal = new BigDecimal(to.getText().toString());
					assertTrue("Fahrenheit is less than Kelvin", fromVal.compareTo(toVal) < 0);
				}
			}
		});
	}*/
}
