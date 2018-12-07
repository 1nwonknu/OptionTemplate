//package de.dfine.options;

import static org.junit.Assert.assertTrue;


import org.junit.Before;
import org.junit.Test;

public class OptionTest {
	
	private EuropeanCall callOption;
	private EuropeanPut putOption;
	
	@Before
	public void setUp() throws Exception {		
						
		int strike = 99;
		int initialPrice = 100;
		float remainingLifeTime = 1f;
		float riskFreeRate = 0.01f;
		float volatility = 0.9f;
		int numberSimulations = 1000;
		
		ScenarioGenerator scenarioGenerator = new ScenarioGenerator(numberSimulations);
		
		scenarioGenerator.computeRandomNumbers();
		
		callOption = new EuropeanCall(strike, initialPrice, remainingLifeTime, riskFreeRate, volatility, scenarioGenerator);
		putOption = new EuropeanPut(strike, initialPrice, remainingLifeTime, riskFreeRate, volatility, scenarioGenerator);
	
	}
	
	@Test
	public void testCall() {
		double value = callOption.computePrice();
		System.out.println("Price for European call " + value);
		assertTrue(value > 0);
	}
	
	@Test
	public void testPut() {
		double value = putOption.computePrice();
		System.out.println("Price for European put " + value);
		assertTrue(value > 0);
	}
	
	
	
}
