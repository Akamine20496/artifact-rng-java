import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public final class Artifact {
	public static final String FLOWER = "Flower of Life";
	public static final String FEATHER = "Plume of Death";
	public static final String SANDS = "Sands of Eon";
	public static final String GOBLET = "Goblet of Eonothem";
	public static final String CIRCLET = "Circlet of Logos";
	
	public static final String[] MAIN_ATTRIBUTE_NAMES = {
			Attribute.HP_FLAT, 
			Attribute.ATK_FLAT, 
			Attribute.HP_PER, 
			Attribute.ATK_PER, 
			Attribute.DEF_PER, 
			Attribute.ENERGY_RECHARGE, 
			Attribute.ELEMENTAL_MASTERY,
			Attribute.CRIT_RATE, 
			Attribute.CRIT_DMG,
			Attribute.PYRO_DMG_BONUS, 
			Attribute.ELECTRO_DMG_BONUS, 
			Attribute.CRYO_DMG_BONUS,
			Attribute.HYDRO_DMG_BONUS, 
			Attribute.DENDRO_DMG_BONUS, 
			Attribute.ANEMO_DMG_BONUS,
			Attribute.GEO_DMG_BONUS, 
			Attribute.PHYSICAL_DMG_BONUS, 
			Attribute.HEALING_BONUS
	};
	
	private static final String[] ARTIFACT_PIECES = { 
			FLOWER, 
			FEATHER, 
			SANDS, 
			GOBLET, 
			CIRCLET 
	};
	private static final String[] FLOWER_OF_LIFE = { Attribute.HP_FLAT };
	private static final String[] PLUME_OF_DEATH = { Attribute.ATK_FLAT };
	private static final String[] SANDS_OF_EON = { 
			Attribute.HP_PER, 
			Attribute.ATK_PER, 
			Attribute.DEF_PER,
			Attribute.ENERGY_RECHARGE, 
			Attribute.ELEMENTAL_MASTERY
	};
	private static final String[] GOBLET_OF_EONOTHEM = {
			Attribute.HP_PER,
			Attribute.ATK_PER,
			Attribute.DEF_PER,
			Attribute.PYRO_DMG_BONUS,
			Attribute.ELECTRO_DMG_BONUS,
			Attribute.CRYO_DMG_BONUS,
			Attribute.HYDRO_DMG_BONUS,
			Attribute.DENDRO_DMG_BONUS,
			Attribute.ANEMO_DMG_BONUS,
			Attribute.GEO_DMG_BONUS,
			Attribute.PHYSICAL_DMG_BONUS,
			Attribute.ELEMENTAL_MASTERY
	};
	private static final String[] CIRCLET_OF_LOGOS = {
			Attribute.HP_PER,
			Attribute.ATK_PER,
			Attribute.DEF_PER,
			Attribute.HEALING_BONUS,
			Attribute.ELEMENTAL_MASTERY,
			Attribute.CRIT_RATE,
			Attribute.CRIT_DMG
	};
	
	private final String FLOWER_STAT = Attribute.HP_FLAT;
	private final String FEATHER_STAT = Attribute.ATK_FLAT;
	
	private final List<AttributeProbabilityStat> LIST_SANDS_STATS = Arrays.asList(
			new AttributeProbabilityStat(Attribute.HP_PER, 26.68),
			new AttributeProbabilityStat(Attribute.ATK_PER, 26.66),
			new AttributeProbabilityStat(Attribute.DEF_PER, 26.66),
			new AttributeProbabilityStat(Attribute.ENERGY_RECHARGE, 10.00),
			new AttributeProbabilityStat(Attribute.ELEMENTAL_MASTERY, 10.00)
	);
	private final List<AttributeProbabilityStat> LIST_GOBLET_STATS = Arrays.asList(
			new AttributeProbabilityStat(Attribute.HP_PER, 19.25),
			new AttributeProbabilityStat(Attribute.ATK_PER, 19.25),
			new AttributeProbabilityStat(Attribute.DEF_PER, 19.00),
			new AttributeProbabilityStat(Attribute.PYRO_DMG_BONUS, 5.00),
			new AttributeProbabilityStat(Attribute.ELECTRO_DMG_BONUS, 5.00),
			new AttributeProbabilityStat(Attribute.CRYO_DMG_BONUS, 5.00),
			new AttributeProbabilityStat(Attribute.HYDRO_DMG_BONUS, 5.00),
			new AttributeProbabilityStat(Attribute.DENDRO_DMG_BONUS, 5.00),
			new AttributeProbabilityStat(Attribute.ANEMO_DMG_BONUS, 5.00),
			new AttributeProbabilityStat(Attribute.GEO_DMG_BONUS, 5.00),
			new AttributeProbabilityStat(Attribute.PHYSICAL_DMG_BONUS, 5.00),
			new AttributeProbabilityStat(Attribute.ELEMENTAL_MASTERY, 2.50)
	);
	private final List<AttributeProbabilityStat> LIST_CIRCLET_STATS = Arrays.asList(
			new AttributeProbabilityStat(Attribute.HP_PER, 22.00),
			new AttributeProbabilityStat(Attribute.ATK_PER, 22.00),
			new AttributeProbabilityStat(Attribute.DEF_PER, 22.00),
			new AttributeProbabilityStat(Attribute.CRIT_RATE, 10.00),
			new AttributeProbabilityStat(Attribute.CRIT_DMG, 10.00),
			new AttributeProbabilityStat(Attribute.HEALING_BONUS, 10.00),
			new AttributeProbabilityStat(Attribute.ELEMENTAL_MASTERY, 4.00)
	);
	
	private final List<AttributeWeightStat> LIST_WEIGHT_STATS = Arrays.asList(
			new AttributeWeightStat(Attribute.HP_FLAT, 6),
			new AttributeWeightStat(Attribute.ATK_FLAT, 6),
			new AttributeWeightStat(Attribute.DEF_FLAT, 6),
			new AttributeWeightStat(Attribute.HP_PER, 4),
			new AttributeWeightStat(Attribute.ATK_PER, 4),
			new AttributeWeightStat(Attribute.DEF_PER, 4),
			new AttributeWeightStat(Attribute.ENERGY_RECHARGE, 4),
			new AttributeWeightStat(Attribute.ELEMENTAL_MASTERY, 4),
			new AttributeWeightStat(Attribute.CRIT_RATE, 3),
			new AttributeWeightStat(Attribute.CRIT_DMG, 3)
	);
	
	private Random rand = new Random();
	private Attribute attribute = Attribute.getInstance();
	
	public String[] getArtifactPiece() {
		return ARTIFACT_PIECES;
	}
	
	public String[] getFlowerPiece() {
		return FLOWER_OF_LIFE;
	}
	
	public String[] getFeatherPiece() {
		return PLUME_OF_DEATH;
	}
	
	public String[] getSandsPiece() {
		return SANDS_OF_EON;
	}
	
	public String[] getGobletPiece() {
		return GOBLET_OF_EONOTHEM;
	}
	
	public String[] getCircletPiece() {
		return CIRCLET_OF_LOGOS;
	}
	
	// RESPONSIBLE FOR GENERATING SPECIFIC PARTS
	
	public String generateRandomPiece() {
		int randomIndex = rand.nextInt(ARTIFACT_PIECES.length);
		return ARTIFACT_PIECES[randomIndex];
	}
	
	public String generateMainAttribute(String artifactPiece) throws IllegalArgumentException {
		return switch (artifactPiece) {
			case FLOWER -> FLOWER_STAT;
			case FEATHER -> FEATHER_STAT;
			case SANDS -> generateAttributeName(LIST_SANDS_STATS);
			case GOBLET -> generateAttributeName(LIST_GOBLET_STATS);
			case CIRCLET -> generateAttributeName(LIST_CIRCLET_STATS);
			// Throw an exception if none of the cases is met
			default -> throw new IllegalArgumentException("Invalid artifactPiece: " + artifactPiece);
		};
	}
	
	public String generateSubAttribute(String... attributeNames) throws NullPointerException {
		List<String> notSpecialAttributeNames = new ArrayList<>();
		
		for (String attributeName : attributeNames) {
			if (attributeName == null) {
				throw new NullPointerException("attributeName must not be null.");
			}
			
			if (attribute.isNotSpecialAttributeName(attributeName)) {
				notSpecialAttributeNames.add(attributeName);
			}
		}
		
		List<AttributeProbabilityStat> listStats = getStatProbabilityList(notSpecialAttributeNames);
		
		// for debugging purposes
		checkIfHundredPercent(listStats);
		
		return generateAttributeName(listStats);
	}
	
	public ArtifactSubStat generateSubStat(String... attributeNames) throws NullPointerException {
		List<String> notSpecialAttributeNames = new ArrayList<>();
		
		for (String attributeName : attributeNames) {
			if (attributeName == null) {
				throw new NullPointerException("attributeName must not be null.");
			}
			
			if (attribute.isNotSpecialAttributeName(attributeName)) {
				notSpecialAttributeNames.add(attributeName);
			}
		}
		
		List<AttributeProbabilityStat> listStats = getStatProbabilityList(notSpecialAttributeNames);
		
		// for debugging purposes
		checkIfHundredPercent(listStats);
		
		String generatedAttributeName = generateAttributeName(listStats);
		double generatedAttributeValue = generateSubAttributeValue(generatedAttributeName);
		
		return new ArtifactSubStat(generatedAttributeName, generatedAttributeValue);
	}
	
	public int generateMaxUpgrade() {
		double noOfSubStatChance = generateNumber();
		
		int[] maxUpgrades = { 4, 5 };
		
		if (noOfSubStatChance <= 66.00) {
			return maxUpgrades[0];
		} else {
			return maxUpgrades[1];
		}
	}
	
	public int generateUpgradeTimes() {
		double upgradeTimesChance = generateNumber(99.99);
		
		int[] upgradeTimes = { 0, 1, 2, 3, 4, 5 };
		double[] probabilities = { 23.73, 39.55, 26.37, 8.79, 1.46, 0.09 };
		double cumulativeProbability = 0;
		
		for (int i = 0; i < upgradeTimes.length; i++) {
			cumulativeProbability += probabilities[i];
			
			if (upgradeTimesChance <= cumulativeProbability) {
				return upgradeTimes[i];
			}
		}
		
		// If we reach here, something went wrong, just return the first element
		return upgradeTimes[0];
	}
	
	public int generateRandomSlot() {
		return generateRandomSlot(false);
	}
	
	public int generateRandomSlot(boolean definedAffixMode) {
		double slotChance = generateNumber();
		
		final int[] AFFIXED_SLOTS = { 1, 2 };
		final int[] ALL_SLOTS = { 1, 2, 3, 4 };

		int[] slots = definedAffixMode ? AFFIXED_SLOTS : ALL_SLOTS;
		double[] probabilities = generateEqualProbabilities(slots.length);
		double cumulativeProbability = 0;

		for (int i = 0; i < slots.length; i++) {
			cumulativeProbability += probabilities[i];
			
			if (slotChance <= cumulativeProbability) {
				return slots[i];
			}
		}

		// If we reach here, something went wrong, so just return the first element
		return slots[0];
	}
	
	private double[] generateEqualProbabilities(int length) {
	    double[] probabilities = new double[length];
	    double value = 100.0 / length;
	    
	    Arrays.fill(probabilities, value);
	    
	    return probabilities;
	}
	
	public double generateSubAttributeValue(String attributeName) throws NullPointerException, IllegalArgumentException {
		if (attributeName == null) {
			throw new NullPointerException("attributeName must not be null");
		}
		
		// "ATK%", "HP%", "DEF%", "ATK", "HP", "DEF", "Energy Recharge", "Elemental Mastery", "Crit Rate", "Crit Damage"
		
		/*
		 * Possibilities for initial values and upgrades of sub-stats:
		 * 
		 * 25% chance 100% value of the max stat
		 * 25% chance 90% value of the max stat
		 * 25% chance 80% value of the max stat
		 * 25% chance 70% value of the max stat
		 */
		
		return generateAttributeValue(attribute.getAttributeValues(attributeName));
	}
	
	private void checkIfHundredPercent(List<AttributeProbabilityStat> listStats) {
		double totalProbability = 0;
		
		for (AttributeProbabilityStat currentStat : listStats) {
			totalProbability += currentStat.getAttributeProbability();
		}
		
		System.out.format("%n%s%nTotal Probability is %.2f%%%n%n", listStats.toString(), totalProbability);
	}
	
	private List<AttributeProbabilityStat> getStatProbabilityList(List<String> listAttributeNames) {
		List<AttributeProbabilityStat> selectedStats = new ArrayList<>();
		
		for (AttributeWeightStat currentWeightStat : LIST_WEIGHT_STATS) {
			if (!listAttributeNames.contains(currentWeightStat.getAttributeName())) {
				AttributeProbabilityStat attributeProbabilityStat = calculateStatProbability(currentWeightStat, listAttributeNames);
				selectedStats.add(attributeProbabilityStat);
			}
		}
		
		return selectedStats;
	}
	
	private AttributeProbabilityStat calculateStatProbability(AttributeWeightStat targetWeightStat, List<String> listAttributeNames) throws NullPointerException, IllegalArgumentException {
		boolean isFound = false;
        int totalWeight = 0;
		
		// verify the targetWeightedStat and existingStats
		if (targetWeightStat == null || listAttributeNames == null) {
        	throw new NullPointerException("Requires non-null object");
        }
        
        // verify the targetWeightedStat
        for (String attributeName : Attribute.ATTRIBUTE_NAMES) {
        	if (attributeName.equals(targetWeightStat.getAttributeName())) {
        		isFound = true;
        		break;
        	}
        }
        
        if (!isFound) {
        	throw new IllegalArgumentException("Invalid attributeName: " + targetWeightStat.getAttributeName());
        }
        
        // Calculate the total weight of available sub-stats (excluding the ones already existing)
        for (AttributeWeightStat currentWeightStat : LIST_WEIGHT_STATS) {
        	if (!listAttributeNames.contains(currentWeightStat.getAttributeName())) {
        		totalWeight += currentWeightStat.getAttributeWeight();
        	}
        }
        
        double probability = (double) targetWeightStat.getAttributeWeight() / totalWeight * 100;
        
        return new AttributeProbabilityStat(targetWeightStat.getAttributeName(), probability);
    }
	
	// RESPONSIBLE FOR CHECKING GENUINE ARTIFACT NAMES
	
	public boolean isAttributeNamePercentage(String attributeName) throws NullPointerException {
		if (attributeName == null) {
			throw new NullPointerException("attributeName must not be null");
		}
		
		return attributeName.endsWith("%");
	}
	
	public String isArtifactPiece(String artifactPieceName) throws NullPointerException, IllegalArgumentException {
		if (artifactPieceName == null) {
			return artifactPieceName;
		}
		
		boolean isArtifactPiece = false;
		
		// check if artifact piece
		for (String currArtifactPiece : ARTIFACT_PIECES) {
			if (currArtifactPiece.equals(artifactPieceName)) {
				isArtifactPiece = true;
				break;
			}
		}
		
		if (isArtifactPiece) {
			return artifactPieceName;
		} else {
			throw new IllegalArgumentException("Invalid artifactPieceName: " + artifactPieceName);
		}
	}
	
	public String isMainAttribute(String mainAttributeName) throws NullPointerException, IllegalArgumentException {
		if (mainAttributeName == null) {
			return mainAttributeName;
		}
		
		boolean isMainAttribute = false;
		
		// check if main attribute
		for (String currMainAttribute : MAIN_ATTRIBUTE_NAMES) {
			if (currMainAttribute.equals(mainAttributeName)) {
				isMainAttribute = true;
				break;
			}
		}
		
		if (isMainAttribute) {
			return mainAttributeName;
		} else {
			throw new IllegalArgumentException("Invalid mainAttributeName: " + mainAttributeName);
		}
	}
	
	public String isSubAttribute(String subAttributeName) throws IllegalArgumentException {
		if (subAttributeName == null) {
			return subAttributeName;
		}
		
		boolean isSubAttribute = false;
		
		// check if main attribute
		for (String currSubAttribute : Attribute.ATTRIBUTE_NAMES) {
			if (currSubAttribute.equals(subAttributeName)) {
				isSubAttribute = true;
				break;
			}
		}
		
		if (isSubAttribute) {
			return subAttributeName;
		} else {
			StringBuilder sb = new StringBuilder();
			sb.append("Invalid subAttributeName: ").append(subAttributeName);
			sb.append(". If this is valid, it's case sensitive and must exact name to the game.").append("\nValid Sub Attribute Name: ");
			
			for (String currSubAttribute : Attribute.ATTRIBUTE_NAMES) {
				sb.append(currSubAttribute).append(", ");
			}
			
			throw new IllegalArgumentException(sb.toString());
		}
	}
	
	public double isSubAttributeValue(String subAttributeName, double subAttributeValue) {
		if (subAttributeName == null || subAttributeValue == 0) {
			return subAttributeValue;
		}
		
		boolean isSubAttributeValue = false;
		
		double[] attributeValues = attribute.getAttributeValues(subAttributeName);
		
		for (double attributeValue : attributeValues) {
			if (attributeValue == subAttributeValue) {
				isSubAttributeValue = true;
				break;
			}
		}
		
		if (isSubAttributeValue) {
			return subAttributeValue;
		} else {
			StringBuilder sb = new StringBuilder();
			sb.append("Invalid subAttributeValue: ").append(subAttributeValue).append(", it's not a value from ");
			sb.append(subAttributeName).append('.').append("\nValid Values of ").append(subAttributeName).append(": ");
			
			for (double attributeValue : attributeValues) {
				sb.append(attributeValue).append(", ");
			}
			
			throw new IllegalArgumentException(sb.toString());
		}
	}
	
	// RESPONSIBLE FOR FORMATTING SUB-STAT
	
	/**
	 * Formats Sub-Stat
	 * @param mode 0 = new, 1 = displaying, 2 = upgrade, 3 = skip upgrade
	 * @param artifactSubStat {@link ArtifactSubStat} class
	 * @return depending on the mode chosen
	 * @throws NullPointerException
	 */
	public String formatSubStat(int mode, ArtifactSubStat artifactSubStat) throws NullPointerException, IllegalArgumentException {
		if (artifactSubStat == null) {
			throw new NullPointerException("artifactSubStat must not be null");
		}
		
		return switch (mode) {
			case 0 -> 
				formatNewSubStat(artifactSubStat.getAttributeName(), artifactSubStat.getAttributeValue());
			case 1 -> 
				formatSubStat(artifactSubStat.getAttributeName(), artifactSubStat.getAttributeValue());
			case 2 -> 
				formatSubStat(artifactSubStat.getAttributeName(), artifactSubStat.getPrevAttributeValue(), artifactSubStat.getAttributeValue());
			case 3 -> 
				formatSubStat(artifactSubStat.getAttributeName(), artifactSubStat.getInitialAttributeValue(), artifactSubStat.getAttributeValue());
			default -> 
				throw new IllegalArgumentException("Invalid mode: " + mode);
		};
	}
	
	public String formatSubStat(String attributeName) throws NullPointerException {
		if (attributeName == null) {
			throw new NullPointerException("attributeName must not be null");
		}
		
		// checks if the attribute name is percentage
		if (isAttributeNamePercentage(attributeName)) {
			return attributeName.substring(0, attributeName.length() - 1);
		}
		
		// return the flat attribute name
		return attributeName;
	}
	
	public String formatSubStat(String attributeName, double attributeValue) throws NullPointerException {
		if (attributeName == null) {
			throw new NullPointerException("attributeName must not be null");
		}
		
		// checks if the attribute name is percentage
		if (isAttributeNamePercentage(attributeName)) {
			return String.format("%s+%.1f%%", formatSubStat(attributeName), attributeValue);
		}
		
		// return the flat sub-stat
		return String.format("%s+%d", attributeName, Math.round(attributeValue));
	}
	
	public String formatSubStat(String attributeName, double prevAttributeValue, double currAttributeValue) throws NullPointerException {
		if (attributeName == null) {
			throw new NullPointerException("attributeName must not be null");
		}
		
		// checks if the attribute name is percentage
		if (isAttributeNamePercentage(attributeName)) {
			return String.format("%-20s %5.1f%% ---> %.1f%%", formatSubStat(attributeName), prevAttributeValue, currAttributeValue);
		}
		
		// return the flat sub-stat
		return String.format("%-20s %5d ---> %d", attributeName, Math.round(prevAttributeValue), Math.round(currAttributeValue));
	}
	
	public String formatNewSubStat(String attributeName, double attributeValue) throws NullPointerException {
		if (attributeName == null) {
			throw new NullPointerException("attributeName must not be null");
		}
		
		// checks if the attribute name is percentage
		if (isAttributeNamePercentage(attributeName)) {
			return String.format("%-25s ----- %.1f%%", formatSubStat(attributeName), attributeValue);
		}
		
		return String.format("%-25s ----- %d", attributeName, Math.round(attributeValue));
	}
	
	public String formatSubAttributeValue(String attributeName, double attributeValue) throws NullPointerException {
		if (attributeName == null) {
			throw new NullPointerException("attributeName must not be null");
		}
		
		// checks if the attribute name is percentage
		if (isAttributeNamePercentage(attributeName)) {
			return String.format("%.1f%%", attributeValue);
		}
		
		// return the flat value
		return String.valueOf(Math.round(attributeValue));
	}
	
	// GENERATING RANDOM ATTRIBUTE NAME
	
	private String generateAttributeName(List<AttributeProbabilityStat> listAttribute) {
		double attributeChance = generateNumber();
		double cumulativeProbability = 0;
		
		for (int i = 0; i < listAttribute.size(); i++) {
			AttributeProbabilityStat currentStat = listAttribute.get(i);
			cumulativeProbability += currentStat.getAttributeProbability();
			
			if(attributeChance <= cumulativeProbability) {
				return currentStat.getAttributeName();
			}
		}
		
		// If we reach here, something went wrong, just return the first element
		return listAttribute.get(0).getAttributeName();
	}
	
	// GENERATING RANDOM ATTRIBUTE VALUE
	
	private double generateAttributeValue(double[] attributeValues) {
		double valueChance = generateNumber();
		double[] probabilities = generateEqualProbabilities(4);
		double cumulativeProbability = 0;
		
		for (int i = 0; i < attributeValues.length; i++) {
			cumulativeProbability += probabilities[i];
			
			if (valueChance <= cumulativeProbability) {
				return attributeValues[i];
			}
		}
		
		// If we reach here, something went wrong, so just return the last element
		return attributeValues[0];
	}
	
	// NUMBER GENERATOR
	public double generateNumber() {
		return generateNumber(100);
	}
	
	public double generateNumber(double maxNumber) {
		if (maxNumber <= 0) {
			throw new IllegalArgumentException("Number cannot go below starting at 0");
		}
		
		double min = 0;
		double max = maxNumber;
		double randomValue = rand.nextDouble() * (max - min) + min;
		// round to 2 decimal places
		return Math.round(randomValue * 100.0) / 100.0;
	}
}
