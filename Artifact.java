import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public final class Artifact extends Attribute {
	public static final String FLOWER = "Flower of Life";
	public static final String FEATHER = "Plume of Death";
	public static final String SANDS = "Sands of Eon";
	public static final String GOBLET = "Goblet of Eonothem";
	public static final String CIRCLET = "Circlet of Logos";
	
	private final String[] PIECE = {
			"Flower of Life", "Plume of Death", 
			"Sands of Eon", "Goblet of Eonothem",
			"Circlet of Logos"
	};
	private final String[] FLOWER_OF_LIFE = { "HP" };
	private final String[] PLUME_OF_DEATH = { "ATK" };
	private final String[] SANDS_OF_EON = { "HP%", "ATK%", "DEF%", "Energy Recharge%", "Elemental Mastery" };
	private final String[] GOBLET_OF_EONOTHEM = {
			"HP%", "ATK%", "DEF%", "Pyro DMG Bonus%", "Electro DMG Bonus%",
			"Cryo DMG Bonus%", "Hydro DMG Bonus%", "Dendro DMG Bonus%", "Anemo DMG Bonus%", "Geo DMG Bonus%",
			"Physical DMG Bonus%", "Elemental Mastery"
	};
	private final String[] CIRCLET_OF_LOGOS = {
			"HP%", "ATK%", "DEF%", "Healing Bonus%", "Elemental Mastery",
			"CRIT Rate%", "CRIT DMG%"
	};
	
	private final AttributeStat FLOWER_STAT = new AttributeStat(Attribute.HP_FLAT);
	private final AttributeStat FEATHER_STAT = new AttributeStat(Attribute.ATK_FLAT);
	
	private final List<ProbabilityStat> listSands = Arrays.asList(
			new ProbabilityStat(Attribute.HP_PER, 26.68),
			new ProbabilityStat(Attribute.ATK_PER, 26.66),
			new ProbabilityStat(Attribute.DEF_PER, 26.66),
			new ProbabilityStat(Attribute.ENERGY_RECHARGE, 10.00),
			new ProbabilityStat(Attribute.ELEMENTAL_MASTERY, 10.00)
	);
	private final List<ProbabilityStat> listGoblet = Arrays.asList(
			new ProbabilityStat(Attribute.HP_PER, 19.25),
			new ProbabilityStat(Attribute.ATK_PER, 19.25),
			new ProbabilityStat(Attribute.DEF_PER, 19.00),
			new ProbabilityStat(Attribute.PYRO_DMG_BONUS, 5.00),
			new ProbabilityStat(Attribute.ELECTRO_DMG_BONUS, 5.00),
			new ProbabilityStat(Attribute.CRYO_DMG_BONUS, 5.00),
			new ProbabilityStat(Attribute.HYDRO_DMG_BONUS, 5.00),
			new ProbabilityStat(Attribute.DENDRO_DMG_BONUS, 5.00),
			new ProbabilityStat(Attribute.ANEMO_DMG_BONUS, 5.00),
			new ProbabilityStat(Attribute.GEO_DMG_BONUS, 5.00),
			new ProbabilityStat(Attribute.PHYSICAL_DMG_BONUS, 5.00),
			new ProbabilityStat(Attribute.ELEMENTAL_MASTERY, 2.50)
	);
	private final List<ProbabilityStat> listCirclet = Arrays.asList(
			new ProbabilityStat(Attribute.HP_PER, 22.00),
			new ProbabilityStat(Attribute.ATK_PER, 22.00),
			new ProbabilityStat(Attribute.DEF_PER, 22.00),
			new ProbabilityStat(Attribute.CRIT_RATE, 10.00),
			new ProbabilityStat(Attribute.CRIT_DMG, 10.00),
			new ProbabilityStat(Attribute.HEALING_BONUS, 10.00),
			new ProbabilityStat(Attribute.ELEMENTAL_MASTERY, 4.00)
	);
	
	private final List<WeightedStat> listStatWeight = Arrays.asList(
			new WeightedStat(Attribute.HP_FLAT, 6),
			new WeightedStat(Attribute.ATK_FLAT, 6),
			new WeightedStat(Attribute.DEF_FLAT, 6),
			new WeightedStat(Attribute.HP_PER, 4),
			new WeightedStat(Attribute.ATK_PER, 4),
			new WeightedStat(Attribute.DEF_PER, 4),
			new WeightedStat(Attribute.ENERGY_RECHARGE, 4),
			new WeightedStat(Attribute.ELEMENTAL_MASTERY, 4),
			new WeightedStat(Attribute.CRIT_RATE, 3),
			new WeightedStat(Attribute.CRIT_DMG, 3)
	);
	
	
	public String[] getPiece() {
		return PIECE;
	}
	
	public String[] getFlower() {
		return FLOWER_OF_LIFE;
	}
	
	public String[] getFeather() {
		return PLUME_OF_DEATH;
	}
	
	public String[] getSands() {
		return SANDS_OF_EON;
	}
	
	public String[] getGoblet() {
		return GOBLET_OF_EONOTHEM;
	}
	
	public String[] getCirclet() {
		return CIRCLET_OF_LOGOS;
	}
	
	public String generateRandomPiece() {
		List<String> listPiece = Arrays.asList(FLOWER, FEATHER, SANDS, GOBLET, CIRCLET);
		int randomIndex = new Random().nextInt(listPiece.size());
		return listPiece.get(randomIndex);
	}
	
	public String generateMainAttribute(String artifactPiece) throws IllegalArgumentException {
		return switch (artifactPiece) {
			case FLOWER ->
				FLOWER_STAT.getAttribute();
			case FEATHER ->
				FEATHER_STAT.getAttribute();
			case SANDS ->
				generatedAttribute(listSands);
			case GOBLET ->
				generatedAttribute(listGoblet);
			case CIRCLET ->
				generatedAttribute(listCirclet);
			default ->
				// Throw an exception if none of the cases is met
				throw new IllegalArgumentException("Invalid artifact piece: " + artifactPiece);
		};
	}
	
	public String generateSubAttribute(String... attributes) throws NullPointerException {
		List<String> notSpecialAttributes = new ArrayList<>();
		
		for (String attribute : attributes) {
			if (attribute == null) {
				throw new NullPointerException("Attribute must not be null.");
			}
			
			if (isNotSpecialAttribute(attribute)) {
				notSpecialAttributes.add(attribute);
			}
		}
		
		List<ProbabilityStat> statsList = getStatProbabilityList(notSpecialAttributes);
		
		// for debugging purposes
		checkIfHundredPercent(statsList);
		
		return generatedAttribute(statsList);
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
	
	public int generateNoOfUpgrade() {
		double upgradeChance = Double.valueOf("%.2f".formatted(generateNumber() - 0.01));
		
		int[] upgradeTimes = { 0, 1, 2, 3, 4, 5 };
		double[] probabilities = { 23.73, 39.55, 26.37, 8.79, 1.46, 0.09 };
		double cumulativeProbability = 0;
		
		for (int i = 0; i <= upgradeTimes.length; i++) {
			cumulativeProbability += probabilities[i];
			
			if (upgradeChance <= cumulativeProbability) {
				return upgradeTimes[i];
			}
		}
		
		// If we reach here, something went wrong, just return the first element
		return upgradeTimes[0];
	}
	
	public int generateRandomSlot() {
		double slotChance = generateNumber();

		int[] slots = { 1, 2, 3, 4 };
		double[] probabilities = { 25.00, 25.00, 25.00, 25.00 };
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
	
	public double generateValue(String attribute) throws IllegalArgumentException {
		if (attribute == null) {
			throw new NullPointerException("Attribute must not be null");
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
		
		for (ValueStat currentStat : Attribute.VALUE_STATS) {
			if (currentStat.getAttribute().equals(attribute)) {
				return generatedValue(currentStat.getValues());
			}
		}
		
		// Throw an exception if we reach here meaning that the attribute is not in the array of stats
		throw new IllegalArgumentException("Invalid attribute: " + attribute);
	}
	
	public String formatText(String attribute) {
		if (attribute == null) {
			throw new NullPointerException("Attribute must not be null");
		}
		
		// checks if the attribute is percentage
		if (attribute.substring(attribute.length() - 1).equals("%")) {
			return attribute.substring(0, attribute.length() - 1);
		}
		
		// return the flat attribute
		return attribute;
	}
	
	public String formatText(String attribute, double value) {
		if (attribute == null) {
			throw new NullPointerException("Attribute must not be null");
		}
		
		// checks if the attribute is percentage
		if (attribute.substring(attribute.length() - 1).equals("%")) {
			String modifiedAtt = attribute.substring(0, attribute.length() - 1);
			return String.format("%s+%.1f%%", modifiedAtt, value);
		}
		
		// return the flat attribute
		return String.format("%s+%d", attribute, Math.round(value));
	}
	
	public String formatText(String attribute, double prevValue, double currValue) {
		if (attribute == null) {
			throw new NullPointerException("Attribute must not be null");
		}
		
		// checks if the attribute is percentage
		if (attribute.substring(attribute.length() - 1).equals("%")) {
			String modifiedAtt = attribute.substring(0, attribute.length() - 1);
			return String.format("%s    %.1f%% ---> %.1f%%", modifiedAtt, prevValue, currValue);
		}
		
		// return the flat attribute
		return String.format("%s    %d ---> %d", attribute, Math.round(prevValue), Math.round(currValue));
	}
	
	public String formatValue(String attribute, double value) {
		if (attribute == null) {
			throw new NullPointerException("Attribute must not be null");
		}
		
		// checks if the attribute is percentage
		if (attribute.substring(attribute.length() - 1).equals("%")) {
			return String.format("%.1f%%", value);
		}
		
		// return the flat attribute
		return String.valueOf(Math.round(value));
	}
	
	private void checkIfHundredPercent(List<ProbabilityStat> listStats) {
		double totalProbability = 0;
		
		for (ProbabilityStat currentStat : listStats) {
			totalProbability += currentStat.getProbability();
		}
		
		System.out.format("%nFrom %s%nTotal Probability is %.2f%%%n%n", listStats.toString(), totalProbability);
	}
	
	private List<ProbabilityStat> getStatProbabilityList(List<String> attributes) {
		List<WeightedStat> notExistingStats = new ArrayList<>();
		List<ProbabilityStat> selectedStats = new ArrayList<>();
		
		for (WeightedStat currentStat : listStatWeight) {
			if (!attributes.contains(currentStat.getAttribute())) {
				notExistingStats.add(currentStat);
			}
		}
		
		for (WeightedStat currentStat : notExistingStats) {
			selectedStats.add(calculateStatProbability(currentStat, attributes));
		}
		
		return selectedStats;
	}
	
	private ProbabilityStat calculateStatProbability(WeightedStat targetWeightedStat, List<String> existingStats) throws NullPointerException, IllegalArgumentException {
		// verify the targetWeightedStat and existingStats
		if (targetWeightedStat == null || existingStats == null) {
        	throw new NullPointerException("Requires non-null object");
        }
				
        int totalWeight = 0;
        
        // verify the targetWeightedStat
        if (!Arrays.asList(Attribute.ATTRIBUTES).contains(targetWeightedStat.getAttribute())) {
        	throw new IllegalArgumentException("Invalid attribute: " + targetWeightedStat.getAttribute());
        }
        
        // Calculate the total weight of available sub-stats (excluding the ones already existing)
        for (WeightedStat currentStat : listStatWeight) {
        	if (!existingStats.contains(currentStat.getAttribute())) {
        		totalWeight += currentStat.getWeight();
        	}
        }
        
        double probability = (double) targetWeightedStat.getWeight() / totalWeight * 100;
        
        return new ProbabilityStat(targetWeightedStat.getAttribute(), probability);
    }
	
	// GENERATING RANDOM ATTRIBUTE
	
	private String generatedAttribute(List<ProbabilityStat> listAttribute) {
		double attributeChance = generateNumber();
		double cumulativeProbability = 0;
		
		for (int i = 0; i < listAttribute.size(); i++) {
			ProbabilityStat currentStat = listAttribute.get(i);
			cumulativeProbability += currentStat.getProbability();
			
			if(attributeChance <= cumulativeProbability) {
				return currentStat.getAttribute();
			}
		}
		
		// If we reach here, something went wrong, just return the first element
		return listAttribute.get(0).getAttribute();
	}
	
	// ATTRIBUTE VALUE
	
	private double generatedValue(double[] attributeValue) {
		double valueChance = generateNumber();
		double[] probabilities = { 25.00, 25.00, 25.00, 25.00 };
		double cumulativeProbability = 0;
		
		for (int i = 0; i < attributeValue.length; i++) {
			cumulativeProbability += probabilities[i];
			
			if (valueChance <= cumulativeProbability) {
				return attributeValue[i];
			}
		}
		
		// If we reach here, something went wrong, so just return the last element
		return attributeValue[0];
	}
	
	// NUMBER GENERATOR
	// Generates number from 0.00 to 100.00
	public double generateNumber() {
		int min = 0;
		int max = 100;
		return Double.valueOf("%.2f".formatted(new Random().nextDouble() * (max - min) + min));
	}
}
