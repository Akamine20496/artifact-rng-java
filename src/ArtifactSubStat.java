public class ArtifactSubStat {
	private String attributeName;
	private double initialAttributeValue;
	private double attributeValue;
	private double prevAttributeValue;
	private boolean isInitialValueEmpty;
	
	private Artifact artifact = new Artifact();
	
	public ArtifactSubStat(String attributeName, double attributeValue) {
		this.attributeName = artifact.isSubAttribute(attributeName);
		initialAttributeValue = this.attributeValue = artifact.isSubAttributeValue(attributeName, attributeValue);
		isInitialValueEmpty = (attributeName == null);
	}
	
	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}
	
	public void setInitialAttributeValue(double initialAttributeValue) {
		this.initialAttributeValue = attributeValue = initialAttributeValue;
		isInitialValueEmpty = (initialAttributeValue == 0);
	}
	
	public void setAttributeValue(double attributeValue) {
		this.attributeValue = attributeValue;
	}
	
	public void setPrevAttributeValue(double currAttributeValue) {
		prevAttributeValue = currAttributeValue;
	}
	
	public String getAttributeName() {
		return attributeName;
	}
	
	public double getInitialAttributeValue() {
		return initialAttributeValue;
	}
	
	public double getAttributeValue() {
		return attributeValue;
	}
	
	public double getPrevAttributeValue() {
		return prevAttributeValue;
	}
	
	public boolean getIsInitialValueEmpty() {
		return isInitialValueEmpty;
	}
	
	public void addAttributeValue(double attributeValue) {
		prevAttributeValue = this.attributeValue;
		this.attributeValue += attributeValue;
	}
	
	public String getSubStat() {
		return attributeName == null ? "None" : artifact.formatSubStat(attributeName, attributeValue);
	}
	
	@Override
	public String toString() {
		return "ArtifactSubStat{" +
                "attributeName='" + attributeName + '\'' +
                ", initialAttributeValue=" + initialAttributeValue +
                ", attributeValue=" + attributeValue +
                ", prevAttributeValue=" + prevAttributeValue +
                ", isInitialValueEmpty=" + isInitialValueEmpty +
                '}';
	}
	
	@Override
	public boolean equals(Object obj) {
	    // Check if the object is compared with itself
	    if (this == obj) {
	        return true;
	    }
	    
	    // Check if the object is an instance of ArtifactSubStat
	    if (!(obj instanceof ArtifactSubStat)) {
	        return false;
	    }
	    
	    // Typecast obj to ArtifactSubStat so that we can compare data members
	    ArtifactSubStat artifactSubStat = (ArtifactSubStat) obj;
	    
	    // Compare the relevant fields for equality
	    return Double.compare(initialAttributeValue, artifactSubStat.initialAttributeValue) == 0 &&
	    		Double.compare(attributeValue, artifactSubStat.attributeValue) == 0 &&
	    		Double.compare(prevAttributeValue, artifactSubStat.prevAttributeValue) == 0 &&
	    		isInitialValueEmpty == artifactSubStat.isInitialValueEmpty &&
	    		(attributeName == null ? artifactSubStat.attributeName == null : 
	    		attributeName.equals(artifactSubStat.attributeName));
	}
}
