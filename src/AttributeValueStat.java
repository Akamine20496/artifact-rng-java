import java.util.Arrays;
import java.util.Objects;

public final class AttributeValueStat extends AttributeStat {
	private final double[] attributeValues;
	
	public AttributeValueStat(String attributeName, double[] attributeValues) {
		super(attributeName);
		this.attributeValues = attributeValues != null ? Arrays.copyOf(attributeValues, attributeValues.length) : new double[0];
	}
	
	public double[] getAttributeValues() {
        return Arrays.copyOf(attributeValues, attributeValues.length);
    }
	
	@Override
    public String toString() {
        return "AttributeValueStat{" +
                "attributeName='" + getAttributeName() + '\'' +
                ", attributeValues=" + Arrays.toString(attributeValues) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true; 														// Check if both references point to the same object
        if (!(o instanceof AttributeValueStat)) return false; 								// Check if the object is of the same type
        AttributeValueStat attributeValueStat = (AttributeValueStat) o; 					// Cast the object to attributeValueStat
        return Arrays.equals(attributeValues, attributeValueStat.attributeValues) && 		// Compare attributeValues arrays
        		getAttributeName().equals(attributeValueStat.getAttributeName()); 			// Compare attributeNames
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAttributeName(), Arrays.hashCode(attributeValues)); // Generate a hash code based on attributeName and attributeValues
    }
}
