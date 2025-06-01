public final class ArtifactStat {
	private Artifact artifact = new Artifact();
	
	private ArtifactSubStat[] artifactSubStats = new ArtifactSubStat[4];
	
	private String artifactPiece;
	private String mainAttribute;
	private boolean isMax;
	private int slotNumber;
	private int upgradeCounter;
	private int totalUpgrade;
	private int maxUpgrade;
	
	private String currentNewSubStat;
	private String currentUpgradedSubStat;
	
	public ArtifactStat() {
		artifactPiece = artifact.generateRandomPiece();
		mainAttribute = null;
		initializeSubStats();
	}
	
	public ArtifactStat(String artifactPiece) throws IllegalArgumentException {
		this.artifactPiece = artifact.isArtifactPiece(artifactPiece);
		mainAttribute = null;
		initializeSubStats();
	}
	
	public ArtifactStat(String artifactPiece, String mainAttribute) throws IllegalArgumentException {
		this.artifactPiece = artifact.isArtifactPiece(artifactPiece);
		this.mainAttribute = artifact.isMainAttribute(mainAttribute);
		initializeSubStats();
	}
	
	public ArtifactStat(String artifactPiece, String mainAttribute, ArtifactSubStat... artifactSubStats) throws IllegalArgumentException {
		this.artifactPiece = artifact.isArtifactPiece(artifactPiece);
		this.mainAttribute = artifact.isMainAttribute(mainAttribute);
		initializeSubStats(artifactSubStats);
	}
	
	public void setArtifactPiece(String artifactPiece) {
		this.artifactPiece = artifact.isArtifactPiece(artifactPiece);
	}

	public void setMainAttribute(String mainAttribute) {
		this.mainAttribute = artifact.isMainAttribute(mainAttribute);
	}

	public void setMaxUpgrade(int maxUpgrade) throws IllegalArgumentException {
		if (maxUpgrade == 4 || maxUpgrade == 5) {
			this.maxUpgrade = maxUpgrade;
		} else {
			throw new IllegalArgumentException("Max Upgrade is either 4 or 5");
		}
	}

	public String getArtifactPiece() {
		return artifactPiece;
	}
	
	public String getMainAttribute() {
		return mainAttribute;
	}

	public int getMaxUpgrade() {
		return maxUpgrade;
	}
	
	public String getCurrentNewSubStat() {
		return currentNewSubStat;
	}
	
	public String getCurrentUpgradedSubStat() {
		return currentUpgradedSubStat;
	}
	
	public int getArraySubStatsLength() {
		return artifactSubStats.length;
	}
	
	/**
	 * Generates random Main Attribute, Max Upgrade, and Sub-Stats. <br> <br>
	 * 
	 * Use this when there is no sub-stats. When sub-stat is already defined but used this function, <br>
	 * this will replace the already defined ones.
	 */
	public void generateStat() {
		if (mainAttribute != null && maxUpgrade != 0) {
			return;
		}
		
		mainAttribute = artifact.generateMainAttribute(artifactPiece);
		maxUpgrade = artifact.generateMaxUpgrade();

		artifactSubStats[0] = artifact.generateSubStat(mainAttribute);
		artifactSubStats[1] = artifact.generateSubStat(mainAttribute, artifactSubStats[0].getAttributeName());
		artifactSubStats[2] = artifact.generateSubStat(mainAttribute, artifactSubStats[0].getAttributeName(), artifactSubStats[1].getAttributeName());
		artifactSubStats[3] = maxUpgrade == 4 ? new ArtifactSubStat(null, 0) : 
			artifact.generateSubStat(mainAttribute, 
										artifactSubStats[0].getAttributeName(), 
										artifactSubStats[1].getAttributeName(), 
										artifactSubStats[2].getAttributeName());
	}
	
	private void generateFourthSubStat() throws NullPointerException {
		if (mainAttribute == null && maxUpgrade == 0) {
			throw new NullPointerException("mainAttribute is null and maxUpgrade is 0");
		}
		
		artifactSubStats[3] = artifact.generateSubStat(mainAttribute, 
														artifactSubStats[0].getAttributeName(), 
														artifactSubStats[1].getAttributeName(), 
														artifactSubStats[2].getAttributeName());
		
		currentNewSubStat = artifact.formatSubStat(0, artifactSubStats[3]);
	}
	
	/**
	 * Generates random Max Upgrade, Sub-Stat 1 and Sub-Stat 2 values (If Sub Attribute Name is present), and Sub-Stat 3 and Sub-Stat 4. <br> <br>
	 * 
	 * Use this when Sub-Stat 1 and Sub-Stat 2's attribute name is defined but no values (or 0, 0.0, 0.00), <br>
	 * but Sub-Stat 3, and Sub-Stat 4 has no value yet like <code>ArtifactSubStat(null, 0)</code>.
	 * @throws NullPointerException
	 */
	public void generateDefinedAffixModeSubStats() throws NullPointerException {
		if (mainAttribute == null) {
			throw new NullPointerException("mainAttribute is null");
		}
		
		maxUpgrade = artifact.generateMaxUpgrade();
		
		if (artifactSubStats[0].getIsInitialValueEmpty() && artifactSubStats[1].getIsInitialValueEmpty()) {
			artifactSubStats[0].setInitialAttributeValue(artifact.generateSubAttributeValue(artifactSubStats[0].getAttributeName()));
			artifactSubStats[1].setInitialAttributeValue(artifact.generateSubAttributeValue(artifactSubStats[1].getAttributeName()));
		}
		
		artifactSubStats[2] = artifact.generateSubStat(mainAttribute, artifactSubStats[0].getAttributeName(), artifactSubStats[1].getAttributeName());
		artifactSubStats[3] = maxUpgrade == 4 ? new ArtifactSubStat(null, 0) : 
			artifact.generateSubStat(mainAttribute, 
										artifactSubStats[0].getAttributeName(), 
										artifactSubStats[1].getAttributeName(), 
										artifactSubStats[2].getAttributeName());
	}
	
	public void rerollStat() {
		removeSubStatUpgrades();
		
		slotNumber = 0;
		upgradeCounter = 0;
		totalUpgrade = 0;
		isMax = false;
	}
	
	public void resetStat() {
		resetSubStats();
		
		mainAttribute = null;
		
		slotNumber = 0;
		upgradeCounter = 0;
		maxUpgrade = 0;
		totalUpgrade = 0;
		isMax = false;
	}
	
	public void upgradeSubStatValue() throws NullPointerException {
		if (mainAttribute == null) {
			throw new NullPointerException("mainAttribute is null");
		}
		
		if (artifactSubStats[3].getAttributeName() == null) {
			generateFourthSubStat();
		} else {
			if (!isMax) { 													// if the total upgrades of 5 or 4 is reached
				if (upgradeCounter == 0) { 									// if the counter is 0, it will loop
					while (upgradeCounter == 0) { 							// until the upgrade counter is not 0
						slotNumber = artifact.generateRandomSlot();
						upgradeCounter = artifact.generateUpgradeTimes();
						totalUpgrade += upgradeCounter;
					}

					if (totalUpgrade == maxUpgrade) { 						// if the total upgrade reaches 5 or 4, set the isMax to true
						isMax = true; 										// and upgrade the value
						selectSlot(slotNumber);
					} else if (totalUpgrade > maxUpgrade) { 				// if the total upgrade exceeds 5 or 4, deduct it from counter
						totalUpgrade -= upgradeCounter; 					// and set the counter to 0, then retry the process
						upgradeCounter = 0;
						upgradeSubStatValue();
					} else {
						selectSlot(slotNumber); 							// if the total upgrade is not 5
					}
				} else {
					selectSlot(slotNumber); 								// if the counter is not 0
				}
			} else {
				selectSlot(slotNumber); 									// if the isMax is true
			}
		}
	}
	
	private void selectSlot(int slotNumber) throws NullPointerException {
		if (mainAttribute == null) {
			throw new NullPointerException("mainAttribute is null");
		}
		
		switch (slotNumber) {
			case 1 -> {
				artifactSubStats[0].addAttributeValue(artifact.generateSubAttributeValue(artifactSubStats[0].getAttributeName()));
				currentUpgradedSubStat = artifact.formatSubStat(2, artifactSubStats[0]);
			}
			case 2 -> {
				artifactSubStats[1].addAttributeValue(artifact.generateSubAttributeValue(artifactSubStats[1].getAttributeName()));
				currentUpgradedSubStat = artifact.formatSubStat(2, artifactSubStats[1]);
			}
			case 3 -> {
				artifactSubStats[2].addAttributeValue(artifact.generateSubAttributeValue(artifactSubStats[2].getAttributeName()));
				currentUpgradedSubStat = artifact.formatSubStat(2, artifactSubStats[2]);
			}
			case 4 -> {
				artifactSubStats[3].addAttributeValue(artifact.generateSubAttributeValue(artifactSubStats[3].getAttributeName()));
				currentUpgradedSubStat = artifact.formatSubStat(2, artifactSubStats[3]);
			}
		}
		
		upgradeCounter--;
	}
	
	/**
	 * Skips upgrading by one and directly upgrades to what {@link ArtifactStat#maxUpgrade} is set. <br>
	 * Must generate stat first from {@link ArtifactStat#generateStat()} or {@link ArtifactStat#generateDefinedAffixModeSubStats()}.
	 * @return array of the final sub-stats
	 * @throws NullPointerException
	 * 
	 * @see {@link ArtifactStat#setMaxUpgrade(maxUpgrade)}
	 */
	public String[] skipUpgradeSubStats() throws NullPointerException {
		if (mainAttribute == null) {
			throw new NullPointerException("mainAttribute is null");
		}
		
		String s1, s2, s3, s4;

		for (int counter = 1; counter <= 5; counter++) {
			upgradeSubStatValue();
		}

		s1 = artifact.formatSubStat(3, artifactSubStats[0]);
		s2 = artifact.formatSubStat(3, artifactSubStats[1]);
		s3 = artifact.formatSubStat(3, artifactSubStats[2]);
		s4 = maxUpgrade == 4 ? artifact.formatSubStat(0, artifactSubStats[3]) : artifact.formatSubStat(3, artifactSubStats[3]);
		
		return new String[] { s1, s2, s3, s4 };
	}
	
	/**
	 * Updates the specific array property from {@link ArtifactStat#artifactSubStats} <br>
	 * <b>Index 0 -> Sub-Stat 1</b> <br>
	 * <b>Index 1 -> Sub-Stat 2</b> <br>
	 * <b>Index 2 -> Sub-Stat 3</b> <br>
	 * <b>Index 3 -> Sub-Stat 4</b>
	 * @param index Their index position of {@link ArtifactStat#artifactSubStats}
	 * @param artifactSubStat New {@link ArtifactSubStat} object
	 * @throws IndexOutOfBoundsException
	 * @throws NullPointerException
	 */
	public void updateArtifactSubStat(int index, ArtifactSubStat artifactSubStat) throws IndexOutOfBoundsException, NullPointerException {
	    if (index < 0 || index >= artifactSubStats.length) {
	        throw new IndexOutOfBoundsException("Index out of bounds: " + index);
	    }
	    
	    if (artifactSubStat == null) {
	        throw new NullPointerException("The artifactSubStat must not be null");
	    }
	    
	    if (hasDuplicateSubStat(artifactSubStat)) {
			throw new IllegalArgumentException("There is duplicate sub-stat found");
		}
	    
	    ArtifactSubStat retrivedArtifactSubStat = artifactSubStats[index];
	    
	    retrivedArtifactSubStat.setAttributeName(artifactSubStat.getAttributeName());
	    
	    if (retrivedArtifactSubStat.getIsInitialValueEmpty()) {
	    	retrivedArtifactSubStat.setInitialAttributeValue(artifactSubStat.getInitialAttributeValue());
	    	retrivedArtifactSubStat.setPrevAttributeValue(artifactSubStat.getAttributeValue());
		} else {
			retrivedArtifactSubStat.setAttributeValue(artifactSubStat.getAttributeValue());
			retrivedArtifactSubStat.setPrevAttributeValue(artifactSubStat.getPrevAttributeValue());
		}
	}
	
	/**
	 * Updates the array properties from {@link ArtifactStat#artifactSubStats} <br>
	 * <b>Index 0 -> Sub-Stat 1</b> <br>
	 * <b>Index 1 -> Sub-Stat 2</b> <br>
	 * <b>Index 2 -> Sub-Stat 3</b> <br>
	 * <b>Index 3 -> Sub-Stat 4</b>
	 * @param artifactSubStats {@link ArtifactSubStat} objects
	 * @throws NullPointerException
	 * @throws IllegalArgumentException
	 */
	public void updateArtifactSubStats(ArtifactSubStat... artifactSubStats) throws NullPointerException, IllegalArgumentException {
		if (artifactSubStats == null) {
			throw new NullPointerException("The array must not be null");
		}
		
		if (artifactSubStats.length > this.artifactSubStats.length) {
			throw new IllegalArgumentException("The array's length exceeds the defined array's length");
		}
		
		if (hasDuplicateSubStat(artifactSubStats)) {
			throw new IllegalArgumentException("There is duplicate sub-stat found");
		}
		
		for (int index = 0; index < artifactSubStats.length; index++) {
			updateArtifactSubStat(index, artifactSubStats[index]);
		}
	}
	
	/**
	 * Gets a specific sub-stat at index <br>
	 * <b>Index 0 -> Sub-Stat 1</b> <br>
	 * <b>Index 1 -> Sub-Stat 2</b> <br>
	 * <b>Index 2 -> Sub-Stat 3</b> <br>
	 * <b>Index 3 -> Sub-Stat 4</b>
	 * @param index of the {@link ArtifactSubStat} from {@link ArtifactStat#artifactSubStats}
	 * @return {@link ArtifactSubStat} object
	 * @throws IndexOutOfBoundsException
	 */
	public ArtifactSubStat getSubStatAt(int index) throws IndexOutOfBoundsException {
		if (index < 0 || index >= artifactSubStats.length) {
			throw new IndexOutOfBoundsException("Index out of bounds: " + index);
		}
		
		return artifactSubStats[index];
	}
	
	private void removeSubStatUpgrades() {
		for (int index = 0; index < artifactSubStats.length; index++) {
			ArtifactSubStat artifactSubStat = artifactSubStats[index];
			
			if ((artifactSubStat.equals(artifactSubStats[3])) && maxUpgrade == 4) {
				artifactSubStat.setAttributeName(null);
				artifactSubStat.setInitialAttributeValue(0);
				artifactSubStat.setPrevAttributeValue(artifactSubStat.getAttributeValue());
			} else {
				artifactSubStat.setAttributeValue(artifactSubStat.getInitialAttributeValue());
				artifactSubStat.setPrevAttributeValue(artifactSubStat.getAttributeValue());
			}
		}
	}
	
	private void resetSubStats() {
		for (int index = 0; index < artifactSubStats.length; index++) {
			ArtifactSubStat artifactSubStat = artifactSubStats[index];
			
			artifactSubStat.setAttributeName(null);
			artifactSubStat.setInitialAttributeValue(0);
			artifactSubStat.setPrevAttributeValue(artifactSubStat.getAttributeValue());
		}
	}
	
	// for the artifactSubStats defined property
	private boolean hasDuplicateSubStat(ArtifactSubStat artifactSubStat) {
		boolean isDuplicate = false;
		
		for (int index = 0; index < artifactSubStats.length; index++) {
			ArtifactSubStat subStat = artifactSubStats[index];
			
			try {
				if (subStat.getAttributeName() != null && subStat.equals(artifactSubStat)) {
					isDuplicate = true;
					break;
				}
			} catch (NullPointerException npe) {
				continue;
			}
		}
		
		return isDuplicate;
	}
	
	// for the artifactSubStats parameter
	private boolean hasDuplicateSubStat(ArtifactSubStat... artifactSubStats) {
		boolean isDuplicate = false;
		
		for (int index = 1; index < artifactSubStats.length; index++) {
			ArtifactSubStat baseSubStat = artifactSubStats[0];
			ArtifactSubStat nextSubStat = artifactSubStats[index];
			
			try {
				if (baseSubStat.equals(nextSubStat)) {
					isDuplicate = true;
					break;
				}
			} catch (NullPointerException npe) {
				continue;
			}
		}
		
		return isDuplicate;
	}
	
    private void initializeSubStats() {
        for (int index = 0; index < artifactSubStats.length; index++) {
            artifactSubStats[index] = new ArtifactSubStat(null, 0);
        }
    }
    
    private void initializeSubStats(ArtifactSubStat... artifactSubStats) throws IllegalArgumentException {
    	int incomingArtifactSubStatsLength = artifactSubStats.length;
    	
    	if (incomingArtifactSubStatsLength == 1) {
    		throw new IllegalArgumentException("Cannot generate stat with only 1 sub-stat");
    	}
    	
        for (int index = 0; index < this.artifactSubStats.length; index++) {
        	this.artifactSubStats[index] = new ArtifactSubStat(null, 0);
        	
        	if (index < incomingArtifactSubStatsLength) {
        		updateArtifactSubStat(index, artifactSubStats[index]);
        	}
        }
    }
	
	private String addContainerToText(String str) {
	    // Define a minimum width for the container
	    int minWidth = 20; // Minimum width for the container
	    String[] lines = str.split("\n"); // Split the input into lines
	    int maxLineLength = 0;

	    // Determine the maximum line length
	    for (String line : lines) {
	        maxLineLength = Math.max(maxLineLength, line.length());
	    }

	    // Calculate the width of the container
	    int width = Math.max(minWidth, maxLineLength + 4); // +4 for borders and padding

	    // Create a StringBuilder to build the output
	    StringBuilder sb = new StringBuilder();

	    // Create the top border
	    String topBorder = "+" + "-".repeat(width - 2) + "+";
	    sb.append("\n").append(topBorder).append("\n");

	    // Print each line within the container
	    for (String line : lines) {
	        String contentLine = "| " + line + " ".repeat(width - line.length() - 4) + " |"; // -4 for the borders and space
	        sb.append(contentLine).append("\n");
	    }

	    // Print the bottom border
	    sb.append(topBorder).append("\n");

	    // Return the final string
	    return sb.toString();
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
        sb.append("Artifact Piece: ").append(artifactPiece).append("\n");
        sb.append("Main Attribute: ").append(mainAttribute).append("\n");
        sb.append("Max Upgrade: ").append(maxUpgrade).append("\n\n");
        sb.append("Sub-Stats:\n");
        
        for (ArtifactSubStat subStat : artifactSubStats) {
            sb.append(subStat.getSubStat()).append("\n");
        }
        
		return addContainerToText(sb.toString());
	}
}
