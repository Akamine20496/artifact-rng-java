import java.util.Objects;

public sealed abstract class AttributeStat permits AttributeProbabilityStat, AttributeValueStat, AttributeWeightStat {
	private final String attributeName;
    
    public AttributeStat(String attributeName) {
    	this.attributeName = attributeName;
    }
    
    public String getAttributeName() {
        return attributeName;
    }
    
    @Override
    public String toString() {
        return "AttributeStat{" +
                "attributeName='" + attributeName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true; 								// Check if both references point to the same object
        if (!(o instanceof AttributeStat)) return false; 			// Check if the object is of the same type
        AttributeStat attributeStat = (AttributeStat) o; 			// Cast the object to BaseStat
        return attributeName.equals(attributeStat.attributeName);	// Compare attributeNames for equality
    }

    @Override
    public int hashCode() {
        return Objects.hash(attributeName); // Generate a hash code based on the attributeName
    }
}
