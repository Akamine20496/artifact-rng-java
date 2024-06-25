public class Stat {
	private String attribute;
	private double probability;
	private double[] values;
	
	public Stat(String attribute, double probability) {
		this.attribute = attribute;
		this.probability = probability;
	}
	
	public Stat(String attribute, double[] values) {
		this.attribute = attribute;
		this.values = values;
	}
	
	public String getAttribute() {
		return attribute;
	}
	
	public double getProbability() {
		return probability;
	}
	
	public double[] getValues() {
		return values;
	}
}
