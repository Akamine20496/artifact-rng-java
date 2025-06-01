import java.util.Objects;

public final class AttributeProbabilityStat extends AttributeStat {
	private final double attributeProbability;
	
	public AttributeProbabilityStat(String attributeName, double attributeProbability) {
		super(attributeName);
		this.attributeProbability = validateProbability(attributeProbability);
	}
	
    private double validateProbability(double probability) {
        if (probability < 0 || probability > 100) {
            throw new IllegalArgumentException("Probability must be between 0 and 100.");
        }
        
        return probability;
    }
    
    public double getAttributeProbability() {
        return attributeProbability;
    }
    
    @Override
    public String toString() {
        return "AttributeProbabilityStat{" +
                "attributeName='" + getAttributeName() + '\'' +
                ", attributeProbability=" + attributeProbability +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true; 																		// Check if both references point to the same object
        if (!(o instanceof AttributeProbabilityStat)) return false; 										// Check if the object is of the same type
        AttributeProbabilityStat attributeProbabilityStat = (AttributeProbabilityStat) o; 					// Cast the object to attributeProbabilityStat
        return Double.compare(attributeProbability, attributeProbabilityStat.attributeProbability) == 0 &&	// Compare attributeProbabilities
        		getAttributeName().equals(attributeProbabilityStat.getAttributeName());						// Compare attributeNames
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAttributeName(), attributeProbability); // Generate a hash code based on attributeName and attributeProbability
    }
}
