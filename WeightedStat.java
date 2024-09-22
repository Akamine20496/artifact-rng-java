import java.util.Objects;

public final class WeightedStat extends AttributeStat {
	private final int weight;

    public WeightedStat(String attribute, int weight) {
        super(attribute);
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }
    
    @Override
    public String toString() {
        return "WeightedStat{" +
                "attribute='" + getAttribute() + '\'' +
                ", weight=" + weight +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true; 											// Check if both references point to the same object
        if (!(o instanceof WeightedStat)) return false; 						// Check if the object is of the same type
        WeightedStat weightedStat = (WeightedStat) o; 							// Cast the object to WeightedStat
        return weight == weightedStat.weight && 								// Compare weights
                Objects.equals(getAttribute(), weightedStat.getAttribute()); 	// Compare attributes
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAttribute(), weight); // Generate a hash code based on attribute and weight
    }
}
