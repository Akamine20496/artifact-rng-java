import java.util.Arrays;
import java.util.Objects;

public final class ValueStat extends AttributeStat {
	private final double[] values;
	
	public ValueStat(String attribute, double[] values) {
		super(attribute);
		this.values = values != null ? Arrays.copyOf(values, values.length) : new double[0];
	}
	
	public double[] getValues() {
        return Arrays.copyOf(values, values.length);
    }
	
	@Override
    public String toString() {
        return "ValueStat{" +
                "attribute='" + getAttribute() + '\'' +
                ", values=" + Arrays.toString(values) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true; 										// Check if both references point to the same object
        if (!(o instanceof ValueStat)) return false; 						// Check if the object is of the same type
        ValueStat valueStat = (ValueStat) o; 								// Cast the object to ValueStat
        return Arrays.equals(values, valueStat.values) && 					// Compare values arrays
                Objects.equals(getAttribute(), valueStat.getAttribute()); 	// Compare attributes
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAttribute(), Arrays.hashCode(values)); // Generate a hash code based on attribute and values
    }
}
