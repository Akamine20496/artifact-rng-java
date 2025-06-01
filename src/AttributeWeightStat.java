import java.util.Objects;

public final class AttributeWeightStat extends AttributeStat {
	private final int attributeWeight;

    public AttributeWeightStat(String attributeName, int attributeWeight) {
        super(attributeName);
        this.attributeWeight = attributeWeight;
    }

    public int getAttributeWeight() {
        return attributeWeight;
    }
    
    @Override
    public String toString() {
        return "AttributeWeightStat{" +
                "attributeName='" + getAttributeName() + '\'' +
                ", attributeWeight=" + attributeWeight +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true; 												// Check if both references point to the same object
        if (!(o instanceof AttributeWeightStat)) return false; 						// Check if the object is of the same type
        AttributeWeightStat weightStat = (AttributeWeightStat) o; 					// Cast the object to WeightedStat
        return Integer.compare(attributeWeight, weightStat.attributeWeight) == 0 &&	// Compare attributeWeights
        		getAttributeName().equals(weightStat.getAttributeName());			// Compare attributeNames
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAttributeName(), attributeWeight); // Generate a hash code based on attributeName and attributeWeights
    }
}
