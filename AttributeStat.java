import java.util.Objects;

public sealed class AttributeStat permits ProbabilityStat, ValueStat, WeightedStat {
	private final String attribute;
    
    public AttributeStat(String attribute) {
    	this.attribute = attribute;
    }
    
    public String getAttribute() {
        return attribute;
    }
    
    @Override
    public String toString() {
        return "AttributeStat{" +
                "attribute='" + attribute + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true; 								// Check if both references point to the same object
        if (!(o instanceof AttributeStat)) return false; 			// Check if the object is of the same type
        AttributeStat attributeStat = (AttributeStat) o; 			// Cast the object to BaseStat
        return Objects.equals(attribute, attributeStat.attribute); 	// Compare attributes for equality
    }

    @Override
    public int hashCode() {
        return Objects.hash(attribute); // Generate a hash code based on the attribute
    }
}
