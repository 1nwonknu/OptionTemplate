import java.util.Vector;

public class ScenarioGenerator {

	
	
	private Vector randomNumbers;
	int numberSimulations;
	
	public ScenarioGenerator(int numberSimulations) {
		this.numberSimulations = numberSimulations;
	}
	
	protected void computeRandomNumbers()  {
		randomNumbers = new Vector<>();
		
		for(int simulationCounter = 1; simulationCounter <= numberSimulations; simulationCounter++){
			randomNumbers.addElement(Math.random());
		}		
	}
	
	public Vector getRandomNumbers() {
		return randomNumbers;
	}	
		
}
