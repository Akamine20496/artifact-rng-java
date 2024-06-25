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
	
	private List<Stat> listFlower = Arrays.asList(new Stat(Attribute.HP_FLAT, 100.00));
	private List<Stat> listFeather = Arrays.asList(new Stat(Attribute.ATK_FLAT, 100.00));
	private List<Stat> listSands = Arrays.asList(
			new Stat(Attribute.HP_PER, 26.68),
			new Stat(Attribute.ATK_PER, 26.66),
			new Stat(Attribute.DEF_PER, 26.66),
			new Stat(Attribute.ENERGY_RECHARGE, 10.00),
			new Stat(Attribute.ELEMENTAL_MASTERY, 10.00)
	);
	private List<Stat> listGoblet = Arrays.asList(
			new Stat(Attribute.HP_PER, 19.25),
			new Stat(Attribute.ATK_PER, 19.25),
			new Stat(Attribute.DEF_PER, 19.00),
			new Stat(Attribute.PYRO_DMG_BONUS, 5.00),
			new Stat(Attribute.ELECTRO_DMG_BONUS, 5.00),
			new Stat(Attribute.CRYO_DMG_BONUS, 5.00),
			new Stat(Attribute.HYDRO_DMG_BONUS, 5.00),
			new Stat(Attribute.DENDRO_DMG_BONUS, 5.00),
			new Stat(Attribute.ANEMO_DMG_BONUS, 5.00),
			new Stat(Attribute.GEO_DMG_BONUS, 5.00),
			new Stat(Attribute.PHYSICAL_DMG_BONUS, 5.00),
			new Stat(Attribute.ELEMENTAL_MASTERY, 2.50)
	);
	private List<Stat> listCirclet = Arrays.asList(
			new Stat(Attribute.HP_PER, 22.00),
			new Stat(Attribute.ATK_PER, 22.00),
			new Stat(Attribute.DEF_PER, 22.00),
			new Stat(Attribute.CRIT_RATE, 10.00),
			new Stat(Attribute.CRIT_DMG, 10.00),
			new Stat(Attribute.HEALING_BONUS, 10.00),
			new Stat(Attribute.ELEMENTAL_MASTERY, 4.00)
	);
	private List<Stat> listHpFlat = Arrays.asList(
			new Stat(Attribute.ATK_FLAT, 15.79),
			new Stat(Attribute.DEF_FLAT, 15.79),
			new Stat(Attribute.HP_PER, 10.53),
			new Stat(Attribute.ATK_PER, 10.53),
			new Stat(Attribute.DEF_PER, 10.53),
			new Stat(Attribute.ENERGY_RECHARGE, 10.53),
			new Stat(Attribute.ELEMENTAL_MASTERY, 10.53),
			new Stat(Attribute.CRIT_RATE, 7.89),
			new Stat(Attribute.CRIT_DMG, 7.89)
	);
	private List<Stat> listAtkFlat = Arrays.asList(
			new Stat(Attribute.HP_FLAT, 15.79),
			new Stat(Attribute.DEF_FLAT, 15.79),
			new Stat(Attribute.HP_PER, 10.53),
			new Stat(Attribute.ATK_PER, 10.53),
			new Stat(Attribute.DEF_PER, 10.53),
			new Stat(Attribute.ENERGY_RECHARGE, 10.53),
			new Stat(Attribute.ELEMENTAL_MASTERY, 10.53),
			new Stat(Attribute.CRIT_RATE, 7.89),
			new Stat(Attribute.CRIT_DMG, 7.89)
	);
	private List<Stat> listHpPer = Arrays.asList(
			new Stat(Attribute.HP_FLAT, 15.00),
			new Stat(Attribute.ATK_FLAT, 15.00),
			new Stat(Attribute.DEF_FLAT, 15.00),
			new Stat(Attribute.ATK_PER, 10.00),
			new Stat(Attribute.DEF_PER, 10.00),
			new Stat(Attribute.ENERGY_RECHARGE, 10.00),
			new Stat(Attribute.ELEMENTAL_MASTERY, 10.00),
			new Stat(Attribute.CRIT_RATE, 7.50),
			new Stat(Attribute.CRIT_DMG, 7.50)
	);
	private List<Stat> listAtkPer = Arrays.asList(
			new Stat(Attribute.HP_FLAT, 15.00),
			new Stat(Attribute.ATK_FLAT, 15.00),
			new Stat(Attribute.DEF_FLAT, 15.00),
			new Stat(Attribute.HP_PER, 10.00),
			new Stat(Attribute.DEF_PER, 10.00),
			new Stat(Attribute.ENERGY_RECHARGE, 10.00),
			new Stat(Attribute.ELEMENTAL_MASTERY, 10.00),
			new Stat(Attribute.CRIT_RATE, 7.50),
			new Stat(Attribute.CRIT_DMG, 7.50)
	);
	private List<Stat> listDefPer = Arrays.asList(
			new Stat(Attribute.HP_FLAT, 15.00),
			new Stat(Attribute.ATK_FLAT, 15.00),
			new Stat(Attribute.DEF_FLAT, 15.00),
			new Stat(Attribute.HP_PER, 10.00),
			new Stat(Attribute.ATK_PER, 10.00),
			new Stat(Attribute.ENERGY_RECHARGE, 10.00),
			new Stat(Attribute.ELEMENTAL_MASTERY, 10.00),
			new Stat(Attribute.CRIT_RATE, 7.50),
			new Stat(Attribute.CRIT_DMG, 7.50)
	);
	private List<Stat> listEnergyRecharge = Arrays.asList(
			new Stat(Attribute.HP_FLAT, 15.00),
			new Stat(Attribute.ATK_FLAT, 15.00),
			new Stat(Attribute.DEF_FLAT, 15.00),
			new Stat(Attribute.HP_PER, 10.00),
			new Stat(Attribute.ATK_PER, 10.00),
			new Stat(Attribute.DEF_PER, 10.00),
			new Stat(Attribute.ELEMENTAL_MASTERY, 10.00),
			new Stat(Attribute.CRIT_RATE, 7.50),
			new Stat(Attribute.CRIT_DMG, 7.50)
	);
	private List<Stat> listElementalMastery = Arrays.asList(
			new Stat(Attribute.HP_FLAT, 15.00),
			new Stat(Attribute.ATK_FLAT, 15.00),
			new Stat(Attribute.DEF_FLAT, 15.00),
			new Stat(Attribute.HP_PER, 10.00),
			new Stat(Attribute.ATK_PER, 10.00),
			new Stat(Attribute.DEF_PER, 10.00),
			new Stat(Attribute.ENERGY_RECHARGE, 10.00),
			new Stat(Attribute.CRIT_RATE, 7.50),
			new Stat(Attribute.CRIT_DMG, 7.50)
	);
	private List<Stat> listCritRate = Arrays.asList(
			new Stat(Attribute.HP_FLAT, 14.63),
			new Stat(Attribute.ATK_FLAT, 14.63),
			new Stat(Attribute.DEF_FLAT, 14.63),
			new Stat(Attribute.HP_PER, 9.76),
			new Stat(Attribute.ATK_PER, 9.76),
			new Stat(Attribute.DEF_PER, 9.76),
			new Stat(Attribute.ENERGY_RECHARGE, 9.76),
			new Stat(Attribute.ELEMENTAL_MASTERY, 9.76),
			new Stat(Attribute.CRIT_DMG, 7.32)
	);
	private List<Stat> listCritDmg = Arrays.asList(
			new Stat(Attribute.HP_FLAT, 14.63),
			new Stat(Attribute.ATK_FLAT, 14.63),
			new Stat(Attribute.DEF_FLAT, 14.63),
			new Stat(Attribute.HP_PER, 9.76),
			new Stat(Attribute.ATK_PER, 9.76),
			new Stat(Attribute.DEF_PER, 9.76),
			new Stat(Attribute.ENERGY_RECHARGE, 9.76),
			new Stat(Attribute.ELEMENTAL_MASTERY, 9.76),
			new Stat(Attribute.CRIT_RATE, 7.32)
	);
	private List<Stat> listSpecialAtt = Arrays.asList(
			new Stat(Attribute.HP_FLAT, 13.64),
			new Stat(Attribute.ATK_FLAT, 13.64),
			new Stat(Attribute.DEF_FLAT, 13.64),
			new Stat(Attribute.HP_PER, 9.09),
			new Stat(Attribute.ATK_PER, 9.09),
			new Stat(Attribute.DEF_PER, 9.09),
			new Stat(Attribute.ENERGY_RECHARGE, 9.09),
			new Stat(Attribute.ELEMENTAL_MASTERY, 9.09),
			new Stat(Attribute.CRIT_RATE, 6.82),
			new Stat(Attribute.CRIT_DMG, 6.82)
	);
	
	public static String generateRandomPiece() {
		List<String> listPiece = Arrays.asList(
				Artifact.FLOWER, Artifact.FEATHER, Artifact.SANDS,
				Artifact.GOBLET, Artifact.CIRCLET
		);
		
		int randomIndex = new Random().nextInt(listPiece.size());
		return listPiece.get(randomIndex);
	}
	
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
	
	public String generateMainAttribute(String artifactPiece) throws IllegalArgumentException {
		return switch(artifactPiece) {
			case Artifact.FLOWER ->
				listFlower.get(0).getAttribute();
			case Artifact.FEATHER ->
				listFeather.get(0).getAttribute();
			case Artifact.SANDS ->
				generatedAttribute(listSands);
			case Artifact.GOBLET ->
				generatedAttribute(listGoblet);
			case Artifact.CIRCLET ->
				generatedAttribute(listCirclet);
			default ->
				// Throw an exception if none of the cases is met
				throw new IllegalArgumentException("Invalid artifact piece: " + artifactPiece);
		};
	}
	
	public String generateSubAttribute(String mainAttribute) throws IllegalArgumentException {
		if(isNotSpecial(mainAttribute)) {
			return switch(mainAttribute) {
				case Attribute.HP_FLAT ->
					generatedAttribute(listHpFlat);
	    		case Attribute.ATK_FLAT ->
	    			generatedAttribute(listAtkFlat);
	            case Attribute.HP_PER ->
	                generatedAttribute(listHpPer);
	            case Attribute.ATK_PER ->
	                generatedAttribute(listAtkPer);
	            case Attribute.DEF_PER ->
	                generatedAttribute(listDefPer);
	            case Attribute.ENERGY_RECHARGE ->
	            	generatedAttribute(listEnergyRecharge);
	            case Attribute.ELEMENTAL_MASTERY ->
	                generatedAttribute(listElementalMastery);
	            case Attribute.CRIT_RATE ->
	            	generatedAttribute(listCritRate);
	            case Attribute.CRIT_DMG ->
	            	generatedAttribute(listCritDmg);
	            default ->
	            	// Throw an exception if none of the cases or the isNotSpecial condition is met
	        	    throw new IllegalArgumentException("Invalid attribute: " + mainAttribute);
			};
		} else {
			return generatedAttribute(listSpecialAtt);
		}
	}
	
	public int noOfMaxUpgrade() {
		double noOfSubStatChance = generateNumber();
		
		int[] maxUpgrades = { 4, 5 };
		
		if (noOfSubStatChance < 50.00) {
			return maxUpgrades[0];
		} else {
			return maxUpgrades[1];
		}
	}
	
	public int noOfUpgrade() {
		double upgradeChance = generateNumber();
		
		int[] upgradeTimes = { 0, 1, 2, 3, 4, 5 };
		double[] probabilities = { 23.73, 39.55, 26.37, 8.79, 1.46, 0.10 };
		double cumulativeProbability = 0;
		
		for(int i = 0; i < upgradeTimes.length; i++) {
			cumulativeProbability += probabilities[i];
			if(upgradeChance < cumulativeProbability) {
				return upgradeTimes[i];
			}
		}
		
		// If we reach here, something went wrong, just return the first element
		return upgradeTimes[0];
	}
	
	public double generateValue(String attribute) throws IllegalArgumentException {
		// "ATK%", "HP%", "DEF%", "ATK", "HP", "DEF", "Energy Recharge", "Elemental Mastery", "Crit Rate", "Crit Damage"
		
		/*
		 * Possibilities for initial values and upgrades of sub-stats:
		 * 
		 * 25% chance 100% value of the max stat
		 * 25% chance 90% value of the max stat
		 * 25% chance 80% value of the max stat
		 * 25% chance 70% value of the max stat
		 */
		
		for(Stat stats : Attribute.STATS) {
			if(stats.getAttribute().equals(attribute)) {
				return generatedValue(stats.getValues());
			}
		}
		
		// Throw an exception if we reach here meaning that the attribute is not
		// in the array of stats
		throw new IllegalArgumentException("Invalid attribute: " + attribute);
	}
	
	public String formatText(String attribute) {
		// checks if the attribute is percentage
		if(attribute.substring(attribute.length() - 1).equals("%")) {
			return attribute.substring(0, attribute.length() - 1);
		}
		
		// return the flat attribute
		return attribute;
	}
	
	public String formatText(String attribute, double value) {
		// checks if the attribute is percentage
		if(attribute.substring(attribute.length() - 1).equals("%")) {
			String modifiedAtt = attribute.substring(0, attribute.length() - 1);
			return String.format("%s+%.1f", modifiedAtt, value) + "%";
		}
		
		// return the flat attribute
		return String.format("%s+%d", attribute, Math.round(value));
	}
	
	public String formatText(String attribute, double prevValue, double currValue) {
		// checks if the attribute is percentage
		if(attribute.substring(attribute.length() - 1).equals("%")) {
			String modifiedAtt = attribute.substring(0, attribute.length() - 1);
			return String.format("%s    %.1f%s ---> %.1f%s", modifiedAtt, prevValue, "%", currValue, "%");
		}
		
		// return the flat attribute
		return String.format("%s    %d ---> %d", attribute, Math.round(prevValue), Math.round(currValue));
	}
	
	public String formatValue(String attribute, double value) {
		// checks if the attribute is percentage
		if(attribute.substring(attribute.length() - 1).equals("%")) {
			return String.format("%.1f", value) + "%";
		}
		
		// return the flat attribute
		return String.valueOf(Math.round(value));
	}
	
	public boolean isUnique(String... attributes) {
		HashMap<String, Integer> map = new HashMap<>();
		
		for(String attribute : attributes) {
			// append the attribute to the hash map
			map.put(attribute, 1);
		}

	    // If map size is equal to array of attributes, all attributes are unique
	    return map.size() == attributes.length;
	}
	
	// GENERATING RANDOM ATTRIBUTE
	
	private String generatedAttribute(List<Stat> listAttribute) {
		double attributeChance = generateNumber();
		double cumulativeProbability = 0;
		
		for(int i = 0; i < listAttribute.size(); i++) {
			cumulativeProbability += listAttribute.get(i).getProbability();
			if(attributeChance < cumulativeProbability) {
				return listAttribute.get(i).getAttribute();
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
		
		for(int i = 0; i < attributeValue.length; i++) {
			cumulativeProbability += probabilities[i];
			if(valueChance < cumulativeProbability) {
				return attributeValue[i];
			}
		}
		
		// If we reach here, something went wrong, so just return the last element
		return attributeValue[0];
	}
	
	// NUMBER GENERATOR
	// Generates number from 0.00 to 99.999999999999...
	public double generateNumber() {
	    return new Random().nextDouble() * 100;
	}
}
