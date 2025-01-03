import java.util.Arrays;

public final class Attribute {
	private static Attribute instance;
	
	private static final double[] HP_FLAT_VALUE = { 209.13, 239.00, 268.88, 298.75 };
	private static final double[] ATK_FLAT_VALUE = { 13.62, 15.56, 17.51, 19.45 };
	private static final double[] DEF_FLAT_VALUE = { 16.20, 18.52, 20.83, 23.15 };
	private static final double[] HP_PER_VALUE = { 4.08, 4.66, 5.25, 5.83 };
	private static final double[] ATK_PER_VALUE = { 4.08, 4.66, 5.25, 5.83 };
	private static final double[] DEF_PER_VALUE = { 5.10, 5.83, 6.56, 7.29 };
	private static final double[] ENERGY_RECHARGE_VALUE = { 4.53, 5.18, 5.83, 6.48 };
	private static final double[] ELEMENTAL_MASTERY_VALUE = { 16.32, 18.65, 20.98, 23.31 };
	private static final double[] CRIT_RATE_VALUE = { 2.72, 3.11, 3.50, 3.89 };
	private static final double[] CRIT_DMG_VALUE = { 5.44, 6.22, 6.99, 7.77 };
	
	public static final String HP_FLAT = "HP";
	public static final String ATK_FLAT = "ATK";
	public static final String DEF_FLAT = "DEF";
	public static final String HP_PER = "HP%";
	public static final String ATK_PER = "ATK%";
	public static final String DEF_PER = "DEF%";
	public static final String ENERGY_RECHARGE = "Energy Recharge%";
	public static final String ELEMENTAL_MASTERY = "Elemental Mastery";
	public static final String CRIT_RATE = "CRIT Rate%";
	public static final String CRIT_DMG = "CRIT DMG%";
	public static final String PYRO_DMG_BONUS = "Pyro DMG Bonus%";
	public static final String ELECTRO_DMG_BONUS = "Electro DMG Bonus%";
	public static final String CRYO_DMG_BONUS = "Cryo DMG Bonus%";
	public static final String HYDRO_DMG_BONUS = "Hydro DMG Bonus%";
	public static final String DENDRO_DMG_BONUS = "Dendro DMG Bonus%";
	public static final String ANEMO_DMG_BONUS = "Anemo DMG Bonus%";
	public static final String GEO_DMG_BONUS = "Geo DMG Bonus%";
	public static final String PHYSICAL_DMG_BONUS = "Physical DMG Bonus%";
	public static final String HEALING_BONUS = "Healing Bonus%";
	
	public static final AttributeValueStat[] VALUE_STATS = {
			new AttributeValueStat(HP_FLAT, HP_FLAT_VALUE),
			new AttributeValueStat(ATK_FLAT, ATK_FLAT_VALUE),
			new AttributeValueStat(DEF_FLAT, DEF_FLAT_VALUE),
			new AttributeValueStat(HP_PER, HP_PER_VALUE),
			new AttributeValueStat(ATK_PER, ATK_PER_VALUE),
			new AttributeValueStat(DEF_PER, DEF_PER_VALUE),
			new AttributeValueStat(ENERGY_RECHARGE, ENERGY_RECHARGE_VALUE),
			new AttributeValueStat(ELEMENTAL_MASTERY, ELEMENTAL_MASTERY_VALUE),
			new AttributeValueStat(CRIT_RATE, CRIT_RATE_VALUE),
			new AttributeValueStat(CRIT_DMG, CRIT_DMG_VALUE)
	};
	public static final String[] ATTRIBUTE_NAMES = { 
			HP_FLAT, 
			ATK_FLAT, 
			DEF_FLAT, 
			HP_PER, 
			ATK_PER, 
			DEF_PER, 
			ENERGY_RECHARGE, 
			ELEMENTAL_MASTERY,
			CRIT_RATE, 
			CRIT_DMG 
	};
	public static final String[] SPECIAL_ATTRIBUTE_NAMES = {
			PYRO_DMG_BONUS, 
			ELECTRO_DMG_BONUS, 
			CRYO_DMG_BONUS,
			HYDRO_DMG_BONUS, 
			DENDRO_DMG_BONUS, 
			ANEMO_DMG_BONUS,
			GEO_DMG_BONUS, 
			PHYSICAL_DMG_BONUS, 
			HEALING_BONUS 
	};
	
	private Attribute() {}
	
	public static Attribute getInstance() {
		if (instance == null) {
			synchronized (Attribute.class) {
				if (instance == null) {
					instance = new Attribute();
				}
			}
		}
		
		return instance;
	}
	
	public double[] getAttributeValues(String attributeName) throws NullPointerException, IllegalArgumentException {
		AttributeValueStat retrievedValueStat = null;
		
		if (attributeName == null) {
			throw new NullPointerException("Attribute name cannot be null");
		}
		
		for (AttributeValueStat currValueStat : VALUE_STATS) {
			String currentAttributeName = currValueStat.getAttributeName();
			
			if (currentAttributeName.equals(attributeName)) {
				retrievedValueStat = currValueStat;
				break;
			}
		}
		
		if (retrievedValueStat == null) {
			throw new IllegalArgumentException("Invalid attributeName: " + attributeName);
		} else {
			return retrievedValueStat.getAttributeValues();
		}
	}
	
	public boolean isNotSpecialAttributeName(String attributeName) throws NullPointerException {
		if (attributeName == null) {
			throw new NullPointerException("Attribute name cannot be null");
		}
		
		boolean isNotSpecialAttributeName = false;
		
		for (String currAttributeName : ATTRIBUTE_NAMES) {
			if (attributeName.equals(currAttributeName)) {
				isNotSpecialAttributeName = true;
				break;
			}
		}
		
		return isNotSpecialAttributeName;
	}
	
	// Helper method to convert double[] to Double[]
    private Double[] toDoubleArray(double[] values) {
        return Arrays.stream(values).boxed().toArray(Double[]::new);
    }

    public Double[] getHpPer() {
        return toDoubleArray(HP_PER_VALUE);
    }

    public Double[] getAtkPer() {
        return toDoubleArray(ATK_PER_VALUE);
    }

    public Double[] getDefPer() {
        return toDoubleArray(DEF_PER_VALUE);
    }

    public Double[] getHpFlat() {
        return toDoubleArray(HP_FLAT_VALUE);
    }

    public Double[] getAtkFlat() {
        return toDoubleArray(ATK_FLAT_VALUE);
    }

    public Double[] getDefFlat() {
        return toDoubleArray(DEF_FLAT_VALUE);
    }

    public Double[] getEnergyRecharge() {
        return toDoubleArray(ENERGY_RECHARGE_VALUE);
    }

    public Double[] getElementalMastery() {
        return toDoubleArray(ELEMENTAL_MASTERY_VALUE);
    }

    public Double[] getCritRate() {
        return toDoubleArray(CRIT_RATE_VALUE);
    }

    public Double[] getCritDamage() {
        return toDoubleArray(CRIT_DMG_VALUE);
    }
}
