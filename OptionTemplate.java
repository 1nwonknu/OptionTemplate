import java.util.Vector;

public abstract class OptionTemplate {

	public int strike;
	public int initialPrice;
	public float remainingLifeTime; // unit: years
	public float riskFreeRate;
	public float volatility;
	
	public ScenarioGenerator scenarioGenerator;
	
	//public int numberSimulations;
	
	OptionTemplate(int strike, int initialPrice, 
			float remainingLifeTime, float riskFreeRate, 
			float volatility, ScenarioGenerator scenarioGenerator) {
			
		this.strike = strike;
		this.initialPrice = initialPrice;
		this.remainingLifeTime = remainingLifeTime;
		this.riskFreeRate = riskFreeRate;
		this.volatility = volatility;
		this.scenarioGenerator = scenarioGenerator;
		//this.numberSimulations = numberSimulations;		
				
	}
	
	protected abstract double payoff(double simulatedPrice, int strike);
	
	protected abstract double sdeSolution( 
			float remainingLifeTime, float riskFreeRate, 
			float volatility, double gaussRand);
	
	protected abstract double computeExpectedValue(double sumPayOffs, 
			int numberSimulations, float riskFreeRate, 
			float remainingLifeTime);
	
	
	//protected abstract Vector getRandomNumbers(int numberSimulations) ;
	
	public final double computePrice() {
		
		double sumPayOffs = 0;
		//float S_adjust = (float) (initialPrice * Math.exp(remainingLifeTime*(riskFreeRate-0.5*volatility*volatility)));
		
		Vector randomNumbers = scenarioGenerator.getRandomNumbers(); //getRandomNumbers(numberSimulations);
		
		int numberSimulations = randomNumbers.size();
		
		double randomNumber;
		double simulatedPrice;
		for(int simulationCounter = 1; simulationCounter <= numberSimulations; simulationCounter++){
			//gaussRand = (float) Math.random();//boxMueller();
			randomNumber = (double) randomNumbers.elementAt(simulationCounter-1);
			//System.out.println(gaussRand);
			//double simulatedPrice = S_adjust * 
			//		Math.exp(Math.sqrt(remainingLifeTime*volatility*volatility) * gaussRand);
			//System.out.println(simulatedPrice);
			
			simulatedPrice = initialPrice * sdeSolution(remainingLifeTime, riskFreeRate, volatility, randomNumber);
			
			sumPayOffs += payoff(simulatedPrice, strike);
		}	
		//System.out.println(sumPayOffs);
		
		double optionPrice = computeExpectedValue(sumPayOffs, numberSimulations, riskFreeRate, remainingLifeTime);
		//double optionPrice = (sumPayOffs / numberSimulations) * Math.exp(-riskFreeRate * remainingLifeTime);
		//System.out.println(optionPrice);
		return optionPrice;
	}
	
	
}
