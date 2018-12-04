
public class EuropeanCall extends OptionTemplate {

	
	public EuropeanCall(int strike, int initialPrice, 
			float remainingLifeTime, float riskFreeRate, 
			float volatility, int numberSimulations) {
		
		super(strike, initialPrice, 
			remainingLifeTime, riskFreeRate, 
			volatility, numberSimulations);
	}
	
	public double payoff(double simulatedPrice, int strike) {
		return Math.max(simulatedPrice - strike, 0);
	}
		
	public double sdeSolution(float remainingLifeTime, float riskFreeRate, 
			float volatility, double gaussRand) {
		
		return Math.exp(remainingLifeTime*(riskFreeRate-0.5*volatility*volatility)) * 
				Math.exp(Math.sqrt(remainingLifeTime*volatility*volatility) * gaussRand);
	}
	
	public double computeExpectedValue(double sumPayOffs, 
			int numberSimulations, float riskFreeRate, 
			float remainingLifeTime) {
		return (sumPayOffs / numberSimulations) * Math.exp(-riskFreeRate * remainingLifeTime);
	}
	
}
