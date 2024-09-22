import java.util.Objects;

public final class ProbabilityStat extends AttributeStat {
	private final double probability;
	
	public ProbabilityStat(String attribute, double probability) {
		super(attribute);
		this.probability = validateProbability(probability);
	}
	
    private double validateProbability(double probability) {
        if (probability < 0 || probability > 100) {
            throw new IllegalArgumentException("Probability must be between 0 and 100.");
        }
        
        return probability;
    }
    
    public double getProbability() {
        return probability;
    }
    
    @Override
    public String toString() {
        return "ProbabilityStat{" +
                "attribute='" + getAttribute() + '\'' +
                ", probability=" + probability +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true; 											// Check if both references point to the same object
        if (!(o instanceof ProbabilityStat)) return false; 						// Check if the object is of the same type
        ProbabilityStat probabilityStat = (ProbabilityStat) o; 					// Cast the object to ProbabilityStat
        return Double.compare(probabilityStat.probability, probability) == 0 &&	// Compare probabilities
                Objects.equals(getAttribute(), probabilityStat.getAttribute());	// Compare attributes
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAttribute(), probability); // Generate a hash code based on attribute and probability
    }
}
